package com.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sys.mapper.ParmMapper;
import com.sys.pojo.Parm;
import com.sys.pojo.RoleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/12 0012
 * @desc
 */
@Service
public class ParmService extends BaseService<Parm> {
    @Autowired
    ParmMapper parmMapper;

    /**
     * 分页查询
     * @param map
     * @return
     */
    public PageInfo<Parm> pageParm(Map map){

        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
                Integer.parseInt((String) map.get("rows")));
        List<Parm> list = parmMapper.selectByPage(map);
        return new PageInfo<Parm>(list);
    }

    /**
     *根据条件查询字典数量  字典编码或者名称
     * @param map
     * @return
     */
    public int parmCount(Map map){
        return parmMapper.parmCount(map);
    }
    /**
     * 查询除本身外相同名称对的数量
     * @param map
     * @return
     */
    public int countParmExceptSelf(Map map){
        return parmMapper.countParmExceptSelf(map);
    }
}
