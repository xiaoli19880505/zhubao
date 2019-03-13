package com.sys.service.apply;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sys.mapper.ApplyUserInfoFormMapper;
import com.sys.mapper.ApplyUserinfoMapper;
import com.sys.pojo.ApplyUserInfoForm;
import com.sys.pojo.extend.DataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("applyUserInfoFormService")
public class ApplyUserInfoFormService {

    @Autowired
    private ApplyUserInfoFormMapper applyUserInfoFormMapper;

    @Autowired
    private ApplyUserInfoFormService applyUserInfoFormService;


     public PageInfo<ApplyUserInfoForm> findAllFormByUid(Map map){
         PageHelper.startPage(Integer.parseInt((String) map.get("page")),
                 Integer.parseInt((String) map.get("rows")));
         List<ApplyUserInfoForm> list=this.applyUserInfoFormMapper.findAllFormByUid(map);
         for (ApplyUserInfoForm apply:list) {
             ApplyUserInfoForm applyUserInfoForm=new ApplyUserInfoForm();
             applyUserInfoForm.setUifRead("1");
             applyUserInfoForm.setUiFid(apply.getUiFid());
             this.applyUserInfoFormMapper.update(applyUserInfoForm);
         }
         return new  PageInfo<ApplyUserInfoForm>(list);
    }

    /**
     * 条件查询通知数目
     * @param map
     * @return
     */
    public int findInformCountByMap(Map map){
        return this.applyUserInfoFormMapper.findInformCountByMap(map);
    }


}
