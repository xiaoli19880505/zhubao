package com.sys.service.apply;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.sys.common.BatchUtil;
import com.sys.common.CommonUtils;
import com.sys.common.PropertiesUtil;
import com.sys.common.StringUtils;
import com.sys.mapper.*;
import com.sys.mapper.apply.ApplyFamilyHouseMapper;
import com.sys.mapper.apply.ApplyFamilyMemberMapper;
import com.sys.mapper.apply.ApplyForForeignMapper;
import com.sys.mapper.apply.ApplyUnitMapper;
import com.sys.mapper.apply.ApproveMapper;
import com.sys.pojo.*;
import com.github.pagehelper.PageInfo;
import com.sys.pojo.apply.*;
import com.sys.service.BaseService;
import com.sys.service.FlowService;
import com.sys.service.ListNumberService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Desc:desc
 * @Author:wangli
 * @CreateTime:10:10 2018/10/13
 */
@Service
public class ApplyForForeignService extends BaseService<ApplyForForeign> {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ListNumberService listNumberService;

    @Autowired
    private ApplyFamilyMemberMapper applyFamilyMemberMapper;

    @Autowired
    private ApplyFamilyHouseMapper applyFamilyHouseMapper;

    @Autowired
    private FlowService flowService;

    @Autowired
    private ApproveMapper approveMapper;

    @Autowired
    private ApplyForForeignMapper applyForForeignMapper;

    @Autowired
    private VolelearcDtlMapper volelearcDtlMapper;

    @Autowired
    private VolelearcMapper volelearcMapper;


    @Autowired
    private ApplyUnitMapper applyUnitMapper;

    @Autowired
    private BatchUtil batchUtil;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;


    public void getEntity(String id){
        ApplyForForeign applyForForeign = this.createInstanceAndSetIdOfFirstGeneric(id);
    }

    public Object selectByCondition(ApplyForForeign applyForForeign,String page,String rows){
        //分页条件
        PageHelper.startPage(Integer.parseInt(page),
                Integer.parseInt(rows));
        List<ApplyForForeign> applyForForeigns = applyForForeignMapper.selectByCondition(applyForForeign);
        return new PageInfo<ApplyForForeign>(applyForForeigns);
    }

    public ApplyForForeign selectByMap(Map<String,Object> map){
        return applyForForeignMapper.selectByMap(map);
    }

    /**
     * 添加外来务工申请单信息
     * @param pojo
     */
    public String addApplyForForeign(ApplyForForeign pojo, HttpServletRequest request){
        int updateColumn = 0;

        /*生成并设置申请编号*/
        String applytype= PropertiesUtil.getApplyTypeProperties("wailaiwg");
        List<ApplyFamilyMember> members = pojo.getApplyFamilyMembers();//获取所有家庭成员信息
        //判断有无已经申请的单子
        //查询是否存在相同的申请信息 申请类别
        if(StringUtils.isEmpty(applytype)){
            logger.error("当前信息申请类型为空!");
            return "false";
        }
        //查询是否存在相同的申请信息 身份证
        if(members==null || members.get(0)==null || StringUtils.isEmpty(members.get(0).getAfmSfzh())){
            logger.error("当前信息身份证为空!");
            return "false";
        }
        Map<String,Object> inputMap = new HashMap<String, Object>();
        inputMap.put("sfzh",members.get(0).getAfmSfzh());
        inputMap.put("apsqlb",applytype);
        Integer num = approveMapper.selectForDeWeighting(inputMap);
        if(num>0){
            logger.error("当前信息" + members.get(0).getAfmSfzh() + "已经申请!");
            return "false";
        }

        /**
         * 在添加之前，先判断用户是否有申请的外来务工单没有走完流程，如果存在，则不得申请；
         * 流程走完的标志是已经分配完房子，并且备案
         * 此块的业务逻辑留由以后再开发
         */
        String appid = CommonUtils.getUUIDWith_();//生成申请单id
        pojo.setAffId(appid);//设置申请单id


        String sqbh = this.listNumberService.addNewBM(pojo.getAffSsq(), applytype);
        pojo.setAffSqbh(sqbh);


        /*插入家庭成员信息*/
        for (ApplyFamilyMember tempMem:members){
            tempMem.setAfmId(CommonUtils.getUUIDWith_());
            tempMem.setAfmSqid(appid);
            // this.applyFamilyMemberMapper.insert(tempMem);
        }
        /*设置申请人id*/
        pojo.setAffSqrid(members.get(0).getAfmId());
        members.get(0).setAfmYsqrgx("本人");
        if(members.size()>1){
            members.get(1).setAfmYsqrgx("配偶");
        }
        /*插入外来务工申请单*/
        updateColumn=super.insert(pojo);

        batchUtil.save(members,"apply.ApplyFamilyMemberMapper");


        /*设置申请住房信息，并插入住房信息*/
        ApplyFamilyHouse applyHouse = pojo.getApplyFamilyHouse();
        applyHouse.setAfhSqid(appid);
        applyHouse.setAfhId(CommonUtils.getUUIDWith_());
        applyFamilyHouseMapper.insert(applyHouse);

        /*设置申请人单位信息，并插入单位表*/
        ApplyUnit applyUnit = pojo.getApplyUnit();
        applyUnit.setUnitid(CommonUtils.getUUIDWith_());
        applyUnit.setAplid(appid);
        applyUnit.setApltype(applytype);
        applyUnitMapper.insert(applyUnit);


        if(updateColumn>0){
            /*创建审批单,启动流程实例*/
            Approve approve = new Approve();//审批单实体
            approve.setApldate(new Date());//设置审批单的申请日期
            approve.setAplid(appid);//设置审批单对应的申请单id
            approve.setAplbh(sqbh);//设置申请编号
            approve.setAptype(applytype);//设置审批单对应的申请业务类别，外来公务人员申请为4
            approve.setApvid(CommonUtils.getUUIDWith_());//设置主键uuid
            /*设置申请用户id，从session读取登录的申请用户信息，将其id设置于审批单 */
            ApplyUserinfo applyUserinfo = (ApplyUserinfo)request.getSession().getAttribute("applyUserinfo");
            approve.setApluserid(applyUserinfo.getUserid());
            //approve.setApluserid("7f169411-4799-4f7e-92a8-337ade59d534");
            Map<String,Object> conditionMap = Maps.newHashMap();
            conditionMap.put("applyUsername",applyUserinfo.getUsername());
            conditionMap.put("applyUsercard",applyUserinfo.getSfzh());
           // conditionMap.put("applyUserSsq",applyUserinfo.getSsq());
            conditionMap.put("applyUserSsq",pojo.getAffSsq());
            //conditionMap.put("applyUserJd",applyUserinfo.getSsj());
            conditionMap.put("applyUserJd",pojo.getAffDwdz());
            conditionMap.put("aplb","cs");
            conditionMap.put("applyType",applytype);
            this.flowService.addProcessInstance(applytype,approve,conditionMap);//添加流程实例，并且将流程实例设置于审批单实体
            this.approveMapper.insert(approve);//添加审批单
            return "success";
        }else{
            return "false";
        }
    }


