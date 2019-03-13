package com.sys.controller.apply;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sys.common.PropertiesUtil;
import com.sys.common.StringUtils;
import com.sys.common.dataconver.BaseInfoDataConvertor;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.ParmItem;
import com.sys.pojo.UserInfo;
import com.sys.pojo.apply.*;
import com.sys.service.ApplyUserinfoService;
import com.sys.service.FlowService;
import com.sys.service.ParmItemService;
import com.sys.service.RoleInfoService;
import com.sys.service.apply.*;
import com.sys.service.common.MessageAndFormService;
import com.sys.service.serialnum.SerialNumService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/16 0016
 * @desc
 */
@Controller
@RequestMapping("/appove")
public class ApproveController {
    @Autowired
    ApproveService appoveService;
    @Autowired
    ApplyForForeignService applyForForeignService;
    @Autowired
    ApplyForgraDuateService applyForgraDuateService;
    @Autowired
    ApplyButieService applyButieService;

    @Autowired
    ApplyService applyService;

    @Autowired
    ApplyNsService applyNsService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RoleInfoService roleInfoService;

    @Autowired
    private ApproveService approveService;

    @Autowired
    private SerialNumService serialNumService;
    @Autowired
    private ParmItemService parmItemService;

    @Autowired
    private FlowService flowService;

    @Autowired
    private MessageAndFormService messageAndFormService;

    @Autowired
    private ApproveRecordService approveRecordService;

    @Autowired
    private ApplyUserinfoService applyUserinfoService;

    /**
     * 我的申请
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/findAppoveSQByUser")
    @ResponseBody
    public Object findAppoveSQByUser(String page,String rows,HttpServletRequest request){
        ApplyUserinfo applyUserinfo = (ApplyUserinfo)request.getSession().getAttribute("applyUserinfo");
        String apluserid=applyUserinfo.getUserid();
        if(page==null||page==""){
            page="1";
        }
        if(rows==null||rows==""){
            rows="20";
        }
        Map<String,Object> map=new HashMap();
        map.put("page",page);
        map.put("rows",rows);
        map.put("apluserid",apluserid);
        List<Approve> appovePageInfo=appoveService.page(map);
        map.remove("page");
        map.remove("rows");
        map.remove("apluserid");

        if(appovePageInfo!=null && appovePageInfo.size()>0){
            for (Approve approve:appovePageInfo) {
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

        map.put("rows",appovePageInfo);
        map.put("total",appovePageInfo.size());
        return map;
    }

    /**
     * 查询申请审核信息
     * @param request
     * @return
     */
    @RequestMapping("findApplyForApprove")
    @ResponseBody
    public Object findApplyForApprove(HttpServletRequest request){
        String rows = request.getParameter("rows");
        String page = request.getParameter("page");
        String aptype = request.getParameter("aptype");//申请类型
        String aplbh = request.getParameter("aplbh");//申请编号
        String xm = request.getParameter("xm");//姓名
        PageInfo pageInfo = (PageInfo)appoveService.findApplyForApprove(aptype,aplbh,xm,page,rows);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    }

