package com.sys.controller.task;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.common.PropertiesUtil;
import com.sys.common.StringUtils;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.RoleInfo;
import com.sys.pojo.UserInfo;
import com.sys.pojo.apply.ApplyNs;
import com.sys.pojo.apply.Approve;
import com.sys.service.ApplyUserinfoService;
import com.sys.service.MyTaskService;
import com.sys.service.ParmItemService;
import com.sys.service.RoleInfoService;
import com.sys.service.apply.*;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Desc:desc
 * @Author:wangli
 * @CreateTime:17:53 2018/10/25
 */
@Controller
@RequestMapping("nstask")
public class TaskNsController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ApproveService approveService;

    @Autowired
    private ApplyUserinfoService applyUserinfoService;

    @Autowired
    private ApplyForForeignService applyForForeignService;

    @Autowired
    private ApplyService applyService;

    @Autowired
    private ApplyForgraDuateService applyForgraDuateService;

    @Autowired
    private ApplyButieService applyButieService;

    @Autowired
    private RoleInfoService roleInfoService;

    @Autowired
    private ParmItemService parmItemService;

    @Autowired
    private MyTaskService myTaskService;

    @Autowired
    private ApplyNsService applyNsService;

    @Autowired
    private HistoryService historyService;


    /**
     * 查询审核中申请单（包含属于个人的申请单和审批流程中其他角色的申请单）
     * @param request
     * @return
     */
    @RequestMapping("listPerToHandleNsTask")
    @ResponseBody
    public Object listPerToHandleTask(HttpServletRequest request){

        String applyType = request.getParameter("applyType");//申请类别
        String notApplyType = "1000";
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");//userInfoService.getUserByMap(conditionMap);
        /*依据当前用户登录的角色信息，判断其所属类别*/
        Map<String, Object> newHashMap = Maps.newHashMap();
        newHashMap.put("userid", userInfo.getUserid());
        if (userInfo.getSsq().equals("0")) {
            /*需要判断用户是公租房管理公司还是市住房保障中心，通过角色名判断*/
            if(StringUtils.isEmpty(applyType)){
                boolean flag = false;
                List<RoleInfo> roleInfoList = roleInfoService.getRoleListByMap(newHashMap);
                for (RoleInfo roleInfo : roleInfoList) {
                    if (roleInfo.getDutyname().indexOf("公租房") != -1) {
                        flag = true;
                        break;
                    }
                }

                /*如果是公租房管理公司的，则查询非，否则设为0*/
                if (flag) {
                    notApplyType = PropertiesUtil.getApplyTypeProperties("butie");
                } else {
                    applyType=PropertiesUtil.getApplyTypeProperties("butie");
                }
            }
        }

        /*读取条件查询的参数*/
        String applyUsername=request.getParameter("applyUsername");//申请用户姓名
        String applyUsercard=request.getParameter("applyUsercard");//申请用户身份证
        String applyUserSsq=request.getParameter("applyUserSsq");//申请用户所属区
        String applyUserJd = request.getParameter("applyUserJd");//申请用户所属街道

        String all = request.getParameter("all");//全部审核还是个人审核
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        int pageIndex =1;
        int pageSize = 20;
        if(rows != null &&!"".equals(rows)){
            pageSize=Integer.parseInt(rows);
        }
        if(page != null &&!"".equals(page)){
            pageIndex=(Integer.parseInt(page) - 1) * pageSize;
        }
        if(applyUsername==null){
            applyUsername="";
        }
        if(applyUsercard==null){
            applyUsercard="";
        }

        if(applyUserJd==null){
            applyUserJd="";
        }
        if(applyType==null){
            applyType="";
        }
        if(all==null){
            all="all";
        }
        if(applyUserSsq==null){
            applyUserSsq="";
        }



        //查询登录的审核用户
        // String usercode = SecurityUtils.getSubject().getPrincipal().toString();
        /*根据用户登录名查询用户实体*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        // conditionMap.put("usercode",usercode);

        System.out.println("--userid:"+userInfo.getUserid());
        String perUserSsq=userInfo.getSsq();//所属区
        if(perUserSsq.indexOf("0")==-1){//如果当前用户不为市区用户，则为当前登录用户的分区
            applyUserSsq=perUserSsq;
        }
        /*如果所属区为市区，则市区的条件放空*/
        if("0".equals(applyUserSsq)){
            applyUserSsq="";
        }
        /*如果街道为空，且当前用户时街道用户，则街道为当前审核用户的街道*/
        if(userInfo.getSsj()!=null && StringUtils.isEmpty(applyUserJd)){
            applyUserJd=userInfo.getSsj();
        }
        /*根据用户id查询其对应的角色列表*/
        conditionMap.put("userid",userInfo.getUserid());
        List<RoleInfo> roleInfoList = roleInfoService.getRoleListByMap(conditionMap);
        /*将角色列表的id合并成一个字符串*/
        String groups="";
        for (RoleInfo roleInfo:roleInfoList){
            groups=groups+roleInfo.getDutyid()+",";
        }

        Long count;//条件查询条目总数
        List<Task> allTaskList;//条件查询分页
        /*如果是全部审核查询*/
        if(all.equals("all")){
            /*查询条件下所有的任务列表数目*/
            count = taskService.createTaskQuery()
                    .processVariableValueLike("applyUsername","%"+applyUsername+"%")
                    .processVariableValueLike("applyUsercard","%"+applyUsercard+"%")
                    .processVariableValueLike("applyUserSsq","%"+applyUserSsq+"%")
                    .processVariableValueLike("applyUserJd","%"+applyUserJd+"%")
                    .processVariableValueLike("applyType","%"+applyType+"%")
                    .processVariableValueLike("aplb","ns")
                    .processVariableValueNotEqualsIgnoreCase("applyType",notApplyType)
                    .active().count();

            /*查询条件下所有的任务列表，分页查询*/
            allTaskList = taskService.createTaskQuery()
                    .processVariableValueLike("applyUsername","%"+applyUsername+"%")
                    .processVariableValueLike("applyUsercard","%"+applyUsercard+"%")
                    .processVariableValueLike("applyUserSsq","%"+applyUserSsq+"%")
                    .processVariableValueLike("applyUserJd","%"+applyUserJd+"%")
                    .processVariableValueLike("applyType","%"+applyType+"%")
                    .processVariableValueNotEqualsIgnoreCase("applyType",notApplyType)
                    .processVariableValueLike("aplb","ns")
                    .active().orderByTaskCreateTime()
                    .desc().listPage(pageIndex,pageSize);
        }else{
            /*查询条件下所有的个人任务列表数目*/
            count = taskService.createTaskQuery()
                    .taskCandidateUser(userInfo.getUserid())
                    .processVariableValueLike("applyUsername","%"+applyUsername+"%")
                    .processVariableValueLike("applyUsercard","%"+applyUsercard+"%")
                    .processVariableValueLike("applyUserSsq","%"+applyUserSsq+"%")
                    .processVariableValueLike("applyUserJd","%"+applyUserJd+"%")
                    .processVariableValueLike("applyType","%"+applyType+"%")
                    .processVariableValueNotEqualsIgnoreCase("applyType",notApplyType)
                    .processVariableValueLike("aplb","ns")
                    .active().count();

            /*查询条件下所有的个人任务列表，分页查询*/
            allTaskList = taskService.createTaskQuery()
                    .taskCandidateUser(userInfo.getUserid())
                    .processVariableValueLike("applyUsername","%"+applyUsername+"%")
                    .processVariableValueLike("applyUsercard","%"+applyUsercard+"%")
                    .processVariableValueLike("applyUserSsq","%"+applyUserSsq+"%")
                    .processVariableValueLike("applyUserJd","%"+applyUserJd+"%")
                    .processVariableValueLike("applyType","%"+applyType+"%")
                    .processVariableValueNotEqualsIgnoreCase("applyType",notApplyType)
                    .processVariableValueLike("aplb","ns")
                    .active().
                    orderByTaskCreateTime()
                    .desc().listPage(pageIndex,pageSize);
        }



        List<Map<String,Object>> resultList = Lists.newArrayList();//返回的结果集

        boolean flag =false;//判断该当前任务的审批节点对应的审批角色是否就是该用户对应的角色
        /*遍历所有task，判断当前task对应组用户是否包含当前登录用户，如果存在，则表明
         * 该用户为当前task节点的审批节点*/
        for(Task task:allTaskList){
            /*查询用户身份列表*/
            List<IdentityLink> idenList = taskService.getIdentityLinksForTask(task.getId());
            flag=false;
            String currTaskGroupId ="";
            for(IdentityLink identityLink:idenList){
                /*当前用户对应的角色包含该task对应的角色id时，flag设为true*/
                currTaskGroupId=identityLink.getGroupId();
                if(groups.indexOf(identityLink.getGroupId())!=-1){
                    flag=true;
                    break;
                }
            }
            Map<String,Object> dataMap = Maps.newHashMap();
            dataMap.put("processInstanceId",task.getProcessInstanceId());
            findMapResult(dataMap);//根据task信息返回map
            dataMap.put("flag",flag);//flag也存于map中
            dataMap.put("taskId",task.getId());
            /*如果是当前用户审批节点，同时该节点的名称又为原件审批，则设置关键字返回给前端*/
            if(flag){
                if(task.getName().indexOf(PropertiesUtil.getFlowProperties("reauditname"))!=-1){
                    dataMap.put("yuanjian",1);
                }
            }
            dataMap.put("nodename",this.roleInfoService.selectById(currTaskGroupId).getDutyname());
            resultList.add(dataMap);
        }

        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("rows",resultList);
        resultMap.put("total",count);
        return resultMap;
    }


    /**
     * 审核通过的年审历史记录查询
     * @param request
     * @return
     */
    @RequestMapping("listPerNsHisTask")
    @ResponseBody
    public Object listPerHisTask(HttpServletRequest request){
        /*读取条件查询的参数*/
        String applyUsername=request.getParameter("applyUsername");//申请用户姓名
        String applyUsercard=request.getParameter("applyUsercard");//申请用户身份证
        String applyUserSsq=request.getParameter("applyUserSsq");//申请用户所属区
        String applyUserJd = request.getParameter("applyUserJd");//申请用户所属街道
        String applyType = request.getParameter("applyType");//申请类别
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String state = request.getParameter("state");//申请单状态 驳回或者通过

        int pageIndex =1;
        int pageSize = 20;
        if(rows != null &&!"".equals(rows)){
            pageSize=Integer.parseInt(rows);
        }
        if(page != null &&!"".equals(page)){
            pageIndex=(Integer.parseInt(page) - 1) * pageSize;
        }
        if(applyUsername==null){
            applyUsername="";
        }
        if(applyUsercard==null){
            applyUsercard="";
        }
        if(applyUserSsq==null){
            applyUserSsq="";
        }
        if(applyUserJd==null){
            applyUserJd="";
        }
        if(applyType==null){
            applyType="";
        }
        if(StringUtils.isEmpty(state)){
            state="";
        }else{
            if("pass".equals(state)){
                state="通过";
            }else if("reject".equals(state)){
                state="驳回";
            }
        }

        String notApplyType = "1000";
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");//userInfoService.getUserByMap(conditionMap);
        /*依据当前用户登录的角色信息，判断其所属类别*/
        Map<String, Object> newHashMap = Maps.newHashMap();
        newHashMap.put("userid", userInfo.getUserid());
        if (userInfo.getSsq().equals("0")) {
            /*需要判断用户是公租房管理公司还是市住房保障中心，通过角色名判断*/
            if(StringUtils.isEmpty(applyType)){
                boolean flag = false;
                List<RoleInfo> roleInfoList = roleInfoService.getRoleListByMap(newHashMap);
                for (RoleInfo roleInfo : roleInfoList) {
                    if (roleInfo.getDutyname().indexOf("公租房") != -1) {
                        flag = true;
                        break;
                    }
                }

                /*如果是公租房管理公司的，则查询非，否则设为0*/
                if (flag) {
                    notApplyType = PropertiesUtil.getApplyTypeProperties("butie");
                } else {
                    applyType=PropertiesUtil.getApplyTypeProperties("butie");
                }
            }
        }
        //查询登录的审核用户
//        String usercode = SecurityUtils.getSubject().getPrincipal().toString();
        /*根据用户登录名查询用户实体*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        //UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");//userInfoService.getUserByMap(conditionMap);
        //String applyUserSsq=userInfo.getSsq();//所属区
        String perUserSsq=userInfo.getSsq();//所属区
        if(perUserSsq.indexOf("0")==-1){//如果当前用户不为市区用户，则为当前登录用户的分区
            applyUserSsq=perUserSsq;
        }

        /*如果所属区为市区，则市区的条件放空*/
        if("0".equals(applyUserSsq)){
            applyUserSsq="";
        }
        /*如果街道为空，且当前用户时街道用户，则街道为当前审核用户的街道*/
        if(userInfo.getSsj()!=null && StringUtils.isEmpty(applyUserJd)){
            applyUserJd=userInfo.getSsj();
        }
        /*根据用户id查询其对应的角色列表*/
        conditionMap.put("userid",userInfo.getUserid());
        List<RoleInfo> roleInfoList = roleInfoService.getRoleListByMap(conditionMap);
        /*将角色列表的id合并成一个字符串*/
        String groups="";
        for (RoleInfo roleInfo:roleInfoList){
            groups=groups+roleInfo.getDutyid()+",";
        }
        /*查询条件下所有的任务列表数目*/
        Long count = historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUsername","%"+applyUsername+"%")
                .processVariableValueLike("applyUsercard","%"+applyUsercard+"%")
                .processVariableValueLike("applyUserSsq","%"+applyUserSsq+"%")
                .processVariableValueLike("applyUserJd","%"+applyUserJd+"%")
                .processVariableValueLike("applyType","%"+applyType+"%")
                .processVariableValueNotEqualsIgnoreCase("applyType",notApplyType)
                .processVariableValueLike("aplb","ns")
                .taskVariableValueLike("shzt","%"+state+"%")
                .count();
        /* 查询用户历史任务，分页查询 */
        List<HistoricTaskInstance> histList = historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUsername","%"+applyUsername+"%")
                .processVariableValueLike("applyUsercard","%"+applyUsercard+"%")
                .processVariableValueLike("applyUserSsq","%"+applyUserSsq+"%")
                .processVariableValueLike("applyUserJd","%"+applyUserJd+"%")
                .processVariableValueLike("applyType","%"+applyType+"%")
                .processVariableValueNotEqualsIgnoreCase("applyType",notApplyType)
                .processVariableValueLike("aplb","ns")
                .taskVariableValueLike("shzt","%"+state+"%")
                .orderByTaskCreateTime()
                .desc()
                .listPage(pageIndex,pageSize);

        List<Map<String,Object>> resultList = Lists.newArrayList();//返回的结果集

        boolean flag =false;//判断该当前任务的审批节点对应的审批角色是否就是该用户对应的角色
        /*遍历所有task，判断当前task对应组用户是否包含当前登录用户，如果存在，则表明
         * 该用户为当前task节点的审批节点*/
        for(HistoricTaskInstance task:histList) {

            /*查询用户身份列表*/
            List<HistoricIdentityLink> idenList = historyService.getHistoricIdentityLinksForTask(task.getId());
           /*获得用户组，即角色id*/
            String currTaskGroupId = idenList.get(0).getGroupId();
            Map<String,Object> dataMap = Maps.newHashMap();
            dataMap.put("processInstanceId",task.getProcessInstanceId());
            findMapResult(dataMap);//根据task信息返回map
            dataMap.put("flag",flag);//flag也存于map中
            dataMap.put("taskId",task.getId());
            dataMap.put("nodename",this.roleInfoService.selectById(currTaskGroupId).getDutyname());
            resultList.add(dataMap);
        }
        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("rows",resultList);
        resultMap.put("total",count);
        return resultMap;
    }

    /**
     * 根据task查询审核单显示所需要的一些重要信息
     * @param resultMap
     * @return
     */
    private void findMapResult(Map resultMap){
        /*流程实例id保存于map中，并根据map查询审批单实体*/

        Approve approve = approveService.findNSByProcessInstanceId(resultMap);
        resultMap.put("applydate",approve.getApldate());//保存申请日期

        /*读取和设置申请用户姓名和身份证号*/
//        ApplyUserinfo applyUserInfo = applyUserinfoService.selectById(approve.getApluserid());
        resultMap.put("username",approve.getUsername());
        resultMap.put("sfzh",approve.getSfzh());

        String sqbh="";//申请编号
        String ssq="";//所属区
        String ssjd="";//所属街道

//        String appid = approve.getAplid();//获得申请单id
//        String aptype = approve.getAptype();//获得申请类别
//        if(aptype.length()>1){//过滤掉申请类别的ns字段
//            aptype=aptype.substring(2);
//        }
//        String apptypename="";
//        ApplyNs applyNs = this.applyNsService.selectById(appid);//获得年审实体
//        sqbh=applyNs.getApSqbh();//读取申请编号
//        ssq = applyNs.getApSsq();//读取所属区
//        ssjd = applyNs.getApJdbsc();//读取所属街道
//        /*根据申请类别和申请单id去查询对应的申请单实体，获取申请编号、所属区、所属街道*/
//        /*如果是外来务工的申请单*/
//        if(aptype.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))){
//           // ApplyForForeign applyForForeign =applyForForeignService.selectById(appid);
//            apptypename="外来户申请";
//        }else if(aptype.equals(PropertiesUtil.getApplyTypeProperties("jingsf")) ||
//                aptype.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))){
//            /*经适房和公租房的申请单*/
//            if(aptype.equals(PropertiesUtil.getApplyTypeProperties("jingsf")))
//                apptypename="经济适应房";
//            else
//                apptypename="公租房";
//        }else if(aptype.equals(PropertiesUtil.getApplyTypeProperties("butie"))){
//            /*补贴的申请单*/
//            apptypename="特困补贴";
//        }else if(aptype.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))){
//            /*新就业的申请单*/
//            apptypename="新就业公租房";
//        }
        resultMap.put("applyType",approve.getAtype());//设置申请类型编号
        resultMap.put("sqbh",approve.getAplbh());//设置申请编号
//        Map<String,Object> conditionMap = Maps.newHashMap();
//        conditionMap.put("piSetcode", PropertiesUtil.getAreaProperties("area"));//获取区的数据字典编码，即04
//        conditionMap.put("piItemcode",ssq);//所属区
        resultMap.put("ssq",approve.getSsq());//设置所属区

//        conditionMap.put("piSetcode", PropertiesUtil.getAreaProperties("jiedao"));//获取区的街道字典编码，即05
//        conditionMap.put("piItemcode",ssjd);//所属街道
//        System.out.println("ssjd--"+ssjd);
        resultMap.put("ssjd",approve.getSsj());//设置所属街道

        resultMap.put("apptypename",approve.getAptype());//设置所属街道
        resultMap.put("appid",approve.getAplid());//设置申请单id
    }

    /**
     * 分页查询待发送年审通知的用户申请信息
     * @param request
     * @return
     */
    @RequestMapping("listInform")
    @ResponseBody
    public Object getInformList(HttpServletRequest request){

        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        String applyUserSsq = request.getParameter("applyUserSsq");
        String applyUserJd = request.getParameter("applyUserJd");
        String applyType = request.getParameter("applyType");//获取申请类型
        int page = 1;
        int rows = 20;
        if(!StringUtils.isEmpty(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if(!StringUtils.isEmpty(rowsStr)){
            rows = Integer.parseInt(rowsStr);
        }
        if(applyUserJd==null){
            applyUserJd="";
        }
        if(applyUserSsq==null){
            applyUserSsq="";
        }

        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");

        String perUserSsq=userInfo.getSsq();//所属区
        if(perUserSsq.indexOf("0")==-1){//如果当前用户不为市区用户，则为当前登录用户的分区
            applyUserSsq=perUserSsq;
        }
        /*如果街道为空，且当前用户时街道用户，则街道为当前审核用户的街道*/
        if(userInfo.getSsj()!=null && StringUtils.isEmpty(applyUserJd)){
            applyUserJd=userInfo.getSsj();
        }

        int pageIndex = (page-1)*rows;
        int pageLaseIndex = page*rows;

        Map<String,Object> map = Maps.newHashMap();
       // PageHelper.startPage(page,rows);
        map.put("pageIndex",pageIndex);
        map.put("pageLaseIndex",pageLaseIndex);
        map.put("username",request.getParameter("applyUsername"));
        map.put("sfzh",request.getParameter("applyUsercard"));
        map.put("oldData",request.getParameter("oldData"));
        map.put("ssq",applyUserSsq);
        map.put("ssj",applyUserJd);
        map.put("applyType",applyType);
        map.put("mesState",request.getParameter("mesState"));
       // PageInfo<Map> pageinfo = new PageInfo<Map>(this.applyNsService.selectNsUserApp());

        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("rows",this.applyNsService.selectNsUserApp(map));//查询数据列表
        resultMap.put("total",this.applyNsService.selectNsUserAppCount(map));//查询总行数
        return resultMap;
    }

    /**
     * 跳转到年审通知提醒界面
     * @return
     */
    @RequestMapping("toinformNs")
    public String toInformNs(){
        return "message/informNs";
    }

    /**
     * 跳转到年审通知提醒界面
     * @return
     */
    @RequestMapping("informNsNew")
    public String informNsNew(){
        return "message/informNsNew";
    }



    /**
     * 跳转到年审通知信息界面
     * @return
     */
    @RequestMapping("toMesInform")
    public String toMesInform(){
        return "message/informDialog/mesInform";
    }

}