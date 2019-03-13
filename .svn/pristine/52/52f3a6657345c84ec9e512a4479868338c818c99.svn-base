package com.sys.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.common.*;
import com.sys.common.Random;
import com.sys.common.encrypt.AESUtil;
import com.sys.common.verification.CodeUtil;
import com.sys.mapper.apply.ApproveMapper;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.ParmItem;
import com.sys.pojo.UserInfo;
import com.sys.pojo.apply.*;
import com.sys.service.ApplyUserinfoService;
import com.sys.service.ParmItemService;
import com.sys.service.apply.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/15 0015
 * @desc
 */
@Controller
@RequestMapping("/applyUserinfo")
public class ApplyUserinfoController {

    @Autowired
    ApplyUserinfoService applyUserinfoService;


    protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    @Autowired
    private ApproveMapper approveMapper;

    @Autowired
    private ApplyForForeignService applyForForeignService;

    @Autowired
    private ApplyForgraDuateService applyForgraDuateService;

    @Autowired
    private ApplyButieService applyButieService;

    @Autowired
    private ApplyService applyService;

    @Autowired
    private ApproveService approveService;

    @Autowired
    private ApplyFamilyMemberService applyFamilyMemberService;

    @Autowired
    private ParmItemService parmItemService;

    @Autowired
    private ApplyUserInfoFormService applyUserInfoFormService;

    @RequestMapping("/toapplogin")
    public String toapplogin(){
        return "index/applogin";
    }


    /**
     * 跳到修改密码页面
     *
     * @return
     */
    @RequestMapping("toChangePsw")
    public String toChangePsw() {
        return "applicationForm/applyUser/changePwd";
    }


    /**
     * 跳到用户个人首页
     *
     * @return
     */
    @RequestMapping("toApplyIndex")
    public String toApplyIndex() {
        return "applicationForm/index";
    }

    /**
     * 跳到个人申请列表页面
     *
     * @return
     */
    @RequestMapping("toPerApplyList")
    public String toPerApplyList(HttpServletRequest request) {
        /*在进入年审页面前，先获取用户自己的初审信息*/
        ApplyUserinfo applyUserinfo = (ApplyUserinfo) request.getSession().getAttribute("applyUserinfo");
        /*通过用户id查询审批单*/
        Map<String, Object> conditionMap = Maps.newHashMap();
        conditionMap.put("userid", applyUserinfo.getUserid());//userid
        conditionMap.put("applyTypeNotLike", "ns");//年审
        //Approve approve = approveMapper.findByMap(conditionMap);
        int approveCount = approveService.findCountByMap(conditionMap);
        //Approve approve = approveService.findByMap(conditionMap);
        //conditionMap.put("applyStateNotLike","驳回");
        //Approve approve = approveService.findByMap(conditionMap);
        /*如果审批单为空，但是存在家庭成员和申请但数据，则认为是原有的老数据，系统会跳到老数据界面*/
        /*if (approveCount == 0) {
            conditionMap.put("sfzh", applyUserinfo.getSfzh());
            List<ApplyFamilyMember> afList = applyFamilyMemberService.selectListByMap(conditionMap);
            if (!afList.isEmpty()) {
                return "applicationForm/perApply/perOldApplyList";
            }
        }*/
        return "applicationForm/perApply/perApplyList";
    }

    /**
     * 查询未查看的通知个数
     * @return
     */
    @RequestMapping("getInformCount")
    @ResponseBody
    public Object getInformCount(HttpSession session) {
        ApplyUserinfo applyUserinfo = (ApplyUserinfo)session.getAttribute("applyUserinfo");
        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("userid",applyUserinfo.getUserid());
        conditionMap.put("readFlag","0");
        conditionMap.put("count",this.applyUserInfoFormService.findInformCountByMap(conditionMap));
        return conditionMap;
    }

