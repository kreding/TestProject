package com.jxj.algorithm.datastructure;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
	
	private Node first;
	private int N;
	
	private class Node {
		// Êý¾Ý
		Item item;
		// Ö¸Õë
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return N;
	}
	
	public void add(Item item) {
		Node oldfirst = first;
		
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		
		N++;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		
		private Node current = first;

		@Override
		public boolean hasNext() {
			return first.next != null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = first.next;
			
			return item;
		}

		@Override
		public void remove() {}
		
	}

}
