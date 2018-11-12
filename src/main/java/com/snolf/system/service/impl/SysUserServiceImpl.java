package com.snolf.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.common.page.PageInfo;
import com.snolf.common.util.UUIDUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.config.shiro.token.TokenManager;
import com.snolf.system.mapper.SysUserMapper;
import com.snolf.system.mapper.SysUserRoleMapper;
import com.snolf.system.model.SysUser;
import com.snolf.system.model.SysUserRole;
import com.snolf.system.service.SysUserService;
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
	@Resource
	private SysUserRoleMapper userRoleMapper;
	
	@Override
	public PageInfo<SysUser> queryList(Map<String,Object> map) throws Exception{
		int pageNum = map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<SysUser> dataList = userMapper.queryList(map);

		SysUserRole paramEntity = new SysUserRole();
		List<SysUserRole> userRoleDataList = userRoleMapper.queryList(paramEntity);
		for (int i = 0; i < dataList.size(); i++) {
			for (int j = 0; j < userRoleDataList.size(); j++) {
				if (dataList.get(i).getId().equals(userRoleDataList.get(j).getUserId())) {
					dataList.get(i).setRoleName(userRoleDataList.get(j).getRoleName());
				}
			}
		}
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
		SysUser resultEntity = userMapper.query(user);
		//获取用户角色信息
		SysUserRole paramData = new SysUserRole();
		paramData.setUserId(resultEntity.getId());
		List<SysUserRole> resultList = userRoleMapper.queryList(paramData);
		String roleNames = "";
		for (int i = 0; i < resultList.size(); i++) {
			roleNames += resultList.get(i).getRoleName();
			roleNames += ",";
		}
		if (roleNames.length() > 0) {
			resultEntity.setRoleName(roleNames.substring(0, roleNames.length()-1));
		}
		return resultEntity;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateStatus(SysUser paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getId(), "ID不能为空");
		ValidateUtil.paramRequired(paramEntity.getUserStatus(), "状态值不能为空");
		ValidateUtil.paramValidate(0 != paramEntity.getUserStatus() && 1 != paramEntity.getUserStatus(), "状态值错误");

		int i = userMapper.update(paramEntity);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(SysUser paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getId(), "ID不能为空");
		ValidateUtil.paramRequired(paramEntity.getLoginName(), "登录名不能为空");
		ValidateUtil.paramRequired(paramEntity.getPassword(), "密码不能为空");
		ValidateUtil.paramValidate(checkLoginName(paramEntity.getLoginName()), "该账户已存在");

		int i = userMapper.update(paramEntity);
		return i;
	}

	/**
	 * 修改用户密码
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updatePassword(String oldPassword, String newPassword) throws Exception {
		ValidateUtil.paramRequired(oldPassword, "旧密码不能为空");
		ValidateUtil.paramRequired(newPassword, "新密码不能为空");
		SysUser userToken = TokenManager.getToken();

		SysUser paramEntity = new SysUser();
		paramEntity.setPassword(newPassword);
		paramEntity.setId(userToken.getId());

		int i = userMapper.update(paramEntity);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateLoginInfo(SysUser paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getId(), "ID不能为空");

		int i = userMapper.update(paramEntity);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(String id) throws Exception {
		int i = userMapper.deleteById(id);
		if (i > 0) {
			SysUserRole paramEntity = new SysUserRole();
			paramEntity.setUserId(id);
			userRoleMapper.delete(paramEntity);
		}
		return i;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int batchDelete(String ids) throws Exception {
		String[] idsArray = ids.split(",");
		int result = userMapper.batchDelete(idsArray);
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
	public int insert(SysUser paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getLoginName(), "登录名不能为空");
		ValidateUtil.paramRequired(paramEntity.getPassword(), "密码不能为空");
		ValidateUtil.paramValidate(checkLoginName(paramEntity.getLoginName()), "该账户已存在");

		paramEntity.setId(UUIDUtil.getUUID32Str());
		paramEntity.setCreateTime(new Date());
		int result = userMapper.insert(paramEntity);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
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