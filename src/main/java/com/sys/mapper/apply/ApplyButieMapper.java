package com.sys.mapper.apply;

import com.sys.mapper.IMapper;
import com.sys.pojo.apply.ApplyButie;

import java.util.List;
import java.util.Map;

public interface ApplyButieMapper extends IMapper<ApplyButie> {

    ApplyButie selectByMap(Map<String,Object> map);
    int updateApLC(Map map);
    int updateApZT(Map map);
    List<ApplyButie> selectByCondition(ApplyButie applyButie);
}