package com.snolf.system.service;

import com.alibaba.fastjson.JSONArray;
import com.snolf.common.page.PageInfo;
import com.snolf.system.model.SysAuthority;

import java.util.List;
import java.util.Map;

public interface SysAuthorityService {
	/**
	 * 查询权限列表
	 * @author wangjunjie
	 * @date 2017/9/4 14:58
	 */
	PageInfo<SysAuthority> queryList(Map<String, Object> map) throws Exception;

	SysAuthority query(SysAuthority paramEntity) throws Exception;
	
	int update(SysAuthority dept) throws Exception;
	
	int delete(String id) throws Exception;

	int batchDelete(String ids) throws Exception;
	
	int insert(SysAuthority paramEntity) throws Exception;

	/**
	 * 权限选择下拉选列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<SysAuthority> queryListByProject(Map<String, Object> map) throws Exception;

	/**
	 * 获取权限树
	 * @param map
	 * @return
	 * @throws Exception
	 */
	JSONArray queryListAll(Map<String, Object> map) throws Exception;

	/**
	 * 获取权限列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<SysAuthority> queryAuthList(Map<String, Object> map) throws Exception;

	/**
	 * 根据角色获取权限URL
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<String> queryUrlByRoleId(String roleId) throws Exception;
}
