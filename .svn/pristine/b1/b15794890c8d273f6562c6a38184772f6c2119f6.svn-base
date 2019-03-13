package com.sys.common.dataconver;

import com.sys.common.StringUtils;
import com.sys.pojo.UserInfo;
import org.apache.commons.collections4.map.LinkedMap;

/**
 * @Desc:desc
 * @Author:wangli
 * @CreateTime:13:59 2018/11/13
 */
public class BaseInfoDataConvertor {
    /**
     * 将审核的用户单位信息返回
     * @param userInfo //用户信息
     */
    public static String convertUserUnit(UserInfo userInfo){
        String ssj=userInfo.getSsj();//获取用户街道
        String ssq = userInfo.getSsq();//获取用户所在区
        if(StringUtils.isEmpty(ssj)){
            /*如果街道为空，则显示市住保或者区住保*/
            if(ssq.equals("0")){
                //lmap.put(key,"市住保");
                return "市住保";
            }else{
                return userInfo.getParmItemssq().getPiItemname()+"住保";
            }
        }else{
            /*如果街道不为空，则显示街道办事处*/
            return  userInfo.getParmItemssq().getPiItemname()
                    +userInfo.getParmItemssjd().getPiItemname()+"街道办事处";
        }
    }
}