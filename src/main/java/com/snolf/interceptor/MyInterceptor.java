package com.snolf.interceptor;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/6/20
 * Time: 10:12
 */
@Service
public class MyInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 拦截规则
		System.out.println(request.getRequestURL());
//		SessionInfo session = (SessionInfo) request.getSession().getAttribute(Config.getStringValue("sessionInfoName"));
//		if(null == session) {
//			response.sendRedirect(request.getContextPath() + "/system/login");
//			return false;
//		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
