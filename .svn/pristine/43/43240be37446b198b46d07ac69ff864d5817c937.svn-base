package com.sys.controller.apply;

import com.github.pagehelper.PageInfo;
import com.sys.common.PropertiesUtil;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.apply.ApplyFamilyMember;
import com.sys.pojo.apply.ApplyForForeign;
import com.sys.service.ApplyUserinfoService;
import com.sys.service.apply.ApplyForForeignService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc:外来户申请单控制类
 * @Author:wangli
 * @CreateTime:10:23 2018/10/13
 */
@Controller
@RequestMapping("applyForForegin")
public class ApplyForForeginController {

    @Autowired
    private ApplyForForeignService applyForForeignService;

    @Autowired
    private ApplyUserinfoService applyUserinfoService;

    /**
     * 列出所有外来户申请单
     * @return
     */
    @RequestMapping("listApply")
    @ResponseBody
    public Object getAllListApply(){
       List<ApplyForForeign> applyList = applyForForeignService.selectList();
       return applyList;
    }

    /**
     * 查询外来务工信息--保障房备案管理
     * @param request
     * @return
     */
    @RequestMapping("selectApplyForForegin")
    @ResponseBody
    public Object selectApplyForgraDuate(HttpServletRequest request){
        ApplyForForeign applyForForeign = new ApplyForForeign();
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
            applyForForeign.setAffSsq(ssq);
        }
        String ssj = (String)request.getParameter("ssj");//所属街
        if(StringUtils.isNotEmpty(ssj)) {
            applyForForeign.setAffDwdz(ssj);
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
        applyForForeign.setApplyUserinfo(applyUserinfo);
        applyForForeign.setAffLc(new Short("4"));
        //非退房小于5
        applyForForeign.setAffZt(new Short("5"));

        String  page = StringUtils.isEmpty((String)request.getParameter("page"))?
                "1":(String)request.getParameter("page");//页数
        String rows = StringUtils.isEmpty((String)request.getParameter("rows"))?
                "20":(String)request.getParameter("rows");//行数
        PageInfo<ApplyForForeign> applyForForeignPageInfoes =
                (PageInfo<ApplyForForeign>)applyForForeignService
                        .selectByCondition(applyForForeign,page,rows);
        Map<String,Object> map=new HashMap();
        map.put("rows",applyForForeignPageInfoes.getList());
        map.put("total",applyForForeignPageInfoes.getTotal());
        return map;
    }

    /**
     * 跳到外来用户申请单页面
     * @return
     */
    @RequestMapping("toApplyForegin")
    public String toApplyForegin(){
        return "applicationForm/migrantWorkers/wailaiwg";
    }

    /**
     * 添加申请信息
     * @return
     */
    @RequestMapping("addApplyInfo")
    @ResponseBody
    public Object addApplyInfo( ApplyForForeign aplff,HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        /*ApplyForForeign aplff = new ApplyForForeign();
        setApplyForForeign(aplff,request);*/
        String result="";//bzfxm返回结果字符
        String applyid="";//返回的applyid

        String conditionResult = applyUserinfoService.getApplyTypeCondition(request);
        if("success".equals(conditionResult)){
            String addResult = applyForForeignService.addApplyForForeign(aplff,request);
            if("success".equals(addResult)){
                result="success";
                applyid=aplff.getAffId();
            }else{
                result="添加失败";
            }
        }else if(conditionResult.indexOf("applyType")!=-1){
            result = PropertiesUtil.getApplyTypeProperties("baozhangzhong");
        }else{
            result = conditionResult;
        }

        resultMap.put("result",result);
        resultMap.put("applyid",applyid);
        return resultMap;

    }

