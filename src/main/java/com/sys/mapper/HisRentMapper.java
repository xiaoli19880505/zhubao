package com.sys.mapper;

import com.sys.pojo.HisRent;

import java.util.List;
import java.util.Map;

public interface HisRentMapper extends IMapper<HisRent> {
    int deleteByPrimaryKey(String hisId);

    int insert(HisRent record);

    int insertSelective(HisRent record);

    HisRent selectByPrimaryKey(String hisId);

    int updateByPrimaryKeySelective(HisRent record);

    int updateByPrimaryKey(HisRent record);

    /**
     * 根据房屋id查询历史租赁 分页
     * @param
     * @return
     */
    List<HisRent> findByFwId(Map map);

    int updateBySqid(Map map);

    /**
     * 根据申请id批量更新上房时间
     * @param map
     * @return
     */
    int updateSFSJBySqid(Map map);
}