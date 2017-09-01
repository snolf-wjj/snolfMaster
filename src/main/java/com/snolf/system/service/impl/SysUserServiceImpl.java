package com.snolf.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.system.mapper.SysUserMapper;
import com.snolf.system.model.SysUser;
import com.snolf.system.service.SysUserService;
import com.snolf.util.common.UUIDUtil;
import com.snolf.util.common.ValidateUtil;
import com.snolf.util.page.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserMapper userMapper;
	
	@Override
	public PageInfo<SysUser> queryList(Map<String,Object> map) throws Exception{
		int pageNum = map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<SysUser> dataList = userMapper.queryList(map);
		PageInfo<SysUser> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	@Override
	public SysUser login(String loginName, String password) throws Exception {
		ValidateUtil.paramRequired(loginName, "账户不能为空");
		ValidateUtil.paramRequired(password, "密码不能为空");

		return userMapper.login(loginName, password);
	}

	@Override
	public SysUser query(SysUser user) throws Exception {
		return userMapper.query(user);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int updateStatus(SysUser paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getId(), "ID不能为空");
		ValidateUtil.paramRequired(paramEntity.getUserStatus(), "状态值不能为空");
		ValidateUtil.paramValidate(0 != paramEntity.getUserStatus() && 1 != paramEntity.getUserStatus(), "状态值错误");

		int i = userMapper.update(paramEntity);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int update(SysUser paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getId(), "ID不能为空");
		ValidateUtil.paramRequired(paramEntity.getLoginName(), "登录名不能为空");
		ValidateUtil.paramRequired(paramEntity.getPassword(), "密码不能为空");
		ValidateUtil.paramValidate(checkLoginName(paramEntity.getLoginName()), "该账户已存在");

		int i = userMapper.update(paramEntity);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int updateLoginInfo(SysUser paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getId(), "ID不能为空");

		int i = userMapper.update(paramEntity);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int delete(String id) throws Exception {
		int i = userMapper.deleteById(id);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int batchDelete(String ids) throws Exception {
		String[] idsArray = ids.split(",");
		int result = userMapper.batchDelete(idsArray);
		return result;
	}
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int insert(SysUser paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getLoginName(), "登录名不能为空");
		ValidateUtil.paramRequired(paramEntity.getPassword(), "密码不能为空");
		ValidateUtil.paramValidate(checkLoginName(paramEntity.getLoginName()), "该账户已存在");

		paramEntity.setId(UUIDUtil.getUUID32Str());
		paramEntity.setCreateTime(new Date());
		int result = userMapper.insert(paramEntity);
		return result;
	}

	/**
	 * 根据登录名校验用户是否存在,存在为TURE,不存在未FALSE
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	private Boolean checkLoginName(String loginName) throws Exception {
		SysUser paramEntity = new SysUser();
		paramEntity.setLoginName(loginName);
		SysUser checkUser = userMapper.query(paramEntity);
		if (null == checkUser) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}

}