package com.sys.mapper.serialnum;


import com.sys.mapper.IMapper;
import com.sys.pojo.serialnum.SerialNumHead;

public interface SerialNumHeadMapper extends IMapper<SerialNumHead> {
    int insertSelective(SerialNumHead record);

    int updateSelective(SerialNumHead record);

    SerialNumHead selectBySNCode(String snCode);
}