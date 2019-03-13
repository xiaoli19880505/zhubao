package com.sys.service.serialnum;


import com.sys.common.CommonUtils;
import com.sys.common.DatetimeUtils;
import com.sys.mapper.serialnum.SerialNumHeadMapper;
import com.sys.mapper.serialnum.SerialNumLineMapper;
import com.sys.pojo.serialnum.SerialNumHead;
import com.sys.pojo.serialnum.SerialNumLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SerialNumService {

    /**
     * 补位0
     */
    private final String fillIn = "0000000000";

    /**
     * 流水号头
     */
    @Autowired
    private SerialNumHeadMapper serialNumHeadMapper;

    /**
     * 流水号行
     */
    @Autowired
    private SerialNumLineMapper serialNumLineMapper;

    /**
     * 根据流水号编码获取流水号
     * @param snCode
     * @return
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW) //不管是否存在事务,都创建一个新的事务,原来的挂起,新的执行完毕,继续执行老的事务
    public Map<String,Object> getSerialNum(String snCode){
        Map<String,Object> map = new HashMap<String, Object>();
        SerialNumHead serialNumHead = serialNumHeadMapper.selectBySNCode(snCode);
        if(serialNumHead==null){
            return CommonUtils.getMsgForRet(false,"未找到此" + snCode + "生成规则!");
        }

        String snPrefix = serialNumHead.getSnPrefix()==null?"":serialNumHead.getSnPrefix();//前缀
        int snSize = serialNumHead.getSnSize();//长度
        String snDateStructure = serialNumHead.getSnDateStructure();//时间规格
        Date date = new Date();
        String dateStr = DatetimeUtils.date2string(date,snDateStructure);//中间时间
        String nowDateStr = DatetimeUtils.date2string(date,DatetimeUtils.YYYYMMDD);//时间yyyyMMdd
        /*
            判断今日是否已经生成流水号，
                1.如果生成取数,从NOW_POSITION + 步长 开始
                2.如果未生成,从SN_START 开始
         */
        SerialNumLine serialNumLine = new SerialNumLine();
        serialNumLine.setSnhId(serialNumHead.getSnId());
        serialNumLine.setNowDate(nowDateStr);
        serialNumLine = serialNumLineMapper.selectByFKIdAndNowDate(serialNumLine);
        Integer start ;
        if(serialNumLine==null){
            serialNumLine = new SerialNumLine();
            start =  serialNumHead.getSnStart();//开始
            serialNumLine.setNowDate(nowDateStr);
            serialNumLine.setSnhId(serialNumHead.getSnId());
            serialNumLine.setCreatePerson("admin");
            serialNumLine.setCreateTime(date);
            serialNumLine.setUpdatePerson("admin");
            serialNumLine.setUpdateTime(date);
            serialNumLine.setNowPosition(start);
            serialNumLine.setSnlId(CommonUtils.getUUID());
            serialNumLineMapper.insert(serialNumLine);//新增
        } else {
            int snStepSize = serialNumHead.getSnStepSize();//步长
            start =  serialNumLine.getNowPosition() + snStepSize;//开始
            //当数据大于最大序号，提示生成序号出错
            if(start>serialNumHead.getSnMaxValue()){
                return CommonUtils.getMsgForRet(false,"序号生成达到最大值无法再进行生成!");
            }
            serialNumLine.setNowPosition(start);
            serialNumLine.setUpdatePerson("admin");
            serialNumLine.setUpdateTime(date);
            serialNumLineMapper.update(serialNumLine);//更新
        }
        //后缀流水长度
        int snlength = snSize - snPrefix.length() - dateStr.length();
        String fillInSta = getSuffix(start,snlength);

        return CommonUtils.getMsgForRet(true,snPrefix + dateStr + fillInSta);
    }

    /**
     * 根据流水号编码获取流水号
     * @param snCode
     * @return
     */
    @Transactional(propagation=Propagation.REQUIRES_NEW) //不管是否存在事务,都创建一个新的事务,原来的挂起,新的执行完毕,继续执行老的事务
    public SerialNumLine getSerialNumInfo(String snCode){
        Map<String,Object> map = new HashMap<String, Object>();
        SerialNumHead serialNumHead = serialNumHeadMapper.selectBySNCode(snCode);
        if(serialNumHead==null){
            throw new RuntimeException("未维护流水号规则!");
        }
        SerialNumLine serialNumLineReturn = new SerialNumLine();
        String snPrefix = serialNumHead.getSnPrefix()==null?"":serialNumHead.getSnPrefix();//前缀
        serialNumLineReturn.setSnPrefix(snPrefix);

        int snSize = serialNumHead.getSnSize();//长度
        String snDateStructure = serialNumHead.getSnDateStructure();//时间规格
        Date date = new Date();
        String dateStr = DatetimeUtils.date2string(date,snDateStructure);//中间时间
        serialNumLineReturn.setSnDateStructure(dateStr);

        /*
            判断今日是否已经生成流水号，
                1.如果生成取数,从NOW_POSITION + 步长 开始
                2.如果未生成,从SN_START 开始
         */
        SerialNumLine serialNumLine = new SerialNumLine();
        serialNumLine.setSnhId(serialNumHead.getSnId());
        String nowDateStr = DatetimeUtils.date2string(date,DatetimeUtils.YYYYMMDD);//时间yyyyMMdd
        serialNumLine.setNowDate(nowDateStr);
        serialNumLine = serialNumLineMapper.selectByFKIdAndNowDate(serialNumLine);
        Integer start ;
        if(serialNumLine==null){
            serialNumLine = new SerialNumLine();
            start =  serialNumHead.getSnStart();//开始
            serialNumLine.setNowDate(nowDateStr);
            serialNumLine.setSnhId(serialNumHead.getSnId());
            serialNumLine.setCreatePerson("admin");
            serialNumLine.setCreateTime(date);
            serialNumLine.setUpdatePerson("admin");
            serialNumLine.setUpdateTime(date);
            serialNumLine.setNowPosition(start);
            serialNumLine.setSnlId(CommonUtils.getUUID());
            serialNumLineMapper.insert(serialNumLine);//新增
        } else {
            int snStepSize = serialNumHead.getSnStepSize();//步长
            start =  serialNumLine.getNowPosition() + snStepSize;//开始
            //当数据大于最大序号，提示生成序号出错
            if(start>serialNumHead.getSnMaxValue()){
                throw new RuntimeException("序号生成达到最大值无法再进行生成!");
            }
            serialNumLine.setNowPosition(start);
            serialNumLine.setUpdatePerson("admin");
            serialNumLine.setUpdateTime(date);
            serialNumLineMapper.update(serialNumLine);//更新
        }
        //后缀流水长度 总长度 - 前缀长度 - 时间长度
        int snlength = snSize - snPrefix.length() - dateStr.length();
        String fillInSta = getSuffix(start,snlength);
        serialNumLineReturn.setPosition(fillInSta);//流水号

        serialNumLineReturn.setCode(snPrefix + dateStr + fillInSta);//编码

        return serialNumLineReturn;
    }

    /**
     * 获取流水号
     * @param start 当前生成要流水号位置
     * @param suffixLeng 流水号长度
     * @return
     */
    private String getSuffix(Integer start,int suffixLeng){
        String fillInStr = fillIn + start.toString();
        String fillInSta = fillInStr.substring(fillInStr.length()-suffixLeng);//获取后缀
        return fillInSta;
    }


}
