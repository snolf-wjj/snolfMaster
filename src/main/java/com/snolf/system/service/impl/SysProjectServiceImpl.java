package com.snolf.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.common.page.PageInfo;
import com.snolf.common.util.ValidateUtil;
import com.snolf.system.mapper.SysProjectMapper;
import com.snolf.system.model.SysProject;
import com.snolf.system.service.SysProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysProjectServiceImpl implements SysProjectService {

	@Resource
	private SysProjectMapper projectMapper;
	
	@Override
	public PageInfo<SysProject> queryList(Map<String,Object> map) throws Exception{
		int pageNum =  map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());;
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<SysProject> dataList = projectMapper.queryList(map);
		PageInfo<SysProject> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	@Override
	public List<SysProject> queryListAll(Map<String,Object> map) throws Exception{
		List<SysProject> dataList = projectMapper.queryList(map);
		return dataList;
	}

	@Override
	public SysProject query(SysProject paramData) throws Exception {
		return projectMapper.query(paramData);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(SysProject paramData) throws Exception {
		paramData.setUpdateTime(new Date());
		int result = projectMapper.update(paramData);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(String id) throws Exception {
		int result = projectMapper.deleteById(id);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int batchDelete(String ids) throws Exception {
		String[] idsArray = ids.split(",");
		int result = projectMapper.batchDelete(idsArray);
		return result;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insert(SysProject paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getName(), "name不能为空");
		ValidateUtil.paramRequired(paramEntity.getProKey(), "标识不能为空");
		ValidateUtil.paramRequired(paramEntity.getUrl(), "链接不能为空");
		ValidateUtil.paramValidate(checkEntityByProkey(paramEntity.getProKey()), "该标识已存在");

		paramEntity.setCreateTime(new Date());
		int result = projectMapper.insert(paramEntity);
		return result;
	}

	/**
	 * 校验标识是否存在
	 * @param proKey
	 * @return
	 * @throws Exception
	 */
	private Boolean checkEntityByProkey(String proKey) throws Exception{
		SysProject checkParam = new SysProject();
		checkParam.setProKey(proKey);
		SysProject checkEntity = projectMapper.query(checkParam);
		if (null != checkEntity) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}