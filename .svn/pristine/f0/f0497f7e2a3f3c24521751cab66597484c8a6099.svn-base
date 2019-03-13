package com.sys.shiro;

import com.sys.mapper.UserInfoMapper;
import com.sys.pojo.UserInfo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RememberMeFilter extends FormAuthenticationFilter {


    protected final static Logger logger = LogManager.getLogger(RememberMeFilter.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue){
        Subject subject = getSubject(request,response);
        Session session = subject.getSession();
        /**满足三个条件：选择记住我,非密码登录,session为空*/
        if(!subject.isAuthenticated() && subject.isRemembered() && session.getAttribute("user") == null){
            Object principal = subject.getPrincipal();
            if(principal!=null){
                UserInfo userInfo = new UserInfo();
                userInfo.setUsercode(principal.toString());
              /*  UserInfo user = userInfoMapper.selectOne(pojo);
                session.setAttribute("user", user.getAccount());*/
            }
        }
        return subject.isAuthenticated()||subject.isRemembered();
    }

}
