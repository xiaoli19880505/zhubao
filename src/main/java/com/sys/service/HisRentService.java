package com.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sys.mapper.HisRentMapper;
import com.sys.pojo.HisRent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/11/22 0022
 * @desc
 */
@Service
public class HisRentService extends BaseService<HisRent>{
    @Autowired
    private HisRentMapper hisRentMapper;

    public PageInfo<HisRent> findByFwId(Map map){
        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
                Integer.parseInt((String) map.get("rows")));
        List<HisRent> list=hisRentMapper.findByFwId(map);
        return new PageInfo<HisRent>(list);
    }

    public int updateHisRent(HisRent hisRent){
        return hisRentMapper.updateByPrimaryKeySelective(hisRent);
    }

    /**
     * 根据申请单id修改退时间
     * @param map
     * @return
     */
    public int updateBySqid(Map map){
        return hisRentMapper.updateBySqid(map);
    }

    public int updateSFSJBySqid(Map map){
        try {
            hisRentMapper.updateSFSJBySqid(map);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
