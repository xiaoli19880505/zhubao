package com.sys.service.report;

import com.sys.common.*;
import com.sys.mapper.report.LowSecurityReportMapper;
import com.sys.pojo.UserInfo;
import com.sys.pojo.report.ReportPojo;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Service
public class LowSecurityReportService {

    private Logger logger = Logger.getLogger(this.getClass());

    private String excelPath = "report" + File.separator + "reportTemplate";
    private String tempExcelPath = "report" + File.separator + "tempReport";
    //房管局留存报表
    private String getRetain = "房管局留存报表.xlsx";
    private String getRetainText1 = "徐州市市区YYYY年MM月份低保家庭租赁补贴发放明细表";
    private String getRetainText2 = "徐州市市区YYYY年MM月份低收入家庭租赁补贴发放明细表";
    private String getRetainText3 = "YYYY.MM新增补贴人员名单";
    private String getRetainText4 = "YYYY.MM取消补贴人员名单";
    private List<String> getRetainList3 = new ArrayList<String>();
    //局发放情况汇总封面
    private String getCover = "局发放情况汇总封面.rtf";
    //银行打款用报表
    private String getPay = "银行打款用报表.xlsx";
    //住保补贴公示文件
    private String getPublicity = "住保补贴公示文件.xlsx";
    //住保中心留存报表
    private String getLiveInsuranceCenterRetain = "住保中心留存报表.xlsx";
    private List<String> textList = new ArrayList<String>();

    {
        //列名称
        getRetainList3.add("序号");
        getRetainList3.add("姓名");
        getRetainList3.add("身份证号码");
        getRetainList3.add("申请人居住所在地");
        getRetainList3.add("房屋现状");
        getRetainList3.add("家庭人口");
        getRetainList3.add("补贴金额");
        getRetainList3.add("联系方式");
        getRetainList3.add("交行卡号");
        getRetainList3.add("补贴性质");
        //封面内容
        textList.add("YYYY年MM月徐州市市区廉租住房租赁补贴发放情况明细表");
        textList.add("低收入住房困难家庭:");
        textList.add("householdsNum 户,计 personNum 人");
        textList.add("发放金额:");
        textList.add("money 元");
        textList.add("低保困难家庭:");
        textList.add("householdsNum 户,计 personNum 人");
        textList.add("发放金额:");
        textList.add("money 元");
        textList.add("发放金额:");
        textList.add("moneyZW( money 元)");
        textList.add("中心领导签字:");
        textList.add("财务科负责人签字");
        textList.add("租售科负责人签字");
        textList.add("制表人：userName");
        textList.add("date");
    }




    @Autowired
    private LowSecurityReportMapper lowSecurityReportMapper;

