package com.snolf.manager.cc.model;

import com.snolf.base.BaseEntity;
/**
 * <p>Title: CcCategory.java </p>
 * <p>Description 商品分类 </p>
 * @author Wjj
 * @CreateDate 2018/3/19 13:41
 */
public class CcCategory extends BaseEntity{

    private Integer id;
    /**
     * 类别名称
     */
    private String name;
    /**
     * 父id
     */
    private Integer parentId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态（0:启用 1:禁用）
     */
    private int status;

	//--------------------
	private String parentName;

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
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}