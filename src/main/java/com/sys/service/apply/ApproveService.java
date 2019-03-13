package com.sys.service.apply;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.common.CommonUtils;
import com.sys.common.PropertiesUtil;
import com.sys.common.SendPhoneCodeUtil;
import com.sys.common.StringUtils;
import com.sys.common.dataconver.BaseInfoDataConvertor;
import com.sys.mapper.ParmItemMapper;
import com.sys.mapper.apply.*;
import com.sys.pojo.Message;
import com.sys.pojo.ParmItem;
import com.sys.pojo.UserInfo;
import com.sys.pojo.apply.*;
import com.sys.service.BaseService;
import com.sys.service.MessageService;
import com.sys.service.common.MessageAndFormService;
import com.sys.service.lotnum.LotNumService;
import com.sys.service.schedule.ScheduleJobDomain;
import com.sys.service.schedule.ScheduleJobService;
import org.apache.commons.collections4.map.LinkedMap;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author hwx
 * @CopyRight (C) 江苏乳虎
 * @date 2018/10/16 0016
 * @desc
 */
@Service
public class ApproveService extends BaseService<Approve> {
    @Autowired
    ApproveMapper appoveMapper;
    @Autowired
    private MessageAndFormService messageAndFormService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ApplyButieMapper applyButieMapper;
    @Autowired
    private ApplyForgraDuateMapper applyForgraDuateMapper;
    @Autowired
    private ApplyForForeignMapper applyForForeignMapper;
    @Autowired
    private ApplyChangeMapper applyChangeMapper;

    @Autowired
    private ScheduleJobService scheduleJobService;

    @Autowired
    private ParmItemMapper parmItemMapper;

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private LotNumService lotNumService;

    /**
     * 分页查询
     * 根据申请用户id查询申请记录
     * @param
     * @return
     */

    public List<Approve> page(Map map) {
//        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
//                Integer.parseInt((String) map.get("rows")));
        List<Approve> list=appoveMapper.findAppoveByApplyUser(map);
        return list;
    }

    public List<Approve> findApproveByMap(Map map) {
//        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
//                Integer.parseInt((String) map.get("rows")));
        List<Approve> list=appoveMapper.findListByMap(map);
        return list;
    }

