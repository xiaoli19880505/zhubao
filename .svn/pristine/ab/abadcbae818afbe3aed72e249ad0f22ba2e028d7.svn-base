package com.sys.mapper;

import com.sys.pojo.MenuInfo;
import java.util.List;
import java.util.Map;

public interface MenuInfoMapper extends IMapper<MenuInfo>{
    /**
     * 查询父级
     */
    List<MenuInfo> findAllMouduleList(Map map);

    List<MenuInfo> listChildrenType(String faid);


    MenuInfo selectById(String meId);


    List<MenuInfo> findMenuInfoByUcode(String userCode);

    /**
     * 多条件分页查询
     *
     * @return
     */
    List<MenuInfo> listModuleByPage(Map<String, Object> map);

    /**
     * 根据条件查询菜单数量
     * @param map
     * @return
     */
    int countMenuInfo(Map<String,Object> map);

    /**
     * 获取最大编号
     * @return
     */
    String getMaxCode();

    /**
     * 获取最大排序号
     * @return
     */
    String getMaxOrder(String faid);
}