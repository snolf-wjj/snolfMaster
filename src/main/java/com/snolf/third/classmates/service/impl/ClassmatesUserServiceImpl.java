package com.snolf.third.classmates.service.impl;

import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.common.page.PageInfo;
import com.snolf.common.util.GenerateUniqueCodeUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.system.mapper.SysUserRoleMapper;
import com.snolf.system.model.SysUserRole;
import com.snolf.third.classmates.mapper.ClassmatesUserMapper;
import com.snolf.third.classmates.model.ClassmatesUser;
import com.snolf.third.classmates.service.ClassmatesUserService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClassmatesUserServiceImpl implements ClassmatesUserService {

	@Resource
	private ClassmatesUserMapper classmatesUserMapper;
	@Resource
	private SysUserRoleMapper userRoleMapper;
	
	@Override
	public PageInfo<ClassmatesUser> queryList(Map<String,Object> map) throws Exception{
		int pageNum = map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<ClassmatesUser> dataList = classmatesUserMapper.queryList(map);

		PageInfo<ClassmatesUser> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	@Override
	public ClassmatesUser login(String loginName, String password) throws Exception {
		ValidateUtil.paramRequired(loginName, "账户不能为空");
		ValidateUtil.paramRequired(password, "密码不能为空");

		return classmatesUserMapper.login(loginName, password);
	}

	@Override
	public ClassmatesUser query(ClassmatesUser user) throws Exception {
		ClassmatesUser resultEntity = classmatesUserMapper.query(user);
		return resultEntity;
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(ClassmatesUser paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getId(), "ID不能为空");
		ValidateUtil.paramRequired(paramEntity.getLoginName(), "登录名不能为空");
		ValidateUtil.paramRequired(paramEntity.getPassword(), "密码不能为空");
		ValidateUtil.paramValidate(checkLoginName(paramEntity.getLoginName()), "该账户已存在");

		int i = classmatesUserMapper.update(paramEntity);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(Long innerCode) throws Exception {
		int i = classmatesUserMapper.deleteByInnerCode(innerCode);
		if (i > 0) {
			SysUserRole paramEntity = new SysUserRole();
			paramEntity.setUserId(innerCode+"");
			userRoleMapper.delete(paramEntity);
		}
		return i;
	}

//	@Override
	@Transactional(rollbackFor = Exception.class)
	public int batchDelete(String ids) throws Exception {
		String[] idsArray = ids.split(",");
		int result = classmatesUserMapper.batchDelete(idsArray);
		if (result > 0) {
			// 删除用户角色表所对应的数据
			for (int i = 0; i < idsArray.length; i++) {
				SysUserRole paramEntity = new SysUserRole();
				paramEntity.setUserId(idsArray[i]);
				userRoleMapper.delete(paramEntity);
			}
		}
		return result;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insert(ClassmatesUser paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getLoginName(), "登录名不能为空");
		ValidateUtil.paramRequired(paramEntity.getPassword(), "密码不能为空");
		ValidateUtil.paramValidate(checkLoginName(paramEntity.getLoginName()), "该账户已存在");

		paramEntity.setInnerCode(GenerateUniqueCodeUtil.unique18NumId());
		paramEntity.setCreateTime(new Date());
		int result = classmatesUserMapper.insert(paramEntity);
		return result;
	}

	public int assignUserRole(List<SysUserRole> paramData, String userId) throws Exception {
		ValidateUtil.paramRequired(userId, "用户ID不能为空！");
		SysUserRole data = new SysUserRole();
		data.setUserId(userId);
		userRoleMapper.delete(data);

		int count = 0;
		if (paramData.size() != 0) {
			for (SysUserRole paramEntity : paramData) {
				ValidateUtil.paramRequired(paramEntity.getRoleId(), "roleId不能为空");
				ValidateUtil.paramRequired(paramEntity.getRoleKey(), "roleKey不能为空");
				ValidateUtil.paramRequired(paramEntity.getUserId(), "userId不能为空");
				ValidateUtil.paramRequired(paramEntity.getUserName(), "userName不能为空");
				count += userRoleMapper.insert(paramEntity);
			}
		}

		return count;
	}

	@Override
	public List<SysUserRole> queryUserRoleList(String userId) throws Exception {
		SysUserRole paramEntity = new SysUserRole();
		paramEntity.setUserId(userId);
		List<SysUserRole> result = userRoleMapper.queryList(paramEntity);
		return result;
	}

	/**
	 * 根据登录名校验用户是否存在,存在为TURE,不存在未FALSE
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	private Boolean checkLoginName(String loginName) throws Exception {
		ClassmatesUser paramEntity = new ClassmatesUser();
		paramEntity.setLoginName(loginName);
		ClassmatesUser checkUser = classmatesUserMapper.query(paramEntity);
		if (null == checkUser) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}

}