package com.sys.service;

import com.sys.common.encrypt.AESUtil;
import com.sys.mapper.HomeUserInfoMapper;
import com.sys.pojo.HomeUserInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeUserInfoService extends BaseService<HomeUserInfo> {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private HomeUserInfoMapper homeUserInfoMapper;

    /**
     * 用户新增 (暂时不用)
     * @return
     */
    @Transactional
    public Object insertHomeUserInfo(){
        return null;
    }

    /**
     * 用户删除 (暂时不用)
     * @return
     */
    @Transactional
    public Object deleteHomeUserInfo(){
        return null;
    }

    /**
     * 用户更改
     * @param userAccount 用户账号
     * @param userName 用户名称
     * @param userPassword 用户密码
     * @return
     */
    @Transactional
    public Object updateHomeUserInfo(String userAccount, String userName, String userPassword){
        if(StringUtils.isEmpty(userAccount)){
            return "用户信息不完整";
        }
        HomeUserInfo homeUserInfo = new HomeUserInfo();
        homeUserInfo.setUserAccout(userAccount);
        homeUserInfo.setDeleteFlag("F");
        List<HomeUserInfo> homeUserInfoes = homeUserInfoMapper.selectByCondition(homeUserInfo);
        if(homeUserInfoes==null || homeUserInfoes.size()==0){
            return "用户信息丢失!";
        }
        if(homeUserInfoes.size()>1){
            return "用户信息验证存在问题!";
        }
        homeUserInfo = homeUserInfoes.get(0);
        homeUserInfo.setUserName(userName);
        //进行base64加密
        String userPasswordBase64 = "";
        try{
            userPasswordBase64 = AESUtil.encryptAES(userPassword);
        } catch (Exception e) {
            logger.error("首页用户信息修改加密异常：" + e.getMessage());
            return "用户信息登陆异常!";
        }
        homeUserInfo.setUserPassword(userPasswordBase64);
        homeUserInfo.setUpdatePerson(userAccount);
        homeUserInfo.setUpdateTime(new Date());
        int num = homeUserInfoMapper.update(homeUserInfo);
        if(num>0){
            return "修改用户信息成功";
        } else {
            return "修改用户信息失败";
        }
    }

    /**
     * 用户信息查询验证
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return
     */
    @Transactional
    public Map<String,Object> selectHomeUserInfo(String userAccount, String userPassword){
        Map<String,Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(userAccount) || StringUtils.isEmpty(userPassword)){
            map.put("flag",false);
            map.put("msg","用户验证信息不完全,无法验证!");
            return map;
        }
        HomeUserInfo homeUserInfo = new HomeUserInfo();
        homeUserInfo.setUserName(userAccount);
        homeUserInfo.setDeleteFlag("F");
        List<HomeUserInfo> homeUserInfoes = homeUserInfoMapper.selectByCondition(homeUserInfo);
        if(homeUserInfoes==null){
            map.put("flag",false);
            map.put("msg","用户不存在!");
            return map;
        }
        //进行base64加密
        String userPasswordBase64 = "";
        try{
            userPasswordBase64 = AESUtil.encryptAES(userPassword);
        } catch (Exception e) {
            logger.error("首页用户登陆加密异常：" + e.getMessage());
            map.put("flag",false);
            map.put("msg","用户信息登陆异常!");
            return map;
        }
        if(homeUserInfoes.size()>1){
            map.put("flag",false);
            map.put("msg","用户信息异常!");
            return map;
        }
        homeUserInfo =  homeUserInfoes.get(0);
        if(!userPasswordBase64.equals(homeUserInfo.getUserPassword())){
            map.put("flag",false);
            map.put("msg","登陆密码不正确!");
            return map;
        }
        map.put("flag",true);
        map.put("userObj",homeUserInfo);
        return map;
    }

}
