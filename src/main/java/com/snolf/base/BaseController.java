package com.snolf.base;

import com.snolf.system.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/6/19
 * Time: 11:34
 */
public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected Map<String, Object> resultMap = new LinkedHashMap<>();

	/**
	 * 获取request参数，并转为 Map<String, Object>
	 * @author wangjunjie
	 * @date 2017/8/23 17:40
	 */
	protected Map<String, Object> getParameters(HttpServletRequest request) {
		Map<String, Object> m = new HashMap<>();
		Enumeration<?> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			Object men = en.nextElement();
			if (StringUtils.isNotBlank(request.getParameter(men.toString()).trim())) {
				m.put(men.toString(), request.getParameter(men.toString()).trim());
			}
		}
		return m;
	}

	protected String getLoginUserName() {
		SysUser userToken = (SysUser) SecurityUtils.getSubject().getPrincipal();
		return userToken.getUserName();
	}

}
