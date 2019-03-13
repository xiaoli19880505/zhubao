package com.sys.mapper.apply;

import com.sys.mapper.IMapper;
import com.sys.pojo.apply.Apply;
import com.sys.pojo.lotnum.LotNum;

import java.util.List;
import java.util.Map;

public interface ApplyMapper extends IMapper<Apply>{
    Apply selectByMap(Map<String,Object> map);
    int updateApLC(Map map);
    List<Apply> selectByCondition(Apply apply);

    /**
     * 查询实体
     * @param map
     * @return
     */
    Apply selectEntityByMap(Map<String,Object> map);

    /**
     * 批量更新申请单状态（用于摇号，更新摇中LC状态为3）
     * @param list
     * @return
     */
    int updateApplyLcBatchByList(List<LotNum> list);
    /**
     * 批量更新申请单状态（用于摇号，更新摇中ZT状态为3）
     * @param list
     * @return
     */
    int updateApplyZtBatchByList(List<Apply> list);

}