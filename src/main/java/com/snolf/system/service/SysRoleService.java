package com.snolf.system.service;

import com.alibaba.fastjson.JSONArray;
import com.snolf.common.page.PageInfo;
import com.snolf.system.model.SysRole;
import com.snolf.system.model.SysRoleAuthority;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/8/21
 * Time: 15:23
 */
public interface SysRoleService {
	/**
	 * 角色列表
	 * @author wangjunjie
	 * @date 2017/8/21 15:24
	 */
	PageInfo<SysRole> queryList(Map<String, Object> map) throws Exception;

	/**
	 * 获取所有角色，用户分配角色用
	 * @param map
	 * @return
	 * @throws Exception
	 */
	JSONArray queryListAll(Map<String, Object> map) throws Exception;

	SysRole query(SysRole paramEntity) throws Exception;

	int update(SysRole paramEntity) throws Exception;

	int delete(String id) throws Exception;

	int batchDelete(String ids) throws Exception;

	int insert(SysRole paramEntity) throws Exception;

	/**
	 * 根据角色id获取分配权限列表
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<SysRoleAuthority> queryRoleAuthList(String roleId) throws Exception;

	int assignRoleAuth(List<SysRoleAuthority> paramData, String roleId) throws Exception;

}