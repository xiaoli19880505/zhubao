package com.sys.service.apply;

import com.sys.mapper.apply.ApplyUnitMapper;
import com.sys.pojo.apply.ApplyUnit;
import com.sys.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyUnitService extends BaseService<ApplyUnit> {

    @Autowired
    private ApplyUnitMapper applyUnitMapper;

    /**
     * 根据申请单ID获取单位信息
     * @param aplid
     * @return
     */
    public ApplyUnit findByApplyId(String aplid){
        return applyUnitMapper.findByApplyId(aplid);
    }

}
