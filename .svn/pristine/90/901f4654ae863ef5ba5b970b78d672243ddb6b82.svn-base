package com.sys.mapper;

import com.sys.pojo.MenuInfo;
import com.sys.pojo.RoleInfo;
import java.util.List;
import java.util.Map;

public interface RoleInfoMapper extends IMapper<RoleInfo>{
    /**
     * @Description: 分页查询
     * @param map
     * @return
     * @throws
     * @author lao
     * @Date 2018年1月4日下午1:55:07
     * @version 1.00
     */
    List<RoleInfo> selectByPage(Map<String, Integer> map);

    /**
     * 根据用户查询所属角色
     * @param usercode
     * @return
     */
    List<Map<String,Object>> selectRoleInfoByUname(String  usercode);

    /**
     * 根据条件查询角色数量
     * @param map
     * @return
     */
    int getRoleInfoCount(Map<String,Object> map);

    /**
     * 获取最大编号
     * @return
     */
    String getMaxCode();

    /**
     * 条件查询角色列表
     * @param map
     * @return
     */
    List<RoleInfo> getRoleListByMap(Map<String,Object> map);
}