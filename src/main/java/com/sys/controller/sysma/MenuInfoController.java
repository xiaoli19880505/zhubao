package com.sys.controller.sysma;

import com.github.pagehelper.PageInfo;
import com.sys.common.CommonUtils;
import com.sys.common.ListToJsonConvertor;
import com.sys.pojo.MenuInfo;
import com.sys.pojo.RoleInfo;
import com.sys.pojo.UserInfo;
import com.sys.service.MenuInfoService;
import com.sys.service.MenuRoleService;
import com.sys.service.QxInfoService;
import net.sf.json.JSONArray;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
@Controller
@RequestMapping("/menuInfo")
public class MenuInfoController {

    @Autowired
    MenuInfoService menuInfoService;
    @Autowired
    MenuRoleService menuRoleService;
//    @Autowired
//    QxInfoService qxInfoService;

    /**
     * 设置就角色权限页面
     * @return
     */
    @RequestMapping("setRole")
    public String toSetRole()
    {
        return "SystemMa/moduleMa/roleBangModule";
    }

    /**
     * 新增菜单页面
     * @return
     */
    @RequestMapping("addMenu")
    public String addMenu() { return "SystemMa/moduleMa/moduleAdd"; }

    /**
     * 修改菜单页面
     * @return
     */
    @RequestMapping("editMenu")
    public String editMenu() { return "SystemMa/moduleMa/moduleEdit"; }


    /**
     * 递归绑定数据
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAllMouduleList")
    @ResponseBody
    public Object findAllMouduleList() throws Exception {
        Map<String, Object> moduleMap = new HashMap<String, Object>();
        moduleMap.put("faid", "0");
        List<MenuInfo> childrenList = this.menuInfoService.findAllMouduleList(moduleMap);
        for (MenuInfo module : childrenList) {
            menuInfoService.findchildrenModuleList(module);
        }
        System.out.println("===end====");
        String[] co = new String[]{"id","text","meCode", "meUrl", "meOrderno", "meDesc"};
        JSONArray array = ListToJsonConvertor.convertorList(childrenList, co);
        String tempStr = array.toString();
        tempStr = tempStr.replace("meName", "text");
        tempStr = tempStr.replace("meId", "id");
        System.out.println("**********"+tempStr);
        return JSONArray.fromObject(tempStr);
    }

    @RequestMapping("/loadMenu")
    @ResponseBody
    public Object loadMenu(HttpSession session){
                String name =SecurityUtils.getSubject().getPrincipal().toString();
        List<MenuInfo> menu=menuInfoService.findMenuInfoByUcode(name);
        boolean flag=true;
        List<MenuInfo> moduleList=new ArrayList<MenuInfo>();
        for (MenuInfo module1:menu){
            flag=true;
            for (MenuInfo module2:menu) {
                if (module2.getMeFaid().equals(module1.getId())){
                    module1.setState("closed");
                    module1.getChildren().add(module2);
                }
                if(!module1.getMeFaid().equals(module2.getId())){
                    continue;
                }else{
                    flag=false;
                }
            }
            if(flag){
                moduleList.add(module1);
            }
        }

        return moduleList;
    }


    /**
     * 多条件分页查询
     *
     * @param rows
     *            页显示数
     * @param page
     *            当前第几页
     * @param moduleid
     * @return
     */
    @RequestMapping("/listModuleAll")
    @ResponseBody
    public Object listModuleAll(String rows, String page, String moduleid) {
        Map<String, Object>map = new HashMap<String, Object>();
        if(page==null||page==""){
            page="1";
        }
        if(rows==null||rows==""){
            rows="20";
        }
        map.put("page",page);
        map.put("rows",rows);
        if(moduleid!=null&&(!moduleid.equals(""))){
            map.put("moduleid",moduleid);
        }
        System.out.println("****************moduleid"+moduleid);
        PageInfo<MenuInfo> roleInfoPageInfo=this.menuInfoService.page(map);
        map.remove("page");
        map.remove("rows");
        map.put("rows",roleInfoPageInfo.getList());
        map.put("total",roleInfoPageInfo.getTotal());
        return map;
    }

    /**
     * 新增菜单
     * @param menuInfo
     * @return
     */
    @RequestMapping("/saveMenuInfo")
    @ResponseBody
    public Object saveMenuInfo(MenuInfo menuInfo,String moduleid){
        String result="";
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("meName",menuInfo.getMeName());
        map.put("meFaid",menuInfo.getMeFaid());

            int menuCount=this.menuInfoService.getMenuInfoCount(map);
            if(menuCount>0){
                result="相同模块下菜单名称已存在";
            }else{
                menuInfo.setMeId(CommonUtils.getUUIDWith_());
                menuInfo.setMeFaid(moduleid);
                //获取同级模块下的最大排序
                String maxOrder=menuInfoService.getMaxOrder(moduleid);
                menuInfo.setMeOrderno(CommonUtils.getCode(maxOrder));
                //获取最大编号
                String maxCode=this.menuInfoService.getMaxCode();
                menuInfo.setMeCode(CommonUtils.getCode(maxCode));
                int saveCount=this.menuInfoService.insert(menuInfo);
                if(saveCount>0){
                    result="新增成功";
                }else{
                    result="新增失败";
                }
            }
        return result;
    }
    /**
     * 修改菜单
     * @param menuInfo
     * @return
     */
    @RequestMapping("/updateMenuInfo")
    @ResponseBody
    public Object updateMenuInfo(MenuInfo menuInfo, String moduleid,String faid){
        String result="";
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("meName",menuInfo.getMeName());
        map.put("meId",moduleid);
        map.put("meFaid",faid);
        menuInfo.setMeId(moduleid);
        int menuCount=this.menuInfoService.getMenuInfoCount(map);
        if(menuCount>0){
            result="相同模块下菜单名称已存在";
        }else{
            int updateCount=this.menuInfoService.update(menuInfo);
            if(updateCount>0){
                result="修改成功";
            }else{
                result="修改失败";
            }
        }
        return result;
    }
    /**
     * 删除菜单
     * @param moduleid
     * @return
     */
    @RequestMapping("/deleteMenuInfo")
    @ResponseBody
    public Object deleteMenuInfo(String moduleid){
        String result="";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("meId",moduleid);
        int qxCount=0;//qxInfoService.findQxInfoByMeId(map);
        int mrCount=menuRoleService.findCountByMenuId(map);
        if(qxCount==0&&mrCount==0){
            map.put("meFaid",moduleid);
            int meCount=menuInfoService.getMenuInfoCount(map);
            if(meCount>0){
                result="该菜单下存在子级菜单，不可以删除";
            }else{
                int deleteCount=menuInfoService.delete(moduleid);
                if(deleteCount>0){
                    result="删除成功";
                }else{
                    result="删除失败";
                }
            }
        }else if(qxCount > 0){
            result="该菜单下权限，不可以删除";
        }else{
            result="该菜单下面有角色信息，不可以删除";
        }
        return result;
    }




}
