package com.snolf.manager.cc.service.impl;

import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.common.page.PageInfo;
import com.snolf.common.util.UUIDUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.manager.cc.mapper.CcGoodsMapper;
import com.snolf.manager.cc.model.CcGoods;
import com.snolf.manager.cc.service.CcGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: CcGoodsServiceImpl.java </p>
 * <p>Description 货源信息业务接口实现 </p>
 * @author Wjj
 * @CreateDate 2018/3/23 11:34
 */
@Service
public class CcGoodsServiceImpl implements CcGoodsService {

	@Resource
	public CcGoodsMapper ccGoodsMapper;

	@Override
	public PageInfo<CcGoods> queryList(Map<String,Object> map) throws Exception{
		int pageNum = map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<CcGoods> dataList = ccGoodsMapper.queryList(map);

		PageInfo<CcGoods> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	@Override
	public CcGoods query(CcGoods reqDto) throws Exception {
		return ccGoodsMapper.query(reqDto);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(CcGoods reqDto) throws Exception {
		ValidateUtil.paramRequired(reqDto.getId(), "ID不能为空");

		int i = ccGoodsMapper.update(reqDto);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insert(CcGoods reqDto) throws Exception {

		reqDto.setGoodsNum(UUIDUtil.getUUID32Str());
		reqDto.setCreateTime(new Date());
		int result = ccGoodsMapper.insert(reqDto);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(String id) throws Exception {
		int result = ccGoodsMapper.deleteById(id);
		return result;
	}

}
