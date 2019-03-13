package com.sys.service;

import com.sys.common.CommonUtils;
import com.sys.common.ExcellUtil;
import com.sys.common.StringUtils;
import com.sys.mapper.FactMappingMapper;
import com.sys.pojo.FactMapping;
import com.sys.pojo.ItemCodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.css.sac.ElementSelector;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FactMappingService {

    @Autowired
    private FactMappingMapper factMappingMapper;

    public  List<FactMapping> findFactByItemid(Map map){
        return  factMappingMapper.findFactByItemid(map);
    }



    /**
     * 房源分配类型查询
     * @param map
     * @return
     */
     public    List<FactMapping> findFactlxByBid(Map map){
        return factMappingMapper.findFactlxByBid(map);
    }



    /**
     * 查询配给房屋信息
     * @param map 楼栋id
     * @return
     */
    public  List<FactMapping> findFactByBuildingInfoId(Map map){
        return  factMappingMapper.findFactByBuildingInfoId(map);

    }

    /**
     * 查询某个楼栋下的房屋数量
     * @param buid
     * @return
     */
    public  int findFactMappingByBuId(String buid){
        return  factMappingMapper.findFactMappingByBuId(buid);

    }

    public  int deleteHouse(String idsArray){
        List<String> strsToList= null;
        try {
            String[] array=idsArray.split(",");
            strsToList = Arrays.asList(array);
            factMappingMapper.deleteByList(strsToList);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return  -1;
        }


    }

    /**
     * 查询待分配房屋信息
     * @param map 楼栋id
     * @return
     */
    public  List<FactMapping> findFactByMap(Map map){
        return  factMappingMapper.findFactByMap(map);

    }


    public String importExcelInfo(InputStream in,String buidid ,MultipartFile file) throws Exception{
        List<List<Object>> listob = ExcellUtil.getBankListByExcel(in,file.getOriginalFilename());
        List<FactMapping> factMappingList = new ArrayList<FactMapping>();
        Map<String,Object> map=new HashMap<String, Object>();
        String result="";
        SimpleDateFormat DateFormat  = new SimpleDateFormat("yyyy-MM-dd");
        //遍历listob数据，把数据放到List中
        for (int i = 0; i < listob.size(); i++) {
            List<Object> ob = listob.get(i);
            System.out.print(listob.getClass());
            FactMapping factMapping = new FactMapping();
            //设
            if (ob.get(0).equals(" ") || ob.get(0) == null) {
                factMapping.setFactmappingId(CommonUtils.getUUIDWith_());
            } else {
                factMapping.setFactmappingId(String.valueOf(ob.get(0)));
            }
            factMapping.setBuildinginfoId(String.valueOf(ob.get(1)));
            factMapping.setCondoplotId(String.valueOf(ob.get(2)));
            factMapping.setfHocode(String.valueOf(ob.get(3)));
            factMapping.setfMapcode(String.valueOf(ob.get(4)));
            factMapping.setfBunum(String.valueOf(ob.get(5)));
            factMapping.setfHicode(String.valueOf(ob.get(6)));
            factMapping.setfFlcode(String.valueOf(ob.get(7)));
            factMapping.setfItsite(String.valueOf(ob.get(8)));
            factMapping.setfHostru(String.valueOf(ob.get(9)));
            factMapping.setfHouse(String.valueOf(ob.get(10)));
            System.out.print(ob.getClass());
            if (ob.get(11).toString().equals(" ") || ob.get(11).toString() == null) {
                factMapping.setfBulay(null);
            } else {
                factMapping.setfBulay(Short.parseShort(String.valueOf(ob.get(11))));
            }
            factMapping.setfCurlayname(String.valueOf(ob.get(12)));
            factMapping.setfBuname(String.valueOf(ob.get(13)));
            factMapping.setfCecodeL(String.valueOf(ob.get(14)));
            factMapping.setfCecode(String.valueOf(ob.get(15)));
            factMapping.setfRonum(String.valueOf(ob.get(16)));
            if (ob.get(17).toString().equals(" ") || ob.get(17).toString() == null) {
                factMapping.setfConacre2(null);
            } else {
                BigDecimal bd = new BigDecimal(String.valueOf(ob.get(17)));
                factMapping.setfConacre2(bd);
            }
            if (ob.get(18).toString().equals(" ") || ob.get(18).toString() == null) {
                factMapping.setfInacre2(null);
            } else {
                BigDecimal bd = new BigDecimal(String.valueOf(ob.get(18)));
                factMapping.setfInacre2(bd);
            }
            if (ob.get(19).toString().equals(" ") || ob.get(19).toString() == null) {
                factMapping.setfApacre2(null);
            } else {
                BigDecimal bd = new BigDecimal(String.valueOf(ob.get(19)));
                factMapping.setfApacre2(bd);
            }
            factMapping.setfCondonum(String.valueOf(ob.get(20)));
            if (ob.get(21).toString().equals(" ") || ob.get(21).toString() == null) {
                factMapping.setfLayhig(null);
            } else {
                BigDecimal bd = new BigDecimal(String.valueOf(ob.get(21)));
                factMapping.setfLayhig(bd);
            }
            factMapping.sethLaytype(String.valueOf(ob.get(22)));
            factMapping.setHousemodelId(String.valueOf(ob.get(23)));
            factMapping.setfHostate(String.valueOf(ob.get(24)));
            factMapping.setfPrebunum(String.valueOf(ob.get(25)));
            factMapping.setfCommunity(String.valueOf(ob.get(26)));
            factMapping.setfItsitedetail(String.valueOf(ob.get(27)));
            if (ob.get(28).toString().equals(" ") || ob.get(28).toString() == null) {
                factMapping.setfCurlay(null);
            } else {
                BigDecimal bd = new BigDecimal(String.valueOf(ob.get(28)));
                factMapping.setfCurlay(bd);
            }
            factMapping.setBdchocode(String.valueOf(ob.get(29)));
            if (ob.get(30).toString().equals(" ") || ob.get(30).toString() == null) {
                factMapping.setfConacre(null);
            } else {
                BigDecimal bd = new BigDecimal(String.valueOf(ob.get(30)));
                factMapping.setfConacre(bd);
            }
            if (ob.get(31).toString().equals(" ") || ob.get(31).toString() == null) {
                factMapping.setfInacre(null);
            } else {
                BigDecimal bd = new BigDecimal(String.valueOf(ob.get(31)));
                factMapping.setfInacre(bd);
            }
            if (ob.get(32).toString().equals(" ") || ob.get(32).toString() == null) {
                factMapping.setfApacre(null);
            } else {
                BigDecimal bd = new BigDecimal(String.valueOf(ob.get(32)));
                factMapping.setfApacre(bd);
            }
            factMapping.setXhocode(String.valueOf(ob.get(33)));
            factMapping.setRemark(String.valueOf(ob.get(34)));
            factMappingList.add(factMapping);
            Integer count = this.factMappingMapper.findFactMappingByName(factMapping.getfCondonum());
            if (factMapping.getBuildinginfoId() == null) {
                factMappingList.remove(factMapping);
            } else if (!factMapping.getBuildinginfoId().equals(buidid)) {
                factMappingList.remove(factMapping);
            } else if (factMapping.getfRonum() == null) {
                factMappingList.remove(factMapping);
            }else  if (factMapping.getBuildinginfoId()==null){
                factMappingList.remove(factMapping);
            }else  if (count>0){
                factMappingList.remove(factMapping);
            }
             //批量插入
            if (factMappingList==null || factMappingList.size()==0){
                result="execul有重复数据,导入失败";
            }else {
                factMappingMapper.insertFactList(factMappingList);
                result="导入成功";
            }
        }

        return  result;

    }
    /**
     * 根据申请单号查询分配的房屋
     * @param map
     * @return
     */
    public List<FactMapping> findFactByApplyid(Map map){
        return factMappingMapper.findFactByApplyid(map);
    }

    /**
     * 根据申请单号查询分配的房屋数量
     * @param map
     * @return
     */
    public int findFactCountByApplyid(Map map){
        return factMappingMapper.findFactCountByApplyid(map);
    }
    /**
     * 根据房屋id查询 用于打印选房确认单
     * @param map 房屋id list
     * @return
     */
    public List<FactMapping> findFactByFwId(Map map){
        return factMappingMapper.findFactByFwId(map);
    }

}
