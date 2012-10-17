package com.jxj.jnuit.main;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public class AllJunitTestRunner extends Suite {

	private static final String DEFAULT_CLASSPATH = "java.class.path"; 
	
	public AllJunitTestRunner(Class<?> klass, RunnerBuilder builder)
			throws InitializationError {
		// TODO 根据新的规则重载父类构造器 
		super(klass, builder);
	}

	//
	public static String getClasspath(){
		return System.getProperty(DEFAULT_CLASSPATH);
	}
}
