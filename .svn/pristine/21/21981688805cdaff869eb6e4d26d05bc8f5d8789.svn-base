package com.sys.mapper;

import com.sys.pojo.BuidInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BuidInfoMapper extends  IMapper<BuidInfo> {

    /**
     * 根据项目id查询
     * @return
     */
    List<BuidInfo>  findBuildInfoByIid(Map map);

    void insertBuildlist(List<BuidInfo> list);


    int findBuildNameByItemId(@Param("name") String name,@Param("itemid") String itemid);

    /**
     * 根据项目id查询存在待配给房屋的楼号
     * @return
     */
    List<BuidInfo>  findBuildInfoByMap(Map map);

    /**
     * 根据查询某个项目下楼栋的数量
     * @param itemid
     * @return
     */
    int findCountByItemId(String itemid);

}