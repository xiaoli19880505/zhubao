package com.sys.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sys.common.PhoneUtil;
import com.sys.pojo.ApplyUserinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 申请端用户验证拦截器
 */
public class UserLoginHandlerInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(UserLoginHandlerInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			ApplyUserinfo user=(ApplyUserinfo) request.getSession().getAttribute("applyUserinfo");

			logger.debug("preHandle--"+user);
			System.out.println("preHandle--"+user);
			if (user==null) {
				/*判断是否是移动端的请求,如果是，则跳到*/
				boolean isPhone = PhoneUtil.JudgeIsMoblie(request);
				if(isPhone){
					response.sendRedirect(basePath+"appPath/login");
				}else{
					response.sendRedirect(basePath+"applyUserinfo/toapplogin");
				}
				return false;
			}
			return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
	}

}
