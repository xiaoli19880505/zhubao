package com.sys.service.schedule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sys.common.CommonUtils;
import com.sys.common.PropertiesUtil;
import com.sys.common.dataconver.BaseInfoDataConvertor;
import com.sys.pojo.Message;
import com.sys.pojo.UserInfo;
import com.sys.pojo.apply.ContractTemplateFillingDataPojo;
import com.sys.service.MessageService;
import com.sys.service.apply.ApproveService;
import com.sys.service.common.MessageAndFormService;
import org.apache.commons.collections4.map.LinkedMap;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;


public class MIMSJob implements Job{
	//private final static Logger log = Logger.getLogger(MIMSJob.class);

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		LinkedMap<String,String> map = new LinkedMap<String, String>();
		Map<String,Object> map2 = new HashMap<String, Object>();
		MessageAndFormService messageAndFormService=(MessageAndFormService)arg0.getJobDetail().getJobDataMap().get("messageAndFormService");
		ApproveService approveService=(ApproveService)arg0.getJobDetail().getJobDataMap().get("approveService");

		MessageService messageService=(MessageService)arg0.getJobDetail().getJobDataMap().get("messageService");
		Map<String, Object> conditionMap = (Map<String, Object>) arg0.getJobDetail().getJobDataMap().get("conditionMap");
		ContractTemplateFillingDataPojo pojo= (ContractTemplateFillingDataPojo) conditionMap.get("contractTemplateFillingDataPojo");
		UserInfo userInfo = (UserInfo)conditionMap.get("userinfo");

		List<Message> msgAddList = Lists.newArrayList();
		List<Message> msgupdateList = Lists.newArrayList();

		/**
		 * @1@同志，您好！您所承租的公租房@2@，
		 * 合同期限将于@3@到期，请您收到提示短信后，
		 * 请及时登录徐州市住保系统，办理年度审核申请业务。
		 * 谢谢！审核人：@4@徐州市住房保障服务中心
		 *
		 * @1@: 主申请人姓名，例如张三；
		 @2@：公租房地址，例如：xxx小区XX楼XX单元XX室[由业务人员前台选择具体小区、楼、单元和室号]；
		 @3@：年月日，例如2018年11月9日[由业务人员选择]；
		 @4@：审核人姓名。
		 1申请业务被驳回时发送短信   2原件复审通知短信
		 3原件审核通过    4住保选房、交余款、签合同、上房通知短信(用于摇号)
		 40住保选房、交余款、签合同、上房通知短信(用于通知签约)
		 5公租房租赁补贴终审通过通知    6新就业、外来务工公租房终审通过通知
		 7公租房年审通知    8公租房年审通过通知  9公租房年审未通过通知
		 10租赁补贴年审通知    11补贴年审通过   12补贴年审驳回 '
		 */
		if(pojo.getApSqlb().equals(PropertiesUtil.getApplyTypeProperties("gongzf"))
				||pojo.getApSqlb().equals(PropertiesUtil.getApplyTypeProperties("wailaiwg"))
				||pojo.getApSqlb().equals(PropertiesUtil.getApplyTypeProperties("xinjy"))){
			String tplid="JSM41823-0031";//模板编码
			map.put("@1@",pojo.getUserName());
			map.put("@2@", pojo.getCommunity()+pojo.getBuilding()+pojo.getUnit()+pojo.getRoom());
			map.put("@3@",pojo.getYearEnd()+"-"+pojo.getMonthEnd()+"-"+pojo.getDayEnd());
			map.put("@4@",userInfo.getLinktel());
			map.put("@5@",userInfo.getUsername());
			messageAndFormService.sendMessage(pojo.getMobilePhone(),tplid,map);
			/**
			 * 插入短信记录 Message表
			 */
			Map<String,Object> mseeageMap = Maps.newHashMap();
			mseeageMap.put("sqbh",pojo.getObjSqbh());
			mseeageMap.put("msType","7");
			if(messageService.selectCountByMap(conditionMap)>0){
				Message message = new Message();
				message.setSqbh(pojo.getObjSqbh());
				message.setMstype("7");
				message.setMstime(new Date());
				msgupdateList.add(message);
			}else{
				Message message = new Message();
				message.setMsuserid(userInfo.getUserid());
				message.setAppType(pojo.getApSqlb());
				message.setLinktel(pojo.getApSqlb());
				message.setSqbh(pojo.getObjSqbh());
				message.setMsid(CommonUtils.getUUIDWith_());
				message.setMstime(new Date());
				message.setMstem(tplid);
				message.setMstype("7");
				message.setToUsername(pojo.getUserName());
				msgAddList.add(message);
			}


		}else if(pojo.getApSqlb().equals(PropertiesUtil.getApplyTypeProperties("butie"))){
			/**
			 * @1@同志，您好！
			 * 您所享受的公共租赁住房租赁补贴，
			 * 合同期限将于@2@到期，请您收到提示短信后，
			 * 请及时登录徐州市住保系统，办理年度审核申请业务。
			 * 谢谢！审核人: @3@，徐州市住房保障服务中心
			说明：
			 @1@: 主申请人姓名，例如张三；
			 @2@：具体年份，例如2018，使用时间插件选择；
			 @3@：审核人姓名。
			 */
			String tplid="JSM41823-0032";//模板编码
			map.put("@1@",pojo.getUserName());
			map.put("@2@",pojo.getYearEnd()+"-"+pojo.getMonthEnd()+"-"+pojo.getDayEnd());
			map.put("@3@",userInfo.getUsername());
			map.put("@4@",userInfo.getLinktel());
			messageAndFormService.sendMessage(pojo.getMobilePhone(),tplid,map);

			/**
			 * 插入短信记录 Message表
			 */
			Map<String,Object> mseeageMap = Maps.newHashMap();
			mseeageMap.put("sqbh",pojo.getObjSqbh());
			mseeageMap.put("msType","10");
			if(messageService.selectCountByMap(conditionMap)>0){
				Message message = new Message();
				message.setSqbh(pojo.getObjSqbh());
				message.setMstype("10");
				message.setMstime(new Date());
				msgupdateList.add(message);
			}else{
				Message message = new Message();
				message.setMsuserid(userInfo.getUserid());
				message.setAppType(pojo.getApSqlb());
				message.setLinktel(pojo.getMobilePhone());
				message.setSqbh(pojo.getObjSqbh());
				message.setMsid(CommonUtils.getUUIDWith_());
				message.setMstime(new Date());
				message.setMstem(tplid);
				message.setMstype("10");
				message.setToUsername(pojo.getUserName());
				msgAddList.add(message);
			}
		}
		messageService.updateAndAddMessageBatch(msgAddList,msgupdateList);

		/*try {

			map2.put("pojo",pojo);
			map2.put("userInfo",userInfo);
			//当前系统时间加一年是下次要发送的时间
			//合同到期时间是发送时间延迟30天

			Date date=new Date();
			Calendar theCa = Calendar.getInstance();
			theCa.setTime(date);
			theCa.add(Calendar.YEAR, 1);

			DateFormat format1 = new SimpleDateFormat("yyyy");
			map2.put("sendYear",format1.format(theCa.getTime()));
			approveService.sentMessageAfterFirstYear(map2);

		} catch (Exception e) {
			e.printStackTrace();
		}*/

	}
}
