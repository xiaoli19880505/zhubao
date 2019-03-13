package com.sys.mapper.blagsh;

import com.sys.mapper.IMapper;
import com.sys.pojo.blagsh.BliveGongS;
import java.util.List;
import java.util.Map;

/**
 * 条件查询
 */
public interface BliveGongSMapper extends IMapper<BliveGongS>{
    /**
     * 条件查询公示条目
     * @param map
     * @return
     */
    List<BliveGongS> selectListByMap(Map map);

    /**
     * 诚信列表信息查询
     * @param bliveGongS
     * @return
     */
    List<BliveGongS> selectForCXLB(BliveGongS bliveGongS);
}