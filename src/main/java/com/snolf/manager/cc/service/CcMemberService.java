package com.snolf.manager.cc.service;

import com.snolf.common.page.PageInfo;
import com.snolf.manager.cc.model.CcMember;

import java.util.Map;

/**
 * <p>Title: CcMemberService.java </p>
 * <p>Description 会员信息业务接口 </p>
 * @author Wjj
 * @CreateDate 2018/3/22 13:53
 */
public interface CcMemberService {

	/**
	 * <查询所有消费商用户>
	 * @Title: queryAll
	 * @Description: <功能详细描述>
	 * @author wangjunjie
	 * @date 2017年4月24日 下午4:48:10
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	PageInfo<CcMember> queryList(Map<String, Object> map) throws Exception;

	CcMember query(CcMember user) throws Exception;

	int update(CcMember user) throws Exception;

	int insert(CcMember user) throws Exception;

	int delete(String id) throws Exception;
}
