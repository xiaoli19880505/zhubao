package com.sys.controller.task;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.common.DatetimeUtils;
import com.sys.common.JsonDateValueProcessor;
import com.sys.common.PropertiesUtil;
import com.sys.common.StringUtils;
import com.sys.common.dataconver.DateJsonValueProcessor;
import com.sys.common.filema.FileManagerUtil;
import com.sys.mapper.apply.ApproveMapper;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.RoleInfo;
import com.sys.pojo.UserInfo;
import com.sys.pojo.apply.*;
import com.sys.pojo.blagsh.Blgsh;
import com.sys.service.*;
import com.sys.service.apply.*;
import com.sys.service.blags.BlgshService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Desc:用户任务控制类
 * @Author:wangli
 * @CreateTime:19:50 2018/10/15
 */
@Controller
@RequestMapping("task")
public class TaskController {

   // private Logger log = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserTaskService userTaskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ApproveService approveService;

    @Autowired
    private BlgshService blgshService;

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
    private  MyTaskService myTaskService;

    @Autowired
    private FlowService flowService;

    @Autowired
    private ApproveMapper approveMapper;


    /**
     * 跳转到个人审核页面
     * @return
     */
    @RequestMapping("toApprove")
    public ModelAndView toApprove(HttpServletRequest request){

        /*参数保存于modelview中*/
        ModelAndView modelAndView = new ModelAndView();

        String applyType = request.getParameter("applyType");//获取申请类型
        String applyId = request.getParameter("applyId");//获取申请单id
        String processInstanceId = request.getParameter("processInstanceId");//获取流程实例id
        String chengxin = request.getParameter("chengxin");//获取流程实例id

        /*通过流程实例查到正在审批的节点task*/
        if(!StringUtils.isEmpty(processInstanceId)){
            Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).
                    singleResult();
            if(task.getName().indexOf(PropertiesUtil.getFlowProperties("reauditname"))!=-1){
                modelAndView.addObject("yuanjianFlag",1);
            }
        }

        //ApplyUserinfo applyUserinfo = (ApplyUserinfo)request.getSession().getAttribute("applyUserinfo");

        modelAndView.addObject("applyType",applyType);
        modelAndView.addObject("applyId",applyId);
        modelAndView.addObject("processInstanceId",processInstanceId);
        modelAndView.addObject("chengxin",chengxin);

