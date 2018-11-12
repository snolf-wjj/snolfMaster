package com.snolf.manager.cc.service.impl;

import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.common.page.PageInfo;
import com.snolf.common.util.ValidateUtil;
import com.snolf.manager.cc.mapper.CcShoppingCartMapper;
import com.snolf.manager.cc.model.CcShoppingCart;
import com.snolf.manager.cc.service.CcShoppingCartService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: CcMemberServiceImpl.java </p>
 * <p>Description 购物车业务实现类 </p>
 * @author Wjj
 * @CreateDate 2018/3/23 13:51
 */
public class CcShoppingCartServiceImpl implements CcShoppingCartService {

	@Resource
	public CcShoppingCartMapper ccShoppingCartMapper;

	@Override
	public PageInfo<CcShoppingCart> queryList(Map<String,Object> map) throws Exception{
		int pageNum = map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<CcShoppingCart> dataList = ccShoppingCartMapper.queryList(map);

		PageInfo<CcShoppingCart> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	@Override
	public CcShoppingCart query(CcShoppingCart reqDto) throws Exception {
		return ccShoppingCartMapper.query(reqDto);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(CcShoppingCart reqDto) throws Exception {
		ValidateUtil.paramRequired(reqDto.getId(), "ID不能为空");

		int i = ccShoppingCartMapper.update(reqDto);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insert(CcShoppingCart reqDto) throws Exception {

		int result = ccShoppingCartMapper.insert(reqDto);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(String id) throws Exception {
		int result = ccShoppingCartMapper.deleteById(id);
		return result;
	}
}
