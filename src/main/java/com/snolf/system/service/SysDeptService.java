package com.snolf.system.service;

import com.alibaba.fastjson.JSONArray;
import com.snolf.system.model.SysDept;
import com.snolf.util.page.PageInfo;

import java.util.Map;

public interface SysDeptService {
	/**
	 * 查询部门列表
	 * @author wangjunjie
	 * @date 2017/7/10 15:30
	 */
	PageInfo<SysDept> queryList(Map<String, Object> map) throws Exception;

	/**
	 * 生成部门树
	 * @author wangjunjie
	 * @date 2017/7/31 17:30
	 */
	JSONArray queryListAll(Map<String, Object> map) throws Exception;

	SysDept query(SysDept dept) throws Exception;
	
	int update(SysDept dept) throws Exception;
	
	int delete(String id) throws Exception;

	int batchDelete(String ids) throws Exception;
	
	int insert(SysDept dept) throws Exception;
}