    /**
     * 跳到个人年审列表页面
     *
     * @return
     */
    @RequestMapping("toPerNsApplyList")
    public String toPerNsApplyList(HttpServletRequest request) {
        /*在进入年审页面前，先获取用户自己的初审信息*/
        ApplyUserinfo applyUserinfo = (ApplyUserinfo) request.getSession().getAttribute("applyUserinfo");
        Map<String, Object> conditionMap = Maps.newHashMap();
        conditionMap.put("nsSfzh",applyUserinfo.getSfzh());
        conditionMap.put("nsStateNotLike","驳回");
        conditionMap.put("nsExist", "notNull");
        conditionMap.put("nsDateEqual",new Date());
        conditionMap.put("apZt",4);
        List<Map<String,Object>> mapList = this.approveService.selectApplyNsMap(conditionMap);

        /*跳转路径和返回信息*/
        String goUrl = "applicationForm/perNsApply/perNsApplyList";
        String message = "";



        /*申请类别和申请单id*/
        String applyType = "";
        String applyId = "";

        /*通过用户id查询审批单*/
        if (mapList.size()>0) {
            /*approveCount大于0则代表已经发起了今年的年审*/
            request.setAttribute("haveHouse", 0);//申请单已经有分房标志
            //goUrl = "applicationForm/message";
            //message = "您申请的经济适用房，不用参加年审申请";

        }else{
            /*查看用户是否存在在保障中的申请单*/
            conditionMap.remove("nsDateEqual");
            //conditionMap.remove("nsApplyType");
            conditionMap.remove("nsStateNotLike");
            conditionMap.remove("nsExist");
            mapList = this.approveService.selectApplyNsMap(conditionMap);//状态为保障中的申请单列表

            /*存在状态为保障中的申请单*/
            if(mapList.size()>0){
                /*为保障中的申请单对应的年审列表（年审不为空）*/
                conditionMap.put("nsExist", "notNull");
                conditionMap.put("nsDateEqual",new Date());
                List<Map<String,Object>> mapList2 = this.approveService.selectApplyNsMap(conditionMap);//保障中的列表
                if(mapList2.size()>0){
                    /*为保障中的申请单对应的年审列表（年审不为空），状态不为驳回*/
                    conditionMap.put("nsStateLike","驳回");
                    List<Map<String,Object>> mapList3 = this.approveService.selectApplyNsMap(conditionMap);//保障中的列表

                    /*该保障中的申请单对应的年审记录均为驳回，则可以发起年审*/
                    if(mapList2.size() == mapList3.size()){
                        Map<String,Object> resultMap = mapList.get(0);
                        applyId=(String)resultMap.get("APPID");
                        applyType=(String)resultMap.get("APPTYPE");
                        request.setAttribute("haveHouse", 1);//申请单已经有分房标志
                    }else{
                        request.setAttribute("haveHouse", 0);//不能新增年审的标记
                    }
                }else{
                    /*不存在年审审批单，则可以发起申请*/
                    Map<String,Object> resultMap = mapList.get(0);
                    applyId=(String)resultMap.get("APPID");
                    applyType=(String)resultMap.get("APPTYPE");
                    request.setAttribute("haveHouse", 1);//申请单已经有分房标志
                }

            }else{/*不存在保障中的申请单，则不可以发起申请*/
                /*查询是否存在年审单记录*/
                conditionMap.put("nsExist", "notNull");
                conditionMap.remove("apZt");
                List<Map<String,Object>> mapList2 = this.approveService.selectApplyNsMap(conditionMap);//保障中的列表
                if(mapList2.size()>0){
                    request.setAttribute("haveHouse", 0);//不能新增年审的标记
                }else{
                    goUrl = "applicationForm/message";
                    message = PropertiesUtil.getApplyTypeProperties("nianshen");
                }
            }



            /*两者的差额为保障中*/
           /* if(mapList2.size() - mapList.size()>0){
                *//*用户存在在保障中的申请单,但不存在今年的年审单，或者发起过年审，但已被驳回，则可以发起年审*//*
                Map<String,Object> resultMap = mapList.get(0);
                applyId=(String)resultMap.get("APPID");
                applyType=(String)resultMap.get("APPTYPE");
                request.setAttribute("haveHouse", 1);//申请单已经有分房标志
            }else{
                *//*查看用户是否有其他种情况的年审记录，诸如驳回的、往年的*//*
                //conditionMap.put("nsApplyType", "ns");
                conditionMap.remove("apZt");
                conditionMap.remove("nsStateNotLike");
                conditionMap.put("nsExist", "notNull");
                mapList = this.approveService.selectApplyNsMap(conditionMap);
                if(mapList.size()==0){
                    goUrl = "applicationForm/message";
                    message = PropertiesUtil.getApplyTypeProperties("nianshen");
                }else{
                    request.setAttribute("haveHouse", 0);//不能新增年审的标记
                }
            }*/
        }

        request.setAttribute("applyid", applyId);
        request.setAttribute("applyType", applyType);
        request.setAttribute("message", message);
        return goUrl;

    }

