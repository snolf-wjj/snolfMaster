package com.snolf.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.system.mapper.SysAuthorityMapper;
import com.snolf.system.mapper.SysRoleAuthorityMapper;
import com.snolf.system.model.SysAuthority;
import com.snolf.system.model.SysRoleAuthority;
import com.snolf.system.service.SysAuthorityService;
import com.snolf.util.common.TreeUtilZTree;
import com.snolf.util.common.ValidateUtil;
import com.snolf.util.model.TreeNodeZTree;
import com.snolf.util.page.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SysAuthorityServiceImpl implements SysAuthorityService {

	@Resource
	private SysAuthorityMapper authorityMapper;
	@Resource
	private SysRoleAuthorityMapper roleAuthorityMapper;
	
	@Override
	public PageInfo<SysAuthority> queryList(Map<String,Object> map) throws Exception{
		int pageNum =  map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());;
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<SysAuthority> dataList = authorityMapper.queryList(map);
		for (int i = 0; i < dataList.size(); i++) {
			SysAuthority tempEntity = dataList.get(i);
			if (tempEntity.getType() == 1) {//如果是目录，取项目名称做上级权限
				dataList.get(i).setParentName(tempEntity.getProName());
			} else {// 否则就根据上级权限id查询权限数据库
				SysAuthority paramEntity = new SysAuthority();
				paramEntity.setId(Integer.parseInt(tempEntity.getParentId()));
				SysAuthority parentAuth = authorityMapper.query(paramEntity);
				dataList.get(i).setParentName(parentAuth.getAuthName());
			}
		}

		PageInfo<SysAuthority> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	@Override
	public SysAuthority query(SysAuthority paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getId(), "id不能为空");
		//校验该权限下是否有子权限
		SysAuthority resultEntity = authorityMapper.query(paramEntity);
		if (null != resultEntity) {
			Map<String,Object> map = new HashMap<>();
			map.put("parentId", paramEntity.getId());
			List<SysAuthority> checkList = authorityMapper.queryList(map);
			if (checkList.size() != 0) {
				resultEntity.setChildrenAuth(Boolean.TRUE);
			}
		}
		return resultEntity;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int update(SysAuthority paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getId(), "id不能为空");
		ValidateUtil.paramRequired(paramEntity.getAuthName(), "authName不能为空");
		ValidateUtil.paramRequired(paramEntity.getProId(), "proId不能为空");
		ValidateUtil.paramRequired(paramEntity.getType(), "type不能为空");
		ValidateUtil.paramRequired(paramEntity.getParentId(), "parentId不能为空");
		ValidateUtil.paramRequired(paramEntity.getAuthKey(), "authKey不能为空");
		// 校验权限标识是否存在
		ValidateUtil.paramValidate(checkAuthKey(paramEntity.getAuthKey(), paramEntity.getId()), "该权限标识已存在,请重新填写");
		//如果权限的proId、type、parentId有所修改则进行以下校验
		SysAuthority checkParamEntity = new SysAuthority();
		checkParamEntity.setId(paramEntity.getId());
		SysAuthority checkEntity = authorityMapper.query(checkParamEntity);
		if (paramEntity.getProId() != checkEntity.getProId() || paramEntity.getType() != checkEntity.getType() || !paramEntity.getParentId().equals(checkEntity.getParentId())) {
			ValidateUtil.paramValidate(checkAuthChildren(paramEntity.getId()), "该权限下存在子权限,无法修改其归属");
		}

		int result = authorityMapper.update(paramEntity);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int delete(String id) throws Exception {
		// 校验该权限下是否有子权限
		ValidateUtil.paramValidate(checkAuthChildren(Integer.parseInt(id)), "该权限下有子权限，请先删除子权限");
		// 校验权限是否被引用
		ValidateUtil.paramValidate(checkAuthAssign(Integer.parseInt(id)), "该权限已被引用，请先取消引用再删除");

		int result = authorityMapper.deleteById(id);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int batchDelete(String ids) throws Exception {
		String[] idsArray = ids.split(",");
		int result = authorityMapper.batchDelete(idsArray);
		return result;
	}
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int insert(SysAuthority paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getAuthName(), "authName不能为空");
		ValidateUtil.paramRequired(paramEntity.getProId(), "proId不能为空");
		ValidateUtil.paramRequired(paramEntity.getType(), "type不能为空");
		ValidateUtil.paramRequired(paramEntity.getParentId(), "parentId不能为空");
		ValidateUtil.paramRequired(paramEntity.getAuthKey(), "authKey不能为空");
		// 校验权限标识是否存在
		ValidateUtil.paramValidate(checkAuthKey(paramEntity.getAuthKey(), null), "该权限标识已存在,请重新填写");

		paramEntity.setCreateTime(new Date());
		int result = authorityMapper.insert(paramEntity);
		return result;
	}

	/**
	 * 权限选择下拉选列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SysAuthority> queryListByProject(Map<String,Object> map) throws Exception{
		Integer type = Integer.parseInt(map.get("type").toString());
		if (type > 1) {
			map.put("type", type-1);
		}
		List<SysAuthority> dataList = authorityMapper.queryList(map);
		if (null != map.get("id")) {
			Integer id = Integer.parseInt(map.get("id").toString());
			for (int i = 0; i < dataList.size(); i++) {
				if (id == dataList.get(i).getId()) {
					dataList.remove(i);
				}
			}
		}
		return dataList;
	}

	@Override
	public JSONArray queryListAll(Map<String, Object> map) throws Exception {
		ValidateUtil.paramRequired(map.get("roleId"), "roleId不能为空");
		List<SysAuthority> dataList = authorityMapper.queryList(map);
		if (dataList.size() == 0) {
			return null;
		}
		SysRoleAuthority paramEntity = new SysRoleAuthority();
		paramEntity.setRoleId(map.get("roleId").toString());
		List<SysRoleAuthority> roleAuthList = roleAuthorityMapper.queryList(paramEntity);
		List<TreeNodeZTree> tree = new ArrayList<>();
		for (SysAuthority auth : dataList) {
			TreeNodeZTree node = new TreeNodeZTree();
			node.setId(auth.getId().toString());
			node.setName(auth.getAuthName());
			node.setOpen(true);
			node.setParentId(auth.getParentId());
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("authKey", auth.getAuthKey());
			for (int i = 0; i < roleAuthList.size(); i++) {
				if (node.getId().equals(roleAuthList.get(i).getAuthId().toString())) {
					node.setChecked(true);
				}
			}
			node.setAttributes(attributes);
			tree.add(node);
		}
		String jsonStr = JSON.toJSONStringWithDateFormat(TreeUtilZTree.generateTree(tree, "0000"), "yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArray = JSONArray.parseArray(jsonStr);
		return jsonArray;
	}

	/**
	 * 校验权限标识是否存在，存在返回true
	 * @param authKey
	 * @return
	 * @throws Exception
	 */
	private boolean checkAuthKey(String authKey, Integer id) throws Exception {
		SysAuthority checkParam = new SysAuthority();
		checkParam.setAuthKey(authKey);
		SysAuthority checkEntity = authorityMapper.query(checkParam);
		if (null != checkEntity) {
			if (null != id && id == checkEntity.getId()) {
				return Boolean.FALSE;
			} else {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	/**
	 * 校验该权限下是否有子权限,存在返回true
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private boolean checkAuthChildren(Integer id) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentId", id.toString());
		List<SysAuthority> checkList = authorityMapper.queryList(paramMap);
		if (0 != checkList.size()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * 根据权限ID校验该权限是否被引用，如果被引用返回true
	 * @param authId
	 * @return
	 * @throws Exception
	 */
	private Boolean checkAuthAssign(Integer authId) throws Exception{
		SysRoleAuthority paramEntity = new SysRoleAuthority();
		paramEntity.setAuthId(authId);
		List<SysRoleAuthority> checkList = roleAuthorityMapper.queryList(paramEntity);
		if (0 != checkList.size()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}