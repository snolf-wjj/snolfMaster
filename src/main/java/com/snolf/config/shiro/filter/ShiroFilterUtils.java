package com.snolf.config.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.snolf.common.util.LoggerUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Shiro Filter 工具类
 */
public class ShiroFilterUtils {
	final static Class<? extends ShiroFilterUtils> CLAZZ = ShiroFilterUtils.class;
	//登录页面
	static final String LOGIN_URL = "/system/login.html";
	//踢出登录提示
//	final static String KICKED_OUT = "/open/kickedOut.shtml";
	//没有权限提醒
	final static String UNAUTHORIZED = "/error/unauthorized.html";
	/**
	 * 是否是Ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(ServletRequest request){
		return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
	}

	/**
	 * response 输出JSON
	 * @param response
	 * @param resultMap
	 * @throws IOException
	 */
	public static void out(ServletResponse response, Map<String, String> resultMap){

		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.println(JSONObject.toJSON(resultMap).toString());
		} catch (Exception e) {
			LoggerUtils.fmtError(CLAZZ, e, "输出JSON报错。");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
}
