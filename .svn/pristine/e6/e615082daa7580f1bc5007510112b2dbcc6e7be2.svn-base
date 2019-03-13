package com.sys.mapper;

import com.sys.pojo.FactMapping;

import java.util.List;
import java.util.Map;

public interface FactMappingMapper extends  IMapper<FactMapping> {

    /**
     * 根据楼盘查询
     * @param map
     * @return
     */
    List<FactMapping> findFactByItemid(Map map);


    void  insertFactList(List<FactMapping> list);

    int findFactMappingByName(String buname);

    /**
     * 根据楼栋查询房屋数量
     * @param buid
     * @return
     */
    int findFactMappingByBuId(String buid);

    /**
     * 房源分配类型查询
     * @param map
     * @return
     */
    List<FactMapping> findFactlxByBid(Map map);


    /**
     * 查询配给房屋信息
     * @param map
     * @return
     */
    List<FactMapping> findFactByBuildingInfoId(Map map);

    /**
     * 查询待分配房屋信息
     * @param map
     * @return
     */
    List<FactMapping> findFactByMap(Map map);

    /**
     * 根据id批量删除
     * @param list
     * @return
     */
    int deleteByList(List<String> list);

    /**
     * 根据申请单号查询分配的房屋 用于展示申请人已经分了哪些房屋
     * 在调房时用到了
     * @param map
     * @return
     */
    List<FactMapping> findFactByApplyid(Map map);

    /*查询用户申请的房源数量*/
    int findFactCountByApplyid(Map map);

    /**
     * 根据房屋id查询 用于打印选房确认单
     * @param map 房屋id list
     * @return
     */
    List<FactMapping> findFactByFwId(Map map);

    /**
     * 根据申请单查询所有分配给当前申请单的房源
     * @param factMapping
     * @return
     */
    List<FactMapping> getFactMappingBySqId(FactMapping factMapping);

}