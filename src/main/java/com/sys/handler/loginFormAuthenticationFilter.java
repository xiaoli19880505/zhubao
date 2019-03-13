package com.sys.handler;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import com.sys.common.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class loginFormAuthenticationFilter extends FormAuthenticationFilter {

   /* @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("successUrl:"+getSuccessUrl());
        if(!StringUtils.isEmpty(getSuccessUrl())){
            Session session = subject.getSession(false);
            if(session!=null){
                session.removeAttribute("shiroSavedRequest");
            }
        }
        return super.onLoginSuccess(token, subject, request, response);
    }*/

    /*@Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        String successUrl = "/login/toIndex";
        WebUtils.issueRedirect(request,response,successUrl);
        return false;
    }*/

   /* @Override
            protected void issueSuccessRedirect(ServletRequest request, ServletResponse response)
        throws Exception{
                System.out.println("successUrl:"+getSuccessUrl());
             WebUtils.issueRedirect(request, response,getSuccessUrl(), null, true);
        }*/
}