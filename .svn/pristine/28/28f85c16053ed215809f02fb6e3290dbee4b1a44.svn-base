package com.sys.mapper;

import java.util.List;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/10 0010
 * @desc
 */
public interface IMapper<T> {
    int insert(T pojo);

    /**
     * 根据id进行更新
     * @param pojo
     * @return 返回更新的列数
     * @author hey
     * @version 1.00
     */
    int update(T pojo);

    /**
     * 根据id删除数据
     * @param id 数据的id值
     * @return 返回删除的数据条数
     * @author hey
     * @version 1.00
     */
    int delete(String id);

    /**
     * 以非空字段值作为查询条件进行查询
     * @param pojo
     * @return 查询到的结果集，以集合形式返回
     * @author hey
     * @version 1.00
     */
    List<T> select(T pojo);

    /**
     * 根据唯一字段查询实体
     * @param id
     * @return
     */
    T selectById(String id);

}
