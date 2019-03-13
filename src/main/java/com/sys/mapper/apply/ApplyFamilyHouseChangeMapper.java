package com.sys.mapper.apply;

import com.sys.mapper.IMapper;
import com.sys.pojo.apply.ApplyFamilyHouse;
import com.sys.pojo.apply.ApplyFamilyHouseChange;
import java.util.List;

public interface ApplyFamilyHouseChangeMapper extends IMapper<ApplyFamilyHouseChange>{
    /**
     * 根据申请id获取家庭住房变更信息
     * @param
     * @return
     */
    ApplyFamilyHouseChange findByApplyId(String afhSqid);
}