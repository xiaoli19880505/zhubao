package com.sys.service;

import com.sys.common.CommonUtils;
import com.sys.common.DatetimeUtils;
import com.sys.common.ExcellUtil;
import com.sys.common.StringUtils;
import com.sys.common.encrypt.AESUtil;
import com.sys.mapper.ItemCodeInfoMapper;
import com.sys.pojo.ItemCodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("itemCodeInfoService")
public class ItemCodeInfoService extends  BaseService<ItemCodeInfo> {

    @Autowired
    private ItemCodeInfoMapper itemCodeInfoMapper;


    public String importExcelInfo(InputStream in, MultipartFile file) throws Exception{
        List<List<Object>> listob = ExcellUtil.getBankListByExcel(in,file.getOriginalFilename());
        List<ItemCodeInfo> itemCodeInfoList = new ArrayList<ItemCodeInfo>();
        Map<String,Object> map=new HashMap<String, Object>();
        String result="";
        SimpleDateFormat DateFormat  = new SimpleDateFormat("yyyy-MM-dd");
        //遍历listob数据，把数据放到List中
        for (int i = 0; i < listob.size(); i++) {
            List<Object> ob = listob.get(i);
            ItemCodeInfo itemCodeInfo = new ItemCodeInfo();
            //设置
            itemCodeInfo.setItemcodeinfoId(String.valueOf(ob.get(0)));
            if (StringUtils.isEmpty(itemCodeInfo.getItemcodeinfoId())){
                itemCodeInfo.setItemcodeinfoId(CommonUtils.getUUIDWith_());
            }
            itemCodeInfo.setConstructerId(String.valueOf(ob.get(1)));
            itemCodeInfo.setIcItname(String.valueOf(ob.get(2)));
            itemCodeInfo.setIcItnum(String.valueOf(ob.get(3)));
            itemCodeInfo.setIcItsite(String.valueOf(ob.get(4)));
            itemCodeInfo.setIcDist(String.valueOf(ob.get(5)));
            itemCodeInfo.setIcOffice(String.valueOf(ob.get(6)));
            itemCodeInfo.setIcZone(String.valueOf(ob.get(7)));
            if (ob.get(8).toString().equals(" ") || ob.get(8).toString()==null){
                itemCodeInfo.setIcSumaarea(null);
            }else {
                itemCodeInfo.setIcSumaarea(new BigDecimal(String.valueOf(ob.get(8))).shortValue());
            }
            itemCodeInfo.setIcItacpaper(String.valueOf(ob.get(9)));
            itemCodeInfo.setIcPllicnum(String.valueOf(ob.get(10)));
            itemCodeInfo.setIcConstructortype(String.valueOf(ob.get(11)));
            if (ob.get(12).toString().equals(" ") || ob.get(12).toString()==null){
                itemCodeInfo.setIcState(null);
            }else {
                itemCodeInfo.setIcState(new BigDecimal(String.valueOf(ob.get(12))).shortValue());
            }
            itemCodeInfo.setIcAcnum(String.valueOf(ob.get(13)));
            if (ob.get(14).toString().equals(" ") || ob.get(14).toString()==null){
                itemCodeInfo.setIcActime(null);
            }else {
                itemCodeInfo.setIcActime(DateFormat.parse(ob.get(14).toString()));
            }
            itemCodeInfo.setIcAcman(String.valueOf(ob.get(15)));
            itemCodeInfo.setIcAcreason(String.valueOf(ob.get(16)));
            if (ob.get(17).toString().equals(" ") || ob.get(17).toString()==null){
                itemCodeInfo.setIcAudittime(null);
            }else {
                itemCodeInfo.setIcActime(DateFormat.parse(ob.get(17).toString()));
            }
            itemCodeInfo.setIcAuditman(String.valueOf(ob.get(18)));
            itemCodeInfo.setIcAuditreason(String.valueOf(ob.get(19)));
            if (ob.get(20).toString().equals(" ") || ob.get(20).toString()==null){
                itemCodeInfo.setIcEnsure(null);
            }else {
                itemCodeInfo.setIcEnsure(new BigDecimal(String.valueOf(ob.get(20))).shortValue());
            }
            itemCodeInfoList.add(itemCodeInfo);
            map.put("itemname",itemCodeInfo.getIcItname());
            Integer count=this.itemCodeInfoMapper.selectItemByName(map);
            if (count>0){
                itemCodeInfoList.remove(itemCodeInfo);
            }
        }
        //批量插入
        if (itemCodeInfoList==null || itemCodeInfoList.size()==0){
            result="execul有重复数据,导入失败";
        }else {
            itemCodeInfoMapper.inserItemCodeInfo(itemCodeInfoList);
            result="导入成功";
        }
        return  result;

    }



}
