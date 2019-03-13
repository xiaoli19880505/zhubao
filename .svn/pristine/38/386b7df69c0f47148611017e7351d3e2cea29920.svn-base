package com.sys.mapper.apply;

import com.sys.mapper.IMapper;
import com.sys.pojo.apply.ApplyNs;
import java.util.List;
import java.util.Map;

public interface ApplyNsMapper extends IMapper<ApplyNs>{

    /**
     * 很据唯一条件查询年审信息
     * @param map
     * @return
     */
    ApplyNs selectByMap(Map map);

    /**
     * 查询待年审的申请表，分页查询
     * @return
     */
    List<Map> selectNsUserApp(Map map);

    /**
     * 查询待年审的申请表数目
     * @return
     */
    int selectNsUserAppCount(Map map);
}