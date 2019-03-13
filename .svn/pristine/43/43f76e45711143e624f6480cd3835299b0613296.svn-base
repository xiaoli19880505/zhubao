package com.sys.mapper;

import com.sys.pojo.QxInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QxInfoMapper {
    int delete(String qxId);

    int insert(QxInfo qxInfo);

    QxInfo selectByPrimaryKey(String qxId);


    List<QxInfo> selectAll();

    /**
     * 查询是否存在不同模块下的角色
     * @param qxname
     * @param qxname
     * @return
     */
    int findQxinfoByNameOrMid(@Param("qxname") String qxname);

    /**
     * 查询全部权限
     * @param map
     * @return
     */
    List<QxInfo> findAllQxInfo(Map map);

    /**
     * 根据菜单id查询权限信息条数
     * @param map
     * @return
     */
    int findQxInfoByMeId(Map map);

    int update(QxInfo qxInfo);
}