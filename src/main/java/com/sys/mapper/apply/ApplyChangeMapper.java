package com.sys.mapper.apply;

import com.sys.mapper.IMapper;
import com.sys.pojo.apply.ApplyChange;

import java.util.List;
import java.util.Map;

public interface ApplyChangeMapper extends IMapper<ApplyChange> {
    /**
     * 条件查询操作记录变更数目
     * @param map
     * @return
     */
    int selectCountByMap(Map map);

    /**
     * 条件查询操作记录变更条目
     * @param map
     * @return
     */
    List<ApplyChange> selectByMap(Map map);

}