package com.snolf.manager.cc.service;

import com.snolf.common.page.PageInfo;
import com.snolf.manager.cc.model.CcGoods;

import java.util.Map;

/**
 * <p>Title: CcGoodsService.java </p>
 * <p>Description 货源信息业务接口 </p>
 * @author Wjj
 * @CreateDate 2018/3/22 13:52
 */ 
public interface CcGoodsService {

	PageInfo<CcGoods> queryList(Map<String, Object> map) throws Exception;

	CcGoods query(CcGoods user) throws Exception;

	int update(CcGoods user) throws Exception;

	int insert(CcGoods user) throws Exception;

	int delete(String id) throws Exception;
}
