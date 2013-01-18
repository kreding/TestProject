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
	
	public static void removeChineseDots() {
		String str = "中，过？《人》“们”测。时：";
		str = str.replaceAll("(?i)[^a-zA-Z0-9\u4E00-\u9FA5]", "");	
		System.out.println("remove chinese dots = " + str); 
	}
	
	public static void removeWhiteSpace() {
		String str = "1	2 3\n4\r56";
		String totalTrimmed = str.replaceAll("\\s+", "");
		System.out.println("remove white space =" + totalTrimmed);
		//remove the blank space 不能移除tab，回车等空白字符
		System.out.println("remove only the blank space" + str.replaceAll(" ", ""));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
