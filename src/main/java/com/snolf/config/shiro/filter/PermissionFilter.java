package com.snolf.config.shiro.filter;

import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.common.util.LoggerUtils;
import com.snolf.config.WebMvcConfig;
import javax.servlet.ServletContext;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 校验权限 Filter
 * User：wangjunjie
 * Date: 2017/10/19
 * Time: 10:36
 */
public class PermissionFilter extends AccessControlFilter{

	/**
	 * 是否允许访问，返回 true 表示允许
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
	 * @throws Exception
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		LoggerUtils.debug(PermissionFilter.class, "校验URL权限");
		Subject subject = getSubject(request, response);
		// TODO 先判断带参数的权限判断
//		if(null != mappedValue){
//			String[] array = (String[])mappedValue;
//			for (String permission : array) {
//				if(subject.isPermitted(permission)){
//					return Boolean.TRUE;
//				}
//			}
//		}
		HttpServletRequest httpRequest = ((HttpServletRequest)request);

		String uri = httpRequest.getRequestURI();//获取URI
		String basePath = httpRequest.getContextPath();//获取basePath
		if(null != uri && uri.startsWith(basePath)){
			uri = uri.replace(basePath, "");
		}
		System.out.println(uri);
		if(subject.isPermitted(uri)){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * 访问拒绝时是否自己处理，如果返回 true 表示自己不处理且继续拦截器链执行，
	 * 返回 false 表示自己已经处理了
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		LoggerUtils.debug(PermissionFilter.class, "权限拦截处理");
		ServletContext application = request.getServletContext();
		if (org.apache.commons.lang3.StringUtils.isBlank((String) application.getAttribute("proUrl"))) {
			application.setAttribute("proUrl", WebMvcConfig.env_url);
		}
		HttpServletRequest hsr = (HttpServletRequest)request;
		if (hsr.getRequestURI().indexOf("login.html") > 0) {
			return true;
		}
		Subject subject = getSubject(request, response);
		if (null == subject.getPrincipal()) {//表示没有登录，重定向到登录页面
			saveRequest(request);
			WebUtils.issueRedirect(request, response, ShiroFilterUtils.LOGIN_URL);
		} else {
			if(ShiroFilterUtils.isAjax(request)){
				ResponseResult<String> result = new ResponseResult<>();
				result.setCode(SystemStatusCode.sysMsg.L_0002.getCode());
				result.setMsg("当前用户没有权限");//当前用户没有登录！
				ResponseUtil.info((HttpServletResponse)response, result);
			} else if (StringUtils.hasText(ShiroFilterUtils.UNAUTHORIZED)) {//如果有未授权页面跳转过去
				WebUtils.issueRedirect(request, response, ShiroFilterUtils.UNAUTHORIZED);
			} else {//否则返回401未授权状态码
				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		return Boolean.FALSE;
	}
}
