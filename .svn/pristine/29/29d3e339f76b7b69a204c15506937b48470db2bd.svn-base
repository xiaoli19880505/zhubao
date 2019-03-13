package com.sys.mapper.apply;

import com.sys.mapper.IMapper;
import com.sys.pojo.apply.ApplyFamilyHouse;

import java.util.Map;

public interface ApplyFamilyHouseMapper extends IMapper<ApplyFamilyHouse> {
    /**
     * 根据申请id获取家庭住房信息
     * @param
     * @return
     */
    ApplyFamilyHouse findByApplyId(String afhSqid);
    /**
     * 根据申请id修改家庭住房信息
     * @param
     * @return
     */
    int updateHouseByApplyId(ApplyFamilyHouse applyFamilyHouse);

    /**
     * 条件删除申请家庭住房信息
     * @param map
     * @return
     */
    int deleteByMap(Map<String,Object> map);
}