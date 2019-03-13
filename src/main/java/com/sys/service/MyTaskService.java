package com.sys.service;

import com.google.common.collect.Maps;
import com.sys.common.*;
import com.sys.common.dataconver.BaseInfoDataConvertor;
import com.sys.common.encrypt.Base64Util;
import com.sys.mapper.ApplyUserInfoFormMapper;
import com.sys.mapper.ApplyUserinfoMapper;
import com.sys.mapper.RoleInfoMapper;
import com.sys.mapper.SourceHouseMapper;
import com.sys.mapper.apply.*;
import com.sys.mapper.blagsh.BliveGongSMapper;
import com.sys.mapper.contract.ContractDetailMapper;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.Message;
import com.sys.pojo.UserInfo;
import com.sys.pojo.apply.*;
import com.sys.pojo.blagsh.BliveGongS;
import com.sys.pojo.contract.ContractDetail;
import com.sys.service.apply.ApplyButieService;
import com.sys.service.apply.ApplyChangeService;
import com.sys.service.common.MessageAndFormService;
import com.sys.service.blags.BliveGongSService;
import com.sys.service.schedule.ScheduleJobDomain;
import com.sys.service.schedule.ScheduleJobService;
import oracle.sql.DATE;
import com.sys.service.blags.BliveGongSService;
import com.sys.service.common.MessageAndFormService;
import oracle.sql.DATE;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.commons.collections4.map.LinkedMap;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Desc:用户任务管理service
 * @Author:wangli
 * @CreateTime:16:30 2018/10/22
 */
