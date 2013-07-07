package com.jxj.algorithm;

import java.util.ArrayList;

/**
 * ����ǰ��������������������������
 * @author kreding
 *
 */
public class BinaryTreeFromAfterOrder {

	private ArrayList<String> resultList;
	
	public String printAfterOrder(String preOrder, String inOrder) {
		int len = preOrder.toCharArray().length;
		resultList = new ArrayList<String>();
		
		printAfterOrder(preOrder, inOrder, len);
		return resultList.toString();
	}

	private void printAfterOrder(String preOrder, String inOrder, int length) {
		if(length == 0) {
			return;
		}
		
		char[] preList = preOrder.toCharArray();
		char[] inList = inOrder.toCharArray();
		
		char root = preList[0];
		
		
		int index = 0;
		for(; index < length ; index++) {
			if(inList[index] == root) {
				break;
			}
		}
		
		// ����������
		printAfterOrder(preOrder.substring(1, index+1), inOrder.substring(0, index), index);
		
		// ����������
		printAfterOrder(preOrder.substring(index+1, length), inOrder.substring(index+1, length), length-index-1);
		
		resultList.add("" + root);
	}
	
	public static void main(String[] args) {
		BinaryTreeFromAfterOrder tree = new BinaryTreeFromAfterOrder(); 
		
		System.out.println(tree.printAfterOrder("GDAFEMHZ", "ADEFGHMZ"));
	}
}
