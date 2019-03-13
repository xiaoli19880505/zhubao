package com.sys.controller.house;

import com.sys.common.CommonUtils;
import com.sys.common.PropertiesUtil;
import com.sys.common.dataconver.BaseInfoDataConvertor;
import com.sys.mapper.SourceHouseMapper;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.SourceHouse;
import com.sys.pojo.UserInfo;
import com.sys.pojo.apply.ApplyChange;
import com.sys.pojo.apply.Approve;
import com.sys.service.ApplyUserinfoService;
import com.sys.service.SourceHouseService;
import com.sys.service.apply.*;
import com.sys.service.common.MessageAndFormService;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/22 0022
 * @desc
 */
@Controller
@RequestMapping("/sourceHouse")
public class SourceHouseController {
    @Autowired
    private SourceHouseService sourceHouseService;
    @Autowired
    private MessageAndFormService messageAndFormService;
    @Autowired
    private ApplyUserinfoService applyUserinfoService;
    @Autowired
    private ApproveService approveService;
    @Autowired
    private ApplyChangeService applyChangeService;

    /**
     *查询待配给房源项目
     * @return
     */
    @RequestMapping("/findSourceHouses")
    @ResponseBody
    public Object findSourceHouses(){
        List<SourceHouse> list=sourceHouseService.findSourceHourses();
        return list;
    }

    /**
     * 房屋配给  --更新房屋信息
     * @param ssq
     * @return
     */
    @RequestMapping("/updateHouses")
    @ResponseBody
    public Object updateHouses(String ssq,String houseid){
        String result="";
        int updateCount=sourceHouseService.updateSourceHouse(ssq,houseid);
        if(updateCount>0){
            result="配给成功";
        }else{
            result="配给失败";
        }
        return result;
    }

    @RequestMapping("/saveSourceHouse")
    @ResponseBody
    public Object saveSourceHouse(String xmid,String lpid,String fwidlist,String fylx){
        String  result="";
        Integer count=this.sourceHouseService.saveSourceHouse(xmid,lpid,fwidlist,fylx);
        if (count>0){
            result="分配成功";
        }else{
            result ="分配失败";
        }
        return result;

    }

    /**
     * 房源分配
     * @param applyid
     * @param houseid
     * @param aptype
     * @param userid
     * @param tel
     * @param request
     * @return
     */
    @RequestMapping("/updateHouseFenPei")
    @ResponseBody
    public Object updateHouseFenPei(String applyid,String houseid,String aptype,String userid,String tel,HttpServletRequest request){
        String result="";
        LinkedMap<String,String> lmap = new LinkedMap<String, String>();
        int updateCount=this.sourceHouseService.updateSourceHouseApplyer(applyid,houseid,aptype);
        if (updateCount>0){
            result="分配成功";
            UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
            ApplyUserinfo applyUserinfo = applyUserinfoService.selectById(userid);
            Approve approve=approveService.findByApplyId(applyid);
            lmap.put("username",applyUserinfo.getUsername());//申请用户姓名
            lmap.put("aptype",aptype);//申请房源类型
            lmap.put("aplbh",approve.getAplbh());//申请编号
            lmap.put("linktel",applyUserinfo.getLinktel());//申请编号
            lmap.put("result",result);//申请编号
            //向申请变更表插入数据
            ApplyChange applyChange=new ApplyChange();
            String acId = CommonUtils.getUUIDWith_();//id
            applyChange.setAcId(acId);
            applyChange.setAcApplyType(aptype);
            applyChange.setAcTime(new Date());
            applyChange.setAcSqid(applyid);
            applyChange.setAcType("4");
            applyChange.setAcSqbh(approve.getAplbh());
            applyChange.setAcUserid(userInfo.getUserid());
            applyChangeService.insert(applyChange);

        }else{
            result ="分配失败";
            lmap.put("result",result);//申请编号
        }
        return lmap;
    }

    /**
     * 公租房打印审批表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getReturnHouseFile")
    @ResponseBody
    public Object getReturnHouseFile(HttpServletRequest request, HttpServletResponse response){
        String applyid = request.getParameter("applyid");//申请类别
        try {
            String result = sourceHouseService.getReturnHpuseFile(applyid,request, response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "打印审批表失败!";
        }
    }

    /**
     * 经适房打印审批表
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/getJSFHouseFile")
    @ResponseBody
    public Object getJSFHouseFile(HttpServletRequest request){
        String applyid = request.getParameter("applyid");//申请单id
        try {
            String result = sourceHouseService.getJSFHouseFile(applyid,request);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "打印审批表失败!";
        }
    }

    /**
     * 经打印选房确认单
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/getConfirmFile")
    @ResponseBody
    public Object getConfirmFile(HttpServletRequest request){
        try {
            String result = sourceHouseService.getConfirmFile(request);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "获取确认单失败!";
        }
    }




    /**
     * 退房
     * @param atype
     * @param applyid
     * @param acSqbh
     * @param request
     * @return
     */
    @RequestMapping("/updateReturnHouse")
    @ResponseBody
    public Object updateReturnHouse(String atype,String applyid,String acSqbh,HttpServletRequest request){
        String result="";
        /**
         * 更新申请记录表流程状态为6 退房，sourcehouse执行软删除操作[SH_FLAG=0]
         */
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        int updateResult=sourceHouseService.updateReturnHouse(atype,applyid,acSqbh,userInfo.getUserid(),request);
        if(updateResult>0){
            result="退房成功";
        }else{
            result="退房失败";
        }
        return result;
    }

    /**
     * 换房
     * @param applyid
     * @param houseid
     * @param aptype
     * @param userid
     * @param tel
     * @param request
     * @return
     */
    @RequestMapping("/updateHouseReset")
    @ResponseBody
    public Object updateHouseReset(String applyid,String houseid,String aptype,String userid,String tel,HttpServletRequest request){
        String result="";
        LinkedMap<String,String> lmap = new LinkedMap<String, String>();
        int updateCount=this.sourceHouseService.updateSourceHouseByApplyid(applyid,houseid);
        if (updateCount>0){
            result="分配成功";
              UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
            ApplyUserinfo applyUserinfo = applyUserinfoService.selectById(userid);
            Approve approve=approveService.findByApplyId(applyid);

            lmap.put("username",applyUserinfo.getUsername());//申请用户姓名
            lmap.put("aptype",aptype);//申请房源类型
            lmap.put("aplbh",approve.getAplbh());//申请编号
            lmap.put("linktel",applyUserinfo.getLinktel());//申请编号
            lmap.put("result",result);//申请编号

            //向申请变更表插入数据
            ApplyChange applyChange=new ApplyChange();
            String acId = CommonUtils.getUUIDWith_();//id
            applyChange.setAcId(acId);
            applyChange.setAcApplyType(aptype);
            applyChange.setAcTime(new Date());
            applyChange.setAcSqid(applyid);
            applyChange.setAcType("5");
            applyChange.setAcSqbh(approve.getAplbh());
            applyChange.setAcUserid(userInfo.getUserid());
            applyChangeService.insert(applyChange);

        }else{
            result ="分配失败";
            lmap.put("result",result);//申请编号
        }
        return lmap;
    }

}
