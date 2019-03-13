package com.sys.controller.apply;

import com.github.pagehelper.PageInfo;
import com.sys.common.PropertiesUtil;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.apply.Apply;
import com.sys.pojo.apply.ApplyFamilyHouse;
import com.sys.pojo.apply.ApplyFamilyMember;
import com.sys.service.ApplyUserinfoService;
import com.sys.service.apply.ApplyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc:经适房和廉租房
 * @Author:wangli
 * @CreateTime:16:07 2018/10/17
 */
@Controller
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private ApplyUserinfoService applyUserinfoService;
    /**
     * 跳转到申请单修改页面
     * @param applyType
     * @return
     */
    @RequestMapping("toUpdateApply")
    public String toUpdateApply(String applyType){
        String toPath = "";
        if(applyType.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))){
            toPath="applicationForm/perApply/perApplyWlwUpdate";
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))){
            toPath="applicationForm/perApply/perApplyXjyUpdate";
        }if(applyType.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))){
            toPath="applicationForm/perApply/perApplyJsfUpdate";
        }if(applyType.equals(PropertiesUtil.getApplyTypeProperties("butie"))){
            toPath="applicationForm/perApply/perApplyButieUpdate";
        }if(applyType.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))){
            toPath="applicationForm/perApply/perApplyLzfUpdate";
        }
        return  toPath;
    }

    /**
     * 查询经济适用、廉租信息--保障房备案管理
     * @param request
     * @return
     */
    @RequestMapping("selectApply")
    @ResponseBody
    public Object selectApply(HttpServletRequest request){
        Apply apply = new Apply();
        ApplyUserinfo applyUserinfo = new ApplyUserinfo();
        String userName = (String)request.getParameter("username");
        if(StringUtils.isNotEmpty(userName)){
            applyUserinfo.setUsername(userName);
        }
        String sfzh = (String)request.getParameter("sfzh");//身份证
        if(StringUtils.isNotEmpty(sfzh)){
            applyUserinfo.setSfzh(sfzh);
        }
        String ssq = (String)request.getParameter("ssq");//所属区
        if(StringUtils.isNotEmpty(ssq) && !"0".equals(ssq)) {
            apply.setApSsq(ssq);
        }
        String ssj = (String)request.getParameter("ssj");//所属街
        if(StringUtils.isNotEmpty(ssj)) {
            apply.setApJdbsc(ssj);
        }
        //默认查询区字典
        String jiedao = PropertiesUtil.getAreaProperties("jiedao");//街道字典
        if(StringUtils.isNotEmpty(jiedao)) {
            applyUserinfo.setJiedao(jiedao);
        }
        String area = PropertiesUtil.getAreaProperties("area");//区域字典
        if(StringUtils.isNotEmpty(area)) {
            applyUserinfo.setXzq(area);
        }
        //备案必备查询条件
        apply.setApplyUserinfo(applyUserinfo);
        apply.setApLc(new Short("4"));
        //非退房小于5
        apply.setApZt(new Short("5"));

        String  page = StringUtils.isEmpty((String)request.getParameter("page"))?
                "1":(String)request.getParameter("page");//页数
        String rows = StringUtils.isEmpty((String)request.getParameter("rows"))?
                "20":(String)request.getParameter("rows");//行数
        String applyType = request.getParameter("applyType");//申请类型
        apply.setApSqlb(applyType);
        PageInfo<Apply> applyForForeignPageInfoes =
                (PageInfo<Apply>)applyService
                        .selectByCondition(apply,page,rows);
        Map<String,Object> map=new HashMap();
        map.put("rows",applyForForeignPageInfoes.getList());
        map.put("total",applyForForeignPageInfoes.getTotal());
        return map;
    }

    /**
     * 跳到公租房或者经适房申请界面
     * @return
     */
    @RequestMapping("toApply")
    public ModelAndView toApply(String applyType){
        ModelAndView modelAndView = new ModelAndView();
        String toPath = "";
        if("0".equals(applyType)){
            applyType= PropertiesUtil.getApplyTypeProperties("jingsf");
            toPath="applicationForm/economicApplication/econonmic";
        }else if("1".equals(applyType)){
            applyType= PropertiesUtil.getApplyTypeProperties("gongzf");
            toPath="applicationForm/lowRentPublicHousing/lowRentPublic";
        }
        modelAndView.setViewName(toPath);
        modelAndView.addObject("applyType",applyType);
        return modelAndView;
    }
    /**
     * 添加申请信息
     * @return
     */
    @RequestMapping("addApplyInfo")
    @ResponseBody
    public Object addApplyInfo(Apply apl, HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        /*ApplyForForeign aplff = new ApplyForForeign();
        setApplyForForeign(aplff,request);*/
        /*首先判断用户是否具有添加申请的条件*/
        String result="";//bzfxm返回结果字符
        String applyid="";//返回的applyid
        String conditionResult = applyUserinfoService.getApplyTypeCondition(request);
        if("success".equals(conditionResult)){
            String addResult = applyService.addApply(apl,request);
            if("success".equals(addResult)){
                result="success";
                applyid=apl.getApId();
            }else{
                result="提交失败";
            }
        }else if(conditionResult.indexOf("applyType")!=-1){
            /*用户申请的是低保特困公租房，而存在低保特困补贴的话，可以提交申请的；否则不能*/
            if(PropertiesUtil.getApplyTypeProperties("gongzf").equals(apl.getApSqlb())
                    && conditionResult.indexOf(PropertiesUtil.getApplyTypeProperties("butie"))!=-1){
                String addResult = applyService.addApply(apl,request);
                if("success".equals(addResult)){
                    result="success";
                    applyid=apl.getApId();
                }else{
                    result="提交失败";
                }
            }else{
                result = PropertiesUtil.getApplyTypeProperties("baozhangzhong");
            }
        }else{
            result = conditionResult;
        }
        resultMap.put("result",result);
        resultMap.put("applyid",applyid);
        return resultMap;
    }

    /**
     * 添加补贴申请单信息
     * @return
     */
    @RequestMapping("addApplyInfoLZ")
    @ResponseBody
    public Object addApplyInfoLZ(Apply apl, HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String result="";//返回结果字符
        String afgId="";//返回的afgId
        String addResult = applyService.addApply(apl,request);
        System.out.println("addResult-------:"+addResult);
        if("success".equals(addResult)){
            result="success";
            afgId=apl.getApId();
        }
        resultMap.put("result",result);
        resultMap.put("applyid",afgId);
        return resultMap;
    }

    /**
     * 手动设置外来户申请单实体
     * @param aplff
     * @param request
     */
    private void setApplyForForeign(Apply aplff,HttpServletRequest request){

        /*个人基本信息设置*/
        aplff.setApTsjt(Short.parseShort(request.getParameter("apTsjt")));//特殊家庭字段
        aplff.setApSqlb(request.getParameter("sqlb"));//申请类别

        /*户籍信息*/
//        aplff.setApJtrk(Short.parseShort(request.getParameter("affJtrks")));//家庭人口
//        aplff.setApSqhjnx(Short.parseShort(request.getParameter("apSqhjnx")));//户籍年限
//        aplff.setApJtnsr(Short.parseShort(request.getParameter("affJtnsr")));//家庭年收入
//        aplff.setApRjysr(Short.parseShort(request.getParameter("affRjysr")));//人均月收入

        /*家庭更换住房信息*/
        aplff.getApplyFamilyHouseChange().setAfhcYzfzl(request.getParameter("yzfzl"));//原住房座落
        aplff.getApplyFamilyHouseChange().setAfhcCqr(request.getParameter("cCqr"));//产权人
        aplff.getApplyFamilyHouseChange().setAfhcJzmj(request.getParameter("cJzmj"));//建筑面积
        aplff.getApplyFamilyHouseChange().setAfhcBgfs(request.getParameter("bgfs"));//变更方式（拆迁或转让）
        aplff.getApplyFamilyHouseChange().setAfhcBgsj(request.getParameter("bgsj"));//变更时间
        aplff.getApplyFamilyHouseChange().setAfhcAzfzl(request.getParameter("azfzl"));//安置房座落
        aplff.getApplyFamilyHouseChange().setAfhcAzfjzmj(request.getParameter("azfjzmj"));//安置房面积

        /*家庭住房信息设置，多个*/
        String[] afhZls = request.getParameterValues("afhZls");//房屋坐落集
        String[] afhCqrs = request.getParameterValues("afhCqrs");//产权单位集
        String[] afhMjs = request.getParameterValues("afhMjs");//坐落面积集
        String[] afhRjmjs = request.getParameterValues("afhRjmjs");//人均建筑面积集
        String[] afhZfxzs = request.getParameterValues("afhZfxzs");//现住房性质集
        /*住房信息遍历*/
        for(int i=0; i< afhZls.length;i++){
            ApplyFamilyHouse member = new ApplyFamilyHouse();
            member.setAfhZl(afhZls[i]);
            member.setAfhCqr(afhCqrs[i]);
            member.setAfhMj(afhMjs[i]);
            member.setAfhRjmj(afhRjmjs[i]);
            member.setAfhZfxz(afhZfxzs[i]);
            aplff.getApplyFamilyHouses().add(member);
        }

        /*家庭成员信息设置，多个，包括个人与配偶以及其他家庭成员*/
        String[] afmYsqrgxs = request.getParameterValues("afmYsqrgx");//与申请人关系集
        String[] afmXms = request.getParameterValues("afmXm");//申请人关系人姓名集
        String[] afmCsnys = request.getParameterValues("afmCsny");//申请人关系人出生年月集
        String[] afmHyzks = request.getParameterValues("afmHyzk");//申请人关系人婚姻状况集
        String[] afmGzdws = request.getParameterValues("afmGzdw");//申请人关系人工作单位集
        String[] afmSfzhs = request.getParameterValues("afmSfzh");//申请人关系人身份号码集
        String[] afmNsrs = request.getParameterValues("afmNsr");//申请人关系人年收入集

        /*家庭成员信息遍历*/
//        for(int i=0; i< afmYsqrgxs.length;i++){
//            ApplyFamilyMember member = new ApplyFamilyMember();
//            member.setAfmYsqrgx(afmYsqrgxs[i]);
//            member.setAfmXm(afmXms[i]);
//            member.setAfmCsny(afmCsnys[i]);
//            member.setAfmHyzk(afmHyzks[i]);
//            member.setAfmGzdw(afmGzdws[i]);
//            member.setAfmSfzh(afmSfzhs[i]);
//            member.setAfmNsr(Short.parseShort(afmNsrs[i]));
//            aplff.getApplyFamilyMembers().add(member);
//        }
    }


    /**
     * 提交修改后的申请信息
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/updateApply")
    @ResponseBody
    public Object updateApply(Apply apply, HttpServletRequest request){
        String result="";//返回结果字符
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String updateResult=applyService.updateApplyInfo(apply);
        if("success".equals(updateResult)){
            result="success";
        }
        resultMap.put("result",result);
        resultMap.put("applyid",apply.getApId());
        return resultMap;
    }
}