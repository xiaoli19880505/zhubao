package com.sys.mapper;

import com.sys.pojo.HomeUserInfo;

import java.util.List;

public interface HomeUserInfoMapper extends  IMapper<HomeUserInfo> {

    List<HomeUserInfo> selectByCondition(HomeUserInfo record);
}