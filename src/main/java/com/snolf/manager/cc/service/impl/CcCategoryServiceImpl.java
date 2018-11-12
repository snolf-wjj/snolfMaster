package com.snolf.manager.cc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.common.model.TreeNodeZTree;
import com.snolf.common.page.PageInfo;
import com.snolf.common.util.TreeUtilZTree;
import com.snolf.common.util.ValidateUtil;
import com.snolf.manager.cc.mapper.CcCategoryMapper;
import com.snolf.manager.cc.model.CcCategory;
import com.snolf.manager.cc.service.CcCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: CcCategoryServiceImpl.java </p>
 * <p>Description 商品分类业务接口实现 </p>
 * @author Wjj
 * @CreateDate 2018/3/22 13:56
 */
@Service
public class CcCategoryServiceImpl implements CcCategoryService{
	@Resource
	private CcCategoryMapper ccCategoryMapper;

	@Override
	public PageInfo<CcCategory> queryList(Map<String,Object> map) throws Exception{
		int pageNum = map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<CcCategory> dataList = ccCategoryMapper.queryList(map);

		PageInfo<CcCategory> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	@Override
	public CcCategory query(CcCategory reqDto) throws Exception {
		return ccCategoryMapper.query(reqDto);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(CcCategory reqDto) throws Exception {
		ValidateUtil.paramRequired(reqDto.getId(), "ID不能为空");

		int i = ccCategoryMapper.update(reqDto);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insert(CcCategory reqDto) throws Exception {
		reqDto.setCreateTime(new Date());
		int result = ccCategoryMapper.insert(reqDto);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(String id) throws Exception {
		int result = ccCategoryMapper.deleteById(id);
		return result;
	}

	@Override
	public JSONArray queryListAll(Map<String, Object> map) throws Exception {
		List<CcCategory> dataList = ccCategoryMapper.queryList(map);
		List<TreeNodeZTree> tree = new ArrayList<>();
		if (null == map.get("goods")) {
			TreeNodeZTree superNode = new TreeNodeZTree();
			superNode.setId("0");
			superNode.setName("主栏目");
			superNode.setOpen(true);
			superNode.setParentId("0");
			tree.add(superNode);
		}

		for (CcCategory data : dataList) {
			TreeNodeZTree node = new TreeNodeZTree();
			node.setId(data.getId().toString());
			node.setName(data.getName());
			node.setOpen(true);
			node.setParentId(data.getParentId().toString());
			tree.add(node);
		}
		String jsonStr = JSON.toJSONStringWithDateFormat(TreeUtilZTree.generateTree(tree, "0"), "yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArray = JSONArray.parseArray(jsonStr);
		return jsonArray;
	}

}
