import java.util.ArrayList;


public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	private TreeNode<String> root;
	
	/**
	 * Default constructor. Builds the tree
	 */
	public MorseCodeTree() {
		buildTree();
	}

	@Override
	/**
	 * Returns a reference to the root of the tree
	 * @return reference to root
	 */
	public TreeNode<String> getRoot() {
		return root;
	}

	@Override
	/**
	 * Sets the root of the tree
	 * @param newNode a copy of this TreeNode will be the new root
	 */
	public void setRoot(TreeNode<String> newNode) {
		root = new TreeNode(newNode);	
	}
	
	
	@Override
	/**
	 * Morse code tree built starting from a single "." or "-"
	 */
	public void buildTree() {
		root = new TreeNode("");
		/*
		 *                 root
		 *                  /\              level 1
		 *               /\    /\           level 2
		 *             /\  /\  /\ /\        level 3
		 *          /\  /\/\  /\  /\ /\ /\  level 4 
		 */
		//level 1
		insert(".", "e");
		insert("-", "t");
		
		//level 2
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		
		//level 3
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		
		//level 4
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");

	}

	@Override
	/**
	 * Calls the recursive method addNode to add the element in the right position
	 * @param
	 * @param
	 * @return
	 */
	public void insert(String code, String letter) {
		addNode(root, code, letter);
	}

	@Override
	/**
	 * Recursive method to add the a new element in the right position based on code
	 * 
	 * '.' means to traverse to left, '-' means to traverse to right
	 * @param root the root of the tree for this recursive instance of addNode
	 * @param code the code for this recursive instance of addNode
	 * @param letter the data of the TreeNode to be added
	 * 
	 * found on geeksforgeeks
	 */
	public void addNode(TreeNode<String> root, String code, String letter) {
		if (code.length()==1) {
			if (code.equals(".")){
				root.leftChild = new TreeNode(letter);
			}else if (code.contentEquals("-")) {
				root.rightChild = new TreeNode(letter);
			}
			
		}else {
			if (code.charAt(0)=='.') {
				root = root.leftChild;
			}else if (code.charAt(0)=='-') {
				root = root.rightChild;
			}
			code = code.substring(1);
			addNode(root, code, letter);
		}
	}

	@Override
	/**
	 * Fetches the data in the tree based on the given code
	 * @param code the code that describes the traversals to retrieve the data
	 * @return the string that corresponds to the code
	 */
	public String fetch(String code) {
		return fetchNode(root, code);
	}

	@Override
	/**
	 * Recursive method to fetch an element from the right position based on the code
	 * 
	 * '.' means to traverse to left, '-' means to traverse to right
	 * @param root the root of the tree for this recursive instance of fetchNode
	 * @param code the code for this recursive instance of fetchNode 
	 * @return the string that corresponds to the code
	 */
	public String fetchNode(TreeNode<String> root, String code) {
		if (code.length()==1) {
			if (code.equals(".")){
				return root.leftChild.getData();
			}else {
				return root.rightChild.getData();
			}
		}else {
			if (code.startsWith(".")) {
				root = root.leftChild;
			}else if (code.startsWith("-")) {
				root = root.rightChild;
			}
			code = code.substring(1);
			return fetchNode(root, code);
		}
	}

	@Override
	/**
	 * This operation isn't supported by the MorseCodeTree
	 * 
	 */
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	/**
	 * This operation isn't supported by the MorseCodeTree
	 */
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}



	@Override
	/**
	 * 
	 */
	public ArrayList<String> toArrayList() {
		ArrayList<String> outputTest = new ArrayList<String>(26);
		LNRoutputTraversal(root, outputTest);
		return outputTest;
	}

	@Override
	/**
	 *
	 */
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root == null) {
			return;
		}
		if (root.leftChild != null) {
			LNRoutputTraversal(root.leftChild, list);
		}
		list.add(root.getData());
		
		LNRoutputTraversal(root.rightChild, list);
	}

}