package com.snolf.common.model;


import java.util.*;

/**
 * easyui使用的tree模型
 *
 * @author liuyang
 *
 */
public class TreeNode implements java.io.Serializable {

	private String id;
	private String parentId;
	private String text;// 树节点名称
	private String iconCls;// 前面的小图标样式
	private Boolean checked = false;// 是否勾选状态
	private Map<String, Object> attributes;// 其他参数
	private List<TreeNode> nodes = new ArrayList();// 子节点
	private String state = "open";// 是否展开(open,closed)
	private int totalRows = 0;//每个节点的叶子几点个数

	public int getTotalRows() {
		totalRows  = 0;
		if(nodes!=null&&nodes.size()!=0){//如果有孩子节点
			for(TreeNode treeNode:nodes){
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<TreeNode> getChildren() {
		return nodes;
	}

	public void setChildren(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	//节点横向排序
	public void sortChildren(Comparator<TreeNode> comparator) {
		if (nodes.size() != 0) {
			// 对本层节点进行排序（可根据不同的排序属性，传入不同的比较器）
			Collections.sort(nodes, comparator);
			// 对每个节点的下一层节点进行排序
			for (Iterator<TreeNode> it = nodes.iterator(); it.hasNext();) {
				it.next().sortChildren(comparator);
			}
		}
	}

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", parentId=" + parentId
				+ ", text=" + text + ", iconCls=" + iconCls
				+ ", checked=" + checked + ", attributes=" + attributes
				+ ", children=" + nodes + ", state=" + state + "]";
	}
}
