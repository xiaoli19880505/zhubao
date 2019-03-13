package com.sys.controller.apply;

import com.github.pagehelper.PageInfo;
import com.sys.common.StringUtils;
import com.sys.pojo.apply.ApplyChange;
import com.sys.service.apply.ApplyChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/11/28 0028
 * @desc
 */
@Controller
@RequestMapping("/applyChange")
public class ApplyChangeController {
    @Autowired
    private ApplyChangeService applyChangeService;

    /**
     * 跳转到申请变更记录查询页面
     * @param request
     * @return
     */
    @RequestMapping("toApplyChange")
    public String toApplyChange(HttpServletRequest request){
        String sqbh = request.getParameter("sqbh");
        if(sqbh!=null){
            request.setAttribute("sqbh",sqbh);
        }
        return "applyChange/applyChangeList";
    }

    /**
     * 申请变更记录分页查询
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @RequestMapping("/getApplyChangeList")
    @ResponseBody
    public Object getApplyChangeList(String page, String rows, HttpServletRequest request){
        String username=request.getParameter("username");
        String acApplyType=request.getParameter("acApplyType");
        String acSqbh=request.getParameter("acSqbh");
        String acType=request.getParameter("acType");
        String beginTime=request.getParameter("beginTime");
        String endTime=request.getParameter("endTime");


        if(page==null||page==""){
            page="1";
        }
        if(rows==null||rows==""){
            rows="20";
        }
        Map<String,Object> map=new HashMap();
        map.put("page",page);
        map.put("rows",rows);

        if(username!=null && !username.equals("")){
            map.put("username",username);
        }
        if(acApplyType!=null && !acApplyType.equals("")){
            map.put("acApplyType",acApplyType);
        }
        if(acSqbh!=null && !acSqbh.equals("")){
            map.put("acSqbh",acSqbh);
        }
        if(acType!=null && !acType.equals("")){
            map.put("acType",acType);
        }
        if(!StringUtils.isEmpty(beginTime)){
            map.put("beginTime",beginTime);
        }
        if(!StringUtils.isEmpty(endTime)){
            map.put("endTime",endTime);
        }

        PageInfo<ApplyChange> applyChanegPageInfo=applyChangeService.selectByMap(map);
        map.remove("page");
        map.remove("rows");
        map.put("rows",applyChanegPageInfo.getList());
        map.put("total",applyChanegPageInfo.getTotal());
        return map;
    }
}
