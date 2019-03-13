package com.sys.service;

import java.util.List;
import java.util.Map;

import com.sys.common.BatchUtil;
import com.sys.mapper.UserTaskMapper;
import com.sys.pojo.UserTask;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户任务管理接口
 * @author wangli
 *
 */
@Service("userTaskService")
public class UserTaskService {

	@Resource
	private UserTaskMapper userTaskDao;

	@Resource
	private BatchUtil batchUtil;

	/**
	 * 添加任务
	 * @param taskList
	 * @return
	 */
	public int addTaskList(List<UserTask> taskList) {
		// TODO Auto-generated method stub
		batchUtil.save(taskList,"UserTaskMapper");
		return 1;
	}

	/**
	 * 条件删除用户任务
	 * @param map
	 * @return
	 */
	public int deleteTask(Map map) {
		// TODO Auto-generated method stub
		return userTaskDao.deleteTask(map);
	}

	/**
	 * 条件查询用户任务
	 * @param map
	 * @return
	 */
	public List<UserTask> getTaskList(Map map) {
		// TODO Auto-generated method stub
		return userTaskDao.getTaskList(map);
	}

}
