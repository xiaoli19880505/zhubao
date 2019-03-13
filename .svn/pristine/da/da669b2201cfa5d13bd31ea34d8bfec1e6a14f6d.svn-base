/**
 * 
 */
package com.sys.shiro;


import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.shiro.crypto.hash.Md5Hash;

import net.sf.json.JSONObject;


/**  
* Copyright (C),2017, Guangzhou sys info. Co., Ltd.
* @ClassName: ShiroUtils  
* @Description: shiro封装工具类 
* @author lao  
* @date 2017年12月20日下午2:25:46  
* @version 1.00 
*/
public class ShiroUtils {
	protected final static Logger logger = LogManager.getLogger(ShiroUtils.class);

	
    /**
    * @Description: 加盐
    * @param password
    * @return
    * @throws  
    * @author lao
    * @Date 2017年12月26日上午9:59:53
    * @version 1.00
     */
    public static String getStrByMD5(String password){
    	return new Md5Hash(password,"3.14159").toString();
    }

    
    
	/**
	* @Description: 是否是Ajax请求
	* @param request
	* @return
	* @throws  
	* @author lao
	* @Date 2018年1月15日下午4:45:31
	* @version 1.00
	 */
	public static boolean isAjax(ServletRequest request){
		return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
	}
	
	/**
	* @Description: response 输出JSON
	* @param servletResponse
	* @param resultMap
	* @throws IOException
	* @author lao
	* @Date 2018年1月15日下午4:46:16
	* @version 1.00
	 */
	public static void out(ServletResponse servletResponse, Map<String, String> resultMap){
		
		PrintWriter out = null;
		try {
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.setCharacterEncoding("UTF-8");
			//在响应头设置session状态
			response.setHeader("session-status", "timeout");
			out = response.getWriter();
			out.println(JSONObject.fromObject(resultMap).toString());
		} catch (Exception e) {
			logger.debug(e);
			logger.info("输出JSON报错!");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}  
    
    
    
}