    /**
     * 房管局留存报表
     * @param yearMonth
     */
    public Object getRetain(String yearMonth,HttpServletResponse response){
        //查询补贴信息
        //获取低保
        ReportPojo reportPojo1 = new ReportPojo();
        reportPojo1.setEffectiveYearAndMonth(yearMonth);
        reportPojo1.setAbLc(Short.valueOf("5"));
        reportPojo1.setAbZt(Short.valueOf("4"));
        List<ReportPojo> reportPojos = lowSecurityReportMapper.selectByCondition(reportPojo1);
        //获取低收入金额
        String low_income_money = PropertiesUtil.getApplyTypeProperties("low_income_money");
        ReportPojo reportPojo2 = new ReportPojo();
        reportPojo2.setEffectiveYearAndMonth(yearMonth);
        reportPojo2.setAbLc(Short.valueOf("5"));
        reportPojo2.setAbZt(Short.valueOf("4"));
        reportPojo2.setAbBtje(new BigDecimal(low_income_money));
        List<ReportPojo> lowReportPojos = lowSecurityReportMapper.selectByCondition(reportPojo2);
        //获取新增、退出名单
        //新增
        ReportPojo reportPojo3 = new ReportPojo();
        reportPojo3.setYearAndMonth(yearMonth);
        reportPojo3.setAbLc(Short.valueOf("5"));
        reportPojo3.setAbZt(Short.valueOf("4"));
        List<ReportPojo> insertReportPojos = lowSecurityReportMapper.selectByCondition(reportPojo3);
        //退出
        ReportPojo reportPojo4 = new ReportPojo();
        reportPojo4.setYearAndMonth(yearMonth);
        reportPojo4.setAbLc(Short.valueOf("5"));
        reportPojo4.setAbZt(Short.valueOf("5"));
        List<ReportPojo> dropOutReportPojos = lowSecurityReportMapper.selectByCondition(reportPojo4);

        OutputStream out = null;
        try {
            //创建文件 文件路径 + YYYYMM文件名
            String finalXlsxPath = FileUtils.getRealPath()
                    + tempExcelPath + File.separator + "getRetain"
                    + File.separator + yearMonth + getRetain;
            //删除文件
            logger.info(FileUtils.deleteFile(finalXlsxPath));

            File finalXlsxOutFile = FileUtils.createFile(finalXlsxPath);//创建文件
            Workbook workBook = new XSSFWorkbook();  //2007+
            // sheet 对应一个工作页
            out =  new FileOutputStream(finalXlsxOutFile);
            workBook.write(out);
            String yyyy = yearMonth.substring(0,4);
            String mm = yearMonth.substring(4);

            //设置sheet1
            Sheet sheet1 = workBook.createSheet("低保");//创建sheet名称
            String getRetainText1 = this.getRetainText1
                    .replace("YYYY",yyyy)
                    .replace("MM",mm);
            getRetainSheet1(sheet1,workBook,getRetainText1,
                    reportPojos);

            //设置sheet2
            Sheet sheet2 = workBook.createSheet("低收入");//创建sheet名称
            String getRetainText2 = this.getRetainText2
                    .replace("YYYY",yyyy)
                    .replace("MM",mm);
            getRetainSheet2(sheet2,workBook,getRetainText2,
                    lowReportPojos);

            //设置sheet3
            Sheet sheet3 = workBook.createSheet("新增退出名单");//创建sheet名称
            String getRetainText3 = this.getRetainText3
                    .replace("YYYY",yyyy)
                    .replace("MM",mm);
            String getRetainText4 = this.getRetainText4
                    .replace("YYYY",yyyy)
                    .replace("MM",mm);
            getRetainSheet3(sheet3,workBook,getRetainText3,getRetainText4,insertReportPojos,
                    dropOutReportPojos);

            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            //下载功能
            FileUtils.buildResponseEntity(response,finalXlsxOutFile,"application/x-xls");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("写入Excel信息异常!" + e.toString());
            throw new RuntimeException("写入Excel信息异常!");
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                logger.error("关闭读写流异常!" + e.toString());
                throw new RuntimeException("关闭读写流异常!");
            }
        }
        return "数据导出成功";
    }

    /**
     * 局发放情况汇总封面
     * @param yearMonth
     */
    public Object getCover(String yearMonth){

        return null;
    }

    /**
     * 银行补贴明细报表
     * @param yearMonth
     */
    public Object getPay(String yearMonth,HttpServletResponse response){
        //查询补贴信息
        //获取低保
        ReportPojo reportPojo1 = new ReportPojo();
        reportPojo1.setEffectiveYearAndMonth(yearMonth);
        reportPojo1.setAbLc(Short.valueOf("5"));
        reportPojo1.setAbZt(Short.valueOf("4"));
        List<ReportPojo> reportPojos = lowSecurityReportMapper.selectByCondition(reportPojo1);
        //获取低收入金额
        String low_income_money = PropertiesUtil.getApplyTypeProperties("low_income_money");
        ReportPojo reportPojo2 = new ReportPojo();
        reportPojo2.setEffectiveYearAndMonth(yearMonth);
        reportPojo2.setAbLc(Short.valueOf("5"));
        reportPojo2.setAbZt(Short.valueOf("4"));
        reportPojo2.setAbBtje(new BigDecimal(low_income_money));
        List<ReportPojo> lowReportPojos = lowSecurityReportMapper.selectByCondition(reportPojo2);

        //低保、低收入汇总
        reportPojos.addAll(lowReportPojos);

        OutputStream out = null;
        try {
            //创建文件 文件路径 + YYYYMM文件名
            String finalXlsxPath = FileUtils.getRealPath()
                    + tempExcelPath + File.separator + "getPay"
                    + File.separator + yearMonth + getPay;
            //删除文件
            logger.info(FileUtils.deleteFile(finalXlsxPath));

            File finalXlsxOutFile = FileUtils.createFile(finalXlsxPath);//创建文件
            Workbook workBook = new XSSFWorkbook();  //2007+
            // sheet 对应一个工作页
            out =  new FileOutputStream(finalXlsxOutFile);
            workBook.write(out);
            /*String yyyy = yearMonth.substring(0,4);
            String mm = yearMonth.substring(4);*/

            //设置sheet1
            Sheet sheet = workBook.createSheet("sheet1");//创建sheet名称
            /*String getRetainText1 = this.getRetainText1
                    .replace("YYYY",yyyy)
                    .replace("MM",mm);*/
            getPaySheet1(sheet,workBook,reportPojos);

            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);

            //下载功能
            FileUtils.buildResponseEntity(response,finalXlsxOutFile,"application/x-xls");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("写入Excel信息异常!" + e.toString());
            throw new RuntimeException("写入Excel信息异常!");
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                logger.error("关闭读写流异常!" + e.toString());
                throw new RuntimeException("关闭读写流异常!");
            }
        }
        return "数据导出成功";
    }

    /**
     * 公示补贴名单
     * @param yearMonth
     */
    public Object getPublicity(String yearMonth,HttpServletResponse response){
        //查询补贴信息
        //获取低保
        ReportPojo reportPojo1 = new ReportPojo();
        reportPojo1.setEffectiveYearAndMonth(yearMonth);
        reportPojo1.setAbLc(Short.valueOf("5"));
        reportPojo1.setAbZt(Short.valueOf("4"));
        List<ReportPojo> reportPojos = lowSecurityReportMapper.selectByCondition(reportPojo1);
        //获取低收入金额
        String low_income_money = PropertiesUtil.getApplyTypeProperties("low_income_money");
        ReportPojo reportPojo2 = new ReportPojo();
        reportPojo2.setEffectiveYearAndMonth(yearMonth);
        reportPojo2.setAbLc(Short.valueOf("5"));
        reportPojo2.setAbZt(Short.valueOf("4"));
        reportPojo2.setAbBtje(new BigDecimal(low_income_money));
        List<ReportPojo> lowReportPojos = lowSecurityReportMapper.selectByCondition(reportPojo2);

        //低保、低收入汇总
        reportPojos.addAll(lowReportPojos);

        OutputStream out = null;
        try {
            //创建文件 文件路径 + YYYYMM文件名
            String finalXlsxPath = FileUtils.getRealPath()
                    + tempExcelPath + File.separator + "getPublicity"
                    + File.separator + yearMonth + getPublicity;
            //删除文件
            logger.info(FileUtils.deleteFile(finalXlsxPath));

            File finalXlsxOutFile = FileUtils.createFile(finalXlsxPath);//创建文件
            Workbook workBook = new XSSFWorkbook();  //2007+
            // sheet 对应一个工作页
            out =  new FileOutputStream(finalXlsxOutFile);
            workBook.write(out);
            /*String yyyy = yearMonth.substring(0,4);
            String mm = yearMonth.substring(4);*/

            //设置sheet1
            Sheet sheet = workBook.createSheet("sheet1");//创建sheet名称
            /*String getRetainText1 = this.getRetainText1
                    .replace("YYYY",yyyy)
                    .replace("MM",mm);*/
            getPublicitySheet1(sheet,workBook,reportPojos);

            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);

            //下载功能
            FileUtils.buildResponseEntity(response,finalXlsxOutFile,"application/x-xls");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("写入Excel信息异常!" + e.toString());
            throw new RuntimeException("写入Excel信息异常!");
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                logger.error("关闭读写流异常!" + e.toString());
                throw new RuntimeException("关闭读写流异常!");
            }
        }
        return "数据导出成功";
    }

    /**
     * 住保中心补贴清册报表
     * @param yearMonth
     */
    public Object getLiveInsuranceCenterRetain(String yearMonth, UserInfo userInfo,
                                               HttpServletResponse response){
        Date date = new Date();
        //查询补贴信息
        //获取低保
        ReportPojo reportPojo1 = new ReportPojo();
        reportPojo1.setEffectiveYearAndMonth(yearMonth);
        reportPojo1.setAbLc(Short.valueOf("5"));
        reportPojo1.setAbZt(Short.valueOf("4"));
        List<ReportPojo> reportPojos = lowSecurityReportMapper.selectByCondition(reportPojo1);
        List<ReportPojo> sumReportPojos = lowSecurityReportMapper.selectForSum(reportPojo1);
        Integer sumReportHouseholdsNum = 0;//获取户数
        Integer sumReportSubsidyNum = 0;//获取人数(补贴)
        BigDecimal sumReportAbBtje = new BigDecimal(0);//获取金额数
        if(sumReportPojos!=null && sumReportPojos.size()>0 && sumReportPojos.get(0)!=null){
            sumReportHouseholdsNum = sumReportPojos.get(0).getHouseholdsNum();
            sumReportSubsidyNum = sumReportPojos.get(0).getSubsidyNum();
            sumReportAbBtje = sumReportPojos.get(0).getAbBtje();
        }

        //获取低收入金额
        String low_income_money = PropertiesUtil.getApplyTypeProperties("low_income_money");
        ReportPojo reportPojo2 = new ReportPojo();
        reportPojo2.setEffectiveYearAndMonth(yearMonth);
        reportPojo2.setAbLc(Short.valueOf("5"));
        reportPojo2.setAbZt(Short.valueOf("4"));
        reportPojo2.setAbBtje(new BigDecimal(low_income_money));
        List<ReportPojo> lowReportPojos = lowSecurityReportMapper.selectByCondition(reportPojo2);
        List<ReportPojo> sumLowReportPojos = lowSecurityReportMapper.selectForSum(reportPojo2);
        Integer sumLowReportHouseholdsNum = 0;//获取户数
        Integer sumLowReportSubsidyNum = 0;//获取人数(补贴)
        BigDecimal sumLowReportAbBtje = new BigDecimal(0);//获取金额数
        if(sumLowReportPojos!=null && sumLowReportPojos.size()>0 && sumLowReportPojos.get(0)!=null){
            sumLowReportHouseholdsNum = sumLowReportPojos.get(0).getHouseholdsNum();
            sumLowReportSubsidyNum = sumLowReportPojos.get(0).getSubsidyNum();
            sumLowReportAbBtje = sumLowReportPojos.get(0).getAbBtje();
        }

        OutputStream out = null;
        try {
            //创建文件 文件路径 + YYYYMM文件名
            String finalXlsxPath = FileUtils.getRealPath()
                    + tempExcelPath + File.separator + "getLiveInsuranceCenterRetain"
                    + File.separator + yearMonth + getLiveInsuranceCenterRetain;
            //删除文件
            logger.info(FileUtils.deleteFile(finalXlsxPath));

            File finalXlsxOutFile = FileUtils.createFile(finalXlsxPath);//创建文件
            Workbook workBook = new XSSFWorkbook();  //2007+
            // sheet 对应一个工作页
            out =  new FileOutputStream(finalXlsxOutFile);
            workBook.write(out);
            String yyyy = yearMonth.substring(0,4);
            String mm = yearMonth.substring(4);

            //设置sheet1
            Sheet sheet1 = workBook.createSheet("低保");//创建sheet名称
            String getRetainText1 = this.getRetainText1
                    .replace("YYYY",yyyy)
                    .replace("MM",mm);
            getLiveInsuranceCenterRetainSheet1(sheet1,workBook,getRetainText1,
                    reportPojos);

            //设置sheet2
            Sheet sheet2 = workBook.createSheet("低收入");//创建sheet名称
            String getRetainText2 = this.getRetainText2
                    .replace("YYYY",yyyy)
                    .replace("MM",mm);
            getLiveInsuranceCenterRetainSheet2(sheet2,workBook,getRetainText2,
                    lowReportPojos);

            //设置sheet3
            Sheet sheet3 = workBook.createSheet("发放情况");//创建sheet名
            List<String> list = new ArrayList<String>();
            list.add(textList.get(0).replace("YYYY",yyyy)
                    .replace("MM",mm));
            list.add(textList.get(1));
            list.add(textList.get(2).replace("householdsNum",getStr(sumReportHouseholdsNum))
                    .replace("personNum",getStr(sumReportSubsidyNum)));
            list.add(textList.get(3));
            list.add(textList.get(4).replace("money ",getStr(sumReportAbBtje)));
            list.add(textList.get(5));
            list.add(textList.get(6).replace("householdsNum",getStr(sumLowReportHouseholdsNum))
                    .replace("personNum",getStr(sumLowReportSubsidyNum)));
            list.add(textList.get(7));
            list.add(textList.get(8).replace("money ",getStr(sumLowReportAbBtje)));
            list.add(textList.get(9));
            list.add(textList.get(10).replace("money",JudgeForBigDecimal(sumLowReportAbBtje)
                    .add(JudgeForBigDecimal(sumReportAbBtje)).toString())
                    .replace("moneyZW",CnNumberUtils.toUppercase(JudgeForBigDecimal(sumLowReportAbBtje)
                            .add(JudgeForBigDecimal(sumReportAbBtje)).doubleValue())));
            list.add(textList.get(11));
            list.add(textList.get(12));
            list.add(textList.get(13));
            list.add(textList.get(14).replace("userName",userInfo.getUsername()));
            list.add(textList.get(15).replace("date",DatetimeUtils.date2string(date,
                    DatetimeUtils.YYYYMMDD_CH)));
            getLiveInsuranceCenterRetainSheet3(sheet3,workBook,list);

            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            //下载功能
            FileUtils.buildResponseEntity(response,finalXlsxOutFile,"application/x-xls");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("写入Excel信息异常!" + e.toString());
            throw new RuntimeException("写入Excel信息异常!");
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                logger.error("关闭读写流异常!" + e.toString());
                throw new RuntimeException("关闭读写流异常!");
            }
        }
        return "数据导出成功";
    }

    /**
     * 住保中心补贴清册报表sheet1数据填充--低保
     * @param sheet
     * @param workBook
     * @param getRetainText
     * @param reportPojos
     */
    private void getRetainSheet1(Sheet sheet,Workbook workBook,String getRetainText,
                                 List<ReportPojo> reportPojos){
        //设置第一行标题
        CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 9);
        sheet.addMergedRegion(region1);
        Row rowTitle1 = sheet.createRow(0);
        rowTitle1.setHeight(Short.valueOf("630"));//行高31.5（ * 20）
        Cell firstTitle = rowTitle1.createCell(0);//序号
        firstTitle.setCellValue(getRetainText);

        ExcelUtil.setStyleForMergeCell(workBook,sheet,region1,firstTitle,Short.valueOf("20"),HSSFFont.BOLDWEIGHT_BOLD,
                "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        /**
         * 往Excel中写新数据
         */
        for (int j = 0; j < reportPojos.size(); j++) {
            // 创建一行：从第二行开始，跳过属性列
            Row row = sheet.createRow(j + 1);
            row.setHeight(Short.valueOf("380"));//行高19（ * 20）
            // 得到要插入的每一条记录
            ReportPojo reportPojoOne = reportPojos.get(j);
            // 在一行内循环
            Cell first = row.createCell(0);//序号
            first.setCellValue(j + 1);
            ExcelUtil.setStyle(workBook,first,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN,
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN);
            Cell two = row.createCell(1);
            two.setCellValue(reportPojoOne.getAbYhkh());//银行卡号
            ExcelUtil.setStyle(workBook,two,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN,
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN);
            Cell three = row.createCell(2);
            three.setCellValue(reportPojoOne.getAfmXm());//姓名
            ExcelUtil.setStyle(workBook,three,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN,
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN);
            Cell four = row.createCell(3);
            four.setCellValue(reportPojoOne.getAfmXb());//性别
            ExcelUtil.setStyle(workBook,four,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN,
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN);
            Cell five = row.createCell(4);
            five.setCellValue(reportPojoOne.getSfzh());//身份证
            ExcelUtil.setStyle(workBook,five,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN,
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN);
            Cell six = row.createCell(5);
            six.setCellValue(reportPojoOne.getAfhZl());//房屋地址
            ExcelUtil.setStyle(workBook,six,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN,
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN);
            Cell seven = row.createCell(6);
            seven.setCellValue(reportPojoOne.getAfhZfxzStr());//住房性质
            ExcelUtil.setStyle(workBook,seven,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN,
                    HSSFCellStyle.BORDER_THIN,HSSFCellStyle.BORDER_THIN);
            Cell eight = row.createCell(7);
            eight.setCellValue(reportPojoOne.getAbBtje()==null?"":
                    String.valueOf(reportPojoOne.getAbBtje()));//补贴金额
            ExcelUtil.setStyle(workBook,eight,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell nine = row.createCell(8);
            nine.setCellValue(reportPojoOne.getSubsidyNum()==null?"":
                    String.valueOf(reportPojoOne.getSubsidyNum()));//补贴人数
            ExcelUtil.setStyle(workBook,nine,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell ten = row.createCell(9);
            ten.setCellValue(reportPojoOne.getAfmLxdh());//联系电话
            ExcelUtil.setStyle(workBook,ten,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        }
        sheet.setColumnWidth(0,(short)((6.13)*256+1840) ); //调整第0列宽度256*width+1840
        sheet.setColumnWidth(1,(short)((19.88)*256+1840) ); //调整第1列宽度
        sheet.setColumnWidth(2,(short)((8.38)*256+1840) ); //调整第2列宽度
        sheet.setColumnWidth(3,(short)((8.38)*256+1840) ); //调整第3列宽度
        sheet.setColumnWidth(4,((short)(18.75)*256+1840) ); //调整第4列宽度
        sheet.setColumnWidth(5,((short)(23.25)*256+1840) ); //调整第5列宽度
        sheet.setColumnWidth(6,((short)(8.38)*256+1840) ); //调整第6列宽度
        sheet.setColumnWidth(7,((short)(8.38)*256+1840) ); //调整第7列宽度
        sheet.setColumnWidth(8,((short)(7.25)*256+1840) ); //调整第8列宽度
        sheet.setColumnWidth(9,((short)(13.88)*256+1840) ); //调整第9列宽度
        sheet.setColumnWidth(10,(short)((8.38)*256+1840) ); //调整第10列宽度
    }

    /**
     * 住保中心补贴清册报表sheet2数据填充--低收入
     * @param sheet
     * @param workBook
     * @param getRetainText
     * @param reportPojos
     */
    private void getRetainSheet2(Sheet sheet,Workbook workBook,String getRetainText,
                                 List<ReportPojo> reportPojos){
        //设置第一行标题
        CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 9);
        sheet.addMergedRegion(region1);
        Row rowTitle1 = sheet.createRow(0);
        rowTitle1.setHeight(Short.valueOf("630"));//行高31.5（ * 20）
        Cell firstTitle = rowTitle1.createCell(0);//序号
        firstTitle.setCellValue(getRetainText);
        ExcelUtil.setStyleForMergeCell(workBook,sheet,region1,firstTitle,Short.valueOf("20"),HSSFFont.BOLDWEIGHT_BOLD,
                "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        /**
         * 往Excel中写新数据
         */
        for (int j = 0; j < reportPojos.size(); j++) {
            // 创建一行：从第二行开始，跳过属性列
            Row row = sheet.createRow(j + 1);
            row.setHeight(Short.valueOf("380"));//行高19（ * 20）
            // 得到要插入的每一条记录
            ReportPojo reportPojoOne = reportPojos.get(j);
            // 在一行内循环
            Cell first = row.createCell(0);//序号
            first.setCellValue(j + 1);
            ExcelUtil.setStyle(workBook,first,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell two = row.createCell(1);
            two.setCellValue(reportPojoOne.getAbYhkh());//银行卡号
            ExcelUtil.setStyle(workBook,two,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell three = row.createCell(2);
            three.setCellValue(reportPojoOne.getAfmXm());//姓名
            ExcelUtil.setStyle(workBook,three,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell four = row.createCell(3);
            four.setCellValue(reportPojoOne.getAfmXb());//性别
            ExcelUtil.setStyle(workBook,four,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell five = row.createCell(4);
            five.setCellValue(reportPojoOne.getSfzh());//身份证
            ExcelUtil.setStyle(workBook,five,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell six = row.createCell(5);
            six.setCellValue(reportPojoOne.getAfhZl());//房屋地址
            ExcelUtil.setStyle(workBook,six,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell seven = row.createCell(6);
            seven.setCellValue(reportPojoOne.getAfhZfxzStr());//住房性质
            ExcelUtil.setStyle(workBook,seven,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell eight = row.createCell(7);
            eight.setCellValue(reportPojoOne.getAbBtje()==null?"":
                    String.valueOf(reportPojoOne.getAbBtje()));//补贴金额
            ExcelUtil.setStyle(workBook,eight,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell nine = row.createCell(8);
            nine.setCellValue(reportPojoOne.getSubsidyNum()==null?"":
                    String.valueOf(reportPojoOne.getSubsidyNum()));//补贴人数
            ExcelUtil.setStyle(workBook,nine,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell ten = row.createCell(9);
            ten.setCellValue(reportPojoOne.getAfmLxdh());//联系电话
            ExcelUtil.setStyle(workBook,ten,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        }
        sheet.setColumnWidth(0,(short)((6.13)*256+1840) ); //调整第0列宽度256*width+1840
        sheet.setColumnWidth(1,(short)((19.88)*256+1840) ); //调整第1列宽度
        sheet.setColumnWidth(2,(short)((8.38)*256+1840) ); //调整第2列宽度
        sheet.setColumnWidth(3,(short)((8.38)*256+1840) ); //调整第3列宽度
        sheet.setColumnWidth(4,((short)(18.75)*256+1840) ); //调整第4列宽度
        sheet.setColumnWidth(5,((short)(23.25)*256+1840) ); //调整第5列宽度
        sheet.setColumnWidth(6,((short)(8.38)*256+1840) ); //调整第6列宽度
        sheet.setColumnWidth(7,((short)(8.38)*256+1840) ); //调整第7列宽度
        sheet.setColumnWidth(8,((short)(7.25)*256+1840) ); //调整第8列宽度
        sheet.setColumnWidth(9,((short)(13.88)*256+1840) ); //调整第9列宽度
        sheet.setColumnWidth(10,(short)((8.38)*256+1840) ); //调整第10列宽度
    }

    /**
     * 住保中心补贴清册报表sheet3数据填充--新增退出名单
     * @param sheet
     * @param workBook
     * @param getRetainTextInsert
     * @param getRetainTextDropOut
     * @param insertReportPojos
     * @param dropOutReportPojos
     */
    private void getRetainSheet3(Sheet sheet,Workbook workBook,String getRetainTextInsert,String getRetainTextDropOut,
                                 List<ReportPojo> insertReportPojos,List<ReportPojo> dropOutReportPojos){
        //新增补贴人员名单
        //设置第一行标题
        CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 9);
        sheet.addMergedRegion(region1);
        Row rowTitle1 = sheet.createRow(0);
        rowTitle1.setHeight(Short.valueOf("450"));//行高22.5（ * 20）
        Cell firstTitle = rowTitle1.createCell(0);//序号
        firstTitle.setCellValue(getRetainTextInsert);
        ExcelUtil.setStyleForMergeCell(workBook,sheet,region1,firstTitle,Short.valueOf("18"),HSSFFont.BOLDWEIGHT_NORMAL,
                "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        //设置列名称
        // 创建一行：属性列
        Row rowAtt = sheet.createRow(1);
        createAttCol(workBook,getRetainList3,rowAtt,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_BOLD,
                "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        /**
         * 往Excel中写新数据
         */
        for (int j = 0; j < insertReportPojos.size(); j++) {
            // 创建一行：从第二行开始，跳过属性列
            Row row = sheet.createRow(j + 2);
            row.setHeight(Short.valueOf("380"));//行高19（ * 20）
            // 得到要插入的每一条记录
            ReportPojo reportPojoOne = insertReportPojos.get(j);
            // 在一行内循环
            Cell first = row.createCell(0);//序号
            first.setCellValue(j + 1);
            ExcelUtil.setStyle(workBook,first,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell two = row.createCell(1);
            two.setCellValue(reportPojoOne.getAfmXm());//姓名
            ExcelUtil.setStyle(workBook,two,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell three = row.createCell(2);
            three.setCellValue(reportPojoOne.getSfzh());//身份证
            ExcelUtil.setStyle(workBook,three,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell four = row.createCell(3);
            four.setCellValue(reportPojoOne.getAfhZl());//房屋地址
            ExcelUtil.setStyle(workBook,four,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell five = row.createCell(4);
            five.setCellValue(reportPojoOne.getAfhZfxzStr());//住房性质
            ExcelUtil.setStyle(workBook,five,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell six = row.createCell(5);
            six.setCellValue(reportPojoOne.getAbJtrk()==null?"0":
                    String.valueOf(reportPojoOne.getAbJtrk()));//家庭人口
            ExcelUtil.setStyle(workBook,six,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell seven = row.createCell(6);
            seven.setCellValue(reportPojoOne.getAbBtje()==null?"":
                    String.valueOf(reportPojoOne.getAbBtje()));//补贴金额
            ExcelUtil.setStyle(workBook,seven,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell eight = row.createCell(7);
            eight.setCellValue(reportPojoOne.getAfmLxdh());//联系电话
            ExcelUtil.setStyle(workBook,eight,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell nine = row.createCell(8);
            nine.setCellValue(reportPojoOne.getAbYhkh());//银行卡号
            ExcelUtil.setStyle(workBook,nine,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell ten = row.createCell(9);
            ten.setCellValue(reportPojoOne.getAbBtxz());//补贴性质
            ExcelUtil.setStyle(workBook,ten,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        }
        //取消补贴人员名单
        CellRangeAddress region2 = new CellRangeAddress(insertReportPojos.size()+4, insertReportPojos.size()+4, (short) 0, (short) 9);
        sheet.addMergedRegion(region2);
        Row rowTitle2 = sheet.createRow(insertReportPojos.size()+4);
        rowTitle2.setHeight(Short.valueOf("450"));//行高22.5（ * 20）
        Cell TwoTitle = rowTitle2.createCell(0);//标题2
        TwoTitle.setCellValue(getRetainTextDropOut);
        ExcelUtil.setStyleForMergeCell(workBook,sheet,region2,TwoTitle,Short.valueOf("18"),HSSFFont.BOLDWEIGHT_NORMAL,
                "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        /**
         * 往Excel中写新数据
         */
        for (int j = 0; j < dropOutReportPojos.size(); j++) {
            // 创建一行：从第二行开始，跳过属性列
            Row row = sheet.createRow(insertReportPojos.size() + 5 + j);
            row.setHeight(Short.valueOf("380"));//行高19（ * 20）
            // 得到要插入的每一条记录
            ReportPojo reportPojoOne = dropOutReportPojos.get(j);
            // 在一行内循环
            Cell first = row.createCell(0);//序号
            first.setCellValue(j + 1);
            ExcelUtil.setStyle(workBook,first,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell two = row.createCell(1);
            two.setCellValue(reportPojoOne.getAfmXm());//姓名
            ExcelUtil.setStyle(workBook,two,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell three = row.createCell(2);
            three.setCellValue(reportPojoOne.getSfzh());//身份证
            ExcelUtil.setStyle(workBook,three,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell four = row.createCell(3);
            four.setCellValue(reportPojoOne.getAfhZl());//房屋地址
            ExcelUtil.setStyle(workBook,four,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell five = row.createCell(4);
            five.setCellValue(reportPojoOne.getAfhZfxzStr());//住房性质
            ExcelUtil.setStyle(workBook,five,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell six = row.createCell(5);
            six.setCellValue(reportPojoOne.getAbJtrk()==null?"":
                    String.valueOf(reportPojoOne.getAbJtrk()));//家庭人口
            ExcelUtil.setStyle(workBook,six,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell seven = row.createCell(6);
            seven.setCellValue(reportPojoOne.getAbBtje()==null?"":
                    String.valueOf(reportPojoOne.getAbBtje()));//补贴金额
            ExcelUtil.setStyle(workBook,seven,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell eight = row.createCell(7);
            eight.setCellValue(reportPojoOne.getAfmLxdh());//联系电话
            ExcelUtil.setStyle(workBook,eight,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell nine = row.createCell(8);
            nine.setCellValue(reportPojoOne.getAbYhkh());//银行卡号
            ExcelUtil.setStyle(workBook,nine,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell ten = row.createCell(9);
            ten.setCellValue(reportPojoOne.getAbBtxz());//补贴性质
            ExcelUtil.setStyle(workBook,ten,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        }

        sheet.setColumnWidth(0,(short)((6.13)*256+1840) ); //调整第0列宽度256*width+1840
        sheet.setColumnWidth(1,(short)((8.38)*256+1840) ); //调整第1列宽度
        sheet.setColumnWidth(2,(short)((23.25)*256+1840) ); //调整第2列宽度
        sheet.setColumnWidth(3,(short)((34.25)*256+1840) ); //调整第3列宽度
        sheet.setColumnWidth(4,((short)(18.75)*256+1840) ); //调整第4列宽度
        sheet.setColumnWidth(5,((short)(8.38)*256+1840) ); //调整第5列宽度
        sheet.setColumnWidth(6,((short)(8.38)*256+1840) ); //调整第6列宽度
        sheet.setColumnWidth(7,((short)(8.38)*256+1840) ); //调整第7列宽度
        sheet.setColumnWidth(8,((short)(28.38)*256+1840) ); //调整第8列宽度
        sheet.setColumnWidth(9,((short)(8.38)*256+1840) ); //调整第9列宽度
//        sheet.setColumnWidth(10,(short)((8.38)*256+1840) ); //调整第10列宽度

    }

    /**
     * 银行补贴明细报表sheet1数据填充--新增退出名单
     * @param sheet
     * @param workBook
     * @param reportPojos
     */
    private void getPaySheet1(Sheet sheet, Workbook workBook,
                              List<ReportPojo> reportPojos) {
        /**
         * 往Excel中写新数据
         */
        for (int j = 0; j < reportPojos.size(); j++) {
            // 创建一行：从第二行开始，跳过属性列
            Row row = sheet.createRow(j);
            row.setHeight(Short.valueOf("380"));//行高19（ * 20）
            // 得到要插入的每一条记录
            ReportPojo reportPojoOne = reportPojos.get(j);
            // 在一行内循环
            Cell first = row.createCell(0);
            first.setCellValue(reportPojoOne.getAbYhkh());//银行卡号
            ExcelUtil.setStyle(workBook,first,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell two = row.createCell(1);
            two.setCellValue(reportPojoOne.getAfmXm());//姓名
            ExcelUtil.setStyle(workBook,two,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell three = row.createCell(2);
            three.setCellValue(reportPojoOne.getAbBtje()==null?"":
                    String.valueOf(reportPojoOne.getAbBtje()));//补贴金额
            ExcelUtil.setStyle(workBook,three,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell four = row.createCell(3);
            four.setCellValue("0");//固定值
            ExcelUtil.setStyle(workBook,four,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        }
        sheet.setColumnWidth(0,(short)((28.38)*256+1840) ); //调整第0列宽度256*width+1840
        sheet.setColumnWidth(1,(short)((23.25)*256+1840) ); //调整第1列宽度
        sheet.setColumnWidth(2,(short)((8.38)*256+1840) ); //调整第2列宽度
        sheet.setColumnWidth(3,(short)((6.38)*256+1840) ); //调整第3列宽度
    }

    /**
     * 公示补贴名单报表sheet数据填充--公示补贴名单
     * @param sheet
     * @param workBook
     * @param reportPojos
     */
    private void getPublicitySheet1(Sheet sheet, Workbook workBook,
                              List<ReportPojo> reportPojos) {
        /**
         * 往Excel中写新数据
         */
        for (int j = 0; j < reportPojos.size(); j++) {
            // 创建一行：从第二行开始，跳过属性列
            Row row = sheet.createRow(j);
            row.setHeight(Short.valueOf("380"));//行高19（ * 20）
            // 得到要插入的每一条记录
            ReportPojo reportPojoOne = reportPojos.get(j);
            // 在一行内循环
            Cell first = row.createCell(0);
            first.setCellValue(j+1);//序号
            ExcelUtil.setStyle(workBook,first,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell two = row.createCell(1);
            two.setCellValue(reportPojoOne.getAfmXm());//姓名
            ExcelUtil.setStyle(workBook,two,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell three = row.createCell(2);
            three.setCellValue(reportPojoOne.getAfhZl());//房屋地址
            ExcelUtil.setStyle(workBook,three,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell four = row.createCell(3);
            four.setCellValue(reportPojoOne.getAfhZfxzStr());//住房性质
            ExcelUtil.setStyle(workBook,four,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell five = row.createCell(4);
            five.setCellValue(reportPojoOne.getSubsidyNum()==null?"":
                    String.valueOf(reportPojoOne.getSubsidyNum()));//补贴人数
            ExcelUtil.setStyle(workBook,five,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell six = row.createCell(5);
            six.setCellValue(reportPojoOne.getAbBtje()==null?"":
                    String.valueOf(reportPojoOne.getAbBtje()));//补贴金额
            ExcelUtil.setStyle(workBook,six,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());

        }
        sheet.setColumnWidth(0,(short)((8.38)*256+1840) ); //调整第0列宽度256*width+1840
        sheet.setColumnWidth(1,(short)((23.25)*256+1840) ); //调整第1列宽度
        sheet.setColumnWidth(2,(short)((28.38)*256+1840) ); //调整第2列宽度
        sheet.setColumnWidth(3,(short)((8.38)*256+1840) ); //调整第3列宽度
        sheet.setColumnWidth(4,(short)((8.38)*256+1840) ); //调整第3列宽度
        sheet.setColumnWidth(5,(short)((8.38)*256+1840) ); //调整第3列宽度
    }

    /**
     * 住保中心补贴清册报表sheet1数据填充--低保
     * @param sheet
     * @param workBook
     * @param getRetainText
     * @param reportPojos
     */
    private void getLiveInsuranceCenterRetainSheet1(Sheet sheet,Workbook workBook,String getRetainText,
                                 List<ReportPojo> reportPojos){
        //设置第一行标题
        CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 9);
        sheet.addMergedRegion(region1);
        Row rowTitle1 = sheet.createRow(0);
        rowTitle1.setHeight(Short.valueOf("630"));//行高31.5（ * 20）
        Cell firstTitle = rowTitle1.createCell(0);//序号
        firstTitle.setCellValue(getRetainText);
        ExcelUtil.setStyleForMergeCell(workBook,sheet,region1,firstTitle,Short.valueOf("20"),HSSFFont.BOLDWEIGHT_BOLD,
                "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        /**
         * 往Excel中写新数据
         */
        for (int j = 0; j < reportPojos.size(); j++) {
            // 创建一行：从第二行开始，跳过属性列
            Row row = sheet.createRow(j + 1);
            row.setHeight(Short.valueOf("380"));//行高19（ * 20）
            // 得到要插入的每一条记录
            ReportPojo reportPojoOne = reportPojos.get(j);
            // 在一行内循环
            Cell first = row.createCell(0);//序号
            first.setCellValue(j + 1);
            ExcelUtil.setStyle(workBook,first,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell two = row.createCell(1);
            two.setCellValue(reportPojoOne.getAbYhkh());//银行卡号
            ExcelUtil.setStyle(workBook,two,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell three = row.createCell(2);
            three.setCellValue(reportPojoOne.getAfmXm());//姓名
            ExcelUtil.setStyle(workBook,three,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell four = row.createCell(3);
            four.setCellValue(reportPojoOne.getAfmXb());//性别
            ExcelUtil.setStyle(workBook,four,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell five = row.createCell(4);
            five.setCellValue(reportPojoOne.getSfzh());//身份证
            ExcelUtil.setStyle(workBook,five,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HorizontalAlignment.CENTER.getCode(),VerticalAlignment.CENTER.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell six = row.createCell(5);
            six.setCellValue(reportPojoOne.getAfhZl());//房屋地址
            ExcelUtil.setStyle(workBook,six,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell seven = row.createCell(6);
            seven.setCellValue(reportPojoOne.getAfmLxdh());//联系电话
            ExcelUtil.setStyle(workBook,seven,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell eight = row.createCell(7);
            eight.setCellValue(reportPojoOne.getAbBtje()==null?"":
                    String.valueOf(reportPojoOne.getAbBtje()));//补贴金额
            ExcelUtil.setStyle(workBook,eight,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        }
        sheet.setColumnWidth(0,(short)((6.13)*256+1840)); //调整第0列宽度256*width+1840
        sheet.setColumnWidth(1,(short)((19.88)*256+1840)); //调整第1列宽度
        sheet.setColumnWidth(2,(short)((8.38)*256+1840)); //调整第2列宽度
        sheet.setColumnWidth(3,(short)((8.38)*256+1840)); //调整第3列宽度
        sheet.setColumnWidth(4,(short)((18.75)*256+1840)); //调整第4列宽度
        sheet.setColumnWidth(5,(short)((23.25)*256+1840)); //调整第5列宽度
        sheet.setColumnWidth(6,(short)((8.38)*256+1840)); //调整第6列宽度
        sheet.setColumnWidth(7,(short)((8.38)*256+1840)); //调整第7列宽度
    }

    /**
     * 住保中心补贴清册报表sheet2数据填充--低收入
     * @param sheet
     * @param workBook
     * @param getRetainText
     * @param reportPojos
     */
    private void getLiveInsuranceCenterRetainSheet2(Sheet sheet,Workbook workBook,String getRetainText,
                                 List<ReportPojo> reportPojos){
        //设置第一行标题
        CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 9);
        sheet.addMergedRegion(region1);
        Row rowTitle1 = sheet.createRow(0);
        rowTitle1.setHeight(Short.valueOf("630"));//行高31.5（ * 20）
        Cell firstTitle = rowTitle1.createCell(0);//序号
        firstTitle.setCellValue(getRetainText);
        ExcelUtil.setStyleForMergeCell(workBook,sheet,region1,firstTitle,Short.valueOf("20"),HSSFFont.BOLDWEIGHT_BOLD,
                "宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        /**
         * 往Excel中写新数据
         */
        for (int j = 0; j < reportPojos.size(); j++) {
            // 创建一行：从第二行开始，跳过属性列
            Row row = sheet.createRow(j + 1);
            row.setHeight(Short.valueOf("380"));//行高19（ * 20）
            // 得到要插入的每一条记录
            ReportPojo reportPojoOne = reportPojos.get(j);
            // 在一行内循环
            Cell first = row.createCell(0);//序号
            first.setCellValue(j + 1);
            ExcelUtil.setStyle(workBook,first,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell two = row.createCell(1);
            two.setCellValue(reportPojoOne.getAbYhkh());//银行卡号
            ExcelUtil.setStyle(workBook,two,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell three = row.createCell(2);
            three.setCellValue(reportPojoOne.getAfmXm());//姓名
            ExcelUtil.setStyle(workBook,three,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell four = row.createCell(3);
            four.setCellValue(reportPojoOne.getAfmXb());//性别
            ExcelUtil.setStyle(workBook,four,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell five = row.createCell(4);
            five.setCellValue(reportPojoOne.getSfzh());//身份证
            ExcelUtil.setStyle(workBook,five,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell six = row.createCell(5);
            six.setCellValue(reportPojoOne.getAfhZl());//房屋地址
            ExcelUtil.setStyle(workBook,six,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell seven = row.createCell(6);
            seven.setCellValue(reportPojoOne.getAfmLxdh());//联系电话
            ExcelUtil.setStyle(workBook,seven,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
            Cell eight = row.createCell(7);
            eight.setCellValue(reportPojoOne.getAbBtje()==null?"":
                    String.valueOf(reportPojoOne.getAbBtje()));//补贴金额
            ExcelUtil.setStyle(workBook,eight,Short.valueOf("10"),HSSFFont.BOLDWEIGHT_NORMAL,
                    "宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode(),
                    BorderStyle.THIN.getCode(),BorderStyle.THIN.getCode());
        }
        sheet.setColumnWidth(0,(short)((6.13)*256+1840)); //调整第0列宽度256*width+1840
        sheet.setColumnWidth(1,(short)((19.88)*256+1840)); //调整第1列宽度
        sheet.setColumnWidth(2,(short)((8.38)*256+1840)); //调整第2列宽度
        sheet.setColumnWidth(3,(short)((8.38)*256+1840)); //调整第3列宽度
        sheet.setColumnWidth(4,(short)((18.75)*256+1840)); //调整第4列宽度
        sheet.setColumnWidth(5,(short)((23.25)*256+1840)); //调整第5列宽度
        sheet.setColumnWidth(6,(short)((8.38)*256+1840)); //调整第6列宽度
        sheet.setColumnWidth(7,(short)((8.38)*256+1840)); //调整第7列宽度
    }

    /**
     * 住保中心补贴清册报表sheet3数据填充--新增退出名单
     * @param sheet
     * @param workBook
     * @param textList
     */
    private void getLiveInsuranceCenterRetainSheet3(Sheet sheet,Workbook workBook,List<String> textList){
        //合并单元格
        //第一行
        //1.标题:2018年11月徐州市市区廉租住房租赁补贴发放情况明细表
        Row row1 = mergeCell(workBook,sheet,0, 0, 0, 11, null,textList.get(0),(short)(27*20),
                (short)22,HSSFFont.BOLDWEIGHT_BOLD,"黑体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //第二行
        //2.低收入住房困难家庭
        Row row2 = mergeCell(workBook,sheet,3, 3,  0, 2, null,textList.get(1),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //2.户数和人数householdsNumAndpPersonNum
        mergeCell(workBook,sheet,3, 3, 3, 4, row2,textList.get(2),(short)(20*20),(short)15,
                HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //2.发放金额
        mergeCell(workBook,sheet,3, 3, 8, 9, row2,textList.get(3),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //2.发放金额money
        mergeCell(workBook,sheet,3, 3, 10, 11, row2,textList.get(4),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());


        //3.低保困难家庭
        Row row3 = mergeCell(workBook,sheet,6, 6, 0, 2, null,textList.get(5),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //3.户数和人数householdsNumAndpPersonNum
        mergeCell(workBook,sheet,6, 6, 3, 4, row3,textList.get(6),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //3.发放金额
        mergeCell(workBook,sheet,6, 6, 8, 9, row3,textList.get(7),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //3.发放金额money
        mergeCell(workBook,sheet,6, 6, 10, 11, row3,textList.get(8),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //4.发放金额
        Row row4 = mergeCell(workBook,sheet,9, 9, 0, 2, null,textList.get(9),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //4.发放金额money
        mergeCell(workBook,sheet,9, 9, 3, 4, row4,textList.get(6),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //5.中心领导签字
        Row row5 = mergeCell(workBook,sheet,12, 12, 0, 2, null,textList.get(11),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //6.财务科负责人签字
        Row row6 = mergeCell(workBook,sheet,15, 15, 0, 2, null,textList.get(12),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //7.中心领导签字
        Row row7 = mergeCell(workBook,sheet,18, 18, 0, 2, null,textList.get(13),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //8.制表人
        Row row8 = mergeCell(workBook,sheet,20, 20, 9, 11, null,textList.get(14),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());
        //9.日期
        Row row9 = mergeCell(workBook,sheet,21, 21, 9, 11, null,textList.get(15),(short)(20*20),
                (short)15,HSSFFont.BOLDWEIGHT_NORMAL,"宋体",HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.VERTICAL_CENTER,
                BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode(),BorderStyle.NONE.getCode());

        for (int i=0;i<11;i++){
            sheet.setColumnWidth(0,(short)((8.38)*256+1840) ); //调整第0列宽度256*width+1840
        }
    }


    /**
     * 设置样式
     * @param workBook
     * @param attCols 属性列集合
     * @param row
     * @param fontHeightInPoints 字体号数
     * @param boldweight 粗体
     * @param fontName 字体样式（"宋体"）
     * @param alignment 水平居中类型
     * @param verticalAlignment 垂直居中
     * @param borderBottom 下边框
     * @param borderLeft 左边框
     * @param borderTop 上边框
     * @param borderRight 右边框
     */
    private void createAttCol(Workbook workBook,List<String> attCols,Row row,Short fontHeightInPoints,
                              Short boldweight,String fontName,Short alignment,
                              Short verticalAlignment,Short borderBottom,Short borderLeft,
                              Short borderTop,Short borderRight){
        for(int i=0;i<attCols.size();i++){
            String attCol = attCols.get(i);
            Cell cell = row.createCell(i);
            cell.setCellValue(attCol);
            ExcelUtil.setStyle(workBook,cell,fontHeightInPoints,boldweight,
                    fontName,alignment,verticalAlignment,
                    borderBottom,borderLeft,
                    borderTop,borderRight);
        }
    }

    /**
     * 封面
     * @param workBook
     * @param sheet
     * @param firstRow 起始行
     * @param lastRow 结束行
     * @param firstCol 起始行位置
     * @param lastCol 偏移量
     * @param row 行
     * @param text 内容
     * @param rowHight 行高
     * @param fontHeightInPoints 字号
     * @param boldweight 加粗
     * @param fontName 字体
     * @param alignment 水平位置
     * @param verticalAlignment 垂直位置
     * @param borderBottom 边框下
     * @param borderLeft 边框左
     * @param borderTop 边框上
     * @param borderRight 边框右
     */
    private Row mergeCell(Workbook workBook,Sheet sheet,Integer firstRow,Integer lastRow,
                           Integer firstCol,Integer lastCol,Row row,String text,
                           Short rowHight ,Short fontHeightInPoints,
                           Short boldweight,String fontName,Short alignment,
                           Short verticalAlignment,Short borderBottom,Short borderLeft,
                           Short borderTop,Short borderRight){
        //合并单元格
        CellRangeAddress region = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegion(region);
        if(row==null){
            row = sheet.createRow(firstRow);
        }
        row.setHeight(rowHight);//行高
        Cell cell = row.createCell(firstCol);//序号
        cell.setCellValue(text);
        ExcelUtil.setStyle(workBook,cell,fontHeightInPoints,boldweight,
                fontName,alignment,verticalAlignment,
                borderBottom,borderLeft,borderTop,borderRight);
        return row;
    }

    private String getStr(Object obj){
        return  obj==null?"0":obj.toString();
    }

    private BigDecimal JudgeForBigDecimal(BigDecimal bigDecimal){
        if(bigDecimal == null){
            return new BigDecimal(0);
        }
        return bigDecimal;
    }

}
