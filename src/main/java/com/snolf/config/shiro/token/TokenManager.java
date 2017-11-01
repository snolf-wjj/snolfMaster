package com.snolf.config.shiro.token;

import com.snolf.system.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Shiro管理下的Token工具类
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/10/16
 * Time: 15:55
 */
public class TokenManager {

	/**
	 * 获取当前登录的用户User对象
	 * @return
	 */
	public static SysUser getToken(){
		SysUser token = (SysUser)SecurityUtils.getSubject().getPrincipal();
		return token;
	}
	/**
	 * 获取当前用户的Session
	 * @return
	 */
	public static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	/**
	 * 获取当前用户NAME
	 * @return
	 */
	public static String getNickname(){
		return getToken().getUserName();
	}
	/**
	 * 获取当前用户ID
	 * @return
	 */
	public static String getUserId(){
		return getToken()==null?null:getToken().getId();
	}
	/**
	 * 把值放入到当前登录用户的Session里
	 * @param key
	 * @param value
	 */
	public static void setVal2Session(Object key ,Object value){
		getSession().setAttribute(key, value);
	}
	/**
	 * 从当前登录用户的Session里取值
	 * @param key
	 * @return
	 */
	public static Object getVal2Session(Object key){
		return getSession().getAttribute(key);
	}
}
