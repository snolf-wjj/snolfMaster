//package com.snolf.interceptor;
//
//import com.snolf.common.Config;
//import com.snolf.common.SessionInfo;
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created with IDEA
// * User：wangjunjie
// * Date: 2017/6/20
// * Time: 10:12
// */
//@Service
//public class MyInterceptor implements HandlerInterceptor{
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		System.out.println(">>>MyInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
//		// 拦截规则
//		System.out.println(request.getRequestURL());
//		SessionInfo session = (SessionInfo) request.getSession().getAttribute(Config.getStringValue("sessionInfoName"));
//		if(null == session) {
//			response.sendRedirect(request.getContextPath() + "/system/login");
//			return false;
//		}
//		return true;
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//		System.out.println(">>>MyInterceptor>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
//
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//		System.out.println(">>>MyInterceptor>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
//	}
//}
