package rbtree;

import rbtree.Node;

public class SinglyLinkedList<E> {
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	
	/**
	 * Default construction 
	 */
	public SinglyLinkedList() {
		
	}
	/**
	 * Returns size of the list							
	 * @return size
	 */
	public int size() {
		return size;
	}
	/**
	 * Returns true or false depending if the list is empty
	 * @return true if empty, false if not empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * Returns the first element of the list
	 * @return first element
	 */
	public E first() {
		if(isEmpty()) {
			return null;
		}
		return head.getElement();
	}
	/**
	 * Returns last element of the list
	 * @return last element
	 */
	public E last() {
		if(isEmpty()) {
			return null;
		}
		return tail.getElement();
	}
	/**
	 * Adds an element to the front of the list
	 * @param e element being added
	 */
	public void addFirst(E e) {
		head = new Node<>(e, head);
		if(size == 0) {
			tail = head;
		}
		size++;
	}
	/**
	 * Adds an element to the end of the list
	 * @param e element being added
	 */
	public void addLast(E e) {
		Node<E> newest = new Node(e, null);
		if(isEmpty()) {
			head = newest;
		}
		else {
			tail.setNext(newest);
		}
		tail = newest;
		size++;
	}
	/**
	 * Returns the first element and then deletes it from the list
	 * @return first element
	 */
	public E removedFirst() {
		if(isEmpty()) {
			return null;
		}
		E answer = head.getElement();
		head = head.getNext();
		size--;
		if(size == 0) {
			tail = null;
		}
		return answer;
	}
}
