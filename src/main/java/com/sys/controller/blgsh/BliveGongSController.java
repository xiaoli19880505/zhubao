package com.sys.controller.blgsh;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.common.CommonUtils;
import com.sys.pojo.ApplyUserinfo;
import com.sys.pojo.RoleInfo;
import com.sys.pojo.UserInfo;
import com.sys.pojo.blagsh.BliveGongS;
import com.sys.pojo.extend.DataGridResult;
import com.sys.service.RoleInfoService;
import com.sys.service.blags.BliveGongSService;
import net.sf.json.JSONArray;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Desc:失信条目控制类
 * @Author:wangli
 * @CreateTime:11:09 2018/10/27
 */
@Controller
@RequestMapping("blivegs")
public class BliveGongSController {

    @Autowired
    private BliveGongSService bliveGongSService;

    @Autowired
    private RoleInfoService roleInfoService;

    @Autowired
    private TaskService taskService;


    /**
     * 跳转到诚信列表页面
     * @return
     */
    @RequestMapping("toChengxinList")
    public String toChengxinList() {
        return "chengxinGs/gongsiList";
    }

    /**
     * 跳转到诚信上报页面
     * @return
     */
    @RequestMapping("toChengxinReport")
    public String toChengxinReport() {
        return "chengxinGs/creditAudit/gongsiReport";
    }

    /**
     * 跳转到诚信审核页面
     * @return
     */
    @RequestMapping("toChengxinAudio")
    public String toChengxinAudio() {
        return "chengxinGs/gongsiAudio";
    }

    /**
     * 跳转到诚信审核审核页面
     * @return
     */
    @RequestMapping("toChengxinAudioSH")
    public String toChengxinAudioSH() {
        return "chengxinGs/creditAudit/auditStart";
    }

    /**
     * 跳转到诚信发布页面
     * @return
     */
    @RequestMapping("toChengxinPublic")
    public String toChengxinPublic() {
        return "chengxinGs/gongsiPublic";
    }

