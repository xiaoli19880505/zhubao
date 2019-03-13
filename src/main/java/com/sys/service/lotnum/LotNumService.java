package com.sys.service.lotnum;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.common.BatchUtil;
import com.sys.common.CommonUtils;
import com.sys.common.StringUtils;
import com.sys.mapper.MessageMapper;
import com.sys.mapper.apply.ApplyChangeMapper;
import com.sys.mapper.apply.ApplyMapper;
import com.sys.mapper.apply.ApproveMapper;
import com.sys.mapper.lotnum.LotNumMapper;
import com.sys.pojo.UserInfo;
import com.sys.pojo.apply.Apply;
import com.sys.pojo.apply.ApplyChange;
import com.sys.pojo.lotnum.LotNum;
import com.sys.service.MessageService;
import org.activiti.engine.identity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Desc:摇号service类
 * @Author:wangli
 * @CreateTime:10:13 2018/11/14
 */
@Service
public class LotNumService {

    private static Logger logger = LoggerFactory.getLogger(LotNumService.class);

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private LotNumMapper lotNumMapper;

    @Autowired
    private ApproveMapper approveMapper;

    @Autowired
    private BatchUtil batchUtil;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ApplyChangeMapper applyChangeMapper;



    /**
     * 根据摇号结果，更新申请人对应的申请单状态为摇号状态
     * @param lotNumList 上传的 list
     * @param chooseNum 选中的数量
     */
    public void updateApplyInfo(List<LotNum> lotNumList, int chooseNum,HttpServletRequest request){

        String applyType = lotNumList.get(0).getSqlb();
        List<ApplyChange> applyChangesList = new ArrayList<ApplyChange>();
        Map<String,Object> conditionMap = Maps.newHashMap();
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        /*摇号变更记录*/
        for(int i=0;i<lotNumList.size();i++){
            ApplyChange applyChange = new ApplyChange();//申请变更
            applyChange.setAcId(CommonUtils.getUUIDWith_());//申请变更表id
            applyChange.setAcApplyType(applyType);
            applyChange.setAcSqbh(lotNumList.get(i).getSqbh());
            applyChange.setAcTime(new Date());
            applyChange.setAcUserid(userInfo.getUserid());
            conditionMap.put("sqbh",lotNumList.get(i).getSqbh());
            applyChange.setAcSqid(approveMapper.findByMap(conditionMap).getApvid());
            if(chooseNum>i){
                applyChange.setAcType("2");
            }else{
                applyChange.setAcType("13");
            }
            applyChangesList.add(applyChange);
        }
        batchUtil.save(lotNumList,"lotnum.LotNumMapper");//批量插入摇号表
        batchUtil.save(applyChangesList,"apply.ApplyChangeMapper");//插入申请变更信息
        //对应审批单更新为摇号待选房状态
        List<LotNum> updateList = lotNumList.subList(0,chooseNum);
        this.applyMapper.updateApplyLcBatchByList(updateList);
    }

