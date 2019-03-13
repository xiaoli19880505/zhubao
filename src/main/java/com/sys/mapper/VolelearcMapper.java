package com.sys.mapper;

import com.sys.pojo.Volelearc;
import java.util.List;
import java.util.Map;

public interface VolelearcMapper extends IMapper<Volelearc>{
    /**
     * 根据申请id查询附件
     * @param applyid
     * @return
     */
    List<Volelearc> findByAppyId(String applyid);

    /**
     * 条件查询附件表
     * @param map
     * @return
     */
    List<Volelearc> getByMap(Map map);
}