package com.sys.controller;

import com.sys.common.*;
import com.sys.pojo.*;
import com.sys.pojo.apply.ContractTemplateFillingDataPojo;
import com.sys.pojo.contract.ContractTemplate;
import com.sys.service.ContractTemplateService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("contractTemplate")
public class ContractTemplateController {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ContractTemplateService contractTemplateService;

    /**
     * 合同信息展示页面
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("{page}")
    public String toLowPaulTrap(@PathVariable String page, HttpServletRequest request) {
        String apSqlb = request.getParameter("apSqlb");//申请类型
        String objId = request.getParameter("objId");//申请id
        String cId = request.getParameter("cId");//合同ID集合（逗号隔开）
        String fmIds = request.getParameter("fmIds");//房屋ID集合（逗号隔开）
        ContractTemplateFillingDataPojo contractTemplateFillingDataPojo = contractTemplateService
                .getContractTemplateFillingDataPojo(apSqlb,objId,fmIds,cId);
        contractTemplateFillingDataPojo.setcId(cId);//合同ID
        request.setAttribute("contractTemplateFillingDataPojo",contractTemplateFillingDataPojo);
        return "SystemMa/contractTemplate/" + page;
    }

    /**
     * 根据申请id获取申请分配的房屋信息
     * @param request
     * @return
     */
    @RequestMapping("getFactMappings")
    @ResponseBody
    public Object getFactMappingsBySqId(HttpServletRequest request){
        String apSqlb = request.getParameter("apSqlb");//申请类型
        String objId = request.getParameter("objId");//申请id
        String cType = request.getParameter("cType");//合同类型 1、新数据初审合同 2、新数据年审合同 3、老数据初始化合同 4、老数据年审合同
        try {
            Map<String,Object> map = (Map<String,Object>)contractTemplateService.getFactMappingsBySqId(objId,apSqlb,cType);
            map.put("objId",objId);
            map.put("apSqlb",apSqlb);
            return map;
        }  catch (Exception e) {
            e.printStackTrace();
            return CommonUtils.getMsgForRet(false,"获取房屋信息失败!");
        }

    }

    /**
     * 备案管理页面合同跳转
     *
     * @param request
     * @return
     */
    @RequestMapping("toPath")
    @ResponseBody
    public Object toPath(HttpServletRequest request) {
        //获取当前行目类型
        String apSqlb = request.getParameter("apSqlb");//申请类型
        String objId = request.getParameter("objId");//申请类型
        String[] fmIdArr = request.getParameterValues("fmIds[]");//房屋ID集合
        String fmIds = StringUtils.join(fmIdArr,",");
        Map<String, Object> map = new HashMap<String, Object>();
        String path = null;
        boolean flag = false;
        if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("jingsf"))) {//经济适用【不进行合同下载】
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("butie"))) {//补贴
            String cId = request.getParameter("cId");//合同ID集合（逗号隔开）
            path = "contractTemplate/ApplyButieTemplate?apSqlb=" + apSqlb + "&objId=" + objId + "&cId=" + cId;
            flag = true;
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("gongzf"))) {//公租房
            path = "contractTemplate/ApplyTemplate?apSqlb=" + apSqlb + "&objId=" + objId + "&fmIds=" +fmIds;
            flag = true;
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))) {//外来务工
            path = "contractTemplate/ApplyForForeginTemplate?apSqlb=" + apSqlb + "&objId=" + objId + "&fmIds=" +fmIds;
            flag = true;
        } else if (apSqlb.equals(PropertiesUtil.getApplyTypeProperties("xinjy"))) {//新就业
            path = "contractTemplate/ApplyForgraDuateTemplate?apSqlb=" + apSqlb + "&objId=" + objId + "&fmIds=" +fmIds;
            flag = true;
        }
        map.put("path", path);
        map.put("flag", flag);
        return map;
    }

    /**
     * 获取合同填充文件
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getContractTemplateFile")
    @ResponseBody
    public Object getContractTemplateFile(ContractTemplateFillingDataPojo contractTemplateFillingDataPojo,
                                          HttpServletRequest request, HttpServletResponse response) {
        try {
            return contractTemplateService.updateContractTemplateFile(contractTemplateFillingDataPojo,request, response);
        } catch (Exception e) {
            Map<String,Object> map = new HashMap<String,Object>();
            e.printStackTrace();
            logger.error(e.getMessage());
            map.put("flag",false);
            map.put("msg",e.getMessage());
            return map;
        }
    }

    /**
     * 金额转换成中文
     * @param request
     * @return
     */
    @RequestMapping("getMoneyZH")
    @ResponseBody
    public Object getMoneyZH(HttpServletRequest request){
        String money = request.getParameter("rentalSubsidiesTP");//补贴金额
        //转换 = 》 补贴总价中文
        try {
            String rentalSubsidiesTPZH = CnNumberUtils.toUppercase(
                    Double.valueOf(money));
            return CommonUtils.getMsgForRet(true,rentalSubsidiesTPZH);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error(e.toString());
            return CommonUtils.getMsgForRet(false,"输入信息有误!不可进行转换");
        }
    }


    /**
     * 查询模板
     *
     * @param request
     * @return
     */
    @RequestMapping("selectContractTemplate")
    @ResponseBody
    public Object selectContractTemplate(HttpServletRequest request) {
        ContractTemplate contractTemplate = new ContractTemplate();
        contractTemplate.setDeleteFlag("F");
        contractTemplate.setCtName((String) request.getParameter("ctName"));//合同模板名称
        List<ContractTemplate> contractTemplates = contractTemplateService.getContractTemplates(contractTemplate);
        return contractTemplates;
    }

    /**
     * 模板新增（暂时不用）
     *
     * @param request
     * @return
     */
    @RequestMapping("insertContractTemplate")
    @ResponseBody
    public Object insertContractTemplate(ContractTemplate contractTemplate, HttpServletRequest request) {
        return null;
    }

    /**
     * 获取PDF（暂时不用）
     *
     * @param request
     * @param response
     */
    @RequestMapping("getPdf")
    @ResponseBody
    public void getPdf(HttpServletRequest request, HttpServletResponse response) {
        String ctCode = request.getParameter("ctCode");
        ContractTemplate contractTemplate = new ContractTemplate();
        contractTemplate.setCtCode(ctCode);
        List<ContractTemplate> contractTemplates = contractTemplateService.getContractTemplates(contractTemplate);
        contractTemplate = contractTemplates.get(0);
        //格式信息
        String ctInputInfo = contractTemplate.getCtInputInfo();
        //文本路径信息
        String ctUrl = contractTemplate.getCtUrl();
        //获取文件名、路径
        int index = ctUrl.lastIndexOf("/");
        if (index < 0) {
            try {
                response.getWriter().write("文件路径解析失败!");
                return;
            } catch (IOException e) {
                logger.error(e.getMessage());
                return;
            }
        }
        //目标文件路径
        String filePath = request.getSession().getServletContext().getRealPath("/");
        String tempLetDir = filePath + ctUrl.substring(0, index);//模板路径
        String templetName = ctUrl.substring(index + 1);//模板文件名
        String targetDir = filePath + "tempContract";//模板文件生成信息临时路径

        String wordPath = tempLetDir + File.separator
                + templetName.replace("xml", "doc");
        String pdfPath = wordPath.replace("doc", "pdf");
        contractTemplateService.GetPdf(wordPath, pdfPath,
                templetName.replace("xml", "pdf"), response);
        return;
    }


}