    /**
     * 跳到个人年审列表页面
     *
     * @return
     */
   /* @RequestMapping("toPerNsApplyList")
    public String toPerNsApplyList(HttpServletRequest request) {
        *//*在进入年审页面前，先获取用户自己的初审信息*//*
        ApplyUserinfo applyUserinfo = (ApplyUserinfo) request.getSession().getAttribute("applyUserinfo");
        *//*通过用户id查询审批单*//*
        Map<String, Object> conditionMap = Maps.newHashMap();
        conditionMap.put("userid", applyUserinfo.getUserid());
        conditionMap.put("applyTypeNotLike", "ns");
       // int approveCount = approveService.findCountByMap(conditionMap);
        //Approve approve = approveService.findByMap(conditionMap);
        conditionMap.put("applyStateNotLike", "驳回");
        conditionMap.put("applyZtEqual",4);
        int approveCount = approveService.findCountByMap(conditionMap);
        //Approve approve = approveService.findByMap(conditionMap);

        *//*跳转路径和返回信息*//*
        String goUrl = "applicationForm/perNsApply/perNsApplyList";
        String message = "";

        *//*申请类别和申请单id*//*
        String applyType = "";
        String applyId = "";

        *//*通过用户id查询审批单*//*
        if (approve != null && "审批全部通过".equals(approve.getState())) {
            applyType = approve.getAptype();
            *//*非经适房的申请才可以有年审申请*//*
            if (!applyType.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))) {
//                String applyId = approve.getAplid();//申请单id
//                        applyType = approve.getAptype();
                *//*非补贴的申请才可以有年审申请*//*
//            if(!applyType.equals(PropertiesUtil.getApplyTypeProperties("butie"))){
                applyId = approve.getAplid();//申请单id
                short afgZt = 0;//申请单分房标记 大于3则为已经选房
                if (applyType.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))) {
                    *//*获取分房标志*//*
                    afgZt = applyForForeignService.selectById(applyId).getAffZt();
                } else if (applyType.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))) {
                    afgZt = applyForgraDuateService.selectById(applyId).getAfgZt();
                } else if (applyType.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))) {
                    afgZt = applyService.selectById(applyId).getApZt();
                }
//                else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))){
//                    afgLc = applyService.selectById(applyId).getApLc();
//                }
                else if (applyType.equals(PropertiesUtil.getApplyTypeProperties("butie"))) {
                    afgZt = applyButieService.selectById(applyId).getAbLc();
                }

                if (afgZt == 4) {//当状态为4，即已保障的情况下，可以发起年审申请；否则不能发起年审申请
                    request.setAttribute("haveHouse", 1);//申请单已经有分房标志
                } else if (afgZt < 4) {//当状态为4，即已保障的情况下，申请尚未通过
                    request.setAttribute("haveHouse", 0);//申请单没有有分房标志
                    goUrl = "applicationForm/message";
                    message = "您的初审流程尚未通过审批，请查看申请记录一栏";
                } else {
                    request.setAttribute("haveHouse", 0);//申请单没有有分房标志
                }
            } else {
                goUrl = "applicationForm/message";
                message = "您申请的经济适用房，不用参加年审申请";
            }
        } else if (approve == null) {
            //如果审批单位空，但申请单存在数据，则认为是老系统用户，依然能够发起申请
            conditionMap.put("sfzh", applyUserinfo.getSfzh());
            conditionMap.put("appZt", "4");
            List<Map<String, Object>> mapList = this.applyForForeignService.selectOldListByMap(conditionMap);
            if (!mapList.isEmpty()) {
                Map<String, Object> resultMap = mapList.get(0);
                applyId = (String) resultMap.get("APPID");//申请单id
                applyType = "" + resultMap.get("APPTYPE");//申请类型的获取
                if (applyType.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))){
                    goUrl = "applicationForm/message";
                    message = "您申请的经济适用房，不用参加年审申请";
                }else{
                    request.setAttribute("haveHouse", 1);//申请单已经有分房标志
                }
            } else {
                *//*不存在老数据，则没有发起初审，提示发起初审*//*
                goUrl = "applicationForm/message";
                message = "您没有在保障中的申请，请查看申请记录一栏";
            }
        } else {
            goUrl = "applicationForm/message";
            message = "您的初审流程尚未通过审批，请查看申请记录一栏";
        }

        request.setAttribute("applyid", applyId);
        request.setAttribute("applyType", applyType);
        request.setAttribute("message", message);
        return goUrl;

    }*/


    /**
     * 跳到个人通知页面
     *
     * @return
     */
    @RequestMapping("toPerInform")
    public String toPerInform() {
        return "applicationForm/applyUser/perInform";
    }

    /**
     * 跳到个人信息页面
     *
     * @return
     */
    @RequestMapping("toPersonalInf")
    public String toPersonalInf(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ApplyUserinfo applyUserinfo = (ApplyUserinfo) session.getAttribute("applyUserinfo");
        Map<String, Object> conditionMap = Maps.newHashMap();
        conditionMap.put("userid", applyUserinfo.getUserid());
        /*通过userid查询审批单*/
        List<Approve> appList = approveService.findApproveByMap(conditionMap);

        /*根据用户是否提交过审批单，设置flag*/
        if (!appList.isEmpty()) {
            request.setAttribute("flag", "1");
        } else {
            request.setAttribute("flag", "0");
        }
        return "applicationForm/applyUser/personal";
    }

    /**
     * 更新用户信息
     *
     * @param newUserinfo
     * @param session
     * @return
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public Object updateUserInfo(String checkcode,ApplyUserinfo newUserinfo, HttpSession session) {
        String returnResult = "";
        /*设置待更新的用户表对应的id为session中的用户id*/
        ApplyUserinfo applyUserinfo = (ApplyUserinfo) session.getAttribute("applyUserinfo");
        newUserinfo.setUserid(applyUserinfo.getUserid());
        int updateCount = this.applyUserinfoService.updateUser(newUserinfo);

        if (session.getAttribute("Verification")==null){
            returnResult="验证码已过期";
            return  returnResult;
        }
        String randomStr= (String) session.getAttribute("Verification");
        if (!checkcode.equals(randomStr)) {
            returnResult = "验证码输入错误";
            session.setAttribute("Verification", Random.ScRandom());
            return returnResult;
        }


