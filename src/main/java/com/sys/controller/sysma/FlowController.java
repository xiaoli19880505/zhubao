package com.sys.controller.sysma;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.sys.common.CommonUtils;
import com.sys.service.FlowService;
import com.sys.service.UserTaskService;
import net.sf.json.JSONObject;

import com.sys.common.act.ActivitiUtil;
import com.sys.common.act.CharUtil;
import com.sys.common.dataconver.ListToJsonConvertor;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.pojo.PageInfo;
import com.sys.pojo.UserTask;

@Controller
@RequestMapping("/flow")
public class FlowController
{

	@Resource private RepositoryService repositoryService;

	@Resource private RuntimeService runtimeService;

	@Resource private TaskService taskService;

	@Resource private HistoryService historyService;

	@Resource private UserTaskService userTaskService;

	@Resource
	private FlowService flowService;
	/**
	 * 分页查询流程对象
	 * 
	 * @param page
	 * @param rows
	 * @param flowname
	 * @return
	 */
	@RequestMapping("/flowPage")
	@ResponseBody
	public Object flowPage(String page, String rows, String flowname)
	{

		if(flowname == null) flowname = "";

		PageInfo flowPage = new PageInfo();
		Integer pageSize = Integer.parseInt(rows);
		flowPage.setPageIndex(pageSize);
		String pageIndex = page;
		if(pageIndex == null || pageIndex == "")
		{
			pageIndex = "1";
		}
		flowPage.setPageIndex((Integer.parseInt(pageIndex) - 1) * pageSize);

		long flowCount = repositoryService.createProcessDefinitionQuery()
				.processDefinitionNameLike("%" + flowname + "%").count();

		List<ProcessDefinition> flowList = repositoryService.createProcessDefinitionQuery()
				.processDefinitionNameLike("%" + flowname + "%").list();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (ProcessDefinition tempPorcess : flowList)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", tempPorcess.getName());
			map.put("key", tempPorcess.getKey());
			map.put("deploymentid", tempPorcess.getDeploymentId());
			Date deploytime = repositoryService.createDeploymentQuery().deploymentId(tempPorcess.getDeploymentId())
					.singleResult().getDeploymentTime();
			map.put("deploytime", deploytime);
			list.add(map);
		}
		//JSONArray jsonArray = ListToJsonConvertor.convertorList(list, new String[]{"identityLinks","processDefinition"});
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", ListToJsonConvertor.convertorList(list, new String[]{}));
		map.put("total", flowCount);
		System.out.println("time:" + map.toString());
		return map;
	}

	/**
	 * 添加发布的流程信息
	 * @param flowname
	 * @param flowno
	 * @param nodeJson
	 * @return
	 */
	@RequestMapping("/addFlow")
	@ResponseBody
	public Object addFlow(String flowname, String flowno, String nodeJson)
	{
		//map.put("result", result);
		return flowService.addFlowDeploment(flowname,flowno,nodeJson);
	}

	/**
	 * 更新流程基本信息
	 * @return
	 */
	@RequestMapping("/updateFlowBaseInfo")
	@ResponseBody
	public Object updateFlowBaseInfo()
	{

		ProcessDefinition processDefinition = repositoryService.getProcessDefinition("");

		//this.repositoryService.

		return "";
	}

	/**
	 * 删除指定的任务
	 * 
	 * @param taskId
	 * @return
	 */
	public Object deleteTaskById(String taskId)
	{
		taskService.deleteTask(taskId);
		return "";
	}
	/**
	 * 跳转到流程新增界面
	 * 
	 * @return
	 */
	@RequestMapping("toAddFlow")
	public String toFlowAdd()
	{
		return "SystemMa/flowMa/flowAdd";
	}

	/**
	 * 跳转到流程管理界面
	 *
	 * @return
	 */
	@RequestMapping("toFlowMa")
	public String toFlowMana()
	{
		return "SystemMa/flowMa";
	}

	/**
	 * 跳转到流程编辑界面
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("toEditFlow")
	public String toFlowEdit(String key, String name, HttpServletRequest request) throws UnsupportedEncodingException
	{
		request.setAttribute("key", key);
		request.setAttribute("name", name);
		return "SystemMa/flowMa/flowEdit";
	}

	/**
	 * 删除流程
	 * 
	 * @param key
	 * @return
	 */
	@RequestMapping("deleteFlow")
	@ResponseBody
	public Object deleteFlow(String key)
	{
		String result = this.flowService.deleteFlow(key);
		return result;
	}

	/**
	 * 根据流程key查询其定义的所有用户信息
	 * 
	 * @param key
	 * @return
	 */
	@RequestMapping("getTaskByFlowKey")
	@ResponseBody
	public Object getTasksByFlowKey(String key)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flowno", key);
		List<UserTask> taskList = this.userTaskService.getTaskList(map);
		return taskList;
	}

	/**
	 * 更新流程
	 * 
	 * @param flowname
	 *            流程名
	 * @param flowno
	 *            流程编码
	 * @param nodeJson
	 *            流程节点json
	 * @return
	 */
	@RequestMapping("updateFlow")
	@ResponseBody
	public Object updateFlow(String flowname, String flowno, String nodeJson)
	{
		String result = this.flowService.updateFlow(flowname,flowno,nodeJson);
		return result;
	}


}
