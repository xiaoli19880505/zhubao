package com.sys.controller.lotnum;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.common.CommonUtils;
import com.sys.common.PropertiesUtil;
import com.sys.common.StringUtils;
import com.sys.common.dataconver.BaseInfoDataConvertor;
import com.sys.common.filema.FileManagerUtil;
import com.sys.pojo.Message;
import com.sys.pojo.UserInfo;
import com.sys.pojo.apply.Apply;
import com.sys.pojo.lotnum.LotNum;
import com.sys.service.MessageService;
import com.sys.service.apply.ApplyService;
import com.sys.service.apply.ApproveService;
import com.sys.service.common.MessageAndFormService;
import com.sys.service.lotnum.LotNumService;
import org.apache.commons.collections4.map.LinkedMap;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Desc:摇号管理控制类
 * @Author:wangli
 * @CreateTime:10:58 2018/11/8
 */
@Controller
@RequestMapping("/lotnum")
public class LotNumMaController {

    @Autowired
    private ApproveService approveService;

    @Autowired
    private ApplyService applyService;

    @Autowired
    private LotNumService lotNumService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageAndFormService messageAndFormService;

    /**
     * 跳转到摇号列表页面
     * @return
     */
    @RequestMapping("toLotList")
    public String toLotList(){
        return "lotnum/lotlist";
    }

    /**
     * 跳转到摇号选中后的列表页面
     * @return
     */
    @RequestMapping("toLotMa")
    public String toLotMa(){
        return "lotnum/lotma";
    }

    /**
     * 跳转到摇号列表页面
     * @return
     */
    @RequestMapping("findlotlist")
    @ResponseBody
    public Object findLotList(HttpServletRequest request){
        String username = request.getParameter("applyUsername");//用户姓名
        String sfzh = request.getParameter("applyUsercard");//身份证号
        String ssq = request.getParameter("applyUserSsq");//所属区
        String ssj = request.getParameter("applyUserJd");//所属街道
        String apptype = request.getParameter("applyType");//申请类别
        String beginDate = request.getParameter("beginDate");//开始时间
        String endDate = request.getParameter("endDate");//结束时间
        String aplcLs= request.getParameter("aplcLs");//申请单状态（apZt<3查询）
        String aplcEq= request.getParameter("aplcEq");//申请单状态（apZt=3查询）
        String page = request.getParameter("page");//页码
        String rows = request.getParameter("rows");//页行数
        int pageIndex =1;
        int pageSize = 20;
        if(!StringUtils.isEmpty(rows)){
            pageSize=Integer.parseInt(rows);
        }
        if(!StringUtils.isEmpty(page)){
            pageIndex=Integer.parseInt(page);
        }

        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");//userInfoService.getUserByMap(conditionMap);
        String perUserSsq=userInfo.getSsq();//所属区
        if(perUserSsq.indexOf("0")==-1){//如果当前用户不为市区用户，则为当前登录用户的分区
            ssq=perUserSsq;
        }else{
            ssq="";
        }

        /*查询条件设置*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("username",username);
        conditionMap.put("sfzh",sfzh);
        conditionMap.put("ssq",ssq);
        conditionMap.put("ssj",ssj);
        conditionMap.put("applytype",apptype);
        conditionMap.put("beginDate",beginDate);
        conditionMap.put("endDate",endDate);
        if(aplcLs!=null){
            conditionMap.put("aplcLs",Integer.parseInt(aplcLs));
        }
        if(aplcEq!=null){
            conditionMap.put("aplcEq",Integer.parseInt(aplcEq));
        }

       /*分页查询*/
        PageHelper.startPage(pageIndex,pageSize);//设置分页
        List<Map<String,Object>> mapList = approveService.findPassApprove(conditionMap);
        PageInfo<Map<String,Object>> pageList =new PageInfo<Map<String, Object>>(mapList) ;
        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("total",pageList.getTotal());//查询总数
        resultMap.put("rows",pageList.getList());//查询条目
        return resultMap;
    }

