package com.snolf.system.service;

import com.snolf.common.page.PageInfo;
import com.snolf.system.model.SysProject;

import java.util.List;
import java.util.Map;

public interface SysProjectService {
	/**
	 * 查询权限列表
	 * @author wangjunjie
	 * @date 2017/9/4 14:58
	 */
	PageInfo<SysProject> queryList(Map<String, Object> map) throws Exception;

	/**
	 * 获取所有项目
	 * @param map
	 * @return
	 * @throws Exception
	 */
	List<SysProject> queryListAll(Map<String, Object> map) throws Exception;

	SysProject query(SysProject paramEntity) throws Exception;
	
	int update(SysProject dept) throws Exception;
	
	int delete(String id) throws Exception;

	int batchDelete(String ids) throws Exception;
	
	int insert(SysProject paramEntity) throws Exception;
}
