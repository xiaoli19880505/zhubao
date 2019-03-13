package com.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sys.mapper.MenuInfoMapper;
import com.sys.pojo.MenuInfo;
import com.sys.pojo.RoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/10 0010
 * @desc
 */
@Service
public class MenuInfoService extends BaseService<MenuInfo> {
    @Resource
    MenuInfoMapper mapper;

    public List<MenuInfo> findAllMouduleList(Map map) {
        // TODO Auto-generated method stub
        return mapper.findAllMouduleList(map);
    }

    public MenuInfo findchildrenModuleList(MenuInfo module) {
        // TODO Auto-generated method stub
        List<MenuInfo> childrenList = this.mapper.listChildrenType(module.getMeId());
        if(childrenList == null || childrenList.size() == 0) return module;
        module.setChildren((List<Object>) (Object) childrenList);
        //System.out.println("------------------1--"+module.getChildren().get(0).getModulename());
        for (MenuInfo temModule : childrenList)
        {
            findchildrenModuleList(temModule);
        }
        return module;
    }

    public PageInfo<MenuInfo> page(Map map) {

        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
                Integer.parseInt((String) map.get("rows")));
        List<MenuInfo> list = this.mapper.listModuleByPage(map);
        return new PageInfo<MenuInfo>(list);
    }

    public int getMenuInfoCount(Map<String,Object> map){

        return this.mapper.countMenuInfo(map);
    }

     public  List<MenuInfo> findMenuInfoByUcode(String userCode){
        return this.mapper.findMenuInfoByUcode(userCode);
     }



    public String getMaxCode(){
        return this.mapper.getMaxCode();
    }

    /**
     * 获取最大排序号
     * @return
     */
    public String getMaxOrder(String faid){
        return this.mapper.getMaxOrder(faid);
    }
}
