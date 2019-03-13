package com.sys.service;

import com.sys.mapper.VolelearcDtlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/29 0029
 * @desc
 */
@Service
public class VolelearcDtlService {
    @Autowired
    private VolelearcDtlMapper volelearcDtlMapper;

    /**
     * 根据图片详细ID删除
     * @param id
     * @return
     */
    public int deleteDetail(String id){
        return  volelearcDtlMapper.delete(id);
    }

}
