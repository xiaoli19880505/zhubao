package com.sys.mapper;

import com.sys.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper extends  IMapper<UserInfo> {


    UserInfo findUserByUserCode(@Param("usercode") String usercode,@Param("userpwd") String userpwd);



    /**
     * 分页查询全部
     * @param map
     * @return
     */
    List<UserInfo> findAllUserInfo(Map map);
    /**
     * 分页查询tiaoshu
     * @param map
     * @return
     */
    int findAllUCountserInfo(Map map);


    List<UserInfo> findUserByRoleId(Map<String, Object> map);

    /**
     * 条件查询用户信息，usercode查询
     * @param map
     * @return
     */
    UserInfo getUserByMap(Map map);
}