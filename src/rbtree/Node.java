package rbtree;

public class Node<E> {
	private E element;
	private Node<E> next;
	
	/**
	 * Constructor of Node
	 * @param e element stored at this Node
	 * @param n element subsequent at this Node
	 */
	public Node(E e, Node<E> n) {
		element = e;
		next = n;
	}
	/**
	 * Returns the data of a Node
	 * @return data of Node
	 */
	public E getElement() {
		return element;
	}
	/**
	 * Returns the next Node data of a Node
	 * @return data of next Node
	 */
	public Node<E> getNext(){
		return next;
	}
	/**
	 * Sets the next Node's data
	 * @param n data of Node
	 */
	public void setNext(Node<E> n) {
		next = n;
	}
}
