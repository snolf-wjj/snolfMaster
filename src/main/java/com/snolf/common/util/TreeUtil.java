package com.snolf.common.util;

import com.snolf.common.model.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author liuyang
 *
 */
public class TreeUtil {
	
	public static List<TreeNode> generateTree(List<TreeNode> list){
		return generate(list,null,null);
	}
	public static List<TreeNode> generateTree(List<TreeNode> list,String parentID){
		return generate(list,parentID,null);
	}
	public static List<TreeNode> generateTree(List<TreeNode> list,Comparator<TreeNode> comparator){
		return generate(list,null,comparator);
	}
	public static List<TreeNode> generateTree(List<TreeNode> list,String parentID,Comparator<TreeNode> comparator){
		return generate(list,parentID,comparator);
	}
	/**
	 * 生成树
	 * @param list
	 * @return
	 */
	private static List<TreeNode> generate(List<TreeNode> list,String parentID,Comparator<TreeNode> comparator){
        // 根节点
        List<TreeNode> root = new ArrayList<TreeNode>();
        for (TreeNode node:list){
        	if(parentID==null){
	            if(node.getParentId() == null || node.getParentId().equals("")){
	            	root.add(node);
	            } else {
	            	for (TreeNode tempNode:list){
	            		if(node.getParentId().equals(tempNode.getId())){
	            			tempNode.getChildren().add(node);
	            		}
	            	}
	            }
        	}else{
        		 if(parentID.equals(node.getParentId())) {
        			 root.add(node);
 	            } else {
 	            	for (TreeNode tempNode:list){
	            		if(node.getParentId().equals(tempNode.getId())){
	            			tempNode.getChildren().add(node);
	            		}
	            	}
 	            }
        	}
        }
        if(comparator!=null){
        	Collections.sort(root, comparator);
        	for(TreeNode node:root){
        		node.sortChildren(comparator);
        	}
        }
        return root;
	}
	
	/**
	 * 过时的
	 * 递归生成树  效率较慢 不适合层级过多
	 * @param listNode
	 * @param childNodeList
	 * @param parentId
	 * @return
	 */
	public static List<TreeNode> generateTree(List<TreeNode> listNode,List<TreeNode> childNodeList,String parentId){
		if(childNodeList==null)
			childNodeList = new ArrayList<TreeNode>();
		for(TreeNode node : listNode){
			if(node.getParentId().equals(parentId)){
				List<TreeNode> childChildNodeList = TreeUtil.generateTree(listNode,node.getChildren(),node.getId());
				node.setChildren(childChildNodeList);
				childNodeList.add(node);
			}
		}
		return childNodeList;
	}
}