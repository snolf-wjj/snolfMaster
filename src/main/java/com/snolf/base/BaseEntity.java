package com.snolf.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/6/19
 * Time: 11:34
 */
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -7527420199751200765L;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人
	 */
	private String createUser;

	/**
	 * 最后更新时间
	 */
	private Date updateTime;

	/**
	 * 最后更新人
	 */
	private String updateUser;

	/**
	 * 删除标记 0:正常1:删除
	 */
	private Integer del;

	/**
	 * 删除时间
	 */
	private Date delTime;

	/**
	 * 删除人
	 */
	private String delUser;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public Date getDelTime() {
		return delTime;
	}

	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}

	public String getDelUser() {
		return delUser;
	}

	public void setDelUser(String delUser) {
		this.delUser = delUser;
	}
}