package com.sys.controller.app;

import com.sys.common.encrypt.AESUtil;
import com.sys.common.verification.CodeUtil;
import com.sys.pojo.ApplyUserinfo;
import com.sys.service.ApplyUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2019/1/4 0004
 * @desc
 */
@Controller
@RequestMapping("/appLogin")
public class AppLoginController {
    @Autowired
    ApplyUserinfoService applyUserinfoService;
    /**
     * 登录过程
     * @param sfzh
     * @param userpwd
     * @param checkcode
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView loadMenu(String sfzh, String userpwd, String checkcode, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> map=new HashMap<String, Object>();
        ModelAndView modelAndView=new ModelAndView();
        HttpSession session =request.getSession();
        /*比较验证码是否输入正确*/
        Object object = session.getAttribute("code");
        /*如果没有验证码*/
        if(object==null){
            String path = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
            ApplyUserinfo user=(ApplyUserinfo) request.getSession().getAttribute("applyUserinfo");
            if (user==null) {
                response.sendRedirect(basePath+"appPath/login");
                return  null;
            }
        }
        String generatorCode =object.toString();
        String goUrl = "zfApp/applyList";
        modelAndView.addObject("sfzh",sfzh);
        modelAndView.addObject("userpwd",userpwd);
        if(!checkcode.equalsIgnoreCase(generatorCode)){
            request.setAttribute("message", "验证码不正确！");
            modelAndView.setViewName("zfApp/login/login");
            return modelAndView;
        }
        map.put("sfzh",sfzh);
        // ApplyUserinfo applyUserinfo = applyUserinfoService.selectAll(map);
        try {
            System.out.println("AESUtil.encryptAES(userpwd):"+userpwd+"----"+ AESUtil.encryptAES(userpwd));
            map.put("userpwd", AESUtil.encryptAES(userpwd));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result="";
        ApplyUserinfo applyUserinfo=this.applyUserinfoService.selectUserInfo(map);
        if (applyUserinfo==null){
            modelAndView.addObject("message","用户名或密码错误");
            Map<String,Object> codeMap = CodeUtil.generateCodeAndPic();
            session.setAttribute("code",codeMap.get("code"));
            modelAndView.setViewName("zfApp/login/login");
            return modelAndView;
        }else  if ("0".equals(applyUserinfo.getState())){
            modelAndView.addObject("message","用户为禁用状态");
            modelAndView.setViewName("zfApp/login/login");
            return modelAndView;
        }
        session.setAttribute("applyUserinfo",applyUserinfo);
        //session.setMaxInactiveInterval(60);
        modelAndView.setViewName("zfApp/applyList/applyList");

        /*审核端一权限账号登录，以获得shrio的接口认证，在申请端调用一些需要shiro认证才能通过的接口*/
       /* Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wangli1", AESUtil.encryptAES("123456"));
        subject.login(token);*/
        return modelAndView;
    }


    @RequestMapping("/appExit")
    public  String appExit(HttpSession session){
            session.removeAttribute("applyUserinfo");
            return "zfApp/login/login";
    }



}
