package com.sys.service;

import com.sys.common.*;
import com.sys.mapper.FactMappingMapper;
import com.sys.mapper.ItemCodeInfoMapper;
import com.sys.mapper.ParmItemMapper;
import com.sys.mapper.SourceHouseMapper;
import com.sys.mapper.apply.*;
import com.sys.mapper.contract.ContractDetailMapper;
import com.sys.mapper.contract.ContractTemplateMapper;
import com.sys.pojo.*;
import com.sys.pojo.apply.*;
import com.sys.pojo.contract.ContractDetail;
import com.sys.pojo.contract.ContractTemplate;
import com.sys.pojo.serialnum.SerialNumLine;
import com.sys.service.schedule.ScheduleJobDomain;
import com.sys.service.schedule.ScheduleJobService;
import com.sys.service.serialnum.SerialNumService;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ContractTemplateService extends BaseService<ContractTemplate> {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ContractTemplateMapper contractTemplateMapper;

    @Autowired
    private SerialNumService serialNumService;

    @Autowired
    private ContractDetailService contractDetailService;

    @Autowired
    private DynamicallyGeneratedWordUtil dynamicallyGeneratedWordService;

    @Autowired
    private ContractDetailMapper contractDetailMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private ApplyFamilyMemberMapper applyFamilyMemberMapper;

    @Autowired
    private SourceHouseMapper sourceHouseMapper;

    @Autowired
    private ItemCodeInfoMapper itemCodeInfoMapper;

    @Autowired
    private FactMappingMapper factMappingMapper;

    @Autowired
    private ApplyForForeignMapper applyForForeignMapper;

    @Autowired
    private ApplyForgraDuateMapper applyForgraDuateMapper;

    @Autowired
    private ParmItemMapper parmItemMapper;

    @Autowired
    private ApplyButieMapper applyButieMapper;

    @Autowired
    private ApplyUnitMapper applyUnitMapper;

    @Autowired
    private ApplyFamilyHouseMapper applyFamilyHouseMapper;

    @Autowired
    private ScheduleJobService scheduleJobService;

    public List<ContractTemplate> getContractTemplates(ContractTemplate contractTemplate){
        return contractTemplateMapper.selectByCondition(contractTemplate);
    }

    public Object insertContractTemplates(ContractTemplate contractTemplate){
        int  num = contractTemplateMapper.insert(contractTemplate);
        if(num==1){
            return "新增成功!";
        } else {
            return "新增失败!";
        }
    }

    public void GetPdf(String wordPath, String pdfPath, String pdfName, HttpServletResponse response){
        //转换成PDF
        PDFUtil.word2PDF(wordPath,pdfPath);
        //从数据库中查出文件位置和文件名字
        try {
            File file = new File(pdfPath);
            if (!file.exists()) {
                response.getWriter().write("该文档生成pdf失败,请下载文档查看");
                return;
            }
            InputStream fis = new FileInputStream(pdfPath.replace("doc","pdf"));
            byte[] buffer = new byte[1024];
            response.reset();
            response.addHeader("Content-Disposition", "inline;filename=" + java.net.URLEncoder.encode(pdfName, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            response.setContentType("application/pdf");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            int nbytes = 0;
            while ((nbytes = fis.read(buffer)) != -1) {
                toClient.write(buffer, 0, nbytes);
                toClient.flush();
            }
            toClient.flush();
            toClient.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return ;
    }

    /**
     * 合同下载并备案
     * @param contractTemplateFillingDataPojo
     * @param request
     * @param response
     * @return
     */
    public Object updateContractTemplateFile(ContractTemplateFillingDataPojo contractTemplateFillingDataPojo,HttpServletRequest request,
                                             HttpServletResponse response) throws RuntimeException{

        Map<String,Object> mapOut = new HashMap<String, Object>();
        //获取合同编码
        String contractNo = contractTemplateFillingDataPojo.getContractNo();//合同编号
        String htQue = contractTemplateFillingDataPojo.getHtQue();//合同序号


        if(contractTemplateFillingDataPojo==null){
            throw new RuntimeException("合同类型不存在!");
        }

        //判断是否已经生成合同
        if(StringUtils.isEmpty(contractTemplateFillingDataPojo.getcId())){//表明初审合同
            ContractDetail contractDetail = new ContractDetail();
            contractDetail.setcSqid(contractTemplateFillingDataPojo.getObjId());
            List<ContractDetail> contractDetails = contractDetailMapper.selectByCondition(contractDetail);
            if(contractDetails!=null && contractDetails.size()>0){
                throw new RuntimeException("合同已生成不允许重复填写合同信息!");
            }
        }else{
            ContractDetail contractDetail = contractDetailMapper.selectById(contractTemplateFillingDataPojo.getcId());
            if(contractDetail.getcLc()!=1){
                throw new RuntimeException("合同已签订不允许重复填写合同信息!");
            }
        }

        Date date = new Date();
        HttpSession session = request.getSession();
        UserInfo userinfo = (UserInfo)session.getAttribute("user");//获取用户信息

        //获取合同模板
        ContractTemplate contractTemplate = new ContractTemplate();
        contractTemplate.setCtCode(contractTemplateFillingDataPojo.getCtCode());
        List<ContractTemplate> contractTemplates = getContractTemplates(contractTemplate);
        if(contractTemplates==null || contractTemplates.size()==0){
            throw new RuntimeException("合同模板信息不存在!");
        }
        contractTemplate = contractTemplates.get(0);
        //文本路径信息
        String ctUrl = contractTemplate.getCtUrl();
        //获取文件名、路径
        int index = ctUrl.lastIndexOf(File.separator);
        if (index < 0) {
            throw new RuntimeException("文件路径解析失败!");
        }
        //目标文件信息
        String templateFilePath = request.getSession().getServletContext().getRealPath("/") + contractTemplate.getCtUrl();//模板文件全路径
        String templetName = contractTemplate.getCtName();//模板文件名
        String tempLetDir = templateFilePath.replace("/" + templetName,"");//模板路径

        //项目路径
        String filePath = request.getSession().getServletContext().getRealPath("/");
        //根据模板文件生成文件路径 + 临时路径 + 合同编号
        String targetWordDir = filePath + "contract" + File.separator +
                "tempContract" + File.separator + "word"
                + File.separator + contractNo;

        String targetName = templetName.replace("xml", "doc");//生成文件名称
        targetName = targetName.replace("（定稿）",contractNo);
        String dirAndName = targetWordDir + File.separator + targetName;//目标全路径

        //根据输入参数信息，填入输入Map
        Map<String, Object> inputMap = new HashMap<String, Object>();
        if ("001".equals(contractTemplateFillingDataPojo.getCtCode())) {//徐州市市区公共租赁住房租赁补贴协议（定稿）-- 合同
            //转换 = 》 补贴总价中文
//            String rentalSubsidiesTPZH = CnNumberUtils.toUppercase(
//                    Double.valueOf(contractTemplateFillingDataPojo.getRentalSubsidiesTP()));
            contractTemplateFillingDataPojo.setcId(request.getParameter("cId"));////合同ID获取存储
            inputMap.put("regionCode", contractTemplateFillingDataPojo.getRegionCodeStr());//区域
            inputMap.put("year",contractTemplateFillingDataPojo.getYear());//年份
            inputMap.put("code",contractTemplateFillingDataPojo.getHtQue());//合同序号
            inputMap.put("userName",toParseSpace(contractTemplateFillingDataPojo.getUserName(),5));//乙方
            inputMap.put("userCardId",toParseSpace(contractTemplateFillingDataPojo.getUserCardId(),20));//身份证号码
            inputMap.put("familyNum",contractTemplateFillingDataPojo.getFamilyNum());//家庭人数
            inputMap.put("rentalSubsidiesNum",contractTemplateFillingDataPojo.getRentalSubsidiesNum());//租赁补贴人数
            inputMap.put("rentalSubsidiesArea",contractTemplateFillingDataPojo.getRentalSubsidiesArea());//人均补贴面积
            inputMap.put("rentalSubsidiesUP",contractTemplateFillingDataPojo.getRentalSubsidiesUP());//补贴单价
            inputMap.put("rentalSubsidiesTP",contractTemplateFillingDataPojo.getRentalSubsidiesTP());//补贴总价
//            inputMap.put("rentalSubsidiesTPZH",rentalSubsidiesTPZH);//补贴总价(中文)
            inputMap.put("rentalSubsidiesTPZH",contractTemplateFillingDataPojo.getRentalSubsidiesTPZH());//补贴总价(中文)
            inputMap.put("yearSta",contractTemplateFillingDataPojo.getYearSta());//起始年
            inputMap.put("monthSta",contractTemplateFillingDataPojo.getMonthSta());//起始月
            inputMap.put("daySta",contractTemplateFillingDataPojo.getDaySta());//起始日
            inputMap.put("yearEnd",contractTemplateFillingDataPojo.getYearEnd());//结束年
            inputMap.put("monthEnd",contractTemplateFillingDataPojo.getMonthEnd());//结束月
            inputMap.put("dayEnd",contractTemplateFillingDataPojo.getDayEnd());//结束日
            inputMap.put("deadline",contractTemplateFillingDataPojo.getDeadline());//截止时间线
            inputMap.put("accountTitle","交通银行");//账户名称
            inputMap.put("bankAccount",toParseSpace(contractTemplateFillingDataPojo.getBankAccount(),10));//银行账户
            inputMap.put("perCapitaArea",contractTemplateFillingDataPojo.getPerCapitaArea());// 人均住房面积
        } else if ("002".equals(contractTemplateFillingDataPojo.getCtCode())) {//徐州市市区公共租赁住房租赁合同（低保、特困，低收入，中等偏下）（定稿） -- 合同
            inputMap.put("userName",contractTemplateFillingDataPojo.getUserName());//乙方
            inputMap.put("userCardId",contractTemplateFillingDataPojo.getUserCardId());//身份证号码
            inputMap.put("fixedTelephone",contractTemplateFillingDataPojo.getFixedTelephone());//固定电话
            inputMap.put("mobilePhone",contractTemplateFillingDataPojo.getMobilePhone());//手机
            inputMap.put("employer",contractTemplateFillingDataPojo.getEmployer());//工作单位
            inputMap.put("contactAddress",contractTemplateFillingDataPojo.getContactAddress());//联系住址
            inputMap.put("postalCode",contractTemplateFillingDataPojo.getPostalCode());//邮政编码
            inputMap.put("principadName",contractTemplateFillingDataPojo.getPrincipadName());//委托代理人姓名
            inputMap.put("principadCardId",contractTemplateFillingDataPojo.getPrincipadCardId());//委托代理人身份证
            inputMap.put("principadAddress",contractTemplateFillingDataPojo.getPrincipadAddress());//委托代理人住址
            inputMap.put("principadPostalCode",contractTemplateFillingDataPojo.getPrincipadPostalCode());//委托代理人邮政编码
            inputMap.put("principadTelephone",contractTemplateFillingDataPojo.getPrincipadTelephone());//委托代理人固定电话
            inputMap.put("principadMobilePhone",contractTemplateFillingDataPojo.getPrincipadMobilePhone());//委托代理人手机
            inputMap.put("principadEmployer",contractTemplateFillingDataPojo.getPrincipadEmployer());//委托代理人工作单位
            inputMap.put("street",contractTemplateFillingDataPojo.getStreet());//街
            inputMap.put("community",contractTemplateFillingDataPojo.getCommunity());//小区
            inputMap.put("building",contractTemplateFillingDataPojo.getBuilding());//栋
            inputMap.put("unit",contractTemplateFillingDataPojo.getUnit());//单元
            inputMap.put("room",contractTemplateFillingDataPojo.getRoom());//室
            inputMap.put("constructionArea",contractTemplateFillingDataPojo.getConstructionArea());//建筑面积
            inputMap.put("yearSta",contractTemplateFillingDataPojo.getYearSta());//起始年
            inputMap.put("monthSta",contractTemplateFillingDataPojo.getMonthSta());//起始月
            inputMap.put("daySta",contractTemplateFillingDataPojo.getDaySta());//起始日
            inputMap.put("yearEnd",contractTemplateFillingDataPojo.getYearEnd());//结束年
            inputMap.put("monthEnd",contractTemplateFillingDataPojo.getMonthEnd());//结束月
            inputMap.put("dayEnd",contractTemplateFillingDataPojo.getDayEnd());//结束日
            inputMap.put("rentUnitPrice",contractTemplateFillingDataPojo.getRentUnitPrice());//租金单价
            String rent = contractTemplateFillingDataPojo.getRent();
            inputMap.put("rent",rent);//租金
//            String rentName = CnNumberUtils.toUppercase(Double.valueOf(rent));
//            inputMap.put("rentName",rentName);//大写租金
            inputMap.put("rentName",contractTemplateFillingDataPojo.getRentName());//补贴总价(中文)
            inputMap.put("disputeResolution",request.getParameter("disputeResolution"));//争议解决方式
        } else if ("003".equals(contractTemplateFillingDataPojo.getCtCode())) {//徐州市市区公共租赁住房租赁合同（新就业外来务工人员）（定稿）
            inputMap.put("contractNo",contractTemplateFillingDataPojo.getContractNo());//合同编号
            inputMap.put("userName",contractTemplateFillingDataPojo.getUserName());//乙方
            inputMap.put("legalRepresentative",contractTemplateFillingDataPojo.getLegalRepresentative());//法定代表人
            inputMap.put("businessLicense",contractTemplateFillingDataPojo.getBusinessLicense());//营业执照号
            inputMap.put("principadName",contractTemplateFillingDataPojo.getPrincipadName());//委托代理人
            inputMap.put("principadMobilePhone",contractTemplateFillingDataPojo.getPrincipadMobilePhone());//委托代理人手机号码
            inputMap.put("principadContactAddress",contractTemplateFillingDataPojo.getPrincipadContactAddress());//委托代理人联系地址
            inputMap.put("lessee",contractTemplateFillingDataPojo.getLessee());//承租人
            inputMap.put("lesseeCardId",contractTemplateFillingDataPojo.getLesseeCardId());//承租人身份证号码
            inputMap.put("lesseeContactAddress",contractTemplateFillingDataPojo.getLesseeContactAddress());//承租人联系地址
            inputMap.put("lesseeContactPhone",contractTemplateFillingDataPojo.getLesseeContactPhone());//承租人联系电话
            inputMap.put("yearSta",contractTemplateFillingDataPojo.getYearSta());//起始年
            inputMap.put("monthSta",contractTemplateFillingDataPojo.getMonthSta());//起始月
            inputMap.put("daySta",contractTemplateFillingDataPojo.getDaySta());//起始日
            inputMap.put("yearEnd",contractTemplateFillingDataPojo.getYearEnd());//结束年
            inputMap.put("monthEnd",contractTemplateFillingDataPojo.getMonthEnd());//结束月
            inputMap.put("dayEnd",contractTemplateFillingDataPojo.getDayEnd());//结束日
            inputMap.put("yearTime",contractTemplateFillingDataPojo.getYearTime());//截止时间线
            inputMap.put("community",contractTemplateFillingDataPojo.getCommunity());//区
            inputMap.put("building",contractTemplateFillingDataPojo.getBuilding());//楼
            inputMap.put("unit",contractTemplateFillingDataPojo.getUnit());//单元
            inputMap.put("room",contractTemplateFillingDataPojo.getRoom());//室
            inputMap.put("buildingStructure",contractTemplateFillingDataPojo.getBuildingStructure());//建筑结构
            inputMap.put("constructionArea",contractTemplateFillingDataPojo.getConstructionArea());//建筑面积
            inputMap.put("roomNum",contractTemplateFillingDataPojo.getRoomNum());//居室数量
            inputMap.put("monthlyRent",contractTemplateFillingDataPojo.getMonthlyRent());//月租金
//            String rentalSubsidiesTPZH = CnNumberUtils.toUppercase(Double.valueOf(contractTemplateFillingDataPojo.getMonthlyRent()));
//            inputMap.put("monthlyRentZH",rentalSubsidiesTPZH);//月租金(中文)
            inputMap.put("monthlyRentZH",contractTemplateFillingDataPojo.getRentalSubsidiesTPZH());//月租金(中文)
            inputMap.put("firstPartyOpinion",contractTemplateFillingDataPojo.getFirstPartyOpinion());//甲方意见
            inputMap.put("disputeResolution",contractTemplateFillingDataPojo.getDisputeResolution());//争议解决方式
        } else {
            throw new RuntimeException("获取合同编码异常!");
        }

        try {
            dynamicallyGeneratedWordService.genWordFile(tempLetDir, templetName, targetWordDir, targetName, inputMap);

            //从数据库中查出文件位置和文件名字
            String pdfPath = filePath + "contract" + File.separator +
                    "tempContract" + File.separator + "pdf"
                    + File.separator + contractNo + File.separator + targetName.replace("doc","pdf");
            String basePdfPath = "contract" + File.separator +
                    "tempContract" + File.separator + "pdf"
                    + File.separator + contractNo + File.separator + targetName.replace("doc","pdf");
            try {
                //转换成PDF
                PDFUtil.word2PDF(dirAndName,pdfPath);
                File file = new File(pdfPath);
                if (!file.exists()) {
                    throw new RuntimeException("合同生成PDF文件失败!");
                }
                dirAndName = pdfPath;
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.toString());
                throw new RuntimeException("合同生成PDF文件失败!");
            }
            //下载，直接进行超链接
//            FileUtils.buildResponseEntity(response, new File(pdfPath), "application/pdf");

            //备案
            contractDetailService.updateFiling(contractTemplateFillingDataPojo,userinfo,contractNo,dirAndName,JSONObject.fromObject(inputMap).toString());

            //年审通知短信提醒 除经适房外
            //合同终止日期
            String contractEndDay=contractTemplateFillingDataPojo.getYearEnd()+"-"
                                    +contractTemplateFillingDataPojo.getMonthEnd()+"-"
                                    +contractTemplateFillingDataPojo.getDayEnd();
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            //格式化终止日期
            Date formatdate = format1.parse(contractEndDay);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("piSetcode","22");
            map.put("piItemcode",contractTemplateFillingDataPojo.getApSqlb());
            //获取不同类型短信定时提前的天数
            ParmItem parmItem=parmItemMapper.selectByMap(map);
            int day=Integer.parseInt(parmItem.getPiItemname());
            Calendar theCa = Calendar.getInstance();
            theCa.setTime(formatdate);
            theCa.add(theCa.DATE, (-1)*day);
            Date start = theCa.getTime();
            //格式化提前到的那一天
            String  startDateString= format1.format(start);
            //获取短信发送定时间
            map.put("piSetcode","23");
            map.put("piItemcode","1");
            ParmItem parmItem2=parmItemMapper.selectByMap(map);
            //短信定时发送时间 年月日时分秒
            startDateString=startDateString+" "+parmItem2.getPiItemname();
            DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date sendTime = format2.parse(startDateString);
            String time=SendPhoneCodeUtil.getCron(sendTime);
            String job_name=contractTemplateFillingDataPojo.getObjId();
            ScheduleJobDomain scheduleJob = new ScheduleJobDomain();
            scheduleJob.setJobGroup("group1");
            scheduleJob.setJobName(job_name);
            scheduleJob.setQuartz(time);
            Map<String, Object> conditionMap = new HashMap<String, Object>();
            conditionMap.put("contractTemplateFillingDataPojo", contractTemplateFillingDataPojo);
            conditionMap.put("userinfo", userinfo);
            scheduleJobService.addJob(scheduleJob, conditionMap);
            mapOut.put("flag",true);
            mapOut.put("msg",basePdfPath);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            throw new RuntimeException("合同生成失败!");
        }
        return mapOut;
    }



    /**
     * 获取合同序号
//     * @param regionCodeStr
     * @return
     */
    private String getHtQue(){
//        String htSnPrefix = PropertiesUtil.getApplyTypeProperties(regionCodeStr);//合同编号前缀
        Map<String,Object> out = serialNumService.getSerialNum("XYXH");
        if(!(Boolean) out.get("flag")){
            return "流水号生成失败!";
        }
        //序号编码
        String htSnCode = (String)out.get("msg");
//        String  htCode = htSnPrefix + htSnCode;//合同协议编号
        String htQue = htSnCode.substring(htSnCode.length()-3);//合同序号（后3位）
        return htQue;
    }

    /**
     * 转换区域信息
     * @param regionCode
     * @return
     */
    public String parseRegionCode(String regionCode){
        //合同序号
        String regionCodeStr = null;
        if("鼓楼区".equals(regionCode)){
            regionCodeStr = "鼓楼";
        } else if ("云龙区".equals(regionCode)) {
            regionCodeStr = "云龙";
        } else if ("泉山区".equals(regionCode)) {
            regionCodeStr = "泉山";
        } else if ("经济技术开发区".equals(regionCode)) {
            regionCodeStr = "经济技术开发";
        }
        return regionCodeStr;
    }


    /**
     * 获取填充信息
     * @param apSqlb
     * @param objId
     * @param fmIds 房屋IDs
     * @param cId 合同ID
     * @return
     */
    public ContractTemplateFillingDataPojo getContractTemplateFillingDataPojo(String apSqlb,String objId,String fmIds,String cId){
        ContractTemplateFillingDataPojo contractTemplateFillingDataPojo = new ContractTemplateFillingDataPojo();

        contractTemplateFillingDataPojo.setObjId(objId);//申请ID
        contractTemplateFillingDataPojo.setApSqlb(apSqlb);//申请类型

        setSomeDateInfo(contractTemplateFillingDataPojo);//填充起止时间
        //获取房屋ID
        String[] fmIdArr = null;
        if(StringUtils.isNotEmpty(fmIds)){
            fmIdArr = fmIds.split(",");
        }


        //根据申请类型判断合同类型
        if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))) {//公租房

            contractTemplateFillingDataPojo.setCtCode("002");//合同编号

            //公租房申请信息
            Apply apply = applyMapper.selectById(objId);

            setContractCodeInfo(contractTemplateFillingDataPojo,apply.getApSsq());//填充合同编号、流水、中间时间部分、前缀信息

            ApplyFamilyMember applyFamilyMember = applyFamilyMemberMapper.selectById(apply.getApSqrid());//获取申请人信息

            List<SourceHouse> sourceHouses = sourceHouseMapper.selectByCondition(new SourceHouse(objId));//房源信息
            SourceHouse sourceHouse = sourceHouses.get(0);
            String room = "";//室号 由于一个人可以分配多套房子
            BigDecimal constructionArea = new BigDecimal(0);
            if(sourceHouses.size()>1 && fmIdArr!=null){
                for (int i=0;i<fmIdArr.length;i++){
                    FactMapping factMapping = factMappingMapper.selectById(fmIdArr[i]);///房源楼盘表
                    room = room + factMapping.getfRonum();
                    if(i<fmIdArr.length-1){
                        room = room + "、";
                    }
                    constructionArea = constructionArea.add(factMapping.getfConacre2());
                }
            }else{
                FactMapping factMapping = factMappingMapper.selectById(sourceHouse.getShFwid());///房源楼盘表
                room = factMapping.getfRonum();
                constructionArea = constructionArea.add(factMapping.getfConacre2());
            }


            ItemCodeInfo itemCodeInfo = itemCodeInfoMapper.selectById(sourceHouse.getShXmid());//房源项目信息

            FactMapping factMapping = factMappingMapper.selectById(sourceHouse.getShFwid());///房源楼盘表

            ApplyFamilyHouse applyFamilyHouse = applyFamilyHouseMapper.findByApplyId(objId);//家庭住房表

            //申请人信息
            contractTemplateFillingDataPojo.setUserName(applyFamilyMember.getAfmXm());//姓名
            contractTemplateFillingDataPojo.setUserCardId(applyFamilyMember.getAfmSfzh());//身份证
            contractTemplateFillingDataPojo.setMobilePhone(applyFamilyMember.getAfmLxdh());//联系电话
            contractTemplateFillingDataPojo.setEmployer(applyFamilyMember.getAfmGzdw());//工作单位
            contractTemplateFillingDataPojo.setLessee(applyFamilyMember.getAfmXm());//承租人
            contractTemplateFillingDataPojo.setLesseeCardId(applyFamilyMember.getAfmSfzh());//承租人身份证
            contractTemplateFillingDataPojo.setLesseeContactPhone(applyFamilyMember.getAfmLxdh());//联系电话
            contractTemplateFillingDataPojo.setContactAddress(applyFamilyHouse==null?"":applyFamilyHouse.getAfhZl());//承租人坐落地址

            ApplyUnit applyUnit = applyUnitMapper.findByApplyId(objId);//获取单位信息
            if(applyUnit!=null){
                contractTemplateFillingDataPojo.setLegalRepresentative(applyUnit.getLegelrep());//法定代表人
                contractTemplateFillingDataPojo.setBusinessLicense(applyUnit.getBsls());//营业执照号
                contractTemplateFillingDataPojo.setPrincipadName(applyUnit.getEntag());//委托代理人
                contractTemplateFillingDataPojo.setPrincipadMobilePhone(applyUnit.getTel());//委托代理人手机号码
                contractTemplateFillingDataPojo.setPrincipadEmployer(applyFamilyMember.getAfmGzdw());//工作单位
                contractTemplateFillingDataPojo.setPrincipadContactAddress(applyUnit.getAddress());//委托代理人联系地址
            }

            contractTemplateFillingDataPojo.setStreet(itemCodeInfo.getIcItsite());//街道
            contractTemplateFillingDataPojo.setCommunity(itemCodeInfo.getIcItname());//小区名
            contractTemplateFillingDataPojo.setBuilding(factMapping.getfBuname().replace("#楼",""));//栋
            contractTemplateFillingDataPojo.setUnit(factMapping.getfCecode());//单元
            contractTemplateFillingDataPojo.setRoom(room);//室号
            contractTemplateFillingDataPojo.setConstructionArea(constructionArea.toString());//建筑面积

            contractTemplateFillingDataPojo.setBuildingStructure(factMapping.getfHostru());//建筑结构

        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))) {//外来务工
            contractTemplateFillingDataPojo.setCtCode("003");
            ApplyForForeign applyForForeign = applyForForeignMapper.selectById(objId);//外来务工

            setContractCodeInfo(contractTemplateFillingDataPojo,applyForForeign.getAffSsq());//填充合同编号、流水、中间时间部分、前缀信息

            ApplyFamilyMember applyFamilyMember = applyFamilyMemberMapper.selectById(applyForForeign.getAffSqrid());//获取申请人信息
            contractTemplateFillingDataPojo.setUserName(applyFamilyMember.getAfmXm());//乙方
            //获取单位信息
            ApplyUnit applyUnit = applyUnitMapper.findByApplyId(objId);
            contractTemplateFillingDataPojo.setLegalRepresentative(applyUnit.getLegelrep());//法定代表人
            contractTemplateFillingDataPojo.setBusinessLicense(applyUnit.getBsls());//营业执照号
            contractTemplateFillingDataPojo.setPrincipadName(applyUnit.getEntag());//委托代理人
            contractTemplateFillingDataPojo.setPrincipadMobilePhone(applyUnit.getTel());//委托代理人手机号码
            contractTemplateFillingDataPojo.setPrincipadContactAddress(applyUnit.getAddress());//委托代理人联系地址

            ApplyFamilyHouse applyFamilyHouse = applyFamilyHouseMapper.findByApplyId(objId);//家庭住房表

            contractTemplateFillingDataPojo.setLessee(applyFamilyMember.getAfmXm());//承租人
            contractTemplateFillingDataPojo.setLesseeCardId(applyFamilyMember.getAfmSfzh());//承租人身份证号码
            contractTemplateFillingDataPojo.setLesseeContactPhone(applyFamilyMember.getAfmLxdh());//承租人联系电话
            contractTemplateFillingDataPojo.setLesseeContactAddress(applyFamilyHouse==null?"":applyFamilyHouse.getAfhZl());//承租人坐落地址

            List<SourceHouse> sourceHouses = sourceHouseMapper.selectByCondition(new SourceHouse(objId));//房源信息
            SourceHouse sourceHouse = sourceHouses.get(0);
            String room = "";//室号 由于一个人可以分配多套房子
            BigDecimal constructionArea = new BigDecimal(0);
            if(sourceHouses.size()>1 && fmIdArr!=null){
                for (int i=0;i<fmIdArr.length;i++){
                    FactMapping factMapping = factMappingMapper.selectById(fmIdArr[i]);///房源楼盘表
                    room = room + factMapping.getfRonum();
                    if(i<fmIdArr.length-1){
                        room = room + "、";
                    }
                    constructionArea = constructionArea.add(factMapping.getfConacre2());
                }
            }else{
                FactMapping factMapping = factMappingMapper.selectById(sourceHouse.getShFwid());///房源楼盘表
                room = factMapping.getfRonum();
                constructionArea = constructionArea.add(factMapping.getfConacre2());
            }

            ItemCodeInfo itemCodeInfo = itemCodeInfoMapper.selectById(sourceHouse.getShXmid());//房源项目信息

            FactMapping factMapping = factMappingMapper.selectById(sourceHouse.getShFwid());//房源楼盘表

            contractTemplateFillingDataPojo.setCommunity(itemCodeInfo.getIcItname());//区
            contractTemplateFillingDataPojo.setBuilding(factMapping.getfBuname().replace("#楼",""));//栋
            contractTemplateFillingDataPojo.setUnit(factMapping.getfCecode());//单元
            contractTemplateFillingDataPojo.setRoom(room);//室号
//            contractTemplateFillingDataPojo.setRoomNum("");//居室 公寓、一室、二室
            contractTemplateFillingDataPojo.setConstructionArea(constructionArea.toString());//建筑面积

            contractTemplateFillingDataPojo.setBuildingStructure(factMapping.getfHostru());//建筑结构

        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("butie"))) {//补贴
            contractTemplateFillingDataPojo.setCtCode("001");
            ApplyButie applyButie = applyButieMapper.selectById(objId);//补贴信息
            //获取合同信息
            if(StringUtils.isNotEmpty(cId)){
                ContractDetail contractDetail = contractDetailMapper.selectById(cId);//当前合同信息
                if("2".equals(contractDetail.getcType()) || "4".equals(contractDetail.getcType())){//新数据年审、老数据多次年审
                    //获取上一次有效数据
                    ContractDetail contractDetail1Map = new ContractDetail();
                    contractDetail1Map.setcSqid(objId);//申请ID
                    contractDetail1Map.setcZfzt(new Short("0"));//有效
                    List<ContractDetail> contractDetail1Maps = contractDetailMapper.selectByCondition(contractDetail1Map);
                    Date endTime = null;
                    for (int i=0;i<contractDetail1Maps.size();i++){
                        if(contractDetail1Maps.get(i)!=null && !cId.equals(contractDetail1Maps.get(i).getcId())){
                            endTime = contractDetail1Maps.get(i).getcEndTime();
                            /*contractDetail1Maps.get(i).setcZfzt(new Short("1"));//过期状态 0 未过期 1过期作废
                            contractDetailMapper.update(contractDetail1Maps.get(i));*/
                        }
                    }
                    if(endTime!=null){
                        //推迟一天:
                        Calendar curr = Calendar.getInstance();
                        curr.setTime(endTime);
                        curr.set(Calendar.DATE,curr.get(Calendar.DATE)+1);
                        Date newDateStart=curr.getTime();
                        String yearSta = DatetimeUtils.date2string(newDateStart,DatetimeUtils.YYYY);//年
                        String monthSta = DatetimeUtils.date2string(newDateStart,DatetimeUtils.MM);//月
                        String daySta = DatetimeUtils.date2string(newDateStart,DatetimeUtils.DD);//日
                        //推迟一年
                        curr.setTime(endTime);
                        curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+1);
                        curr.set(Calendar.DATE,curr.get(Calendar.DATE)-1);
                        Date newDateEnd=curr.getTime();
                        String yearEnd = DatetimeUtils.date2string(newDateEnd,DatetimeUtils.YYYY);//后一年 年
                        String monthEnd = DatetimeUtils.date2string(newDateEnd,DatetimeUtils.MM);//后一年 月
                        String dayEnd = DatetimeUtils.date2string(newDateEnd,DatetimeUtils.DD);//后一年 天
                        // 填充数据
                        contractTemplateFillingDataPojo.setYearSta(yearSta);
                        contractTemplateFillingDataPojo.setMonthSta(monthSta);
                        contractTemplateFillingDataPojo.setDaySta(daySta);
                        contractTemplateFillingDataPojo.setYearEnd(yearEnd);
                        contractTemplateFillingDataPojo.setMonthEnd(monthEnd);
                        contractTemplateFillingDataPojo.setDayEnd(dayEnd);

                    }
                }
            }



            setContractCodeInfo(contractTemplateFillingDataPojo,applyButie.getAbSsq());//填充合同编号、流水、中间时间部分、前缀信息

            ApplyFamilyMember applyFamilyMember = applyFamilyMemberMapper.selectById(applyButie.getAbSqrid());//获取申请人信息

            ApplyFamilyHouse applyFamilyHouse = applyFamilyHouseMapper.findByApplyId(applyButie.getAbId());//家庭住房表

            contractTemplateFillingDataPojo.setUserName(applyFamilyMember.getAfmXm());//乙方
            contractTemplateFillingDataPojo.setUserCardId(applyFamilyMember.getAfmSfzh());//身份证
            contractTemplateFillingDataPojo.setFamilyNum(applyButie.getAbJtrk().toString());//家庭人数

            //人均住房面积
            if("4".equals(applyFamilyHouse.getAfhZfxz()) && applyFamilyHouse.getAfhRjmj()!=null){//住房性质4 表示自有私房
                contractTemplateFillingDataPojo.setPerCapitaArea(applyFamilyHouse.getAfhRjmj().toString());
            } else {
                contractTemplateFillingDataPojo.setPerCapitaArea("0");
            }
            contractTemplateFillingDataPojo.setAccountTitle("交通银行");//账户名称

        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))) {//新就业
            contractTemplateFillingDataPojo.setCtCode("003");
            ApplyForgraDuate applyForgraDuate = applyForgraDuateMapper.selectById(objId);//新就业

            setContractCodeInfo(contractTemplateFillingDataPojo,applyForgraDuate.getAfgSsq());//填充合同编号、流水、中间时间部分、前缀信息

            ApplyFamilyMember applyFamilyMember = applyFamilyMemberMapper.selectById(applyForgraDuate.getAfgSqrid());//获取申请人信息

            contractTemplateFillingDataPojo.setUserName(applyFamilyMember.getAfmXm());//乙方

            //获取单位信息
            ApplyUnit applyUnit = applyUnitMapper.findByApplyId(objId);
            contractTemplateFillingDataPojo.setLegalRepresentative(applyUnit.getLegelrep());//法定代表人
            contractTemplateFillingDataPojo.setBusinessLicense(applyUnit.getBsls());//营业执照号
            contractTemplateFillingDataPojo.setPrincipadName(applyUnit.getEntag());//委托代理人
            contractTemplateFillingDataPojo.setPrincipadMobilePhone(applyUnit.getTel());//委托代理人手机号码
            contractTemplateFillingDataPojo.setPrincipadContactAddress(applyUnit.getAddress());//委托代理人联系地址

            ApplyFamilyHouse applyFamilyHouse = applyFamilyHouseMapper.findByApplyId(objId);//家庭住房表
            contractTemplateFillingDataPojo.setLessee(applyFamilyMember.getAfmXm());//承租人
            contractTemplateFillingDataPojo.setLesseeCardId(applyFamilyMember.getAfmSfzh());//承租人身份证号码
            contractTemplateFillingDataPojo.setLesseeContactPhone(applyFamilyMember.getAfmLxdh());//承租人联系电话
            if(applyFamilyHouse!=null && StringUtils.isNotEmpty(applyFamilyHouse.getAfhZl())){
                contractTemplateFillingDataPojo.setLesseeContactAddress(applyFamilyHouse==null?"":applyFamilyHouse.getAfhZl());//承租人坐落地址
            }else{
                contractTemplateFillingDataPojo.setLesseeContactAddress("");//承租人坐落地址
            }

            //房源信息
            SourceHouse sourceHouse = new SourceHouse();
            sourceHouse.setShApplyid(objId);
            List<SourceHouse> sourceHouses = sourceHouseMapper.selectByCondition(sourceHouse);//房源信息
            sourceHouse = sourceHouses.get(0);
            String room = "";//室号 由于一个人可以分配多套房子
            BigDecimal constructionArea = new BigDecimal(0);
            if(sourceHouses.size()>1 && fmIdArr!=null){
                for (int i=0;i<fmIdArr.length;i++){
                    FactMapping factMapping = factMappingMapper.selectById(fmIdArr[i]);///房源楼盘表
                    room = room + factMapping.getfRonum();
                    if(i<fmIdArr.length-1){
                        room = room + "、";
                    }
                    constructionArea = constructionArea.add(factMapping.getfConacre2());
                }
            }else{
                FactMapping factMapping = factMappingMapper.selectById(sourceHouse.getShFwid());///房源楼盘表
                room = factMapping.getfRonum();
                constructionArea = constructionArea.add(factMapping.getfConacre2());
            }

            ItemCodeInfo itemCodeInfo = itemCodeInfoMapper.selectById(sourceHouse.getShXmid());//房源项目信息
            //房源楼盘表
            FactMapping factMapping = factMappingMapper.selectById(sourceHouse.getShFwid());//房屋表信息
            contractTemplateFillingDataPojo.setCommunity(itemCodeInfo.getIcItname());//区
            contractTemplateFillingDataPojo.setBuilding(factMapping.getfBuname().replace("#楼",""));//栋
            contractTemplateFillingDataPojo.setUnit(factMapping.getfCecode());//单元
            contractTemplateFillingDataPojo.setRoom(room);//室号
            contractTemplateFillingDataPojo.setConstructionArea(constructionArea.toString());//建筑面积

            contractTemplateFillingDataPojo.setBuildingStructure(factMapping.getfHostru());//建筑结构
        } else {
            throw new RuntimeException("没有当前申请类型!");
        }
        return contractTemplateFillingDataPojo;
    }

    /**
     * 获取时间从##时间起至##时间止 并填充
     * @param contractTemplateFillingDataPojo
     */
    private void setSomeDateInfo(ContractTemplateFillingDataPojo contractTemplateFillingDataPojo){

        //当前时间
        String yearSta = DatetimeUtils.getNowYear();//年
        String monthSta = DatetimeUtils.getNowMonth();//月
        String daySta = DatetimeUtils.getNowDay();//日
        //推迟一年:
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+1);
        curr.set(Calendar.DATE,curr.get(Calendar.DATE)-1);
        Date date=curr.getTime();
        String yearEnd = DatetimeUtils.date2string(date,DatetimeUtils.YYYY);//后一年 年
        String monthEnd = DatetimeUtils.date2string(date,DatetimeUtils.MM);//后一年 月
        String dayEnd = DatetimeUtils.date2string(date,DatetimeUtils.DD);//后一年 天

        //填充数据
        contractTemplateFillingDataPojo.setYearSta(yearSta);
        contractTemplateFillingDataPojo.setMonthSta(monthSta);
        contractTemplateFillingDataPojo.setDaySta(daySta);
        contractTemplateFillingDataPojo.setYearEnd(yearEnd);
        contractTemplateFillingDataPojo.setMonthEnd(monthEnd);
        contractTemplateFillingDataPojo.setDayEnd(dayEnd);
        contractTemplateFillingDataPojo.setDeadline("25");//按月25日转入乙方账户
        contractTemplateFillingDataPojo.setYearTime("1");//时间期限长度（年）
    }

    /**
     * 获取合同信息
     * @param contractTemplateFillingDataPojo
     * @param ssq 所属区
     */
    private void setContractCodeInfo(ContractTemplateFillingDataPojo contractTemplateFillingDataPojo,
                                     String ssq){
        ParmItem parmItem = getParmItem("04",ssq);//获取申请单区域信息
        String regionName = parmItem.getPiItemname();//获取区域名（###区）
        String regionCodeStr = parseRegionCode(regionName);//获取转换后的区域(鼓楼、云龙、泉山[##])
        contractTemplateFillingDataPojo.setRegionCode(parmItem.getPiItemcode());//区域编码
        contractTemplateFillingDataPojo.setRegionCodeStr(regionCodeStr);//获取转换后的区域(鼓楼、云龙、泉山)

        SerialNumLine serialNumLine = serialNumService.getSerialNumInfo("XYXH");//获取合同序号(流水号)
        String prefix = PropertiesUtil.getContractProperties(regionCodeStr);//合同编号前缀
        if(StringUtils.isEmpty(prefix)){
            throw new RuntimeException("获取合同编号前缀失败!");
        }
        String contractNo = prefix + serialNumLine.getCode();//合同编码
        String htQue = serialNumLine.getPosition();//合同序号[流水号]
        contractTemplateFillingDataPojo.setHtQue(htQue);//合同序号
        contractTemplateFillingDataPojo.setYear(serialNumLine.getSnDateStructure());//合同中间时间部分
        contractTemplateFillingDataPojo.setContractNo(contractNo);//合同编号
    }

    /**
     * 根据申请类型添加合同模板
     * @param contractTemplateFillingDataPojo
     * @param apSqlb 合同类型
     * @param objId id
     * @param contractNo 合同编码
     * @return
     */
    private ContractTemplateFillingDataPojo
                    getCtCodeByApSqlb(ContractTemplateFillingDataPojo contractTemplateFillingDataPojo,
                    String apSqlb,String objId,String contractNo,String htQue){

        setSomeDateInfo(contractTemplateFillingDataPojo);//填充起止时间

        //合同模板编号
        if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))) {//公租房
            contractTemplateFillingDataPojo.setCtCode("002");
            //获取合同信息
            Apply apply = applyMapper.selectById(objId);//获取申请信息
            ParmItem parmItem = getParmItem("04",apply.getApSsq());//获取区域信息
            String regionCode = parmItem.getPiItemname();
            contractTemplateFillingDataPojo.setRegionCode(parmItem.getPiItemcode());//区域编码
            String regionCodeStr = parseRegionCode(regionCode);//获取转换后的区域(鼓楼、云龙、泉山)
            contractTemplateFillingDataPojo.setRegionCodeStr(regionCodeStr);//获取转换后的区域(鼓楼、云龙、泉山)
            if(StringUtils.isEmpty(contractNo)){
                htQue = getHtQue();//获取合同序号
                String prefix = PropertiesUtil.getContractProperties(regionCodeStr);//合同编号前缀
                if(StringUtils.isEmpty(prefix)){
                    return null;
                }
                contractNo = prefix + DatetimeUtils.getNowSystemDateString() + htQue;
            }
            contractTemplateFillingDataPojo.setHtQue(htQue);//合同序号
            contractTemplateFillingDataPojo.setContractNo(contractNo);//合同编号
            ApplyFamilyMember applyFamilyMember = applyFamilyMemberMapper.selectById(apply.getApSqrid());//获取申请人信息
            //房源信息
            SourceHouse sourceHouse = new SourceHouse();
            sourceHouse.setShApplyid(objId);
            List<SourceHouse> sourceHouses = sourceHouseMapper.selectByCondition(sourceHouse);//房源信息
            sourceHouse = sourceHouses.get(0);
            ItemCodeInfo itemCodeInfo = itemCodeInfoMapper.selectById(sourceHouse.getShXmid());//房源项目信息
            //房源楼盘表
            FactMapping factMapping = factMappingMapper.selectById(sourceHouse.getShFwid());//房屋表信息
            //申请人信息
            contractTemplateFillingDataPojo.setUserName(applyFamilyMember.getAfmXm());//姓名
            contractTemplateFillingDataPojo.setUserCardId(applyFamilyMember.getAfmSfzh());//身份证
            contractTemplateFillingDataPojo.setFixedTelephone("");//固定电话
            contractTemplateFillingDataPojo.setMobilePhone(applyFamilyMember.getAfmLxdh());//联系电话
            contractTemplateFillingDataPojo.setEmployer(applyFamilyMember.getAfmGzdw());//工作单位
            contractTemplateFillingDataPojo.setContactAddress("");//联系地址
            contractTemplateFillingDataPojo.setPostalCode("");//邮政编码
            contractTemplateFillingDataPojo.setLessee(applyFamilyMember.getAfmXm());//承租人
            contractTemplateFillingDataPojo.setLesseeCardId(applyFamilyMember.getAfmSfzh());//承租人身份证
            contractTemplateFillingDataPojo.setLesseeContactPhone(applyFamilyMember.getAfmLxdh());//联系电话
            //获取单位信息
            ApplyUnit applyUnit = applyUnitMapper.findByApplyId(objId);
            if(applyUnit!=null){
                contractTemplateFillingDataPojo.setLegalRepresentative(applyUnit.getLegelrep());//法定代表人
                contractTemplateFillingDataPojo.setBusinessLicense(applyUnit.getBsls());//营业执照号
                contractTemplateFillingDataPojo.setPrincipadName(applyUnit.getEntag());//委托代理人
                contractTemplateFillingDataPojo.setPrincipadCardId("");//委托代理人身份证
                contractTemplateFillingDataPojo.setPrincipadAddress("");//委托代理人地址
                contractTemplateFillingDataPojo.setPrincipadPostalCode("");//委托代理人邮编地址
                contractTemplateFillingDataPojo.setPrincipadTelephone("");//委托代理人固定电话
                contractTemplateFillingDataPojo.setPrincipadMobilePhone(applyUnit.getTel());//委托代理人手机号码
                contractTemplateFillingDataPojo.setPrincipadEmployer(applyFamilyMember.getAfmGzdw());//工作单位
                contractTemplateFillingDataPojo.setPrincipadContactAddress(applyUnit.getAddress());//委托代理人联系地址
            }else{
                contractTemplateFillingDataPojo.setLegalRepresentative("");//法定代表人
                contractTemplateFillingDataPojo.setBusinessLicense("");//营业执照号
                contractTemplateFillingDataPojo.setPrincipadName("");//委托代理人姓名
                contractTemplateFillingDataPojo.setPrincipadCardId("");//委托代理人身份证
                contractTemplateFillingDataPojo.setPrincipadAddress("");//委托代理人地址
                contractTemplateFillingDataPojo.setPrincipadPostalCode("");//委托代理人邮编地址
                contractTemplateFillingDataPojo.setPrincipadTelephone("");//委托代理人固定电话
                contractTemplateFillingDataPojo.setPrincipadMobilePhone("");//委托代理人手机
                contractTemplateFillingDataPojo.setPrincipadEmployer("");//工作单位
                contractTemplateFillingDataPojo.setPrincipadContactAddress("");//委托代理人联系地址
            }

            contractTemplateFillingDataPojo.setStreet(itemCodeInfo.getIcItsite());//街道
            contractTemplateFillingDataPojo.setCommunity(itemCodeInfo.getIcItname());//小区名
            contractTemplateFillingDataPojo.setBuilding(factMapping.getfBuname().replace("#楼",""));//栋
            contractTemplateFillingDataPojo.setUnit(factMapping.getfCecode());//单元
            contractTemplateFillingDataPojo.setRoom(factMapping.getfRonum());//室号
            contractTemplateFillingDataPojo.setConstructionArea(factMapping.getfConacre().toString());//建筑面积
            contractTemplateFillingDataPojo.setRentUnitPrice("");//租金单价
            contractTemplateFillingDataPojo.setRent("");//租金
            contractTemplateFillingDataPojo.setRentName("");//租金中文
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))) {//外来务工
            contractTemplateFillingDataPojo.setCtCode("003");
            ApplyForForeign applyForForeign = applyForForeignMapper.selectById(objId);//外来务工
            //根据区域ID获取区域
            ParmItem parmItem = getParmItem("04",applyForForeign.getAffSsq());//获取区域信息
            String regionCode = parmItem.getPiItemname();
            contractTemplateFillingDataPojo.setRegionCode(parmItem.getPiItemcode());//区域编码
            String regionCodeStr = parseRegionCode(regionCode);//获取转换后的区域(鼓楼、云龙、泉山)
            contractTemplateFillingDataPojo.setRegionCodeStr(regionCodeStr);//获取转换后的区域(鼓楼、云龙、泉山)
            contractTemplateFillingDataPojo.setYear(DatetimeUtils.getNowYear());//年份
            if(StringUtils.isEmpty(contractNo)){
                htQue = getHtQue();//获取合同序号
                String prefix = PropertiesUtil.getContractProperties(regionCodeStr);//合同编号前缀
                contractNo = prefix + DatetimeUtils.getNowSystemDateString() + htQue;
            }
            contractTemplateFillingDataPojo.setHtQue(htQue);//合同序号
            contractTemplateFillingDataPojo.setContractNo(contractNo);//合同编号
            ApplyFamilyMember applyFamilyMember = applyFamilyMemberMapper.selectById(applyForForeign.getAffSqrid());//获取申请人信息
            contractTemplateFillingDataPojo.setUserName(applyFamilyMember.getAfmXm());//乙方
            //获取单位信息
            ApplyUnit applyUnit = applyUnitMapper.findByApplyId(objId);
            contractTemplateFillingDataPojo.setLegalRepresentative(applyUnit.getLegelrep());//法定代表人
            contractTemplateFillingDataPojo.setBusinessLicense(applyUnit.getBsls());//营业执照号
            contractTemplateFillingDataPojo.setPrincipadName(applyUnit.getEntag());//委托代理人
            contractTemplateFillingDataPojo.setPrincipadMobilePhone(applyUnit.getTel());//委托代理人手机号码
            contractTemplateFillingDataPojo.setPrincipadContactAddress(applyUnit.getAddress());//委托代理人联系地址

            contractTemplateFillingDataPojo.setLessee(applyFamilyMember.getAfmXm());//承租人
            contractTemplateFillingDataPojo.setLesseeCardId(applyFamilyMember.getAfmSfzh());//承租人身份证号码
            contractTemplateFillingDataPojo.setLesseeContactAddress("");//承租人联系地址
            contractTemplateFillingDataPojo.setLesseeContactPhone(applyFamilyMember.getAfmLxdh());//承租人联系电话
            //房源信息
            SourceHouse sourceHouse = new SourceHouse();
            sourceHouse.setShApplyid(objId);
            List<SourceHouse> sourceHouses = sourceHouseMapper.selectByCondition(sourceHouse);//房源信息
            sourceHouse = sourceHouses.get(0);
            ItemCodeInfo itemCodeInfo = itemCodeInfoMapper.selectById(sourceHouse.getShXmid());//房源项目信息
            //房源楼盘表
            FactMapping factMapping = factMappingMapper.selectById(sourceHouse.getShFwid());//房屋表信息
            contractTemplateFillingDataPojo.setCommunity(itemCodeInfo.getIcItname());//区
            contractTemplateFillingDataPojo.setBuilding(factMapping.getfBuname().replace("#楼",""));//栋
            contractTemplateFillingDataPojo.setUnit(factMapping.getfCecode());//单元
            contractTemplateFillingDataPojo.setRoom(factMapping.getfRonum());//室号
            contractTemplateFillingDataPojo.setRoomNum("");//居室
            contractTemplateFillingDataPojo.setConstructionArea(factMapping.getfConacre().toString());//建筑面积
            contractTemplateFillingDataPojo.setRentUnitPrice("");//租金单价
            contractTemplateFillingDataPojo.setRent("");//租金
            contractTemplateFillingDataPojo.setRentName("");//租金中文
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("butie"))) {//补贴
            contractTemplateFillingDataPojo.setCtCode("001");
            ApplyButie applyButie = applyButieMapper.selectById(objId);//补贴信息
            //根据区域ID获取区域
            ParmItem parmItem = getParmItem("04",applyButie.getAbSsq());//获取区域信息
            String regionCode = parmItem.getPiItemname();
            contractTemplateFillingDataPojo.setRegionCode(parmItem.getPiItemcode());//区域编码
            String regionCodeStr = parseRegionCode(regionCode);//获取转换后的区域(鼓楼、云龙、泉山)
            contractTemplateFillingDataPojo.setRegionCodeStr(regionCodeStr);//获取转换后的区域(鼓楼、云龙、泉山)
            contractTemplateFillingDataPojo.setYear(DatetimeUtils.getNowYear());//年份
            if(StringUtils.isEmpty(contractNo)){
                htQue = getHtQue();//获取合同序号
                String prefix = PropertiesUtil.getContractProperties(regionCodeStr);//合同编号前缀
                contractNo = prefix + DatetimeUtils.getNowSystemDateString() + htQue;
            }
            contractTemplateFillingDataPojo.setHtQue(htQue);//合同序号
            contractTemplateFillingDataPojo.setContractNo(contractNo);//合同编号
            ApplyFamilyMember applyFamilyMember = applyFamilyMemberMapper
                    .selectById(applyButie.getAbSqrid());//获取申请人信息
            ApplyFamilyHouse applyFamilyHouse = applyFamilyHouseMapper.findByApplyId(applyButie.getAbSqrid());//家庭住房表

            contractTemplateFillingDataPojo.setUserName(applyFamilyMember.getAfmXm());//乙方
            contractTemplateFillingDataPojo.setUserCardId(applyFamilyMember.getAfmSfzh());//身份证
            contractTemplateFillingDataPojo.setFamilyNum(applyButie.getAbJtrk().toString());//家庭人数
            contractTemplateFillingDataPojo.setRentalSubsidiesNum("");//家庭补贴人数
            contractTemplateFillingDataPojo.setPerCapitaArea(
                    applyFamilyHouse.getAfhRjmj()==null?"":applyFamilyHouse.getAfhRjmj().toString());//人均住房面积
            contractTemplateFillingDataPojo.setRentalSubsidiesArea("");//人均享受补贴住房面积
            contractTemplateFillingDataPojo.setRentalSubsidiesUP("");//补贴发放标准
            contractTemplateFillingDataPojo.setRentalSubsidiesTP("");//补贴金额
            contractTemplateFillingDataPojo.setRentalSubsidiesTPZH("");//补贴金额大写
            contractTemplateFillingDataPojo.setAccountTitle("交通银行");//账户名称
            contractTemplateFillingDataPojo.setBankAccount("");//银行账户
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))) {//新就业
            contractTemplateFillingDataPojo.setCtCode("003");
            ApplyForgraDuate applyForgraDuate = applyForgraDuateMapper.selectById(objId);//新就业
            //根据区域ID获取区域
            ParmItem parmItem = getParmItem("04",applyForgraDuate.getAfgSsq());//获取区域信息
            String regionCode = parmItem.getPiItemname();
            contractTemplateFillingDataPojo.setRegionCode(parmItem.getPiItemcode());//区域编码
            String regionCodeStr = parseRegionCode(regionCode);//获取转换后的区域(鼓楼、云龙、泉山)
            contractTemplateFillingDataPojo.setRegionCodeStr(regionCodeStr);//获取转换后的区域(鼓楼、云龙、泉山)
            contractTemplateFillingDataPojo.setYear(DatetimeUtils.getNowYear());//年份
            if(StringUtils.isEmpty(contractNo)){
                htQue = getHtQue();//获取合同序号
                String prefix = PropertiesUtil.getContractProperties(regionCodeStr);//合同编号前缀
                contractNo = prefix + DatetimeUtils.getNowSystemDateString() + htQue;
            }
            contractTemplateFillingDataPojo.setHtQue(htQue);//合同序号
            contractTemplateFillingDataPojo.setContractNo(contractNo);//合同编号
            ApplyFamilyMember applyFamilyMember = applyFamilyMemberMapper
                    .selectById(applyForgraDuate.getAfgSqrid());//获取申请人信息
            contractTemplateFillingDataPojo.setUserName(applyFamilyMember.getAfmXm());//乙方
            //获取单位信息
            ApplyUnit applyUnit = applyUnitMapper.findByApplyId(objId);
            contractTemplateFillingDataPojo.setLegalRepresentative(applyUnit.getLegelrep());//法定代表人
            contractTemplateFillingDataPojo.setBusinessLicense(applyUnit.getBsls());//营业执照号
            contractTemplateFillingDataPojo.setPrincipadName(applyUnit.getEntag());//委托代理人
            contractTemplateFillingDataPojo.setPrincipadMobilePhone(applyUnit.getTel());//委托代理人手机号码
            contractTemplateFillingDataPojo.setPrincipadContactAddress(applyUnit.getAddress());//委托代理人联系地址

            contractTemplateFillingDataPojo.setLessee(applyFamilyMember.getAfmXm());//承租人
            contractTemplateFillingDataPojo.setLesseeCardId(applyFamilyMember.getAfmSfzh());//承租人身份证号码
            contractTemplateFillingDataPojo.setLesseeContactAddress("");//承租人联系地址
            contractTemplateFillingDataPojo.setLesseeContactPhone(applyFamilyMember.getAfmLxdh());//承租人联系电话
            //房源信息
            SourceHouse sourceHouse = new SourceHouse();
            sourceHouse.setShApplyid(objId);
            List<SourceHouse> sourceHouses = sourceHouseMapper.selectByCondition(sourceHouse);//房源信息
            sourceHouse = sourceHouses.get(0);
            ItemCodeInfo itemCodeInfo = itemCodeInfoMapper.selectById(sourceHouse.getShXmid());//房源项目信息
            //房源楼盘表
            FactMapping factMapping = factMappingMapper.selectById(sourceHouse.getShFwid());//房屋表信息
            contractTemplateFillingDataPojo.setCommunity(itemCodeInfo.getIcItname());//区
            contractTemplateFillingDataPojo.setBuilding(factMapping.getfBuname().replace("#楼",""));//栋
            contractTemplateFillingDataPojo.setUnit(factMapping.getfCecode());//单元
            contractTemplateFillingDataPojo.setRoom(factMapping.getfRonum());//室号
            contractTemplateFillingDataPojo.setRoomNum("");//居室
            contractTemplateFillingDataPojo.setConstructionArea(factMapping.getfConacre().toString());//建筑面积
            contractTemplateFillingDataPojo.setRentUnitPrice("");//租金单价
            contractTemplateFillingDataPojo.setRent("");//租金
            contractTemplateFillingDataPojo.setRentName("");//租金中文
        } else {
            return null;
        }
        return contractTemplateFillingDataPojo;
    }

    /**
     * 获取所属区域信息
     * @param piSetcode 固定数据字典编码
     * @param piItemcode 区域字段code
     * @return
     */
    public ParmItem getParmItem(String piSetcode, String piItemcode){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("piSetcode","04");
        map.put("piItemcode",piItemcode);
        List<ParmItem> parmItems = parmItemMapper.selectBySetCodeAndItemCode(map);
        ParmItem parmItem = parmItems.get(0);//获取区域信息
        return parmItem;
    }

    /**
     * 去null,并填充" "字符串
     * @param str 原字符串
     * @param length 需要字符串长度
     * @return
     */
    private String toParseSpace(String str,int length) {
        if(StringUtils.isEmpty(str)){
            str = "";
        }else if(str.length()>length){
            return str;
        }
        return String.format("%1$-" + length + "s",str);
    }


    public Object getFactMappingsBySqId(String objId, String apSqlb,String cType) {
        if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))) {//公租房
            //获取合同信息
            /*Apply apply = applyMapper.selectById(objId);//获取申请信息*/
            /*//房源信息
            SourceHouse sourceHouse = new SourceHouse();
            sourceHouse.setShApplyid(objId);
            List<SourceHouse> sourceHouses = sourceHouseMapper.selectByCondition(sourceHouse);//房源信息
            sourceHouse = sourceHouses.get(0);
            ItemCodeInfo itemCodeInfo = itemCodeInfoMapper.selectById(sourceHouse.getShXmid());//房源项目信息
            //房源楼盘表
            FactMapping factMapping = factMappingMapper.selectById(sourceHouse.getShFwid());//房屋表信息*/
            FactMapping factMapping = new FactMapping();
            factMapping.setSh_applyid(objId);
            List<FactMapping> factMappings = factMappingMapper.getFactMappingBySqId(factMapping);
            if(factMappings==null || factMappings.size()==0){
                return CommonUtils.getMsgForRet(false,"未获取房源信息");
            }
            if(factMappings!=null && factMappings.size()==1){
                return CommonUtils.getMsgForRet(true,"success");
            }
            Map<String,Object> resultMap = new HashMap<String, Object>();
            resultMap.put("rows",factMappings);
            resultMap.put("total",factMappings.size());
            resultMap.put("flag",true);
            resultMap.put("msg","msg");
            return resultMap;
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))) {//外来务工
            /*ApplyForForeign applyForForeign = applyForForeignMapper.selectById(objId);//外来务工*/
            /*//房源信息
            SourceHouse sourceHouse = new SourceHouse();
            sourceHouse.setShApplyid(objId);
            List<SourceHouse> sourceHouses = sourceHouseMapper.selectByCondition(sourceHouse);//房源信息
            sourceHouse = sourceHouses.get(0);
            ItemCodeInfo itemCodeInfo = itemCodeInfoMapper.selectById(sourceHouse.getShXmid());//房源项目信息
            //房源楼盘表
            FactMapping factMapping = factMappingMapper.selectById(sourceHouse.getShFwid());//房屋表信息*/
            FactMapping factMapping = new FactMapping();
            factMapping.setSh_applyid(objId);
            List<FactMapping> factMappings = factMappingMapper.getFactMappingBySqId(factMapping);
            if(factMappings==null || factMappings.size()==0){
                return CommonUtils.getMsgForRet(false,"未获取房源信息");
            }
            if(factMappings!=null && factMappings.size()==1){
                return CommonUtils.getMsgForRet(true,"success");
            }
            Map<String,Object> resultMap = new HashMap<String, Object>();
            resultMap.put("rows",factMappings);
            resultMap.put("total",factMappings.size());
            resultMap.put("flag",true);
            resultMap.put("msg","msg");
            return resultMap;
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("butie"))) {//补贴
            /*ApplyButie applyButie = applyButieMapper.selectById(objId);//补贴信息*/
            return CommonUtils.getMsgForRet(true,"success");
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))) {//新就业
            /*ApplyForgraDuate applyForgraDuate = applyForgraDuateMapper.selectById(objId);//新就业
            //房源信息
            SourceHouse sourceHouse = new SourceHouse();
            sourceHouse.setShApplyid(objId);
            List<SourceHouse> sourceHouses = sourceHouseMapper.selectByCondition(sourceHouse);//房源信息
            sourceHouse = sourceHouses.get(0);
            //房源楼盘表
            FactMapping factMapping = factMappingMapper.selectById(sourceHouse.getShFwid());//房屋表信息*/
            FactMapping factMapping = new FactMapping();
            factMapping.setSh_applyid(objId);
            List<FactMapping> factMappings = factMappingMapper.getFactMappingBySqId(factMapping);
            if(factMappings==null || factMappings.size()==0){
                return CommonUtils.getMsgForRet(false,"未获取房源信息");
            }
            if(factMappings!=null && factMappings.size()==1){
                return CommonUtils.getMsgForRet(true,"success");
            }
            Map<String,Object> resultMap = new HashMap<String, Object>();
            resultMap.put("rows",factMappings);
            resultMap.put("total",factMappings.size());
            resultMap.put("flag",true);
            resultMap.put("msg","msg");
            return resultMap;
        } else {
            return CommonUtils.getMsgForRet(true,"success");
        }
    }
}
