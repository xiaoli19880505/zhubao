package com.sys.mapper;

import com.sys.pojo.UserRoleInfo;
import java.util.List;
import java.util.Map;

public interface UserRoleInfoMapper extends IMapper<UserRoleInfo> {
    int insert(UserRoleInfo pojo);

    int update(UserRoleInfo pojo);

    int delete(String id);

    List<UserRoleInfo> select(UserRoleInfo pojo);


    /**
     * 用户查询所属角色
     * @param userid
     * @return
     */
    List<UserRoleInfo> findRoleByUid(String userid);

    UserRoleInfo selectByPrimaryKey(String id);

    /**
     * 删除用户判断是否有角色
     * @param userid
     * @return
     */
    int selectCountByUid(String userid);

    /**
     * 根据角色id删除用户角色信息
     * @param map
     * @return
     */
    int deleteUserRole(Map map);

    /**
     * 删除用户
     * @param map
     * @return
     */
    int deleteUserRoleByUid(Map map);


    /**
     * 插入用户角色集合
     * @param list
     * @return
     */
    int insertList(List<UserRoleInfo> list);
}