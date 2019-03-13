package com.sys.service.apply;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sys.mapper.apply.ApplyChangeMapper;
import com.sys.pojo.apply.ApplyChange;
import com.sys.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApplyChangeService extends BaseService<ApplyChange> {

    @Autowired
    private ApplyChangeMapper applyChangeMapper;

    /**
     * 条件查询操作记录变更数目
     * @param map
     * @return
     */
    public int selectCountByMap(Map map){
        return applyChangeMapper.selectCountByMap(map);
    }

    /**
     * 条件查询操作记录变更条目
     * @param map
     * @return
     */
    public PageInfo<ApplyChange> selectByMap(Map map){
        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
                Integer.parseInt((String) map.get("rows")));
        List<ApplyChange> list=applyChangeMapper.selectByMap(map);
        return new PageInfo<ApplyChange>(list);
    }
}
