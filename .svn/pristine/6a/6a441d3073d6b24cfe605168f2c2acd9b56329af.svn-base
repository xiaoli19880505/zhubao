package com.sys.mapper;

import com.sys.pojo.Parm;
import com.sys.pojo.RoleInfo;

import java.util.List;
import java.util.Map;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/12 0012
 * @desc
 */
public interface ParmMapper extends IMapper<Parm>{
    /**
     * 分页查询
     * @param map
     * @return
     */
    List<Parm> selectByPage(Map<String, Integer> map);

    /**
     * 根据条件查询条数  字典名或者编号
     * @param map
     * @return
     */
    int parmCount(Map map);

    /**
     * 查询除本身外相同名称对的数量
     * @param map
     * @return
     */
    int countParmExceptSelf(Map map);

    Parm selectBySetCode(Map map);
}
