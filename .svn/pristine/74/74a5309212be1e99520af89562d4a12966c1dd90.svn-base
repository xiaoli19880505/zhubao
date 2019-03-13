package com.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sys.mapper.UserInfoMapper;
import com.sys.pojo.UserInfo;
import com.sys.pojo.extend.DataGridResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service("userInfoService")
public class UserInfoService extends  BaseService<UserInfo> {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 分页查询全部
     * @param map
     * @return
     */
    public List<UserInfo> findAllUserInfo(Map map){
        return this.userInfoMapper.findAllUserInfo(map);
    }

    public  int findAllUCountserInfo(Map map){
        return this.userInfoMapper.findAllUCountserInfo(map);
    }


    public UserInfo findUserByUserCode(String usercode,String userpwd){
        return userInfoMapper.findUserByUserCode(usercode,userpwd);
    }

    /**
     * 根据角色查询用户
     * @param map
     * @return
     */
    public List<UserInfo> findUserByRoleId(Map map){
        return this.userInfoMapper.findUserByRoleId(map);
    }

    /**
     * 条件查询用户实体
     * @param map
     * @return
     */
    public UserInfo getUserByMap(Map map){
        return  this.userInfoMapper.getUserByMap(map);
    }
}