    /**
     * 查询申请审核信息
     * @param request
     * @return
     */
    @RequestMapping("toApplyForApprove")
    public Object toApplyForApprove(HttpServletRequest request){
//        String apvid = request.getParameter("apvid");//审批ID:apvid
        String aptype = request.getParameter("aptype");//申请类型
        String aplid = request.getParameter("aplid");//申请类型
        String url = "";
        Map<String,Object> map=new HashMap<String,Object>();
        ApproveRecord approveRecord = new ApproveRecord();
        approveRecord.setOrderBy("asc");
        approveRecord.setApplyId(aplid);
        List<ApproveRecord> approveRecords = (List<ApproveRecord>)approveRecordService.select(approveRecord);
        request.setAttribute("approveRecords",approveRecords);
        if("1".equals(aptype) || "3".equals(aptype)){//经济适用、公租
            map.put("apId",aplid);
            Apply apply=applyService.selectByMap(map);
            //查询所属区、街道地址
            Map<String,Object> parmMap1 = new HashMap<String, Object>();//区域
            parmMap1.put("piSetcode","04");
            parmMap1.put("piItemcode",apply.getApSsq());
            List<ParmItem> parmItems1 = parmItemService.selectBySetCodeAndItemCode(parmMap1);
            apply.setSsqStr(parmItems1!=null?parmItems1.size()>0?parmItems1.get(0).getPiItemname():null:null);//所属区中文
            Map<String,Object> parmMap2 = new HashMap<String, Object>();//区域
            parmMap2.put("piSetcode","05");
            parmMap2.put("piItemcode",apply.getApJdbsc());
            List<ParmItem> parmItems2 = parmItemService.selectBySetCodeAndItemCode(parmMap2);
            apply.setSsjStr(parmItems2!=null?parmItems2.size()>0?parmItems2.get(0).getPiItemname():null:null);//所属街道中文
            Collections.sort(apply.getApplyFamilyMembers());
            List<ApplyFamilyHouse> applyFamilyHouses = apply.getApplyFamilyHouses();
            if(applyFamilyHouses==null){
                applyFamilyHouses = new ArrayList<ApplyFamilyHouse>();
            }else if(applyFamilyHouses.size()>2){
                List<ApplyFamilyHouse> applyFamilyHouseList = new ArrayList<ApplyFamilyHouse>();
                applyFamilyHouseList.add(applyFamilyHouses.get(0));
                applyFamilyHouseList.add(applyFamilyHouses.get(1));
                applyFamilyHouses = applyFamilyHouseList;
            }
            for(int i=0;i<2-applyFamilyHouses.size();i++){
                applyFamilyHouses.add(new ApplyFamilyHouse());
            }
            //同住家庭成员
            List<ApplyFamilyMember> applyFamilyMembers = apply.getApplyFamilyMembers();
            ApplyFamilyMember applyFamilyMember = new ApplyFamilyMember();
            if(applyFamilyMembers==null){
                applyFamilyMembers = new ArrayList<ApplyFamilyMember>();
            }
            int size = applyFamilyMembers.size();
            for (int i=0;i<5-size;i++){
                applyFamilyMembers.add(applyFamilyMember);
            }
            request.setAttribute("apply",apply);
            if("1".equals(aptype)){
                url = "jingsfPrint";
            }else{
                url = "gongzfPrint";
            }
        }else if("2".equals(aptype)){//补贴
            map.put("abId",aplid);
            ApplyButie applyButie=applyButieService.selectByMap(map);
            Collections.sort(applyButie.getApplyFamilyMembers());
            List<ApplyFamilyHouse> applyFamilyHouses = applyButie.getApplyFamilyHouses();
            BigDecimal sumArea = new BigDecimal("0");
            for (ApplyFamilyHouse applyFamilyHouse : applyFamilyHouses){
                String afhMj = applyFamilyHouse.getAfhMj();
                sumArea = sumArea.add(new BigDecimal(afhMj));
            }
            applyButie.setSumArea(sumArea);//总租房面积
            //查询所属区、街道地址
            Map<String,Object> parmMap1 = new HashMap<String, Object>();//区域
            parmMap1.put("piSetcode","04");
            parmMap1.put("piItemcode",applyButie.getAbSsq());
            List<ParmItem> parmItems1 = parmItemService.selectBySetCodeAndItemCode(parmMap1);
            applyButie.setSsqStr(parmItems1!=null?parmItems1.size()>0?parmItems1.get(0).getPiItemname():null:null);//所属区中文
            Map<String,Object> parmMap2 = new HashMap<String, Object>();//区域
            parmMap2.put("piSetcode","05");
            parmMap2.put("piItemcode",applyButie.getAbJdbsc());
            List<ParmItem> parmItems2 = parmItemService.selectBySetCodeAndItemCode(parmMap2);
            applyButie.setSsjStr(parmItems2!=null?parmItems2.size()>0?parmItems2.get(0).getPiItemname():null:null);//所属街道中文
            if(applyFamilyHouses==null){
                applyFamilyHouses = new ArrayList<ApplyFamilyHouse>();
            }else if(applyFamilyHouses.size()>2){
                List<ApplyFamilyHouse> applyFamilyHouseList = new ArrayList<ApplyFamilyHouse>();
                applyFamilyHouseList.add(applyFamilyHouses.get(0));
                applyFamilyHouseList.add(applyFamilyHouses.get(1));
                applyFamilyHouses = applyFamilyHouseList;
            }
            for(int i=0;i<2-applyFamilyHouses.size();i++){
                applyFamilyHouses.add(new ApplyFamilyHouse());
            }
            //同住家庭成员
            List<ApplyFamilyMember> applyFamilyMembers = applyButie.getApplyFamilyMembers();
            ApplyFamilyMember applyFamilyMember = null;
            if(applyFamilyMembers==null){
                applyFamilyMembers = new ArrayList<ApplyFamilyMember>();
            }
            int size = applyFamilyMembers.size();
            for (int i=0;i<5-size;i++){
                applyFamilyMembers.add(applyFamilyMember);
            }
            request.setAttribute("applyButie",applyButie);
            url = "butiePrint";
        }else if("4".equals(aptype)){//外来
            map.put("affId",aplid);
            ApplyForForeign applyForForeign=applyForForeignService.selectByMap(map);
            //查询所属区、街道地址
            Map<String,Object> parmMap1 = new HashMap<String, Object>();//区域
            parmMap1.put("piSetcode","04");
            parmMap1.put("piItemcode",applyForForeign.getAffSsq());
            List<ParmItem> parmItems1 = parmItemService.selectBySetCodeAndItemCode(parmMap1);
            applyForForeign.setSsqStr(parmItems1!=null?parmItems1.size()>0?parmItems1.get(0).getPiItemname():null:null);//所属区中文
            Map<String,Object> parmMap2 = new HashMap<String, Object>();//区域
            parmMap2.put("piSetcode","05");
            parmMap2.put("piItemcode",applyForForeign.getAffDwdz());
            List<ParmItem> parmItems2 = parmItemService.selectBySetCodeAndItemCode(parmMap2);
            applyForForeign.setSsjStr(parmItems2!=null?parmItems2.size()>0?parmItems2.get(0).getPiItemname():null:null);//所属街道中文
            applyForForeign.setAffLxjzsj(applyForForeign.getAffLxjzsj()==null?"":
            applyForForeign.getAffLxjzsj().substring(0,8).replaceFirst("-","年")
                    .replaceFirst("-","月"));//来徐时间
            applyForForeign.setAffLdhtkssj(applyForForeign.getAffLdhtkssj()==null?"":
                    applyForForeign.getAffLdhtkssj().substring(0,8).replaceFirst("-","年")
                            .replaceFirst("-","月"));//劳动合同开始时间
            applyForForeign.setAffLdhtjssj(applyForForeign.getAffLdhtjssj()==null?"":
                    applyForForeign.getAffLdhtjssj().substring(0,8).replaceFirst("-","年")
                            .replaceFirst("-","月"));//劳动合同结束时间
            applyForForeign.setAffSbjnsj(applyForForeign.getAffSbjnsj()==null?"":
                    applyForForeign.getAffSbjnsj().substring(0,8).replaceFirst("-","年")
                            .replaceFirst("-","月"));//社保缴纳时间
            applyForForeign.setAffGjjjnsj(applyForForeign.getAffGjjjnsj()==null?"":
                    applyForForeign.getAffGjjjnsj().substring(0,8).replaceFirst("-","年")
                            .replaceFirst("-","月"));//公积金缴纳时间
            Collections.sort(applyForForeign.getApplyFamilyMembers());
            //同住家庭成员
            List<ApplyFamilyMember> applyFamilyMembers = applyForForeign.getApplyFamilyMembers();
            ApplyFamilyMember applyFamilyMember = null;
            if(applyFamilyMembers==null){
                applyFamilyMembers = new ArrayList<ApplyFamilyMember>();
            }
            int size = applyFamilyMembers.size();
            for (int i=0;i<6-size;i++){
                applyFamilyMembers.add(applyFamilyMember);
            }
            request.setAttribute("applyForForeign",applyForForeign);
            url = "wailaiwgPrint";
        }else if("5".equals(aptype)){//新就业
            map.put("afgId",aplid);
            ApplyForgraDuate applyForgraDuate=applyForgraDuateService.selectByMap(map);
            Collections.sort(applyForgraDuate.getApplyFamilyMembers());
            //设置社保缴纳时间和公积金缴纳时间,合同签订时间区间
            applyForgraDuate.setAfgSbjnsj(applyForgraDuate.getAfgSbjnsj()==null?"":
                    applyForgraDuate.getAfgSbjnsj().substring(0,8).replaceFirst("-","年")
                            .replaceFirst("-","月"));//社保缴纳时间
            applyForgraDuate.setAfgGjjjnsj(applyForgraDuate.getAfgGjjjnsj()==null?"":
                    applyForgraDuate.getAfgGjjjnsj().substring(0,8).replaceFirst("-","年")
                            .replaceFirst("-","月"));//公积金缴纳时间
            applyForgraDuate.setAfgLdhtkssj(applyForgraDuate.getAfgGjjjnsj()==null?"":
                    applyForgraDuate.getAfgLdhtkssj().substring(0,8).replaceFirst("-","年")
                            .replaceFirst("-","月"));//合同签订
            applyForgraDuate.setAfgLdhtjssj(applyForgraDuate.getAfgGjjjnsj()==null?"":
                    applyForgraDuate.getAfgLdhtjssj().substring(0,8).replaceFirst("-","年")
                            .replaceFirst("-","月"));//合同签订
            List<ApplyFamilyMember> applyFamilyMembers = applyForgraDuate.getApplyFamilyMembers();
            ApplyFamilyMember applyFamilyMember = new ApplyFamilyMember();
            if(applyFamilyMembers==null){
                applyFamilyMembers = new ArrayList<ApplyFamilyMember>();
            }
            int size = applyFamilyMembers.size();
            for (int i=0;i<6-size;i++){
                applyFamilyMembers.add(applyFamilyMember);
            }

            request.setAttribute("applyForgraDuate",applyForgraDuate);
            url = "xinjyPrint";
        }
        return "applicationForm/print/" + url;
    }

