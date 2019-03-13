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
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.Volelearc;
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

/**
 * @Desc:（经适房、廉租房）申请单service类
 * @Author:wangli
 * @CreateTime:17:37 2018/10/17
 */
@Service
public class ApplyService extends BaseService<Apply> {

    private Logger logger = Logger.getLogger(ApplyService.class);

    @Autowired
    private ListNumberService listNumberService;

    @Autowired
    private BatchUtil batchUtil;

    @Autowired
    private ApplyFamilyHouseChangeMapper applyFamilyHouseChangeMapper;

    @Autowired
    private FlowService flowService;

    @Autowired
    private ApproveMapper approveMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private ApplyFamilyMemberMapper applyFamilyMemberMapper;

    @Autowired
    private ApplyFamilyHouseMapper applyFamilyHouseMapper;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private VolelearcDtlMapper volelearcDtlMapper;

    @Autowired
    private VolelearcMapper volelearcMapper;

    @Autowired
    private ApplyUnitMapper applyUnitMapper;

    public Apply selectByMap(Map<String,Object> map){
        return applyMapper.selectByMap(map);
    }

    public Object selectByCondition(Apply apply,String page,String rows){
        //分页条件
        PageHelper.startPage(Integer.parseInt(page),
                Integer.parseInt(rows));
        List<Apply> applys = applyMapper.selectByCondition(apply);
        return new PageInfo<Apply>(applys);
    }


