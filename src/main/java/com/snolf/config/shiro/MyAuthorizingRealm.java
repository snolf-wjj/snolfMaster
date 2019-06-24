package com.snolf.config.shiro;

import com.snolf.common.util.DateUtil;
import com.snolf.common.util.LoggerUtils;
import com.snolf.config.shiro.token.TokenManager;
import com.snolf.system.model.SysAuthority;
import com.snolf.system.model.SysProject;
import com.snolf.system.model.SysUser;
import com.snolf.system.model.SysUserRole;
import com.snolf.system.service.SysAuthorityService;
import com.snolf.system.service.SysProjectService;
import com.snolf.system.service.SysRoleService;
import com.snolf.system.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.*;

/**
 * 自定义身份校验核心类
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/10/10
 * Time: 18:39
 */
@Configuration
public class MyAuthorizingRealm extends AuthorizingRealm {
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysAuthorityService sysAuthorityService;
	@Resource
	private SysProjectService sysProjectService;

	@Value("${env}")
	private String env;

	@Value("${env.url}")
	private String env_url;
	/**
	 * 角色授权
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		LoggerUtils.debug(MyAuthorizingRealm.class, "权限配置-->doGetAuthorizationInfo");
		SysUser loginUser = (SysUser) principalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		try {
			if ("0".equals(loginUser.getId()) && "admin".equals(loginUser.getLoginName())) {
				// 超级账号授权
				List<SysAuthority> authList = sysAuthorityService.queryAuthList(new HashMap<String, Object>());
				Set<String> authSet = new HashSet<>();
				for (int i = 0; i < authList.size(); i++) {
					if (StringUtils.isNotBlank(authList.get(i).getUrl())) {
						authSet.add(authList.get(i).getUrl());
					}
				}
				//访问授权
				info.setStringPermissions(authSet);
				return info;
			} else {//登录账号非超级账号
				List<SysUserRole> userRoleList = sysUserService.queryUserRoleList(loginUser.getId());
				if (userRoleList.size() != 0) {
					Set<String> roleSet = new HashSet<>();
					Set<String> authSet = new HashSet<>();
					for (int i = 0; i < userRoleList.size(); i++) {
						roleSet.add(userRoleList.get(i).getRoleKey());
						List<String> authUrlList = sysAuthorityService.queryUrlByRoleId(userRoleList.get(i).getRoleId());
						for (int j = 0; j < authUrlList.size(); j++) {
							if (StringUtils.isNotBlank(authUrlList.get(j))) {
								authSet.add(authUrlList.get(j));
							}
						}
					}
					//角色角色
					info.setRoles(roleSet);
					//访问授权
					info.setStringPermissions(authSet);
					return info;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 角色认证
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		LoggerUtils.debug(MyAuthorizingRealm.class, "正在验证身份...");
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		SysUser checkUser = null;
		// 获取初始项目地址
		SysProject paramEntity_project = new SysProject();
		paramEntity_project.setProKey("master");
		SysProject project = null;
		try {
			checkUser = sysUserService.login(token.getUsername(), String.valueOf(token.getPassword()));
			project = sysProjectService.query(paramEntity_project);
		} catch (Exception e) {
			throw new UnknownAccountException("服务器异常");
		}
		//登录判断
		if (null == checkUser) {
			throw new AccountException("帐号或密码不正确！");
		} else if (1 == checkUser.getUserStatus()) {
			throw new AccountException("帐号已被禁用！");
		} else {
			Session session = TokenManager.getSession();
			if ("local".equals(env)) {
				session.setAttribute("proUrl", env_url);
			} else {
				session.setAttribute("proUrl", project.getUrl());
			}

			session.setAttribute("userId", checkUser.getId());
			session.setAttribute("userName", checkUser.getUserName());
			session.setAttribute("lastLoginIp", checkUser.getLoginIp());
			if (checkUser.getLoginTime() != null) {
				session.setAttribute("lastLoginTime", DateUtil.formatDateString(checkUser.getLoginTime(), DateUtil.DATETIME_PATTERN_19));
			}
			//更新用户登录信息
			SysUser paramEntity = new SysUser();
			paramEntity.setId(checkUser.getId());
			paramEntity.setLastLoginIp(checkUser.getLoginIp());
			paramEntity.setLastLoginTime(checkUser.getLoginTime());
			paramEntity.setLoginIp(session.getHost().equals("0:0:0:0:0:0:0:1") ? "本地" : session.getHost());
			paramEntity.setLoginTime(new Date());
			try {
				sysUserService.updateLoginInfo(paramEntity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new SimpleAuthenticationInfo(checkUser, checkUser.getPassword(), getName());
	}
}
