package com.snolf.util.common;

import com.snolf.util.model.TreeNodeZTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author liuyang
 *
 */
public class TreeUtilZTree {
	
	public static List<TreeNodeZTree> generateTree(List<TreeNodeZTree> list){
		return generate(list,null,null);
	}
	public static List<TreeNodeZTree> generateTree(List<TreeNodeZTree> list,String parentID){
		return generate(list,parentID,null);
	}
	public static List<TreeNodeZTree> generateTree(List<TreeNodeZTree> list,Comparator<TreeNodeZTree> comparator){
		return generate(list,null,comparator);
	}
	public static List<TreeNodeZTree> generateTree(List<TreeNodeZTree> list,String parentID,Comparator<TreeNodeZTree> comparator){
		return generate(list,parentID,comparator);
	}
	/**
	 * 生成树
	 * @param list
	 * @return
	 */
	private static List<TreeNodeZTree> generate(List<TreeNodeZTree> list,String parentID,Comparator<TreeNodeZTree> comparator){
        // 根节点
        List<TreeNodeZTree> root = new ArrayList<TreeNodeZTree>();
        for (TreeNodeZTree node:list){
        	if(parentID==null){
	            if(node.getParentId() == null || node.getParentId().equals("")){
	            	root.add(node);
	            } else {
	            	for (TreeNodeZTree tempNode:list){
	            		if(node.getParentId().equals(tempNode.getId())){
	            			tempNode.getChildren().add(node);
	            		}
	            	}
	            }
        	}else{
        		 if(parentID.equals(node.getParentId())) {
        			 root.add(node);
 	            } else {
 	            	for (TreeNodeZTree tempNode:list){
	            		if(node.getParentId().equals(tempNode.getId())){
	            			tempNode.getChildren().add(node);
	            		}
	            	}
 	            }
        	}
        }
        if(comparator!=null){
        	Collections.sort(root, comparator);
        	for(TreeNodeZTree node:root){
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
	public static List<TreeNodeZTree> generateTree(List<TreeNodeZTree> listNode,List<TreeNodeZTree> childNodeList,String parentId){
		if(childNodeList==null)
			childNodeList = new ArrayList<TreeNodeZTree>();
		for(TreeNodeZTree node : listNode){
			if(node.getParentId().equals(parentId)){
				List<TreeNodeZTree> childChildNodeList = TreeUtilZTree.generateTree(listNode,node.getChildren(),node.getId());
				node.setChildren(childChildNodeList);
				childNodeList.add(node);
			}
		}
		return childNodeList;
	}
}