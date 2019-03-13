package com.sys.service;

import com.google.common.collect.Maps;
import com.sys.common.*;
import com.sys.mapper.HisRentMapper;
import com.sys.mapper.ParmItemMapper;
import com.sys.mapper.SourceHouseMapper;
import com.sys.mapper.apply.ApplyChangeMapper;
import com.sys.mapper.apply.ApplyMapper;
import com.sys.mapper.apply.ApproveMapper;
import com.sys.mapper.contract.ContractDetailMapper;
import com.sys.pojo.*;
import com.sys.pojo.apply.*;
import com.sys.pojo.contract.ContractDetail;
import com.sys.pojo.contract.ContractTemplate;
import com.sys.pojo.serialnum.SerialNumLine;
import com.sys.service.apply.*;
import com.sys.service.common.MessageAndFormService;
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
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ContractDetailService extends BaseService<ContractDetail>  {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ContractTemplateService contractTemplateService;

    @Autowired
    private SerialNumService serialNumService;
    @Autowired
    private ParmItemMapper parmItemMapper;

    /**
     * 经济适用、廉租房对象
     */
    @Autowired
    private ApplyService applyService;

    /**
     * 新就业对象
     */
    @Autowired
    private ApplyForgraDuateService applyForgraDuateService;

    /**
     * 住房补贴对象
     */
    @Autowired
    private ApplyButieService applyButieService;

    /**
     * 外来务工对象
     */
    @Autowired
    private ApplyForForeignService applyForForeignService;

    /**
     * 房源对象
     */
    @Autowired
    private SourceHouseMapper sourceHouseMapper;

    @Autowired
    private ContractDetailMapper contractDetailMapper;

    @Autowired
    private ApplyChangeMapper applyChangeMapper;

    /**
     * 发送短信接口
     */
    @Autowired
    private MessageAndFormService messageAndFormService;

    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 用户操作类
     */
    @Autowired
    private ApplyUserinfoService applyUserinfoService;

    /**
     * 流程记录表
     */
    @Autowired
    private ApproveMapper approveMapper;

    /**
     * 签约历史
     */
    @Autowired
    private HisRentMapper hisRentMapper;

    @Autowired
    private ContractDetailService contractDetailService;

    @Autowired
    private DynamicallyGeneratedWordUtil dynamicallyGeneratedWordService;

    /**
     * 备案动作
     * @param contractTemplateFillingDataPojo 对象
     * @param cHtbh 合同编号
     * @param dirAndName 文件生成位置
     * @param inputMapStr json字符串
     * @return
     */
    public String updateFiling(ContractTemplateFillingDataPojo contractTemplateFillingDataPojo, UserInfo userInfo,String cHtbh,String dirAndName,
                               String  inputMapStr){
        Date date = new Date();
        String objId = contractTemplateFillingDataPojo.getObjId();//ID
        String apSqlb = contractTemplateFillingDataPojo.getApSqlb();//申请类别
        String userid = null;

        String startDateStr = contractTemplateFillingDataPojo.getYearSta()
                + "-" + contractTemplateFillingDataPojo.getMonthSta()
                + "-" + contractTemplateFillingDataPojo.getDaySta();//补贴开始时间
        Date startDate = DatetimeUtils.string2date(startDateStr,DatetimeUtils.YYYY_MM_DD);
        String endDateStr = contractTemplateFillingDataPojo.getYearEnd()
                + "-" + contractTemplateFillingDataPojo.getMonthEnd()
                + "-" + contractTemplateFillingDataPojo.getDayEnd();//补贴结束时间
        Date endDate = DatetimeUtils.string2date(endDateStr,DatetimeUtils.YYYY_MM_DD);
        //根据申请ID查询房源
        SourceHouse sourceHouse = new SourceHouse();
        sourceHouse.setShApplyid(objId);
        List<SourceHouse> sourceHouses = sourceHouseMapper.selectByCondition(sourceHouse);
        String apFwid = null;
        /*if(sourceHouses!=null && sourceHouses.size()>0){
            sourceHouse = sourceHouses.get(0);
            apFwid = sourceHouse.getShFwid();
        }*/
        if(apSqlb.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))
                || apSqlb.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))) {//经济适用,廉租
            Apply apply = applyService.selectById(objId);
            userid = apply.getApSqrid();//申请人
            contractTemplateFillingDataPojo.setObjSqbh(apply.getApSqbh());
            ContractDetail contractDetail = new ContractDetail();
            contractDetail.setcId(CommonUtils.getUUID());//UUID
            contractDetail.setcFwid(apply.getApFwid());//房屋ID
            contractDetail.setcHtbh(cHtbh);//合同编号
            contractDetail.setcHtbarq(date);//合同备案日期
            contractDetail.setcBaczy(userInfo.getUsercode());//备案操作人
            contractDetail.setcLc(new Short("2"));//合同备案流程1签约 2备案
            contractDetail.setcZt(new Short("1"));//合同备案状态1正常 2续约 3作废
            contractDetail.setcZfzt(new Short("0"));//过期状态 0 未过期 1过期作废
            contractDetail.setcSqid(objId);//申请单ID
            contractDetail.setcHtlx(apSqlb);//合同类型 1经济适用房 2廉租房 3公租房（公租房中等收入偏下） 4公租房（公租房用人单位） 5短期租赁合同
            contractDetail.setCreateDate(date);
            contractDetail.setCreatePerson(userInfo.getUsercode());
            contractDetail.setUpdateDate(date);
            contractDetail.setUpdatePerson(userInfo.getUsercode());
            contractDetail.setcUrl(dirAndName);
            contractDetail.setcBeginTime(startDate);//合同开始时间
            contractDetail.setcEndTime(endDate);//合同到期时间
            contractDetail.setcType("1");//合同类型 1、新数据初审合同 2、新数据年审合同 3、老数据初始化合同 4、老数据年审合同
            contractDetail.setcDataInput(inputMapStr);//合同填充数据
            contractDetailMapper.insert(contractDetail);
            apply.setApLc(new Short("5"));//签约
            apply.setApZt(new Short("4"));
            applyService.update(apply);
        }  else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))) {//外来务工
            ApplyForForeign applyForForeign = applyForForeignService.selectById(objId);
            userid = applyForForeign.getAffSqrid();//申请人
            contractTemplateFillingDataPojo.setObjSqbh(applyForForeign.getAffSqbh());
            ContractDetail contractDetail = new ContractDetail();
            contractDetail.setcId(CommonUtils.getUUID());//UUID
            contractDetail.setcFwid(apFwid);//房屋ID
            contractDetail.setcHtbh(cHtbh);//合同编号
            contractDetail.setcHtbarq(date);//合同备案日期
            contractDetail.setcBaczy(userInfo.getUsercode());//备案操作人
            contractDetail.setcLc(new Short("2"));//合同备案流程1签约 2备案
            contractDetail.setcZt(new Short("1"));//合同备案状态1正常 2续约 3作废
            contractDetail.setcZfzt(new Short("0"));//过期状态 0 未过期 1过期作废
            contractDetail.setcSqid(objId);//申请单ID
            contractDetail.setcHtlx(apSqlb);//合同类型 1经济适用房 2廉租房 3公租房（公租房中等收入偏下） 4公租房（公租房用人单位） 5短期租赁合同
            contractDetail.setCreateDate(date);
            contractDetail.setCreatePerson(userInfo.getUsercode());
            contractDetail.setUpdateDate(date);
            contractDetail.setUpdatePerson(userInfo.getUsercode());
            contractDetail.setcUrl(dirAndName);
            contractDetail.setcBeginTime(startDate);//合同开始时间
            contractDetail.setcEndTime(endDate);//合同到期时间
            contractDetail.setcType("1");//合同类型 1、新数据初审合同 2、新数据年审合同 3、老数据初始化合同 4、老数据年审合同
            contractDetail.setcDataInput(inputMapStr);//合同填充数据
            contractDetailMapper.insert(contractDetail);
            applyForForeign.setAffLc(new Short("5"));//签约
            applyForForeign.setAffZt(new Short("4"));
            applyForForeignService.update(applyForForeign);
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("butie"))) {//补贴
            ApplyButie applyButie = applyButieService.selectById(objId);
            userid = applyButie.getAbSqrid();//申请人
            contractTemplateFillingDataPojo.setObjSqbh(applyButie.getAbSqbh());
            /*判断合同是否存在，不存在表示初审，
            * 存在表示年审、或老数据年审
             */
            ContractDetail contractDetail = null;
            if(StringUtils.isEmpty(contractTemplateFillingDataPojo.getcId())){
                contractDetail = new ContractDetail();
                contractDetail.setcId(CommonUtils.getUUID());//UUID
                contractDetail.setcType("1");//合同类型 1、新数据初审合同 2、新数据年审合同 3、老数据初始化合同 4、老数据年审合同
            }else{
                //获取上一次有效数据,标注过期
                ContractDetail contractDetail1Map = new ContractDetail();
                contractDetail1Map.setcSqid(objId);//申请ID
                contractDetail1Map.setcZfzt(new Short("0"));//有效
                List<ContractDetail> contractDetail1Maps = contractDetailMapper.selectByCondition(contractDetail1Map);
                for (int i=0;i<contractDetail1Maps.size();i++){
                    if(contractDetail1Maps.get(i)!=null
                            && !contractTemplateFillingDataPojo.getcId().equals(contractDetail1Maps.get(i).getcId())){
                            contractDetail1Maps.get(i).setcZfzt(new Short("1"));//过期状态 0 未过期 1过期作废
                            contractDetailMapper.update(contractDetail1Maps.get(i));
                    }
                }
                //查询当前合同数据
                contractDetail = contractDetailMapper.selectById(contractTemplateFillingDataPojo.getcId());
            }

            contractDetail.setcFwid(apFwid);//房屋ID
            contractDetail.setcHtbh(cHtbh);//合同编号
            contractDetail.setcHtbarq(date);//合同备案日期
            contractDetail.setcBaczy(userInfo.getUsercode());//备案操作人
            contractDetail.setcLc(new Short("2"));//合同备案流程1签约 2备案
            contractDetail.setcZt(new Short("1"));//合同备案状态1正常 2续约 3作废
            contractDetail.setcZfzt(new Short("0"));//过期状态 0 未过期 1过期作废
            contractDetail.setcSqid(objId);//申请单ID
            contractDetail.setcHtlx(apSqlb);//合同类型 1经济适用房 2廉租房 3公租房（公租房中等收入偏下） 4公租房（公租房用人单位） 5短期租赁合同
            contractDetail.setCreateDate(date);
            contractDetail.setCreatePerson(userInfo.getUsercode());
            contractDetail.setUpdateDate(date);
            contractDetail.setUpdatePerson(userInfo.getUsercode());
            contractDetail.setcUrl(dirAndName);
            contractDetail.setSubsidyNum(Short.valueOf(contractTemplateFillingDataPojo.getRentalSubsidiesNum()));//补贴人数
            contractDetail.setcBeginTime(startDate);//合同开始时间
            contractDetail.setcEndTime(endDate);//合同到期时间
            contractDetail.setcDataInput(inputMapStr);//合同填充数据
            if(StringUtils.isEmpty(contractTemplateFillingDataPojo.getcId())){
                contractDetailMapper.insert(contractDetail);
            }else{
                contractDetailMapper.update(contractDetail);
            }
            applyButie.setAbYhkh(contractTemplateFillingDataPojo.getBankAccount());//银行账号
            applyButie.setAbLc(new Short("5"));//签约
            applyButie.setAbZt(new Short("4"));
            applyButie.setAbKsrq(startDateStr);//补贴开始时间
            applyButie.setAbJsrq(endDateStr);//补贴结束时间
            applyButie.setAbBtje(contractTemplateFillingDataPojo.getRentalSubsidiesTP()==null?
                    new BigDecimal("0"):new BigDecimal(contractTemplateFillingDataPojo.getRentalSubsidiesTP()));//补贴金额
            applyButieService.update(applyButie);
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))) {//新就业
            ApplyForgraDuate applyForgraDuate = applyForgraDuateService.selectById(objId);
            userid = applyForgraDuate.getAfgSqrid();//申请人
            contractTemplateFillingDataPojo.setObjSqbh(applyForgraDuate.getAfgSqbh());
            ContractDetail contractDetail = new ContractDetail();
            contractDetail.setcId(CommonUtils.getUUID());//UUID
            contractDetail.setcFwid(apFwid);//房屋ID
            contractDetail.setcHtbh(cHtbh);//合同编号
            contractDetail.setcHtbarq(date);//合同备案日期
            contractDetail.setcBaczy(userInfo.getUsercode());//备案操作人
            contractDetail.setcLc(new Short("2"));//合同备案流程1签约 2备案
            contractDetail.setcZt(new Short("1"));//合同备案状态1正常 2续约 3作废
            contractDetail.setcZfzt(new Short("0"));//过期状态 0 未过期 1过期作废
            contractDetail.setcSqid(objId);//申请单ID
            contractDetail.setcHtlx(apSqlb);//合同类型 1经济适用房 2廉租房 3公租房（公租房中等收入偏下） 4公租房（公租房用人单位） 5短期租赁合同
            contractDetail.setCreateDate(date);
            contractDetail.setCreatePerson(userInfo.getUsercode());
            contractDetail.setUpdateDate(date);
            contractDetail.setUpdatePerson(userInfo.getUsercode());
            contractDetail.setcUrl(dirAndName);
            contractDetail.setcBeginTime(startDate);//合同开始时间
            contractDetail.setcEndTime(endDate);//合同到期时间
            contractDetail.setcType("1");//合同类型 1、新数据初审合同 2、新数据年审合同 3、老数据初始化合同 4、老数据年审合同
            contractDetail.setcDataInput(inputMapStr);//合同填充数据
            contractDetailMapper.insert(contractDetail);
            applyForgraDuate.setAfgLc(new Short("5"));//签约
            applyForgraDuate.setAfgZt(new Short("4"));
            applyForgraDuateService.update(applyForgraDuate);
        } else{
            return "不存在此申请类型的合同!";
        }
        ApplyChange applyChange = new ApplyChange();
        applyChange.setAcId(CommonUtils.getUUID());//UUID
        applyChange.setAcUserid(userInfo.getUserid());//userId
        applyChange.setAcApplyType(apSqlb);//申请类别
        applyChange.setAcSqbh(contractTemplateFillingDataPojo.getObjSqbh());//申请编号
        applyChange.setAcSqid(contractTemplateFillingDataPojo.getObjId());//申请ID
        applyChange.setAcTime(date);//时间
        applyChange.setAcType("6");//合同签订
        applyChangeMapper.insert(applyChange);
        //添加历史信息

        if(sourceHouses!=null && sourceHouses.size()>0){
            for (int i = 0; i < sourceHouses.size(); i++) {
                HisRent hisRent = new HisRent();
                hisRent.setHisId(CommonUtils.getUUID());//ID
                hisRent.setFwId(sourceHouses.get(i).getShFwid());//房屋ID
                hisRent.setUserid(userid);//申请人ID，家庭成员ID
                hisRent.setQysj(DatetimeUtils.date2string(date,DatetimeUtils.YYYY_MM_DD));//签约时间
                if(StringUtils.isNotEmpty(contractTemplateFillingDataPojo.getYearEnd()) && StringUtils.isNotEmpty(contractTemplateFillingDataPojo.getMonthEnd())
                        && StringUtils.isNotEmpty(contractTemplateFillingDataPojo.getDayEnd())){
                    hisRent.setJzsj(contractTemplateFillingDataPojo.getYearEnd()
                            + "-" + contractTemplateFillingDataPojo.getMonthEnd()
                            + "-" + contractTemplateFillingDataPojo.getDayEnd());//截止时间
                }
                hisRent.setSqid(objId);//申请时间
                hisRentMapper.insert(hisRent);
            }
        }



        return "备案成功!";
    }


    /**
     * 合直接下载
     * @param objId
     * @param apSqlb
     * @param response
     * @throws IOException
     */
    public void downLoad(String objId, String apSqlb, HttpServletResponse response,HttpServletRequest request) throws IOException {
        //根据objId获取生成合同信息
        ContractDetail contractDetail = new ContractDetail();
        contractDetail.setcSqid(objId);
        contractDetail.setcZfzt(new Short("0"));
        List<ContractDetail> contractDetails = contractDetailMapper.selectByCondition(contractDetail);
        if(contractDetails.size()<1){
            throw new RuntimeException("未找到合同信息");
        }
        contractDetail = contractDetails.get(0);
        if(StringUtils.isEmpty(contractDetail.getcUrl())){//看可以进行备案状态进行判断，此处用url合同路径判断
            //获取下载信息并下载
            String cDataInput = contractDetail.getcDataInput();//合同填充数据
            doCDataInput(objId,apSqlb,contractDetail,cDataInput,request,response);
        }else{
            FileUtils.buildResponseEntity(response, new File(contractDetail.getcUrl()), "application/msword");
            /*Map<String,Object> mapOut = new HashMap<String, Object>();
            mapOut.put("flag",true);
            mapOut.put("msg",contractDetail.getcUrl().replace(request.getSession().getServletContext().getRealPath("/"),""));
            return mapOut;*/
        }
    }

    private void doCDataInput(String objId, String apSqlb,ContractDetail contractDetail,String cDataInput,
                              HttpServletRequest request,HttpServletResponse response){
        //获取当前用户
        HttpSession session = request.getSession();
        UserInfo userinfo = (UserInfo)session.getAttribute("user");//获取用户信息
        String ctCode = "";//合同模板
        //获取合同编码
        String contractNo = "";//合同编号
        String htQue = "";//合同序号
        //根据申请类型判断合同类型
        String userid = "";
        //申请编号
        String  apSqbh = null;
        if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))) {//公租房
            ctCode = "002";//合同编号
            //公租房申请信息
            Apply apply = applyService.selectById(objId);
            userid = apply.getApSqrid();//申请人
            apSqbh = apply.getApSqbh();//申请编号
            //填充合同编号、流水、中间时间部分、前缀信息
            ParmItem parmItem = contractTemplateService.getParmItem("04",apply.getApSsq());//获取申请单区域信息
            String regionName = parmItem.getPiItemname();//获取区域名（###区）
            String regionCodeStr = contractTemplateService.parseRegionCode(regionName);//获取转换后的区域(鼓楼、云龙、泉山[##])，获取转换后的区域(鼓楼、云龙、泉山)
            parmItem.getPiItemcode();//区域编码

            SerialNumLine serialNumLine = serialNumService.getSerialNumInfo("XYXH");//获取合同序号(流水号)
            String prefix = PropertiesUtil.getContractProperties(regionCodeStr);//合同编号前缀
            if(StringUtils.isEmpty(prefix)){
                throw new RuntimeException("获取合同编号前缀失败!");
            }
            contractNo = prefix + serialNumLine.getCode();//合同编码
            htQue = serialNumLine.getPosition();//合同序号[流水号]
            String SnDateStructure = serialNumLine.getSnDateStructure();//合同中间时间部分

        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))) {//外来务工
            ctCode = "003";//合同编号
            ApplyForForeign applyForForeign = applyForForeignService.selectById(objId);//外来务工
            userid = applyForForeign.getAffSqrid();//申请人
            apSqbh = applyForForeign.getAffSqbh();//申请编号
            //填充合同编号、流水、中间时间部分、前缀信息
            ParmItem parmItem = contractTemplateService.getParmItem("04",applyForForeign.getAffSsq());//获取申请单区域信息
            String regionName = parmItem.getPiItemname();//获取区域名（###区）
            String regionCodeStr = contractTemplateService.parseRegionCode(regionName);//获取转换后的区域(鼓楼、云龙、泉山[##])，获取转换后的区域(鼓楼、云龙、泉山)
            parmItem.getPiItemcode();//区域编码

            SerialNumLine serialNumLine = serialNumService.getSerialNumInfo("XYXH");//获取合同序号(流水号)
            String prefix = PropertiesUtil.getContractProperties(regionCodeStr);//合同编号前缀
            if(StringUtils.isEmpty(prefix)){
                throw new RuntimeException("获取合同编号前缀失败!");
            }
            contractNo = prefix + serialNumLine.getCode();//合同编码
            htQue = serialNumLine.getPosition();//合同序号[流水号]
            String SnDateStructure = serialNumLine.getSnDateStructure();//合同中间时间部分

        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("butie"))) {//补贴
            ctCode = "001";//合同编号
            ApplyButie applyButie = applyButieService.selectById(objId);//补贴信息
            userid = applyButie.getAbSqrid();//申请人
            apSqbh = applyButie.getAbSqbh();//申请编号
            //填充合同编号、流水、中间时间部分、前缀信息
            ParmItem parmItem = contractTemplateService.getParmItem("04",applyButie.getAbSsq());//获取申请单区域信息
            String regionName = parmItem.getPiItemname();//获取区域名（###区）
            String regionCodeStr = contractTemplateService.parseRegionCode(regionName);//获取转换后的区域(鼓楼、云龙、泉山[##])，获取转换后的区域(鼓楼、云龙、泉山)
            parmItem.getPiItemcode();//区域编码

            SerialNumLine serialNumLine = serialNumService.getSerialNumInfo("XYXH");//获取合同序号(流水号)
            String prefix = PropertiesUtil.getContractProperties(regionCodeStr);//合同编号前缀
            if(StringUtils.isEmpty(prefix)){
                throw new RuntimeException("获取合同编号前缀失败!");
            }
            contractNo = prefix + serialNumLine.getCode();//合同编码
            htQue = serialNumLine.getPosition();//合同序号[流水号]
            String SnDateStructure = serialNumLine.getSnDateStructure();//合同中间时间部分

        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))) {//新就业
            ctCode = "003";//合同编号
            ApplyForgraDuate applyForgraDuate = applyForgraDuateService.selectById(objId);//新就业
            userid = applyForgraDuate.getAfgSqrid();//申请人
            apSqbh = applyForgraDuate.getAfgSqbh();//申请编号
            //填充合同编号、流水、中间时间部分、前缀信息
            ParmItem parmItem = contractTemplateService.getParmItem("04",applyForgraDuate.getAfgSsq());//获取申请单区域信息
            String regionName = parmItem.getPiItemname();//获取区域名（###区）
            String regionCodeStr = contractTemplateService.parseRegionCode(regionName);//获取转换后的区域(鼓楼、云龙、泉山[##])，获取转换后的区域(鼓楼、云龙、泉山)
            parmItem.getPiItemcode();//区域编码

            SerialNumLine serialNumLine = serialNumService.getSerialNumInfo("XYXH");//获取合同序号(流水号)
            String prefix = PropertiesUtil.getContractProperties(regionCodeStr);//合同编号前缀
            if(StringUtils.isEmpty(prefix)){
                throw new RuntimeException("获取合同编号前缀失败!");
            }
            contractNo = prefix + serialNumLine.getCode();//合同编码
            htQue = serialNumLine.getPosition();//合同序号[流水号]
            String SnDateStructure = serialNumLine.getSnDateStructure();//合同中间时间部分

        } else {
            throw new RuntimeException("没有当前申请类型!");
        }

        //获取合同模板
        ContractTemplate contractTemplate = new ContractTemplate();
        contractTemplate.setCtCode(ctCode);
        List<ContractTemplate> contractTemplates = contractTemplateService.getContractTemplates(contractTemplate);
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
        Date date = new Date();
        //获取合同结束时间
        Date cEndTime = contractDetail.getcEndTime();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(cEndTime);
