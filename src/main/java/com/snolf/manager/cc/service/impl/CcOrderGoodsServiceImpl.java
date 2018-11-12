package com.snolf.manager.cc.service.impl;

import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.common.page.PageInfo;
import com.snolf.common.util.ValidateUtil;
import com.snolf.manager.cc.mapper.CcOrderGoodsMapper;
import com.snolf.manager.cc.model.CcOrderGoods;
import com.snolf.manager.cc.service.CcOrderGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: CcOrderGoodsServiceImpl.java </p>
 * <p>Description 订单货源信息业务实现 </p>
 * @author Wjj
 * @CreateDate 2018/3/23 13:53
 */
@Service
public class CcOrderGoodsServiceImpl implements CcOrderGoodsService {

	@Resource
	public CcOrderGoodsMapper ccOrderGoodsMapper;

	@Override
	public PageInfo<CcOrderGoods> queryList(Map<String,Object> map) throws Exception{
		int pageNum = map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<CcOrderGoods> dataList = ccOrderGoodsMapper.queryList(map);

		PageInfo<CcOrderGoods> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	@Override
	public CcOrderGoods query(CcOrderGoods reqDto) throws Exception {
		return ccOrderGoodsMapper.query(reqDto);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(CcOrderGoods reqDto) throws Exception {
		ValidateUtil.paramRequired(reqDto.getId(), "ID不能为空");

		int i = ccOrderGoodsMapper.update(reqDto);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insert(CcOrderGoods reqDto) throws Exception {

		int result = ccOrderGoodsMapper.insert(reqDto);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(String id) throws Exception {
		int result = ccOrderGoodsMapper.deleteById(id);
		return result;
	}
}