    /**
     * 发送全部信息
     * @param request
     * @return
     */
    @RequestMapping("sendAllLotMessage")
    @ResponseBody
    public Object sendAllMessage(HttpServletRequest request){
        String username = request.getParameter("applyUsername");//用户姓名
        String sfzh = request.getParameter("applyUsercard");//身份证号
        String ssq = request.getParameter("applyUserSsq");//所属区
        String ssj = request.getParameter("applyUserJd");//所属街道
        String apptype = request.getParameter("applyType");//申请类别
        String beginDate = request.getParameter("beginDate");//开始时间
        String endDate = request.getParameter("endDate");//结束时间
        String aplcLs= request.getParameter("aplcLs");//申请单状态（apZt<3查询）
        String aplcEq= request.getParameter("aplcEq");//申请单状态（apZt=3查询）
        String sqrq = request.getParameter("sqrq");//约定日期
        String doc = request.getParameter("doc");//传来的资料名称
        String area = request.getParameter("area");//办理业务地点
        String content = request.getParameter("content");//具体办理事项内容，多选框选择，如□选房、 □交余款、 □签订合同、 □上房；
        String tplid="JSM41823-0026";
        /* String page = request.getParameter("page");//页码
        String rows = request.getParameter("rows");//页行数
        int pageIndex =1;
        int pageSize = 20;
        if(!StringUtils.isEmpty(rows)){
            pageSize=Integer.parseInt(rows);
        }
        if(!StringUtils.isEmpty(page)){
            pageIndex=Integer.parseInt(page);
        }*/

        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");//userInfoService.getUserByMap(conditionMap);
        String perUserSsq=userInfo.getSsq();//所属区
        if(perUserSsq.indexOf("0")==-1){//如果当前用户不为市区用户，则为当前登录用户的分区
            ssq=perUserSsq;
        }

        /*查询条件设置*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("username",username);
        conditionMap.put("sfzh",sfzh);
        conditionMap.put("ssq",ssq);
        conditionMap.put("ssj",ssj);
        conditionMap.put("applytype",apptype);
        conditionMap.put("beginDate",beginDate);
        conditionMap.put("endDate",endDate);
        if(aplcLs!=null){
            conditionMap.put("aplcLs",Integer.parseInt(aplcLs));
        }
        if(aplcEq!=null){
            conditionMap.put("aplcEq",Integer.parseInt(aplcEq));
        }

         /*查询条件下的全部信息*/
        List<Map<String,Object>> mapList = approveService.findPassApprove(conditionMap);
        LinkedMap<String,String> msgMap = new LinkedMap<String,String>();
        msgMap.put("@4@",sqrq);
        msgMap.put("@5@",doc);
        msgMap.put("@6@",area);
        msgMap.put("@7@",content);
        msgMap.put("@8@",userInfo.getUsername());
        msgMap.put("@9@",userInfo.getLinktel());
        msgMap.put("@10@", BaseInfoDataConvertor.convertUserUnit(userInfo));

        List<Message> msgAddList = Lists.newArrayList();
        List<Message> msgupdateList = Lists.newArrayList();
        Map<String,Object> messageMap = Maps.newHashMap();

        for(Map<String,Object> map:mapList){
            conditionMap.put("sqbh",map.get("ID"));
            conditionMap.put("msType","3");
            if(messageService.selectCountByMap(conditionMap)>0){
                Message message = new Message();
                message.setSqbh((String) map.get("ID"));
                message.setMstype("3");
                message.setMstime(new Date());
                msgupdateList.add(message);
            }else{
                Message message = new Message();
                message.setMsuserid(userInfo.getUserid());
                message.setAppType((String)map.get("APPTYPE"));
                message.setLinktel((String)map.get("LINKTEL"));
                message.setSqbh((String)map.get("ID"));
                message.setMsid(CommonUtils.getUUIDWith_());
                message.setMstime(new Date());
                message.setMstem(tplid);
                message.setMstype("3");
                message.setToUsername((String)map.get("USERNAME"));
                msgAddList.add(message);
                // message.setToUserSfzh(sfzhArr[i]);
            }
            msgMap.put("@1@",(String)map.get("USERNAME"));
            msgMap.put("@3@",(String)map.get("ID"));
            msgMap.put("@2@", PropertiesUtil.getApplyTypeProperties((String)map.get("APPTYPE")));
            messageAndFormService.sendMessage((String)map.get("LINKTEL"),tplid,msgMap);
        }

        /*发送短信*/
        messageService.updateAndAddMessageBatch(msgAddList,msgupdateList);
        return "发送成功";
    }

