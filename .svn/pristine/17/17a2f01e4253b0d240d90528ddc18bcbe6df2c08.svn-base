package com.sys.service.apply;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sys.common.BatchUtil;
import com.sys.common.CommonUtils;
import com.sys.common.PropertiesUtil;
import com.sys.common.StringUtils;
import com.sys.mapper.VolelearcDtlMapper;
import com.sys.mapper.VolelearcMapper;
import com.sys.mapper.apply.*;
import com.sys.pojo.*;
import com.sys.pojo.apply.*;
import com.sys.service.BaseService;
import com.sys.service.FlowService;
import com.sys.service.ListNumberService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class ApplyButieService extends BaseService<ApplyButie> {

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
    private ApplyButieMapper applyButieMapper;

    @Autowired
    private BatchUtil batchUtil;

    @Autowired
    private ApplyFamilyHouseChangeMapper applyFamilyHouseChangeMapper;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private VolelearcDtlMapper volelearcDtlMapper;

    @Autowired
    private VolelearcMapper volelearcMapper;

    @Autowired
    private ApplyUnitMapper applyUnitMapper;


    public ApplyButie selectByMap(Map<String,Object> map){
        return applyButieMapper.selectByMap(map);
    }

    /**
     * 住房补贴备案管理
     * @param applyButie
     * @param page
     * @param rows
     * @return
     */
    public Object selectByCondition(ApplyButie applyButie,String page,String rows){
        PageHelper.startPage(Integer.parseInt(page),
                Integer.parseInt(rows));
        List<ApplyButie> applyButies = applyButieMapper.selectByCondition(applyButie);
        return new PageInfo<ApplyButie>(applyButies);
    }

    /**
     * 添加外来务工申请单信息
     * @param applyButie
     */
    public String addApplyForForeign(ApplyButie applyButie, HttpServletRequest request){
        int updateColumn = 0;
        /*生成并设置申请编号*/
        String applytype= PropertiesUtil.getApplyTypeProperties("butie");
        List<ApplyFamilyMember> members = applyButie.getApplyFamilyMembers();//获取所有家庭成员信息
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
         * 在添加之前，先判断用户是否有申请的住房补贴单没有走完流程，如果存在，则不得申请；
         * 流程走完的标志是已经分配完房子，并且备案
         * 此块的业务逻辑留由以后再开发
         */
        String appid = CommonUtils.getUUIDWith_();//生成申请单id
        applyButie.setAbId(appid);//设置申请单id


        String abSqbh = this.listNumberService.addNewBM(applyButie.getAbSsq(),applytype);
        applyButie.setAbSqbh(abSqbh);



        /*插入家庭成员信息*/
        for (ApplyFamilyMember tempMem:members){
            tempMem.setAfmId(CommonUtils.getUUIDWith_());
            tempMem.setAfmSqid(appid);
        }
        /*设置申请人id*/
        applyButie.setAbSqrid(members.get(0).getAfmId());
        //与申请人关系配偶--0
        applyButie.setAbSqrpoid(members.get(1).getAfmId());
        members.get(0).setAfmYsqrgx("本人");
        if(members.size()>1){
            members.get(1).setAfmYsqrgx("配偶");
        }
        /*插入住房补贴申请单*/
        updateColumn=super.insert(applyButie);
        batchUtil.save(members,"apply.ApplyFamilyMemberMapper");//批量插入家庭成员信息

        /*设置申请住房信息*/
        List<ApplyFamilyHouse> applyHouse = applyButie.getApplyFamilyHouses();
        for(ApplyFamilyHouse ApplyFamilyHouse : applyHouse){
            ApplyFamilyHouse.setAfhSqid(appid);
            ApplyFamilyHouse.setAfhId(CommonUtils.getUUIDWith_());
        }
        batchUtil.save(applyHouse,"apply.ApplyFamilyHouseMapper");//批量插入住房信息

        /*设置申请人单位信息，并插入单位表*/
        ApplyUnit applyUnit = applyButie.getApplyUnit();
        applyUnit.setUnitid(CommonUtils.getUUIDWith_());
        applyUnit.setAplid(appid);
        applyUnit.setApltype(applytype);
        applyUnitMapper.insert(applyUnit);

        /*设置更换住房信息并插入*/
        ApplyFamilyHouseChange applyFamilyHouseChange = applyButie.getApplyFamilyHouseChange();
        applyFamilyHouseChange.setAfhcId(CommonUtils.getUUIDWith_());
        applyFamilyHouseChange.setAfhcSqid(appid);
        applyFamilyHouseChangeMapper.insert(applyFamilyHouseChange);

        if(updateColumn>0){
            //创建审批单,启动流程实例
            Approve approve = new Approve();//审批单实体
            approve.setApldate(new Date());//设置审批单的申请日期
            approve.setAplbh(abSqbh);//设置申请编号
            approve.setAplid(appid);//设置审批单对应的申请单id
            approve.setAptype(applytype);//设置审批单对应的申请业务类别，外来公务人员申请为4
            approve.setApvid(CommonUtils.getUUIDWith_());//设置主键uuid
            //设置申请用户id，从session读取登录的申请用户信息，将其id设置于审批单
            ApplyUserinfo applyUserinfo = (ApplyUserinfo)request.getSession().getAttribute("applyUserinfo");
            approve.setApluserid(applyUserinfo.getUserid());
           // approve.setApluserid("0101010101010101");
            /*添加流程审批单以及流程申请*/
            Map<String,Object> conditionMap = Maps.newHashMap();
            conditionMap.put("applyUsername",applyUserinfo.getUsername());
            conditionMap.put("applyUsercard",applyUserinfo.getSfzh());
            //conditionMap.put("applyUserSsq",applyUserinfo.getSsq());
            conditionMap.put("applyUserSsq",applyButie.getAbSsq());
            //conditionMap.put("applyUserJd",applyUserinfo.getSsj());
            conditionMap.put("applyUserJd",applyButie.getAbJdbsc());
            conditionMap.put("aplb","cs");
            conditionMap.put("applyType",applytype);
            this.flowService.addProcessInstance(applytype,approve,conditionMap);//添加流程实例，并且将流程实例设置于审批单实体
            this.approveMapper.insert(approve);//添加审批单
            return "success";
        }else{
            return "false";
        }
    }

    /**
     * 更新补贴住房申请信息
     * @param
     * @return
     */
    public String updateApplyButieInfo(ApplyButie pojo){
        int updateColumn = 0;

        //删除家庭成员信息
        applyFamilyMemberMapper.deleteFamilyMember(pojo.getAbId());
        //获取所有家庭成员信息
        List<ApplyFamilyMember> members = pojo.getApplyFamilyMembers();
        /*插入家庭成员信息*/
        for (ApplyFamilyMember tempMem:members){
            tempMem.setAfmId(CommonUtils.getUUIDWith_());
            tempMem.setAfmSqid(pojo.getAbId());
        }
        /*设置申请人id*/
        pojo.setAbSqrid(members.get(0).getAfmId());
        //与申请人关系配偶--0
        pojo.setAbSqrpoid(members.get(1).getAfmId());
        members.get(0).setAfmYsqrgx("本人");
        members.get(1).setAfmYsqrgx("配偶");
        //添加家庭成员信息
        batchUtil.save(members,"apply.ApplyFamilyMemberMapper");
        //删除住房信息
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("applyid",pojo.getAbId());
        applyFamilyHouseMapper.deleteByMap(map);
        //更新申请人单位信息
        applyUnitMapper.update(pojo.getApplyUnit());
        //获取住房信息
        List<ApplyFamilyHouse> applyHouse = pojo.getApplyFamilyHouses();
        for(ApplyFamilyHouse ApplyFamilyHouse : applyHouse){
            ApplyFamilyHouse.setAfhSqid(pojo.getAbId());
            ApplyFamilyHouse.setAfhId(CommonUtils.getUUIDWith_());
        }
        //批量插入住房信息
        batchUtil.save(applyHouse,"apply.ApplyFamilyHouseMapper");
        //获取房屋变更
        ApplyFamilyHouseChange applyFamilyHouseChange = pojo.getApplyFamilyHouseChange();
        //更新房屋变更信息
        applyFamilyHouseChangeMapper.update(applyFamilyHouseChange);
        updateColumn=applyButieMapper.update(pojo);
//        //获取附件信息
//        List<Volelearc> volelearcs=pojo.getVolList();
//        //获取附件的id集合
//        List<String> ids=new ArrayList<String>();
//        for (Volelearc v:volelearcs) {
//            ids.add(v.getElearcvolId());
//        }
//        //根据附件id集合删除附件详细
//        volelearcDtlMapper.deleteVoleearcDtl(ids);
//        //根据申请id删除附件信息
//        volelearcMapper.delete(pojo.getAbId());

        if(updateColumn>0){
            Approve approve=approveMapper.findByApplyId(pojo.getAbId());
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

}
