package com.sys.shiro;

import com.sys.mapper.RoleInfoMapper;
import com.sys.mapper.UserInfoMapper;
import com.sys.pojo.UserInfo;
import com.sys.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 自定义 MyRealm 查询数据并返回正确的数据
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RoleInfoMapper roleInfoMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pricipal) {
        //获取当前登陆用户,账号
        String userCode=pricipal.toString();
        System.out.println("当前登陆用户"+userCode);
        //获取角色信息
        List<Map<String, Object>> roleList = new ArrayList<Map<String, Object>>();
        roleList =roleInfoMapper.selectRoleInfoByUname(userCode);
        Set<String> roles = new HashSet<String>();
        if(roleList.size()>0){
            for(Map<String, Object> role : roleList){
                roles.add(String.valueOf(role.get("dutyname")));
            }
        }else{
            System.out.println("当前用户没有角色！");
        }

        SimpleAuthorizationInfo info = null;
        info = new SimpleAuthorizationInfo(roles);
        return info;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.将token转换为UsernamePasswordToken
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        //2.获取token中的登录账户
        String userCode = userToken.getUsername();
        String userpwd=userToken.getCredentials().toString();
        String password = new String((char[])token.getCredentials());
        //3.查询数据库，是否存在指定的用户名和密码的用户(主键/账户/密码/账户状态/盐)
        UserInfo   us = null;
        us = userInfoMapper.findUserByUserCode(userCode,password);
        //4.1 如果没有查询到，抛出异常
        if( us == null ) {
            throw new UnknownAccountException("账户"+userCode+"不存在！");
        }
        if( us.getState() == "0"){
            throw new LockedAccountException(us.getUsercode()+"帐号已被禁用！");
        }
        //4.2 如果查询到了，封装查询结果，
        Object principal = us.getUsercode();
        Object credentials = us.getUserpwd();
        String realmName = this.getName();
        //*String salt = us.getSalt();*//*
        //获取盐，用于对密码在加密算法(MD5)的基础上二次加密ֵ
       /* ByteSource byteSalt = ByteSource.Util.bytes(salt);*/
        SimpleAuthenticationInfo info= new SimpleAuthenticationInfo(principal, credentials,realmName);


        //5. 返回给调用login(token)方法
       return info;
    }


    /**
     * 清空当前用户权限信息
     */
    public  void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 指定principalCollection 清除
     */
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }


}
