package com.snolf.system.model;

import java.io.Serializable;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/9/27
 * Time: 16:46
 */
public class SysUserRole implements Serializable{
	private static final long serialVersionUID = 3216262482817681602L;

	private String roleId;
	private String roleKey;
	private String roleName;
	private String userId;
	private String userName;

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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
