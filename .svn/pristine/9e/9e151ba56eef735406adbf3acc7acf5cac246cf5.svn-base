package com.sys.service;

import com.google.common.collect.Maps;
import com.sys.common.CommonUtils;
import com.sys.common.PropertiesUtil;
import com.sys.common.act.ActivitiUtil;
import com.sys.common.act.CharUtil;
import com.sys.pojo.UserTask;
import com.sys.pojo.apply.Approve;
import net.sf.json.JSONObject;
import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.SimpleContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Desc:流程管理service
 * @Author:wangli
 * @CreateTime:20:10 2018/10/15
 */
@Service
public class FlowService {

    private static Logger logger = Logger.getLogger(FlowService.class);

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


    /**
     * 创建流程实例，并且经流程实例id设置到审批单实体中
     * @param applyType 申请的业务类别
     * @param approve 审批单实体
     */
    public void addProcessInstance(String applyType,Approve approve,Map<String,Object> conditionMap){
        String flowname = PropertiesUtil.getFlowProperties("flow"+applyType);//获取流程定义名称的关键字
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionName(flowname).singleResult();//通过关键字查询流程定义
        System.out.println("processDefinition:"+processDefinition);
        ProcessInstance processInstance = this.runtimeService.startProcessInstanceById(processDefinition.getId(),conditionMap);//启动流程，获得流程实例
        approve.setProcessinstanceid(processInstance.getProcessInstanceId());//将流程实例id设置于审批单
    }

    /**
     * 创建流程实例，并且经流程实例id设置到审批单实体中
     * @param flowname 申请的业务类别
     * @param conditionMap 变量对象
     */
    public String addProcessInstanceNew(String flowname,Map<String,Object> conditionMap){
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionName(flowname).singleResult();//通过关键字查询流程定义
        System.out.println("processDefinition:"+processDefinition);
        ProcessInstance processInstance = this.runtimeService.startProcessInstanceById(processDefinition.getId(),conditionMap);//启动流程，获得流程实例
        return processInstance.getProcessInstanceId();//将流程实例id返回
    }


    public String addFlowDeploment(String flowname, String flowno, String nodeJson){
        String result = "";
        Map<String, Object> map = new HashMap<String, Object>();
        /* 查询是否有编码相同的流程存在 */
        long flowCount = repositoryService.createProcessDefinitionQuery().processDefinitionKey(flowno).count();
        if(flowCount > 0)
        {
            result = "存在相同流程编码，不得添加";
            //map.put("result", result);
            return result;
        }
        /* 查询是否有命名相同的流程存在 */
        flowCount = repositoryService.createProcessDefinitionQuery().processDefinitionName(flowname).count();
        if(flowCount > 0)
        {
            result = "存在相同流程编码，不得添加";
            //map.put("result", result);
            return result;
        }

        addPrivateFlow(flowname, flowno, nodeJson);

        result = "添加流程成功";
        return result;
    }

    /**
     * 更新流程部署
     * @param flowname
     * @param flowno
     * @param nodeJson
     * @return
     */
    public String updateFlow(String flowname, String flowno, String nodeJson)
    {
        String result = "";
        Map<String, Object> map = new HashMap<String, Object>();
        /* 在删除之前，先判断该流程下是否有流程正在运行的实例存在 */
        long count = runtimeService.createProcessInstanceQuery().processDefinitionKey(flowno).count();
        if(count > 0) result = "流程下存在正在运行的流程，不得更新";
        else
        {
            //this.repositoryService.getBpmnMod;
            /* 删除原先部署的流程 */
            Deployment deploy = repositoryService.createDeploymentQuery().processDefinitionKey(flowno).singleResult();
            this.repositoryService.deleteDeployment(deploy.getId(), true);
            map.put("flowno", flowno);
            this.userTaskService.deleteTask(map);
            /* 添加新的部署 */
            addPrivateFlow(flowname, flowno, nodeJson);
            result = "更新流程成功";
        }
        return result;
    }

    /**
     * 删除流程部署
     * @param key
     * @return
     */
    public String deleteFlow(String key)
    {
        String result = "";
        Map<String, Object> map = new HashMap<String, Object>();
        /* 在删除之前，先判断该流程下是否有流程正在运行的实例存在 */
        long count = runtimeService.createProcessInstanceQuery().processDefinitionKey(key).count();
        if(count > 0) result = "流程下存在正在运行的流程，不得删除";
        else
        {
            Deployment deploy = repositoryService.createDeploymentQuery().processDefinitionKey(key).singleResult();
            this.repositoryService.deleteDeployment(deploy.getId(), true);
            map.put("flowno", key);
            this.userTaskService.deleteTask(map);
            result = "删除成功";
        }
        return result;
    }



