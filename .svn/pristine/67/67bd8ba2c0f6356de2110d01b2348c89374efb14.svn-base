package com.sys.handler;

import com.sys.pojo.ApplyUserinfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;



public class SessionManageListener implements HttpSessionAttributeListener,HttpSessionListener{
	private static List<SessionAndUser> sessions;
	public static String loginFlag = "applyUserinfo";
	static {
		if (sessions == null) {
		sessions = Collections.synchronizedList(new ArrayList<SessionAndUser>());
		}
	}
	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
	}

	@Override
	public synchronized void  attributeAdded(HttpSessionBindingEvent e) {
		HttpSession session = e.getSession();
		String attrName = e.getName();
		// 登录
		if (attrName.equals(loginFlag)) {
			ApplyUserinfo nowUser = (ApplyUserinfo) e.getValue();
		// 遍历所有session
		for (int i = sessions.size()-1; i >= 0; i--) {
			SessionAndUser tem = sessions.get(i);
			if (tem.getUserId().equals(nowUser.getUserid())) {
				tem.getSession().invalidate();//自动调用remove
				break;
			}
		}
		SessionAndUser sau = new SessionAndUser();
		sau.setUserId(nowUser.getUserid());
		sau.setSession(session);
		sau.setSid(session.getId());
		sessions.add(sau);
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent e) {
		String attrName = e.getName();
		// 登录
		if (attrName.equals(loginFlag)) {
			ApplyUserinfo nowUser = (ApplyUserinfo) e.getValue();
			// 遍历所有session
			for (int i = sessions.size()-1; i >= 0; i--) {
				SessionAndUser tem = sessions.get(i);
				if (tem.getUserId().equals(nowUser.getUserid())) {
				sessions.remove(i);
				break;
				}
			}
		}

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent e) {
		HttpSession session = e.getSession();
		String attrName = e.getName();
		int delS=-1;
		// 登录
		if (attrName.equals(loginFlag)) {
			// User nowUser = (User) e.getValue();//old value
			ApplyUserinfo nowUser = (ApplyUserinfo)session.getAttribute(loginFlag);//当前session中的user
			// 遍历所有session
			for (int i = sessions.size()-1; i >= 0; i--) {
				SessionAndUser tem = sessions.get(i);
				if (tem.getUserId().equals(nowUser.getUserid())&&!tem.getSid().equals(session.getId())) {
					delS=i;
				}else if(tem.getSid().equals(session.getId())){
					tem.setUserId(nowUser.getUserid());
				}
			}
			if (delS!=-1) {
			sessions.get(delS).getSession().invalidate();//失效时自动调用了remove方法。也就会把它从sessions中移除了
			}
		}

	}

}
