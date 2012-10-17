package com.jxj.somethingelse;

import java.lang.reflect.Modifier;

public class TemporaryExercise {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(Modifier.ABSTRACT & Modifier.ABSTRACT);
		System.out.println(Modifier.PRIVATE);
		System.out.println(Integer.toBinaryString(1024));
	}

}
