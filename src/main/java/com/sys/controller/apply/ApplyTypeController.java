package com.sys.controller.apply;

import com.google.common.collect.Maps;
import com.sys.common.PropertiesUtil;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.apply.ApplyChange;
import com.sys.service.apply.ApplyChangeService;
import com.sys.service.apply.ApplyFamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @Desc:类型跳转
 * @Author:wangli
 * @CreateTime:18:09 2018/11/14
 */
@Controller
@RequestMapping("toappinform")
public class ApplyTypeController {

    @Autowired
    private ApplyChangeService applyChangeService;

    @Autowired
    private ApplyFamilyMemberService applyFamilyMemberService;

    /**
     * 跳转到申请须知页面
     * @param applytype
     * @return
     */
    @RequestMapping("/toapplytype")
    public ModelAndView toApplyType(int applytype, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("applytype",applytype);
        /*如果是经适房的申请，先判断在之前两年内是否存在退房的经适房的记录;如果存在，则不得申请*/
        if(applytype==1){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.YEAR,-2);
            ApplyUserinfo applyUserinfo = (ApplyUserinfo)request.getSession().getAttribute("applyUserinfo");

            Map<String, Object> conditionMap = Maps.newHashMap();
            conditionMap.put("sfzh",applyUserinfo.getSfzh());
            conditionMap.put("approveExist","no");
            int oldDataCount = this.applyFamilyMemberService.selectCountByMap(conditionMap);
            /*属于老数据用户，需要判断是否存在取消保障的经适房；
            * 如果存在，则需要判断退房时间是否超过两年，目前还没有依据*/
            if(oldDataCount>0){

            }
            /*设置查询条件*/
            //Map<String,Object> conditionMap = Maps.newHashMap();
            conditionMap.put("date",calendar.getTime());//查询日期
            conditionMap.put("sfzh",applyUserinfo.getSfzh());//身份证号
            conditionMap.put("acType",7);//记录类别;7为退房
            conditionMap.put("acApplyType",PropertiesUtil.getApplyTypeProperties("jingsf"));//申请业务类别
            int count = applyChangeService.selectCountByMap(conditionMap);//查询个数
            //判断是否存在两年内的年审退房记录
            if(count>0){
                modelAndView.addObject("message","存在您两年内的经适房退房记录");
                modelAndView.setViewName("applicationForm/message");
            }else{
                modelAndView.setViewName("applicationForm/appguide");
            }
        }else{
            modelAndView.setViewName("applicationForm/appguide");
        }
        return modelAndView;
    }
}