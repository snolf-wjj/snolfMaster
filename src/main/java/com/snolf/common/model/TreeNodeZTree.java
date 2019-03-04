package com.snolf.common.model;

import java.util.*;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/7/31
 * Time: 19:00
 */
public class TreeNodeZTree {

	private String id;
	private String parentId;
	private String name;// 树节点名称
	private String iconSkin;// 前面的小图标样式
	private Boolean checked = false;// 是否勾选状态
	private Boolean chkDisabled = false;// 设置节点的 checkbox / radio 是否禁用, [setting.check.enable = true 时有效]
	private Boolean open  = false;// 记录 treeNode 节点的 展开 / 折叠 状态
	private String url;	//节点链接的目标 URL
	private String target;	//设置点击节点后在何处打开 url,默认为 "_blank"
	private Boolean isHidden = false;	//判断 treeNode 节点是否被隐藏
	private Map<String, Object> attributes;// 其他参数
	private List<TreeNodeZTree> children = new ArrayList();// 子节点
	private int totalRows = 0;//每个节点的叶子几点个数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getChkDisabled() {
		return chkDisabled;
	}

	public void setChkDisabled(Boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Boolean getHidden() {
		return isHidden;
	}

	public void setHidden(Boolean hidden) {
		isHidden = hidden;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<TreeNodeZTree> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeZTree> children) {
		this.children = children;
	}

	public int getTotalRows() {
		totalRows  = 0;
		if(children !=null&& children.size()!=0){//如果有孩子节点
			for(TreeNodeZTree treeNode: children){
				totalRows+=treeNode.getTotalRows();
			}
		}else{//如果没有孩子节点
			totalRows = 1;
			return totalRows;
		}
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	//节点横向排序
	public void sortChildren(Comparator<TreeNodeZTree> comparator) {
		if (children.size() != 0) {
			// 对本层节点进行排序（可根据不同的排序属性，传入不同的比较器）
			Collections.sort(children, comparator);
			// 对每个节点的下一层节点进行排序
			for (Iterator<TreeNodeZTree> it = children.iterator(); it.hasNext();) {
				it.next().sortChildren(comparator);
			}
		}
	}
}
