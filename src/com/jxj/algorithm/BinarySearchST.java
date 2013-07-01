package com.jxj.algorithm;

import com.jxj.algorithm.datastructure.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] vals;
	private int N;
	
	@SuppressWarnings("unchecked")
	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Comparable[capacity];
	}
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public Value get(Key key) {
		if(isEmpty()) {
			return null;
		}
		
		int i = rank(key);
		
		if(i < N && keys[i].compareTo(key) == 0) {
			return vals[i];
		}
		
		return null;
	}
	
	public void put(Key key, Value val) {
		// ����С�ڵ������key������,
		// ��ֵ���Ա�֤��key����λ�ã�
		// ʹ����ʼ�ձ�������
		int i = rank(key);
		
		if(i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}
		
		for(int j = N; j > i; j--) {
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		
		keys[i] = key;
		vals[i] = val;
		
		// �ǵø�������ֵ
		N++;
	}
	
	/**
	 * С��key�ļ��ĸ���
	 * @param key
	 * @return
	 */
	public int rank(Key key){
		int lo = 0;
		int hi = N-1;
		
		while(lo <= hi) {
			int mid = lo + (hi - lo)/2;
			int cmp = key.compareTo(keys[mid]);
			
			if(cmp < 0) {
				hi = mid-1;
			}else if(cmp > 0) {
				lo = mid + 1;
			}else {
				return mid;
			}
		}
		
		return lo;
	}
	
	public boolean contains(Key key) {
		int lo = 0;
		int hi = N-1;
		
		while(lo <= hi) {
			int mid = lo + (hi-lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			
			if(cmp < 0) {
				hi = mid -1;
			}else if(cmp > 0) {
				lo = mid + 1;
			}else {
				return true;
			}
		}
		return false;
	}
	
	public Key min() {
		return keys[0];
	}
	
	public Key max() {
		return keys[N-1];
	}
	
	public Key select( int i) {
		return keys[i];
	}
	
	/**
	 * ���ڵ���key����С��
	 * @param key
	 * @return
	 */
	public Key ceiling(Key key) {
		int i = rank(key);
		return keys[i];
	}
	
	/**
	 * С�ڵ���key�����ֵ
	 * @param key
	 * @return
	 */
	public Key floor(Key key) {
		boolean isCtn = contains(key);
		int i = rank(key);
		
		if( isCtn ) {
			return keys[i];
		}else{
			return keys[i-1];
		}
	}
	
	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> q = new Queue<Key>();
		int loi = rank(lo);
		int hii = rank(hi);
		
		if(hii < loi) return q;
		
		for(int i = loi; i < hii; i++) {
			q.enqueue(keys[i]);
		}
		
		if(contains(hi)) {
			q.enqueue(keys[hii]);
		}
		
		return q;
	}
	
}
