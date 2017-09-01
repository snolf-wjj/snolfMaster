package com.snolf.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.system.mapper.SysRoleMapper;
import com.snolf.system.model.SysRole;
import com.snolf.system.service.SysRoleService;
import com.snolf.util.common.UUIDUtil;
import com.snolf.util.common.ValidateUtil;
import com.snolf.util.page.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/8/21
 * Time: 16:17
 */
@Service
public class SysRoleServiceImpl implements SysRoleService{

	@Resource
	private SysRoleMapper roleMapper;

	@Override
	public PageInfo<SysRole> queryList(Map<String, Object> map) throws Exception {
		int pageNum = map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<SysRole> dataList = roleMapper.queryList(map);
		PageInfo<SysRole> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	@Override
	public SysRole query(SysRole paramEntity) throws Exception {
		return roleMapper.query(paramEntity);
	}

	@Override
	public int update(SysRole paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getId(), "id不能为空");
		ValidateUtil.paramRequired(paramEntity.getName(), "name不能为空");
		ValidateUtil.paramRequired(paramEntity.getRoleKey(), "roleKey不能为空");
		ValidateUtil.paramValidate(checkRoleKey(paramEntity.getRoleKey(), paramEntity.getId()), "标识已存在");

		int result = roleMapper.update(paramEntity);
		return result;
	}

	@Override
	public int delete(String id) throws Exception {
		int result = roleMapper.deleteById(id);
		return result;
	}

	@Override
	public int insert(SysRole paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getName(), "name不能为空");
		ValidateUtil.paramRequired(paramEntity.getRoleKey(), "roleKey不能为空");
		ValidateUtil.paramValidate(checkRoleKey(paramEntity.getRoleKey(), null), "标识已存在");

		paramEntity.setId(UUIDUtil.getUUID32Str());
		paramEntity.setCreateTime(new Date());
		int result = roleMapper.insert(paramEntity);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int batchDelete(String ids) throws Exception {
		String[] idsArray = ids.split(",");
		int result = roleMapper.batchDelete(idsArray);
		return result;
	}
	
	/**
	 * 校验角色标识是否存在，存在为TRUE，不存在为FALSE
	 * @author wangjunjie
	 * @date 2017/8/23 16:24
	 */
	private Boolean checkRoleKey(String roleKey, String id) throws Exception{
		SysRole paramEntity = new SysRole();
		paramEntity.setRoleKey(roleKey);
		SysRole entity = roleMapper.query(paramEntity);
		if (null != entity) {
			if (id != null && id.equals(entity.getId())) {
				return Boolean.FALSE;
			} else {
				return Boolean.TRUE;
			}
		} else {
			return Boolean.FALSE;
		}
	}
}
