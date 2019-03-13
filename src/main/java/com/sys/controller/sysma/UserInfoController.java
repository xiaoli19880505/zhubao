package com.sys.controller.sysma;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sys.common.CommonUtils;
import com.sys.common.encrypt.AESUtil;
import com.sys.pojo.UserInfo;
import com.sys.pojo.extend.DataGridResult;
import com.sys.service.UserInfoService;
import com.sys.service.UserRoleInfoService;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("UserInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserRoleInfoService userRoleInfoService;

    @RequestMapping("/touseradd")
    public String  touseradd(){
        return "SystemMa/userMa/userAdd";
    }

    @RequestMapping("/touseredit")
    public String  touseredit(){
        return "SystemMa/userMa/userEdit";
    }

    @RequestMapping("/toBangRole")
    public String  toBangRole(){
        return "SystemMa/userMa/userBangRole";
    }

    @RequestMapping("/updateUserInfoByCsh")
    @ResponseBody
    public Object updateUserInfoByCsh(UserInfo userInfo){
        String result="";
        try {
            userInfo.setUserpwd(AESUtil.encryptAES("000000"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer count=this.userInfoService.update(userInfo);
        if (count>0){
            result=" 修改成功";
        }else{
            result="修改失败";
        }
        return result;
    }



    @RequestMapping("findAllUserInfo")
    @ResponseBody
    public  Object findAllUserInfo(String ssq,String ssj,String page,String rows,String username,String linktel,String state,String sex){
        if (page==null || page==""){
            page="1";
        }
        Map<String,Object> map=new HashMap<String, Object>();
        //map.put("page",page);
        map.put("ssq",ssq);
        map.put("ssj",ssj);
        //map.put("rows",rows);
        map.put("state",state);
        map.put("sex",sex);
        map.put("username",username);
        map.put("linktel",linktel);

        PageHelper.startPage(Integer.parseInt(page),Integer.parseInt(rows));
        PageInfo<UserInfo> pageList = new PageInfo<UserInfo>(this.userInfoService.findAllUserInfo(map));

        map.put("rows",pageList.getList());
        map.put("total",pageList.getTotal());
        return map;
    }

    @RequestMapping("delete")
    @ResponseBody
    public  Object  delete(String userid){
        Integer count=this.userRoleInfoService.selectCountByUid(userid);
        String result="";
            Integer delete=this.userInfoService.delete(userid);
            if (delete>0){
                result="删除成功";
            }else {
                result="删除失败";
            }

        return result;
    }

    @RequestMapping("insert")
    @ResponseBody
    public  Object  insert(UserInfo user){
        String result="";
        try {
            user.setUserid(CommonUtils.getUUIDWith_());
            user.setUserpwd("123456");
            user.setUserpwd(AESUtil.encryptAES(user.getUserpwd()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer delete=this.userInfoService.insert(user);
        if (delete>0){
            result="添加成功";
        }else {
            result="添加失败";
        }
        return result;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(UserInfo userInfo){
        String result="";
        Integer updatecount=this.userInfoService.update(userInfo);
        if (updatecount>0){
            result="修改成功";
        }else{
            result="修改失败";
        }
        return result;
    }

    /**
     * 根据角色查询用户
     * @param dutyid
     * @return
     */
    @RequestMapping("/findUserByRoleId")
    @ResponseBody
    public Object findUserByRoleId(String dutyid){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("dutyid",dutyid);
        List<UserInfo> list=this.userInfoService.findUserByRoleId(map);
        map.remove("dutyid");
        map.put("userInfo",list);
        return map;
    }

    /**
     * 修改密码
     * @param oldPass 原始密码
     * @param newPass 新密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePerPass",method = RequestMethod.POST )
    @ResponseBody
    public Object updatePerPass(@RequestParam("oldPass") String oldPass,@RequestParam("newPass") String newPass,@RequestParam("repeatNewPass")String repeatNewPass, HttpServletRequest request)throws Exception{
       UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
       String result = "";
       String userTruePass = AESUtil.decryptAES(userInfo.getUserpwd());
       System.out.println("userInfo.getUserpwd():"+userTruePass);
       if(!oldPass.equals(newPass) && oldPass.equals(userTruePass)){
            userInfo.setUserpwd(AESUtil.encryptAES(newPass));
            int updateCount = this.userInfoService.update(userInfo);
            if(updateCount>0){
                result ="修改成功";
            }else{
                result ="密码修改失败";
            }
       }else if(oldPass.equals(newPass)){
           result ="新密码和原始密码不得相同";
       }else{
           result ="原始密码不正确";
       }
       return result;
    }



}
