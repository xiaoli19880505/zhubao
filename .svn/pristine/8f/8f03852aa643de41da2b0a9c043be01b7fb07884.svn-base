package com.sys.service.sysma;

import com.sys.mapper.HolidayMapper;
import com.sys.pojo.Holiday;
import com.sys.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HolidayService extends BaseService<Holiday> {

    @Resource
    private HolidayMapper holidayMapper;

    /**
     * 分页查询实体
     * @param map
     * @return
     */
    public List<Holiday> selectByMap(Map map){
        return holidayMapper.selectByMap(map);
    }

    /**
     * 条件查询数目
     * @param map
     * @return
     */
    public int selectCountMap(Map map){
        return this.holidayMapper.selectCountMap(map);
    }
}
