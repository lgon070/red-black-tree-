package rbtree;

import rbtree.SinglyLinkedList;

public class LinkedStack<E> {
	private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	/**
	 * Default constructor
	 */
	public LinkedStack() {
		
	}
	/**
	 * Returns size of the stack
	 * @return size
	 */
	public int size() {
		return list.size();
	}
	/**
	 * Returns true or false depending if the list is empty
	 * @return true if empty, false if not empty
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
	/**
	 * Pushes a new element on the top of the Stack
	 * @param element
	 */
	public void push(E element) {
		list.addFirst(element);
	}
	/**
	 * Returns the top element of the Stack
	 * @return top element
	 */
	public E top() {
		return list.first();
	}
	/**
	 * Returns the top element of the Stack and then deletes the element
	 * @return top element
	 */
	public E pop() {
		return list.removedFirst();
	}
	
	
}
