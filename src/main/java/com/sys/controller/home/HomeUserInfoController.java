package com.sys.controller.home;

import com.sys.pojo.HomeUserInfo;
import com.sys.service.BaseService;
import com.sys.service.HomeUserInfoService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author xiaofeng
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/13 0012
 * @desc 首页后台用户controller
 */
@Controller
@RequestMapping("homeUserInfo")
public class HomeUserInfoController {

    @Autowired
    private HomeUserInfoService homeUserInfoService;

    /**
     * 更改用户信息
     * @param request
     * @return
     */
    @RequestMapping("updateHomeUserInfo")
    @ResponseBody
    public Object updateHomeUserInfo(HttpServletRequest request){
        String userAccount = request.getParameter("userAccount");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        return homeUserInfoService.updateHomeUserInfo(userAccount, userName, userPassword);
    }

    /**
     * 用户验证
     * @param request
     * @return
     */
    @RequestMapping("selectHomeUserInfo")
    @ResponseBody
    public Object selectHomeUserInfo(HttpServletRequest request){
        String userAccount = request.getParameter("userAccount");
        String userPassword = request.getParameter("userPassword");
        Map<String,Object> map = homeUserInfoService.selectHomeUserInfo(userAccount, userPassword);
        if (!(Boolean) map.get("flag")) {
            return map.get("msg");
        } else {
            //把用户信息放到Session
            HomeUserInfo homeUserInfo = (HomeUserInfo)map.get("userObj");
            HttpSession session = request.getSession();
            session.setAttribute("userName",homeUserInfo.getUserName());
            session.setAttribute("userAccount",homeUserInfo.getUserAccout());
            session.setAttribute("userPassword",homeUserInfo.getUserPassword());
            return true;
        }
    }

}
