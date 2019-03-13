package com.sys.mapper;

import com.sys.pojo.blagsh.BliveGongS;

import java.util.List;
import java.util.Map;

public interface ExcelMapper {

    /**
     * 诚信列表导出
     * @return
     */
    List<Map<String,Object>> findAllBliveGos(BliveGongS bliveGongS);
}
