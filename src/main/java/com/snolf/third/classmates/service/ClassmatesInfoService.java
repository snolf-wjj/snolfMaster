package com.snolf.third.classmates.service;

import com.snolf.common.page.PageInfo;
import com.snolf.system.model.SysUser;
import com.snolf.system.model.SysUserRole;
import com.snolf.third.classmates.model.ClassmatesInfo;
import java.util.List;
import java.util.Map;

public interface ClassmatesInfoService {
	/**
	 * 查询所有同学录信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	PageInfo<ClassmatesInfo> queryList(Map<String, Object> map) throws Exception;

	ClassmatesInfo query(ClassmatesInfo paramData) throws Exception;
	
	int update(ClassmatesInfo paramData) throws Exception;

	int insert(ClassmatesInfo paramData) throws Exception;

	int delete(Integer id) throws Exception;

}
