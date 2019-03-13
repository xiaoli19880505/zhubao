package com.sys.mapper.lotnum;

import com.sys.mapper.IMapper;
import com.sys.pojo.lotnum.LotNum;

import java.util.List;
import java.util.Map;

public interface LotNumMapper extends IMapper<LotNum> {
    /**
     * 条件查询摇号数据的条目
     * @param map
     * @return
     */
    int selectByMap(Map map);

    /**
     * 查询lotnum实体，order by num
     * @return
     */
    LotNum selectOneByOreder(Map map);

    /**
     * 查询条件下num最大的摇号
     * @param map
     * @return
     */
    String selectMaxNumByMap(Map map);

    /**
     * 批量更新摇号信息
     * @param list
     * @return
     */
    int updateLotList(List<LotNum> list);

}