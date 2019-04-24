package rbtree;



public class RBNode<E extends Comparable<E>> {
	RBNode<E> parent;
	RBNode<E> left;
	RBNode<E> right;
	public String color;
	public E data;
	
	/**
	 * Constructor that makes a NIL node.
	 */
	public RBNode() {
		this.color = "B";
		this.left = null;
		this.right = null;
		this.parent =null;
	}
	/**
	 * Constructor that makes a nodes.
	 * @param data data of the node.
	 */
	public RBNode(E data) {
		this.data = data;
		this.color = "R";
	}
	/**
	 * Returns data of node.
	 * @return data of node.
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * Sets data of node
	 * @param data data to be set in node.
	 */
	public void setData(E data) {
		this.data = data;
	}
	/**
	 * Sets the color of the Node
	 * @param c color of node.
	 */
	public void setColor(String c) {
		this.color = c;
	}
	/**
	 * Returns the color of the node.
	 * @return color of node.
	 */
	public String getColor() {
		return color;
	}
}
