package com.sys.common;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

//import com.jswhzl.lease.entity.common.AuthCode;

/**
  * Http Demo for Java
  * 采用httpclient调用SendMsg接口
  * 参考文档《短信http接口文档.doc》
  */
@Service
public class SendPhoneCodeUtil {
	
	
	private static SendPhoneCodeUtil sms = null;  
	
	/** 
     *  
     * @function:获得单例 
     */  
    public static SendPhoneCodeUtil getInstance()  
    {  
        if(sms==null)  
        {  
            synchronized (SendPhoneCodeUtil.class)   
            {  
                if(sms==null) {  
                	sms = new SendPhoneCodeUtil();  
                }  
            }  
        }  
        return sms;  
    }  
	/**
	 * 短信提供商开设账号时提供一下参数:
	 * 
	 * 账号、密码、通信key、ip、端口
	 */
	static String account = "JSM41823";
	static String password = "a234sclg";
	static String veryCode = "w0i3x5o79r0r";
	static String http_url  = "http://112.74.76.186:8030";
	
	  /**
     * 
     */
    
	
	
	/**
	 * 默认字符编码集
	 */
	public static final String CHARSET_UTF8 = "UTF-8";
	
	
	
	/**
	 * 发送普通短信  普通短信发送需要人工审核
	 * @param mobile 手机号码, 多个号码以英文逗号隔开,最多支持100个号码
	 * @param content 短信内容 
	 * @return  
	 * String xml字符串，格式请参考文档说明
	 */
	public static String sendSms(String mobile,String content) throws UnsupportedEncodingException {
		String sendSmsUrl = http_url + "/service/httpService/httpInterface.do?method=sendMsg";
		StringBuilder param = new StringBuilder();
		param.append("&username=").append(account);
		param.append("&password=").append(password);
		param.append("&veryCode=").append(veryCode);
		param.append("&mobile=").append(mobile);
		param.append("&content=").append(content);
		param.append("&msgtype=").append("1");
		param.append("&code=").append("utf-8");

		StringEntity entity = new StringEntity(param.toString(),"utf-8");
		entity.setContentType("application/x-www-form-urlencoded");
		entity.setContentEncoding("UTF-8");

		String result = sendPostHttp(sendSmsUrl, entity);
		System.out.println(result);
		return result;
	}
	
	/**
	 * 模版短信,无需人工审核，直接发送
	 *   (短信模版的创建参考客户端操作手册)
	 *   模版：@1@会员，动态验证码@2@(五分钟内有效)。请注意保密，勿将验证码告知他人。
	 *   参数值:@1@=某某,@2@=4293
	 *   手机接收内容：【短信签名】某某会员，动态验证码4293(五分钟内有效)。请注意保密，勿将验证码告知他人。
	 *   注意:
		 1.发送模板短信变量值不能包含英文逗号和等号（, =）
		 2.特殊字符+ / # ?无需转义
		 3.& %需要转义
	 *	 	+   %2b  
	 *  	空格    %20  
	 * 		&   %26
	 * 		%	%25
	 * @param mobile 手机号码
	 * @param tplId 模板编号，对应客户端模版编号
	 * @param content 模板参数值，以英文逗号隔开，如：@1@=某某,@2@=4293
	 * @return xml字符串，格式请参考文档说明
	 */
	public static String sendTplSms(String mobile,String tplId,String content) throws UnsupportedEncodingException {
		String sendTplSmsUrl = http_url + "/service/httpService/httpInterface.do?method=sendMsg";
		StringBuilder param = new StringBuilder();
		param.append("&username=").append(account);
		param.append("&password=").append(password);
		param.append("&veryCode=").append(veryCode);
		param.append("&mobile=").append(mobile);
		param.append("&content=").append(content);
		param.append("&msgtype=").append("2");//2-模板短信
		param.append("&tempid=").append(tplId);//模板编号
		param.append("&code=").append("utf-8");
		StringEntity entity;
		entity = new StringEntity(param.toString(),"utf-8");
		entity.setContentType("application/x-www-form-urlencoded");
		entity.setContentEncoding("UTF-8");
		String result = sendPostHttp(sendTplSmsUrl, entity);
		return result;
	}
	
