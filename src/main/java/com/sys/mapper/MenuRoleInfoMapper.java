package com.sys.mapper;

import com.sys.pojo.MenuRoleInfo;
import java.util.List;
import java.util.Map;

public interface MenuRoleInfoMapper extends IMapper<MenuRoleInfo>{

    int findCountByMenuId(Map map);

    /**
     * 根据角色查询菜单
     * @param map
     * @return
     */
    List<MenuRoleInfo> selectByRoleId(Map<String,Object> map);

    /**
     * 根据角色删除菜单角色信息
     * @param map
     * @return
     */
    int deleteMenuRoleByRoleId(Map<String,Object> map);

    /**
     * 批量添加菜单角色信息
     * @param list
     * @return
     */
    int insertList(List<MenuRoleInfo> list);

}