    /**
     * 手动设置外来户申请单实体
     * @param aplff
     * @param request
     */
    private void setApplyForForeign(ApplyForForeign aplff,HttpServletRequest request){

        /*个人基本信息设置*/
        aplff.setAffDwmc(request.getParameter("affDwmc"));//单位名称
        aplff.setAffSsq(request.getParameter("affSsq"));//所属区
        aplff.setAffWdhjdz(request.getParameter("affWdhjdz"));//外籍户籍地址
        aplff.setAffLxjzsj(request.getParameter("affLxjzsj"));//来徐时间
        aplff.setAffLdhtkssj(request.getParameter("affLdhtkssj"));//劳动合同签订年限开始
        aplff.setAffLdhtjssj(request.getParameter("affLdhtjssj"));//劳动合同签订年限结束
        aplff.setAffSbjnsj(request.getParameter("affSbjnsj"));//社会保险缴纳时间
        aplff.setAffDwmc(request.getParameter("affGjjjnsj"));//住房公积金缴纳时间

        /*家庭情况*/
//        aplff.setAffJtrks(Short.parseShort(request.getParameter("affJtrks")));//家庭人口
//        aplff.setAffGrnsr((request.getParameter("affGrnsr")));//个人年收入
//        aplff.setAffJtnsr(Short.parseShort(request.getParameter("affJtnsr")));//家庭年收入
//        aplff.setAffRjysr(Short.parseShort(request.getParameter("affRjysr")));//人均年收入

        /*单位信息设置*/
        aplff.getApplyUnit().setLegelrep(request.getParameter("afhZl"));//法定代表人
        aplff.getApplyUnit().setBsls(request.getParameter("bsls"));//营业执照号
        aplff.getApplyUnit().setEntag(request.getParameter("entag"));//委托代理人
        aplff.getApplyUnit().setTel(request.getParameter("tel"));//手机号码
        aplff.getApplyUnit().setAddress(request.getParameter("address"));//地址

        /*家庭住房信息设置*/
        aplff.getApplyFamilyHouse().setAfhZl(request.getParameter("afhZl"));//房屋坐落
        aplff.getApplyFamilyHouse().setAfhCqr(request.getParameter("afhCqr"));//产权单位
        aplff.getApplyFamilyHouse().setAfhMj(request.getParameter("afhMj"));//坐落面积
        aplff.getApplyFamilyHouse().setAfhRjmj(request.getParameter("afhRjmj"));//人均建筑面积
        aplff.getApplyFamilyHouse().setAfhZfxz(request.getParameter("afhZfxz"));//现住房性质

        /*家庭成员信息设置*/
        String[] afmYsqrgxs = request.getParameterValues("afmYsqrgx");//与申请人关系集
        String[] afmXms = request.getParameterValues("afmXm");//申请人关系人姓名集
        String[] afmCsnys = request.getParameterValues("afmCsny");//申请人关系人出生年月集
        String[] afmHyzks = request.getParameterValues("afmHyzk");//申请人关系人婚姻状况集
        String[] afmGzdws = request.getParameterValues("afmGzdw");//申请人关系人工作单位集
        String[] afmSfzhs = request.getParameterValues("afmSfzh");//申请人关系人身份号码集
        String[] afmNsrs = request.getParameterValues("afmNsr");//申请人关系人年收入集

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
     * @param aplff
     * @param request
     * @return
     */
    @RequestMapping("/updateApplyForForeign")
    @ResponseBody
    public Object updateApplyForForeign(ApplyForForeign aplff, HttpServletRequest request){
        String result="";//返回结果字符
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String updateResult=applyForForeignService.updateApplyForForeign(aplff,request);
        if("success".equals(updateResult)){
            result="success";
        }
        resultMap.put("result",result);
        resultMap.put("applyid",aplff.getAffId());
        return resultMap;
    }

    /**
     * 删除申请单及其关联信息
     * @param aplffid
     * @param request
     * @return
     */
    @RequestMapping("deleteApplyInfo")
    @ResponseBody
    public Object deleteApplyInfo(String aplffid , HttpServletRequest request){
        int count=0;
        String result = this.applyForForeignService.deleteApplyInfo(aplffid);
        if("success".equals(result)){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }
}