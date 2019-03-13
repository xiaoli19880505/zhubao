package com.sys.controller.house;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.common.CommonUtils;
import com.sys.pojo.HisRent;
import com.sys.service.HisRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/11/22 0022
 * @desc
 */
@Controller
@RequestMapping("/hisRent")
public class HisRentController {
    @Autowired
    private HisRentService hisRentService;

    /**
     * 根据房屋id分页查询租赁历史记录
     * @param page
     * @param rows
     * @param fwid
     * @return
     */
    @RequestMapping("/findByFwId")
    @ResponseBody
    public Object findByFwId(String page,String rows,String fwid){
        if(page==null||page==""){
            page="1";
        }
        if(rows==null||rows==""){
            rows="20";
        }
        Map<String,Object> map=new HashMap();
        map.put("page",page);
        map.put("rows",rows);
        map.put("fwid",fwid);
        PageInfo<HisRent> histPageInfo=hisRentService.findByFwId(map);
//        map.remove("page");
//        map.remove("rows");
        map.put("rows",histPageInfo.getList());
        map.put("total",histPageInfo.getTotal());
        return map;
    }

    /**
     * 新增历史记录
     * @param request
     * @return
     */
    @RequestMapping("/addHisRent")
    @ResponseBody
    public Object addHisRent(HttpServletRequest request){
        String result="";
        String userid=request.getParameter("userid");
        String fwid=request.getParameter("fwid");
        String qysj=request.getParameter("qysj");
        String jzsj=request.getParameter("jzsj");
        String hisId = CommonUtils.getUUIDWith_();//生成申请单id
        HisRent hisRent=new HisRent();
        hisRent.setHisId(hisId);
        hisRent.setUserid(userid);
        hisRent.setFwId(fwid);
        hisRent.setQysj(qysj);
        hisRent.setJzsj(jzsj);
        int insertCount=hisRentService.insert(hisRent);
        if(insertCount>0){
            result="success";
        }else{
            result="false";
        }
        return result;
    }

    /**
     *
     * @param hisRent
     * @return
     */
    @RequestMapping("/updateHisRent")
    @ResponseBody
    public Object updateHisRent(HisRent hisRent){
        String result="";
        int updateCount=hisRentService.updateHisRent(hisRent);
        if(updateCount>0){
            result="修改成功";
        }else{
            result="修改失败";
        }
        return result;
    }


    /**
     *批量更新上房时间
     * @param
     * @return
     */
    @RequestMapping("/updateSFSJBySqid")
    @ResponseBody
    public Object updateSFSJBySqid(HttpServletRequest request){
        String result="";
        String jsonStr=request.getParameter("jsonStr");
        JSONArray jsonArray = JSONArray.parseArray(jsonStr);
        System.out.println("jsonStr   "+jsonStr);
        List<HisRent> list= Lists.newArrayList();
        for (int i = 0; i <jsonArray.size() ; i++) {
            HisRent hisRent=new HisRent();
            hisRent.setHisId(jsonArray.getJSONObject(i).getString("hisId"));
            hisRent.setSfsj(jsonArray.getJSONObject(i).getString("sfsj"));
            list.add(hisRent);
        }
        Map<String,Object> map = Maps.newHashMap();
        map.put("list",list);
        int updateCount=hisRentService.updateSFSJBySqid(map);
        if(updateCount>0){
            result="修改成功";
        }else{
            result="修改失败";
        }
        return result;
    }

}
