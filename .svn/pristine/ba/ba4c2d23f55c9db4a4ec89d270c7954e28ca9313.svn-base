package com.sys.controller.sysma;

import com.sys.pojo.UserRoleInfo;
import com.sys.service.UserRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/11 0011
 * @desc
 */
@Controller
@RequestMapping("/UserRoleInfo")
public class UserRoleInfoController {
    @Autowired
    UserRoleInfoService userRoleInfoService;
    @RequestMapping("/savaUserRoleInfo")
    @ResponseBody
    public Object savaUserRoleInfo(String dutyid,String userArray){
        String result="";
        System.out.println("**********"+dutyid);
        System.out.println("******userArray*****"+userArray);
        if(userArray==null||userArray.equals("")){
            result="请选择用户";
        }else{
            int saveCount=userRoleInfoService.savaUserRoleInfo(dutyid,userArray);
            if(saveCount>0){
                result="保存成功";
            }else{
                result="保存失败";
            }
        }
        return result;
    }

    @RequestMapping("/savaUserRoleInfoByUid")
    @ResponseBody
    public Object savaUserRoleInfoByUid(String userid,String userArray){
        String result="";
        System.out.println("**********"+userid);
        System.out.println("******userArray*****"+userArray);
            int saveCount=userRoleInfoService.saveUserRoleInfoByJs(userid,userArray);
            if(saveCount>0){
                result="保存成功";
            }else{
                result="保存失败";
            }
        return result;
    }










    @RequestMapping("findRoleByUid")
    @ResponseBody
    public Object findRoleByUid(String userid){
        List<UserRoleInfo> list=this.userRoleInfoService.findRoleByUid(userid);
        return list;
    }


}
