package com.snolf.system.model;

import java.io.Serializable;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/9/27
 * Time: 16:46
 */
public class SysRoleAuthority implements Serializable{
	private static final long serialVersionUID = 3216262482817681602L;

	private String roleId;
	private String roleKey;
	private Integer authId;
	private String authKey;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
}
