package com.sys.mapper.blagsh;


import com.sys.mapper.IMapper;
import com.sys.pojo.blagsh.BliveGongsDetail;

import java.util.List;

public interface BliveGongsDetailMapper extends IMapper<BliveGongsDetail> {

    int insertSelective(BliveGongsDetail record);

    int updateByPrimaryKeySelective(BliveGongsDetail record);

    List<BliveGongsDetail> selectByCondition(BliveGongsDetail record);

}