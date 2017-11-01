package com.snolf.system.model;

import com.snolf.base.BaseEntity;
/**
 * 权限资源表
 * @author wangjunjie
 * @date 2017/9/4 14:28
 */
public class SysAuthority extends BaseEntity{
    private Integer id;
    /**
     *权限名称
     */
    private String authName;
    /**
     *权限地址
     */
    private String url;
    /**
     *权限级别
     */
    private Integer level;
    /**
     *权限类型
     */
    private Integer type;
    /**
     * 父id
     */
    private String parentId;
    /**
     * 权限标识
     */
    private String authKey;
    /**
     * 项目id
     */
    private Integer proId;
    /**
     * 是否在菜单中显示 0：展示 1：不展示
     */
    private Integer show;

    /**
     * 排序字段
     */
    private Integer sort;


    private String remark;
    /**
     * 页面回显 项目名称
     */
    private String proName;
    /**
     * 页面回显 上级权限名称
     */
    private String parentName;

//----------------------------------------
    /**
     * 是否有子权限
     */
    private Boolean childrenAuth = Boolean.FALSE;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName == null ? null : authName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey == null ? null : authKey.trim();
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Boolean getChildrenAuth() {
        return childrenAuth;
    }

    public void setChildrenAuth(Boolean childrenAuth) {
        this.childrenAuth = childrenAuth;
    }
}