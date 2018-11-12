package com.snolf.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.common.model.TreeNodeZTree;
import com.snolf.common.page.PageInfo;
import com.snolf.common.util.TreeUtilZTree;
import com.snolf.common.util.UUIDUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.system.mapper.SysRoleAuthorityMapper;
import com.snolf.system.mapper.SysRoleMapper;
import com.snolf.system.mapper.SysUserRoleMapper;
import com.snolf.system.model.SysRole;
import com.snolf.system.model.SysRoleAuthority;
import com.snolf.system.model.SysUserRole;
import com.snolf.system.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

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
	@Resource
	private SysRoleAuthorityMapper roleAuthorityMapper;
	@Resource
	private SysUserRoleMapper userRoleMapper;

	@Override
	public PageInfo<SysRole> queryList(Map<String, Object> map) throws Exception {
		int pageNum = map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<SysRole> dataList = roleMapper.queryList(map);
		PageInfo<SysRole> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	@Override
	public JSONArray queryListAll(Map<String, Object> map) throws Exception {
		ValidateUtil.paramRequired(map.get("userId"), "userId不能为空");
		List<SysRole> dataList = roleMapper.queryList(map);
		if (dataList.size() == 0) {
			return null;
		}
		SysUserRole paramEntity = new SysUserRole();
		paramEntity.setUserId(map.get("userId").toString());
		List<SysUserRole> userRoleList = userRoleMapper.queryList(paramEntity);
		List<TreeNodeZTree> tree = new ArrayList<>();
		for (SysRole role : dataList) {
			TreeNodeZTree node = new TreeNodeZTree();
			node.setId(role.getId());
			node.setName(role.getName());
			node.setOpen(true);
			node.setParentId("");
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("roleKey", role.getRoleKey());
			for (int i = 0; i < userRoleList.size(); i++) {
				if (node.getId().equals(userRoleList.get(i).getRoleId())) {
					node.setChecked(true);
				}
			}
			node.setAttributes(attributes);
			tree.add(node);
		}
		String jsonStr = JSON.toJSONStringWithDateFormat(TreeUtilZTree.generateTree(tree, ""), "yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArray = JSONArray.parseArray(jsonStr);
		return jsonArray;
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
		ValidateUtil.paramRequired(id, "id不能为空");
		ValidateUtil.businessValidate(checkRoleUser(id), "该角色下存在用户，请先解除关联");
		int result = roleMapper.deleteById(id);
		//删除角色下的权限
		SysRoleAuthority paramEntity = new SysRoleAuthority();
		paramEntity.setRoleId(id);
		roleAuthorityMapper.delete(paramEntity);
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
	@Transactional(rollbackFor = Exception.class)
	public int batchDelete(String ids) throws Exception {
		String[] idsArray = ids.split(",");
		int result = roleMapper.batchDelete(idsArray);
		for (int i = 0; i < idsArray.length; i++) {
			ValidateUtil.businessValidate(checkRoleUser(idsArray[i]), "选择的角色下存在用户，请先解除关联");
			//删除角色下的权限
			SysRoleAuthority paramEntity = new SysRoleAuthority();
			paramEntity.setRoleId(idsArray[i]);
			roleAuthorityMapper.delete(paramEntity);
		}
		return result;
	}

	@Override
	public List<SysRoleAuthority> queryRoleAuthList(String roleId) throws Exception {
		SysRoleAuthority paramEntity = new SysRoleAuthority();
		paramEntity.setRoleId(roleId);
		List<SysRoleAuthority> result = roleAuthorityMapper.queryList(paramEntity);
		return result;
	}

	/**
	 * 给角色分配权限
	 * @param paramData
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int assignRoleAuth(List<SysRoleAuthority> paramData, String roleId) throws Exception {
		ValidateUtil.paramRequired(roleId, "角色ID不能为空！");
		SysRoleAuthority data = new SysRoleAuthority();
		data.setRoleId(roleId);
		roleAuthorityMapper.delete(data);

		int count = 0;
		if (paramData.size() != 0) {
			for (SysRoleAuthority paramEntity : paramData) {
				ValidateUtil.paramRequired(paramEntity.getRoleId(), "roleId不能为空");
				ValidateUtil.paramRequired(paramEntity.getRoleKey(), "roleKey不能为空");
				ValidateUtil.paramRequired(paramEntity.getAuthId(), "authId不能为空");
				ValidateUtil.paramRequired(paramEntity.getAuthKey(), "authKey不能为空");
				count += roleAuthorityMapper.insert(paramEntity);
			}
		}

		return count;
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

	/**
	 * 根据角色id校验改角色下是否有用户
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	private Boolean checkRoleUser(String roleId) throws Exception {
		SysUserRole paramEntity = new SysUserRole();
		paramEntity.setRoleId(roleId);
		List<SysUserRole> checkList = userRoleMapper.queryList(paramEntity);
		if (checkList.size() > 0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
