package com.sys.mapper;

import com.sys.mapper.IMapper;
import com.sys.pojo.ApplyUserinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ApplyUserinfoMapper extends IMapper<ApplyUserinfo> {

    ApplyUserinfo selectUserInfo(Map map);



    /**
     * 查询通过注册用户
     * @param map
     * @return
     */
    List<ApplyUserinfo> selectAll(Map map);

    int updateByPrimaryKey(ApplyUserinfo record);

    int updatePwd(Map map);

    /**
     * 查询未激活的用户列表
     * @param map
     * @return
     */
    List<ApplyUserinfo> selectNoActiv(Map map);

    /**
     * 根据用户id更新用户状态
     * @param map
     * @return
     */
    int updateUserState(Map map);
}