    /**
     * 跳转到摇号列表页面
     * @return
     */
    @RequestMapping("downlotlist")
    public Object downLotList(HttpServletRequest request, HttpServletResponse response){
        //String username = request.getParameter("applyUsername");//用户姓名
        //String sfzh = request.getParameter("applyUsercard");//身份证号
        String ssq = request.getParameter("applyUserSsq");//所属区
       // String ssj = request.getParameter("applyUserJd");//所属街道
        String apptype = request.getParameter("applyType");//申请类别
        String aplcLs = request.getParameter("aplcLs");//流程类别名称

        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");//userInfoService.getUserByMap(conditionMap);
        String perUserSsq=userInfo.getSsq();//所属区
        if(perUserSsq.indexOf("0")==-1){//如果当前用户不为市区用户，则为当前登录用户的分区
            ssq=perUserSsq;
        }

        /*查询条件设置*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        //conditionMap.put("username",username);
        //conditionMap.put("sfzh",sfzh);
        conditionMap.put("ssq",ssq);
        //conditionMap.put("ssj",ssj);
        conditionMap.put("applytype",apptype);
        if(!StringUtils.isEmpty(aplcLs)){
            conditionMap.put("aplcLs",Integer.parseInt(aplcLs));
        }

        /*查询结果*/
        List<Map<String,Object>> mapList = approveService.findPassApprove(conditionMap);
        if (mapList.isEmpty()){//如果没有数据，则返回null
            request.setAttribute("message","没有数据");
            return "applicationForm/message";
        }
        /*更改类型名称*/
        if(apptype.equals("1")){
            apptype="经济适用房";
        }else if(apptype.equals("3")){
            apptype="公共租赁住房实物配租";
        }
        String ssqname=(String)mapList.get(0).get("SSQ");//获取区名称
        File file = generatorXmlFile(mapList,ssqname,apptype);//生成xml文件
        String newFileName = ssqname+"_"+apptype+".BApply";//下载后的文件名
        try {
            /*文件下载*/
            FileManagerUtil.downloadToLocal(file.getAbsolutePath(),newFileName,response,request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.delete();//下载完成后，原文件删除
        return null;
    }




    /***
     * 上传文件 用@RequestParam注解来指定表单上的file为MultipartFile
     * @param file
     * @return
     */
    @RequestMapping("uploadXml")
    @ResponseBody
    public Object fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            String num = request.getParameter("num");//获取签订输入的筛选摇号数量
            int lotnum = 0;
            if(!StringUtils.isEmpty(num)){
                lotnum=Integer.parseInt(num);//筛选数量
            }

            if(lotnum<=0){
                return "筛选数量必须为大于0的数字";
            }
            /*文件上传路径和文件存储的名称*/
            String path = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator;
            String tempFilename = CommonUtils.getUUIDWith_()+".xml";

            File dir = new File(path);
            /*目录创建*/
            if(!dir.exists()){
                dir.mkdir();
            }

            /*创建临时文件*/
            File newFile = new File(path+tempFilename);
            if(!newFile.exists()){
                newFile.createNewFile();
            }
            /*文件转存*/
            file.transferTo(newFile);
            /*读取xml*/
            //List<Map<String,Object>> mapList = readXml(newFile,lotnum);
            List<LotNum> lotNumList = Lists.newArrayList();
            String result = readXmlToList(newFile,lotNumList,lotnum,request);
            newFile.delete();//存储文件删除
            if("success".equals(result)){
                lotNumService.updateApplyInfo(lotNumList,lotnum,request);//更新申请单状态
                return "摇号结果更新成功";
            }else if("falture".equals(result)){
                return "筛选的数量大于导入的数量";
            }else{
                return result;
            }
        } else {
            return CommonUtils.getMsgForRet(false,"未找到上传的文件!");
        }
    }


