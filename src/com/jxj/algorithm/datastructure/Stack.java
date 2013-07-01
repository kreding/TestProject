/**
 * 
 */
package com.jxj.algorithm.datastructure;

import java.util.Iterator;

/**
 * @author kreding
 * @description 下压栈，链表实现
 */
public class Stack<Item> implements Iterable<Item> {
	
	private Node first;
	private int N;
	
	private class Node {
		Item item;
		Node next;
	}
	
	public void push(Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		
		N++;
	}
	
	public Item pop() {
		Item item = first.item;
		first = first.next;
		
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
