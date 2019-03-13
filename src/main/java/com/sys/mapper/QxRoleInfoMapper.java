package com.sys.mapper;

import com.sys.pojo.QxRoleInfo;
import java.util.List;

public interface QxRoleInfoMapper {
    int deleteByPrimaryKey(String qrId);

    int insert(QxRoleInfo record);

    QxRoleInfo selectByPrimaryKey(String qrId);

    List<QxRoleInfo> selectAll();

    int updateByPrimaryKey(QxRoleInfo record);
}