    /***
     * 所有用户不用摇号，直接更新申请单状态为可选房状态
     * @return
     */
    @RequestMapping("lotAllUser")
    @ResponseBody
    public Object LotAllUser( HttpServletRequest request) {
        // 判断文件是否为空
        String username = request.getParameter("applyUsername");//用户姓名
        String sfzh = request.getParameter("applyUsercard");//身份证号
        String ssq = request.getParameter("applyUserSsq");//所属区
        String ssj = request.getParameter("applyUserJd");//所属街道
        String apptype = request.getParameter("applyType");//申请类别
        String aplcLs = request.getParameter("aplcLs");//申请类别
        if(apptype==null){
            return "申请类型不能为空";
        }

        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");//userInfoService.getUserByMap(conditionMap);
        String perUserSsq=userInfo.getSsq();//所属区
        if(perUserSsq.indexOf("0")==-1){//如果当前用户不为市区用户，则为当前登录用户的分区
            ssq=perUserSsq;
        }

        /*查询条件设置*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("username",username);
        conditionMap.put("sfzh",sfzh);
        conditionMap.put("ssq",ssq);
        conditionMap.put("ssj",ssj);
        conditionMap.put("applytype",apptype);
        if(aplcLs!=null){
            conditionMap.put("aplcLs",Integer.parseInt(aplcLs));
        }

        /*查询全部，并且结果集转为lognum实体*/
        List<Map<String,Object>> mapList = approveService.findPassApprove(conditionMap);
        lotNumService.updateApplyInfo(mapList,apptype,userInfo.getUserid());//更新申请单状态
        return "摇号结果更新成功";
    }

    /**
     * 申请期限已过，但仍存在轮候与审批通过等待摇号的用户，需要操作将其申请作废
     * @param request
     * @return
     */
    @RequestMapping("rejectAllUser")
    @ResponseBody
    public Object rejectAllUser(HttpServletRequest request){
        // 判断文件是否为空
        String username = request.getParameter("applyUsername");//用户姓名
        String sfzh = request.getParameter("applyUsercard");//身份证号
        String ssq = request.getParameter("applyUserSsq");//所属区
        String ssj = request.getParameter("applyUserJd");//所属街道
        String apptype = request.getParameter("applyType");//申请类别
        String aplcLs = request.getParameter("aplcLs");//申请类别
        if(apptype==null){
            return "申请类型不能为空";
        }

        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");//userInfoService.getUserByMap(conditionMap);
        String perUserSsq=userInfo.getSsq();//所属区
        if(perUserSsq.indexOf("0")==-1){//如果当前用户不为市区用户，则为当前登录用户的分区
            ssq=perUserSsq;
        }

        /*查询条件设置*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("username",username);
        conditionMap.put("sfzh",sfzh);
        conditionMap.put("ssq",ssq);
        conditionMap.put("ssj",ssj);
        conditionMap.put("applytype",apptype);
        if(aplcLs!=null){
            conditionMap.put("aplcLs",Integer.parseInt(aplcLs));
        }

        /*查询全部，并且结果集转为lognum实体*/
        List<Map<String,Object>> mapList = approveService.findPassApprove(conditionMap);
        lotNumService.updateApplyInfo(mapList,apptype,request);//更新申请单状态
        return "取消成功";

    }

    /**
     * 放弃待选房资格
     * @param applyid 申请单id
     * @return
     */
    @RequestMapping(value = "quitLotNum",method = RequestMethod.POST)
    @ResponseBody
    public Object quitLotNum(@RequestParam(value = "applyid") String applyid,@RequestParam(value = "applyType") String applyType,HttpServletRequest request){
        int count = this.lotNumService.updateApplyInfo(applyid,applyType,request);
        String result="";
        if(count>0){
            result="操作成功";
        }else{
            result="操作失败";
        }
        return result;
    }


