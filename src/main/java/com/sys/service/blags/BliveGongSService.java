package com.sys.service.blags;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sys.common.CommonUtils;
import com.sys.common.FtpUtils;
import com.sys.common.PropertiesUtil;
import com.sys.common.FileUtils;
import com.sys.common.enumClass.TemplateEnum;
import com.sys.mapper.AnnexFileMapper;
import com.sys.mapper.apply.*;
import com.sys.mapper.blagsh.BlgshMapper;
import com.sys.mapper.blagsh.BliveGongSMapper;
import com.sys.mapper.blagsh.BliveGongsDetailMapper;
import com.sys.pojo.*;
import com.sys.pojo.apply.*;
import com.sys.pojo.blagsh.Blgsh;
import com.sys.pojo.blagsh.BliveGongS;
import com.sys.pojo.blagsh.BliveGongsDetail;
import com.sys.pojo.extend.DataGridResult;
import com.sys.service.*;
import com.sys.service.apply.ApplyFamilyMemberService;
import com.sys.service.apply.ApplyUnitService;
import com.sys.service.home.ArticleInfoService;
import com.sys.service.home.ColumnInfoService;
import com.sys.service.serialnum.SerialNumService;
import net.sf.json.JSONObject;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Desc:公示条目service类
 * @Author:wangli
 * @CreateTime:10:56 2018/10/27
 */
@Service
public class BliveGongSService extends BaseService<BliveGongS>{

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private BliveGongSMapper bliveGongSMapper;

    @Autowired
    private ApplyFamilyMemberService applyFamilyMemberService;

    @Autowired
    private ApplyUnitService applyUnitService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ApplyUserinfoService applyUserinfoService;

    @Autowired
    private FlowService flowService;

    @Autowired
    private BlgshMapper blgshService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private AnnexFileMapper annexFileMapper;

    @Autowired
    private ColumnInfoService columnInfoService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ArticleInfoService articleInfoService;

    @Autowired
    private ApproveMapper approveMapper;

    @Autowired
    private ApplyNsMapper applyNsMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private ApplyButieMapper applyButieMapper;

    @Autowired
    private ApplyForgraDuateMapper applyForgraDuateMapper;

    @Autowired
    private ApplyForForeignMapper applyForForeignMapper;

    @Autowired
    private BliveGongsDetailMapper bliveGongsDetailMapper;

    @Autowired
    private ApplyUnitMapper applyUnitMapper;

    @Autowired
    private SerialNumService serialNumService;