    /**
     * 跳转到诚信失信内容
     * @return
     */
    @RequestMapping("toChengxinContent")
    public String toChengxinContent() {
        return "chengxinGs/creditAudit/creditContent";
    }
    /**
     * 跳转到诚信审核意见
     * @return
     */
    @RequestMapping("toChengxinOpinion")
    public String toChengxinOpinion() {
        return "chengxinGs/creditAudit/creditOpinion";
    }
    /**
     * 获取个人加入失信的条目列表
     * @return
     */
    @RequestMapping("getPerBliveGongSList")
    @ResponseBody
    public Object getPerBliveGongSList(HttpServletRequest request){
        /*获取用户信息*/
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        /*设置分页参数*/
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String blgUserName = request.getParameter("blgUserName");
        String blgSfzh = request.getParameter("blgSfzh");
        String blgSsq = request.getParameter("blgSsq");
        String blgSsj = request.getParameter("blgSsj");
        if(rows == null || "".equals(rows)){
            rows="20";
        }
        if(page == null || "".equals(page)){
            page="1";
        }
        BliveGongS bliveGongS = new BliveGongS();
        if(StringUtils.isNotEmpty(blgUserName)){
            bliveGongS.setBlgUserName(blgUserName);
        }
        if(StringUtils.isNotEmpty(blgSfzh)){
            bliveGongS.setBlgSfzh(blgSfzh);
        }
        if(StringUtils.isNotEmpty(blgSsq)){
            bliveGongS.setBlgSsq(blgSsq);
        }
        if(StringUtils.isNotEmpty(blgSsj)){
            bliveGongS.setBlgSsj(blgSsj);
        }
        if(userInfo!=null){
            if(StringUtils.isNotEmpty(userInfo.getUserid())){
                bliveGongS.setBlgRpuserid(userInfo.getUserid());
            }
        }
        /*分页查询*/
        PageInfo<BliveGongS> pageInfo =bliveGongSService.selectForCXLB(bliveGongS,page,rows);
        /*分页查询的list和总数存于map返回*/
        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("rows",pageInfo.getList());
        resultMap.put("total",pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 获取诚信审核的条目列表
     * @return
     */
    @RequestMapping("getIntegrityAudit")
    @ResponseBody
    public Object getIntegrityAudit(HttpServletRequest request){
        /*设置分页参数*/
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String appusername = request.getParameter("appusername");//申请人
        String appsfzh= request.getParameter("appsfzh");//身份证
        String appssq = request.getParameter("appssq");//所在区
        String appssjd = request.getParameter("appssjd");//所在街道
        String shstatus = request.getParameter("shstatus");//审核状态
        String all = request.getParameter("all");
        int pageIndex =1;
        int pageSize = 10;
        if(rows != null &&!"".equals(rows)){
            pageSize=Integer.parseInt(rows);
        }
        if(page != null &&!"".equals(page)){
            pageIndex=(Integer.parseInt(page) - 1) * pageSize;
        }
        if(all==null){
            all="all";
        }
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");//当前用户
        /*根据用户登录名查询用户实体*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        /*根据用户id查询其对应的角色列表*/
        String userid = userInfo.getUserid();//"5C57D84E-6C21-008A-E053-C0A86406008A"
        conditionMap.put("userid",userid);
        List<RoleInfo> roleInfoList = roleInfoService.getRoleListByMap(conditionMap);
        /*将角色列表的id合并成一个字符串*/
        String groups="";
        for (RoleInfo roleInfo:roleInfoList){
            groups=groups+roleInfo.getDutyid()+",";
        }
        Long count;//条件查询条目总数
        List<Task> allTaskList;//条件查询分页
        /*如果是全部审核查询*/
        if(all.equals("all")){
            /*查询条件下所有的任务列表数目*/
            TaskQuery taskQuery = taskService.createTaskQuery()
                    .processVariableValueLike("aplb","gs");
            if(StringUtils.isNotEmpty(appusername)){
                taskQuery = taskQuery.processVariableValueLike("applyUsername","%"+appusername+"%");
            }
            if(StringUtils.isNotEmpty(appsfzh)){
                taskQuery = taskQuery.processVariableValueLike("applyUsercard","%"+appsfzh+"%");
            }
            if(StringUtils.isNotEmpty(appssq)){
                taskQuery = taskQuery.processVariableValueLike("applyUserSsq","%"+appssq+"%");
            }
            if(StringUtils.isNotEmpty(appssjd)){
                taskQuery = taskQuery.processVariableValueLike("applyUserJd","%"+appssjd+"%");
            }
            if(StringUtils.isNotEmpty(shstatus)){
                taskQuery = taskQuery.processVariableValueLike("blgState","%"+shstatus+"%");
            }
            count = taskQuery.active().count();
            /*查询条件下所有的任务列表，分页查询*/
            allTaskList = taskQuery.active().listPage(pageIndex,pageSize);
        }else{
            /*查询条件下所有的个人任务列表数目*/
            TaskQuery taskQuery =taskService.createTaskQuery()
                    .taskCandidateUser(userid)
                    .processVariableValueLike("aplb","gs");
            if(StringUtils.isNotEmpty(appusername)){
                taskQuery = taskQuery.processVariableValueLike("applyUsername","%"+appusername+"%");
            }
            if(StringUtils.isNotEmpty(appsfzh)){
                taskQuery = taskQuery.processVariableValueLike("applyUsercard","%"+appsfzh+"%");
            }
            if(StringUtils.isNotEmpty(appssq)){
                taskQuery = taskQuery.processVariableValueLike("applyUserSsq","%"+appssq+"%");
            }
            if(StringUtils.isNotEmpty(appssjd)){
                taskQuery = taskQuery.processVariableValueLike("applyUserJd","%"+appssjd+"%");
            }
            if(StringUtils.isNotEmpty(shstatus)){
                taskQuery = taskQuery.processVariableValueLike("blgState","%"+shstatus+"%");
            }
            count = taskQuery.active().count();
            /*查询条件下所有的个人任务列表，分页查询*/
            allTaskList = taskQuery.active().listPage(pageIndex,pageSize);
        }

        List<BliveGongS> resultList = Lists.newArrayList();//返回的结果集

        boolean flag =false;//判断该当前任务的审批节点对应的审批角色是否就是该用户对应的角色
        /*遍历所有task，判断当前task对应组用户是否包含当前登录用户，如果存在，则表明
         * 该用户为当前task节点的审批节点*/
        for(Task task:allTaskList){
            /*查询用户身份列表*/
            List<IdentityLink> idenList = taskService.getIdentityLinksForTask(task.getId());
            flag=false;
            String currTaskGroupId ="";
            for(IdentityLink identityLink:idenList){
                /*当前用户对应的角色包含该task对应的角色id时，flag设为true*/
                currTaskGroupId=identityLink.getGroupId();
                if(groups.indexOf(identityLink.getGroupId())!=-1){
                    flag=true;
                    break;
                }
            }
            Map<String,Object> dataMap = Maps.newHashMap();
            BliveGongS bliveGongS = findMapResult(dataMap,task);//根据task信息返回map
            if(bliveGongS==null){
                continue;
            }
            dataMap.put("flag",flag);//flag也存于map中
            dataMap.put("nodename",this.roleInfoService.selectById(currTaskGroupId).getDutyname());
            resultList.add(bliveGongS);
        }

        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("rows",resultList);
        resultMap.put("total",count);
        return resultMap;
    }

    /**
     * 获取条目详细信息
     * @param request
     * @return
     */
    @RequestMapping("selectCXInfo")
    @ResponseBody
    public Object selectCXInfo(HttpServletRequest request){
        String blgId = request.getParameter("blgId");
        Map<String,Object> map = bliveGongSService.selectCXInfo(blgId);
        return map;
    }

    /**
     * 撤回动作，删除诚信信息，激活原流程
     * @param request
     * @return
     */
    @RequestMapping("updateWithdraw")
    @ResponseBody
    public Object updateWithdraw(HttpServletRequest request){
        String blgId = request.getParameter("blgId");
        try{
            bliveGongSService.updateWithdraw(blgId);
            return "撤回成功!";
        } catch (Exception e){
            e.printStackTrace();
            return "撤回失败!";
        }
    }

    /**
     * 开始审核诚信公示流程
     * @param request
     * @return
     */
    @RequestMapping("addCXReview")
    @ResponseBody
    public Object addCXReview(BliveGongS bliveGongS,HttpServletRequest request){
        /*获取用户信息*/
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        try {
            bliveGongSService.addCXReview(bliveGongS,userInfo);
            return "上报成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "上报失败";
        }
    }

    /**
     * 审核诚信公示流程
     * @param request
     * @return
     */
    @RequestMapping("addCXReviewSH")
    @ResponseBody
    public Object addCXReviewSH(HttpServletRequest request){
        /*获取用户信息*/
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        String blgId = request.getParameter("blgId");//诚信行ID
        String blgOpinion = request.getParameter("blgOpinion");//意见
        String blgCXType = request.getParameter("blgCXType");//诚信审核类型
        try {
            bliveGongSService.addCXReviewSH(userInfo,blgId,blgOpinion,blgCXType);
            return "审核成功!";
        } catch (Exception e) {
            e.printStackTrace();
            return "审核失败!";
        }
    }

    /**
     * 审核诚信公布列表
     * @param request
     * @return
     */
    @RequestMapping("Shgongbulb")
    @ResponseBody
    public Object Shgongbulb(HttpServletRequest request){
        /*设置分页参数*/
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String appusername = request.getParameter("appusername");//申请人
        String appsfzh= request.getParameter("appsfzh");//身份证
        String appssq = request.getParameter("appssq");//所在区
        String appssjd = request.getParameter("appssjd");//所在街道
        if(rows == null || "".equals(rows)){
            rows="20";
        }
        if(page == null || "".equals(page)){
            page="1";
        }
        BliveGongS bliveGongS = new BliveGongS();
        if(StringUtils.isNotEmpty(appusername)){
            bliveGongS.setBlgUserName(appusername);
        }
        if(StringUtils.isNotEmpty(appsfzh)){
            bliveGongS.setBlgSfzh(appsfzh);
        }
        if(StringUtils.isNotEmpty(appssq)){
            bliveGongS.setBlgSsq(appssq);
        }
        if(StringUtils.isNotEmpty(appssjd)){
            bliveGongS.setBlgSsj(appssjd);
        }
        bliveGongS.setBlgStatus("T");
        /*分页查询*/
      DataGridResult dataGridResult =bliveGongSService.selectIntegrityAudit(bliveGongS,page,rows);
        /*分页查询的list和总数存于map返回*/
        /*Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("rows",pageInfo.getList());
        resultMap.put("total",pageInfo.getTotal());*/
        return dataGridResult;
    }







    /**
     * 诚信发布
     * @param request
     * @return
     */
    @RequestMapping("updateRelease")
    @ResponseBody
    public Object updateRelease(HttpServletRequest request){
        /*获取用户信息*/
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        String blgIds = request.getParameter("blgIds");//诚信行ID
        JSONArray jsonArr = JSONArray.fromObject(blgIds);
        List<String> blgIdList = JSONArray.toList(jsonArr, String.class);// 过时方法
        try {
            String returnStr = (String)bliveGongSService.updateRelease(userInfo,blgIdList);
            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "发布失败!";
        }

    }

    /**
     * 根据流程ID获取详细信息
     * @param resultMap
     * @param task
     * @return
     */
    private BliveGongS findMapResult(Map resultMap,Task task){
        String processInstanceId = task.getProcessInstanceId();//流程对象
        BliveGongS bliveGongS = new BliveGongS();
        bliveGongS.setBlgProcessinstanceid(processInstanceId);
        bliveGongS = bliveGongSService.selectForCXLBSH(bliveGongS);
        return bliveGongS;
    }


    /**
     * 不用废弃【无法批量上传】
     * @param file
     * @param request
     * @return

    public Object fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        // 判断文件是否为空
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String code = String.valueOf(new Date().getTime());
            String filePath = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator
                    + "blive" + File.separator
                    + code ;
            String blgId = request.getParameter("blgId");
            return bliveGongSService.saveBliveGongs(file,blgId,filePath,code,request);
        } else {
            return CommonUtils.getMsgForRet(false,"未找到上传的文件!");
        }
    } */

    /***
     * 上传文件【批量】 用@RequestParam注解来指定表单上的file为MultipartFile
     * @param request
     * @return
     */
    @RequestMapping("fileUpload")
    @ResponseBody
    public Object fileUpload(HttpServletRequest request){
        return bliveGongSService.saveBliveGongs(request);
    }



}