    /**
     * 条件查询审批单
     * @param map
     * @return
     */
    public Approve findByMap(Map map){
        return appoveMapper.findByMap(map);
    }
    /**
     * 条件查询审批单列表
     * @param map
     * @return
     */
    public PageInfo<Approve> findListByMap(Map map){
        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
                Integer.parseInt((String) map.get("rows")));
        List<Approve> list=appoveMapper.findListByMap(map);
        return new PageInfo<Approve>(list);

    }

    /**
     * 根据身份证号查询审核全部通过的申请人信息
     * @param
     * @return
     */
    public Approve findBySfzh(Map map){
        return appoveMapper.findBySfzh(map);
    }

    /**
     * 根据条件查询数量
     * @param map
     * @return
     */
    public int countNsByMap(Map map){
        return appoveMapper.countNsByMap(map);
    }

    /**
     * 根据申请房源类型和所属区查询
     * @param map
     * @return
     */
    public List<Map> findByFylxAndSSQ(Map map){
//        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
//                Integer.parseInt((String) map.get("rows")));
        List<Map> list= appoveMapper.findByFylxAndSSQ(map);
        return list;
    }

    /**
     * 查询申请审核信息
     * @param aptype
     * @param aplbh
     * @return
     */
    public Object findApplyForApprove(String aptype,String aplbh,String xm,String page,String rows){
        if(StringUtils.isEmpty(page)){
            page="1";
        }
        if(StringUtils.isEmpty(rows)){
            rows="20";
        }
        Approve approve = new Approve();
        if(StringUtils.isEmpty(aptype)){//申请类型
            approve.setAptype(null);
        }else{
            approve.setAptype(aptype);
        }
        if(StringUtils.isEmpty(aplbh)){//申请编号
            approve.setAplbh(null);
        }else{
            approve.setAplbh(aplbh);
        }
        if(StringUtils.isEmpty(xm)){//申请编号
            approve.setXm(null);
        }else{
            approve.setXm(xm);
        }
        PageHelper.startPage(Integer.parseInt(page),
                Integer.parseInt(rows));
        List<Approve> list= appoveMapper.findApplyForApprove(approve);
        return new PageInfo<Approve>(list);
    }

    public int findByFylxAndSSQCount(Map map){
        return  appoveMapper.findByFylxAndSSQCount(map);
    }

    public List<Map> findAllApply(Map map){
//        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
//                Integer.parseInt((String) map.get("rows")));
        List<Map> list= appoveMapper.findAllApply(map);
//        List<String> tempList=new ArrayList<String>();
//        for (int i = 0; i <list.size() ; i++) {
//            list.get(i).get("aplid");
//
//        }
        return list;
    }

    public List<Map> findNoSign(Map map){
        List<Map> list= appoveMapper.findNoSign(map);
        return list;
    }

    public List<Map> findNoSignToMes(Map map){
        List<Map> list= appoveMapper.findNoSignToMes(map);
        return list;
    }

    public List<Approve> findAllNoSign(Map map){
        List<Approve> list= appoveMapper.findAllNoSign(map);
        return list;
    }

    public int countNoSignToMes(Map map){
        return appoveMapper.countNoSignToMes(map);
    }

    public int countNoSign(Map map){
        return appoveMapper.countNoSign(map);
    }

    public List<Map> houseQuitList(Map map){
        List<Map> list= appoveMapper.houseQuitList(map);
        return list;
    }
    public int houseQuitListCount(Map map){
        return appoveMapper.houseQuitListCount(map);
    }

    public int findAllApplyCount(Map map){
        return appoveMapper.findAllApplyCount(map);
    }

    public List<Approve> findTFSpForm(Map map){
        return appoveMapper.findTFSpForm(map);
    }

    /**
     * 条件查询已经申请通过但没有分到房子的申请信息（经适房和低保特困公租房）
     * @param map
     * @return
     */
    public List<Map<String,Object>> findPassApprove(Map map){
        return this.appoveMapper.findPassApprove(map);
    }

    /**
     * 更新审批单状态（主要用于摇号，更新为摇号驳回状态）
     * @param mapList
     * @return
     */
    public int updateApproveStateBatch(List<Map<String,Object>> mapList){
        return this.appoveMapper.updateApproveStateBatch(mapList);
    }

    public Approve findByApplyId(String aplid){
        return this.appoveMapper.findByApplyId(aplid);
    }

    /**
     * 根据条件查询审批单的数量（主要用于初审）
     * @param map
     * @return
     */
    public int findCountByMap(Map map){
        return this.appoveMapper.findCountByMap(map);
    }

    public Object sendMessage(Map map){
        String tplid="JSM41823-0026";
        List<Approve> appoveList= (List<Approve>) map.get("appoveList");
        String sqrq=(String)map.get("sqrq");
        String doc=(String)map.get("doc");
        String area=(String)map.get("area");
        String content=(String)map.get("content");
        UserInfo userInfo= (UserInfo) map.get("userInfo");
        List<Message> msgAddList = Lists.newArrayList();
        List<Message> msgupdateList = Lists.newArrayList();
        if(appoveList!=null){
            for (Approve approve: appoveList) {
                LinkedMap<String,String> linkedMap = new LinkedMap<String, String>();
                linkedMap.put("@1@",approve.getUsername());
                linkedMap.put("@2@", PropertiesUtil.getApplyTypeProperties(approve.getAtype()));
                linkedMap.put("@3@",approve.getAplbh());
                linkedMap.put("@4@",sqrq);
                linkedMap.put("@5@",doc);
                linkedMap.put("@6@",area);
                linkedMap.put("@7@",content);
                linkedMap.put("@8@",userInfo.getUsername());
                linkedMap.put("@9@",userInfo.getLinktel());
                linkedMap.put("@10@", BaseInfoDataConvertor.convertUserUnit(userInfo));
                /*发送短信*/
                messageAndFormService.sendMessage(approve.getLinktel(),tplid,linkedMap);
                Map<String,Object> conditionMap = Maps.newHashMap();
                conditionMap.put("sqbh",approve.getAplbh());
                conditionMap.put("msType","40");
                if(messageService.selectCountByMap(conditionMap)>0){
                    Message message = new Message();
                    message.setSqbh(approve.getAplbh());
                    message.setMstype("40");
                    message.setMstime(new Date());
                    msgupdateList.add(message);
                }else{
                    Message message = new Message();
                    message.setMsuserid(userInfo.getUserid());
                    message.setAppType(approve.getAtype());
                    message.setLinktel(approve.getLinktel());
                    message.setSqbh(approve.getAplbh());
                    message.setMsid(CommonUtils.getUUIDWith_());
                    message.setMstime(new Date());
                    message.setMstem(tplid);
                    message.setMstype("40");
                    message.setToUsername(approve.getUsername());
                    msgAddList.add(message);
                    // message.setToUserSfzh(sfzhArr[i]);
                }
            }
            messageService.updateAndAddMessageBatch(msgAddList,msgupdateList);
        }

        return "success";
    }

    /**
     * 条件查询数目
     * @param map
     * @return
     */
    public int findPassApproveCount(Map map){
        return this.appoveMapper.findPassApproveCount(map);
    }

    /**
     * 放弃操作
     * @param applyid 申请单id
     * @param sqbh 申请编号
     * @param userid 操作人id
     * @param acRemark 备注
     * @return
     */
    public int updatehouseQuitOperate(String atype, String applyid, String sqbh, String userid, String acRemark, HttpServletRequest request){
        //放弃操作跟新申请单状态 zt为5
        try {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("zt",5);
            map.put("applyid",applyid);
            if(PropertiesUtil.getApplyTypeProperties("butie").equals(atype)){
                applyButieMapper.updateApZT(map);
            }else if(PropertiesUtil.getApplyTypeProperties("xinjy").equals(atype)){
                applyForgraDuateMapper.updateApLC(map);
            }else if(PropertiesUtil.getApplyTypeProperties("wailaiwg").equals(atype)){
               applyForForeignMapper.updateApLC(map);
            }else if(PropertiesUtil.getApplyTypeProperties("jingsf").equals(atype)
                    ||PropertiesUtil.getApplyTypeProperties("gongzf").equals(atype)){
                Apply apply = this.applyMapper.selectById(applyid);
                /*如果是摇号选中之后的状态，即摇号选中状态，分房状态，签约状态，则要通过摇号service更新，
                处理轮候状态的申请单*/
                if(apply.getApLc()>=3){
                    lotNumService.updateApplyInfo(applyid,atype,request);
                }else{
                    applyMapper.updateApLC(map);
                }

            }
            //申请单变更表插入记录
            ApplyChange applyChange=new ApplyChange();
            String acid=CommonUtils.getUUIDWith_();
            applyChange.setAcId(acid);
            applyChange.setAcUserid(userid);
            applyChange.setAcSqbh(sqbh);
            applyChange.setAcType("8");
            long currentTime = System.currentTimeMillis() + 5000;
            applyChange.setAcTime(new Date(currentTime));
            applyChange.setAcApplyType(atype);
            applyChange.setAcRemark(acRemark);
            applyChange.setAcSqid(applyid);
            applyChangeMapper.insert(applyChange);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }



    }

    public List<Approve> findJSFForm(Map map){
        return this.appoveMapper.findJSFForm(map);
    }

    public List<Map> findChangePersons(Map map){
//        PageHelper.startPage(Integer.parseInt((String) map.get("page")),
//                Integer.parseInt((String) map.get("rows")));
        List<Map> list= appoveMapper.findChangePersons(map);
        return list;
    }
    public int findChangePersonsCount(Map map){
        return appoveMapper.findChangePersonsCount(map);
    }

    /**
     * 第一次自动发送短信后，定时下一年发送
     * @param map
     * @return
     */
    public Object sentMessageAfterFirstYear(Map map) throws SchedulerException, ParseException {
        ContractTemplateFillingDataPojo pojo= (ContractTemplateFillingDataPojo) map.get("pojo");
        UserInfo userInfo=(UserInfo) map.get("userInfo");
        String sendYear= (String) map.get("sendYear");//新的合同到期年份

        pojo.setYearEnd(sendYear);

        //下一次合同到期时间
        String contractEndDay=pojo.getYearEnd()+"-"
                              +pojo.getMonthEnd()+"-"
                              +pojo.getDayEnd();


        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        //格式化终止日期
        Date formatdate = format1.parse(contractEndDay);


        Map<String,Object> mapCon=new HashMap<String, Object>();
        mapCon.put("piSetcode","22");
        mapCon.put("piItemcode",pojo.getApSqlb());
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
        String time= SendPhoneCodeUtil.getCron(sendTime);
        String job_name=pojo.getObjId();


        ScheduleJobDomain scheduleJob = new ScheduleJobDomain();
        scheduleJob.setJobGroup("group1");
        scheduleJob.setJobName(job_name);
        scheduleJob.setQuartz(time);
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("contractTemplateFillingDataPojo", pojo);
        conditionMap.put("userinfo", userInfo);
        scheduleJobService.addJob(scheduleJob, conditionMap);
        return "success";
    }


    /**
     * 条件查询待年审列表
     * @param map
     * @return
     */
    public List<Map<String,Object>> selectApplyNsMap(Map map){
        return appoveMapper.selectApplyNsMap(map);
    }

    /**
     * 根据当前用户身份证号查询初审申请记录
     * @param map
     * @return
     */
    public List<Map> findApplyByUserSfzh(Map map){
        List<Map> list= appoveMapper.findApplyByUserSfzh(map);
        return list;
    }
    /**
     * 根据当前用户身份证号查询初审申请记录总数
     * @param map
     * @return
     */
    public int findApplyByUserSfzhCount(Map map){
        return appoveMapper.findApplyByUserSfzhCount(map);
    }
    /**
     * 根据根据流程实例id关联查询
     * @param map
     * @return
     */
    public Approve findByProcessInstanceId(Map map){
        return appoveMapper.findByProcessInstanceId(map);
    }


    /**
     * 根据根据流程实例id关联查询年审
     * @param map
     * @return
     */
    public Approve findNSByProcessInstanceId(Map map){
        return appoveMapper.findNSByProcessInstanceId(map);
    }

    public Approve h5FindPerApply(Map map){
        return appoveMapper.h5FindPerApply(map);
    }


    public Approve h5FindPerAudio(Map map){
        return appoveMapper.h5FindPerAudio(map);
    }

    /**
     * 判断有无重复数据
     * @param map
     * @return
     */
    public Integer selectForDeWeighting(Map map){
        return appoveMapper.selectForDeWeighting(map);
    }
}
