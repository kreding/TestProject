package com.jxj.somethingelse;

public class Utils {

	/**
	 * 通过XOR获取字符串的反转值，StringBuffer中也有反转字符串的API
	 * @param resource
	 * @return
	 */
	public static String Reverse(String resource){
		char[] charArray = resource.toCharArray();
		int len = charArray.length-1;
		
		for(int i=0;i<len;i++,len--){
			charArray[i] ^= charArray[len];
			charArray[len] ^= charArray[i];
			charArray[i] ^= charArray[len];
		}
		
		return new String(charArray);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
