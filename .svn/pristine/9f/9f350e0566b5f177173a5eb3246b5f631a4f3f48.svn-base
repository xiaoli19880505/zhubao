package com.sys.service;

import com.sys.common.CommonUtils;
import com.sys.common.StringUtils;
import com.sys.mapper.MenuRoleInfoMapper;
import com.sys.pojo.MenuRoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/12 0012
 * @desc
 */
@Service
public class MenuRoleService extends BaseService<MenuRoleInfo>{
    @Autowired
    MenuRoleInfoMapper menuRoleInfoMapper;
    public int findCountByMenuId(Map map){
        return menuRoleInfoMapper.findCountByMenuId(map);
    }
    /**
     * 根据角色查询菜单
     * @param map
     * @return
     */
    public List<MenuRoleInfo> selectByRoleId(Map<String,Object> map){
        return menuRoleInfoMapper.selectByRoleId(map);
    }
    @Transactional
    public int saveMenuRole(String dutyid,String menuArray ){
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("dutyid",dutyid);
            //删除原来角色id为dutyid的数据
            menuRoleInfoMapper.deleteMenuRoleByRoleId(map);
            List<MenuRoleInfo> list=new ArrayList<MenuRoleInfo>();
            String[] array=menuArray.split(",");
            for (String menuid:array) {
                if(StringUtils.isEmpty(menuid)){
                    continue;
                }
                MenuRoleInfo menuRoleInfo=new MenuRoleInfo();
                menuRoleInfo.setMrId(CommonUtils.getUUIDWith_());
                menuRoleInfo.setMrDutyid(dutyid);
                menuRoleInfo.setMrMenuid(menuid);
                list.add(menuRoleInfo);
            }
            menuRoleInfoMapper.insertList(list);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }



}
