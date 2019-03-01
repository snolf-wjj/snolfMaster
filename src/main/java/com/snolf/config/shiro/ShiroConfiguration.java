package com.snolf.config.shiro;

import com.snolf.common.util.LoggerUtils;
import com.snolf.config.shiro.filter.PermissionFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/10/10
 * Time: 18:09
 */
@Configuration
public class ShiroConfiguration {
	public static Logger log = LoggerFactory.getLogger(ShiroConfiguration.class);

	/**
	 * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
	 * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
	 * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 密码匹配凭证管理器
	 * HashedCredentialsMatcher，这个类是为了对密码进行编码的，
	 * 防止密码在数据库里明码保存，当然在登陆认证的时候，
	 * 这个类也负责对form里输入的密码进行编码。
	 */
	@Bean(name = "hashedCredentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		credentialsMatcher.setHashIterations(2);
		credentialsMatcher.setStoredCredentialsHexEncoded(true);
		return credentialsMatcher;
	}

	/**
	 * MyAuthorizingRealm，这是个自定义的认证类，继承自AuthorizingRealm，
	 * 负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
	 */
	@Bean(name = "authRealm")
	@DependsOn("lifecycleBeanPostProcessor")
	public MyAuthorizingRealm authRealm() {
		MyAuthorizingRealm realm = new MyAuthorizingRealm();
//        realm.setCredentialsMatcher(hashedCredentialsMatcher());
		return realm;
	}

	/**
     * EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，
     * 然后每次用户请求时，放入用户的session中，
	 * 如果不设置这个bean，每个请求都会查询一次数据库。
     */
    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager() {
        return new EhCacheManager();
    }

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager() {
		LoggerUtils.debug(ShiroConfiguration.class, "=========>securityManager()");
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(authRealm());
		//设置缓存
		securityManager.setCacheManager(ehCacheManager());
		// 设置rememberMe管理器
//		securityManager.setRememberMeManager(cookieRememberMeManager);
		return securityManager;
	}

	/**
	 * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
	 * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		LoggerUtils.debug(ShiroConfiguration.class, "=========>shiroFilter过滤器");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		/**
		* anon（匿名）  org.apache.shiro.web.filter.authc.AnonymousFilter
		* authc（身份验证）       org.apache.shiro.web.filter.authc.FormAuthenticationFilter
		* authcBasic（http基本验证）    org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
		* logout（退出）        org.apache.shiro.web.filter.authc.LogoutFilter
		* noSessionCreation（不创建session） org.apache.shiro.web.filter.session.NoSessionCreationFilter
		* perms(许可验证)  org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
		* port（端口验证）   org.apache.shiro.web.filter.authz.PortFilter
		* rest  (rest方面)  org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
		* roles（权限验证）  org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
		* ssl （ssl方面）   org.apache.shiro.web.filter.authz.SslFilter
		* member （用户方面）  org.apache.shiro.web.filter.authc.UserFilter
		* user  表示用户不一定已通过认证,只要曾被Shiro记住过登录状态的用户就可以正常发起请求,比如rememberMe
		*/

		Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
		filterMap.put("myPerms", new PermissionFilter());
		shiroFilterFactoryBean.setFilters(filterMap);

		shiroFilterFactoryBean.setSecurityManager(securityManager());

		Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();
		//静态资源
		filterChainDefinitionManager.put("/common/**", "anon");
		filterChainDefinitionManager.put("/h-ui/**", "anon");
		filterChainDefinitionManager.put("/h-ui.admin/**", "anon");
		filterChainDefinitionManager.put("/lib/**", "anon");
		filterChainDefinitionManager.put("/skin/**", "anon");
		filterChainDefinitionManager.put("/favicon.ico", "anon");

//		filterChainDefinitionManager.put("/system/login.html", "anon");	//标识可以匿名访问
		filterChainDefinitionManager.put("/system/rest/login", "anon");
		filterChainDefinitionManager.put("/error/**", "anon");
		filterChainDefinitionManager.put("/system/index.html", "authc");
		filterChainDefinitionManager.put("/system/welcome.html", "authc");
		filterChainDefinitionManager.put("/system/rest/logout", "authc");
//		try {
//			List<SysAuthority> authorityList = sysAuthorityService.queryAuthList(new HashedMap());
//			//TODO
//			for (int i = 0; i < authorityList.size(); i++) {
//				filterChainDefinitionManager.put(authorityList.get(i).getUrl(), "perms[" + authorityList.get(i).getUrl() + "]");
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/system/login.html");
//		shiroFilterFactoryBean.setUnauthorizedUrl("/error/404.html");
		filterChainDefinitionManager.put("/**", "authc,myPerms");//标识需要认证后访问

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
		return shiroFilterFactoryBean;
	}

	/**
	 *  开启shiro aop注解支持.
	 *  使用代理方式;所以需要开启代码支持;
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}
}
