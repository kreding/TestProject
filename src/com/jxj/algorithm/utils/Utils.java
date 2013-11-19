package com.jxj.algorithm.utils;

public class Utils {
	/**
	 * ÅĞ¶ÏÊÇ·ñÊÇËØÊı
	 * @param N
	 * @return
	 */
	public static boolean isPrime(int N) {
		if(N < 2) {
			return false;
		}
		
		for(int i = 2; i*i < N; i++) {
			if(N % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		double err = 1e-15;
		System.out.println(err);

	}

}
