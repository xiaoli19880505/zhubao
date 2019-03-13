package com.sys.mapper.apply;

import com.sys.mapper.IMapper;
import com.sys.pojo.apply.ApplyFamilyMember;

import java.util.List;
import java.util.Map;

public interface ApplyFamilyMemberMapper extends IMapper<ApplyFamilyMember> {
    /**
     * 根据申请id查询家庭成员
     * @param applyid
     * @return
     */
    List<ApplyFamilyMember> findByApplyId(String applyid);

    /**
     * 根据申请ID删除家庭成员信息
     * @param applyid
     * @return
     */
    int deleteFamilyMember(String applyid);

    /**
     * 获取申请人信息（家庭成员关系为空）
     * @param afmSqid
     * @return
     */
    ApplyFamilyMember selectForApplicationPerson(String afmSqid);

    /*条件查询家庭成员列表*/
    List<ApplyFamilyMember> selectListByMap(Map map);

    /*条件查询家庭成员个数*/
    int selectCountByMap(Map map);

    /*条件查询部分申请单信息*/
    List<Map<String,Object>> selectApplyMap(Map map);

    int updateMemberBatch(List<ApplyFamilyMember> list);


}