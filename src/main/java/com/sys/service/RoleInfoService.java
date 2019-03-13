package com.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sys.mapper.RoleInfoMapper;
import com.sys.pojo.RoleInfo;
import com.sys.pojo.UserInfo;
import com.sys.pojo.extend.DataGridResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/10 0010
 * @desc
 */
@Service
public class RoleInfoService extends BaseService<RoleInfo> {
    @Resource
    RoleInfoMapper roleInfoMapper;

    public int getRoleInfoCount(Map<String,Object> map){

        return roleInfoMapper.getRoleInfoCount(map);
    }

    public String getMaxCode(){
        return roleInfoMapper.getMaxCode();
    }

    public PageInfo<RoleInfo> page(Map map) {

        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
                Integer.parseInt((String) map.get("rows")));
        List<RoleInfo> list = roleInfoMapper.selectByPage(map);
        return new PageInfo<RoleInfo>(list);
    }

    /**
     * 条件查询角色列表
     * @param map
     * @return
     */
    public List<RoleInfo> getRoleListByMap(Map map){
        return this.roleInfoMapper.getRoleListByMap(map);
    }
}
