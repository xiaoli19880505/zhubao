package com.sys.mapper.serialnum;


import com.sys.mapper.IMapper;
import com.sys.pojo.serialnum.SerialNumLine;

import java.util.List;

public interface SerialNumLineMapper extends IMapper<SerialNumLine> {


    int insertSelective(SerialNumLine record);

    int updateSelective(SerialNumLine record);

    SerialNumLine selectByFKIdAndNowDate(SerialNumLine record);
}