    /**
     * 我的年审
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/findAppoveNSByUser")
    @ResponseBody
    public Object findAppoveNSByUser(String page,String rows){
        String apluserid="0101010101010101";
        if(page==null||page==""){
            page="1";
        }
        if(rows==null||rows==""){
            rows="20";
        }
        Map<String,Object> map=new HashMap();
        map.put("page",page);
        map.put("rows",rows);
        map.put("apluserid",apluserid);
        map.put("type","ns");
        List<Approve> appovePageInfo=appoveService.page(map);
        map.remove("page");
        map.remove("rows");
        map.remove("apluserid");
        map.remove("type");
        map.put("rows",appovePageInfo);
        map.put("total",appovePageInfo.size());
        return map;
    }

    /**
     * 获取我的申请详细，用于页面回显
     * @param aptype
     * @param aplid
     * @param request
     * @return
     */
    @RequestMapping("/getSqApproveDetail")
    @ResponseBody
    public Object getSqApproveDetail(String aptype, String aplid,HttpServletRequest request){

        Map<String,Object> map=new HashMap<String,Object>();

        if(aptype.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))){//经济适用房--1
            map.put("apId",aplid);
            Apply apply=applyService.selectByMap(map);
            Collections.sort(apply.getApplyFamilyMembers());
            request.setAttribute("apply",apply);
            return apply;
        }else if(aptype.equals(PropertiesUtil.getApplyTypeProperties("butie"))){//低保特困补贴--2
            map.put("abId",aplid);
            ApplyButie applyButie=applyButieService.selectByMap(map);
            Collections.sort(applyButie.getApplyFamilyMembers());
            request.setAttribute("applyButie",applyButie);
            return applyButie;
        }else if(aptype.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))){//低保特困公租房--3
            map.put("apId",aplid);
            Apply apply=applyService.selectByMap(map);
            Collections.sort(apply.getApplyFamilyMembers());
            request.setAttribute("apply",apply);
            return apply;
        }else if(aptype.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))){//外来务工公租房--4
            map.put("affId",aplid);
            ApplyForForeign applyForForeign=applyForForeignService.selectByMap(map);
            request.setAttribute("applyForForeign",applyForForeign);
            Collections.sort(applyForForeign.getApplyFamilyMembers());
            return applyForForeign;

        }else if(aptype.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))){//新就业公租房--5
            map.put("afgId",aplid);
            ApplyForgraDuate applyForgraDuate=applyForgraDuateService.selectByMap(map);
            Collections.sort(applyForgraDuate.getApplyFamilyMembers());
            request.setAttribute("applyForgraDuate",applyForgraDuate);
            return applyForgraDuate;
        }else{
            System.out.println("未知房屋类型");
            return "未知房屋类型";
        }
    }

    /**
     * 获取年审详细信息用于回显
     * @param
     * @param aplid
     * @param request
     * @return
     */
    @RequestMapping("/getNsApproveDetail")
    @ResponseBody
    public Object getNsApproveDetail(String aplid,HttpServletRequest request){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("aplid",aplid);
        ApplyNs applyNs=applyNsService.selectByMap(map);
        Collections.sort(applyNs.getApplyFamilyMembers());
        request.setAttribute("applyNs",applyNs);
        return applyNs;

    }

    @RequestMapping("toNsAudit")
    public String toNsAudit(HttpServletRequest request){
        String applyType = request.getParameter("applyType");//获取申请类型
        String applyId = request.getParameter("applyId");//获取申请单id
        String processInstanceId = request.getParameter("processInstanceId");//获取流程实例id
        String chengxin = request.getParameter("chengxin");//获取流程实例id

        System.out.println("applyType"+applyType);
        System.out.println("applyId"+applyId);
        System.out.println("processInstanceId"+processInstanceId);


        String toPath = "";
//        if(PropertiesUtil.getApplyTypeProperties("jingsf").equals(applyType)){//经济适用房
//            toPath = "houseAudit/auditDialog/approveJsf";
//        }else

        if(PropertiesUtil.getApplyTypeProperties("butie").equals(applyType)){//廉租房--低保特困补贴
            toPath = "houseAudit/auditDialog/approveButie";
            if(!StringUtils.isEmpty(processInstanceId)){
                /*如果当前节点的下一个节点为空，则当前节点为最后一个审批节点，设置reaudit标志给前端，用于发送通过审批通知*/
                List<TaskDefinition> taskDefinitionList = flowService.getTaskDefinitionList(processInstanceId);
                if(taskDefinitionList!=null && !taskDefinitionList.isEmpty()){
                    request.setAttribute("reaudit",0);
                }else{
                    /*如果是补贴的审批，*/
                    Map<String,Object> map = Maps.newHashMap();
                    map.put("processInstanceId",processInstanceId);//流程实例id
                    Approve approve = this.approveService.findByMap(map);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(approve.getApldate());
                    request.setAttribute("year",calendar.get(Calendar.YEAR));
                    request.setAttribute("reaudit",1);
                }
            }
        }else if(PropertiesUtil.getApplyTypeProperties("gongzf").equals(applyType)){//公租房--低保特困公租房
            toPath = "houseAudit/auditDialog/approveGzf";
        } else if (PropertiesUtil.getApplyTypeProperties("wailaiwg").equals(applyType)) {//外来务工--外来务工公租房
            toPath = "houseAudit/auditDialog/approveWlh";
        }else if(PropertiesUtil.getApplyTypeProperties("xinjy").equals(applyType)){//新就业--新就业公租房
            toPath = "houseAudit/auditDialog/approveXjy";
        }else if(PropertiesUtil.getApplyTypeProperties("nsgongzf").equals(applyType)){//公租房--低保特困公租房
            toPath = "houseAudit/auditDialog/approveGzf";
        } else if (PropertiesUtil.getApplyTypeProperties("nswailaiwg").equals(applyType)) {//外来务工--外来务工公租房
            toPath = "houseAudit/auditDialog/approveWlh";
        }else if(PropertiesUtil.getApplyTypeProperties("nsxinjy").equals(applyType)){//新就业--新就业公租房
            toPath = "houseAudit/auditDialog/approveXjy";
        }else if(PropertiesUtil.getApplyTypeProperties("nsbutie").equals(applyType)){//廉租房--低保特困补贴
            toPath = "houseAudit/auditDialog/approveButie";
            if(!StringUtils.isEmpty(processInstanceId)){
                /*如果当前节点的写一个节点为空，则当前节点为最后一个审批节点，设置reaudit标志给前端，用于发送通过审批通知*/
                List<TaskDefinition> taskDefinitionList = flowService.getTaskDefinitionList(processInstanceId);
                if(taskDefinitionList!=null && !taskDefinitionList.isEmpty()){
                    request.setAttribute("reaudit",0);
                }else{
                    /*如果是补贴的审批，*/
                    Map<String,Object> map = Maps.newHashMap();
                    map.put("processInstanceId",processInstanceId);//流程实例id
                    Approve approve = this.approveService.findByMap(map);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(approve.getApldate());
                    request.setAttribute("year",calendar.get(Calendar.YEAR));
                    request.setAttribute("reaudit",1);
                }
            }
        }
        request.setAttribute("applyId",applyId);
        request.setAttribute("applyType",applyType);
        request.setAttribute("processInstanceId",processInstanceId);
        request.setAttribute("chengxin",chengxin);
        return  toPath;
    }


    @RequestMapping("/findBySfzh")
    @ResponseBody
    public Object findBySfzh(String ssq,String aptype,HttpServletRequest request){
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        String uname = request.getParameter("uname");
        int page = 1;
        int rows = 20;
        if(!StringUtils.isEmpty(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if(!StringUtils.isEmpty(rowsStr)){
            rows = Integer.parseInt(rowsStr);
        }

        int pageIndex = (page-1)*rows;
        int pageLaseIndex = page*rows;
        Map<String,Object> map=new HashMap();
        map.put("pageIndex",pageIndex);
        map.put("pageLaseIndex",pageLaseIndex);
        map.put("aptype",aptype);
        if(StringUtils.isEmpty(ssq) || "0".equals(ssq)){
            ssq = null;
        }
        map.put("ssq",ssq);
        if(uname!=null&&uname!=""){
            map.put("uname",uname);
        }
        List<Map> appovePageInfo=appoveService.findByFylxAndSSQ(map);
        Map<String,Object> map2=new HashMap<String, Object>();
        map2.put("rows",appovePageInfo);
        map2.put("total",appoveService.findByFylxAndSSQCount(map));
        return map2;
    }

    /**
     * 放弃列表
     * @param request
     * @return
     */
    @RequestMapping("/houseQuitList")
    @ResponseBody
    public Object houseQuitList(HttpServletRequest request){
        String username = request.getParameter("username");
        String sfzh = request.getParameter("sfzh");
        String sqbh = request.getParameter("sqbh");
        String atype=request.getParameter("atype");
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        String ssq = request.getParameter("ssq");
        String ssj = request.getParameter("ssj");
        int page = 1;
        int rows = 20;
        if(!StringUtils.isEmpty(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if(!StringUtils.isEmpty(rowsStr)){
            rows = Integer.parseInt(rowsStr);
        }

        /*通过session获取所在区和街道信息*/
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        if(StringUtils.isEmpty(ssq)){
            ssq = userInfo.getParmItemssq().getPiItemname();
            if(userInfo.getParmItemssjd()!=null){
                ssj = userInfo.getParmItemssjd().getPiItemname();
            }
        }


        /*如果是市区用户，则不用设置市区查询条件*/
        if("市区".equals(ssq)){
            ssq="";
        }

        int pageIndex = (page-1)*rows;
        int pageLaseIndex = page*rows;
        Map<String,Object> map=new HashMap();
        map.put("pageIndex",pageIndex);
        map.put("pageLaseIndex",pageLaseIndex);
        if(atype!=null && !atype.equals("")){
            map.put("aptype",atype);
        }
        if(username!=null && !username.equals("")){
            map.put("username",username);
        }
        if(sfzh!=null && !sfzh.equals("")){
            map.put("sfzh",sfzh);
        }
        if(sqbh!=null && !sqbh.equals("")){
            map.put("aplbh",sqbh);
        }

        /*加上区和街道的条件搜索*/
        if(!StringUtils.isEmpty(ssq)){
            map.put("ssq",ssq);
        }
        if(!StringUtils.isEmpty(ssj)){
            map.put("ssj",ssj);
        }

        List<Map> appovePageInfo=appoveService.houseQuitList(map);
        Map<String,Object> map2=new HashMap<String, Object>();
        map2.put("rows",appovePageInfo);
        map2.put("total",appoveService.houseQuitListCount(map));
        return map2;
    }

    /**
     * 放弃操作
     * @param request
     * @return
     */
    @RequestMapping("/houseQuitOperate")
    @ResponseBody
    public Object houseQuitOperate(HttpServletRequest request){
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        String result="";
        String atype = request.getParameter("atype");
        String applyid = request.getParameter("applyid");
        String sqbh = request.getParameter("sqbh");
        String acremark = request.getParameter("acremark");
       /* System.out.println("**********"+atype);
        System.out.println("**********"+applyid);
        System.out.println("**********"+sqbh);
        System.out.println("**********"+acremark);*/
        int updateCont=appoveService.updatehouseQuitOperate(atype,applyid,sqbh,userInfo.getUserid(),acremark,request);
        if(updateCont>0){
            result="放弃成功";
        }else{
            result="放弃失败";
        }
        return result;
    }



    @RequestMapping("/findAllApply")
    @ResponseBody
    public Object findAllApply(HttpServletRequest request){
        String apSqlb = request.getParameter("apSqlb");
        String username = request.getParameter("username");
        String sfzh = request.getParameter("sfzh");
        String sqbh = request.getParameter("sqbh");
        String item = request.getParameter("item");
        String ssq = request.getParameter("ssq");
        String buname = request.getParameter("buname");
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        int page = 1;
        int rows = 20;
        if(!StringUtils.isEmpty(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if(!StringUtils.isEmpty(rowsStr)){
            rows = Integer.parseInt(rowsStr);
        }
        Map<String,Object> map=new HashMap();

        int pageIndex = (page-1)*rows;
        int pageLaseIndex = page*rows;
        if(apSqlb!=null && !apSqlb.equals("")){
            map.put("apSqlb",apSqlb);
        }
        if(username!=null && !username.equals("")){
            map.put("username",username);
        }
        if(ssq!=null && !"".equals(ssq) && !"0".equals(ssq)){
            map.put("ssq",ssq);
        }
        if(sfzh!=null && !sfzh.equals("")){
            map.put("sfzh",sfzh);
        }
        if(sqbh!=null && !sqbh.equals("")){
            map.put("sqbh",sqbh);
        }
        if(item!=null && !item.equals("")){
            map.put("item",item);
        }
        if(buname!=null && !buname.equals("")){
            map.put("buname",buname);
        }

        map.put("pageIndex",pageIndex);
        map.put("pageLaseIndex",pageLaseIndex);
        List<Map> appovePageInfo=appoveService.findAllApply(map);
        Map<String,Object> map2=new HashMap<String, Object>();
        map2.put("rows",appovePageInfo);
        map2.put("total",appoveService.findAllApplyCount(map));
        return map2;
    }

    /**
     * 已分房未签合同的人
     * @param request
     * @return
     */
    @RequestMapping("/findNoSign")
    @ResponseBody
    public Object findNoSign(HttpServletRequest request){
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        String username = request.getParameter("username");
        String sfzh = request.getParameter("sfzh");
        String aplbh = request.getParameter("aplbh");
        String aptype = request.getParameter("aptype");
        String ssq = request.getParameter("ssq");
        int page = 1;
        int rows = 20;
        if(!StringUtils.isEmpty(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if(!StringUtils.isEmpty(rowsStr)){
            rows = Integer.parseInt(rowsStr);
        }
        int pageIndex = (page-1)*rows;
        int pageLaseIndex = page*rows;
        Map<String,Object> map=new HashMap();
        map.put("pageIndex",pageIndex);
        map.put("pageLaseIndex",pageLaseIndex);

        if(!StringUtils.isEmpty(ssq)){
            map.put("ssq",ssq);
        }
        if(!StringUtils.isEmpty(aptype)){
            map.put("aptype",aptype);
        }
        if(username!=null && !username.equals("")){
            map.put("username",username);
        }
        if(sfzh!=null && !sfzh.equals("")){
            map.put("sfzh",sfzh);
        }
        if(aplbh!=null && !aplbh.equals("")){
            map.put("aplbh",aplbh);
        }

        List<Map> appovePageInfo=appoveService.findNoSignToMes(map);
        Map<String,Object> map2=new HashMap<String, Object>();
        map2.put("rows",appovePageInfo);
        map2.put("total",appoveService.countNoSignToMes(map));
        return map2;
    }

    @RequestMapping("/sendAllMessage")
    @ResponseBody
    public Object sendAllMessage(HttpServletRequest request){
        //aptype 外来务工新就业传3，低保特困公租房传2
        String aptype= request.getParameter("aptype");
        String username= request.getParameter("username");
        String sfzh= request.getParameter("sfzh");
        String aplbh= request.getParameter("aplbh");
        Map<String,Object> map1=new HashMap();
        if(aptype!=null &&aptype!=""){
            map1.put("aptype",aptype);
        }
        if(username!=null &&username!=""){
            map1.put("username",username);
        }
        if(sfzh!=null &&sfzh!=""){
            map1.put("sfzh",sfzh);
        }
        if(aplbh!=null &&aplbh!=""){
            map1.put("aplbh",aplbh);
        }


        List<Approve> appoveList=appoveService.findAllNoSign(map1);
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        String sqrq = request.getParameter("sqrq");//约定日期
        String doc = request.getParameter("doc");//传来的资料名称
        String area = request.getParameter("area");//办理业务地点
        String content = request.getParameter("content");//具体办理事项内容，多选框选择，如□选房、 □交余款、 □签订合同、 □上房；
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("appoveList",appoveList);
        map.put("userInfo",userInfo);
        map.put("sqrq",sqrq);
        map.put("doc",doc);
        map.put("area",area);
        map.put("content",content);
        appoveService.sendMessage(map);
        return "success";
    }



    /**
     * 判断是否可以添加年审
     * @return
     */
    @RequestMapping("/cangAddNs")
    @ResponseBody
    public Object cangAddNs(HttpServletRequest request){
        /**String result=PropertiesUtil.getApplyTypeProperties("nianshen");

         * 1.审核全部通过并且不是今年的
         * 2.没有年审信息
         * 3.被驳回的
         */
        /*获取申请用户的信息*/
        /*ApplyUserinfo applyUserinfo = (ApplyUserinfo)session.getAttribute("applyUserinfo");
        String userid = applyUserinfo.getUserid();
        Map<String,Object> conditionMap = Maps.newHashMap();

        //审核全部通过并且不是今年的
        conditionMap.put("userid",userid);//用户id
        conditionMap.put("applyType","ns");//用户申请类型为年审类型
        List<Approve> list=approveService.findApproveByMap(conditionMap);
        ParmItem parmItem=parmItemService.selectSwitch();
        *//*开关未设置或者未开启直接返回*//*
        if(parmItem!=null) {
            if (!"是".equals(parmItem.getPiItemname())) {
                result = PropertiesUtil.getApplyTypeProperties("shenqingtd");
                return result;
            }
        }else {
            result = PropertiesUtil.getApplyTypeProperties("shenqingtd");
            return result;
        }

        if(list!=null){
            if(list.size()==0){//未申请过
                result="success";
            }else{
                boolean flag=false;//为true表示有未审核通过的年审
                boolean flag2=true;//为true表示没有今年审批通过的年审
                for (Approve approve:list) {
                    Task task= taskService.createTaskQuery()
                            .processInstanceId(approve.getProcessinstanceid())
                            .singleResult();
                    if(task!=null){
                        result=PropertiesUtil.getApplyTypeProperties("nianshen");
                        flag=true;
                        break;
                    }
                }
                if(!flag){//没没完成的，是否有驳回的今年已经完成的
                    for (Approve approve:list) {
                        HistoricTaskInstance historicTaskInstance= historyService.createHistoricTaskInstanceQuery()
                                .processInstanceId(approve.getProcessinstanceid())
                                .taskVariableValueLike("shzt","通过")
                                .singleResult();
                        if(historicTaskInstance!=null){
                            //判断是否是今年的
                            Date d = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                            Date d2=approve.getApldate();
                            String csid = (applyNsService.selectById(approve.getAplid())).getApSqid();
                            boolean ztBaoZhang = this.isBaoZhangZhong(approve.getAptype(),csid);
                            if(sdf.format(d).equals(sdf.format(d2)) && ztBaoZhang){
                                result=PropertiesUtil.getApplyTypeProperties("nianshen");
                                flag2=false;
                                break;
                            }
                        }
                    }
                    if(flag2){
                        result="success";
                    }
                }
            }
        }*/
        String result = applyUserinfoService.toPerNsApplyCondition(request);
        return  result;
    }

    /**
     * 个人发起年审前的判断条件
     *
     * @return
     */
    public String toPerNsApplyCondition(HttpServletRequest request) {
        /*在进入年审页面前，先获取用户自己的初审信息*/
        String result="";
        /**
         * 1.审核全部通过并且不是今年的
         * 2.没有年审信息
         * 3.被驳回的
         */
        /*获取申请用户的信息*/
        ApplyUserinfo applyUserinfo = (ApplyUserinfo)request.getSession().getAttribute("applyUserinfo");
        String userid = applyUserinfo.getUserid();
        Map<String,Object> conditionMap = Maps.newHashMap();

        //审核全部通过并且不是今年的
        conditionMap.put("userid",userid);//用户id
        conditionMap.put("applyType","ns");//用户申请类型为年审类型
        List<Approve> list=approveService.findApproveByMap(conditionMap);
        ParmItem parmItem=parmItemService.selectSwitch();
        /*开关未设置或者未开启直接返回*/
        if(parmItem!=null) {
            if (!"是".equals(parmItem.getPiItemname())) {
                result = PropertiesUtil.getApplyTypeProperties("shenqingtd");
                return result;
            }
        }else {
            result = PropertiesUtil.getApplyTypeProperties("shenqingtd");
            return result;
        }

        if(list!=null){
            if(list.size()==0){//未申请过
                result="success";
            }else{
                boolean flag=false;//为true表示有未审核通过的年审
                boolean flag2=true;//为true表示没有今年审批通过的年审
                for (Approve approve:list) {
                    Task task= taskService.createTaskQuery()
                            .processInstanceId(approve.getProcessinstanceid())
                            .singleResult();
                    if(task!=null){
                        result=PropertiesUtil.getApplyTypeProperties("nianshen");
                        flag=true;
                        break;
                    }
                }
                if(!flag){//没没完成的，是否有驳回的今年已经完成的
                    for (Approve approve:list) {
                        HistoricTaskInstance historicTaskInstance= historyService.createHistoricTaskInstanceQuery()
                                .processInstanceId(approve.getProcessinstanceid())
                                .taskVariableValueLike("shzt","通过")
                                .singleResult();
                        if(historicTaskInstance!=null){
                            //判断是否是今年的
                            Date d = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                            Date d2=approve.getApldate();
                            if(sdf.format(d).equals(sdf.format(d2))){
                                result=PropertiesUtil.getApplyTypeProperties("nianshen");
                                flag2=false;
                                break;
                            }
                        }
                    }
                    if(flag2){
                        result="success";
                    }
                }
            }
        }
        return  result;
    }

    /**
     * 获取退房审批的内容
     * @param applyid
     * @return
     */
    @RequestMapping("/getTFSpForm")
    @ResponseBody
    public Object getTFSpForm(String applyid){
        Map<String,Object> map=new HashMap();
        map.put("applyid",applyid);
        List<Approve> approveList=approveService.findTFSpForm(map);
        if(approveList!=null && approveList.size()>0){
            if(approveList.size()>1){

                int count = 0;//记录遍历次数，相当与普通for循环的  i 记录所引值
                int index = 0;//初始索引
                for (Approve approve : approveList) {
                    count++;
                    if (approve.getF_conacre().compareTo(approveList.get(index).getF_conacre())==-1) {
                        index = count;
                    }

                }
                return approveList.get(index);
            }else{
                return approveList.get(0);
            }
        }else{
            return "未找到审批单";
        }

    }
    @RequestMapping("/toTFSpPage")
    public Object toTFSpPage(String applyid, HttpServletRequest request){
        Map<String,Object> map=new HashMap();
        map.put("applyid",applyid);
        List<Approve> approveList=approveService.findTFSpForm(map);
        if(approveList==null||approveList.size()==0){
            request.setAttribute("message","审批单不存在，或已退房!");
            return "applicationForm/message";
        }
        Approve approve=null;
        if(approveList.size()==1){
            approve=approveList.get(0);
        }else{
            approve = approveList.get(0);//获取初始元素值，用来做比较
            String roomNom=approve.getF_ronum();
//            for (Approve apove : approveList) {//person代表每一个元素值。
//                if (approve.getF_conacre().compareTo(apove.getF_conacre())==-1) {
//                    approve=apove;
//                }
//            }
            for (int i = 1; i <approveList.size() ; i++) {
                roomNom=roomNom+"、"+approveList.get(i).getF_ronum();
            }
            approve.setF_ronum(roomNom);
        }
        Map<String,Object> out = serialNumService.getSerialNum("TF");
        if(!(Boolean) out.get("flag")){
            request.setAttribute("message","流水号生成失败!");
            return "applicationForm/message";
            //return "流水号生成失败!";
        }
        //序号编码
        String htSnCode = (String)out.get("msg");
//        String  htCode = htSnPrefix + htSnCode;//合同协议编号
        String htQue = htSnCode.substring(htSnCode.length()-4);//合同序号（后4位）
        System.out.println("htSnCode1"+htSnCode);
        System.out.println("htSnCode2"+htSnCode.substring(0,4));
        System.out.println("htQue"+htQue);
        request.setAttribute("year",htSnCode.substring(0,4));
        request.setAttribute("htQue",htQue);
        request.setAttribute("approve",approve);
        request.setAttribute("applyid",applyid);
        return "houseInfoMa/returnHouseTemplate";
    }

    /**
     *经适房退房审批表内容
     * @param applyid
     * @param request
     * @return
     */
    @RequestMapping("/findJSFForm")
    public Object findJSFForm(String applyid, HttpServletRequest request){
        Map<String,Object> map=new HashMap();
        map.put("applyid",applyid);
        List<Approve> approveList=approveService.findJSFForm(map);

        if(approveList==null||approveList.size()==0){
            request.setAttribute("message","审批单不存在，或已退房!");
            return "applicationForm/message";
        }

        Approve approve=null;
        if(approveList.size()==1){
            approve=approveList.get(0);
        }else{
            approve = approveList.get(0);//获取初始元素值，用来做比较
            String roomNom=approve.getF_ronum();
//            for (Approve apove : approveList) {//person代表每一个元素值。
//                if (approve.getF_conacre().compareTo(apove.getF_conacre())==-1) {
//                    approve=apove;
//                }
//            }
            for (int i = 1; i <approveList.size() ; i++) {
                roomNom=roomNom+"、"+approveList.get(i).getF_ronum();
            }
            approve.setF_ronum(roomNom);

        }
        Map<String,Object> out = serialNumService.getSerialNum("TF");
        if(!(Boolean) out.get("flag")){
            request.setAttribute("message","流水号生成失败!");
            return "applicationForm/message";
        }
        //序号编码
        String htSnCode = (String)out.get("msg");
//        String  htCode = htSnPrefix + htSnCode;//合同协议编号
        String htQue = htSnCode.substring(htSnCode.length()-4);//合同序号（后4位）
//        System.out.println("htSnCode1"+htSnCode);
//        System.out.println("htSnCode2"+htSnCode.substring(0,4));
//        System.out.println("htQue"+htQue);
        request.setAttribute("year",htSnCode.substring(0,4));
        request.setAttribute("htQue",htQue);
        request.setAttribute("approve",approve);
        request.setAttribute("applyid",applyid);
        return "houseInfoMa/reportDialog/jsfReback";
    }

    /**
     * 调房人员列表
     * @param ssq
     * @param aptype
     * @param request
     * @return
     */
    @RequestMapping("/findChangePersons")
    @ResponseBody
    public Object findChangePersons(String ssq,String aptype,HttpServletRequest request){
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        String uname = request.getParameter("uname");

        int page = 1;
        int rows = 20;
        if(!StringUtils.isEmpty(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if(!StringUtils.isEmpty(rowsStr)){
            rows = Integer.parseInt(rowsStr);
        }
        int pageIndex = (page-1)*rows;
        int pageLaseIndex = page*rows;
        Map<String,Object> map=new HashMap();
        map.put("pageIndex",pageIndex);
        map.put("pageLaseIndex",pageLaseIndex);
        map.put("aptype",aptype);
        map.put("ssq",ssq);

        if(uname!=null && !uname.equals("")){
            map.put("uname",uname);
        }


        List<Map> appovePageInfo=appoveService.findChangePersons(map);
        Map<String,Object> map2=new HashMap<String, Object>();
        map2.put("rows",appovePageInfo);
        map2.put("total",appoveService.findChangePersonsCount(map));
        return map2;
    }

    @RequestMapping("/findApplyByUserSfzh")
    @ResponseBody
    public Object findApplyByUserSfzh(HttpServletRequest request){
        ApplyUserinfo applyUserinfo = (ApplyUserinfo)request.getSession().getAttribute("applyUserinfo");
        String pageStr = request.getParameter("page");
        String rowsStr = request.getParameter("rows");
        int page = 1;
        int rows = 20;
        if(!StringUtils.isEmpty(pageStr)){
            page = Integer.parseInt(pageStr);
        }
        if(!StringUtils.isEmpty(rowsStr)){
            rows = Integer.parseInt(rowsStr);
        }

        int pageIndex = (page-1)*rows;
        int pageLaseIndex = page*rows;
        Map<String,Object> map=new HashMap();
        map.put("pageIndex",pageIndex);
        map.put("pageLaseIndex",pageLaseIndex);
        map.put("sfzh",applyUserinfo.getSfzh());

        List<Map> appovePageInfo=appoveService.findApplyByUserSfzh(map);
        Map<String,Object> map2=new HashMap<String, Object>();
        map2.put("rows",appovePageInfo);
        map2.put("total",appoveService.findApplyByUserSfzhCount(map));
        return map2;
    }
}
