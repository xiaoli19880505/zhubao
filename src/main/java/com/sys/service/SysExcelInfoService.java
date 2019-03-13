package com.sys.service;

import com.sys.common.ExcelUtil;
import com.sys.mapper.ExcelMapper;
import com.sys.mapper.blagsh.BliveGongSMapper;
import com.sys.pojo.Excel.ExcelBean;
import com.sys.pojo.blagsh.BliveGongS;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysExcelInfoService {

    @Autowired
    private BliveGongSMapper bliveGongSMapper;
    @Autowired
    private ExcelMapper excelMapper;


    public XSSFWorkbook exportExcelInfo(BliveGongS bliveGongS) throws Exception{
        //根据条件查询数据
        List<Map<String,Object>> list =excelMapper.findAllBliveGos(bliveGongS) ;
        //System.out.println(list);
        List<ExcelBean> excel = new ArrayList<ExcelBean>();
        Map<Integer,List<ExcelBean>> map = new LinkedHashMap<Integer,List<ExcelBean>>();
        //设置标题栏
        excel.add(new ExcelBean("申请类型","BLG_TYPE_NAME",0));
        excel.add(new ExcelBean("申请编号","BLG_SQBH",0));
        excel.add(new ExcelBean("申请日期", "APLDATE", 0));
        excel.add(new ExcelBean("申请人","BLG_USER_NAME",0));
        excel.add(new ExcelBean("身份证号","BLG_SFZH",0));
        excel.add(new ExcelBean("所属县区","BLG_SSQ_STR", 0));
        excel.add(new ExcelBean("所属街道","BLG_SSJ_STR",0));
        excel.add(new ExcelBean("审核状态","BLG_STATUS_STR",0));
        map.put(0,excel);
        String sheetName ="诚信列表";
        //调用ExcelUtil方法
        XSSFWorkbook xssfWorkbook = ExcelUtil.createExcelFile(BliveGongS.class, list, map, sheetName);
        System.out.println(xssfWorkbook);
        return xssfWorkbook;
    }


}
