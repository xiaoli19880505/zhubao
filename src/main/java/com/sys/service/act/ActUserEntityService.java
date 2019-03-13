/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sys.service.act;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sys.common.act.ActConvertUtil;
import com.sys.mapper.RoleInfoMapper;
import com.sys.mapper.UserInfoMapper;
import com.sys.pojo.RoleInfo;
import com.sys.pojo.UserInfo;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;


/**
 * Activiti User Entity Service
 * @author wangli
 * @version 2018-10-17
 */
@Service
public class ActUserEntityService extends UserEntityManager {


	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private RoleInfoMapper roleInfoMapper;

	public User createNewUser(String userId) {
		return new UserEntity(userId);
	}

	public void insertUser(User user) {
	//	getDbSqlSession().insert((PersistentObject) user);
		throw new RuntimeException("not implement method.");
	}

	public void updateUser(UserEntity updatedUser) {
//		CommandContext commandContext = Context.getCommandContext();
//		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
//		dbSqlSession.update(updatedUser);
		throw new RuntimeException("not implement method.");
	}

	public UserEntity findUserById(String userId) {
//		return (UserEntity) getDbSqlSession().selectOne("selectUserById", userId);
		//getSystemService().getUserByLoginName(userId)
		return ActConvertUtil.toActivitiUser(this.userInfoMapper.selectById(userId));
	}

	public void deleteUser(String userId) {
//		UserEntity user = findUserById(userId);
//		if (user != null) {
//			List<IdentityInfoEntity> identityInfos = getDbSqlSession().selectList("selectIdentityInfoByUserId", userId);
//			for (IdentityInfoEntity identityInfo : identityInfos) {
//				getIdentityInfoManager().deleteIdentityInfo(identityInfo);
//			}
//			getDbSqlSession().delete("deleteMembershipsByUserId", userId);
//			user.delete();
//		}
		User user = findUserById(userId);
		if (user != null) {
			//getSystemService().deleteUser(new com.thinkgem.jeesite.modules.sys.entity.User(user.getId()));
			//UserInfo userInfo = this.userInfoMapper.selectById(userId);
			userInfoMapper.delete(userId);
		}
	}

	public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
//		return getDbSqlSession().selectList("selectUserByQueryCriteria", query, page);
		throw new RuntimeException("not implement method.");
	}

	public long findUserCountByQueryCriteria(UserQueryImpl query) {
//		return (Long) getDbSqlSession().selectOne("selectUserCountByQueryCriteria", query);
		throw new RuntimeException("not implement method.");
	}

	public List<Group> findGroupsByUser(String userId) {
//		return getDbSqlSession().selectList("selectGroupsByUserId", userId);
		List<Group> list = Lists.newArrayList();
		/*获得用户信息*/
		Map<String,Object> conditionMap = new HashMap<String,Object>();
		/*根据用户id查询对应的角色列表*/
		conditionMap.put("userid",userId);
		List<RoleInfo> roleList = roleInfoMapper.getRoleListByMap(conditionMap);
		//getSystemService().findRole(new Role(new com.thinkgem.jeesite.modules.sys.entity.User(null, userId)))
		for (RoleInfo role : roleList){
			list.add(ActConvertUtil.toActivitiGroup(role));
		}
		return list;
	}

	public UserQuery createNewUserQuery() {
//		return new UserQueryImpl(Context.getProcessEngineConfiguration().getCommandExecutorTxRequired());
		throw new RuntimeException("not implement method.");
	}

	public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
//		Map<String, String> parameters = new HashMap<String, String>();
//		parameters.put("userId", userId);
//		parameters.put("key", key);
//		return (IdentityInfoEntity) getDbSqlSession().selectOne("selectIdentityInfoByUserIdAndKey", parameters);
		throw new RuntimeException("not implement method.");
	}

	public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
//		Map<String, String> parameters = new HashMap<String, String>();
//		parameters.put("userId", userId);
//		parameters.put("type", type);
//		return (List) getDbSqlSession().getSqlSession().selectList("selectIdentityInfoKeysByUserIdAndType", parameters);
		throw new RuntimeException("not implement method.");
	}

	public Boolean checkPassword(String userId, String password) {
//		User user = findUserById(userId);
//		if ((user != null) && (password != null) && (password.equals(user.getPassword()))) {
//			return true;
//		}
//		return false;
		throw new RuntimeException("not implement method.");
	}

	public List<User> findPotentialStarterUsers(String proceDefId) {
//		Map<String, String> parameters = new HashMap<String, String>();
//		parameters.put("procDefId", proceDefId);
//		return (List<User>) getDbSqlSession().selectOne("selectUserByQueryCriteria", parameters);
		throw new RuntimeException("not implement method.");

	}

	public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
//		return getDbSqlSession().selectListWithRawParameter("selectUserByNativeQuery", parameterMap, firstResult, maxResults);
		throw new RuntimeException("not implement method.");
	}

	public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
//		return (Long) getDbSqlSession().selectOne("selectUserCountByNativeQuery", parameterMap);
		throw new RuntimeException("not implement method.");
	}
	
}
