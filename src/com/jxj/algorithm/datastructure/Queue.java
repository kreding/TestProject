/**
 * 
 */
package com.jxj.algorithm.datastructure;

import java.util.Iterator;

/**
 * @author kreding
 *
 */
public class Queue<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int N;
	
	private class Node {
		Item item;
		Node next;
	}
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	/**
	 * 表尾插入元素
	 * @return
	 */
	public void enqueue(Item item) {
		Node oldlast = last;
		
		last = new Node();
		last.item = item;
		last.next = null;
		
		if(isEmpty()) {
			first = last;
		}else {
			oldlast.next = last;
		}
		
		N++;
	}
	
	/**
	 * 表头删除元素
	 * @return
	 */
	public Item dequeue() {
		Item item = first.item;
		
		first = first.next;
		if(isEmpty()) {
			last = null;
		}
		
		N--;
		return item;
	}

	/* 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		
		private Node current = first;

		@Override
		public boolean hasNext() {
			return current.next != null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			
			return item;
		}

		@Override
		public void remove() {}
		
	}

}
