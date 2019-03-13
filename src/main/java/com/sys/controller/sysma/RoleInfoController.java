package com.sys.controller.sysma;

import com.github.pagehelper.PageInfo;
import com.sys.common.CommonUtils;
import com.sys.pojo.RoleInfo;
import com.sys.pojo.UserInfo;
import com.sys.service.MenuRoleService;
import com.sys.service.RoleInfoService;
import com.sys.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
@RequestMapping("/roleInfo")
public class RoleInfoController {
    @Autowired
    RoleInfoService roleInfoService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    MenuRoleService menuRoleService;

    /**
     * 新增角色页面
     * @return
     */
    @RequestMapping("addRole")
    public String toAddRole()
    {
        return "SystemMa/roleMa/roleAdd";
    }





    /**
     * 修改角色页面
     * @return
     */
    @RequestMapping("editRole")
    public String toEditRole()
    {
        return "SystemMa/roleMa/roleEdit";
    }

    /**
     * 设置用户角色页面
     * @return
     */
    @RequestMapping("setRole")
    public String toSetRole()
    {
        return "SystemMa/roleMa/roleBangUser";
    }

    /**
     * 查询列表
     * @return
     */
    @RequestMapping("/selectRoleInfo")
    @ResponseBody
    public Object selectRoleInfo(String page,String rows,String dutyname){
        if(page==null||page==""){
            page="1";
        }
        if(rows==null||rows==""){
            rows="20";
        }
        Map<String,Object> map=new HashMap();
        map.put("page",page);
        map.put("rows",rows);
        if(dutyname!=null&&(!dutyname.equals(""))){
            map.put("dutyname",dutyname);
        }

        PageInfo<RoleInfo> roleInfoPageInfo=roleInfoService.page(map);

        map.remove("page");
        map.remove("rows");
        map.put("rows",roleInfoPageInfo.getList());
        map.put("total",roleInfoPageInfo.getTotal());

       return map;
    }

    /**
     * 新增角色
     * @param roleInfo
     * @return
     */
    @RequestMapping("/saveRoleInfo")
    @ResponseBody
    public Object saveRoleInfo(RoleInfo roleInfo){
        System.out.println("************进来新增");
        String result="";
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("dutyname",roleInfo.getDutyname());
        int count=roleInfoService.getRoleInfoCount(map);
        if(count>0){
            result="相同角色名称已存在";
        }else{
            roleInfo.setDutyid(CommonUtils.getUUIDWith_());
            String maxCode=roleInfoService.getMaxCode();
            roleInfo.setDutycode(CommonUtils.getCode(maxCode));
            int insertCount=roleInfoService.insert(roleInfo);
            if(insertCount>0){
                result="新增成功";
            }else{
                result="新增失败";
            }
        }

        return result;
    }

    /**
     * 修改角色
     * @param roleInfo
     * @return
     */
    @RequestMapping("/updateRoleInfo")
    @ResponseBody
    public Object updateRoleInfo(RoleInfo roleInfo){
        String result="";
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("dutyid",roleInfo.getDutyid());
        map.put("dutyname",roleInfo.getDutyname());
        int count=roleInfoService.getRoleInfoCount(map);
        if (count>0){
            result="相同角色名称已存在";
        }else{
            int updateCount=roleInfoService.update(roleInfo);
            if (updateCount>0){
                result="修改成功";
            }else {
                result="修改失败";
            }
        }
        return result;
    }

    /**
     * 删除角色
     * @param dutyid
     * @return
     */
    @RequestMapping("/deleteRoleInfo")
    @ResponseBody
    public Object deleteRoleInfo(String dutyid){
        String result="";
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("dutyid",dutyid);
        //获取当前角色的用户数
        List<UserInfo> userInfos=userInfoService.findUserByRoleId(map);
        if(userInfos!=null&&userInfos.size()>0){
            result="当前角色存在用户，不可以删除";
        }else{
            //删除中间表信息
            int mrCount=menuRoleService.findCountByMenuId(map);
            if(mrCount>0){
                return "当前角色存在为清除的菜单权限关联!";
            }
            int deleteCount=roleInfoService.delete(dutyid);
            if(deleteCount>0){
                result="删除成功";
            }else{
                result="删除失败";
            }
        }

        return result;
    }

}
