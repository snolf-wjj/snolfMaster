package com.snolf.base;

import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/6/19
 * Time: 18:17
 */
public interface BaseMapper<T> {
	/**
	 * 查询单个实体
	 * @Title: query
	 * @author wangjunjie
	 * @date 2017/6/19 18:27
	 * @param entity
	 * @return T
	 * @see [类、类#方法、类#成员]
	 */
	T query(T entity) throws Exception;

	/**
	 * 查询实体列表
	 * @Title: queryList
	 * @author wangjunjie
	 * @date 2017/6/19 18:28
	 * @param entity
	 * @return List
	 * @see [类、类#方法、类#成员]
	 */
	List<T> queryList(T entity) throws Exception;

	/**
	 * 查询实体列表
	 * @Title: queryList
	 * @author wangjunjie
	 * @date 2017/6/19 18:28
	 * @param map
	 * @return List
	 * @see [类、类#方法、类#成员]
	 */
	List<T> queryList(Map<String, Object> map) throws Exception;

	/**
	 * 查询实体总数
	 * @Title: queryListCount
	 * @author wangjunjie
	 * @date 2017/6/19 18:27
	 * @param entity
	 * @return int
	 * @see [类、类#方法、类#成员]
	 */
	int queryListCount(T entity) throws Exception;


	/**
	 * 添加实体
	 * @Title: insert
	 * @author wangjunjie
	 * @date 2017/6/19 18:35
	 * @see [类、类#方法、类#成员]
	 */
	int insert(T entity) throws Exception;

	/**
	 * 批量添加实体
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	int batchAdd(List<T> entity) throws Exception;

	/**
	 * 修改实体
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	int update(T entity) throws Exception;

	/**
	 * 批量更新实体
	 * @param list
	 * @return
	 * @throws Exception
	 */
	int batchUpdate(List<T> list)throws Exception;

	/**
	 * 删除实体
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	int delete(T entity)throws Exception;
	/**
	 * 根据ID删除数据
	 * @Title:
	 * @author wangjunjie
	 * @date 2017/6/21 16:44
	 * @see [类、类#方法、类#成员]
	 */
	int deleteById(String id);

	/**
	 * 批量删除实体
	 * @param array
	 * @return
	 * @throws Exception
	 */
	int batchDelete(String[] array) throws Exception;
}