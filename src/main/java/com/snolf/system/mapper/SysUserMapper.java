package com.snolf.system.mapper;

import com.snolf.base.BaseMapper;
import com.snolf.system.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
	/**
	 * 用户登录
	 * @author wangjunjie
	 * @date 2017/6/23 11:50
	 */
	SysUser login(String userName, String password) throws Exception;

}