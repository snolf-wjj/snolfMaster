package com.snolf.system.service;

import com.snolf.common.page.PageInfo;
import com.snolf.system.model.SysUser;
import com.snolf.system.model.SysUserRole;

import java.util.List;
import java.util.Map;

public interface SysUserService {
	/**
	 * <查询所有用户>
	 * @Title: queryAll
	 * @Description: <功能详细描述>
	 * @author wangjunjie
	 * @date 2017年4月24日 下午4:48:10 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	PageInfo<SysUser> queryList(Map<String, Object> map) throws Exception;
	/**
	 * <用户登录>
	 * @Title: query
	 * @Description: <功能详细描述>
	 * @author wangjunjie
	 * @date 2017年4月25日 下午4:17:47 
	 * @param loginName
	 * @param password
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	SysUser login(String loginName, String password) throws Exception;

	SysUser query(SysUser user) throws Exception;
	
	int update(SysUser user) throws Exception;

	int updatePassword(String oldPassword, String newPassword) throws Exception;
	/**
	 * 更新用户登录信息
	 * @author wangjunjie
	 * @date 2017/8/31 17:08
	 */
	int updateLoginInfo(SysUser user) throws Exception;
	/**
	 * 更新用户状态
	 * @author wangjunjie
	 * @date 2017/8/28 16:04
	 */
	int updateStatus(SysUser user) throws Exception;

	int delete(String id) throws Exception;

	int batchDelete(String ids) throws Exception;
	
	int insert(SysUser user) throws Exception;

	/**
	 * 给用户分配角色
	 * @param paramData
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	int assignUserRole(List<SysUserRole> paramData, String userId) throws Exception;

	/**
	 * 根据用户id获取角色列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<SysUserRole> queryUserRoleList(String userId) throws Exception;
}
