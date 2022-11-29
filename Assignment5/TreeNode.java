

public class TreeNode<T> {

	protected TreeNode<T> leftChild, rightChild;
	private T data;
	
	/**
	 * Create a new TreeNode with left and right child set to null and data set to the dataNode
	 * @param dataNode the data to be stored in the node
	 */
	public TreeNode(T dataNode) {
		leftChild = null;
		rightChild = null;
		data = dataNode;
	}
	
	/**
	 * Creates a new TreeNode which is a deep copy of the given parameter
	 * @param node the node to be copied
	 */
	public TreeNode(TreeNode<T> node) {
		leftChild = node.leftChild;
		rightChild = node.rightChild;
		data = node.getData();
	}
	
	/**
	 * Returns the data within the TreeNode
	 * @return the data within the node
	 */
	public T getData() {
		return data;
	}

}