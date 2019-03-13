package com.sys.mapper.apply;

import com.sys.mapper.IMapper;
import com.sys.pojo.apply.ApplyUnit;

import java.util.Map;

public interface ApplyUnitMapper extends IMapper<ApplyUnit> {
    /**
     * 条件删除申请单位信息
     * @param map
     * @return
     */
    int deleteByMap(Map<String,Object> map);

    /**
     * 查询单位信息根据申请单号
     * @param aplid
     * @return
     */
    ApplyUnit findByApplyId(String aplid);
}