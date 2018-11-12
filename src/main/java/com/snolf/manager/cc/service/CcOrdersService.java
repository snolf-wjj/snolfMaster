package com.snolf.manager.cc.service;

import com.snolf.common.page.PageInfo;
import com.snolf.manager.cc.model.CcOrders;

import java.util.Map;

/**
 * <p>Title: CcOrdersService.java </p>
 * <p>Description 订单信息业务接口 </p>
 * @author Wjj
 * @CreateDate 2018/3/22 13:54
 */
public interface CcOrdersService {

	PageInfo<CcOrders> queryList(Map<String, Object> map) throws Exception;

	CcOrders query(CcOrders reqDto) throws Exception;

	int update(CcOrders reqDto) throws Exception;

	int insert(CcOrders reqDto) throws Exception;

	int delete(String id) throws Exception;
}
