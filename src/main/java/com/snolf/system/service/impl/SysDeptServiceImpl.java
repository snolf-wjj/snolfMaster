package com.snolf.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.common.exception.ParamMistakeException;
import com.snolf.system.mapper.SysDeptMapper;
import com.snolf.system.model.SysDept;
import com.snolf.system.service.SysDeptService;
import com.snolf.util.common.TreeUtilZTree;
import com.snolf.util.common.UUIDUtil;
import com.snolf.util.model.TreeNodeZTree;
import com.snolf.util.page.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysDeptServiceImpl implements SysDeptService {

	@Resource
	private SysDeptMapper deptMapper;
	
	@Override
	public PageInfo<SysDept> queryList(Map<String,Object> map) throws Exception{
		int pageNum =  map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());;
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<SysDept> dataList = deptMapper.queryList(map);
		PageInfo<SysDept> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	/**
	 * 生成部门树
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONArray queryListAll(Map<String, Object> map) throws Exception {
		List<SysDept> dataList = deptMapper.queryList(map);
		if (dataList.size() == 0) {
			return null;
		}
		List<TreeNodeZTree> tree = new ArrayList<>();
		for (SysDept dept : dataList) {
			if (map.get("id") != null && map.get("id").equals(dept.getId()))
				continue;
			TreeNodeZTree node = new TreeNodeZTree();
			node.setId(dept.getId());
			node.setName(dept.getDeptName());
			node.setOpen(true);
			node.setParentId(dept.getParentId());
			tree.add(node);
		}
		String jsonStr = JSON.toJSONStringWithDateFormat(TreeUtilZTree.generateTree(tree, ""), "yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArray = JSONArray.parseArray(jsonStr);
		return jsonArray;
	}

	@Override
	public SysDept query(SysDept dept) throws Exception {
		return deptMapper.query(dept);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int update(SysDept dept) throws Exception {
		int result = deptMapper.update(dept);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int delete(String id) throws Exception {
		int result = deptMapper.deleteById(id);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int batchDelete(String ids) throws Exception {
		String[] idsArray = ids.split(",");
		for (int i = 0; i < idsArray.length; i++) {
			if (idsArray[i].equals("0")) {
				throw new ParamMistakeException("管理员无法删除");
			}
		}
		int result = deptMapper.batchDelete(idsArray);
		return result;
	}
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int insert(SysDept paramEntity) throws Exception {
		paramEntity.setId(UUIDUtil.getUUID32Str());
		paramEntity.setCreateTime(new Date());
		int result = deptMapper.insert(paramEntity);
		return result;
	}

}