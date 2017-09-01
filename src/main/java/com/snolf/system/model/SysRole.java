package com.snolf.system.model;

import com.snolf.base.BaseEntity;

/**
 * 用户角色
 * @author wangjunjie
 * @date 2017/8/21 14:36
 */
public class SysRole extends BaseEntity{
    private String id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色标识
     */
    private String roleKey;
    /**
     * 备注
     */
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}