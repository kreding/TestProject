package com.jxj.thinkinjava.extend;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Game {
	public Game(int i){
		System.out.println("Game instructor!");
	}
	
	public static void main(String[] args) {
		ByteArrayOutputStream op = new ByteArrayOutputStream();
		try {
//			byte[] bzq = new byte[Integer.MAX_VALUE+100];
//			System.out.println(bzq.length);
			System.out.println(Runtime.getRuntime().totalMemory()+":"+Runtime.getRuntime().maxMemory()+":"+Runtime.getRuntime().freeMemory());
			op.write(new byte[Integer.MAX_VALUE/3]);
			
			byte[] bz = op.toByteArray();
			System.out.println("*********bz.size="+bz.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
