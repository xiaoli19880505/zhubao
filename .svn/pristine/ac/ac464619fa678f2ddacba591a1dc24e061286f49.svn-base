package com.sys.mapper.contract;


import com.sys.mapper.IMapper;
import com.sys.pojo.contract.ContractDetail;

import java.util.List;
import java.util.Map;

public interface ContractDetailMapper extends IMapper<ContractDetail> {

    int insertSelective(ContractDetail record);

    int updateBySelective(ContractDetail record);

    List<ContractDetail> selectByCondition(ContractDetail record);

    /*条件查询合同实体*/
    ContractDetail selectContractDetailbyMap(Map map);

}