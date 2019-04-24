package rbtree;

import rbtree.SinglyLinkedList;

public class LinkedQueue<E> {
	private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	/**
	 * Default constructor
	 */
	public LinkedQueue() {	
	}
	/**
	 * Returns size of Queue
	 * @return size of Queue
	 */
	public int size() {
		return list.size();
	}
	/**
	 * Returns true if empty
	 * @return true if empty
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
	/**
	 * Enqueues data.
	 * @param el data to be enqueued
	 */
	public void enqueue(E el) {
		list.addLast(el);
	}
	/**
	 * Returns first value in Queue.
	 * @return first value in Queue
	 */
	public E first() {
		return list.first();
	}
	/**
	 * Dequeues data.
	 * @return dequeued data.
	 */
	public E dequeue() {
		return list.removedFirst();
	}
}

