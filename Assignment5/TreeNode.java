/*
 * Author: Edward Gregg
 * Class: CMSC 204
 * Project: Assignment 5
 * Due Date: Nov 30, 2022
 * 
 */


public class TreeNode<T> {
	
	/*
	 * do not touch these, if you do I will throw tape
	 */
	protected TreeNode<T> leftChild, rightChild;
	private T data;
	
	/**
	 * Create a new TreeNode with left and right child set to null and data set to the dataNode
	 * 
	 * @param
	 */
	public TreeNode(T dataNode) {
		this.data = dataNode;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	/**
	 * 
	 * 
	 * @param
	 */
	public TreeNode(TreeNode<T> node) {
		this.data = node.getData();
		this.leftChild = node.leftChild;
		this.rightChild = node.rightChild;
		
	}
	
	/**
	 * Returns the data
	 * @return the data
	 * 
	 */
	public T getData() {
		return data;
	}

}