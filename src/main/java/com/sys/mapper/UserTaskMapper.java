package com.sys.mapper;

import java.util.List;
import java.util.Map;

import com.sys.pojo.UserTask;

public interface UserTaskMapper {
	/**
	 * 插入单条实体
	 * @param userTask
	 * @return
	 */
	int insert(UserTask userTask);
	/**
	 * 批量插入用户任务信息
	 * @param taskList
	 * @return
	 */
	int addTaskList(List<UserTask> taskList);
	/**
	 * 条件删除用户任务
	 * @param map
	 * @return
	 */
	int deleteTask(Map map);
	/**
	 * 多条件查询用户信息集合
	 * @param map
	 * @return
	 */
	List<UserTask> getTaskList(Map map);
}