    /**
     * 添加申请信息
     * @return
     */
    public String addApply(Apply pojo, HttpServletRequest request){
        /*生成并设置申请编号*/
        String applytype= pojo.getApSqlb();// 申请类别，选择//PropertiesUtil.getApplyTypeProperties("gongzf");
        List<ApplyFamilyMember> members = pojo.getApplyFamilyMembers();//获取所有家庭成员信息
        //查询是否存在相同的申请信息 申请类别
        if(StringUtils.isEmpty(applytype)){
            logger.error("当前信息申请类型为空!");
            return "false";
        }

        logger.debug(pojo.getApTsjt());
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

        int updateColumn = 0;
        /**
         * 在添加之前，先判断用户是否有申请的外来务工单没有走完流程，如果存在，则不得申请；
         * 流程走完的标志是已经分配完房子，并且备案
         * 此块的业务逻辑留由以后再开发
         */
        String appid = CommonUtils.getUUIDWith_();//生成申请单id
        pojo.setApId(appid);//设置申请单id

        String sqbh = this.listNumberService.addNewBM(pojo.getApSsq(), applytype);
        pojo.setApSqbh(sqbh);



        /*插入家庭成员信息*/
        for (ApplyFamilyMember tempMem:members){
            tempMem.setAfmId(CommonUtils.getUUIDWith_());
            tempMem.setAfmSqid(appid);
            // this.applyFamilyMemberMapper.insert(tempMem);
        }
        /*设置申请人id和申请人配偶id*/
        pojo.setApSqrid(members.get(0).getAfmId());
        members.get(0).setAfmYsqrgx("本人");
        if(members.size()>1){
            members.get(1).setAfmYsqrgx("配偶");
            pojo.setApSqrpoid(members.get(1).getAfmId());
        }
        /*if(applytype.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))){
            pojo.setApSqrpoid(members.get(1).getAfmId());
        }else{
            for (int i = 0; i <members.size() ; i++) {
                if(members.get(i).getAfmYsqrgx().equals("配偶")){
                    pojo.setApSqrpoid(members.get(i).getAfmId());
                    break;
                }
            }
        }*/


        //与申请人关系--0 配偶

//        members.get(1).setAfmYsqrgx("配偶");
        /*插入申请单*/
        updateColumn=super.insert(pojo);
        batchUtil.save(members,"apply.ApplyFamilyMemberMapper");//批量插入家庭成员信息


        /*设置申请住房信息，并插入住房信息*/
        List<ApplyFamilyHouse> applyHouse = pojo.getApplyFamilyHouses();
        for(ApplyFamilyHouse tempHou:applyHouse){
            tempHou.setAfhSqid(appid);
            tempHou.setAfhId(CommonUtils.getUUIDWith_());
        }
        batchUtil.save(applyHouse,"apply.ApplyFamilyHouseMapper");//批量插入住房信息

        /*设置申请人单位信息，并插入单位表*/
        ApplyUnit applyUnit = pojo.getApplyUnit();
        applyUnit.setUnitid(CommonUtils.getUUIDWith_());
        applyUnit.setAplid(appid);
        applyUnit.setApltype(applytype);
        applyUnitMapper.insert(applyUnit);

        /*设置更换住房信息并插入*/
       ApplyFamilyHouseChange applyFamilyHouseChange = pojo.getApplyFamilyHouseChange();
        applyFamilyHouseChange.setAfhcId(CommonUtils.getUUIDWith_());
       applyFamilyHouseChange.setAfhcSqid(appid);
       applyFamilyHouseChangeMapper.insert(applyFamilyHouseChange);

        if(updateColumn>0){
            /*创建审批单,启动流程实例*/
            Approve approve = new Approve();//审批单实体
            approve.setApldate(new Date());//设置审批单的申请日期
            approve.setAplbh(sqbh);//设置申请编号
            approve.setAplid(appid);//设置审批单对应的申请单id
            approve.setAptype(applytype);//设置审批单对应的申请业务类别，外来公务人员申请为4
            approve.setApvid(CommonUtils.getUUIDWith_());//设置主键uuid
           // 设置申请用户id，从session读取登录的申请用户信息，将其id设置于审批单
            ApplyUserinfo applyUserinfo = (ApplyUserinfo)request.getSession().getAttribute("applyUserinfo");
            approve.setApluserid(applyUserinfo.getUserid());
            Map<String,Object> conditionMap = Maps.newHashMap();
            conditionMap.put("applyUsername",applyUserinfo.getUsername());
            conditionMap.put("applyUsercard",applyUserinfo.getSfzh());
            //conditionMap.put("applyUserSsq",applyUserinfo.getSsq());
            //conditionMap.put("applyUserJd",applyUserinfo.getSsj());
            conditionMap.put("applyUserSsq",pojo.getApSsq());
            conditionMap.put("applyUserJd",pojo.getApJdbsc());
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
     * 提交经济适用房修改后的申请信息
     * @param pojo
     * @return
     */
    public String updateApplyInfo(Apply pojo){
        int updateColumn = 0;

        logger.debug(pojo.getApTsjt());

        //删除家庭成员信息
        applyFamilyMemberMapper.deleteFamilyMember(pojo.getApId());
        //获取所有家庭成员信息
        List<ApplyFamilyMember> members = pojo.getApplyFamilyMembers();
        for (ApplyFamilyMember tempMem:members){
            tempMem.setAfmId(CommonUtils.getUUIDWith_());
            tempMem.setAfmSqid(pojo.getApId());
            // this.applyFamilyMemberMapper.insert(tempMem);
        }
        /*设置申请人id和申请人配偶id*/
        pojo.setApSqrid(members.get(0).getAfmId());
//        pojo.setApSqrpoid(members.get(1).getAfmId());
//        //与申请人关系--0 配偶
//        members.get(1).setAfmYsqrgx("0");


        members.get(0).setAfmYsqrgx("本人");
        if(pojo.getApSqlb().equals(PropertiesUtil.getApplyTypeProperties("jingsf"))){
            pojo.setApSqrpoid(members.get(1).getAfmId());
        }else{
            for (int i = 0; i <members.size() ; i++) {
                if(members.get(i).getAfmYsqrgx().equals("配偶")){
                    pojo.setApSqrpoid(members.get(i).getAfmId());
                    break;
                }
            }
        }

        //添加家庭成员信息
        batchUtil.save(members,"apply.ApplyFamilyMemberMapper");
        //更新申请人单位信息
        applyUnitMapper.update(pojo.getApplyUnit());
        //删除住房信息
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("applyid",pojo.getApId());
        applyFamilyHouseMapper.deleteByMap(map);
        //获取住房信息
        List<ApplyFamilyHouse> applyHouse = pojo.getApplyFamilyHouses();
        for(ApplyFamilyHouse ApplyFamilyHouse : applyHouse){
            ApplyFamilyHouse.setAfhSqid(pojo.getApId());
            ApplyFamilyHouse.setAfhId(CommonUtils.getUUIDWith_());
        }
        //批量插入住房信息
        batchUtil.save(applyHouse,"apply.ApplyFamilyHouseMapper");
        //获取房屋变更
        ApplyFamilyHouseChange applyFamilyHouseChange = pojo.getApplyFamilyHouseChange();
        //更新房屋变更信息
        applyFamilyHouseChangeMapper.update(applyFamilyHouseChange);
        updateColumn=applyMapper.update(pojo);
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
//        volelearcMapper.delete(pojo.getApId());

        if(updateColumn>0){
            Approve approve=approveMapper.findByApplyId(pojo.getApId());
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

   /* *//**
     * 批量更新申请单状态（用于摇号，更新摇中状态为3）
     * @param list
     * @return
     *//*
    public int updateApplyLcBatch11(List< Map<String,Object>> list){
        return this.applyMapper.updateApplyLcBatch(list);
    }*/

}