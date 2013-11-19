package com.jxj.algorithm;

public class BinarySearch {

	/**
	 * �ǵݹ�ʵ�ֶ��ֲ���
	 * @param key
	 * @param a
	 * @return
	 */
	public static int _rank(int key, int[] a) {
		int lo = 0;
		int hi = a.length-1;
		int mid = lo + hi/2;
		while(lo <= hi) {
			if(a[key] < a[mid]) {
				hi = mid-1;
			}else if(a[key] > a[mid]) {
				lo = mid +1;
			}else {
				return mid;
			}
		}
		return -1;
	}
	
	/**
	 * �ݹ�ʵ�ֶ��ֲ���
	 * @param key
	 * @param a
	 * @return
	 */
	public static int rank(int key, int[] a) {
		return rank(key, a, 0, a.length-1);
	}
	
	public static int rank(int key, int[] a, int lo, int hi) {
		if(lo > hi) {
			return -1;
		}
		
		int mid = lo + hi/2;
		if(key > a[mid]) {
			return rank(key, a, mid+1, hi);
		}else if(key < a[mid]) {
			return rank(key, a, lo, mid-1);
		}else{
			return mid;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