	/**
	 * 发送HttpPOST请求
	 * @param postUrl 请求地址
	 * @param entity 请求参数实体
	 * @return
	 * String
	 */
	public static String sendPostHttp(String postUrl,StringEntity entity){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String str = "";
		HttpPost method = new HttpPost(postUrl);
		method.setEntity(entity);
		try {
			HttpResponse result = httpClient.execute(method);
			if(result.getStatusLine().getStatusCode() == 200){
				str = EntityUtils.toString(result.getEntity());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
    
	/*public Boolean sendSMS(AuthCode acode) throws Exception{

		*//*	String sendTplSms=sendTplSms(acode.getPhone(),acode.getModeType(),"@1@="+acode.getCode());
			System.out.println(sendTplSms);
	        long begin = System.currentTimeMillis();  
	         boolean parse = parse(sendTplSms);  
	        long after = System.currentTimeMillis();
	        System.out.println("DOM用时"+(after-begin)+"毫秒");
			return parse; *//*
		 boolean parse=false;
		try {
			if (acode.getType()==0){
				String sendTplSms=sendTplSms(acode.getPhone(),acode.getModeType(),"@1@="+acode.getCode());
				System.out.println(sendTplSms);
			    long begin = System.currentTimeMillis();  
			      parse = parse(sendTplSms);  
			    long after = System.currentTimeMillis();
			    System.out.println("DOM用时"+(after-begin)+"毫秒");
				return parse;  
			}else if (acode.getType()==1){//住保审批发送短信
				String sendTplSms=sendTplSms(acode.getPhone(),acode.getModeType(),acode.getCode());
				System.out.println(sendTplSms);
				long begin = System.currentTimeMillis();
				parse = parse(sendTplSms);
				long after = System.currentTimeMillis();
				System.out.println("DOM用时"+(after-begin)+"毫秒");
				return parse;
			}else {
				String sendTplSms=sendTplSms(acode.getPhone(),acode.getModeType(),"@1@="+acode.getCode()+",@2@="+acode.getCode1()+",@3@="+acode.getCode2());
				System.out.println(sendTplSms);
			    long begin = System.currentTimeMillis();  
			      parse = parse(sendTplSms);  
			    long after = System.currentTimeMillis();
			    System.out.println("DOM用时"+(after-begin)+"毫秒");
				return parse;  
			}
		} catch (Exception e) {
			System.out.println("发送短信抛出异常------------------------------");
			return parse; 
		}
	}*/
  
     
   public  boolean parse(String protocolXML) {  
       try {  
            DocumentBuilderFactory factory = DocumentBuilderFactory  
                    .newInstance();  
            DocumentBuilder builder = factory.newDocumentBuilder();  
            Document doc = builder  
                    .parse(new InputSource(new StringReader(protocolXML)));
            Element element = doc.getDocumentElement();
            NodeList elementsByTagName = element.getElementsByTagName("status");
            System.out.println(elementsByTagName.item(0).getTextContent());
            if (!elementsByTagName.item(0).getTextContent().equals("0")) {
                   return false;
			}
        } catch (Exception e) {  
            System.out.println("解析时短信xml时抛出异常--------------");
        }
	return true;  
    }  
		
		
	
	
	
	
	public static void main(String[] args) throws Exception {
		
		//查询账号余额
		//System.out.println(getBalance());
		
		//发送普通短信，修改接收短信的手机号码及短信内容,多个号码以英文逗号隔开，最多支持100个号码
//		System.out.println(sendSms("18052178970", "您的验证码是8888,请注意保密，勿将验证码告知他人。"));
		
//    	System.out.println(sendTplSms("18052178970","模板编号","@1@=参数值1,@2@=参数值2"));
		/*authCode aCode = new authCode();
		String code=getFourRandom();
		String phone ="13770379801";
		aCode.setCode(code);
		aCode.setPhone(phone);
		redisClient=new RedisClientCodeUtil();
		redisClient.set(phone, aCode, aCode.getExpireSecond());
		System.out.println(redisClient.getcode("13770379801").getCode());
		System.out.println(sendTplSms("13770379801","JSM41823-0001","@1@="+code));*/
//		sendSms();
		//查询下发短信的状态报告
		//System.out.println(queryReport());
		
		//查询上行回复短信
		//System.out.println(queryMo());

		System.out.println(sendTplSms("15150041877","JSM41823-0001","@1@="+Random.ScRandom()));
   }

	public static String formatDateByPattern(Date date, String dateFormat)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String formatTimeStr = null;
		if(date != null)
		{
			formatTimeStr = sdf.format(date);
		}
		return formatTimeStr;
	}

	/***
	 * convert Date to cron ,eg. "0 06 10 15 1 ? 2014"
	 *
	 * @param date
	 *            : 时间点
	 * @return
	 */
	public static String getCron(java.util.Date date)
	{
		String dateFormat = "ss mm HH dd MM ? yyyy";
		return formatDateByPattern(date, dateFormat);
	}
	
}