@Service
public class MyTaskService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ApproveMapper approveMapper;

    @Autowired
    private RoleInfoMapper roleInfoMapper;

    @Autowired
    private ApplyUserinfoMapper applyUserinfoMapper;

    @Autowired
    private FlowService flowService;

    @Autowired
    private MessageAndFormService messageAndFormService;

    @Autowired
    private ApplyUserInfoFormMapper applyUserInfoFormMapper;

    @Autowired
    private BliveGongSMapper bliveGongSMapper;

    @Autowired
    private ApplyButieService applyButieService;

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private SourceHouseMapper sourceHouseMapper;

    @Autowired
    private ApplyNsMapper applyNsMapper;

    @Autowired
    private ApplyChangeService applyChangeService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ContractDetailMapper contractDetailMapper;

    @Autowired
    private ScheduleJobService scheduleJobService;

    @Autowired
    private ApproveRecordMapper approveRecordMapper;

    @Autowired
    private ApplyForgraDuateMapper applyForgraDuateMapper;

    @Autowired
    private ApplyForForeignMapper applyForForeignMapper;

    /**
     * 审批用户
     * @param request
     */
    // @Transactional(readOnly = false, propagation = Propagation.REQUIRED ,rollbackFor=RuntimeException.class)
    public int updateTask(HttpServletRequest request) throws SchedulerException {
        String processInstanceId = request.getParameter("processInstanceId");//获得流程实例id
        String comment = request.getParameter("comment");//获得审批意见
        String resultStr = request.getParameter("approveResult");//获得审批结果
        /*审批结果转为整型数字*/
        int approveResult = 0;
        if(resultStr!=null){
            approveResult=Integer.parseInt(resultStr);
        }

        /*通过流程实例id查询审批记录单*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("processInstanceId",processInstanceId);
        Approve approve = approveMapper.findByMap(conditionMap);

        /*获取参与审批的用户信息,并将用户信息添加到审批单中*/
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        String applyUsers = approve.getApvusers();
        if(applyUsers==null || "".equals(applyUsers)){
            approve.setApvusers(userInfo.getUsercode());
        }else{
            approve.setApvusers(approve.getApvusers()+","+userInfo.getUsercode());
        }

        /*通过流程实例查到正在审批的节点task*/
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).
                singleResult();
        /*通过task获取节点对应的角色名*/
        String roleid = taskService.getIdentityLinksForTask(task.getId()).get(0).getGroupId();
        String rolename = this.roleInfoMapper.selectById(roleid).getDutyname();

        /*获取申请用户信息*/
        ApplyUserinfo applyUserinfo = applyUserinfoMapper.selectById(approve.getApluserid());

        /*变量map，即审核的map*/
        Map<String,Object> variables = Maps.newHashMap();
        /*存储短信参数的map*/
        LinkedMap<String,String> lmap = new LinkedMap<String, String>();

        /*根据审批类型，进行分别处理；1、同意，2、打回修改 3、驳回 4、加入失信*/
        switch (approveResult){
            case 1:
                /*审核通过*/
                /*查找下一个任务节点，如果返回null，则该任务节点为最后一个节点*/
                List<TaskDefinition> taskDefinitionList = flowService.getTaskDefinitionList(processInstanceId);
                /*下一个任务节点为空时，则该节点为最后一个节点；否则为中间节点；根据情况设置审批单状态*/
                if(taskDefinitionList==null || taskDefinitionList.size()==0){
                    approve.setPassdate(new Date());//设置审批通过时间
                    approve.setState("审批全部通过");//设置审批单状态
                    variables.put("shzt","通过");
                    /*终审通过记录*/
                    ApplyChange applyChange = new ApplyChange();//申请变更
                    applyChange.setAcId(CommonUtils.getUUIDWith_());//申请变更表id
                    applyChange.setAcType("1");//申请变更类型
                    applyChange.setAcApplyType(approve.getAptype());//申请业务类型
                    applyChange.setAcUserid(userInfo.getUserid());//操作用户id
                    applyChange.setAcSqbh(approve.getAplbh());//申请编号
                    applyChange.setAcTime(new Date());//操作日期
                    applyChange.setAcSqid(approve.getAplid());//申请单id
                   // applyChange.setAcSqid(approve.getAplid());
                    /*终审通过短信实体的添加*/
                    Message message = new Message();
                    message.setMsid(CommonUtils.getUUIDWith_());//设置主键id
                    message.setMstime(new Date());//设置操作时间
                    message.setToUsername(applyUserinfo.getUsername());//发送用户姓名
                    message.setToUserSfzh(applyUserinfo.getSfzh());//身份证号
                    message.setMsuserid(userInfo.getUserid());//设置操作用户id
                    message.setSqbh(approve.getAplbh());//设置申请编号
                    message.setAppType(approve.getAptype());//设置业务类别
                    message.setLinktel(applyUserinfo.getLinktel());//设置联系电话


                    /*如果是补贴，则更新申请单的状态为4，即分配房子状态；便于合同部分业务查询（补贴不用分房）*/
                    if(approve.getAptype().equals(PropertiesUtil.getApplyTypeProperties("butie"))){
                        Map<String,Object> map = Maps.newHashMap();
                        map.put("abId",approve.getAplid());
                        ApplyButie applyButie = this.applyButieService.selectByMap(map);
                        applyButie.setAbLc((short)4);
                        applyButieService.update(applyButie);
                    }else if(approve.getAptype().equals(PropertiesUtil.getApplyTypeProperties("xinjy"))
                            || approve.getAptype().equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))){
                        /*新就业和外来务工终审通过通知*/
                        lmap.put("@1@",applyUserinfo.getUsername());
                        lmap.put("@2@",PropertiesUtil.getApplyTypeProperties(approve.getAptype()));
                        lmap.put("@3@",approve.getAplbh());
                        lmap.put("@4@",userInfo.getUsername());
                        lmap.put("@5@",userInfo.getLinktel());
                        lmap.put("@6@", BaseInfoDataConvertor.convertUserUnit(userInfo));

                        /*设置消息实体*/
                        message.setMstem("JSM41823-0029");//设置模板
                        message.setMstype("6");//设置消息类型
                        message.setAppType(approve.getAptype());//设置业务类别

                        messageAndFormService.sendMessage(applyUserinfo.getLinktel(),"JSM41823-0029",lmap);
                    }else if(approve.getAptype().equals(PropertiesUtil.getApplyTypeProperties("gongzf"))){
                        /*低保特困公租房的申请，在申请之前判断是否有正在享受的补贴，如果存在，则将补贴状态更新为保障结束*/
                        Map<String,Object> map = Maps.newHashMap();
                        map.put("abzt",4);
                        map.put("sfzh",applyUserinfo.getSfzh());
                        ApplyButie applyButie = this.applyButieService.selectByMap(map);
                        if(applyButie!=null){
                            applyButie.setAbZt((short)5);//更新保障已结束
                            applyButieService.update(applyButie);//更新补贴申请单状态
                        }
                    }
                    /*当申请类型为非补贴的年审申请，即公租房的年审申请时,发送年审通过通知短信*/
                    if(approve.getAptype().indexOf("ns")!=-1){
                        String csApplyType = approve.getAptype().substring(approve.getAptype().indexOf("ns")+2);//初审申请类别
                        String ysapplyid = this.applyNsMapper.selectById(approve.getAplid()).getApSqid();//初审申请编号
                        String csSqbh = this.getSqbh(ysapplyid,csApplyType);//初审申请编号
                        applyChange.setAcType("10");//申请变更类型
                        applyChange.setAcApplyType(csApplyType);//设置申请类别

                        /*公租房年审通过的处理,插入待录入的合同数据*/
                        ContractDetail newContractDetail = new ContractDetail();
                        newContractDetail.setcId(CommonUtils.getUUIDWith_());

                        newContractDetail.setcSqid(ysapplyid);//设置申请单id
                        newContractDetail.setcLc((short)1);//设为待录入状态
                        newContractDetail.setcZfzt((short)0);//未过期状态
                        newContractDetail.setCreateDate(new Date());//创建时间
                        newContractDetail.setcHtlx(csApplyType);//设置申请类别
                        /*判断该条年审单对应的初审单是否是老数据*/
                        Map<String,Object> tempMap = Maps.newHashMap();
                        tempMap.put("applyid",ysapplyid);
                        int count = this.approveMapper.findCountByMap(tempMap);
                        if(count>0){
                            /*是新数据*/
                            newContractDetail.setcType("2");//类别设为2
                        }else{
                            newContractDetail.setcType("4");//类别设为4
                        }

                        /*如果是补贴，需要发送年补贴年审申请通过通知*/
                        if(!approve.getAptype().equals(PropertiesUtil.getApplyTypeProperties("nsbutie"))){
                            /*设置消息实体*/
                            message.setMstem("JSM41823-0027");//设置模板
                            message.setMstype("8");//设置消息类型

                            message.setAppType(csApplyType);//设置业务类别
                            /*公租房审核通过通知*/
                            this.sendGzfNsMessage(applyUserinfo,userInfo,approve,"JSM41823-0027");


                            /*如果老数据，新合同记录的状态直接设为已录入状态*/
                            if(count<=0){
                                newContractDetail.setcLc((short)2);//设为已录入状态
                            }
                            tempMap.put("zfzt",0);//有效合同查询
                            ContractDetail oldContractDetail1 = this.contractDetailMapper.selectContractDetailbyMap(tempMap);
                            oldContractDetail1.setcZfzt((short)1);//原来的合同设为过期状态
                            contractDetailMapper.update(oldContractDetail1);

                            newContractDetail.setcDataInput(oldContractDetail1.getcDataInput());//设置合同填充数据
                            /*新的合同日期设为老的合同日期加上一年*/
                            Date endTime = oldContractDetail1.getcEndTime();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(endTime);
                            calendar.add(Calendar.DATE,1);
                            newContractDetail.setcBeginTime(calendar.getTime());//设置开始时间
                            calendar.add(Calendar.YEAR,1);
                            calendar.add(Calendar.DATE,-1);
                            newContractDetail.setcEndTime(calendar.getTime());//设置结束时间


                            String job_name=approve.getAplid();
                            ScheduleJobDomain scheduleJob = new ScheduleJobDomain();
                            scheduleJob.setJobGroup("group1");
                            scheduleJob.setJobName(job_name);
                                /*移除原有的定时器*/
                                scheduleJobService.deleteJob(scheduleJob);

                                calendar.add(Calendar.DATE,-30);//提前30天通知

                                /*时间测试*/
                                /*Calendar calendar2 = Calendar.getInstance();
                                calendar2.setTime(new Date());
                                calendar2.add(Calendar.MINUTE,3);*/
                                String time = SendPhoneCodeUtil.getCron(calendar.getTime());//设置通知的时间
                                //String time = SendPhoneCodeUtil.getCron(calendar2.getTime());//设置通知的时间
                                scheduleJob.setQuartz(time);
                                /*设置新的定时器，配置参数*/
                                ContractTemplateFillingDataPojo contractTemplateFillingDataPojo = new ContractTemplateFillingDataPojo();
                                contractTemplateFillingDataPojo.setUserName(applyUserinfo.getUsername());//设置发送用户姓名
                                contractTemplateFillingDataPojo.setApSqlb(csApplyType);//设置申请类别
                                contractTemplateFillingDataPojo.setMobilePhone(applyUserinfo.getLinktel());//联系方式
                                contractTemplateFillingDataPojo.setObjSqbh(csSqbh);//设置申请编号
                                tempMap.put("pageLaseIndex",5);
                                tempMap.put("pageIndex",0);
                                List<Map> approveList = this.approveMapper.findAllApply(tempMap);

                                /*设置房源信息*/
                                contractTemplateFillingDataPojo.setCommunity(((Approve)approveList.get(0)).getF_community());//小区
                                contractTemplateFillingDataPojo.setBuilding(((Approve)approveList.get(0)).getF_buname());//栋
                                contractTemplateFillingDataPojo.setUnit(((Approve)approveList.get(0)).getF_cecode());//单元
                                contractTemplateFillingDataPojo.setRoom(((Approve)approveList.get(0)).getF_ronum());//房号

                                /*设置合同到期年月日*/
                                Calendar newCalendar = Calendar.getInstance();
                                newCalendar.setTime(endTime);
                                contractTemplateFillingDataPojo.setYearEnd(""+newCalendar.get(Calendar.YEAR));//年
                                contractTemplateFillingDataPojo.setMonthEnd(""+(newCalendar.get(Calendar.MONTH)+1));//月
                                contractTemplateFillingDataPojo.setDayEnd(""+newCalendar.get(Calendar.DATE));//日

                                Map<String, Object> scheduleMap = new HashMap<String, Object>();
                                scheduleMap.put("contractTemplateFillingDataPojo", contractTemplateFillingDataPojo);
                                scheduleMap.put("userinfo", userInfo);
                                /*添加新的定时器*/
                                scheduleJobService.addJob(scheduleJob, scheduleMap);


                        }

                       this.contractDetailMapper.insert(newContractDetail);

                    }

                    taskService.setVariableLocal(task.getId(),"shzt","通过");
                    applyChangeService.insert(applyChange);//插入终审通过数据
                    /*存在消息实体*/
                    if(message.getMstype()!=null){
                        this.messageService.insert(message);
                    }
                }else{
                    approve.setState(userInfo.getUsercode()+"-审批通过");
                }
                /*原件审核通过通知，仅通知初审；年审不发原件审核通过通知*/
                if(task.getName().indexOf(PropertiesUtil.getFlowProperties("reauditname"))!=-1
                        && approve.getAptype().indexOf("ns")==-1){
                    /*原件复审通过的短信实体*/
                    Message message = new Message();
                    message.setMsid(CommonUtils.getUUIDWith_());//设置主键id
                    message.setMstime(new Date());//设置操作时间
                    message.setToUsername(applyUserinfo.getUsername());//发送用户姓名
                    message.setToUserSfzh(applyUserinfo.getSfzh());//身份证号
                    message.setMsuserid(userInfo.getUserid());//设置操作用户id
                    message.setSqbh(approve.getAplbh());//设置申请编号
                    message.setAppType(approve.getAptype());//设置业务类别
                    message.setLinktel(applyUserinfo.getLinktel());//设置联系电话
                    message.setMstem("JSM41823-0025");//设置模板
                    message.setMstype("3");//设置消息类型
                    message.setAppType(approve.getAptype());//设置业务类别
                    if(approve.getAptype().indexOf("ns")!=-1){
                        message.setAppType(approve.getAptype().substring(approve.getAptype().indexOf("ns")+2));//设置业务类别
                    }
                    /*发送短信参数设置*/
                    lmap.put("@1@",applyUserinfo.getUsername());
                    lmap.put("@2@",PropertiesUtil.getApplyTypeProperties(approve.getAptype()));
                    lmap.put("@3@",approve.getAplbh());
                    lmap.put("@4@",userInfo.getUsername());
                    lmap.put("@5@",userInfo.getLinktel());
                    lmap.put("@6@", BaseInfoDataConvertor.convertUserUnit(userInfo));
                    messageAndFormService.sendMessage(applyUserinfo.getLinktel(),"JSM41823-0025",lmap);
                    this.messageService.insert(message);
                }
                /*审批通过*/
                variables.put("msg", "通过");
                /*添加审批意见*/
                taskService.addComment(task.getId(), processInstanceId, comment);
                /*审批提交*/
                taskService.complete(task.getId(),variables);
                /*如果是初审批准通过，需要添加签名记录数据,并且不是原件复审节点*/
                if(approve.getAptype().indexOf("ns")==-1
                        && task.getName().indexOf(PropertiesUtil.getFlowProperties("reauditname"))==-1){
                    addSignData(approve,request);
                }
                break;
            case 2:
                /*设置审批状态*/
                approve.setState(userInfo.getUsercode()+"-打回修改");
                /*添加审批意见*/
                taskService.setVariableLocal(task.getId(),"shzt","打回修改");
                taskService.addComment(task.getId(), processInstanceId, comment);
                /*流程挂起*/
                runtimeService.suspendProcessInstanceById(processInstanceId);
                /*打回修改*/
                break;
            case 3:
                /*设置审批状态*/
                approve.setState(userInfo.getUsercode()+"-驳回");
                /*审批不通过*/
                variables.put("msg", "不通过");
                /*添加审批意见*/
                taskService.addComment(task.getId(), processInstanceId, comment);
                /*设置task局部变量*/
                taskService.setVariableLocal(task.getId(),"shzt","驳回");
                /*审批提交*/
                taskService.complete(task.getId(),variables);
                /*驳回记录*/
                ApplyChange applyChange = new ApplyChange();//申请变更
                applyChange.setAcId(CommonUtils.getUUIDWith_());//申请变更表id
                applyChange.setAcType("9");//申请变更类型
                applyChange.setAcApplyType(approve.getAptype());//申请业务类型
                applyChange.setAcUserid(userInfo.getUserid());//操作用户id
                applyChange.setAcSqbh(approve.getAplbh());//申请编号
                applyChange.setAcTime(new Date());//操作日期
                applyChange.setAcSqid(approve.getAplid());//申请单id
                /*消息实体*/
                Message messagePojo = new Message();
                messagePojo.setMsid(CommonUtils.getUUIDWith_());//设置主键id
                messagePojo.setMstime(new Date());//设置操作时间
                messagePojo.setToUsername(applyUserinfo.getUsername());//发送用户姓名
                messagePojo.setToUserSfzh(applyUserinfo.getSfzh());//身份证号
                messagePojo.setMsuserid(userInfo.getUserid());//设置操作用户id
                messagePojo.setSqbh(approve.getAplbh());//设置申请编号
                messagePojo.setLinktel(applyUserinfo.getLinktel());//设置联系电话
                /*驳回之后发送短信提醒,短信用驳回模板*/
                if(approve.getAptype().indexOf("ns")!=-1){
                    /*公租房年审的驳回通知*/
                    if(!approve.getAptype().equals(PropertiesUtil.getApplyTypeProperties("nsbutie"))){
                        /*消息实体的插入*/
                        messagePojo.setAppType(approve.getAptype());//设置业务类别
                        messagePojo.setMstem("JSM41823-0030");//设置模板
                        messagePojo.setMstype("9");//设置消息类型
                        this.messageService.insert(messagePojo);
                        this.sendGzfNsMessage(applyUserinfo,userInfo,approve,"JSM41823-0030");
                    }else{
                        /*补贴年审的驳回通知*/
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(approve.getApldate());
                        lmap.put("@1@",applyUserinfo.getUsername());
                        lmap.put("@2@",""+calendar.get(Calendar.YEAR));
                        lmap.put("@3@",userInfo.getUsername());
                        lmap.put("@4@",userInfo.getLinktel());
                        messageAndFormService.sendMessage(applyUserinfo.getLinktel(),"JSM41823-0034",lmap);
                        /*消息实体的插入*/
                        messagePojo.setAppType("2");//设置业务类别
                        messagePojo.setMstem("JSM41823-0034");//设置模板
                        messagePojo.setMstype("12");//设置消息类型
                        this.messageService.insert(messagePojo);
                    }
                    /*申请变更跟踪的录入*/
                    String csApplyType = approve.getAptype().substring(approve.getAptype().indexOf("ns")+2);//初审申请类别
                    String ysapplyid = this.applyNsMapper.selectById(approve.getAplid()).getApSqid();//初审申请编号
                    String csSqbh = this.getSqbh(ysapplyid,csApplyType);//初审申请编号
                    applyChange.setAcType("11");//申请变更类型
                    applyChange.setAcApplyType(csApplyType);
                    applyChange.setAcSqbh(csSqbh);
                    /*发送驳回通知*/
                    String message="年审提醒：您的申请编号为"+approve.getAplbh()+"的年审申请被驳回，请悉知";
                    messageAndFormService.addMessageAndFormService(message,approve.getApluserid(),applyUserinfo.getLinktel());
                }else{
                    /*非年审的驳回通知*/
                    lmap.put("@1@",applyUserinfo.getUsername());
                    lmap.put("@2@",PropertiesUtil.getApplyTypeProperties(approve.getAptype()));
                    lmap.put("@3@",approve.getAplbh());
                    lmap.put("@4@",userInfo.getUsername());
                    lmap.put("@5@",userInfo.getLinktel());
                    lmap.put("@6@", BaseInfoDataConvertor.convertUserUnit(userInfo));
                    /*消息实体的插入*/
                    messagePojo.setAppType(approve.getAptype());//设置业务类别
                    messagePojo.setMstem("JSM41823-0023");//设置模板
                    messagePojo.setMstype("1");//设置消息类型
                    this.messageService.insert(messagePojo);
                    /*短信发送*/
                    messageAndFormService.sendMessage(applyUserinfo.getLinktel(),"JSM41823-0023",lmap);
                    /*发送驳回通知*/
                    String message="申请提醒：您的申请编号为"+approve.getAplbh()+"的申请被驳回，请悉知";
                    messageAndFormService.addMessageAndFormService(message,approve.getApluserid(),applyUserinfo.getLinktel());
                }
                applyChangeService.insert(applyChange);//插入驳回变更数据
                break;
            case 4:
                /*加入失信,插入数据到失信条目对象中*/
                BliveGongS bliveGongS = new BliveGongS();
                bliveGongS.setBlgId(CommonUtils.getUUIDWith_());//生成主键uuid
                bliveGongS.setBlgApid(approve.getAplid());//设置申请单id
                bliveGongS.setBlgIsgs("0");//设置是否显示 0 否，1 是
                bliveGongS.setBlgUserid(approve.getApluserid());//设置申请用户id
                bliveGongS.setBlgShProcessid(processInstanceId);//设置申请审核时的流程实例id
                bliveGongS.setBlgType(approve.getAptype());//设置申请类别
                bliveGongS.setBlgSqrq(new Date());//设置添加日期为当前时间
                bliveGongS.setBlgSqbh(approve.getAplbh());//设置添加日期为当前时间
                bliveGongS.setBlgRpuserid(userInfo.getUserid());//设置上报用户id
                bliveGongSMapper.insert(bliveGongS);

                /*设置审批状态*/
                approve.setState(userInfo.getUsercode()+"-提交失信");//判别为失信
                /*添加审批意见*/
                taskService.setVariableLocal(task.getId(),"shzt","认定失信");
                taskService.addComment(task.getId(), processInstanceId, comment);
                /*流程挂起*/
                runtimeService.suspendProcessInstanceById(processInstanceId);
                break;
        }

        /*插入审批单*/
        int updateCount = approveMapper.update(approve);
        return updateCount;
    }

    /**
     * 短信发送（公租房的年审，包括通过和驳回）
     * @param applyUserinfo
     * @param userInfo
     * @param approve
     * @param tplid
     */
    private void sendGzfNsMessage(ApplyUserinfo applyUserinfo,UserInfo userInfo,Approve approve,String tplid){
        Map<String,Object> conditionMap = Maps.newHashMap();
        /*公租房审核驳回通知*/
        LinkedMap<String,String> lmap = new LinkedMap<String, String>();
        lmap.put("@1@",applyUserinfo.getUsername());//申请用户姓名
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(approve.getApldate());
        //conditionMap.put("shApplyid",this.applyNsMapper.selectById(approve.getAplid()).getApSqid());//申请日期
        conditionMap.put("applyid",this.applyNsMapper.selectById(approve.getAplid()).getApSqid());
        conditionMap.put("pageLaseIndex",5);
        conditionMap.put("pageIndex",0);
        List<Map> approveList = this.approveMapper.findAllApply(conditionMap);
        Approve tempApprove = (Approve)approveList.get(0);
        /*获取房屋小区等信息*/
        String srouceinfo = new StringBuffer().append(tempApprove.getF_community())
                .append("小区").append(tempApprove.getF_buname())
                .append("楼").append(tempApprove.getF_cecode())
                .append("单元").append(tempApprove.getF_ronum())
                .append("室").toString();
        lmap.put("@2@",srouceinfo);
        lmap.put("@3@",""+calendar.get(Calendar.YEAR));
        lmap.put("@4@",userInfo.getUsername());
        lmap.put("@5@",userInfo.getLinktel());
        messageAndFormService.sendMessage(applyUserinfo.getLinktel(),tplid,lmap);
    }

    /**
     * 审批通过时添加签名图片记录数据
     * @param approve
     * @param request
     */
    private void  addSignData(Approve approve,HttpServletRequest request){
        String comment = request.getParameter("comment");//获得审批意见
        String imgBase64Str = request.getParameter("imgBase64Str");
        System.out.println("imgBase64Str:"+imgBase64Str);
        /*签名文件存储的目录*/
        String  filePath=request.getSession().getServletContext().getRealPath("/") + "ArchivePic/";
        filePath=filePath+approve.getAplbh().substring(0,8)+"/"+approve.getAplbh()+"/sign/";
        /*uuid命名签名图片名*/
        String fileName = CommonUtils.getUUIDWith_()+".png";
        String allPath= filePath+fileName;//文件的绝对路径
        /*获取参与审批的用户信息,并将用户信息添加到审批单中*/
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");

        ApproveRecord approveRecord = new ApproveRecord();
        approveRecord.setApcId(CommonUtils.getUUIDWith_());//设置主键id
        approveRecord.setApplyId(approve.getAplid());//申请单id
        approveRecord.setApproveId(approve.getApvid());//设置审批单id
        approveRecord.setApcComment(comment);//设置审核意见
        approveRecord.setApplyType(approve.getAptype());//设置申请类别
        approveRecord.setApproveNode("");//设置审批节点(暂时不用)
        approveRecord.setApprovetime(new Date());//设置审核时间
        approveRecord.setApproveUserid(userInfo.getUserid());//设置申请用户id
        approveRecord.setNameUrl(allPath.substring(filePath.indexOf("ArchivePic")));//存储图片的相对路径
        approveRecord.setBaseImg(imgBase64Str);//存储图片的相对路径
        approveRecordMapper.insert(approveRecord);
        Base64Util.base64SvgXmlToImage(imgBase64Str,filePath,fileName); //生成签名图片

    }

    /**
     * 通过申请单id和申请类别获取申请编号
     * @param applyid
     * @param applyType
     * @return
     */
    private String getSqbh(String applyid,String applyType){
        if(PropertiesUtil.getApplyTypeProperties("gongzf").equals(applyType)){
            return this.applyMapper.selectById(applyid).getApSqbh();
        }else if(PropertiesUtil.getApplyTypeProperties("xinjy").equals(applyType)){
            return this.applyForgraDuateMapper.selectById(applyid).getAfgSqbh();
        }else if(PropertiesUtil.getApplyTypeProperties("wailaiwg").equals(applyType)){
            return this.applyForForeignMapper.selectById(applyid).getAffSqbh();
        }
        return null;
    }

    public static void main(String[] args){
        LinkedMap<String,String> map = new LinkedMap<String, String>();
        map.put("@1@","111");
        map.put("@2@","22");
        map.put("@3@","2323");
        map.put("@4@","ww");
        map.put("@5@","13835654432");
        map.put("@6@", "asdf");

        MessageAndFormService messageAndFormService = new MessageAndFormService();
        messageAndFormService.sendMessage("18652955650","JSM41823-0029",map);
    }
}