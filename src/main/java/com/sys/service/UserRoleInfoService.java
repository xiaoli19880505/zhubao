package com.sys.service;


import com.sys.common.CommonUtils;
import com.sys.mapper.UserInfoMapper;
import com.sys.mapper.UserRoleInfoMapper;
import com.sys.pojo.UserRoleInfo;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userRoleInfoService")
public class UserRoleInfoService  {

    @Autowired
    private UserRoleInfoMapper userRoleInfoMapper;

    /**
     * 删除用户判断是否有角色
     * @param userid
     * @return
     */
     public  int selectCountByUid(String userid){
         return userRoleInfoMapper.selectCountByUid(userid);
    }

    /**
     * 用户查询所属角色
     * @param userid
     * @return
     */
     public    List<UserRoleInfo> findRoleByUid(String userid){
        return userRoleInfoMapper.findRoleByUid(userid);
    }

    @Transactional
    public int saveUserRoleInfoByJs(String userid,String userArray){
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("userid",userid);
            //删除原来角色id为dutyid的数据
            userRoleInfoMapper.deleteUserRole(map);
            List<UserRoleInfo> list=new ArrayList<UserRoleInfo>();
            String[] array=userArray.split(",");
            for (String dutyid:array) {
                UserRoleInfo userRoleInfo=new UserRoleInfo();
                userRoleInfo.setUserdutyid(CommonUtils.getUUIDWith_());
                userRoleInfo.setDutyid(dutyid);
                userRoleInfo.setUserid(userid);
                list.add(userRoleInfo);
            }
            //添加
            userRoleInfoMapper.insertList(list);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }



    }



    /**
     * 保存修改的用户角色
     * @param dutyid 角色ID
     * @param userArray 用户ID数组
     * @return
     */
    @Transactional
    public int savaUserRoleInfo(String dutyid,String userArray){
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("dutyid",dutyid);
            //删除原来角色id为dutyid的数据
            userRoleInfoMapper.deleteUserRole(map);
            List<UserRoleInfo> list=new ArrayList<UserRoleInfo>();
            String[] array=userArray.split(",");
            for (String userid:array) {
                UserRoleInfo userRoleInfo=new UserRoleInfo();
                userRoleInfo.setUserdutyid(CommonUtils.getUUIDWith_());
                userRoleInfo.setDutyid(dutyid);
                userRoleInfo.setUserid(userid);
                list.add(userRoleInfo);
            }
            //添加
            userRoleInfoMapper.insertList(list);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

}