    /**
     * 部署流程
     *
     * @param flowname
     * @param flowno
     * @param nodeJson
     */
    private void addPrivateFlow(String flowname, String flowno, String nodeJson)
    {
        /* 建立模型对象 */
        BpmnModel model = new BpmnModel();
        Process process = new Process();
        model.addProcess(process);
        final String PROCESSID = flowno; //流程id
        final String PROCESSNAME = flowname; //流程名
        process.setId(PROCESSID);
        process.setName(PROCESSNAME);
        // process.addFlowElement(ActivitiUtil.createStartEvent());

        /* 解析前端传来的节点json数据，并且遍历 */
        JSONObject jsonObject = JSONObject.fromObject(nodeJson);
        Iterator iterKey = jsonObject.keys();
        String key = ""; //临时存储json key
        String lastValue = ""; //遍历时，记录上次的用户id
        String laseGatewayname = null; //上一次遍记录的网管名称
        JSONObject value;
        int pos = 0;
        List<UserTask> taskList = new ArrayList<UserTask>(); //存储用户任务的list
        /* 创建开始节点 */
        process.addFlowElement(ActivitiUtil.createStartEvent());
        /* 创建结束节点 */
        process.addFlowElement(ActivitiUtil.createEndEvent());
        /* 遍历json数据 */
        while (iterKey.hasNext())
        {
            System.out.println("---pos:" + pos);
            key = (String) iterKey.next();
            value = jsonObject.getJSONObject(key);
            /* 设置任务对象，并添加到list中 */
            UserTask tempTask = new UserTask();
            tempTask.setId(CommonUtils.getUUIDWith_());
            tempTask.setFlowno(flowno);
            tempTask.setTaskname(value.getString("nodename"));
            tempTask.setTaskno(value.getString("nodeno"));
            tempTask.setRoleno(value.getString("node_role_id"));
            tempTask.setDesc(value.getString("desc"));
            taskList.add(tempTask);
            /* 添加角色任务 */
            process.addFlowElement(ActivitiUtil.createUserTask(value.getString("nodeno"), value.getString("nodename"),
                    value.getString("node_role_id")));
            /* 添加流程线对应关系，如果是第一次进来，则第一个用户任务与开始节点连线，否则则与上一次的用户节点相连 */
            if(pos == 0)
            {
                // System.out.println("---连线开始:"+lastValue+"--laseFlowname:"+laseFlowname+"----"+value.getString("nodeno"));
                process.addFlowElement(ActivitiUtil.createSequenceFlow("startEvent", value.getString("nodeno"), "", ""));
                String gatwayname = CharUtil.getRandomString() + new Date().getTime() + pos;
                process.addFlowElement(ActivitiUtil.createExclusiveGateway(gatwayname));
                process.addFlowElement(ActivitiUtil.createSequenceFlow(value.getString("nodeno"), gatwayname, "", ""));
                process.addFlowElement(ActivitiUtil.createSequenceFlow(gatwayname, "endEvent", "不通过", "${msg=='不通过'}"));
                laseGatewayname = gatwayname;
            }
            else
            {
                System.out.println("---连线中间:" + lastValue + "--laseGatewayname:" + laseGatewayname + "----"
                        + value.getString("nodeno"));
                if(laseGatewayname != null)
                    process.addFlowElement(ActivitiUtil.createSequenceFlow(laseGatewayname, value.getString("nodeno"),
                            "通过", "${msg=='通过'}"));
                String gatwayname = CharUtil.getRandomString() + new Date().getTime() + pos;
                process.addFlowElement(ActivitiUtil.createExclusiveGateway(gatwayname));
                process.addFlowElement(ActivitiUtil.createSequenceFlow(value.getString("nodeno"), gatwayname, "", ""));
                process.addFlowElement(ActivitiUtil.createSequenceFlow(gatwayname, "endEvent", "不通过", "${msg=='不通过'}"));
                laseGatewayname = gatwayname;
            }
            pos++;
            lastValue = value.getString("nodeno");
        }

        /* 结束节点与最后一个用户任务id相连 */
        process.addFlowElement(ActivitiUtil.createSequenceFlow(laseGatewayname, "endEvent", "通过", "${msg=='通过'}"));
        /* 执行模型信息 */
        new BpmnAutoLayout(model).execute();
        /* 部署流程 */
        Deployment deployment = repositoryService.createDeployment().addBpmnModel(PROCESSID + ".bpmn", model)
                .name(PROCESSID + "_deployment").deploy();

        /* 添加用户任务对象 */
        userTaskService.addTaskList(taskList);
    }

