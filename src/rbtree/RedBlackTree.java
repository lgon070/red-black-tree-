package rbtree;

import java.util.ArrayList;




public class RedBlackTree<E extends Comparable<E>> {
	
	public RBNode<E> root;
	private RBNode<E> NIL = new RBNode<E>();
	/**
	 * Default empty constructor for a BST.
	 */
	public RedBlackTree() {
		root = null;
	}
	/**
	 * Constructor that creates BST out of an array.
	 * @param userArray array inputed by user.
	 */
	public RedBlackTree(E[] userArray) {
		
		root = null;
		for(int i = 0; i < userArray.length; i++) {
			insert(userArray[i]);
		}
	}
	/**
	 * Method that inserts new key into BST following the BST rules.
	 * @param key data inserted by user.
	 */
	public void insert(E key){
		RBNode<E> child = new RBNode<E>(key);
		child.left = NIL;
		child.right = NIL;
		child.parent = NIL;
		
		if(isEmpty()) {
			root = child;
			insertCleanup(root);
		}
		else {
			RBNode<E> parent = insertionPoint(key);
			if(key.compareTo(parent.getData()) <= -1) {
				parent.left = child;
				child.parent = parent;
				insertCleanup(child);
				
			}
			else if(key.compareTo(parent.getData()) >= 1) {
				parent.right = child;
				child.parent = parent;
				insertCleanup(child);
			}
		}
		
	}
	/**
	 * Private method that works with insertKey to find the insertion point of where the key belongs.
	 * @param key data inserted by user
	 * @return insertion point of key
	 * @throws DuplicateItemException if another node contains the data of key
	 */
	private  RBNode<E> insertionPoint(E key) throws DuplicateItemException{
		RBNode<E> current = root;
		RBNode<E> parent = NIL;
		
		while(!current.equals(NIL)) {
			if(key.compareTo(current.getData()) == 0) {
				throw new DuplicateItemException("Duplicate Item: " + key);
			}
			else if(key.compareTo(current.getData()) <= -1) {
				parent = current;
				current = current.left;
			}
			else if(key.compareTo(current.getData()) >= 1) {
				parent = current;
				current = current.right;
			}
		}
		return parent;
		
	}
	/**
	 * Method that cleans up an insertion to maintain the balance of a RB tree.
	 * @param node node needed to be cleaned.
	 */
	private void insertCleanup(RBNode<E> node) {
		//CASE 1
		if(root.getColor().equals("R")) {
			//System.out.println("CASE 1 for Node: " + node.getData());
			root.setColor("B");
			return;
		}
		//CASE 2
		if(node.parent.getColor().equals("B")) {
			//System.out.println("CASE 2 for Node: " + node.getData());
			return;
		}
		//CASE 3
		if(node.parent.getColor().equals("R")) {
			if(uncle(node).equals(NIL)) {
			
			}
			else if(uncle(node).getColor().equals("R")) {
				//System.out.println("CASE 3 for Node: " + node.getData());
				node.parent.setColor("B");
				uncle(node).setColor("B");
				grandparent(node).setColor("R");
				insertCleanup(grandparent(node));
				return;
			}
		}
		//CASE 4
		if(node.parent.getColor().equals("R") && (uncle(node).equals(NIL) || uncle(node).getColor().equals("B"))) {
			
			if(isRightChild(node) && isLeftChild(node.parent)) {
			//	System.out.println("CASE 4A for Node: " +node.getData());
				node = node.parent;
				leftRotate(node, node.right);
				
			}
			else if(isLeftChild(node) && isRightChild(node.parent)) {
				//System.out.println("CASE 4B for Node: " +node.getData());
				node = node.parent;
				rightRotate(node, node.left);
			}
			
			
		}
		//CASE 5
		if(node.parent.getColor().equals("R") && (uncle(node).equals(NIL) || uncle(node).getColor().equals("B"))) {
		
			if(isLeftChild(node) && isLeftChild(node.parent)) {
			//	System.out.println("CASE 5A for Node: " + node.getData());
				node.parent.setColor("B");
				grandparent(node).setColor("R");
				rightRotate(grandparent(node), node.parent);
				return;
			}
			else if(isRightChild(node) && isRightChild(node.parent)) {
			//	System.out.println("CASE 5B for Node: " + node.getData());
				node.parent.setColor("B");
				grandparent(node).setColor("R");
				leftRotate(grandparent(node), node.parent);
				return;
			}
		}
		
	}
	/**
	 * Left rotates a node using a pivot and root.
	 * @param root root of rotation
	 * @param pivot pivot of rotation
	 */
	private void leftRotate(RBNode<E> root, RBNode<E> pivot) {
		root.right = pivot.left;
		if(!pivot.left.equals(NIL)) {
			pivot.left.parent = root;
		}
		pivot.parent = root.parent;
		if(isLeftChild(root)) {
			root.parent.left = pivot;
		}
		else if(isRightChild(root)) {
			root.parent.right = pivot;
		}
		else if(this.root.getData().compareTo(root.getData())==0) {
			this.root = pivot;
		}
		root.parent = pivot;
		pivot.left = root;
		
		
	}
	/**
	 * Right rotates a node using a pivot and root.
	 * @param root root of rotation
	 * @param pivot pivot of rotation
	 */
	private void rightRotate(RBNode<E> root, RBNode<E> pivot) {
		root.left = pivot.right;
		if(!pivot.right.equals(NIL)) {
			pivot.right.parent = root;
		}
		pivot.parent = root.parent;
		if(isLeftChild(root)) {
			root.parent.left = pivot;
		}
		else if(isRightChild(root)) {
			root.parent.right = pivot;
		}
		else if(this.root.getData().compareTo(root.getData())==0) {
			this.root = pivot;
		}
		root.parent = pivot;
		pivot.right = root;
		
		
	}
	/**
	 * Method that calls the executeOrder66 method to delete a node.
	 * @param key data that will be deleted.
	 */
	public void delete(E key) {	
		executeOrder66(nodeToDelete(key));
	}
	/**
	 * Deletes a node from the RB tree.
	 * @param node node selected for termination.
	 */
	private void executeOrder66(RBNode<E> node) {
		if(isLeaf(node)) {
			if(isLeftChild(node)) {
				node.parent.left = NIL;
			}
			else if(isRightChild(node)) {
				node.parent.right = NIL;
			}
			NIL.parent = node.parent;		
			if(node.getColor().equals("B")) {
				NIL.setColor("DB");
				fixDoubleBlack(NIL);
			}
		}
		else if(numOfChildren(node) == 1) {
			RBNode<E> child = null;
			if(node.left.equals(NIL)) {
				child = node.right;
			}
			else if( node.right.equals(NIL)) {
				child = node.left;
			}
			
			if(isLeftChild(node)) {
				child.parent = node.parent;
				node.parent.left = child;
			}
			else if(isRightChild(node)) {
				child.parent = node.parent;
				node.parent.right = child;
			}
			
			if(child.getColor().equals("R") || node.getColor().equals("R")) {
				child.setColor("B");
			}
			else if(child.getColor().equals("B") && node.getColor().equals("B")) {
				child.setColor("DB");
				fixDoubleBlack(child);
			}
			
		}
		else if(numOfChildren(node) == 2) {
			RBNode<E> max = maxSubTree(node.left);
			node.setData(max.getData());
			executeOrder66(max);
		
		}
		
	}
	/**
	 * Fixes any double black nodes to maintain RB tree balance.
	 * @param node node to be fixed.
	 */
	public void fixDoubleBlack(RBNode<E> node) {
		//CASE 1
		if(root.getColor().equals("DB")) {
		
			root.setColor("B");
			return;
		}
		//CASE 2
		if(sibling(node).getColor().equals("R")) {
			
			if(isRightChild(node)) {
				sibling(node).setColor("B");
				node.parent.setColor("R");
				rightRotate(node.parent, sibling(node));
				fixDoubleBlack(node);
			}
			else if(isLeftChild(node)) {
				sibling(node).setColor("B");
				node.parent.setColor("R");
				leftRotate(node.parent, sibling(node));
				fixDoubleBlack(node);
			}
		}
		//CASE 3
		if((sibling(node).left.getColor().equals("R")) || (sibling(node).right.equals("R"))) {
			
			if(isLeftChild(sibling(node))) {
				if(sibling(node).right.getColor().equals("R")) {
					leftRotate(sibling(node), sibling(node).right);
					rightRotate(node.parent, sibling(node));
					grandparent(node).setColor(node.parent.getColor());
					uncle(node).setColor("B");
					node.parent.setColor("B");
					node.setColor("B");
					return;
				}
				else if(sibling(node).left.getColor().equals("R")) {
					rightRotate(node.parent, sibling(node));
					grandparent(node).setColor(node.parent.getColor());
					uncle(node).setColor("B");
					node.parent.setColor("B");
					node.setColor("B");
					return;
				}
			}
			else if(isRightChild(sibling(node))) {
				if(sibling(node).left.getColor().equals("R")) {
					rightRotate(sibling(node), sibling(node).left);
					leftRotate(node.parent, sibling(node));
					grandparent(node).setColor(node.parent.getColor());
					uncle(node).setColor("B");
					node.parent.setColor("B");
					node.setColor("B");
					return;
				}
				else if(sibling(node).right.getColor().equals("R")){
					leftRotate(node.parent, sibling(node));
					grandparent(node).setColor(node.parent.getColor());
					uncle(node).setColor("B");
					node.parent.setColor("B");
					node.setColor("B");
					return;
				}
			}
		}
		//CASE 4
		if(sibling(node).getColor().equals("B") && (
		  (sibling(node).left.getColor().equals("DB") || sibling(node).left.getColor().equals("B") ) && 
		  (sibling(node).right.getColor().equals("DB") || sibling(node).right.getColor().equals("B") ))) {
			
			
			if(node.parent.getColor().equals("R")) {
				sibling(node).setColor("R");
				node.parent.setColor("B");
				node.setColor("B");
				return;
			}
			else if(node.parent.getColor().equals("B")) {
				sibling(node).setColor("R");
				node.parent.setColor("DB");
				node.setColor("B");
				fixDoubleBlack(node.parent);
			}
		}
		
	}
	/**
	 * Private method used for finding the max node of a subtree.
	 * @param node starting point of subtree
	 * @return max node of subtree
	 */
	private RBNode<E> maxSubTree(RBNode<E> node){
		RBNode<E> max = node;
		while(!max.right.equals(NIL)) {
			max = max.right;
		}
		return max;
	}
	/**
	 * Return true if it finds the key given. False otherwise.
	 * @param key given Key to find.
	 * @return true or false depending if the key was found.
	 */
	public Boolean find(E key) {
		if(isEmpty()) {
			return false;
		}
		RBNode<E> current = root;
		while(!current.equals(NIL)) {
			if(key.compareTo(current.getData()) == 0) {
				return true;
			}
			else if(key.compareTo(current.getData()) <= -1) {
				current = current.left;
			}
			else if(key.compareTo(current.getData()) >= 1) {
				current = current.right;
			}
		}
		return false;
	}
	/**
	 * Returns a node with a certain key.
	 * @param key data to find.
	 * @return node.
	 */
	public RBNode<E> findNode(E key) {
		RBNode<E> current = root;
		while(!current.equals(NIL)) {
			if(key.compareTo(current.getData()) == 0) {
				return current;
			}
			else if(key.compareTo(current.getData()) <= -1) {
				current = current.left;
			}
			else if(key.compareTo(current.getData()) >= 1) {
				current = current.right;
			}
		}
		return null;
	}
	/**
	 * Private method that finds number of children of a parent.
	 * @param node
	 * @return
	 */
	private int numOfChildren(RBNode<E> node) {
		int count = 0;
		if(!node.left.equals(NIL)) {
			count++;
		}
		if(!node.right.equals(NIL)) {
			count++;
		}
		return count;
	}
	/**
	 * Finds the node that contains the data given by the user to delete such node
	 * @param key data given by user.
	 * @return node that contains chosen key.
	 */
	private RBNode<E> nodeToDelete(E key){
		RBNode<E> current = root;
		while(!current.equals(NIL)) {
			if(key.compareTo(current.getData()) == 0) {
				return current;
			}
			else if(key.compareTo(current.getData()) <= -1) {
				current = current.left;
			}
			else if(key.compareTo(current.getData()) >= 1) {
				current = current.right;
			}
		}
		return null;
	}
	/**
	 * Returns true or false if BST is empty or not.
	 * @return true or false id BST is empty or not.
	 */
	public Boolean isEmpty() {
		return root == null;
	}
	/**
	 * Returns true if a node is a leaf. False otherwise.
	 * @param chNode chosen node.
	 * @return true if node is a leaf. False otherwise.
	 */
	public boolean isLeaf(RBNode<E> chNode){
		if(chNode.left.equals(NIL) && chNode.right.equals(NIL)) {
			return true;
		}
		return false;
	}
	/**
	 * Returns true if node is a left child.
	 * @param chNode chosen node.
	 * @return true if node is a left child.
	 */
	public Boolean isLeftChild(RBNode<E> chNode) {
		if(chNode.parent.equals(NIL)) {
			return false;
		}
		else if(chNode.equals(NIL) && chNode.parent.left.equals(NIL)) {
			return true;
		}
		else if(!chNode.equals(NIL) && chNode.parent.left.equals(NIL)) {
			return false;
		}
		return chNode.parent.left.data == chNode.data;
		
	}
	/**
	 * Returns true if node is right child.
	 * @param chNode chosen node.
	 * @return true if node is right child.
	 */
	public Boolean isRightChild(RBNode<E> chNode){
		if(chNode.parent.equals(NIL)) {
			return false;
		}
		else if(chNode.equals(NIL) && chNode.parent.right.equals(NIL)) {
			return true;
		}
		else if(!chNode.equals(NIL) && chNode.parent.right.equals(NIL)) {
			return false;
		}
		return chNode.parent.right.data == chNode.data;
	}
	/**
	 * Returns the sibling of the node. Returns null if no sibling.
	 * @param chNode chosen node
	 * @return sibling of node, null if no sibling.
	 */
	public RBNode<E> sibling(RBNode<E> chNode){
		RBNode<E> sibNode = NIL;
		if(isLeftChild(chNode)) {
			if(chNode.parent.right.equals(NIL)) {
				return NIL;
			}
			else {
				sibNode = chNode.parent.right;
			}
		}
		else if(isRightChild(chNode)) {
			
			if(chNode.parent.left.equals(NIL)) {
				return null;
			}
			else {
				sibNode = chNode.parent.left;
			}
		}
		
		return sibNode;	
	}
	/**
	 * Returns uncle of chosen node. Returns null if no uncle
	 * @param chNode chosen node
	 * @return  uncle of node, null if no uncle.
	 */
	public RBNode<E> uncle(RBNode<E> chNode){
		RBNode<E> uNode = NIL;
		if(chNode.parent.equals(NIL) || chNode.parent.parent.equals(NIL)) {
			return NIL;
		}
		if(isLeftChild(chNode.parent)) {
			if(chNode.parent.parent.right.equals(NIL)) {
				return NIL;
			}
			else {
				uNode = chNode.parent.parent.right;
			}
		}
		else if(isRightChild(chNode.parent)) {
			if(chNode.parent.parent.right.equals(NIL)) {
				return NIL;
			}
			else {
				uNode = chNode.parent.parent.left;
			}
		}
		return uNode;	
	}
	/**
	 * Returns grandparent of node.
	 * @param chNode chosen node
	 * @return grandparent of node.
	 */
	public RBNode<E> grandparent(RBNode<E> chNode){
		RBNode<E> grNode = NIL;
		if(chNode.parent.equals(NIL)) {
			return NIL;
		}
		else if(chNode.parent.parent.equals(NIL)) {
			return NIL;
		}
		else {
			grNode = chNode.parent.parent;
		}
		return grNode;	
	}
	/**
	 * Returns an ArrayList of nodes in preorder.
	 * @return arraylist of nodes in preorder.
	 */
	public ArrayList<RBNode<E>> preorder() {
		ArrayList<RBNode<E>> pOrder = new ArrayList<>();
		if(isEmpty()) {
			return null;
		}
		LinkedStack<RBNode<E>> stack = new LinkedStack<>();
		stack.push(root);
		
		while(!stack.isEmpty()) {
			RBNode<E> current = stack.pop();
			pOrder.add(current);
			if(!current.right.equals(NIL)) {
				stack.push(current.right);
			}
			if(!current.left.equals(NIL)) {
				stack.push(current.left);
			}
		}
		return pOrder;
	}
	/**
	 * Returns ArrayList of nodes inorder.
	 * @return arraylist of nodes inorder.
	 */
	public ArrayList<RBNode<E>> inorder() {
		ArrayList<RBNode<E>> iOrder = new ArrayList<>();
		if(isEmpty()) {
			return null;
		}
		LinkedStack<RBNode<E>> stack = new LinkedStack<>();
		RBNode<E> current = root;
		
		while(!stack.isEmpty() || !current.equals(NIL)) {
			if(!current.equals(NIL)) {
				stack.push(current);
				current = current.left;
			}
			else {
				current = stack.pop();
				iOrder.add(current);
				current = current.right;
			}
		}
		return iOrder;
	}
	/**
	 * Returns size of tree;
	 * @return size of tree;
	 */
	public int size() {
		int count = 0;
		
		if(isEmpty()) {
			return 0;
		}
		LinkedStack<RBNode<E>> stack = new LinkedStack<>();
		RBNode<E> current = root;
		
		while(!stack.isEmpty() || !current.equals(NIL)) {
			if(!current.equals(NIL)) {
				stack.push(current);
				current = current.left;
			}
			else {
				current = stack.pop();
				count++;
				current = current.right;
			}
		}
		return count;
		
	}
	/**
	 * Returns ArrayList of nodes in postorder.
	 * @return arraylist of nodes in postorder.
	 */
	public ArrayList<RBNode<E>> postorder() {
		ArrayList<RBNode<E>> poOrder = new ArrayList<>();
		if(isEmpty()) {
			return null;
		}
		LinkedStack<RBNode<E>> stack1 = new LinkedStack<>();
		LinkedStack<RBNode<E>> stack2 = new LinkedStack<>();
		
		stack1.push(root);
		while(!stack1.isEmpty()) {
			RBNode<E> current = stack1.pop();
			stack2.push(current);
			
			if(!current.left.equals(NIL)) {
				stack1.push(current.left);
			}
			if(!current.right.equals(NIL)) {
				stack1.push(current.right);
			}
		}
		while(!stack2.isEmpty()) {
			RBNode<E> current = stack2.pop();
			poOrder.add(current);
		}
		return poOrder;
	}
	/**
	 * Returns ArrayList of nodes in breadthfirst.
	 * @return arraylist of nodes in breadthfirst.
	 */
	public ArrayList<RBNode<E>> breadthfirst(){
		ArrayList<RBNode<E>> bOrder = new ArrayList<>();
		LinkedQueue<RBNode<E>> queue = new LinkedQueue<RBNode<E>>();
		queue.enqueue(root);
		while(!queue.isEmpty()) {
			RBNode<E> node = queue.dequeue();
			bOrder.add(node);
			if(!node.left.equals(NIL)) {
				queue.enqueue(node.left);
			}
			if(!node.right.equals(NIL)) {
			queue.enqueue(node.right);
			}
		}
		return bOrder;
	}
	/**
	 * Method that prints the tree.
	 */
	public void printTree() {

	    if (this.root.right != null) {
	        this.printTree(this.root.right, true, "");
	    }

	    printNodeValue(this.root);

	    if (this.root.left != null) {
	        this.printTree(this.root.left, false, "");
	    }
	}
	/**
	 * Method that prints a tree.
	 * @param node starting point. 
	 * @param isRight true or false depending if its a right or left subtree.
	 * @param indent indentation for printing tree.
	 */
	private void printTree(RBNode<E> node, boolean isRight, String indent) {
	    if (node.right != null) {
	        printTree(node.right, true, indent + (isRight ? "        " : " |      "));
	    }

	    System.out.print(indent);

	    if (isRight) {
	        System.out.print(" /");
	    }
	    else {
	        System.out.print(" \\");
	    }
	    System.out.print("----- ");
	    printNodeValue(node);
	    if (node.left != null) {
	        printTree(node.left, false, indent + (isRight ? " |      " : "        "));
	    }
	}
	/**
	 * Prints node value
	 * @param node node to be printed
	 */
	private void printNodeValue(RBNode<E> node) {
	    if (node == null || node.equals(NIL)) {
	        System.out.print("NIL" + "(" +  node.getColor() + ")");
	    }
	    else {
	        System.out.print(node.getData() + "(" + node.getColor() + ")");
	    }
	    System.out.println();
	}
	/** 
	 * Returns root of tree.
	 * @return root of tree.
	 */
	public RBNode<E> getRoot(){
		return this.root;
	}
	
	

}