    /**
     * 读取xml，转换为list
     * @param file xml源文件
     * @param lotList 转换后存储的list
     * @param lotnum 筛选的个数
     * @return
     * @throws Exception
     */
    private String readXmlToList(File file,List<LotNum> lotList,int lotnum,HttpServletRequest request) throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        String applyType = request.getParameter("applyType");
        String aplcLs = request.getParameter("aplcLs");

        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("applytype",applyType);
        conditionMap.put("aplcLs",aplcLs);

        /*读取用户信息*/
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");//userInfoService.getUserByMap(conditionMap);

       // List<Map<String,Object>> mapList = Lists.newArrayList();
        int pos = 0;
        String result = "";
        /*读取xml*/
        Iterator it = root.elementIterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            conditionMap.put("sfzh",element.attributeValue("IDCardNo"));
            conditionMap.put("sqbh",element.attributeValue("ID"));
            if(this.approveService.findPassApproveCount(conditionMap)==0){
                result= new StringBuffer("姓名为").append(element.attributeValue("Name"))
                        .append("身份证号为").append(element.attributeValue("IDCardNo"))
                        .append("的用户不存在待摇号申请记录").toString();
                return result;
            }
            //Map<String,Object> map = Maps.newHashMap();
            //已知属性名称情况下
           // map.put("ID",element.attributeValue("ID"));
           // map.put("Name",element.attributeValue("Name"));
           // map.put("IDCardNo",element.attributeValue("IDCardNo"));
           // map.put("Num",element.attributeValue("Num"));
            LotNum lotNum = new LotNum();
            lotNum.setLid(CommonUtils.getUUIDWith_());//主键id
            lotNum.setName(element.attributeValue("Name"));//申请人姓名
            lotNum.setSfzh(element.attributeValue("IDCardNo"));//申请人身份证号
            lotNum.setNum(element.attributeValue("Num"));//排序号
            lotNum.setSqbh(element.attributeValue("ID"));//申请单编号
            lotNum.setUserid(userInfo.getUserid());//设置操作id
            lotNum.setQztime(new Date());//设置操作时间
            lotNum.setSqlb(applyType);
            pos++;
            /*在lotnum之前的数据状态设为待选房状态，之后的设为候选轮候状态*/
            if(pos<=lotnum){
                lotNum.setState("1");
            }else{
                lotNum.setState("0");
            }
            lotList.add(lotNum);
        }
        /*如果筛选的数目小于等于xml中条目数，则返回true，否则为false*/
        if(lotnum<=lotList.size()){
            result="success";
        }else{
            result="falture";
        }
        return  result;
    }

    /**
     * 生成xml文件
     * @param mapList 查询的记过集
     * @param ssqname 区名称
     * @param applyType 申请类型名称
     * @return
     */
    private File generatorXmlFile(List<Map<String,Object>> mapList,String ssqname,String applyType){
        try {
            // 1、创建document对象
            Document document = DocumentHelper.createDocument();
            // 2、创建根节点
            Element rss = document.addElement("BApply");
            // 3、向rss节点添加Area和属性
            rss.addAttribute("Area", ssqname);
            rss.addAttribute("Type",applyType);
            // 4、生成子节点及子节点内容
            for(Map<String,Object> tempMap:mapList){
                Element applyElement = rss.addElement("Apply");
                applyElement.addAttribute("ID", (String)tempMap.get("ID"));
                applyElement.addAttribute("Name", (String)tempMap.get("USERNAME"));
                applyElement.addAttribute("IDCardNo", (String)tempMap.get("SFZH"));
                applyElement.addAttribute("Num", "");
            }
            // 5、设置生成xml的格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            // 设置编码格式
            format.setEncoding("UTF-8");
            // 6、生成xml文件
            File file = new File("rss.xml");
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            // 设置是否转义，默认使用转义字符
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
            return file;
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("生成rss.xml失败");
        }
        return null;
    }
}