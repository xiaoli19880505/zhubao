package com.sys.controller.apply;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.common.PropertiesUtil;
import com.sys.mapper.apply.ApproveMapper;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.UserInfo;
import com.sys.pojo.apply.*;
import com.sys.service.ApplyUserinfoService;
import com.sys.service.RoleInfoService;
import com.sys.service.apply.*;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc:年审控制类
 * @Author:wangli
 * @CreateTime:16:22 2018/10/18
 */
@Controller
@RequestMapping("/applyns")
public class ApplyNsController {


    @Autowired
    private ApplyNsService applyNsService;

    @Autowired
    private ApproveService approveService;

    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RoleInfoService roleInfoService;
    @Autowired
    private ApplyUserinfoService applyUserinfoService;

    /**
     * 跳转到申请单新增页面
     * @param applyType
     * @return
     */
    @RequestMapping("toAddNsApply")
    public String toAddNsApply(String applyType,String applyid,HttpServletRequest request){
        String toPath = "";
        if(applyType.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))){
            toPath="applicationForm/perNsApply/perApplyWlwAdd";
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))){
            toPath="applicationForm/perNsApply/perApplyXjyAdd";
        }if(applyType.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))){
            toPath="applicationForm/perNsApply/perApplyJsfAdd";
        }if(applyType.equals(PropertiesUtil.getApplyTypeProperties("butie"))){
            toPath="applicationForm/perNsApply/perApplyButieAdd";
        }if(applyType.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))){
            toPath="applicationForm/perNsApply/perApplyLzfAdd";
        }
        request.setAttribute("applyType",applyType);
        request.setAttribute("applyid",applyid);
        return  toPath;
    }

    /**
     * 查询个人年审信息
     * @return
     */
    @RequestMapping("getPerNsApply")
    @ResponseBody
    public Object getPerNsApply(String page,String rows,HttpSession session){
        /*获取申请用户的信息*/
        ApplyUserinfo applyUserinfo = (ApplyUserinfo)session.getAttribute("applyUserinfo");
        String userid = applyUserinfo.getUserid();
        if(page==null||page==""){
            page="1";
        }
        if(rows==null||rows==""){
            rows="20";
        }
        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("userid",userid);//用户id
        conditionMap.put("applyType","ns");//用户申请类型为年审类型
        conditionMap.put("page",page);
        conditionMap.put("rows",rows);
        //Approve approve = approveService.findByMap(conditionMap);
        PageInfo<Approve> approveList = approveService.findListByMap(conditionMap);

        if(approveList.getList()!=null && approveList.getList().size()>0){
            for (Approve approve:approveList.getList()) {
                if(approve.getState()!=null && !"".equals(approve.getState())){
                    if(approve.getState().indexOf("审批全部通过")!=-1
                            ||approve.getState().indexOf("驳回")!=-1){
                        List<HistoricTaskInstance> historicTaskInstance= historyService.createHistoricTaskInstanceQuery()
                                .processInstanceId(approve.getProcessinstanceid())
                                .orderByTaskCreateTime().desc().list();
                        //历史
                        if(historicTaskInstance!=null){
                            List<HistoricIdentityLink> idenList = historyService.getHistoricIdentityLinksForTask(historicTaskInstance.get(0).getId());
                            if(idenList!=null &&idenList.size()>0){
                                approve.setProcessinstanceid(this.roleInfoService.selectById(idenList.get(0).getGroupId()).getDutyname());
                            }
                        }


                    }else{
                        Task task= taskService.createTaskQuery()
                                .processInstanceId(approve.getProcessinstanceid())
                                .singleResult();

                        if(task!=null){
                            List<IdentityLink> idenList = taskService.getIdentityLinksForTask(task.getId());
                            if(idenList!=null &&idenList.size()>0){
                                approve.setProcessinstanceid(this.roleInfoService.selectById(idenList.get(0).getGroupId()).getDutyname());
                            }
                        }

                    }
                }else{
                    Task task= taskService.createTaskQuery()
                            .processInstanceId(approve.getProcessinstanceid())
                            .singleResult();

                    if(task!=null){
                        List<IdentityLink> idenList = taskService.getIdentityLinksForTask(task.getId());
                        if(idenList!=null &&idenList.size()>0){
                            approve.setProcessinstanceid(this.roleInfoService.selectById(idenList.get(0).getGroupId()).getDutyname());
                        }
                    }
                }

            }
        }


        conditionMap.put("rows",approveList.getList());
        conditionMap.put("total",approveList.getTotal());
        return conditionMap;
    }

    /**
     * 跳转到申请单修改页面
     * @param
     * @return
     */
    @RequestMapping("toUpdateNsApply")
    public String toUpdateNsApply(HttpServletRequest request){
        String applyType = request.getParameter("applyType");//获取申请类型
        String applyId = request.getParameter("applyId");//获取申请单id
        String toPath = "";
        if(applyType.equals(PropertiesUtil.getApplyTypeProperties("nswailaiwg"))){
            toPath="applicationForm/perNsApply/perApplyWlwUpdate";
        }else if(applyType.equals(PropertiesUtil.getApplyTypeProperties("nsxinjy"))){
            toPath="applicationForm/perNsApply/perApplyXjyUpdate";
        }if(applyType.equals(PropertiesUtil.getApplyTypeProperties("nsbutie"))){
            toPath="applicationForm/perNsApply/perApplyButieUpdate";
        }if(applyType.equals(PropertiesUtil.getApplyTypeProperties("nsgongzf"))){
            toPath="applicationForm/perNsApply/perApplyLzfUpdate";
        }
        request.setAttribute("applyId",applyId);
        request.setAttribute("applyType",applyType);
        return  toPath;
    }


    /**
     * 添加申请信息
     * @return
     */
    @RequestMapping("addApplyNsInfo")
    @ResponseBody
    public Object addApplyInfo(ApplyNs apl,HttpServletRequest request){
        /*在添加年审申请单之前，先判断用户是否存在已经通过的审批单*/
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String conditionResult = applyUserinfoService.toPerNsApplyCondition(request);

        /*不符合年审条件的给予提示*/
        if(!"success".equals(conditionResult)){
            resultMap.put("result",conditionResult);
            return resultMap;
        }

        /*发起年审请求*/
        String aptype = request.getParameter("applyType");
        String appid = request.getParameter("applyid");
        apl.setApSqlb(aptype);
        apl.setApSqid(appid);

        /*ApplyNs aplff = new ApplyNs();
        setApplyNs(aplff,request);*/
        String result="";//bzfxm返回结果字符
        String applyid="";//返回的applyid
        String addResult = applyNsService.addApply(apl,request);
        if("success".equals(addResult)){
            result="success";
            applyid=apl.getAlsid();
        }
        resultMap.put("result",result);
        resultMap.put("applyid",applyid);
        return resultMap;
    }

    /**
     * 提交修改后的年审
     * @return
     */
    @RequestMapping("updateNsInfo")
    @ResponseBody
    public Object updateNsInfo(ApplyNs applyNs){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String result="";//返回结果字符
        String updateResult=applyNsService.updateNsInfo(applyNs);
        if("success".equals(updateResult)){
            result="success";
        }
        resultMap.put("result",result);
        resultMap.put("applyid",applyNs.getAlsid());
        return resultMap;
    }


    /**
     * 手动设置外来户申请单实体
     * @param aplff
     * @param request
     */
    private void setApplyNs(ApplyNs aplff,HttpServletRequest request){

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


}