//            calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+1);
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+1);
        Date newCBeginTime=calendar.getTime();//开始时间
        String newYearStart = DatetimeUtils.date2string(newCBeginTime,DatetimeUtils.YYYY);//新Start 年
        String newMonthStart = DatetimeUtils.date2string(newCBeginTime,DatetimeUtils.MM);//新Start 月
        String newDayStart = DatetimeUtils.date2string(newCBeginTime,DatetimeUtils.DD);//新Start 天
        calendar.setTime(cEndTime);
        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+1);
        Date newCEndTime=calendar.getTime();//结束时间
        String newYearEnd = DatetimeUtils.date2string(newCEndTime,DatetimeUtils.YYYY);//新End 年
        String newMonthEnd = DatetimeUtils.date2string(newCEndTime,DatetimeUtils.MM);//新End 月
        String newDayEnd = DatetimeUtils.date2string(newCEndTime,DatetimeUtils.DD);//新End 天

        String startDateStr = newYearStart + "-" + newMonthStart + "-" + newDayStart;//有效开始时间
        Date startDate = DatetimeUtils.string2date(startDateStr,DatetimeUtils.YYYY_MM_DD);
        String endDateStr = newYearEnd + "-" + newMonthEnd + "-" + newDayEnd;//有效结束时间
        Date endDate = DatetimeUtils.string2date(endDateStr,DatetimeUtils.YYYY_MM_DD);
        Map cDataInputMap = (Map)JSONObject.fromObject(cDataInput);
        cDataInputMap.put("yearSta",newYearStart);//起始年
        cDataInputMap.put("monthSta",newMonthStart);//起始月
        cDataInputMap.put("daySta",newDayStart);//起始日
        cDataInputMap.put("yearEnd",newYearEnd);//结束年
        cDataInputMap.put("monthEnd",newMonthEnd);//结束月
        cDataInputMap.put("dayEnd",newDayEnd);//结束日
        try {
            dynamicallyGeneratedWordService.genWordFile(tempLetDir, templetName, targetWordDir, targetName,cDataInputMap);

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
            FileUtils.buildResponseEntity(response, new File(pdfPath), "application/pdf");


            //备案
            //根据申请ID查询房源
            contractDetail.setcHtbh(contractNo);//合同编号
            contractDetail.setcHtbarq(date);//合同备案日期
            contractDetail.setcBaczy(userinfo.getUsercode());//备案操作人
            contractDetail.setcLc(new Short("2"));//合同备案流程1签约 2备案
            contractDetail.setcZt(new Short("1"));//合同备案状态1正常 2续约 3作废
            contractDetail.setcZfzt(new Short("0"));//过期状态 0 未过期 1过期作废
            contractDetail.setcSqid(objId);//申请单ID
            contractDetail.setcHtlx(apSqlb);//合同类型 1经济适用房 2廉租房 3公租房（公租房中等收入偏下） 4公租房（公租房用人单位） 5短期租赁合同
            contractDetail.setCreateDate(date);
            contractDetail.setCreatePerson(userinfo.getUsercode());
            contractDetail.setUpdateDate(date);
            contractDetail.setUpdatePerson(userinfo.getUsercode());
            contractDetail.setcUrl(dirAndName);
            contractDetail.setcBeginTime(startDate);//合同开始时间
            contractDetail.setcEndTime(endDate);//合同到期时间
//            contractDetail.setcType("1");//合同类型 1、新数据初审合同 2、新数据年审合同 3、老数据初始化合同 4、老数据年审合同
            contractDetail.setcDataInput(cDataInput);//合同填充数据
            contractDetailMapper.update(contractDetail);

            ApplyChange applyChange = new ApplyChange();
            applyChange.setAcId(CommonUtils.getUUID());//UUID
            applyChange.setAcUserid(userinfo.getUserid());//userId
            applyChange.setAcApplyType(apSqlb);//申请类别
            applyChange.setAcSqbh(apSqbh);//申请编号
            applyChange.setAcSqid(objId);//申请ID
            applyChange.setAcTime(date);//时间
            applyChange.setAcType("6");//合同签订
            applyChangeMapper.insert(applyChange);
            //添加历史信息
            HisRent hisRent = new HisRent();
            hisRent.setHisId(CommonUtils.getUUID());//ID
            hisRent.setUserid(userid);//申请人ID，家庭成员ID
            hisRent.setQysj(DatetimeUtils.date2string(date,DatetimeUtils.YYYY_MM_DD));//签约时间
            if(StringUtils.isNotEmpty(newYearEnd) && StringUtils.isNotEmpty(newMonthEnd)
                    && StringUtils.isNotEmpty(newDayEnd)){
                hisRent.setJzsj(endDateStr);//截止时间
            }
            hisRent.setSqid(objId);//申请时间
            hisRentMapper.insert(hisRent);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            throw new RuntimeException("合同生成失败!");
        }
    }

    private ContractTemplateFillingDataPojo parseContractTemplateFillingDataPojo(String objId,String apSqlb,Map inputMap) {
        ContractTemplateFillingDataPojo contractTemplateFillingDataPojo = contractTemplateService
                    .getContractTemplateFillingDataPojo(apSqlb,objId,null,null);
        //合同填写信息录入
        contractTemplateFillingDataPojo.setYearSta((String)inputMap.get("yearSta"));
        contractTemplateFillingDataPojo.setMonthSta((String)inputMap.get("monthSta"));
        contractTemplateFillingDataPojo.setDaySta((String)inputMap.get("daySta"));
        contractTemplateFillingDataPojo.setYearEnd((String)inputMap.get("yearEnd"));
        contractTemplateFillingDataPojo.setMonthEnd((String)inputMap.get("monthEnd"));
        contractTemplateFillingDataPojo.setDayEnd((String)inputMap.get("dayEnd"));
        return contractTemplateFillingDataPojo;
    }


    public String findMsgToSend(String objId,String apSqlb){
        Approve approve = approveMapper.findByApplyId(objId);
        if(apSqlb.equals(PropertiesUtil.getApplyTypeProperties("jingsf")) || apSqlb.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))) {//经济适用,廉租
        }  else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))) {//外来务工
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("butie"))) {//补贴
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))) {//新就业
        } else{
            return "不存在此申请类型的合同!";
        }
        //获取用户信息
        ApplyUserinfo applyUserinfo = applyUserinfoService.selectById(approve.getApluserid());
        toMsg("合同提醒：你的申请单号：" + approve.getAplbh() + "已经签约!",applyUserinfo.getUserid(),applyUserinfo.getLinktel());
        return "发送成功!";
    }

    /**
     * 发送短信
     * @param msg
     * @param userId
     * @param userPhone
     */
    private void toMsg(String msg,String userId, String userPhone){
        /*发送短信和插入通知*/
        messageAndFormService.addMessageAndFormService(msg ,userId,userPhone);
    }

}
