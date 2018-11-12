package com.snolf.manager.cc.service.impl;

import com.github.pagehelper.PageHelper;
import com.snolf.common.Static;
import com.snolf.common.page.PageInfo;
import com.snolf.common.util.UUIDUtil;
import com.snolf.common.util.ValidateUtil;
import com.snolf.manager.cc.mapper.CcMemberMapper;
import com.snolf.manager.cc.model.CcMember;
import com.snolf.manager.cc.service.CcMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>Title: CcMemberServiceImpl.java </p>
 * <p>Description 会员信息业务实现类 </p>
 * @author Wjj
 * @CreateDate 2018/3/23 13:51
 */
@Service
public class CcMemberServiceImpl implements CcMemberService {

	@Resource
	public CcMemberMapper ccMemberMapper;

	@Override
	public PageInfo<CcMember> queryList(Map<String,Object> map) throws Exception{
		int pageNum = map.get("pageNum") == null ? 1 : Integer.parseInt(map.get("pageNum").toString());
		PageHelper.startPage(pageNum, Static.PAGE_SIZE);
		List<CcMember> dataList = ccMemberMapper.queryList(map);

		PageInfo<CcMember> pageInfo = new PageInfo<>(dataList);
		return pageInfo;
	}

	@Override
	public CcMember query(CcMember member) throws Exception {
		return ccMemberMapper.query(member);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(CcMember paramEntity) throws Exception {
		ValidateUtil.paramRequired(paramEntity.getId(), "ID不能为空");

		int i = ccMemberMapper.update(paramEntity);
		return i;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insert(CcMember paramEntity) throws Exception {

		paramEntity.setId(UUIDUtil.getUUID32Str());
		String recommendCode = getRecommendCode();
		// TODO 校验推荐码是否存在

		paramEntity.setRecommendCode(recommendCode);
		paramEntity.setCreateTime(new Date());
		int result = ccMemberMapper.insert(paramEntity);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delete(String id) throws Exception {
		int result = ccMemberMapper.deleteById(id);
		return result;
	}

	private String getRecommendCode() {
		String str = "";
		Random ran = new Random();
		for (int i = 0; i < 6; i++) {
			str += Static.ABCNUMBER[ran.nextInt(Static.ABCNUMBER.length)];
		}
		return str;
	}
}
