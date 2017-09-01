package com.snolf.system.service;

import com.snolf.system.model.SysRole;
import com.snolf.util.page.PageInfo;

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

	SysRole query(SysRole paramEntity) throws Exception;

	int update(SysRole paramEntity) throws Exception;

	int delete(String id) throws Exception;

	int batchDelete(String ids) throws Exception;

	int insert(SysRole paramEntity) throws Exception;

}