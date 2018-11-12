package com.snolf.manager.cc.service;

import com.snolf.common.page.PageInfo;
import com.snolf.manager.cc.model.CcShoppingCart;

import java.util.Map;

/**
 * <p>Title: CcShoppingCartService.java </p>
 * <p>Description 购物车业务接口 </p>
 * @author Wjj
 * @CreateDate 2018/3/22 13:54
 */ 
public interface CcShoppingCartService {

	PageInfo<CcShoppingCart> queryList(Map<String, Object> map) throws Exception;

	CcShoppingCart query(CcShoppingCart reqDto) throws Exception;

	int update(CcShoppingCart reqDto) throws Exception;

	int insert(CcShoppingCart reqDto) throws Exception;

	int delete(String id) throws Exception;
}
