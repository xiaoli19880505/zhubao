package com.sys.mapper;

import com.sys.pojo.SourceHouse;

import java.util.List;
import java.util.Map;

public interface SourceHouseMapper extends IMapper<SourceHouse> {

    int saveSourceHouse(List<SourceHouse> list);

    int deleteByPrimaryKey(String shId);

    int insert(SourceHouse record);

    int insertSelective(SourceHouse record);

    SourceHouse selectByPrimaryKey(String shId);

    List<SourceHouse> selectByCondition(SourceHouse record);

    int updateByPrimaryKeySelective(SourceHouse record);

    int updateByPrimaryKey(SourceHouse record);

    /**
     * 查询待配给房源项目
     * @return
     */
    List<SourceHouse> findSourceHourses();

    /**
     * 配给房源后更新房源信息
     * @param list
     * @return
     */
    int updateSourceHourse(List<SourceHouse> list);

    /**
     * 房屋分配给申请人后，更新房屋申请人字段
     * @param map
     * @return
     */
    int updateSourceHouseApplyer(Map map);

    /**
     * 退房后更新
     * @param map
     * @return
     */
    int updateSourceHouseRetrunHouse(Map map);



    /**
     * 通过申请单id查询小区、楼栋、单元、房室
     * @param map
     * @return
     */
    Map<String,String> selectHouseInfoByMap(Map map);
}