package com.sys.controller.sysma;

import com.sys.pojo.MenuRoleInfo;
import com.sys.pojo.UserInfo;
import com.sys.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/12 0012
 * @desc
 */
@Controller
@RequestMapping("/MenuRole")
public class MenuRoleController {
    @Autowired
    MenuRoleService menuRoleService;
    @RequestMapping("/selectByRoleId")
    @ResponseBody
    public Object selectByRoleId(String dutyid){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("dutyid",dutyid);
        List<MenuRoleInfo> list=this.menuRoleService.selectByRoleId(map);
        map.remove("dutyid");
        map.put("menuRole",list);
        return map;
    }
    @RequestMapping("/saveMenuRole")
    @ResponseBody
    public Object saveMenuRole(String dutyid,String menuArray){
        String result="";
        System.out.println("**********"+dutyid);
        System.out.println("******menuArray*****"+menuArray);
//        if(menuArray==null||menuArray.equals("")){
//            result="请选择菜单";
//        }else{
            int saveCount=menuRoleService.saveMenuRole(dutyid,menuArray);
            if(saveCount>0){
                result="保存成功";
            }else{
                result="保存失败";
            }
//        }
        return result;
    }
}
