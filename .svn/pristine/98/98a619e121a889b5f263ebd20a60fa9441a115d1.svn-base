package com.sys.service.common;

import com.sys.common.CommonUtils;
import com.sys.common.DatetimeUtils;
import com.sys.common.SendPhoneCodeUtil;
import com.sys.mapper.ApplyUserInfoFormMapper;
import com.sys.pojo.ApplyUserInfoForm;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @Desc:desc
 * @Author:wangli
 * @CreateTime:12:18 2018/10/26
 */
@Service
public class MessageAndFormService {

    @Autowired
    private ApplyUserInfoFormMapper applyUserInfoFormMapper;

    /**
     * 发送短信和插入通知
     *
     * @param messageContent 信息内容
     * @param userid         发送的用户id
     * @param tel            发送的用户手机号
     */
    public void addMessageAndFormService(String messageContent, String userid, String tel) {
        try {
            /*向申请人发送短信通知*/
            SendPhoneCodeUtil.sendTplSms(tel, "JSM41823-0043", "@1@=" + messageContent);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /*向申请用户发送通知信息，插入数据*/
        ApplyUserInfoForm applyUserInfoForm = new ApplyUserInfoForm();
        applyUserInfoForm.setUiFid(CommonUtils.getUUIDWith_());//主键id
        applyUserInfoForm.setUifRead("0");//设为未读
        applyUserInfoForm.setUifUserid(userid);//设置申请用户id
        applyUserInfoForm.setUifBt(messageContent);//设置短信内容
        applyUserInfoForm.setUiFxfrq(DatetimeUtils.date2string(new Date(), "yyyy-MM-dd"));//设置发送日期
        applyUserInfoFormMapper.insert(applyUserInfoForm);//插入数据
    }


    /**
     * 发送通知
     *
     * @param messageContent 信息内容
     * @param userid         发送的用户id
     */
    public void addInformService(String messageContent, String userid) {

        /*向申请用户发送通知信息，插入数据*/
        ApplyUserInfoForm applyUserInfoForm = new ApplyUserInfoForm();
        applyUserInfoForm.setUiFid(CommonUtils.getUUIDWith_());//主键id
        applyUserInfoForm.setUifRead("0");//设为未读
        applyUserInfoForm.setUifUserid(userid);//设置申请用户id
        applyUserInfoForm.setUifBt(messageContent);//设置短信内容
        applyUserInfoForm.setUiFxfrq(DatetimeUtils.date2string(new Date(), "yyyy-MM-dd"));//设置发送日期
        applyUserInfoFormMapper.insert(applyUserInfoForm);//插入数据
    }


    /**
     * 发送短信公共接口
     * @param tel //申请人电话
     * @param tplid //申请人模板编号
     * @param map //接口所带的参数信息，存于map中
     */
    public void sendMessage(String tel,String tplid,LinkedMap<String, String> map){
        /*获取信息模板的参数*/
        Iterator it = map.entrySet().iterator();
        String content =    "";
        Map<String,Object> putongMap = new HashMap<String, Object>();
        StringBuffer contentBuffer = new StringBuffer();
        int pos = 0;
        /*拼接参数信息*/
        while (it.hasNext()) {
            Map.Entry<String,String> entity = (Map.Entry<String,String>)it.next();
           /*如果是第一个则不用加前缀&符号，否则要加上*/
            if(pos==0){
                contentBuffer.append(entity.getKey()).append("=").append(entity.getValue());
            }else{
                /*如果参数中带上了‘=’字符，
                则不用再拼接=进入字符中;用于处理同样的key在模板中出现两次的情况*/
                if(entity.getKey().indexOf("=")!=-1){
                    contentBuffer.append(",").append(entity.getKey()).append(entity.getValue());
                }else{
                    contentBuffer.append(",").append(entity.getKey()).append("=").append(entity.getValue());
                }
            }
            pos++;
        }

        System.out.println("contentBuffer:"+contentBuffer.toString()+"--tel:"+tel);
        try {
            /*向申请人发送短信通知*/
            SendPhoneCodeUtil.sendTplSms(tel, tplid, contentBuffer.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}