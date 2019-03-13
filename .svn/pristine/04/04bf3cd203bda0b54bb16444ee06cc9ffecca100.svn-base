package com.sys.service;

import com.sys.common.CommonUtils;
import com.sys.common.ExcellUtil;
import com.sys.common.StringUtils;
import com.sys.mapper.BuidInfoMapper;
import com.sys.mapper.ItemCodeInfoMapper;
import com.sys.pojo.BuidInfo;
import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuidInfoService extends  BaseService<BuidInfo>{

    @Autowired
    private BuidInfoMapper buidInfoMapper;
    @Autowired
    private ItemCodeInfoMapper itemCodeInfoMapper;

    public  List<BuidInfo> findBuildInfoByIid(Map map){
        return  this.buidInfoMapper.findBuildInfoByIid(map);
    }

    public  List<BuidInfo> findBuildInfoByMap(Map map){
        return buidInfoMapper.findBuildInfoByMap(map);
    }


    public String importExcelInfo(InputStream in,String itemid ,MultipartFile file) throws Exception{
        List<List<Object>> listob = ExcellUtil.getBankListByExcel(in,file.getOriginalFilename());
        List<BuidInfo> buidInfoList = new ArrayList<BuidInfo>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result="";
        Map<String,Object> map=new HashMap<String, Object>();
        //遍历listob数据，把数据放到List中
        for (int i = 0; i < listob.size(); i++) {
            List<Object> ob = listob.get(i);
            BuidInfo buidInfo = new BuidInfo();
            //设置

            if (ob.get(0).equals(" ") || ob.get(0)==null) {
                buidInfo.setBuildinginfoId(CommonUtils.getUUIDWith_());
            }else {
                buidInfo.setBuildinginfoId(String.valueOf(ob.get(0)));
            }
            buidInfo.setItemcodeinfoId(String.valueOf(ob.get(1)));
            buidInfo.setBuconpermitsId(String.valueOf(ob.get(2)));
            buidInfo.setBunum(String.valueOf(ob.get(3)));
            buidInfo.setBucode(String.valueOf(ob.get(4)));
            buidInfo.setBuname(String.valueOf(new BigDecimal(String.valueOf(ob.get(5))).shortValue()));
            buidInfo.setMapcode(String.valueOf(ob.get(6)));
            buidInfo.setHillcode(String.valueOf(ob.get(7)));
            buidInfo.setBuildcode(String.valueOf(ob.get(8)));
            buidInfo.setHillcodeL(String.valueOf(ob.get(9)));
            buidInfo.setBuildcodeL(String.valueOf(ob.get(10)));
            buidInfo.setBunllicnum(String.valueOf(ob.get(11)));
            buidInfo.setBustructtype(String.valueOf(ob.get(12)));
            buidInfo.setBuusetype(String.valueOf(ob.get(13)));
            buidInfo.setBufinishyear(String.valueOf(ob.get(14)));
            buidInfo.setPropertykind(String.valueOf(ob.get(15)));
            if (ob.get(16).toString().equals(" ") || ob.get(16).toString() == null) {
                buidInfo.setLaycount(null);
            } else {
                buidInfo.setLaycount(Short.parseShort(ob.get(16).toString()));
            }
            if (ob.get(17).toString().equals(" ") || ob.get(17).toString() == null) {
                buidInfo.setHousecount(null);
            } else {
                buidInfo.setHousecount(Short.parseShort(ob.get(17).toString()));
            }
            if (ob.get(18).toString().equals(" ") || ob.get(18).toString() == null) {
                buidInfo.setStruarea(null);
            } else{
                BigDecimal bd = new BigDecimal(String.valueOf(ob.get(18)));
                buidInfo.setStruarea(bd);
            }
            if (ob.get(19).toString().equals(" ") || ob.get(19).toString() == null) {
                buidInfo.setInarea(null);
            } else{
                BigDecimal inarea = new BigDecimal(String.valueOf(ob.get(19)));
                buidInfo.setInarea(inarea);
            }
            if (ob.get(20).toString().equals(" ") || ob.get(20).toString() == null) {
                buidInfo.setSharearea(null);
            } else{
                BigDecimal sharearea = new BigDecimal(String.valueOf(ob.get(20)));
                buidInfo.setSharearea(sharearea);
            }
            buidInfo.setSharecalexplain(String.valueOf(ob.get(21)));
            buidInfo.setWallbelonginfo(String.valueOf(ob.get(22)));
            buidInfo.setBuildinfostates(String.valueOf(ob.get(23)));
            buidInfo.setFinsate(String.valueOf(ob.get(24)));
            buidInfo.setInistate(String.valueOf(ob.get(25)));
            buidInfo.setIsused(String.valueOf(ob.get(26)));
            buidInfo.setIsfinish(String.valueOf(ob.get(27)));
            buidInfo.setRemark(String.valueOf(ob.get(28)));
            if (ob.get(29).toString().equals(" ") || ob.get(29).toString() == null) {
                buidInfo.setUnderlay(null);
            } else {
                buidInfo.setUnderlay(Short.parseShort(String.valueOf(ob.get(29))));
            }
            if (ob.get(30).toString().equals(" ") || ob.get(30).toString() == null) {
                buidInfo.setElevator(null);
            } else {
                buidInfo.setElevator(new BigDecimal(String.valueOf(ob.get(30))).shortValue());
            }
            buidInfo.setBuildinfostates(String.valueOf(ob.get(31)));
            if (ob.get(32).toString().equals(" ") || ob.get(32).toString() == null) {
                buidInfo.setButype(null);
            } else {
                buidInfo.setButype(new BigDecimal(String.valueOf(ob.get(32))).shortValue());
            }
            if (ob.get(33).toString().equals(" ") || ob.get(33).toString() == null) {
                buidInfo.setDatastate(null);
            } else {
                buidInfo.setDatastate(new BigDecimal(String.valueOf(ob.get(33))).shortValue());
            }
            buidInfo.setReckonincommon(String.valueOf(ob.get(34)));
            buidInfo.setUnreckonincommon(String.valueOf(ob.get(35)));
            if (ob.get(36).toString().equals(" ") || ob.get(36).toString() == null) {
                buidInfo.setGhsgmj(null);
            } else{
                BigDecimal bd = new BigDecimal(String.valueOf(ob.get(36)));
                buidInfo.setGhsgmj(bd);
            }
            if (ob.get(37).toString().equals(" ") || ob.get(37).toString() == null) {
                buidInfo.setSgmj(null);
            } else{
                BigDecimal bd = new BigDecimal(String.valueOf(ob.get(37)));
                buidInfo.setSgmj(bd);
            }
            buidInfo.setCoordx(String.valueOf(ob.get(38)));
            buidInfo.setCoordy(String.valueOf(ob.get(39)));
            buidInfo.setAllotuserid(String.valueOf(ob.get(40)));
            if (ob.get(41).toString().equals(" ") || ob.get(41).toString() == null) {
                buidInfo.setOutputstate(null);
            } else {
                buidInfo.setOutputstate(new BigDecimal(String.valueOf(ob.get(41))).shortValue());
            }
            buidInfo.setBdcBu(String.valueOf(ob.get(42)));
            buidInfo.setZdtybh(String.valueOf(ob.get(43)));
            buidInfo.setBdcZh(String.valueOf(ob.get(44)));
            buidInfo.setXbucode(String.valueOf(ob.get(45)));
            buidInfoList.add(buidInfo);
            if (itemid.equals(buidInfo.getItemcodeinfoId())){
                Integer count=this.buidInfoMapper.findBuildNameByItemId(buidInfo.getBuname(),buidInfo.getItemcodeinfoId());
                if (count>0) {
                    buidInfoList.remove(buidInfo);
                }
            }else{
                buidInfoList.remove(buidInfo);
            }
        }
        //批量插入
        if (buidInfoList==null || buidInfoList.size()==0){
            result="execul有重复数据,导入失败";
        }else {
            buidInfoMapper.insertBuildlist(buidInfoList);
            result="导入成功";
        }
        return  result;
    }

    /**
     * 查询某个项目下的楼栋数量
     * @param itemid
     * @return
     */
    public int findCountByItemId(String itemid){
        return buidInfoMapper.findCountByItemId(itemid);
    }

}