    /**
     * 更新申请单信息，用于主动放弃申请的用户
     * @param applyid 申请单id
     * @return
     */
    public int updateApplyInfo(String applyid,String applyType,HttpServletRequest request){
        int count = 0;
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        /*更新申请单的状态为保障结束*/
        Apply apply = this.applyMapper.selectById(applyid);
        apply.setApLc((short)1);
        apply.setApZt((short) 5);
        count=count+applyMapper.update(apply);

        /*申请变更记录*/
        ApplyChange applyChange = new ApplyChange();//申请变更
        applyChange.setAcId(CommonUtils.getUUIDWith_());//申请变更表id
        applyChange.setAcApplyType(applyType);//业务类别
        applyChange.setAcSqbh(apply.getApSqbh());//申请编号
        applyChange.setAcTime(new Date());//操作日期
        applyChange.setAcUserid(userInfo.getUserid());//操作用户id
        applyChange.setAcSqid(apply.getApId());//申请单id
        applyChange.setAcType("3");//设为摇号放弃状态
        this.applyChangeMapper.insert(applyChange);

        /*将轮候状态的申请单更新为3，待选房状态*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("applyType",applyType);//申请类别
        LotNum lotNum = this.lotNumMapper.selectOneByOreder(conditionMap);
        if(count>0){
            /*设为待选房状态*/
            if(lotNum!=null){
                conditionMap.put("sqbh", lotNum.getSqbh());
                Apply chooseApply = this.applyMapper.selectByMap(conditionMap);
                chooseApply.setApLc((short)3);
                count=count+applyMapper.update(chooseApply);
                lotNum.setState("1");//设为待选房状态
                count=count+lotNumMapper.update(lotNum);//更新摇号表对应条目状态为待选房状态

                /*插入变更记录*/
                applyChange.setAcId(CommonUtils.getUUIDWith_());//申请变更表id
                applyChange.setAcType("2");//设为摇号成功状态
                applyChange.setAcSqbh(lotNum.getSqbh());//设置申请编号
                applyChange.setAcTime(new Date());//操作日期
                applyChange.setAcSqid(chooseApply.getApId());//申请单id
                this.applyChangeMapper.insert(applyChange);
            }

        }
        return count;

    }

    /**
     * 全部设置为待选房状态（针对轮候和未摇号两种状态）
     * @param mapList
     * @param apptype
     * @param userid
     */
    public void updateApplyInfo(List<Map<String,Object>> mapList, String apptype,String userid){

        List<LotNum> lotNumAddList = Lists.newArrayList();
        List<LotNum> lotNumUpdateList = Lists.newArrayList();
        List<ApplyChange> applyChangesList = new ArrayList<ApplyChange>();//申请变更记录表
        /*查询类别为applytyp且状态为轮候状态的NUM最大值*/
        Map<String,Object> conditionMap = Maps.newHashMap();
        conditionMap.put("applytype",apptype);
        conditionMap.put("state","0");
        String posStr = this.lotNumMapper.selectMaxNumByMap(conditionMap);
        int pos = 1;
        if(!StringUtils.isEmpty(posStr)){
            pos=Integer.parseInt(posStr);
            pos++;
        }
        /*移除key*/
        conditionMap.remove("applytype");
        conditionMap.remove("state");

        for(Map<String,Object> map:mapList){
            /*如果lid存在，则为轮候状态的记录，加入更新集合中*/
            if(map.get("LID")!=null){
                LotNum lotNum = this.lotNumMapper.selectById((String) map.get("LID"));
                lotNum.setState("1");
                lotNumUpdateList.add(lotNum);
            }else{
                LotNum lotNum = new LotNum();
                lotNum.setLid(CommonUtils.getUUIDWith_());//设置主键
                lotNum.setState("1");//设置状态
                lotNum.setSqbh((String) map.get("ID"));//设置申请编号
                lotNum.setSfzh((String) map.get("SFZH"));//设置身份证号
                lotNum.setName((String) map.get("USERNAME"));//设置申请人姓名
                lotNum.setQztime(new Date());//设置日期
                lotNum.setUserid(userid);//设置操作用户id
                lotNum.setSqlb(apptype);//设置业务类别
                lotNum.setNum(""+pos);
                lotNumAddList.add(lotNum);
                pos++;
            }

            /*申请变更记录*/
            ApplyChange applyChange = new ApplyChange();//申请变更
            applyChange.setAcId(CommonUtils.getUUIDWith_());//申请变更表id
            applyChange.setAcApplyType(apptype);//业务类别
            applyChange.setAcSqbh((String) map.get("ID"));//申请编号
            applyChange.setAcTime(new Date());//操作日期
            applyChange.setAcUserid(userid);//操作用户id
            conditionMap.put("sqbh",(String) map.get("ID"));
            applyChange.setAcSqid(approveMapper.findByMap(conditionMap).getApvid());
            applyChange.setAcType("2");//设为戴选芳状态
            //this.applyChangeMapper.insert(applyChange);
            applyChangesList.add(applyChange);
        }

        logger.debug("size:"+lotNumUpdateList.size());

        //批量插入摇号表
        batchUtil.save(lotNumAddList,"lotnum.LotNumMapper");
        batchUtil.save(applyChangesList,"apply.ApplyChangeMapper");//插入申请变更信息
        /*批量更新轮候状态的数据为待选房状态*/
        if(!lotNumUpdateList.isEmpty()){
            this.lotNumMapper.updateLotList(lotNumUpdateList);
        }

        //对应审批单更新为摇号驳回状态
        if(!lotNumUpdateList.isEmpty()){
            lotNumAddList.addAll(lotNumUpdateList);
        }

        /*批量更新申请单的lc状态值为3*/
        if(!lotNumAddList.isEmpty()){
            this.applyMapper.updateApplyLcBatchByList(lotNumAddList);
        }
    }

    /**
     * 经适房轮候或者待摇号状态过期，被操作员取消；需要更新申请单的状态;经适房AP_ZT更改为6，低保特困公租房为5
     * @param mapList
     * @param apptype
     */
    public void updateApplyInfo( List<Map<String,Object>> mapList,String apptype,HttpServletRequest request){
        List<Apply> applyList = Lists.newArrayList();
        List<ApplyChange> applyChangesList = new ArrayList<ApplyChange>();//申请变更记录表
        UserInfo userInfo = (UserInfo)request.getSession().getAttribute("user");
        Map<String,Object> conditionMap = Maps.newHashMap();
        /*经适房AP_ZT更改为6,低保特困公租房改为5*/
        short apzt=6;
        if(apptype.equals("3")){
            apzt=5;
        }

        /*更新申请单状态*/
        for (Map<String,Object> map:mapList){
            Apply apply = new Apply();
            apply.setApZt(apzt);
            apply.setApId((String) map.get("APPID"));
            applyList.add(apply);
            /*申请变更记录*/
            ApplyChange applyChange = new ApplyChange();//申请变更
            applyChange.setAcId(CommonUtils.getUUIDWith_());//申请变更表id
            applyChange.setAcApplyType(apptype);//业务类别
            applyChange.setAcSqbh((String) map.get("ID"));//申请编号
            applyChange.setAcTime(new Date());//操作日期
            applyChange.setAcUserid(userInfo.getUserid());//操作用户id
            conditionMap.put("sqbh",(String) map.get("ID"));
            applyChange.setAcSqid(approveMapper.findByMap(conditionMap).getAplid());
            applyChange.setAcType("12");//设为戴选芳状态
            //this.applyChangeMapper.insert(applyChange);
            applyChangesList.add(applyChange);
        }

        this.applyMapper.updateApplyZtBatchByList(applyList);//批量更新
        batchUtil.save(applyChangesList,"apply.ApplyChangeMapper");//插入申请变更信息
    }


}