package com.snolf.system.model;

import com.snolf.base.BaseEntity;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/7/10
 * Time: 10:50
 */
public class SysDept extends BaseEntity{
	private String id;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 联系人
	 */
	private String contactPerson;
	/**
	 * 上级部门
	 */
	private String parentId;
	/**
	 * 上级部门名称
	 */
	private String parentName;
	/**
	 * 联系电话
	 */
	private String contactPhone;
	/**
	 * 部门级别
	 */
	private Integer deptLevel;

	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Integer getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(Integer deptLevel) {
		this.deptLevel = deptLevel;
	}
}