    /**
     * 分页条件查询公示条目
     * @param map
     * @return
     */
    public PageInfo<BliveGongS> getListByMap(Map<String,Object> map){
        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
                Integer.parseInt((String) map.get("rows")));
        List<BliveGongS> list = this.bliveGongSMapper.selectListByMap(map);
        return new PageInfo<BliveGongS>(list);
    }

    /**
     * 诚信列表信息分页查询
     * @param bliveGongS
     * @param page
     * @param rows
     * @return
     */
    public PageInfo<BliveGongS> selectForCXLB(BliveGongS bliveGongS,String page,String rows){
        PageHelper.startPage(Integer.parseInt(page),
                Integer.parseInt(rows));
        List<BliveGongS> bliveGongSes = bliveGongSMapper.selectForCXLB(bliveGongS);
        return new PageInfo<BliveGongS>(bliveGongSes);
    }

    /**
     * 新增（废弃）
     * @param file 文件
     * @param filePath 保存文件路径
     * @param imageCode 文件编码
     * @param request
     * @return
     */
    public Object saveBliveGongsFQ(MultipartFile file, String bliveGongs, String filePath, String imageCode, HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userinfo = (UserInfo)session.getAttribute("user");//获取用户信息
        // 判断文件是否为空
        JSONObject jsonObj = new JSONObject();
        if (!file.isEmpty()) {
            boolean flag = FileUtils.checkFileSize(file,5,"M");
            if(!flag){
                logger.error("文章附件上传异常");
                jsonObj.put("flag",false);
                jsonObj.put("msg","文章附件超出" + 5 + "M");
                return jsonObj;
            }
            FileUtils.saveFile(file,filePath);
            if(!flag){
                logger.error("文章附件上传异常");
                jsonObj.put("flag",false);
                jsonObj.put("msg","文章附件上传异常");
                return jsonObj;
            }
            //保存文件至系统
            Date date = new Date();
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            AnnexFile annexFile = new AnnexFile();
            annexFile.setId(uuid);//ID
            annexFile.setFkId(bliveGongs);//外键ID
            annexFile.setFileName(file.getOriginalFilename());//文件名称
            annexFile.setFileUrl(filePath + File.separator + file.getOriginalFilename());//文件路径
            annexFile.setCreateDate(date);//创建时间
            annexFile.setCreateName(userinfo.getUsercode());//创建人
            int num = annexFileMapper.insert(annexFile);
            if(num<1){
                jsonObj.put("flag",false);
                jsonObj.put("msg","文章附件保存异常");
                return jsonObj;
            }
        }
        jsonObj.put("flag",true);
        return jsonObj;
    }

    /**
     * 新增附件
     * @param request
     * @return
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public Object saveBliveGongs(HttpServletRequest request) throws RuntimeException{
        HttpSession session = request.getSession();
        UserInfo userinfo = (UserInfo)session.getAttribute("user");//获取用户信息

        String code = String.valueOf(new Date().getTime());//时间戳
        String filePath = "ArchivePic/";
        /*String relativelyFilePath = "upload" + File.separator
                + "blive" + File.separator
                + code ;*///文件相对项目路径

        String name = request.getParameter("volname");//获得所有档案附件名列表
        String blgId = request.getParameter("blgId");//获得申请表id

        String sqbh = bliveGongSMapper.selectById(blgId).getBlgSqbh();
        /*创建上传文件的路径，context/申请编号前8位/申请编号*/
        filePath=filePath+sqbh.substring(0,8)+"/"+sqbh+"/chengxin/";

        /*如果目录不存在，则创建目录*/
       /* File dir = new File(filePath);
        if(!dir.exists()){
            dir.mkdirs();
        }*/

        long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());

        Date date = new Date();

        /*对附件文件名进行遍历*/
        //将request变成多部分request
        MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
        List<MultipartFile> fileList = multiRequest.getFiles("volname");//获取附件列表

        List<AnnexFile> annexFiles = new ArrayList<AnnexFile>();
        /*对附件进行遍历*/
        String uuidFile = null;
        for (MultipartFile tempFile:fileList){
            String path="";
            String fileNameNew = "";
            if(tempFile!=null) {
                boolean flag = FileUtils.checkFileSize(tempFile,5,"M");
                if(!flag){
                    logger.error("文章附件上传异常");
                    throw new RuntimeException("文章附件超出" + 5 + "M");
                }
                String dirFileName = new StringBuffer(CommonUtils.getUUIDWith_()).toString();;
                //FTP文件上传
                if(tempFile.getOriginalFilename().lastIndexOf(".")<0){
                    fileNameNew = dirFileName;
                }else{
                    fileNameNew = new String(tempFile.getOriginalFilename())
                            .replace(tempFile.getOriginalFilename().substring(0,tempFile.getOriginalFilename().lastIndexOf(".")),dirFileName);
                }

                try {
                    FtpUtils.uploadFile(
                            filePath, fileNameNew,tempFile.getInputStream());
                    path = FtpUtils.FTPUrl + filePath + fileNameNew;//文件再ftp上存储的路径
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("文章附件上传异常");
                    throw new RuntimeException("ftp文件上传失败!");
                }

            }



            /*附件对象的新增*/
            AnnexFile annexFile = new AnnexFile();
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            annexFile.setId(uuid);//ID
            annexFile.setFkId(blgId);//外键ID
            annexFile.setFileName(fileNameNew);//文件名称
            annexFile.setFileUrl(path);//文件路径
            annexFile.setCreateDate(date);//创建时间
            annexFile.setCreateName(userinfo.getUsercode());//创建人
            int num = annexFileMapper.insert(annexFile);

            if(num<1){
                throw new RuntimeException("文件信息保存失败!");
            }
            annexFiles.add(annexFile);
            uuidFile = uuid;
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("上传的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return uuidFile;
    }

    /**
     * 诚信列表信息分页查询
     * @param bliveGongS
     * @return
     */
    public BliveGongS selectForCXLBSH(BliveGongS bliveGongS){
        List<BliveGongS> bliveGongSes = bliveGongSMapper.selectForCXLB(bliveGongS);
        if(bliveGongSes!=null && bliveGongSes.size()>0){
            return bliveGongSes.get(0);
        }
        return null;
    }

    /**
     * 查询诚信详细信息
     * @param blgId
     * @return
     */
    public Map<String,Object> selectCXInfo(String blgId){
        Map<String,Object> map = new HashMap<String, Object>();
        //获取诚信行目信息
        BliveGongS bliveGongS = bliveGongSMapper.selectById(blgId);
        //获取申请单信息
        String apSqrid = getSqrid(bliveGongS.getBlgApid(),bliveGongS.getBlgType());
        //获取申请人信息（从家庭成员表信息获取）
        ApplyFamilyMember applyFamilyMember = applyFamilyMemberService.selectById(apSqrid);
        //外来务工、新就业有单位信息
        map.put("applyUnitFlag",false);
        ApplyUnit applyUnit = applyUnitService.findByApplyId(bliveGongS.getBlgApid());
        map.put("applyinfo",this.getSqApply(bliveGongS.getBlgApid(),bliveGongS.getBlgType()));

        logger.debug("appunit:"+applyUnit.toString());
        if(applyUnit!=null){
            //获取单位信息
            //ApplyUnit applyUnit = applyUnitService.findByApplyId(bliveGongS.getBlgApid());
            map.put("applyUnit",applyUnit);
            map.put("applyUnitFlag",true);
        }
        //获取详细信息
        BliveGongS bliveGongS2 = new BliveGongS();
        bliveGongS2.setBlgId(blgId);
        List<BliveGongS> bliveGongSes = (List<BliveGongS>)bliveGongSMapper.selectForCXLB(bliveGongS2);
        bliveGongS2 = bliveGongSes.get(0);
        if(bliveGongS2!=null){
            AnnexFile annexFile = new AnnexFile();
            annexFile.setFkId(bliveGongS2.getBlgId());
            List<AnnexFile> annexFiles = annexFileMapper.selectByCondition(annexFile);
            map.put("annexFiles",annexFiles);
        }
        map.put("applyUserinfo",applyFamilyMember);
        map.put("blgId",blgId);
        bliveGongS2.setBlgType(bliveGongS.getBlgType());//类型
        bliveGongS2.setBlgShProcessid(bliveGongS.getBlgShProcessid());//流程ID
        bliveGongS2.setBlgApid(bliveGongS.getBlgApid());//申请单号
        //获取详细诚信行信息
        BliveGongsDetail bliveGongsDetail = new BliveGongsDetail();
        bliveGongsDetail.setBlgdId(bliveGongS2.getBlgId());
        List<BliveGongsDetail> bliveGongsDetails = bliveGongsDetailMapper.selectByCondition(bliveGongsDetail);
        map.put("bliveGongS",bliveGongS2);
        map.put("bliveGongsDetails",bliveGongsDetails);
        map.put("chengxin","chengxin");
        return map;
    }

    /**
     * 撤回功能
     * @param blgId
     */
    public void updateWithdraw(String blgId){
        //查询诚信信息
        BliveGongS bliveGongS = bliveGongSMapper.selectById(blgId);//诚信行目信息
        //申请单信息blgApid
        String blgApid = bliveGongS.getBlgApid();
        //激活流程
        runtimeService.activateProcessInstanceById(bliveGongS.getBlgShProcessid());
        //删除诚信信息
        bliveGongSMapper.delete(blgId);
    }

    /**
     * 开启诚信公示流程
     * @param bliveGongSInp
     * @param userInfo
     */
    public void addCXReview(BliveGongS bliveGongSInp, UserInfo userInfo) throws RuntimeException {

        //查询诚信信息
        BliveGongS bliveGongS = bliveGongSMapper.selectById(bliveGongSInp.getBlgId());//诚信行目信息
        bliveGongS.setBlgSqtype(bliveGongSInp.getBlgSqtype());//失信行为类型(1.一般 2.严重)
        bliveGongS.setBlgIsgs("0");//未公示
        bliveGongS.setBlgDesc(bliveGongSInp.getBlgDesc());//失信行为描述
        bliveGongSMapper.update(bliveGongS);
        //保存行信息
        for (int i=0;i<bliveGongSInp.getBliveGongsDetails().size();i++) {
            BliveGongsDetail bliveGongsDetail = bliveGongSInp.getBliveGongsDetails().get(i);
            String uuid = CommonUtils.getUUIDWith_();
            bliveGongsDetail.setId(uuid);
            bliveGongsDetail.setBlgdId(bliveGongSInp.getBlgId());
            bliveGongsDetailMapper.insert(bliveGongsDetail);
        }

        //查询申请人信息
        ApplyUserinfo applyUserinfo = applyUserinfoService.selectById(bliveGongS.getBlgUserid());
        String ssq = null;
        String ssj = null;
        //获取申请单
        if(bliveGongS.getBlgType().contains("ns")){//表示年审
            ApplyNs applyNs = applyNsMapper.selectById(bliveGongS.getBlgApid());
            ssq = applyNs.getApSsq();
            ssj = applyNs.getApJdbsc();
        }else if(bliveGongS.getBlgType().contains("1") || bliveGongS.getBlgType().contains("3")){
            Apply apply = applyMapper.selectById(bliveGongS.getBlgApid());
            ssq = apply.getApSsq();
            ssj = apply.getApJdbsc();
        }else if(bliveGongS.getBlgType().contains("2")){
            ApplyButie applyButie = applyButieMapper.selectById(bliveGongS.getBlgApid());
            ssq = applyButie.getAbSsq();
            ssj = applyButie.getAbJdbsc();
        }else if(bliveGongS.getBlgType().contains("4")){
            ApplyForForeign applyForForeign = applyForForeignMapper.selectById(bliveGongS.getBlgApid());
            ssq = applyForForeign.getAffSsq();
            ssj = applyForForeign.getAffDwdz();
        }else if(bliveGongS.getBlgType().contains("5")){
            ApplyForgraDuate applyForgraDuate = applyForgraDuateMapper.selectById(bliveGongS.getBlgApid());
            ssq = applyForgraDuate.getAfgSsq();
            ssj = applyForgraDuate.getAfgDwdz();
        }else{
            throw new RuntimeException("申请类型有误!");
        }

        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("applyUsername",applyUserinfo.getUsername());
        conditionMap.put("applyUsercard",applyUserinfo.getSfzh());
        conditionMap.put("applyUserSsq",ssq);
        conditionMap.put("applyUserJd",ssj);
        conditionMap.put("blgState","-1");
        conditionMap.put("aplb","gs");
        conditionMap.put("applyType",bliveGongS.getBlgType());
        conditionMap.put("shzt",bliveGongS.getBlgOpinion());
        logger.info("applyUsername:" + applyUserinfo.getUsername()
            + "applyUsercard:" + applyUserinfo.getSfzh() + "applyUserSsq:" + ssq
            + "applyUserJd:" + ssj + "blgState:" + "-1" + "aplb:" + "gs"
            + "applyType:" + bliveGongS.getBlgType() + "shzt:" + "申请");
        String flowname = PropertiesUtil.getFlowProperties("gongshi");//获取流程定义名称的关键字
        String processinstanceid = this.flowService.addProcessInstanceNew(flowname,conditionMap);//添加流程实例，并且将流程实例设置于审批单实体
        //插入诚信单审批信息
        Blgsh blgsh = new Blgsh();
        blgsh.setBsId(CommonUtils.getUUIDWith_());//主键
        blgsh.setBlgSbrq(new Date());//上报日期
        blgsh.setBlgId(bliveGongSInp.getBlgId());//关联公示条目单id
        blgsh.setBlgProcessinstanceid(processinstanceid);//流程实例ID
        blgshService.insert(blgsh);

        //关闭原流程
        //关闭原流程
        Map<String,Object> variables = Maps.newHashMap();
        //获取流程信息
        Approve approve = approveMapper.findByApplyId(bliveGongS.getBlgApid());
        approve.setState(userInfo.getUsercode()+"-驳回");
        approveMapper.update(approve);

        /*审批不通过*/
        variables.put("msg", "不通过");
        /*添加审批意见*/
        //流程实例ID
        String processInstanceId = bliveGongS.getBlgShProcessid();
        //激活流程
        runtimeService.activateProcessInstanceById(processInstanceId);
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).
                singleResult();
        taskService.addComment(task.getId(), processInstanceId, "进行诚信审核!");
        /*审批提交*/
        taskService.complete(task.getId(),variables);
    }

    /**
     * 结束诚信审核节点
     * @param userInfo
     * @param blgId
     * @param blgOpinion
     * @param blgCXType
     * @return
     */
    public void addCXReviewSH(UserInfo userInfo, String blgId, String blgOpinion, String blgCXType){
        //查询诚信信息
        BliveGongS bliveGongS = bliveGongSMapper.selectById(blgId);//诚信行目信息
        Date date = new Date();
        //获取审核表信息
        Blgsh blgsh = new Blgsh();
        blgsh.setBlgId(bliveGongS.getBlgId());
        blgsh = blgshService.selectByCondition(blgsh);
        //获取诚信审核流程ID
        String processinstanceid = blgsh.getBlgProcessinstanceid();
        /*通过流程实例查到正在审批的节点task*/
        Task task = taskService.createTaskQuery().processInstanceId(processinstanceid).
                singleResult();
        //此节点不存在驳回，只允许通过，并记录审核类型
        List<TaskDefinition> taskDefinitionList = flowService.getTaskDefinitionList(processinstanceid);
        /*变量map，即审核的map*/
        Map<String,Object> variables = Maps.newHashMap();
        String blgState ;
        if("0".equals(blgCXType)){//失信
            blgState = "失信状态";
        } else {//不失信
            blgState = "非失信状态";
        }
        blgsh.setBlgState(blgCXType);
        /*下一个任务节点为空时，则该节点为最后一个节点；否则为中间节点；根据情况设置审批单状态*/
        if(taskDefinitionList==null || taskDefinitionList.size()==0){
            /*blgsh.setBlgState("审批全部通过");*/
            variables.put("shzt","通过");
            taskService.setVariableLocal(task.getId(),"shzt","通过" + blgState);
            blgsh.setBlgApvusers(userInfo.getUsercode());
        }else{
            /*blgsh.setBlgState(userInfo.getUsercode()+"-审批通过");*/
            blgsh.setBlgApvusers("," + userInfo.getUsercode());
        }
//        String messageContent= "您的申请通过审核,被认定为"+blgState;
        /*审批通过*/
        variables.put("msg", "通过");
        /*添加审批意见*/
        taskService.addComment(task.getId(), processinstanceid, blgOpinion);

        /*审批提交*/
        taskService.complete(task.getId(),variables);
        //保存信息
        blgsh.setBlgShyh(userInfo.getUserid());
        blgsh.setBlgPassdate(date);
        blgshService.update(blgsh);
        System.out.println("流程审批end");
    }

    /**
     * 诚信审核列表查询
     * @param bliveGongS
     * @param page
     * @param rows
     * @return
     */
    public DataGridResult selectIntegrityAudit(BliveGongS bliveGongS, String page, String rows){
        PageHelper.startPage(Integer.parseInt(page),
                Integer.parseInt(rows));
        List<BliveGongS> list=this.bliveGongSMapper.selectForCXLB(bliveGongS);
        PageInfo<BliveGongS> pageInfo=new PageInfo<BliveGongS>(list);
        DataGridResult dataGridResult=new DataGridResult(pageInfo.getTotal(),list);
        return dataGridResult;
    }

    /**
     * 诚信发布
     * @param user
     * @param blgIdList
     * @return
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public Object updateRelease(UserInfo user, List<String> blgIdList) {
        try{
            for (String blgId : blgIdList) {
                //查询诚信信息
                BliveGongS bliveGongS = bliveGongSMapper.selectById(blgId);//诚信行目信息
                if ("1".equals(bliveGongS.getBlgIsgs())) {
                    return "当前信息已发布,不可重复发布！";
                }
                if ("1".equals(bliveGongS.getBlgStatus())) {
                    return "当前信息为非失信,不可发布！";
                }
                //获取失信行信息
                BliveGongsDetail bliveGongsDetail = new BliveGongsDetail();
                bliveGongsDetail.setBlgdId(bliveGongS.getBlgId());
                List<BliveGongsDetail> bliveGongsDetails = bliveGongsDetailMapper.selectByCondition(bliveGongsDetail);
                //获取审核表信息
                Blgsh blgsh = new Blgsh();
                blgsh.setBlgId(bliveGongS.getBlgId());
                blgsh = blgshService.selectByCondition(blgsh);
                //查询诚信编码
                List<ColumnInfo> columnInfoes = (List<ColumnInfo>) columnInfoService.selectByConditions("诚信");
                if (columnInfoes == null || columnInfoes.size() == 0) {
                    throw new RuntimeException("没有诚信公告信息!");
                }
                ColumnInfo columnInfo = columnInfoes.get(0);
                //保存诚信公告文章信息
                Date date = new Date();

                //获取申请人信息
                ApplyUserinfo applyUserInfo = applyUserinfoService.selectById(bliveGongS.getBlgUserid());
                //获取上报人信息
                UserInfo userInfo = userInfoService.selectById(bliveGongS.getBlgRpuserid());
                //生成失信编号
                Map<String,Object> mapSx = serialNumService.getSerialNum("SX");
                if(!(Boolean)mapSx.get("flag")){
                    throw new RuntimeException((String)mapSx.get("msg"));
                }
                String lostLetterNo = (String)mapSx.get("msg");//失信编号

                for (BliveGongsDetail bliveGongsD : bliveGongsDetails) {
                    //文章标题
                    String articleName = null;

                    ArticleInfo articleInfo = new ArticleInfo();
                    articleInfo.setColumnId(columnInfo.getId());//外键
                    articleInfo.setDeleteFlag("F");//未删除
                    articleInfo.setAuthor("诚信公告");
                    articleInfo.setUpdatePerson(user.getUsercode());
                    articleInfo.setUpdateTime(date);
                    articleInfo.setCreateTime(date);
                    articleInfo.setCreatePerson(user.getUsercode());
                    String desc = new String(TemplateEnum.LOST_LETTER.getDesc());
                    String userName;//用户
                    String userCardId;//证件号
                    if ("2".equals(bliveGongsD.getLostLetterType())) {//企业
                        //获取企业信息
                        ApplyUnit applyUnit = applyUnitMapper.findByApplyId(bliveGongS.getBlgApid());
                        userName = getUnitName(bliveGongS.getBlgApid(), bliveGongS.getBlgType());//企业名称
                        userCardId = applyUnit.getBsls();//统一社会信用代码
                        articleName = "关于对" + userName + "失信行为公告";
                    } else {//个人
                        userName = applyUserInfo.getUsername();//申请人姓名
                        userCardId = applyUserInfo.getSfzh();//身份证号
                        //加星***处理yyMMdd
                        userCardId = userCardId.substring(0,8)
                                + "******" + userCardId.substring(18-4);
                        articleName = "关于对" + userName + "同志失信行为公告";
                    }
                    articleInfo.setArticleName(articleName);
                    logger.debug("desc---------"+desc);
                    String descStr = desc.replace("userName", userName)//替换用户
                            .replace("userCardId", userCardId)//替换证件号
                            .replace("blgdDesc", bliveGongsD.getBlgdDesc())//替换失信行为描述
                            .replace("blgdSqtype", "1".equals(bliveGongsD.getBlgdSqtype()) ? "一般" : "严重")
                            .replace("lostLetterNo",lostLetterNo);//替换失信行为级别
                    articleInfo.setArticleBody(descStr);//失信行为描述
                    articleInfo.setArticleCode(String.valueOf(date.getTime()));//编码时间戳
                    articleInfo.setIssuingTime(date);//发布时间
                    articleInfo.setClickNumber(0);//点击次数
                    String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
                    articleInfo.setId(uuid);
                    articleInfoService.insert(articleInfo);
                }

                bliveGongS.setBlgIsgs("1");//发布状态
                bliveGongS.setLostLetterNo(lostLetterNo);//失信编号
                bliveGongSMapper.update(bliveGongS);
            }
            return "发布成功";
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发布失败");
        }
    }

    /**
     * 获取单位名称(从家庭成员表查找本人)
     * @param objId
     * @param blgType
     * @return
     */
    private String getUnitName(String objId,String blgType){
        //获取申请单
        if(blgType.contains("ns")){//表示年审

            if(blgType.contains("2") || blgType.contains("3")){
                ApplyNs applyNs = applyNsMapper.selectById(objId);
                ApplyFamilyMember applyFamilyMember = applyFamilyMemberService.selectById(applyNs.getApSqrid());
                return applyFamilyMember.getAfmGzdw()==null?"":applyFamilyMember.getAfmGzdw();
            }else if(blgType.contains("4") || blgType.contains("5")){
                ApplyNs applyNs = applyNsMapper.selectById(objId);
                return applyNs.getAffDwmc()==null?"":applyNs.getAffDwmc();
            }else{
                return "";
            }
        }else if(blgType.contains("1") || blgType.contains("3")){
            Apply apply = applyMapper.selectById(objId);
            ApplyFamilyMember applyFamilyMember = applyFamilyMemberService.selectById(apply.getApSqrid());
            return applyFamilyMember.getAfmGzdw()==null?"":applyFamilyMember.getAfmGzdw();
        }else if(blgType.contains("2")){
            ApplyButie applyButie = applyButieMapper.selectById(objId);
            ApplyFamilyMember applyFamilyMember = applyFamilyMemberService.selectById(applyButie.getAbSqrid());
            return applyFamilyMember.getAfmGzdw()==null?"":applyFamilyMember.getAfmGzdw();
        }else if(blgType.contains("4")){
            ApplyForForeign applyForForeign = applyForForeignMapper.selectById(objId);
            //ApplyFamilyMember applyFamilyMember = applyFamilyMemberService.selectById(applyForForeign.getAffSqrid());
            return applyForForeign.getAffDwmc()==null?"":applyForForeign.getAffDwmc();
        }else if(blgType.contains("5")){
            ApplyForgraDuate applyForgraDuate = applyForgraDuateMapper.selectById(objId);
            //ApplyFamilyMember applyFamilyMember = applyFamilyMemberService.selectById(applyForgraDuate.getAfgSqrid());
            return applyForgraDuate.getAfgDwmc()==null?"":applyForgraDuate.getAfgDwmc();
        }else{
            throw new RuntimeException("申请类型有误!");
        }
    }

    /**
     * 获取申请人id
     * @param objId
     * @param blgType
     * @return
     */
    private String getSqrid(String objId,String blgType){
        //获取申请单
        if(blgType.contains("ns")){//表示年审
            ApplyNs applyNs = applyNsMapper.selectById(objId);
            return applyNs.getApSqrid();
        }else if(blgType.contains("1") || blgType.contains("3")){
            Apply apply = applyMapper.selectById(objId);
            return apply.getApSqrid();
        }else if(blgType.contains("2")){
            ApplyButie applyButie = applyButieMapper.selectById(objId);
            return applyButie.getAbSqrid();
        }else if(blgType.contains("4")){
            ApplyForForeign applyForForeign = applyForForeignMapper.selectById(objId);
            return applyForForeign.getAffSqrid();
        }else if(blgType.contains("5")){
            ApplyForgraDuate applyForgraDuate = applyForgraDuateMapper.selectById(objId);
            return applyForgraDuate.getAfgSqrid();
        }else{
            throw new RuntimeException("申请类型有误!");
        }
    }

    /**
     * 获取申请人id
     * @param objId
     * @param blgType
     * @return
     */
    private Object getSqApply(String objId,String blgType){
        //获取申请单
        if(blgType.contains("ns")){//表示年审
            ApplyNs applyNs = applyNsMapper.selectById(objId);
            return applyNs.getApSqrid();
        }else if(blgType.contains("1") || blgType.contains("3")){
            Apply apply = applyMapper.selectById(objId);
            return apply;
        }else if(blgType.contains("2")){
            ApplyButie applyButie = applyButieMapper.selectById(objId);
            return applyButie;
        }else if(blgType.contains("4")){
            ApplyForForeign applyForForeign = applyForForeignMapper.selectById(objId);
            return applyForForeign;
        }else if(blgType.contains("5")){
            ApplyForgraDuate applyForgraDuate = applyForgraDuateMapper.selectById(objId);
            return applyForgraDuate;
        }else{
            throw new RuntimeException("申请类型有误!");
        }
    }


}