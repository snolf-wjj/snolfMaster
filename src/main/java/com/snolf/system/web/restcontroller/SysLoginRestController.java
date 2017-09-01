package com.snolf.system.web.restcontroller;

import com.snolf.common.Config;
import com.snolf.common.SessionInfo;
import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.response.ResponseExceptionUtil;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.system.model.SysUser;
import com.snolf.system.service.SysUserService;
import com.snolf.util.common.IpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/8/28
 * Time: 17:13
 */
@RestController
@RequestMapping("user/")
public class SysLoginRestController {
	@Resource
	private SysUserService sysUserService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> login(String loginName, String password, HttpServletRequest request) {
		try {
			SysUser user = sysUserService.login(loginName, password);
			if (null == user) {
				return ResponseUtil.error(SystemStatusCode.sysMsg.L_0002.getCode(), "账号或密码错误");
			} else {
				//更新登录信息
				SysUser paramEntity = new SysUser();
				paramEntity.setId(user.getId());
				paramEntity.setLastLoginIp(user.getLoginIp());
				paramEntity.setLastLoginTime(user.getLoginTime());
				paramEntity.setLoginIp(IpUtil.getIpAddr(request));
				paramEntity.setLoginTime(new Date());
				sysUserService.updateLoginInfo(paramEntity);

				SessionInfo session = new SessionInfo();
				session.setLoginName(user.getLoginName());
				session.setUserName(user.getUserName());
				request.getSession().setAttribute(Config.getStringValue("sessionInfoName"), session);
				return ResponseUtil.success("登录成功");
			}
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
}
