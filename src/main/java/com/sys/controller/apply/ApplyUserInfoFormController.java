package com.sys.controller.apply;

import com.github.pagehelper.PageInfo;
import com.sys.common.StringUtils;
import com.sys.pojo.ApplyUserInfoForm;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.extend.DataGridResult;
import com.sys.service.ApplyUserinfoService;
import com.sys.service.apply.ApplyUserInfoFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/InfoForm")
public class ApplyUserInfoFormController {

    @Autowired
    private ApplyUserInfoFormService applyUserInfoFormService;


    @RequestMapping("/findAllFormByUid")
    @ResponseBody
    public Object findAllFormByUid(String page, String rows,String uifRead,HttpServletRequest request){
        ApplyUserinfo applyUserinfo=(ApplyUserinfo)request.getSession().getAttribute("applyUserinfo");
        if (page=="" || page==null){
            page="1";
        }
        Map<String,Object> map=new HashMap<String, Object>();
        Map<String,Object> map1=new HashMap();
        map.put("page",page);
        map.put("uifRead",uifRead);
        map.put("rows",rows);
        map.put("userid",applyUserinfo.getUserid());
        PageInfo<ApplyUserInfoForm> applyUserInfoFormPageInfo=this.applyUserInfoFormService.findAllFormByUid(map);
        map1.put("rows",applyUserInfoFormPageInfo.getList());
        map1.put("total",applyUserInfoFormPageInfo.getTotal());
        return  map1;
    }

}
