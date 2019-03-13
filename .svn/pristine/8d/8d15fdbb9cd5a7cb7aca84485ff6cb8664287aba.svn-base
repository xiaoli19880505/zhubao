package com.sys.shiro;

import com.sys.common.JedisUtils;
import com.sys.pojo.UserInfo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class KickoutSessionControlFilter extends AccessControlFilter {

    protected final static Logger logger = LogManager.getLogger(KickoutSessionControlFilter.class);
    //踢出状态，true标示踢出
    final static String KICKOUT_STATUS = KickoutSessionControlFilter.class.getCanonicalName()+ "_kickout_status";

    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object mappedValue) throws Exception {

        HttpServletRequest httpRequest = ((HttpServletRequest)request);
        /*UserInfo user = (UserInfo) httpRequest.getSession().getAttribute("user");
        //Session未失效时验证通过
        if(null != user || isLoginRequest(request, response)){
            return Boolean.TRUE;
        }*/

        String url = httpRequest.getRequestURI();
        Subject subject = getSubject(request, response);
        //如果是相关目录 or 如果没有登录 就直接return true
        if(url.startsWith("/tologin/") || (!subject.isAuthenticated() && !subject.isRemembered())){
            return Boolean.TRUE;
        }
        Session session = subject.getSession();
        UserInfo user = (UserInfo)session.getAttribute("user");
        /*session.getLastAccessTime();//最后登陆时间
        session.getTimeout();//超时时间*/
        System.out.println("最后登陆时间moreTime:" + session.getLastAccessTime() + ",超时时间:" + session.getTimeout());
        logger.error("最后登陆时间moreTime:" + session.getLastAccessTime() + ",超时时间:" + session.getTimeout());

        if(new Date().getTime()-session.getLastAccessTime().getTime()
                >session.getTimeout()){//判断超时 当前时间-最后登陆时间大于设置设定超时时间
//            session.setAttribute("user",null);
            return Boolean.FALSE;
        }
        //Session未失效时验证通过
        if(null != user || isLoginRequest(request, response)){
            return Boolean.TRUE;
        }
        session.getAttribute(user);
        Serializable sessionId = session.getId();



        /**
         * 判断是否已经踢出
         * 1.如果是Ajax 访问，那么给予json返回值提示。
         * 2.如果是普通请求，直接跳转到登录页
         */
        /*Boolean marker = (Boolean)session.getAttribute(KICKOUT_STATUS);
        if (null != marker && marker ) {
            Map<String, String> resultMap = new HashMap<String, String>();
            //判断是不是Ajax请求
            if (ShiroUtils.isAjax(request) ) {
                logger.debug("当前用户已经在其他地方登录，并且是Ajax请求！");
                resultMap.put("user_status", "300");
                resultMap.put("message", "您已经在其他地方登录，请重新登录！");
                ShiroUtils.out(response, resultMap);
            }
            return  Boolean.FALSE;
        }else{
            session.setAttribute(KICKOUT_STATUS, Boolean.TRUE);
            return Boolean.TRUE;
        }*/


        subject.getSession().getTimeout();
        //获取用户账号
        String userName = (String)subject.getPrincipal();

        //如果是ajax请求响应头会有，x-requested-with

        if(ShiroUtils.isAjax(request)){
            logger.debug("当前用户没有登录，并且是Ajax请求！");
            ShiroUtils.out(response, (Map)mappedValue);

            //通过返回TRUE，通过前台的统一AJAX接受头部设置的sessionstatus参数，判断是否跳转到登录页面

            return Boolean.TRUE;
        }//FALSE  Session失效，切实非AJAX请求，验证是否，调用onAccessDenied跳转到登录页面
        return Boolean.FALSE;

        /*//从缓存获取用户-Session信息 <userName,SessionId>
       String jedisSessionId = JedisUtils.get(userName);

        //如果已经包含当前Session，并且是同一个用户，跳过。
        if(null!=jedisSessionId && jedisSessionId.equals((String)sessionId)){
            //更新存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
            JedisUtils.setex(userName,3600, (String)sessionId);
            return Boolean.TRUE;
        }
        //如果用户相同，Session不相同，那么就要处理了
        *//**
         * 如果用户Id相同,Session不相同
         * 1.获取到原来的session，并且标记为踢出。
         * 2.继续走
         *//*
        if(null!=jedisSessionId && !jedisSessionId.equals((String)sessionId)){
            //标记session已经踢出
            session.setAttribute(KICKOUT_STATUS, Boolean.TRUE);
            //存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
            JedisUtils.setex(userName, 3600, (String)sessionId);
            return  Boolean.TRUE;
        }

        if(null==jedisSessionId){
            //存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
            JedisUtils.setex(userName, 3600, (String)sessionId);
        }*/
//        return Boolean.TRUE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {

        //先退出,这一步才是正常清除Session
        Subject subject = getSubject(request, response);
        subject.logout();
        request.setAttribute("time_out_msg","登陆超时!");
        WebUtils.getSavedRequest(request);
        //再重定向
        WebUtils.issueRedirect(request, response,"/login");
        return false;
    }


}
