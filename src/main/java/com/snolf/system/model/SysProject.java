package com.snolf.system.model;

import com.snolf.base.BaseEntity;

/**
 * 项目类
 * @author wangjunjie
 * @date 2017/9/5 15:54
 */
public class SysProject extends BaseEntity{

	private Integer id;

	private String name;

	private String proKey;

	private String url;
	/**
	 * 项目状态 0：正常 1：停用 2：维护
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getProKey() {
		return proKey;
	}

	public void setProKey(String proKey) {
		this.proKey = proKey == null ? null : proKey.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
