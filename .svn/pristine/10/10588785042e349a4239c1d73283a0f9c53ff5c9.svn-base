package com.sys.service.apply;

import com.sys.mapper.apply.ApplyFamilyMemberMapper;
import com.sys.pojo.apply.ApplyFamilyMember;
import com.sys.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApplyFamilyMemberService extends BaseService<ApplyFamilyMember> {

    @Autowired
    private ApplyFamilyMemberMapper applyFamilyMemberMapper;


    /**
     * 获取申请人信息
     * @param afmSqid 申请ID
     * @return
     */
    public ApplyFamilyMember getApplication(String afmSqid){
        return applyFamilyMemberMapper.selectForApplicationPerson(afmSqid);
    }

    /*条件查询家庭成员列表*/
     public List<ApplyFamilyMember> selectListByMap(Map map){
         return applyFamilyMemberMapper.selectListByMap(map);
     }

    /*条件查询家庭成员个数*/
    public int selectCountByMap(Map map){
        return applyFamilyMemberMapper.selectCountByMap(map);
    }

    /*条件查询部分申请单信息*/
    public List<Map<String,Object>> selectApplyMap(Map map){
        return applyFamilyMemberMapper.selectApplyMap(map);
    }

    /**
     * 批量更新家庭成员信息
     * @param list
     * @return
     */
    int updateMemberBatch(List<ApplyFamilyMember> list){
        return this.applyFamilyMemberMapper.updateMemberBatch(list);
    }

}
