package com.sys.mapper;

import com.sys.pojo.Message;

import java.util.List;
import java.util.Map;

public interface MessageMapper extends IMapper<Message>{
    /**
     * 查询条件下消息数目
      * @param map
     * @return
     */
    int selectCountByMap(Map map);

    /**
     * 批量更新信息实体
     * @param list
     * @return
     */
    int updateMessBaBatch(List<Message> list);

    /**
     * 条件查询消息列表
     * @param map
     * @return
     */
    List<Message> findMessageList(Map map);
}