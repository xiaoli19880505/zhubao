package com.sys.controller.apply;

import com.github.pagehelper.PageInfo;
import com.sys.common.PropertiesUtil;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.apply.ApplyForgraDuate;
import com.sys.service.ApplyUserinfoService;
import com.sys.service.apply.ApplyForgraDuateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("applyForgraDuate")
public class ApplyForgraDuateController {

    @Autowired
    private ApplyForgraDuateService applyForgraDuateService;

    @Autowired
    private ApplyUserinfoService applyUserinfoService;

    /**
     * 跳到新就业申请录入界面
     * @return
     */
    @RequestMapping("toNewGraduate")
    public Object toNewGraduate(){
        return "applicationForm/newEmployment/newEmployment";
    }

    /**
     * 查询新就业信息--保障房备案管理
     * @param request
     * @return
     */
    @RequestMapping("selectApplyForgraDuate")
    @ResponseBody
    public Object selectApplyForgraDuate(HttpServletRequest request){
        ApplyForgraDuate applyForgraDuate = new ApplyForgraDuate();
        ApplyUserinfo applyUserinfo = new ApplyUserinfo();
        String userName = (String)request.getParameter("username");
        if(StringUtils.isNotEmpty(userName)){
            applyUserinfo.setUsername(userName);
        }
        String sfzh = (String)request.getParameter("sfzh");
        if(StringUtils.isNotEmpty(sfzh)){
            applyUserinfo.setSfzh(sfzh);
        }
        String ssq = (String)request.getParameter("ssq");
        if(StringUtils.isNotEmpty(ssq) && !"0".equals(ssq)) {
            applyForgraDuate.setAfgSsq(ssq);
        }
        String ssj = (String)request.getParameter("ssj");
        if(StringUtils.isNotEmpty(ssj)) {
            applyForgraDuate.setAfgDwdz(ssj);
        }
        //默认查询区字典
        String jiedao = PropertiesUtil.getAreaProperties("jiedao");
        if(StringUtils.isNotEmpty(jiedao)) {
            applyUserinfo.setJiedao(jiedao);
        }
        String area = PropertiesUtil.getAreaProperties("area");
        if(StringUtils.isNotEmpty(area)) {
            applyUserinfo.setXzq(area);
        }
        String  page = StringUtils.isEmpty((String)request.getParameter("page"))?
                "1":(String)request.getParameter("page");//页数
        String rows = StringUtils.isEmpty((String)request.getParameter("rows"))?
                "20":(String)request.getParameter("rows");//行数
        applyForgraDuate.setApplyUserinfo(applyUserinfo);
        //备案必备查询条件
        applyForgraDuate.setAfgLc(new Short("4"));
        //非退房小于5
        applyForgraDuate.setAfgZt(new Short("5"));

        PageInfo<ApplyForgraDuate> applyForForeignPageInfoes =
                (PageInfo<ApplyForgraDuate>)applyForgraDuateService
                        .selectByCondition(applyForgraDuate,page,rows);
        Map<String,Object> map=new HashMap();
        map.put("rows",applyForForeignPageInfoes.getList());
        map.put("total",applyForForeignPageInfoes.getTotal());
        return map;
    }


    /**
     * 添加补贴申请单信息
     * @return
     */
    @RequestMapping("addApplyForgraDuate")
    @ResponseBody
    public Object addApplyForgraDuate(ApplyForgraDuate applyForgraDuate, HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String result="";//返回结果字符
        String afgId="";//返回的afgId


        String conditionResult = applyUserinfoService.getApplyTypeCondition(request);
        if("success".equals(conditionResult)){
            String addResult = applyForgraDuateService.addApplyForForeign(applyForgraDuate,request);
            if("success".equals(addResult)){
                result="success";
                afgId=applyForgraDuate.getAfgId();
            }else{
                result="添加失败";
            }
        }else if(conditionResult.indexOf("applyType")!=-1){
            result = PropertiesUtil.getApplyTypeProperties("baozhangzhong");
        }else{
            result = conditionResult;
        }
        resultMap.put("result",result);
        resultMap.put("applyid",afgId);
        return resultMap;
    }

    /**
     * 提交修改后的新就业申请单
     * @param aplfd
     * @param request
     * @return
     */
    @RequestMapping("updateApplyForgraDuate")
    @ResponseBody
    public Object updateApplyForgraDuate(ApplyForgraDuate aplfd, HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String result="";//返回结果字符
        String updateResult=applyForgraDuateService.updateApplyForgraDuate(aplfd,request);
        System.out.println("updateResult-------:"+updateResult);
        if("success".equals(updateResult)){
            result="success";
        }
        resultMap.put("result",result);
        resultMap.put("applyid",aplfd.getAfgId());
        return resultMap;
    }

}
