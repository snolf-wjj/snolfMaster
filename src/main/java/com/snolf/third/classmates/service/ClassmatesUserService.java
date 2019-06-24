package com.snolf.third.classmates.service;

import com.snolf.common.page.PageInfo;
import com.snolf.system.model.SysUserRole;
import com.snolf.third.classmates.model.ClassmatesUser;
import java.util.List;
import java.util.Map;

public interface ClassmatesUserService {
	/**
	 * <查询所有用户>
	 * @Title: queryAll
	 * @Description: <功能详细描述>
	 * @author wangjunjie
	 * @date 2017年4月24日 下午4:48:10 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	PageInfo<ClassmatesUser> queryList(Map<String, Object> map) throws Exception;
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
	ClassmatesUser login(String loginName, String password) throws Exception;

	ClassmatesUser query(ClassmatesUser user) throws Exception;
	
	int update(ClassmatesUser user) throws Exception;

	int delete(Long innerCode) throws Exception;

	int insert(ClassmatesUser user) throws Exception;

	/**
	 * 根据用户id获取角色列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	List<SysUserRole> queryUserRoleList(String userId) throws Exception;
}
