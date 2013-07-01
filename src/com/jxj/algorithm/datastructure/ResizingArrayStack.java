package com.jxj.algorithm.datastructure;

import java.util.Iterator;
/**
 * 下压栈，可动态调整数组大小的实现
 * @author kreding
 *
 * @param <Item>
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
	
	@SuppressWarnings("unchecked")
	private Item[] a = (Item[]) new Object[1];
	private int N;
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	private void resize(int max) {
		@SuppressWarnings("unchecked")
		Item[] temp = (Item[]) new Object[max];
		
		for(int i=0; i < N; i++) {
			temp[i] = a[i];
		}
		
		a = temp;
	}
	
	public void push(Item item){
		if(N == a.length) {
			resize(2*N);
		}
		
		a[N++] = item;
	}
	
	public Item pop() {
		Item item = a[--N];
		a[N] = null;
		
		if(N > 0 && N < a.length/4) {
			resize(a.length/2);
		}
		
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		return null;
	}

}