    public String updateApplyForForeign(ApplyForForeign pojo, HttpServletRequest request){
        int updateColumn = 0;


        //删除家庭成员信息
        applyFamilyMemberMapper.deleteFamilyMember(pojo.getAffId());
        //获取所有家庭成员信息
        List<ApplyFamilyMember> members = pojo.getApplyFamilyMembers();
        for (ApplyFamilyMember tempMem:members){
            tempMem.setAfmId(CommonUtils.getUUIDWith_());
            tempMem.setAfmSqid(pojo.getAffId());
        }
        /*设置申请人id*/
        pojo.setAffSqrid(members.get(0).getAfmId());
        members.get(0).setAfmYsqrgx("本人");
        //添加家庭成员信息
        batchUtil.save(members,"apply.ApplyFamilyMemberMapper");
        //更新家庭住房信息
        applyFamilyHouseMapper.updateHouseByApplyId(pojo.getApplyFamilyHouse());
        //更新申请人单位信息
        applyUnitMapper.update(pojo.getApplyUnit());
        updateColumn=applyForForeignMapper.update(pojo);
        //获取附件信息
//        List<Volelearc> volelearcs=pojo.getVolList();
//        //获取附件的id集合
//        List<String> ids=new ArrayList<String>();
//        for (Volelearc v:volelearcs) {
//            ids.add(v.getElearcvolId());
//        }
//        //根据附件id集合删除附件详细
//        volelearcDtlMapper.deleteVoleearcDtl(ids);
//        //根据申请id删除附件信息
//        volelearcMapper.delete(pojo.getAffId());

        if(updateColumn>0){
            Approve approve=approveMapper.findByApplyId(pojo.getAffId());
            /*更新完毕后重新激活挂起的流程实例*/
            String processInstanceId = approve.getProcessinstanceid();
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
           /*如果流程是挂起状态，则重新激活*/
            if(pi.isSuspended()){
                this.runtimeService.activateProcessInstanceById(processInstanceId);
           }
            approve.setState("");
            approveMapper.update(approve);
            return "success";
        }else{
            return "false";
        }


    }

    /**
     * 删除申请单及其关联表信息
     * @param applfid
     * @return
     */
    public  String deleteApplyInfo(String applfid){
        int delCount = 0 ;
        ApplyForForeign pojo = this.applyForForeignMapper.selectById(applfid);
        //删除家庭成员信息
        applyFamilyMemberMapper.deleteFamilyMember(applfid);

        Map<String,Object> conditionMap = new HashMap<String,Object>();
        conditionMap.put("applyid",applfid);
        //删除家庭住房信息
        applyFamilyHouseMapper.deleteByMap(conditionMap);
        //删除单位信息
        applyUnitMapper.deleteByMap(conditionMap);
        List<Volelearc> volelearcs=pojo.getVolList();
        //获取附件的id集合
        List<String> ids=new ArrayList<String>();
        for (Volelearc v:volelearcs) {
            ids.add(v.getElearcvolId());
        }
        //根据附件id集合删除附件详细
        volelearcDtlMapper.deleteVoleearcDtl(ids);
        //根据申请id删除附件信息
        volelearcMapper.delete(applfid);

        /*删除审批单*/
        Approve approve=approveMapper.findByApplyId(pojo.getAffId());
        approveMapper.delete(approve.getApvid());

        /**
         * 删除流程实例
         */
        String processInstanceId = approve.getProcessinstanceid();
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        /*如果流程实例已经不存在，则删除历史流程实例；如果存在，先删除当前流程实例，再删除历史流程实例*/
        if(pi==null){
            historyService.deleteHistoricProcessInstance(processInstanceId);//删除历史流程实例
        }else{
            runtimeService.deleteProcessInstance(processInstanceId,"");//删除正在运行的流程实例
            historyService.deleteHistoricProcessInstance(processInstanceId);
        }

        delCount = this.applyForForeignMapper.delete(applfid);
        if(delCount>0){
            return "success";
        }else{
            return "false";
        }
    }

    /**
     * 老数据的查询
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectOldListByMap(Map map){
        return this.applyForForeignMapper.selectOldListByMap(map);
    }
}