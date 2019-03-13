package com.sys.mapper.apply;

import com.sys.mapper.IMapper;
import com.sys.pojo.apply.ApplyForForeign;

import java.util.List;
import java.util.Map;

public interface ApplyForForeignMapper extends IMapper<ApplyForForeign> {
    ApplyForForeign selectByMap(Map<String,Object> map);
    int updateApLC(Map map);

    /**
     * 保障房备案管理查询--外来务工
     * @param applyForForeign
     * @return
     */
    List<ApplyForForeign> selectByCondition(ApplyForForeign applyForForeign);

    /**
     * 老数据的查询
     * @param map
     * @return
     */
    List<Map<String,Object>> selectOldListByMap(Map map);

}