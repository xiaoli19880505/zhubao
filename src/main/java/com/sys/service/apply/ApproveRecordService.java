package com.sys.service.apply;

import com.sys.mapper.apply.ApproveRecordMapper;
import com.sys.pojo.apply.ApproveRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApproveRecordService {

    @Autowired
    private ApproveRecordMapper approveRecordMapper;

    /**
     * 查询审核记录
     */
    public List<ApproveRecord> select(ApproveRecord approveRecord){
        return approveRecordMapper.select(approveRecord);
    }

}