    public List<TaskDefinition> getTaskDefinitionList(String procInstId){
       /* ProcessEngine processEngine = WorkFlowService.getProcessEngine(sessionId, isNeedTransaction);
        HistoryService historyService = processEngine.getHistoryService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();*/
        List<TaskDefinition> taskDefinitionList = new ArrayList<TaskDefinition>();
        //流程标示
        String processDefinitionId = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInstId).singleResult().getProcessDefinitionId();
        ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        //ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefinitionId);
        //执行实例
        ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery().processInstanceId(procInstId).singleResult();
        //当前实例的执行到哪个节点
        if(execution!=null){
            String activitiId = execution.getActivityId();
            //获得当前任务的所有节点
            List<ActivityImpl> activitiList = pde.getActivities();
            String id = null;
            for(ActivityImpl activityImpl:activitiList){
                id = activityImpl.getId();
                if(activitiId.equals(id)){
                    logger.debug("当前任务："+activityImpl.getProperty("name"));
                    taskDefinitionList = nextTaskDefinition(activityImpl, activityImpl.getId());
                }
            }
        }

        return taskDefinitionList;
    }

    private List<TaskDefinition> nextTaskDefinition(ActivityImpl activityImpl, String activityId){
        List<TaskDefinition> taskDefinitionList = new ArrayList<TaskDefinition>();//所有的任务实例
        List<TaskDefinition> nextTaskDefinition = new ArrayList<TaskDefinition>();//逐个获取的任务实例
        TaskDefinition taskDefinition = null;
        if("userTask".equals(activityImpl.getProperty("type")) && !activityId.equals(activityImpl.getId())){
            taskDefinition = ((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition();
            taskDefinitionList.add(taskDefinition);
        }else{
            List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
            List<PvmTransition> outTransitionsTemp = null;
            for(PvmTransition tr:outTransitions){
                PvmActivity ac = tr.getDestination(); //获取线路的终点节点
                //如果是互斥网关或者是并行网关
                if("exclusiveGateway".equals(ac.getProperty("type"))||"parallelGateway".equals(ac.getProperty("type"))){
                    outTransitionsTemp = ac.getOutgoingTransitions();
                    if(outTransitionsTemp.size() == 1){
                        nextTaskDefinition = nextTaskDefinition((ActivityImpl)outTransitionsTemp.get(0).getDestination(), activityId);
                        taskDefinitionList.addAll(nextTaskDefinition);
                    }else if(outTransitionsTemp.size() > 1){
                        for(PvmTransition tr1 : outTransitionsTemp){
                            nextTaskDefinition = nextTaskDefinition((ActivityImpl)tr1.getDestination(), activityId);
                            taskDefinitionList.addAll(nextTaskDefinition);
                        }
                    }
                }else if("userTask".equals(ac.getProperty("type"))){
                    taskDefinition = ((UserTaskActivityBehavior)((ActivityImpl)ac).getActivityBehavior()).getTaskDefinition();
                    taskDefinitionList.add(taskDefinition);
                }else{
                    logger.debug((String) ac.getProperty("type"));
                }
            }
        }
        return taskDefinitionList;
    }

    @SuppressWarnings("unused")
    private List<TaskDefinition> nextTaskDefinition(ActivityImpl activityImpl, String activityId, String elString){
        List<TaskDefinition> taskDefinitionList = new ArrayList<TaskDefinition>();//所有的任务实例
        List<TaskDefinition> nextTaskDefinition = new ArrayList<TaskDefinition>();//逐个获取的任务实例
        TaskDefinition taskDefinition = null;
        if("userTask".equals(activityImpl.getProperty("type")) && !activityId.equals(activityImpl.getId())){
            taskDefinition = ((UserTaskActivityBehavior)activityImpl.getActivityBehavior()).getTaskDefinition();
            taskDefinitionList.add(taskDefinition);
        }else{
            List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
            List<PvmTransition> outTransitionsTemp = null;
            for(PvmTransition tr:outTransitions){
                PvmActivity ac = tr.getDestination(); //获取线路的终点节点
                //如果是互斥网关或者是并行网关
                if("exclusiveGateway".equals(ac.getProperty("type"))||"parallelGateway".equals(ac.getProperty("type"))){
                    outTransitionsTemp = ac.getOutgoingTransitions();
                    if(outTransitionsTemp.size() == 1){
                        nextTaskDefinition = nextTaskDefinition((ActivityImpl)outTransitionsTemp.get(0).getDestination(), activityId,elString);
                        taskDefinitionList.addAll(nextTaskDefinition);
                    }else if(outTransitionsTemp.size() > 1){
                        for(PvmTransition tr1 : outTransitionsTemp){
                            Object s = tr1.getProperty("conditionText");
                            if(elString.equals(StringUtils.trim(s.toString()))){
                                nextTaskDefinition = nextTaskDefinition((ActivityImpl)tr1.getDestination(), activityId,elString);
                                taskDefinitionList.addAll(nextTaskDefinition);
                            }
                        }
                    }
                }else if("userTask".equals(ac.getProperty("type"))){
                    taskDefinition = ((UserTaskActivityBehavior)((ActivityImpl)ac).getActivityBehavior()).getTaskDefinition();
                    taskDefinitionList.add(taskDefinition);
                }else{
                    logger.debug((String) ac.getProperty("type"));
                }
            }
        }
        return taskDefinitionList;
    }




    /**
     * 获取下一个用户任务信息
     * @param taskId
     * @return
     * @throws Exception
    public TaskDefinition getNextTaskInfo(String taskId) throws Exception {

        ProcessDefinitionEntity processDefinitionEntity = null;

        String id = null;

        TaskDefinition task = null;

        //获取流程实例Id信息
        String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();

        //获取流程发布Id信息
        String definitionId = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getProcessDefinitionId();

        processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(definitionId);

        ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        //当前流程节点Id信息
        String activitiId = execution.getActivityId();

        //获取流程所有节点信息
        List<ActivityImpl> activitiList = processDefinitionEntity.getActivities();

        //遍历所有节点信息
        for(ActivityImpl activityImpl : activitiList){
            id = activityImpl.getId();
            if (activitiId.equals(id)) {
                //获取下一个节点信息
                task = nextTaskDefinition(activityImpl, activityImpl.getId(), null, processInstanceId);
                break;
            }
        }
        return task;
    }

    *//**
     * 下一个任务节点信息
     * @param activityImpl
     * @param activityId
     * @param elString
     * @param processInstanceId
     * @return
     *//*
    private TaskDefinition nextTaskDefinition(ActivityImpl activityImpl, String activityId, String elString, String processInstanceId){

        PvmActivity ac = null;

        Object s = null;

        // 如果遍历节点为用户任务并且节点不是当前节点信息
        if ("userTask".equals(activityImpl.getProperty("type")) && !activityId.equals(activityImpl.getId())) {
            // 获取该节点下一个节点信息
            TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activityImpl.getActivityBehavior())
                    .getTaskDefinition();
            return taskDefinition;
        } else {
            // 获取节点所有流向线路信息
            List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
            List<PvmTransition> outTransitionsTemp = null;
            for (PvmTransition tr : outTransitions) {
                ac = tr.getDestination(); // 获取线路的终点节点
                // 如果流向线路为排他网关
                if ("exclusiveGateway".equals(ac.getProperty("type"))) {
                    outTransitionsTemp = ac.getOutgoingTransitions();

                    // 如果网关路线判断条件为空信息
                    if (StringUtils.isEmpty(elString)) {
                        // 获取流程启动时设置的网关判断条件信息
                        elString = getGatewayCondition(ac.getId(), processInstanceId);
                    }

                    // 如果排他网关只有一条线路信息
                    if (outTransitionsTemp.size() == 1) {
                        return nextTaskDefinition((ActivityImpl) outTransitionsTemp.get(0).getDestination(), activityId,
                                elString, processInstanceId);
                    } else if (outTransitionsTemp.size() > 1) { // 如果排他网关有多条线路信息
                        for (PvmTransition tr1 : outTransitionsTemp) {
                            s = tr1.getProperty("conditionText"); // 获取排他网关线路判断条件信息
                            // 判断el表达式是否成立
                            if (isCondition(ac.getId(), StringUtils.trim(s.toString()), elString)) {
                                return nextTaskDefinition((ActivityImpl) tr1.getDestination(), activityId, elString,
                                        processInstanceId);
                            }
                        }
                    }
                } else if ("userTask".equals(ac.getProperty("type"))) {
                    return ((UserTaskActivityBehavior) ((ActivityImpl) ac).getActivityBehavior()).getTaskDefinition();
                } else {
                }
            }
            return null;
        }
    }

    *//**
     * 查询流程启动时设置排他网关判断条件信息
     * @param gatewayId
     * @param processInstanceId
     * @return
     *//*
    public String getGatewayCondition(String gatewayId, String processInstanceId) {
        Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).singleResult();
        Object object= runtimeService.getVariable(execution.getId(), gatewayId);
        return object==null? "":object.toString();
    }

    *//**
     * 根据key和value判断el表达式是否通过信息
     * @param key
     * @param el
     * @param value
     * @return
     *//*
    public boolean isCondition(String key, String el, String value) {
        ExpressionFactory factory = new ExpressionFactoryImpl();
        SimpleContext context = new SimpleContext();
        context.setVariable(key, factory.createValueExpression(value, String.class));
        ValueExpression e = factory.createValueExpression(context, el, boolean.class);
        return (Boolean) e.getValue(context);
    }*/

}