        if(!StringUtils.isEmpty(processInstanceId)){
            /*如果当前节点的下一个节点含有“原件复审”字样，则认为是需要发送原件复审短信*/
            List<TaskDefinition> taskDefinitionList = flowService.getTaskDefinitionList(processInstanceId);
            if(taskDefinitionList!=null && !taskDefinitionList.isEmpty()){
                String taskname = taskDefinitionList.get(0).getNameExpression().getExpressionText();
                /*是否是原件复审节点*/
                if (taskname.indexOf(PropertiesUtil.getFlowProperties("reauditname"))!=-1) {
                    modelAndView.addObject("reaudit",0);
                }else{
                    modelAndView.addObject("reaudit",1);
                }
            }else{
                if(applyType.equals(PropertiesUtil.getApplyTypeProperties("butie"))){
                    Map<String,Object> conditionMap = Maps.newHashMap();
                    conditionMap.put("processInstanceId",processInstanceId);
                    Approve approve = this.approveService.findByMap(conditionMap);//审批单
                    ApplyUserinfo applyUserinfo = this.applyUserinfoService.selectById(approve.getApluserid());//查询申请用户
                   // ScheduleJobServiceImpl.java

                    /*查询用户是否存在保障中的低保特困公租房*/
                    conditionMap.put("sfzh",applyUserinfo.getSfzh());//身份证号
                    conditionMap.put("apSqlb","3");//申请类别为低保特困公租房
                    conditionMap.put("apZt",4);
                    Apply apply = this.applyService.selectByMap(conditionMap);
                    if(apply!=null){
                        modelAndView.addObject("gzfExt",1);
                    }
                }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))){
                    Map<String,Object> conditionMap = Maps.newHashMap();
                    conditionMap.put("processInstanceId",processInstanceId);
                    Approve approve = this.approveService.findByMap(conditionMap);//审批单
                    ApplyUserinfo applyUserinfo = this.applyUserinfoService.selectById(approve.getApluserid());//查询申请用户

                    /*查询用户是否存在保障中的补贴申请单*/
                    conditionMap.put("sfzh",applyUserinfo.getSfzh());//身份证号
                    conditionMap.put("abzt",4);
                    ApplyButie applyButie = this.applyButieService.selectByMap(conditionMap);
                    if(applyButie!=null){
                        modelAndView.addObject("butieExt",1);
                    }
                }
                modelAndView.addObject("reaudit",2);
            }
        }

        /*根据申请类型，跳转到不同类型页面进行反写*/
        if(applyType.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))){
            modelAndView.setViewName("houseApply/applyDialog/approveJsf");
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("butie"))){
            modelAndView.setViewName("houseApply/applyDialog/approveButie");
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))){
            modelAndView.setViewName("houseApply/applyDialog/approveGzf");
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))){
            modelAndView.setViewName("houseApply/applyDialog/approveWlh");
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))){
            modelAndView.setViewName("houseApply/applyDialog/approveXjy");
        }
        return modelAndView;
    }


    /**
     * 跳转到合同查看详情
     * @return
     */
    @RequestMapping("toApplyForHtLook")
    public ModelAndView toApplyForHtLook(HttpServletRequest request){

        String applyType = request.getParameter("applyType");//获取申请类型
        String applyId = request.getParameter("applyId");//获取申请单id
//        String processInstanceId = request.getParameter("processInstanceId");//获取流程实例id
        System.out.println("applyType"+applyType);
        System.out.println("applyId"+applyId);
        /*参数保存于modelview中*/
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("applyType",applyType);
        modelAndView.addObject("applyId",applyId);
//        modelAndView.addObject("processInstanceId",processInstanceId);

        /*根据申请类型，跳转到不同类型页面进行反写*/
        if(applyType.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))){
            modelAndView.setViewName("houseRecord/view/perApplyJsfUpdate");
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("butie"))){
            modelAndView.setViewName("houseRecord/view/perApplyButieUpdate");
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))){
            modelAndView.setViewName("houseRecord/view/perApplyLzfUpdate");
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))){
            modelAndView.setViewName("houseRecord/view/perApplyWlwUpdate");
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))){
            modelAndView.setViewName("houseRecord/view/perApplyXjyUpdate");
        }
        return modelAndView;
    }

    /**
     * 跳转到个人审核页面
     * @return
     */
    @RequestMapping("toApplyUpdate")
    public ModelAndView toApplyUpdate(HttpServletRequest request){

        String applyType = request.getParameter("applyType");//获取申请类型
        String applyId = request.getParameter("applyId");//获取申请单id
//        String processInstanceId = request.getParameter("processInstanceId");//获取流程实例id
        System.out.println("applyType"+applyType);
        System.out.println("applyId"+applyId);
        /*参数保存于modelview中*/
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("applyType",applyType);
        modelAndView.addObject("applyId",applyId);
//        modelAndView.addObject("processInstanceId",processInstanceId);

        /*根据申请类型，跳转到不同类型页面进行反写*/
        if(applyType.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))){
            modelAndView.setViewName("applicationForm/perApply/perApplyJsfUpdate");
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("butie"))){
            modelAndView.setViewName("applicationForm/perApply/perApplyButieUpdate");
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))){
            modelAndView.setViewName("applicationForm/perApply/perApplyLzfUpdate");
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))){
            modelAndView.setViewName("applicationForm/perApply/perApplyWlwUpdate");
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))){
            modelAndView.setViewName("applicationForm/perApply/perApplyXjyUpdate");
        }
        return modelAndView;
    }


    /**
     * 跳转到审批意见查询页面
     * @param request
     * @return
     */
    @RequestMapping("toApproveOpinion")
    public ModelAndView toApproveOpinion(HttpServletRequest request){
        //String processInstanceId = request.getParameter("processInstanceId");//流程实例id
        ModelAndView modelAndView = new ModelAndView();
       // modelAndView.addObject("processInstanceId",processInstanceId);
        modelAndView.setViewName("houseApply/applyDialog/approveOpinion");//跳转到审批意见页面
        return modelAndView;
    }

    /**
     * 跳转到审核进度查询页面
     * @param request
     * @return
     */
    @RequestMapping("toApproveProcess")
    public ModelAndView toProcessSearch(HttpServletRequest request){
        //String processInstanceId = request.getParameter("processInstanceId");//流程实例id
        ModelAndView modelAndView = new ModelAndView();
        // modelAndView.addObject("processInstanceId",processInstanceId);
        modelAndView.setViewName("houseApply/applyDialog/approveProcess");//跳转到审批意见页面
        return modelAndView;
    }

    @RequestMapping("toAuditProcess")
    public ModelAndView toAuditProcess(HttpServletRequest request){
        //String processInstanceId = request.getParameter("processInstanceId");//流程实例id
        ModelAndView modelAndView = new ModelAndView();
        // modelAndView.addObject("processInstanceId",processInstanceId);
        modelAndView.setViewName("houseApply/applyDialog/approveAuditProcess");//跳转到审批意见页面
        return modelAndView;
    }

    /**
     * 已审核数量
     * @param request
     * @return
     */
    @RequestMapping("/rejectApplyCount")
    public ModelAndView rejectApplyCount(HttpServletRequest request){

        HttpSession session =request.getSession();
        /*获取申请的审批类类别。1、初审 2、年审*/
        String aplb = request.getParameter("aplb");

        ModelAndView modelAndView = new ModelAndView();

        /*获取用户信息*/
        UserInfo userInfo = (UserInfo)session.getAttribute("user");

        String ssq = userInfo.getSsq();//获取用户区
        String ssj = userInfo.getSsj();//获取用户街道信息
        /*如果为市区用户，则去掉市区过滤查询*/
        if(ssq==null || ssq.indexOf("0")>-1){
            ssq="";
        }
        /*如果当前街道信息为null，则设为空*/
        if(StringUtils.isEmpty(ssj)){
            ssj="";
        }

        String notApplyType = "1000";

        /*如果是年审，则根据当前用户的登录角色名来判断查询类别*/
        if("ns".equals(aplb) && userInfo.getSsq().equals("0")){
            Map<String, Object> newHashMap = Maps.newHashMap();
            newHashMap.put("userid", userInfo.getUserid());
            /*需要判断用户是公租房管理公司还是市住房保障中心，通过角色名判断*/
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
                modelAndView.addObject("area",1);
            }else{
                modelAndView.addObject("area",0);
            }
        }

        /**
         * 查询区内已审核总数
         */
        Long   allCount = 0L;
        /**
         * 查询区内已审核总数
         */
        if("ns".equals(aplb)){
            if("1000".equals(notApplyType)){
                allCount=this.historyService.createHistoricTaskInstanceQuery()
                        .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                        .processVariableValueLike("applyUserJd","%"+ssj+"%")
                        .processVariableValueLike("aplb",aplb)
                        .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("butie"))
                        .taskVariableValueLike("shzt", "驳回")
                        .finished()
                        .count();
            }else{
                allCount=this.historyService.createHistoricTaskInstanceQuery()
                        .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                        .processVariableValueLike("applyUserJd","%"+ssj+"%")
                        .processVariableValueLike("aplb",aplb)
                        .processVariableValueNotEquals("applyType",notApplyType)
                        .taskVariableValueLike("shzt", "驳回")
                        .finished()
                        .count();
            }
        }else{
            allCount=this.historyService.createHistoricTaskInstanceQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .processVariableValueLike("aplb",aplb)
                    .taskVariableValueLike("shzt", "驳回")
                    .finished()
                    .count();
        }


        /**
         * 查询区内外来务工申请待审核总数
         */
        Long wailaiCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb",aplb)
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("wailaiwg"))
                .taskVariableValueLike("shzt", "驳回")
                .finished()
                .count();

        /**
         * 查询区内新就业申请已审核总数
         */
        Long xinjyCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb",aplb)
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("xinjy"))
                .taskVariableValueLike("shzt", "驳回")
                .finished()
                .count();

        /**
         * 查询区内经适房申请已审核总数
         */
        Long jingsfCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb",aplb)
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("jingsf"))
                .taskVariableValueLike("shzt", "驳回")
                .finished()
                .count();

        /**
         * 查询区内特困补贴申请已审核总数
         */
        Long butieCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb",aplb)
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("butie"))
                .taskVariableValueLike("shzt", "驳回")
                .finished()
                .count();

        /**
         * 查询区内公租房申请待已核总数
         */
        Long gongzfCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb",aplb)
                .taskVariableValueLike("shzt", "驳回")
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("gongzf"))
                .finished()
                .count();

        /*将查询信息存于modeview中*/

        modelAndView.addObject("allCount",allCount);
        modelAndView.addObject("wailaiCount",wailaiCount);
        modelAndView.addObject("jingsfCount",jingsfCount);
        modelAndView.addObject("xinjyCount",xinjyCount);
        modelAndView.addObject("gongzfCount",gongzfCount);
        modelAndView.addObject("butieCount",butieCount);
        if("cs".equals(aplb)){
            modelAndView.setViewName("houseApply/rejectApply");
        }else{
            modelAndView.setViewName("houseAudit/rejectApply");
        }
        return modelAndView;
    }

    /**
     * 普通、年审驳回查询
     * @param request
     * @return
     */
    @RequestMapping("getRejectApplyCount")
    @ResponseBody
    public Object getRejectApplyCount(HttpServletRequest request){
        HttpSession session =request.getSession();
        /*获取申请的审批类类别。1、初审 2、年审*/
        String aplb = request.getParameter("aplb");
        /*获取用户信息*/
        UserInfo userInfo = (UserInfo)session.getAttribute("user");

        /*获取用户信息*/
//        UserInfo userInfo = (UserInfo)session.getAttribute("user");

        String ssq = request.getParameter("ssq");//获取用户区
        String ssj = request.getParameter("ssj");//获取用户街道信息
        /*如果为市区用户，则去掉市区过滤查询*/
        if(StringUtils.isEmpty(ssq) || ssq.indexOf("0")!=-1){
            ssq="";
        }
        /*如果当前街道信息为null，则设为空*/
        if(StringUtils.isEmpty(ssj)){
            ssj="";
        }
        /*如果申请类别，则设为空*/
        if(StringUtils.isEmpty(aplb)){
            aplb="";
        }

        String notApplyType = "1000";

        /*如果是年审，则根据当前用户的登录角色名来判断查询类别*/
        if("ns".equals(aplb) && userInfo.getSsq().equals("0")){
            Map<String, Object> newHashMap = Maps.newHashMap();
            newHashMap.put("userid", userInfo.getUserid());
            /*需要判断用户是公租房管理公司还是市住房保障中心，通过角色名判断*/
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
            }
        }

        /**
         * 查询区内待审核总数
         */
        Long   allCount = 0L;
        /**
         * 查询区内已审核总数
         */
        if("ns".equals(aplb)){
            if("1000".equals(notApplyType)){
                allCount=this.historyService.createHistoricTaskInstanceQuery()
                        .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                        .processVariableValueLike("applyUserJd","%"+ssj+"%")
                        .processVariableValueLike("aplb","%"+aplb+"%")
                        .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("butie"))
                        .taskVariableValueLike("shzt", "驳回")
                        .finished()
                        .count();
            }else{
                allCount=this.historyService.createHistoricTaskInstanceQuery()
                        .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                        .processVariableValueLike("applyUserJd","%"+ssj+"%")
                        .processVariableValueLike("aplb","%"+aplb+"%")
                        .processVariableValueNotEquals("applyType",notApplyType)
                        .taskVariableValueLike("shzt", "驳回")
                        .finished()
                        .count();
            }
        }else{
            allCount=this.historyService.createHistoricTaskInstanceQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .processVariableValueLike("aplb","%"+aplb+"%")
                    .taskVariableValueLike("shzt", "驳回")
                    .finished()
                    .count();
        }



        /**
         * 查询区内外来务工申请待审核总数
         */
        Long wailaiCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("wailaiwg"))
                .taskVariableValueLike("shzt", "驳回")
                .finished()
                .count();

        /**
         * 查询区内新就业申请已审核总数
         */
        Long xinjyCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("xinjy"))
                .taskVariableValueLike("shzt", "驳回")
                .finished()
                .count();

        /**
         * 查询区内经适房申请已审核总数
         */
        Long jingsfCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("jingsf"))
                .taskVariableValueLike("shzt", "驳回")
                .finished()
                .count();

        /**
         * 查询区内特困补贴申请已审核总数
         */
        Long butieCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("butie"))
                .taskVariableValueLike("shzt", "驳回")
                .finished()
                .count();

        /**
         * 查询区内公租房申请待已核总数
         */
        Long gongzfCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .taskVariableValueLike("shzt", "驳回")
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("gongzf"))
                .finished()
                .count();

        /*将查询信息存于modeview中*/
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("allCount",allCount);
        map.put("wailaiCount",wailaiCount);
        map.put("jingsfCount",jingsfCount);
        map.put("xinjyCount",xinjyCount);
        map.put("gongzfCount",gongzfCount);
        map.put("butieCount",butieCount);
        return map;
    }


    /**
     * 跳到个人待审批页面
     * @param request
     * @return
     */
    @RequestMapping("/toPerApprove")
    public ModelAndView toPerApprove(HttpServletRequest request){

        /*获取申请的审批类类别。1、初审 2、年审*/
        String aplb = request.getParameter("aplb");
        /*获取session*/
        HttpSession session = request.getSession();

        /*获取用户信息*/
        UserInfo userInfo = (UserInfo)session.getAttribute("user");
        String ssq = userInfo.getSsq();//获取用户区
        String ssj = userInfo.getSsj();//获取用户街道信息
        /*如果为市区用户，则去掉市区过滤查询*/
        if(ssq.indexOf("0")!=-1){
            ssq="";
        }


        /*如果当前街道信息为null，则设为空*/
        if(StringUtils.isEmpty(ssj)){
            /*依据当前用户登录的角色信息，判断其所属类别*/
            ssj="";
        }

        String notApplyType = "1000";

        /*如果是年审，则根据当前用户的登录角色名来判断查询类别*/
        if("ns".equals(aplb) && userInfo.getSsq().equals("0")){
                Map<String, Object> newHashMap = Maps.newHashMap();
                newHashMap.put("userid", userInfo.getUserid());
                /*需要判断用户是公租房管理公司还是市住房保障中心，通过角色名判断*/
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
                }
        }

        /**
         * 查询区内待审核总数
         */
        Long   allCount = 0L;
        /*如果是年审，当登录用户为公租房管理公司时，查询非补贴的年审申请；
        * 当登录用户为住保中心时，查询补贴的年审申请汇总*/
        if("ns".equals(aplb)) {
            if("1000".equals(notApplyType)){
                allCount = this.taskService.createTaskQuery()
                        .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                        .processVariableValueLike("applyUserJd","%"+ssj+"%")
                        .processVariableValueLike("aplb",aplb)
                        .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("butie"))
                        .active()
                        .count();
            }else{
                allCount = this.taskService.createTaskQuery()
                        .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                        .processVariableValueLike("applyUserJd","%"+ssj+"%")
                        .processVariableValueLike("aplb",aplb)
                        .processVariableValueNotEquals("applyType",notApplyType)
                        .active()
                        .count();
            }
        }else{
            allCount = this.taskService.createTaskQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .processVariableValueLike("aplb",aplb)
                    .active()
                    .count();
        }
            /**
             * 查询区内外来务工申请待审核总数
             */
        Long   wailaiCount = taskService.createTaskQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                     .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .processVariableValueLike("aplb",aplb)
                    .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("wailaiwg"))
                    .active()
                    .count();

            /**
             * 查询区内新就业申请待审核总数
             */
        Long   xinjyCount = taskService.createTaskQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .processVariableValueLike("aplb",aplb)
                    .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("xinjy"))
                    .active()
                    .count();

            /**
             * 查询区内经适房申请待审核总数
             */
        Long   jingsfCount = taskService.createTaskQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                     .processVariableValueLike("aplb",aplb)
                    .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("jingsf"))
                    .active()
                    .count();


            /**
             * 查询区内公租房申请待审核总数
             */
        Long   gongzfCount = taskService.createTaskQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                     .processVariableValueLike("applyUserJd","%"+ssj+"%")
                     .processVariableValueLike("aplb",aplb)
                    .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("gongzf"))
                    .active()
                    .count();

        /**
         * 查询区内补贴申请待审核总数
         */
        Long   butieCount = taskService.createTaskQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb",aplb)
                .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("butie"))
                .active()
                .count();

        /*将查询信息存于modeview中*/
        ModelAndView modelAndView = new ModelAndView();

        /*如果是年审的查询，则要判断当前登录用户是公租房管理公司的还是市住保中心的用户*/
        if("ns".equals(aplb)) {
            Map<String, Object> conditionMap = Maps.newHashMap();
            conditionMap.put("userid", userInfo.getUserid());
            if (userInfo.getSsq().equals("0")) {
                /*需要判断用户是公租房管理公司还是市住房保障中心，通过角色名判断*/
                boolean flag = false;
                List<RoleInfo> roleInfoList = roleInfoService.getRoleListByMap(conditionMap);
                for (RoleInfo roleInfo : roleInfoList) {
                    if (roleInfo.getDutyname().indexOf("公租房") != -1) {
                        flag = true;
                        break;
                    }
                }

                /*如果是公租房管理公司的，则area设为1，否则设为0*/
                if (flag) {
                    modelAndView.addObject("area", 1);
                } else {
                    modelAndView.addObject("area", 0);
                }
            }
        }


        modelAndView.addObject("allCount",allCount);
        modelAndView.addObject("wailaiCount",wailaiCount);
        modelAndView.addObject("jingsfCount",jingsfCount);
        modelAndView.addObject("xinjyCount",xinjyCount);
        modelAndView.addObject("gongzfCount",gongzfCount);
        modelAndView.addObject("butieCount",butieCount);
        if("ns".equals(aplb)){//当flag为2时跳到年审待审核界面
            modelAndView.setViewName("houseAudit/noauditApply");
        }else{//当flag为其他情况时跳到初审待审核界面
            modelAndView.setViewName("houseApply/noauditApply");
        }

        return modelAndView;
    }

    /**
     * 获取申请头信息
     * @param request
     * @return
     */
    @RequestMapping("getPerApprove")
    @ResponseBody
    public Object getPerApprove(HttpServletRequest request){
        /*获取申请的审批类类别。1、初审 2、年审*/
        String aplb = request.getParameter("aplb");
        /*获取session*/
        HttpSession session = request.getSession();
        /*获取用户信息*/
//        UserInfo userInfo = (UserInfo)session.getAttribute("user");
        String ssq = request.getParameter("ssq");//获取用户区
        String ssj = request.getParameter("ssj");//获取用户街道信息
        /*如果为市区用户，则去掉市区过滤查询*/
        if(StringUtils.isEmpty(ssq) || ssq.indexOf("0")!=-1){
            ssq="";
        }
        /*如果当前街道信息为null，则设为空*/
        if(StringUtils.isEmpty(ssj)){
            ssj="";
        }
        /*如果申请类别，则设为空*/
        if(StringUtils.isEmpty(aplb)){
            aplb="";
        }

        /*获取用户信息*/
        UserInfo userInfo = (UserInfo)session.getAttribute("user");
        String notApplyType = "1000";

        /*如果是年审，则根据当前用户的登录角色名来判断查询类别*/
        if("ns".equals(aplb) && userInfo.getSsq().equals("0")){
            Map<String, Object> newHashMap = Maps.newHashMap();
            newHashMap.put("userid", userInfo.getUserid());
            /*需要判断用户是公租房管理公司还是市住房保障中心，通过角色名判断*/
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
            }
        }

        /**
         * 查询区内待审核总数
         */
        Long   allCount = 0L;
        /*如果是年审，当登录用户为公租房管理公司时，查询非补贴的年审申请；
         * 当登录用户为住保中心时，查询补贴的年审申请汇总*/
        if("ns".equals(aplb)) {
            if("1000".equals(notApplyType)){
                allCount = this.taskService.createTaskQuery()
                        .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                        .processVariableValueLike("applyUserJd","%"+ssj+"%")
                        .processVariableValueLike("aplb",aplb)
                        .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("butie"))
                        .active()
                        .count();
            }else{
                allCount = this.taskService.createTaskQuery()
                        .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                        .processVariableValueLike("applyUserJd","%"+ssj+"%")
                        .processVariableValueLike("aplb",aplb)
                        .processVariableValueNotEquals("applyType",notApplyType)
                        .active()
                        .count();
            }
        }else{
            allCount = this.taskService.createTaskQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .processVariableValueLike("aplb",aplb)
                    .active()
                    .count();
        }

        /**
         * 查询区内待审核总数
         */
       /* Long   allCount = this.taskService.createTaskQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .active()
                .count();*/
        /**
         * 查询区内外来务工申请待审核总数
         */
        Long   wailaiCount = taskService.createTaskQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("wailaiwg"))
                .active()
                .count();

        /**
         * 查询区内新就业申请待审核总数
         */
        Long   xinjyCount = taskService.createTaskQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("xinjy"))
                .active()
                .count();

        /**
         * 查询区内经适房申请待审核总数
         */
        Long   jingsfCount = taskService.createTaskQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("jingsf"))
                .active()
                .count();


        /**
         * 查询区内公租房申请待审核总数
         */
        Long   gongzfCount = taskService.createTaskQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("gongzf"))
                .active()
                .count();

        /**
         * 查询区内补贴申请待审核总数
         */
        Long   butieCount = taskService.createTaskQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("butie"))
                .active()
                .count();

        /*将查询信息存于modeview中*/
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("allCount",allCount);
        map.put("wailaiCount",wailaiCount);
        map.put("jingsfCount",jingsfCount);
        map.put("xinjyCount",xinjyCount);
        map.put("gongzfCount",gongzfCount);
        map.put("butieCount",butieCount);

        return map;
    }

    /**
     * 已审核数量
     * @param request
     * @return
     */
    @RequestMapping("/auditApplyCount")
    public ModelAndView auditApplyCount(HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView();
        HttpSession session =request.getSession();
        /*获取申请的审批类类别。1、初审 2、年审*/
        String aplb = request.getParameter("aplb");

        /*获取用户信息*/
        UserInfo userInfo = (UserInfo)session.getAttribute("user");
        String ssq = userInfo.getSsq();//获取用户区
        String ssj = userInfo.getSsj();//获取用户街道信息
        /*如果为市区用户，则去掉市区过滤查询*/
        if(ssq.indexOf("0")!=-1){
            ssq="";
        }
        /*如果当前街道信息为null，则设为空*/
        if(StringUtils.isEmpty(ssj)){
            ssj="";
        }

        Long time1 = System.currentTimeMillis();

        /*获取用户信息*/
        String notApplyType = "1000";

        /*如果是年审，则根据当前用户的登录角色名来判断查询类别*/
        if("ns".equals(aplb) && userInfo.getSsq().equals("0")){
            Map<String, Object> newHashMap = Maps.newHashMap();
            newHashMap.put("userid", userInfo.getUserid());
            /*需要判断用户是公租房管理公司还是市住房保障中心，通过角色名判断*/
            boolean flag = false;
            List<RoleInfo> roleInfoList = roleInfoService.getRoleListByMap(newHashMap);
            for (RoleInfo roleInfo : roleInfoList) {
                if (roleInfo.getDutyname().indexOf("公租房") != -1) {
                    flag = true;
                    break;
                }
            }
            /*如果是公租房管理公司的，则查询非，否则设为0*/
            /*如果是公租房管理公司的，则area设为1，否则设为0*/
            if (flag) {
                modelAndView.addObject("area", 1);
                notApplyType = PropertiesUtil.getApplyTypeProperties("butie");
            } else {
                modelAndView.addObject("area", 0);
            }
        }


        Long allCount = 0L;

        if("ns".equals(aplb)) {
            if("1000".equals(notApplyType)){
                allCount = this.historyService.createHistoricTaskInstanceQuery()
                        .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                        .processVariableValueLike("applyUserJd","%"+ssj+"%")
                        .processVariableValueLike("aplb",aplb)
                        .processVariableValueLike("applyType", "%%")
                        .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("butie"))
                        .taskVariableValueLike("shzt", "通过")
                        .finished()
                        .count();
            }else{
                allCount = this.historyService.createHistoricTaskInstanceQuery()
                        .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                        .processVariableValueLike("applyUserJd","%"+ssj+"%")
                        .processVariableValueLike("aplb",aplb)
                        .processVariableValueLike("applyType", "%%")
                        .processVariableValueNotEquals("applyType",notApplyType)
                        .taskVariableValueLike("shzt", "通过")
                        .finished()
                        .count();
            }
        }else{
            allCount = this.historyService.createHistoricTaskInstanceQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .processVariableValueLike("aplb",aplb)
                    .processVariableValueLike("applyType", "%%")
                    .taskVariableValueLike("shzt", "通过")
                    .finished()
                    .count();

        }


        /**
         * 查询区内已审核总数
         */

            /**
             * 查询区内外来务工申请待审核总数
             */
            Long wailaiCount = this.historyService.createHistoricTaskInstanceQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .processVariableValueLike("aplb",aplb)
                    .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("wailaiwg"))
                    .taskVariableValueLike("shzt", "通过")
                    .finished()
                    .count();



        /**
         * 查询区内新就业申请已审核总数
         */
            Long xinjyCount = this.historyService.createHistoricTaskInstanceQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .processVariableValueLike("aplb",aplb)
                    .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("xinjy"))
                    .taskVariableValueLike("shzt", "通过")
                    .finished()
                    .count();


            /**
             * 查询区内经适房申请已审核总数
             */
            Long jingsfCount = this.historyService.createHistoricTaskInstanceQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .processVariableValueLike("aplb",aplb)
                    .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("jingsf"))
                    .taskVariableValueLike("shzt", "通过")
                    .finished()
                    .count();


            /**
             * 查询区内特困补贴申请已审核总数
             */
            Long butieCount = this.historyService.createHistoricTaskInstanceQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .processVariableValueLike("aplb",aplb)
                    .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("butie"))
                    .taskVariableValueLike("shzt", "通过")
                    .finished()
                    .count();


            /**
             * 查询区内公租房申请待已核总数
             */
            Long gongzfCount = this.historyService.createHistoricTaskInstanceQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .processVariableValueLike("aplb",aplb)
                    .taskVariableValueLike("shzt", "通过")
                    .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("gongzf"))
                    .finished()
                    .count();


        /*将查询信息存于modeview中*/

        modelAndView.addObject("allCount",allCount);
        modelAndView.addObject("wailaiCount",wailaiCount);
        modelAndView.addObject("jingsfCount",jingsfCount);
        modelAndView.addObject("xinjyCount",xinjyCount);
        modelAndView.addObject("gongzfCount",gongzfCount);
        modelAndView.addObject("butieCount",butieCount);
        if("cs".equals(aplb)){
            modelAndView.setViewName("houseApply/auditApply");
        }else{
            modelAndView.setViewName("houseAudit/auditApply");
        }
        return modelAndView;
    }

    /**
     * 申请年审已审核头信息
     * @param request
     * @return
     */
    @RequestMapping("getAuditApplyCount")
    @ResponseBody
    public Object getAuditApplyCount(HttpServletRequest request){
        HttpSession session =request.getSession();
        /*获取申请的审批类类别。1、初审 2、年审*/
        String aplb = request.getParameter("aplb");

        /*获取用户信息*/
//        UserInfo userInfo = (UserInfo)session.getAttribute("user");
        String ssq = request.getParameter("ssq");//获取用户区
        String ssj = request.getParameter("ssj");//获取用户街道信息
        /*如果为市区用户，则去掉市区过滤查询*/
        if(StringUtils.isEmpty(ssq) || ssq.indexOf("0")!=-1){
            ssq="";
        }
        /*如果当前街道信息为null，则设为空*/
        if(StringUtils.isEmpty(ssj)){
            ssj="";
        }
        /*如果申请类别，则设为空*/
        if(StringUtils.isEmpty(aplb)){
            aplb="";
        }

        Long time1 = System.currentTimeMillis();

        /*获取用户信息*/
        UserInfo userInfo = (UserInfo)session.getAttribute("user");
        /*获取用户信息*/
        String notApplyType = "1000";

        /*如果是年审，则根据当前用户的登录角色名来判断查询类别*/
        if("ns".equals(aplb) && userInfo.getSsq().equals("0")){
            Map<String, Object> newHashMap = Maps.newHashMap();
            newHashMap.put("userid", userInfo.getUserid());
            /*需要判断用户是公租房管理公司还是市住房保障中心，通过角色名判断*/
            boolean flag = false;
            List<RoleInfo> roleInfoList = roleInfoService.getRoleListByMap(newHashMap);
            for (RoleInfo roleInfo : roleInfoList) {
                if (roleInfo.getDutyname().indexOf("公租房") != -1) {
                    flag = true;
                    break;
                }
            }
            /*如果是公租房管理公司的，则查询非，否则设为0*/
            /*如果是公租房管理公司的，则area设为1，否则设为0*/
            if (flag) {
                notApplyType = PropertiesUtil.getApplyTypeProperties("butie");
            }
        }


        Long allCount = 0L;

        /**
         * 查询区内已审核总数
         */

        if("ns".equals(aplb)) {
            if("1000".equals(notApplyType)){
                allCount = this.historyService.createHistoricTaskInstanceQuery()
                        .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                        .processVariableValueLike("applyUserJd","%"+ssj+"%")
                        .processVariableValueLike("aplb","%"+aplb+"%")
                        .processVariableValueLike("applyType", "%%")
                        .processVariableValueEquals("applyType",PropertiesUtil.getApplyTypeProperties("butie"))
                        .taskVariableValueLike("shzt", "通过")
                        .finished()
                        .count();
            }else{
                allCount = this.historyService.createHistoricTaskInstanceQuery()
                        .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                        .processVariableValueLike("applyUserJd","%"+ssj+"%")
                        .processVariableValueLike("aplb","%"+aplb+"%")
                        .processVariableValueLike("applyType", "%%")
                        .processVariableValueNotEquals("applyType",notApplyType)
                        .taskVariableValueLike("shzt", "通过")
                        .finished()
                        .count();
            }
        }else{
            allCount = this.historyService.createHistoricTaskInstanceQuery()
                    .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                    .processVariableValueLike("applyUserJd","%"+ssj+"%")
                    .processVariableValueLike("aplb","%"+aplb+"%")
                    .processVariableValueLike("applyType", "%%")
                    .taskVariableValueLike("shzt", "通过")
                    .finished()
                    .count();

        }



        /**
         * 查询区内外来务工申请待审核总数
         */
        Long wailaiCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("wailaiwg"))
                .taskVariableValueLike("shzt", "通过")
                .finished()
                .count();



        /**
         * 查询区内新就业申请已审核总数
         */
        Long xinjyCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("xinjy"))
                .taskVariableValueLike("shzt", "通过")
                .finished()
                .count();


        /**
         * 查询区内经适房申请已审核总数
         */
        Long jingsfCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("jingsf"))
                .taskVariableValueLike("shzt", "通过")
                .finished()
                .count();


        /**
         * 查询区内特困补贴申请已审核总数
         */
        Long butieCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("butie"))
                .taskVariableValueLike("shzt", "通过")
                .finished()
                .count();


        /**
         * 查询区内公租房申请待已核总数
         */
        Long gongzfCount = this.historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUserSsq","%"+ssq+"%")
                .processVariableValueLike("applyUserJd","%"+ssj+"%")
                .processVariableValueLike("aplb","%"+aplb+"%")
                .taskVariableValueLike("shzt", "通过")
                .processVariableValueLike("applyType", PropertiesUtil.getApplyTypeProperties("gongzf"))
                .finished()
                .count();


        /*将查询信息存于modeview中*/
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("allCount",allCount);
        map.put("wailaiCount",wailaiCount);
        map.put("jingsfCount",jingsfCount);
        map.put("xinjyCount",xinjyCount);
        map.put("gongzfCount",gongzfCount);
        map.put("butieCount",butieCount);
        return map;
    }


    /**
     * 查询审核中申请单（包含属于个人的申请单和审批流程中其他角色的申请单）
     * @param request
     * @return
     */
    @RequestMapping("listPerToHandleTask")
    @ResponseBody
    public Object listPerToHandleTask(HttpServletRequest request){

        /*读取条件查询的参数*/
        String applyUsername=request.getParameter("applyUsername");//申请用户姓名
        String applyUsercard=request.getParameter("applyUsercard");//申请用户身份证
        String applyUserSsq=request.getParameter("applyUserSsq");//申请用户所属区
        String applyUserJd = request.getParameter("applyUserJd");//申请用户所属街道
        String applyType = request.getParameter("applyType");//申请类别
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
        if(applyUserSsq==null || "0".equals(applyUserSsq)){
            applyUserSsq="";
        }


        //查询登录的审核用户
       // String usercode = SecurityUtils.getSubject().getPrincipal().toString();
        /*根据用户登录名查询用户实体*/
        Map<String,Object> conditionMap = Maps.newHashMap();
       // conditionMap.put("usercode",usercode);
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");//userInfoService.getUserByMap(conditionMap);
        System.out.println("--userid:"+userInfo.getUserid());
        //String applyUserSsq=userInfo.getSsq();//所属区
        String perUserSsq=userInfo.getSsq();//所属区
        if(perUserSsq.indexOf("0")==-1){//如果当前用户不为市区用户，则为当前登录用户的分区
            applyUserSsq=perUserSsq;
        }
        /*如果街道为空，且当前用户时街道用户，则街道为当前审核用户的街道*/
        if(userInfo.getSsj()!=null && StringUtils.isEmpty(applyUserJd)){
            applyUserJd=userInfo.getSsj();
        }
        System.out.println("applyUsername:"+applyUsername+"--applyUsercard:"+applyUsercard
                +"---applyUserJd:"+applyUserJd+"---applyType:"+applyType
                +"--all:"+all+"---page:"+page+"----rows:"+rows+"--applyUserSsq:"+applyUserSsq);
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
                    .processVariableValueLike("aplb","cs")
                    .active().count();
            /*查询条件下所有的任务列表，分页查询*/
            allTaskList = taskService.createTaskQuery()
                    .processVariableValueLike("applyUsername","%"+applyUsername+"%")
                    .processVariableValueLike("applyUsercard","%"+applyUsercard+"%")
                    .processVariableValueLike("applyUserSsq","%"+applyUserSsq+"%")
                    .processVariableValueLike("applyUserJd","%"+applyUserJd+"%")
                    .processVariableValueLike("applyType","%"+applyType+"%")
                    .processVariableValueLike("aplb","cs")
                    .active()
                    .orderByTaskCreateTime()
                    .desc()
                    .listPage(pageIndex,pageSize);
        }else{
            /*查询条件下所有的个人任务列表数目*/
            count = taskService.createTaskQuery()
                    .taskCandidateUser(userInfo.getUserid())
                    .processVariableValueLike("applyUsername","%"+applyUsername+"%")
                    .processVariableValueLike("applyUsercard","%"+applyUsercard+"%")
                    .processVariableValueLike("applyUserSsq","%"+applyUserSsq+"%")
                    .processVariableValueLike("applyUserJd","%"+applyUserJd+"%")
                    .processVariableValueLike("applyType","%"+applyType+"%")
                    .processVariableValueLike("aplb","cs")
                    .active().count();

            /*查询条件下所有的个人任务列表，分页查询*/
            allTaskList = taskService.createTaskQuery()
                    .taskCandidateUser(userInfo.getUserid())
                    .processVariableValueLike("applyUsername","%"+applyUsername+"%")
                    .processVariableValueLike("applyUsercard","%"+applyUsercard+"%")
                    .processVariableValueLike("applyUserSsq","%"+applyUserSsq+"%")
                    .processVariableValueLike("applyUserJd","%"+applyUserJd+"%")
                    .processVariableValueLike("applyType","%"+applyType+"%")
                    .processVariableValueLike("aplb","cs")
                    .active()
                    .orderByTaskCreateTime()
                    .desc()
                    .listPage(pageIndex,pageSize);
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
            dataMap.put("taskId",task.getId());
            dataMap.put("flag",flag);//flag也存于map中
            /*如果是当前用户审批节点，同时该节点的名称又为原件审批，则设置关键字返回给前端*/
            if(flag){
                if(task.getName().indexOf(PropertiesUtil.getFlowProperties("reauditname"))!=-1){
                    dataMap.put("yuanjian",1);
                }
            }
            /*设置节点信息*/
            dataMap.put("nodename",this.roleInfoService.selectById(currTaskGroupId).getDutyname());
            resultList.add(dataMap);
        }

        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("rows",resultList);
        resultMap.put("total",count);
       return resultMap;
    }


    /**
     * 查询历史记录（审批通过或者驳回）
     * @param request
     * @return
     */
    @RequestMapping("listPerHisTask")
    @ResponseBody
    public Object listPerHisTask(HttpServletRequest request){
        /*读取条件查询的参数*/
        String applyUsername=request.getParameter("applyUsername");//申请用户姓名
        String applyUsercard=request.getParameter("applyUsercard");//申请用户身份证
        String applyUserSsq=request.getParameter("applyUserSsq");//申请用户所属区
        String applyUserJd = request.getParameter("applyUserJd");//申请用户所属街道
        String applyType = request.getParameter("applyType");//申请类别
        String state = request.getParameter("state");//申请单状态 驳回或者通过
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");

        int pageIndex =1;
        int pageSize = 10;
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
        if(applyUserSsq==null || applyUserSsq.indexOf("0")!=-1){
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



        //查询登录的审核用户
//        String usercode = SecurityUtils.getSubject().getPrincipal().toString();
        /*根据用户登录名查询用户实体*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");//userInfoService.getUserByMap(conditionMap);
        System.out.println("--userid:"+userInfo.getUserid());
        //String applyUserSsq=userInfo.getSsq();//所属区
        if(StringUtils.isEmpty(applyUserSsq)){
            String perUserSsq=userInfo.getSsq();//所属区
            if(perUserSsq==null || perUserSsq.indexOf("0")!=-1){//如果当前用户不为市区用户，则为当前登录用户的分区
                applyUserSsq="";
            }
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
                .processVariableValueLike("aplb","cs")
                .taskVariableValueLike("shzt","%"+state+"%")
                .count();
        /* 查询用户历史任务，分页查询 */
        List<HistoricTaskInstance> histList = historyService.createHistoricTaskInstanceQuery()
                .processVariableValueLike("applyUsername","%"+applyUsername+"%")
                .processVariableValueLike("applyUsercard","%"+applyUsercard+"%")
                .processVariableValueLike("applyUserSsq","%"+applyUserSsq+"%")
                .processVariableValueLike("applyUserJd","%"+applyUserJd+"%")
                .processVariableValueLike("applyType","%"+applyType+"%")
                .processVariableValueLike("aplb","cs")
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
            flag = false;
            /*获得用户组，即角色id*/
            String currTaskGroupId = idenList.get(0).getGroupId();
            Map<String,Object> dataMap = Maps.newHashMap();
            dataMap.put("processInstanceId",task.getProcessInstanceId());
            findMapResult(dataMap);//根据task信息返回map
           // dataMap.put("flag",flag);//flag也存于map中
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
       // resultMap.put("processInstanceId",task.getProcessInstanceId());
        Approve approve = approveService.findByProcessInstanceId(resultMap);
        System.out.println("*********processInstanceId"+resultMap.get("processInstanceId"));
//        System.out.println("*********getApldate"+approve.getApldate());
       // resultMap.put("taskId",task.getId());//保存用户任务id
        if(approve!=null){
            resultMap.put("applydate",DatetimeUtils.date2string(approve.getApldate(),"yyyy-MM-dd"));//保存申请日期



        /*读取和设置申请用户姓名和身份证号*/
//        ApplyUserinfo applyUserInfo = applyUserinfoService.selectById(approve.getApluserid());
        resultMap.put("username",approve.getUsername());
        resultMap.put("sfzh",approve.getSfzh());

//        String sqbh="";//申请编号
//        String ssq="";//所属区
//        String ssjd="";//所属街道

//        String appid = approve.getAplid();//获得申请单id
//        String aptype = approve.getAptype();//获得申请类别
//        String apptypename="";
        /*根据申请类别和申请单id去查询对应的申请单实体，获取申请编号、所属区、所属街道*/
        /*如果是外来务工的申请单*/
//        if(aptype.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))){
//            ApplyForForeign applyForForeign =applyForForeignService.selectById(appid);
//            sqbh=applyForForeign.getAffSqbh();//读取编号
//            ssq=applyForForeign.getAffSsq();//读取所属区
//            ssjd=applyForForeign.getAffDwdz();//读取所属街道
//            apptypename="外来户申请";
//        }else if(aptype.equals(PropertiesUtil.getApplyTypeProperties("jingsf")) ||
//                aptype.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))){
//                /*经适房和公租房的申请单*/
//                Apply apply = applyService.selectById(appid);
//                sqbh = approve.getAplbh();//apply.getApSqbh();
//                ssq = apply.getApSsq();//applyUserInfo.getSsq();//
//                ssjd = apply.getApJdbsc();//applyUserInfo.getSsj();//
//            if(aptype.equals(PropertiesUtil.getApplyTypeProperties("jingsf")))
//                apptypename="经济适应房";
//            else
//                apptypename="公租房";
//        }else if(aptype.equals(PropertiesUtil.getApplyTypeProperties("butie"))){
//            /*补贴的申请单*/
//            ApplyButie applyButie = this.applyButieService.selectById(appid);
//            sqbh = approve.getAplbh();//applyButie.getAbSqbh();
//            ssq = applyButie.getAbSsq();//applyUserInfo.getSsq();//applyButie.getAbSsq();
//            ssjd = applyButie.getAbJdbsc();//applyUserInfo.getSsj();//applyButie.getAbJdbsc();
//            apptypename="特困补贴";
//        }else if(aptype.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))){
//            /*新就业的申请单*/
//            ApplyForgraDuate applyForgraDuate = this.applyForgraDuateService.selectById(appid);
//            sqbh = applyForgraDuate.getAfgSqbh();
//            ssq=applyForgraDuate.getAfgSsq();
//            ssjd=applyForgraDuate.getAfgDwdz();
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
    }


    /**
     * 审批过程
     * @param request
     * @return
     */
    @RequestMapping("approveTask")
    @ResponseBody
    public Object approveTask(HttpServletRequest request) throws SchedulerException {
        int updateCount=myTaskService.updateTask(request);
        if(updateCount>0){
            return  "审批成功";
        } else{
            return "审批失败";
        }
    }


    /**
     * 查看审核意见
     * @param request
     * @return
     */
    @RequestMapping("listComment")
    @ResponseBody
    public Object findProcessTaskAndComment(HttpServletRequest request){
        /*获取流程实例id*/
        String processInstanceId = request.getParameter("processInstanceId");
        /*查看历史task实例*/
        List<HistoricTaskInstance> hiTaskList = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId).list();

        /*通过流程实例id查询审批单*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("processInstanceId",processInstanceId);
        Approve approve = this.approveService.findByMap(conditionMap);

        /*将审批单中记录的审批用户转为数组*/
        String users = approve.getApvusers();
        String[] userArr;
        if(users!=null && !"".equals(users)){
            userArr=users.split(",");
        }else{
            userArr = new String[0];
        }

        /*结果集*/
        List<Map<String,Object>> resultList = Lists.newArrayList();
        int commentLength =0 ;//记载上次审批意见的数量
        /*对历史任务进行遍历*/
        for (int i=0;i<hiTaskList.size();i++){
            HistoricTaskInstance historicTaskInstance= hiTaskList.get(i);//历史人物实例

            List<HistoricIdentityLink> idenList = historyService.
                    getHistoricIdentityLinksForTask(historicTaskInstance.getId());//历史任务对应的身份认证信息

           /*根据历史流程id查询历史评论信息*/
            List<Comment> commentList = taskService.getTaskComments(historicTaskInstance.getId());
            /*查询节点名称*/
            String nodename = this.roleInfoService.selectById(idenList.get(0).getGroupId()).getDutyname();
            String approveUser="";

            /*对评论信息进行遍历，由于每个可能有多个用户参与的多条评论，所以要合理遍历评论信息*/
            if(commentList!=null && commentList.size()!=0){
                int pos =0;

                for(Comment comment:commentList){
                    if((i*commentLength)<userArr.length){
                        approveUser=userArr[i*commentLength+pos];
                    }else{
                        approveUser="";
                    }
                    Map<String,Object> tempMap = Maps.newHashMap();
                    tempMap.put("nodename",nodename);//节点名称
                    tempMap.put("comment",comment.getFullMessage());//审批信息
                    tempMap.put("commentDate",comment.getTime());
                    /*审批用户信息*/
                    tempMap.put("approveUser",approveUser);
                    pos++;
                    resultList.add(tempMap);
                }
                commentLength=commentList.size();
            }


            /*else{
                tempMap.put("nodename",nodename);
                tempMap.put("comment","");
                tempMap.put("commentDate","");
                *//*审批用户信息*//*
                tempMap.put("approveUser",approveUser);
                resultList.add(tempMap);
            }*/



        }
        System.out.print(resultList);
        //对list集合进行排序
        Collections.sort(resultList, new Comparator<Map>(){
            @Override
            public int compare(Map o1, Map o2) {
                Long diff = ((Date)(o1.get("commentDate"))).getTime() - ((Date)(o2.get("commentDate"))).getTime();
                return new BigDecimal(diff).compareTo(new BigDecimal(0)); //返回的结果是int类型，-1表示小于，0是等于，1是大于。
            }
        });
        return resultList;
    }

    /**
     * 查看审核意见
     * @param request
     * @return
     */
    @RequestMapping("listCommentCX")
    @ResponseBody
    public Object findProcessTaskAndCommentCX(HttpServletRequest request){
        /*获取流程实例id*/
        String processInstanceId = request.getParameter("processInstanceId");
        /*查看历史task实例*/
        List<HistoricTaskInstance> hiTaskList = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId).list();
        /*通过流程实例id查询审批单*/
        Blgsh blgsh = new Blgsh();
        blgsh.setBlgProcessinstanceid(processInstanceId);
        blgsh = this.blgshService.selectByCondition(blgsh);
        /*将审批单中记录的审批用户转为数组*/
        String users = blgsh.getBlgApvusers();
        String[] userArr;
        if(users!=null && !"".equals(users)){
            userArr=users.split(",");
        }else{
            userArr = new String[0];
        }

        /*结果集*/
        List<Map<String,Object>> resultList = Lists.newArrayList();
        int commentLength=0;//记载上次审批意见的数量
        /*对历史任务进行遍历*/
        for (int i=0;i<hiTaskList.size();i++){
            HistoricTaskInstance historicTaskInstance= hiTaskList.get(i);//历史人物实例
            Map<String,Object> tempMap = Maps.newHashMap();
            List<HistoricIdentityLink> idenList = historyService.
                    getHistoricIdentityLinksForTask(historicTaskInstance.getId());//历史任务对应的身份认证信息

            /*根据历史流程id查询历史评论信息*/
            List<Comment> commentList = taskService.getTaskComments(historicTaskInstance.getId());
            /*查询节点名称*/
            String nodename = this.roleInfoService.selectById(idenList.get(0).getGroupId()).getDutyname();
            String approveUser="";

            /*对评论信息进行遍历，由于每个可能有多个用户参与的多条评论，所以要合理遍历评论信息*/
            if(commentList!=null && commentList.size()!=0){
                int pos =0;

                for(Comment comment:commentList){
                    if((i*commentLength)<userArr.length){
                        approveUser=userArr[i*commentLength+pos];
                    }else{
                        approveUser="";
                    }
                    tempMap.put("nodename",nodename);//节点名称
                    tempMap.put("comment",comment.getFullMessage());//审批信息
                    tempMap.put("commentDate",comment.getTime());
                    /*审批用户信息*/
                    tempMap.put("approveUser",approveUser);
                    pos++;
                    resultList.add(tempMap);
                }
                commentLength = commentList.size();
            }
        }
        return resultList;
    }


    /**
     * 查看流程
     * @param processInstanceId
     * @return
     */
    @RequestMapping("getApproveProcess")
    @ResponseBody
    public  Object getApproveProcess(String processInstanceId){

        List<Map<String,Object>> resultList = Lists.newArrayList();
        /*根据实例id查询流程实例*/
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)repositoryService.
                getProcessDefinition(processInstance.getProcessDefinitionId());
        ExecutionEntity executionEntity = (ExecutionEntity)processInstance;
        //当前实例的执行到哪个节点
        String activitiId = executionEntity.getActivityId();
        String id="";
        //获得当前任务的所有节点
        List<ActivityImpl> activitiList = processDefinitionEntity.getActivities();
        for(ActivityImpl activityImpl:activitiList){
            id = activityImpl.getId();
            /*当节点为用户任务节点时*/
            if("userTask".equals(activityImpl.getProperty("type"))){
                Map<String,Object> tempMap = Maps.newHashMap();//map集
                /*获得任务定义，并通过任务定义获得角色id*/
                TaskDefinition taskDefinition = ((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition();
                String groupid = taskDefinition.getCandidateGroupIdExpressions().iterator().next().getExpressionText();

                /*通过角色id获得角色名，加入map中*/
                String nodename = this.roleInfoService.selectById(groupid).getDutyname();
                tempMap.put("nodename",nodename);

                /*如果当前审批节点与遍历中的该节点id相同，则flag设为1*/
                if(activitiId.equals(id)){
                    tempMap.put("flag",1);
                }else{
                    tempMap.put("flag",0);
                }
                resultList.add(tempMap);
            }
        }
        return resultList;
    }
    /**
     * 查看已审核流程
     * @param processInstanceId
     * @return
     */
    @RequestMapping("getHisProcess")
    @ResponseBody
    public  Object getHisProcess(String processInstanceId){

        List<Map<String,Object>> resultList = Lists.newArrayList();
        /*根据历史实例id查询流程实例*/
        HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity)repositoryService.
                getProcessDefinition(historicProcessInstance.getProcessDefinitionId());
        //获得当前任务的所有节点
        List<ActivityImpl> activitiList = processDefinitionEntity.getActivities();
        for(ActivityImpl activityImpl:activitiList){
            /*当节点为用户任务节点时*/
            if("userTask".equals(activityImpl.getProperty("type"))){
                Map<String,Object> tempMap = Maps.newHashMap();//map集
                /*获得任务定义，并通过任务定义获得角色id*/
                TaskDefinition taskDefinition = ((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition();
                String groupid = taskDefinition.getCandidateGroupIdExpressions().iterator().next().getExpressionText();

                /*通过角色id获得角色名，加入map中*/
                String nodename = this.roleInfoService.selectById(groupid).getDutyname();
                tempMap.put("nodename",nodename);
                resultList.add(tempMap);
            }
        }
        return resultList;
    }


    /**
     * 下载待审核单据
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("downloadDsh")
    public void downloadsEntity(HttpServletRequest request,HttpServletResponse response) throws Exception{

        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");//userInfoService.getUserByMap(conditionMap);
        //String applyUserSsq=userInfo.getSsq();//所属区

        String applyUserJd = request.getParameter("applyUserJd");
        String applyType = request.getParameter("applyType");
        String all = request.getParameter("all");//全部审核还是个人审核
        String applyUserSsq = request.getParameter("applyUserSsq");
        if(applyUserJd==null){
            applyUserJd="";
        }
        if(applyType==null){
            applyType="";
        }
        if(all==null){
            all="";
        }

        /*当用户为非市区用户时，区查询设为当前审核用户所在区*/
        String perUserSsq=userInfo.getSsq();
        if(perUserSsq.indexOf("0")==-1){
            applyUserSsq=userInfo.getSsq();
        }

        /*System.out.println("---applyUserJd:"+applyUserJd+"---applyType:"+applyType
                +"--all:"+all+"--applyUserSsq:"+applyUserSsq);*/

        List<Task> allTaskList = new ArrayList<Task>();

        if(all.equals("all")){
            allTaskList = taskService.createTaskQuery()
                    .processVariableValueLike("applyUserSsq","%"+applyUserSsq+"%")
                    .processVariableValueLike("applyUserJd","%"+applyUserJd+"%")
                    .processVariableValueLike("aplb","cs")
                    .processVariableValueLike("applyType","%"+applyType+"%")
                    .active().list();
        }else{
            /*查询条件下所有的个人任务列表，分页查询*/
            allTaskList = taskService.createTaskQuery()
                    .taskCandidateUser(userInfo.getUserid())
                    .processVariableValueLike("applyUserSsq","%"+applyUserSsq+"%")
                    .processVariableValueLike("applyUserJd","%"+applyUserJd+"%")
                    .processVariableValueLike("applyType","%"+applyType+"%")
                    .processVariableValueLike("aplb","cs")
                    .active().list();
        }


        List<Map<String,Object>> resultList = Lists.newArrayList();//返回的结果集
        /*遍历所有task点*/
        for(Task task:allTaskList){
            String currTaskGroupId = taskService.getIdentityLinksForTask(task.getId()).get(0).getGroupId();
            Map<String,Object> dataMap = Maps.newHashMap();
            dataMap.put("processInstanceId",task.getProcessInstanceId());
            findMapResult(dataMap);//根据task信息返回map
            dataMap.put("taskId",task.getId());
            dataMap.put("nodename",this.roleInfoService.selectById(currTaskGroupId).getDutyname());
            resultList.add(dataMap);
        }

        String currPath=request.getSession().getServletContext().getRealPath("/");
        String fileName="待审核单.xlsx";
        String filepath=currPath+fileName;
        String[] heads = new String[]{"申请类型","申请编号","申请日期","申请人","身份证号","所属县区","所属街道"};
        String[] keys = new String[]{"apptypename","sqbh","applydate","username","sfzh","ssq","ssjd"};
        FileManagerUtil.generatorExcelFile(resultList,keys,heads,filepath);
        FileManagerUtil.downloadToLocal(filepath,fileName,response,request);
    }
}