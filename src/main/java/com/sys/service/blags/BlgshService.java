package com.sys.service.blags;

import com.sys.mapper.blagsh.BlgshMapper;
import com.sys.pojo.blagsh.Blgsh;
import com.sys.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Desc:失信公示审核service
 * @Author:wangli
 * @CreateTime:10:58 2018/10/27
 */
@Service
public class BlgshService extends BaseService<Blgsh>{

    @Autowired
    private BlgshMapper blgshMapper;

    public Blgsh selectByCondition(Blgsh blgsh){
        return blgshMapper.selectByCondition(blgsh);
    }

}