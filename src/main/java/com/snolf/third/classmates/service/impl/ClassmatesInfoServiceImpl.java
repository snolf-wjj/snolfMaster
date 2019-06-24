package com.snolf.third.classmates.service.impl;

import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.common.page.PageInfo;
import com.snolf.common.util.GenerateUniqueCodeUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.system.mapper.SysUserRoleMapper;
import com.snolf.system.model.SysUserRole;
import com.snolf.third.classmates.mapper.ClassmatesInfoMapper;
import com.snolf.third.classmates.model.ClassmatesInfo;
import com.snolf.third.classmates.service.ClassmatesInfoService;
import com.snolf.third.classmates.service.ClassmatesInfoService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClassmatesInfoServiceImpl implements ClassmatesInfoService {

	@Resource
	private ClassmatesInfoMapper ClassmatesInfoMapper;

	@Override
	public PageInfo<ClassmatesInfo> queryList(Map<String,Object> map) throws Exception{
		int pageNum = map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<ClassmatesInfo> dataList = ClassmatesInfoMapper.queryList(map);

		PageInfo<ClassmatesInfo> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	@Override
	public ClassmatesInfo query(ClassmatesInfo paramData) throws Exception {
		return ClassmatesInfoMapper.query(paramData);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(ClassmatesInfo paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getId(), "ID不能为空");

		int i = ClassmatesInfoMapper.update(paramEntity);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(Integer id) throws Exception {
		int i = ClassmatesInfoMapper.deleteById(id);
		return i;
	}

//	@Override
	@Transactional(rollbackFor = Exception.class)
	public int batchDelete(String ids) throws Exception {
		String[] idsArray = ids.split(",");
		int result = ClassmatesInfoMapper.batchDelete(idsArray);
		return result;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insert(ClassmatesInfo paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getUserName(), "姓名不能为空");
		ValidateUtil.paramRequired(paramEntity.getPhone(), "手机号不能为空");
		ValidateUtil.paramRequired(paramEntity.getAddress(), "地址不能为空");
		ValidateUtil.paramRequired(paramEntity.getCompanyName(), "公司名不能为空");
		ValidateUtil.paramRequired(paramEntity.getIndustryType(), "从事行业不能为空");
		ValidateUtil.paramRequired(paramEntity.getQqNumber(), "QQ号不能为空");
		ValidateUtil.paramRequired(paramEntity.getWeixinNumber(), "微信号不能为空");

		paramEntity.setCreateTime(new Date());
		int result = ClassmatesInfoMapper.insert(paramEntity);
		return result;
	}


}