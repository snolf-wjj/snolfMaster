package com.snolf.system.web.restcontroller;

import com.alibaba.fastjson.JSONArray;
import com.snolf.base.BaseController;
import com.snolf.common.contact.SystemStatusCode;
import com.snolf.common.model.SystemInfo;
import com.snolf.common.response.ResponseExceptionUtil;
import com.snolf.common.response.ResponseResult;
import com.snolf.common.response.ResponseUtil;
import com.snolf.system.model.SysUser;
import com.snolf.system.model.SysUserRole;
import com.snolf.system.service.SysAuthorityService;
import com.snolf.system.service.SysRoleService;
import com.snolf.system.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/8/28
 * Time: 17:13
 */
@RestController
@RequestMapping("system/rest/")
public class SysLoginRestController extends BaseController{
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysAuthorityService sysAuthorityService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Map<String, Object>> login(String loginName, String password, boolean rememberMe, HttpServletRequest request) {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
			token.setRememberMe(rememberMe);
			SecurityUtils.getSubject().login(token);

			SysUser userToken = (SysUser) SecurityUtils.getSubject().getPrincipal();
			/**
			 * shiro 获取登录之前的地址
			 */
			SavedRequest savedRequest = WebUtils.getSavedRequest(request);
			String url = null;
			if(null != savedRequest){
				url = savedRequest.getRequestUrl();
			}
			//如果登录之前没有地址，那么就跳转到首页。
			if(StringUtils.isBlank(url) || url.contains("login")){
				url = request.getContextPath() + "/system/index.html";
			}

			resultMap.put("url", url);
			resultMap.put("message", "登录成功");
			return ResponseUtil.success(resultMap);
		} catch (AccountException e) {
			return ResponseUtil.error(SystemStatusCode.sysMsg.L_0001.getCode(), e.getMessage());
		}catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	/**
	 * 退出登录
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value="logout",method =RequestMethod.GET)
	@ResponseBody
	public ResponseResult<String> logout(){
		try {
			SecurityUtils.getSubject().logout();
			return ResponseUtil.success("退出成功");
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	/**
	 * 获取系统信息
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value="getSystemInfo",method =RequestMethod.GET)
	@ResponseBody
	public ResponseResult<SystemInfo> getSystemInfo(HttpServletRequest request){
		try {
			return ResponseUtil.success(SystemInfo.getInstance(request));
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}

	/**
	 * 获取系统菜单信息
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value="getSystemMenu",method =RequestMethod.GET)
	@ResponseBody
	public ResponseResult<JSONArray> getSystemMenu(HttpServletRequest request){
		try {
			// 获取登录用户的信息
			SysUser userToken = (SysUser) SecurityUtils.getSubject().getPrincipal();
			// 获取角色信息
			String roleId = "";
			if (StringUtils.isNotBlank(userToken.getRoleId())) {
				roleId = userToken.getRoleId();
			} else {
				List<SysUserRole> roles = sysUserService.queryUserRoleList(userToken.getId());
				roleId = roles.get(0).getRoleId();
			}
			JSONArray authArray = sysAuthorityService.queryAuthorityByRoleId(roleId);

			return ResponseUtil.success(authArray);
		} catch (Exception e) {
			return ResponseExceptionUtil.handleException(e);
		}
	}
}