        if (updateCount > 0) {
            /*更新session中的用户信息*/
            ApplyUserinfo newUser = this.applyUserinfoService.selectById(applyUserinfo.getUserid());
            session.setAttribute("applyUserinfo", newUser);
            returnResult = "用户信息更新成功";
        } else {
            returnResult = "用户信息更新失败";
        }
        return returnResult;
    }

    /**
     * 跳到到申请发起页面
     *
     * @return
     */
    @RequestMapping("/toApplyType")
    public String toApplyType(HttpServletRequest request) {

        ParmItem parmItem = parmItemService.selectSwitch();
        /*申请开关为设置为否，或者为null，则提示申请通道开关未设置，不允许申请*/
        if (parmItem==null  || !"是".equals(parmItem.getPiItemname())){
            request.setAttribute("message", PropertiesUtil.getApplyTypeProperties("shenqingtd"));
            return "applicationForm/message";
        }
        /*申请单中的老数据对应的用户申请数据*/
        ApplyUserinfo applyUserinfo = (ApplyUserinfo) request.getSession().getAttribute("applyUserinfo");
        String userid = applyUserinfo.getUserid();

        String returnResult = "applicationForm/type";
        Map<String, Object> conditionMap = Maps.newHashMap();
        conditionMap.put("sfzh",applyUserinfo.getSfzh());
        conditionMap.put("approveExist","no");
        int oldDataCount = this.applyFamilyMemberService.selectCountByMap(conditionMap);
        /*oldDataCount>0表示用户存在老数据，老的申请单（判断依据：存在家庭成员，并且家庭成员表中对应的申请id在审批
        单中不存在）*/
        if(oldDataCount>0){
            conditionMap.put("approveExist","yes");
            int newDataCount = this.applyFamilyMemberService.selectCountByMap(conditionMap);
            /*老用户没有在新系统中发起过申请，则不存在审批单记录*/
            if(newDataCount==0){
                conditionMap.remove("approveExist");
                /*获取申请单类型和状态*/
                List<Map<String,Object>> resultMap = this.applyFamilyMemberService.selectApplyMap(conditionMap);
                String applyType = (String)resultMap.get(0).get("APPTYPE");//申请类型
                String applyZT = ""+resultMap.get(0).get("APPZT");//申请状态
                /*如果为保障中的状态*/
                if(applyZT.equals("4")){
                    /*如果为保障中的低保特困公租房或者补贴，则可以继续申请另一种类别*/
                    if(applyType.equals(PropertiesUtil.getApplyTypeProperties("butie"))
                            || applyType.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))){
                        request.setAttribute("appType", applyType);
                    }else{
                        /*保障中的其他情况均不能申请*/
                        request.setAttribute("message", PropertiesUtil.getApplyTypeProperties("baozhangzhong"));
                        returnResult = "applicationForm/message";
                    }
                }else if(!applyZT.equals("5")){
                    /*其他非保障结束状态的申请单均不能申请*/
                    request.setAttribute("message", PropertiesUtil.getApplyTypeProperties("baozhangzhong"));
                    returnResult = "applicationForm/message";
                }
            }else{
                returnResult=setCommonResult(request);
            }
        }else{
            //以下为新用户的处理
            returnResult=setCommonResult(request);
        }
        return returnResult;
    }

    /*处理审批单，判断是否能够申请*/
    private String setCommonResult(HttpServletRequest request){
        String returnResult="applicationForm/type";
        Map<String,Object> conditionMap = Maps.newHashMap();
        /*老数据用户发起过申请（之前老数据申请单状态已更新为5，即保障结束后，重新发起的申请）*/
        ApplyUserinfo applyUserinfo = (ApplyUserinfo) request.getSession().getAttribute("applyUserinfo");
        conditionMap.put("userid", applyUserinfo.getUserid());
        conditionMap.put("applyTypeNotLike", "ns");//非年审审批单查询
        conditionMap.put("applyStateNotLike", "驳回");//审批单的状态不是驳回
        //conditionMap.put("applyZtEqual",5);//审批单对应的申请单状态是5
        int approveCount = approveService.findCountByMap(conditionMap);
        /*用户存在的状态不是驳回也不是年审的审批单大于1的话，则不能再申请*/
       /* if(approveCount>1){
            conditionMap.put("applyZtEqual", 5);//审批单对应的申请单状态是5
            int applyQuitCount = approveService.findCountByMap(conditionMap);//查询保障结束的申请信息
            if (applyQuitCount != approveCount){//如果二者不同，说明还存在未走完流程的申请单
                request.setAttribute("message", "您已经发起过申请，不能再次申请。请参考申请记录一栏");
                returnResult = "applicationForm/message";
            }else{
                returnResult = "applicationForm/type";
            }

        }else */if (approveCount > 0){
            conditionMap.put("applyZtEqual", 5);//审批单对应的申请单状态是5
            int applyQuitCount = approveService.findCountByMap(conditionMap);//查询保障结束的申请信息
            if (applyQuitCount != approveCount) {//如果审批单中对应的申请单不全为保障结束，即还存在其他状态的审批单
                int notQuitCount = approveCount - applyQuitCount;//状态不为保障结束的申请单
                if (notQuitCount == 1) {//只存在一个申请单
                    conditionMap.remove("applyZtEqual");
                    conditionMap.put("applyZtLess",5);
                    Approve approve = approveService.findByMap(conditionMap);//获取原有的审批单
                    String appType = approve.getAptype();//获取已经存在的申请类别
                    boolean flag = false;
                    /*如果之前的申请单位补贴或者低保特困公租房，并且是在保障中的状态，那么flag为true，用户可以继续申请
                     * 另一类的申请类型，诸如低保特困公租房在保障中，则可以申请补贴*/
                    if (appType.equals(PropertiesUtil.getApplyTypeProperties("butie"))) {
                        ApplyButie applyButie = this.applyButieService.selectById(approve.getAplid());
                        if (4 == applyButie.getAbZt()) {
                            flag = true;
                        }
                    } else if (appType.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))) {
                        Apply apply = this.applyService.selectById(approve.getAplid());
                        if (4 == apply.getApZt()) {
                            flag = true;
                        }
                    }
                    /*如果flag为true，则可以继续发起申请，否则不能申请*/
                    if (flag) {
                        request.setAttribute("appType", appType);
                    } else {
                            /*当申请类别为经适房，并且zt状态为6的时候，依然可以申请；6视为经适房轮候或者待摇号状态过期，
                            被操作员取消*/
                        returnResult = "applicationForm/message";
                        if (appType.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))) {
                            Apply apply = this.applyService.selectById(approve.getAplid());
                            if (6 == apply.getApZt()) {
                                returnResult = "applicationForm/type";
                            }
                        }
                        request.setAttribute("message", PropertiesUtil.getApplyTypeProperties("shenqingzhong"));
                    }
                }else{
                    request.setAttribute("message", PropertiesUtil.getApplyTypeProperties("shenqingzhong"));
                    returnResult = "applicationForm/message";
                }
            }
        }
        return  returnResult;
    }

    /**
     * 跳到到申请发起页面
     *
     * @return
     */
    /*@RequestMapping("/toApplyType")
    public String toApplyType(HttpServletRequest request) {
        *//*申请单中的老数据对应的用户申请数据*//*
        ApplyUserinfo applyUserinfo = (ApplyUserinfo) request.getSession().getAttribute("applyUserinfo");
        String userid = applyUserinfo.getUserid();
        Map<String, Object> conditionMap = Maps.newHashMap();
        //conditionMap.put("applyStateNotLike","驳回");
        //conditionMap.put("applyStateLike","审批全部通过");
        conditionMap.put("userid", applyUserinfo.getUserid());
        conditionMap.put("applyTypeNotLike", "ns");//非年审审批单查询
        conditionMap.put("applyStateNotLike", "驳回");//审批单的状态不是驳回
        //conditionMap.put("applyZtEqual",5);//审批单对应的申请单状态是5
        int approveCount = approveService.findCountByMap(conditionMap);
        //Approve approve = approveService.findByMap(conditionMap);
        // conditionMap.put("applyStateNotLike","驳回");
        // approveCount

        conditionMap.put("sfzh", applyUserinfo.getSfzh());
        List<ApplyFamilyMember> afList = applyFamilyMemberService.selectListByMap(conditionMap);
        ParmItem parmItem = parmItemService.selectSwitch();
        if (parmItem != null) {
            if ("是".equals(parmItem.getPiItemname())) {
                // boolean approveCondtion = approve==null
                boolean afCondtion = afList == null || afList.size() == 0;
                *//*当用户不存在审批单或者存在的审批单状态为驳回的时候，则允许发起申请；否则不允许发起申请*//*
                if (approveCount == 0 && !afCondtion) {//不存在审批单，却存在申请信息，则是老数据，不能再发起申请
                    request.setAttribute("message", "系统中存在您以前的申请信息，请参考申请记录一栏");
                    return "applicationForm/message";
                } else {
                    conditionMap.put("applyZtEqual", 5);//审批单对应的申请单状态是5
                    int applyQuitCount = approveService.findCountByMap(conditionMap);//查询保障结束的申请信息
                    if (applyQuitCount == approveCount) {//如果审批单中对应的申请单全为保障结束
                        return "applicationForm/type";
                    }
                    int notQuitCount = approveCount - applyQuitCount;//状态不为保障结束的申请单
                    if (notQuitCount == 1) {//只存在一个申请单
                        conditionMap.remove("applyZtEqual");
                        Approve approve = approveService.findByMap(conditionMap);//获取原有的审批单
                        String appType = approve.getAptype();//获取已经存在的申请类别
                        boolean flag = false;
                        *//*如果之前的申请单位补贴或者低保特困公租房，并且是在保障中的状态，那么flag为true，用户可以继续申请
                         * 另一类的申请类型，诸如低保特困公租房在保障中，则可以申请补贴*//*
                        if (appType.equals(PropertiesUtil.getApplyTypeProperties("butie"))) {
                            ApplyButie applyButie = this.applyButieService.selectById(approve.getAplid());
                            if (4 == applyButie.getAbZt()) {
                                flag = true;
                            }
                        } else if (appType.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))) {
                            Apply apply = this.applyService.selectById(approve.getAplid());
                            if (4 == apply.getApZt()) {
                                flag = true;
                            }
                        }

                        String returnUrl = "applicationForm/message";
                        *//*如果flag为true，则可以继续发起申请，否则不能申请*//*
                        if (flag) {
                            request.setAttribute("appType", appType);
                            returnUrl = "applicationForm/type";
                        } else {
                            *//*当申请类别为经适房，并且zt状态为6的时候，依然可以申请；6视为经适房轮候或者待摇号状态过期，
                            被操作员取消*//*
                            if (appType.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))) {
                                Apply apply = this.applyService.selectById(approve.getAplid());
                                if (6 == apply.getApZt()) {
                                    returnUrl= "applicationForm/type";
                                }
                            }
                            request.setAttribute("message", "您已经发起过申请，不能再次申请。请参考申请记录一栏");
                        }
                        return returnUrl;
                    } else {
                        request.setAttribute("message", "您已经发起过申请，不能再次申请。请参考申请记录一栏");
                        return "applicationForm/message";
                    }
                }
            } else {
                request.setAttribute("message", "申请通道已关闭");
                return "applicationForm/message";
            }
        } else {
            request.setAttribute("message", "申请通道开关尚未设置");
            return "applicationForm/message";
        }
    }*/


    @RequestMapping("/getApplyUserInfo")
    @ResponseBody
    public Object getApplyUserInfo(HttpServletRequest request){
        ApplyUserinfo applyUserinfo=(ApplyUserinfo)request.getSession().getAttribute("applyUserinfo");
        String userid=applyUserinfo.getUserid();
        return applyUserinfoService.selectById(userid);
    }


    @RequestMapping("/updatePwd")
    @ResponseBody
    public Object updatePwd(String oldpwd,String newPwd,HttpServletRequest request) throws Exception {
        String result="";
        ApplyUserinfo userinfo=(ApplyUserinfo) request.getSession().getAttribute("applyUserinfo");
        if(oldpwd.equals(AESUtil.decryptAES(userinfo.getUserpwd()))){
            Map<String,Object> map=new HashMap();
            map.put("userid",userinfo.getUserid());
            //System.out.println("newPwd-------"+newPwd+"---AESUtil.encryptAES(newPwd):"+AESUtil.encryptAES(newPwd));
            map.put("userpwd",AESUtil.encryptAES(newPwd));
            int updateCount=applyUserinfoService.updatePwd(map);
            if(updateCount>0){
                result="修改成功";
            }else{
                result="修改失败";
            }
        }else{
            result="原密码输入不正确";
        }
        return result;
    }

    @RequestMapping("/newPwd")
    @ResponseBody
    public Object newPwd(String newPwd,String userid) throws Exception {
        String result="";
        Map<String,Object> map=new HashMap();
        map.put("userid",userid);
        map.put("userpwd",AESUtil.encryptAES(newPwd));
        int updateCount=applyUserinfoService.updatePwd(map);
        if(updateCount>0){
            result="修改成功";
        }else {
            result = "修改失败";
        }
        return result;
    }

    @RequestMapping("/findAllApplyUserInfo")
    @ResponseBody
    public Object findAllApplyUserInfo(String rows,String page,String ssq,String ssj,String sfzh,String username,HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        if (StringUtils.isEmpty(page)){
            page="1";
        }
        if (StringUtils.isEmpty(rows)){
            rows="20";
        }
        map.put("rows",rows);
        map.put("page",page);
        /*市区的查询设置，如果用户所属区为市区，则不设置区的查询条件*/
        if("0".equals(ssq)){
            ssq="";
        }
        map.put("ssq",ssq);
        map.put("ssj",ssj);
        map.put("username",username);
        map.put("sfzh",sfzh);
        PageInfo<ApplyUserinfo> pageInfo=this.applyUserinfoService.selectAll(map);
        Map<String,Object> result=new HashMap<String, Object>();
        result.put("rows",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }

    @RequestMapping("/selectAllApp")
    @ResponseBody
    public  Object selectAllApp(String usercode,String username, String sfzh,String linktel){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("usercode",usercode);
        map.put("username",username);
        map.put("sfzh",sfzh);
        map.put("linktel",linktel);
        List<ApplyUserinfo> list=this.applyUserinfoService.selectAllApp(map);
        return  list;
    }

    @RequestMapping("/SendCode")
    @ResponseBody
    public Object SendCode(String linktel,HttpSession session){
        String randomStr = Random.ScRandom();
        try {
            session.setAttribute("Verification",randomStr );
            session.setMaxInactiveInterval(60);
            SendPhoneCodeUtil.sendTplSms(linktel,"JSM41823-0043","@1@="+randomStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  "1";
    }

    @RequestMapping("/newSend")
    @ResponseBody
    public Object newSend(HttpSession session){
        session.setAttribute("Verification", Random.ScRandom());
        return  "刷新";
    }



    @RequestMapping("/submitCode")
    @ResponseBody
    public Object submitCode(String checkcode,HttpSession session){
        String result="";
        System.out.println("nul--"+session.getAttribute("Verification"));
        if (session.getAttribute("Verification")==null){
            result="验证码已过期";
            return  result;
        }
        String randomStr= (String) session.getAttribute("Verification");
        if (checkcode.equals(randomStr)){
            result="成功";
            session.setAttribute("Verification", Random.ScRandom());
        }else{
            result="验证码输入错误";
        }
        return  result;
    }


    /**
     * 登录过程
     * @param sfzh
     * @param userpwd
     * @param checkcode
     * @param request
     * @return
     */
    @RequestMapping(value = "/loadMenu",method = RequestMethod.POST)
    public ModelAndView loadMenu(@RequestParam(value = "sfzh")String sfzh, @RequestParam(value = "userpwd")String userpwd, @RequestParam(value = "checkcode")String checkcode, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,Object> map=new HashMap<String, Object>();
        ModelAndView modelAndView=new ModelAndView();
        HttpSession session =request.getSession();

        /*清空身份证号、密码、验证码的空格*/
        sfzh=sfzh.trim();
        userpwd=userpwd.trim();
        checkcode=checkcode.trim();

        /*比较验证码是否输入正确*/
        Object object = session.getAttribute("code");
        /*如果没有验证码*/
        if(object==null){
            String path = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
            ApplyUserinfo user=(ApplyUserinfo) request.getSession().getAttribute("applyUserinfo");
            if (user==null) {
                response.sendRedirect(basePath+"applyUserinfo/toapplogin");
                return  null;
            }
        }
        String generatorCode =object.toString();
        String goUrl = "applicationForm/type";
        modelAndView.addObject("sfzh",sfzh);
        modelAndView.addObject("userpwd",userpwd);
        if(!checkcode.equalsIgnoreCase(generatorCode)){
            request.setAttribute("message", "验证码不正确！");
            modelAndView.setViewName("index/applogin");
            return modelAndView;
        }
        map.put("sfzh",sfzh);
        // ApplyUserinfo applyUserinfo = applyUserinfoService.selectAll(map);
        try {
            System.out.println("AESUtil.encryptAES(userpwd):"+userpwd+"----"+AESUtil.encryptAES(userpwd));
            map.put("userpwd", AESUtil.encryptAES(userpwd));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result="";
        ApplyUserinfo applyUserinfo=this.applyUserinfoService.selectUserInfo(map);
        if (applyUserinfo==null){
            modelAndView.addObject("message","用户名或密码错误");
            Map<String,Object> codeMap = CodeUtil.generateCodeAndPic();
            session.setAttribute("code",codeMap.get("code"));
            modelAndView.setViewName("index/applogin");
            return modelAndView;
        }else  if ("0".equals(applyUserinfo.getState())){
            modelAndView.addObject("message","用户为禁用状态");
            modelAndView.setViewName("index/applogin");
            return modelAndView;
        }
        session.setAttribute("applyUserinfo",applyUserinfo);
        modelAndView.setViewName("applicationForm/index");

        /*审核端一权限账号登录，以获得shrio的接口认证，在申请端调用一些需要shiro认证才能通过的接口*/
       /* Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wangli1", AESUtil.encryptAES("123456"));
        subject.login(token);*/
        return modelAndView;
    }







    @RequestMapping("insertUserInfo")
    @ResponseBody
    public Object insertUserInfo(String checkcode,ApplyUserinfo applyUserinfo,HttpSession session){
        String result="";//返回结果集
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("usercode", applyUserinfo.getUsercode());//用户登录账号
        map.put("sfzh", applyUserinfo.getSfzh());//身份证号
        ApplyUserinfo applyUserinfo1 = this.applyUserinfoService.selectUserInfo(map);
        if (session.getAttribute("Verification")==null){
            result="验证码已过期";
            return  result;
        }
        String randomStr= (String) session.getAttribute("Verification");
        if (!checkcode.equals(randomStr)) {
            result = "验证码输入错误";
            //session.setAttribute("Verification", Random.ScRandom());
            return result;
        }
        /*如果用户为空，则可以插入*/
        if (applyUserinfo1 == null) {
            /*公安接口认证*/
            String resultjg=Aliyun.APISTORE("http://v.apis.la/api/idcard/dcidcard",
                    "realName="+applyUserinfo.getUsername()+"&cardNo="+applyUserinfo.getSfzh(),
                    "88f2ea464e1540ab8db0e20a80a7e0ad","POST");
            JSONObject object = JSONObject.fromObject(resultjg);
            if(!"0".equals(String.valueOf(object.get("error_code")))){
                return object.get("reason");
            }
            JSONArray jsa=JSONArray.fromObject(object.get("result"));
            JSONObject jsonObject=jsa.getJSONObject(0).getJSONObject("details");
            String sex="";
            if ("1".equals(String.valueOf(jsonObject.get("sex")))){
                sex="男";
            }else{
                sex="女";
            }
            System.out.println("object.get(reason)--"+object.get("reason"));
            if (!"一致".equals(object.get("reason"))) {
                result="姓名和身份证号码不匹配";
                return result;
            }else  if (!applyUserinfo.getSex().equals(sex)){
                result="性别与身份证上面不符";
                return result;
            }
            /*密码加密*/
            try {
                applyUserinfo.setUserpwd(AESUtil.encryptAES(applyUserinfo.getUserpwd()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*接口认证通过则调用接口插入*/
            applyUserinfo.setUserid(CommonUtils.getUUIDWith_());
            applyUserinfo.setState("0");
            Integer add = this.applyUserinfoService.insert(applyUserinfo);
            if (add > 0) {
                result="注册成功";
            } else {
                result="注册失败";
            }
        }  else{
            result="身份证号码只能注册一次";
        }
        return result;
    }

    /**
     * 公安接口校验用户
     * @param request
     * @return
     */
    @RequestMapping("checkUser")
    @ResponseBody
    public String checkUser(HttpServletRequest request){
        try {
            String userName = request.getParameter("userName");//用户名
            String userSfzh = request.getParameter("sfzh");//身份证
            if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(userSfzh)){
                return "请输入需验证人的姓名与身份证号!";
            }
            /*公安接口认证*/
            String resultjg = Aliyun.APISTORE("http://v.apis.la/api/idcard/dcidcard",
                    "realName="+userName+"&cardNo="+userSfzh,
                    "88f2ea464e1540ab8db0e20a80a7e0ad","POST");
            JSONObject object = JSONObject.fromObject(resultjg);
            if(!"0".equals(String.valueOf(object.get("error_code")))){
                return String.valueOf(object.get("reason"));
            }
            JSONArray jsa=JSONArray.fromObject(object.get("result"));
            JSONObject jsonObject=jsa.getJSONObject(0).getJSONObject("details");

            System.out.println("object.get(reason)--"+object.get("reason"));
            if (!"一致".equals(object.get("reason"))) {
                return "姓名和身份证号码不匹配";
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }


    /**
     * 公安接口批量用户认证
     * @param usernames
     * @param sfzhs
     * @return
     */
    @RequestMapping("checkUsers")
    @ResponseBody
    public String checkUser(@RequestParam(value = "usernames")String usernames,@RequestParam(value = "sfzhs")String sfzhs){
        try {
           /* String userName = request.getParameter("usernames");//用户名
            String userSfzh = request.getParameter("sfzhs");//身份证*/
            if(StringUtils.isEmpty(usernames) || StringUtils.isEmpty(sfzhs)){
                return "请输入需验证人的姓名与身份证号!";
            }
            String[] usernameArr = usernames.split(",");
            String[] sfzhArr = sfzhs.split(",");
            String result = "";
            for(int i=0;i<usernameArr.length;i++){
                /*公安接口认证*/
                String resultjg = Aliyun.APISTORE("http://v.apis.la/api/idcard/dcidcard",
                        "realName="+usernameArr[i]+"&cardNo="+sfzhArr[i],
                        "88f2ea464e1540ab8db0e20a80a7e0ad","POST");
                JSONObject object = JSONObject.fromObject(resultjg);
                /*记录下验证为通过的用户姓名*/
                if(!"0".equals(String.valueOf(object.get("error_code")))){
                    if(StringUtils.isEmpty(result)){
                        result=usernameArr[i];
                    }else{
                        result=result+","+usernameArr[i];
                    }
                    continue;
                   // return String.valueOf(object.get("reason"));
                }
                JSONArray jsa=JSONArray.fromObject(object.get("result"));
                JSONObject jsonObject=jsa.getJSONObject(0).getJSONObject("details");

                System.out.println("object.get(reason)--"+object.get("reason"));
                /*记录下验证为通过的用户姓名*/
                if (!"一致".equals(object.get("reason"))) {
                    if(StringUtils.isEmpty(result)){
                        result=usernameArr[i];
                    }else{
                        result=result+","+usernameArr[i];
                    }
                }
            }

            /*返回结果*/
            if(StringUtils.isEmpty(result)){
                return "success";
            }else{
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 查询个人申请信息
     * @param session
     * @return
     */
    @RequestMapping("/findPerOldData")
    @ResponseBody
    public Object findPerOldData(HttpSession session){
        ApplyUserinfo applyUserinfo =(ApplyUserinfo)session.getAttribute("applyUserinfo");
        /*通过用户id查询审批单*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("userid",applyUserinfo.getUserid());//userid
        conditionMap.put("applyTypeNotLike","ns");//年审
        Approve approve = approveMapper.findByMap(conditionMap);

        List<Map<String,Object>> mapList = Lists.newArrayList();
        /*如果审批单为空，但是存在家庭成员和申请但数据，则认为是原有的老数据，系统会跳到老数据界面*/
        if(approve==null){
            conditionMap.put("sfzh",applyUserinfo.getSfzh());
            return this.applyForForeignService.selectOldListByMap(conditionMap);
        }else{
            return null;
        }
    }

    /**
     * 获取未激活的用户列表
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @RequestMapping("/findNoActiveUsers")
    @ResponseBody
    public Object findNoActiveUsers(String page,String rows,HttpServletRequest request){

        String userName = request.getParameter("username");//用户名
        String userSfzh = request.getParameter("sfzh");//身份证
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        Map<String,Object> map=new HashMap<String, Object>();

        if(userInfo.getSsj()!=null
                &&userInfo.getSsj()!=""){
            if(page==null||page==""){
                page="1";
            }
            if(rows==null||rows==""){
                rows="20";
            }
            map.put("page",page);
            map.put("rows",rows);
            map.put("ssq",userInfo.getSsq());
            map.put("ssj",userInfo.getSsj());
            map.put("username",userName);
            map.put("sfzh",userSfzh);
            PageInfo<ApplyUserinfo> pageInfo=applyUserinfoService.selectNoActiv(map);
            map.remove("ssq");
            map.remove("ssj");
            map.put("rows",pageInfo.getList());
            map.put("total",pageInfo.getTotal());

        }
        return map;

    }
    /**
     * 激活用户
     * @param request
     * @return
     */
    @RequestMapping("/updateState")
    @ResponseBody

    public Object updateState(HttpServletRequest request){
        String result="";
        String userid = request.getParameter("userid");//
        System.out.println("userid  "+userid);
        String state = "1";//
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("userid",userid);
        map.put("state",state);
        int updateCount=applyUserinfoService.updateUserState(map);

        if(updateCount>0){
            result="激活成功";
        }else{
            result="激活失败";
        }
        return result;

    }


}
