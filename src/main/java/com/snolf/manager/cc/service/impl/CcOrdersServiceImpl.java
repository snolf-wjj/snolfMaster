package com.snolf.manager.cc.service.impl;

import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.common.page.PageInfo;
import com.snolf.common.util.ValidateUtil;
import com.snolf.manager.cc.mapper.CcOrdersMapper;
import com.snolf.manager.cc.model.CcOrders;
import com.snolf.manager.cc.service.CcOrdersService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: CcMemberServiceImpl.java </p>
 * <p>Description 订单信息业务实现 </p>
 * @author Wjj
 * @CreateDate 2018/3s/23 13:51
 */ 
public class CcOrdersServiceImpl implements CcOrdersService {

	@Resource
	public CcOrdersMapper ccOrdersMapper;

	@Override
	public PageInfo<CcOrders> queryList(Map<String,Object> map) throws Exception{
		int pageNum = map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<CcOrders> dataList = ccOrdersMapper.queryList(map);

		PageInfo<CcOrders> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	@Override
	public CcOrders query(CcOrders reqDto) throws Exception {
		return ccOrdersMapper.query(reqDto);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(CcOrders reqDto) throws Exception {
		ValidateUtil.paramRequired(reqDto.getId(), "ID不能为空");

		int i = ccOrdersMapper.update(reqDto);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insert(CcOrders reqDto) throws Exception {

		int result = ccOrdersMapper.insert(reqDto);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(String id) throws Exception {
		int result = ccOrdersMapper.deleteById(id);
		return result;
	}
}
