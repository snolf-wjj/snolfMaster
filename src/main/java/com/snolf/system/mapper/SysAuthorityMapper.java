package com.snolf.system.mapper;

import com.snolf.base.BaseMapper;
import com.snolf.system.model.SysAuthority;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysAuthorityMapper extends BaseMapper<SysAuthority> {

	List<String> queryUrlByRoleId(String roleId) throws Exception;

	List<SysAuthority> queryAuthorityByRoleId(String roleId) throws Exception;
}