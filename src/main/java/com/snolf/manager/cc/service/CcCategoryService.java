package com.snolf.manager.cc.service;

import com.alibaba.fastjson.JSONArray;
import com.snolf.common.page.PageInfo;
import com.snolf.manager.cc.model.CcCategory;

import java.util.Map;

/**
 * <p>Title: CcCategoryService.java </p>
 * <p>Description 商品分类业务接口 </p>
 * <p>Company: http://www.hnxianyi.com </p>
 * @author Wjj
 * @CreateDate 2018/3/21 14:09
 */ 
public interface CcCategoryService {

	/**
	 * <查询所有消费商用户>
	 * @Title: queryAll
	 * @Description: <功能详细描述>
	 * @author wangjunjie
	 * @date 2017年4月24日 下午4:48:10
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	PageInfo<CcCategory> queryList(Map<String, Object> map) throws Exception;

	CcCategory query(CcCategory user) throws Exception;

	int update(CcCategory user) throws Exception;

	int insert(CcCategory user) throws Exception;

	int delete(String id) throws Exception;

	/**
	 * 获取商品类目树
	 * @param map
	 * @return
	 * @throws Exception
	 */
	JSONArray queryListAll(Map<String, Object> map) throws Exception;
}
