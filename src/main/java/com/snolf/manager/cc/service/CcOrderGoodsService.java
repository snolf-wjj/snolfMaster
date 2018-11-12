package com.snolf.manager.cc.service;

import com.snolf.common.page.PageInfo;
import com.snolf.manager.cc.model.CcOrderGoods;

import java.util.Map;

/**
 * <p>Title: CcOrderGoodsService.java </p>
 * <p>Description 订单货源信息业务接口 </p>
 * @author Wjj
 * @CreateDate 2018/3/22 13:54
 */ 
public interface CcOrderGoodsService {

	PageInfo<CcOrderGoods> queryList(Map<String, Object> map) throws Exception;

	CcOrderGoods query(CcOrderGoods reqDto) throws Exception;

	int update(CcOrderGoods reqDto) throws Exception;

	int insert(CcOrderGoods reqDto) throws Exception;

	int delete(String id) throws Exception;
}
