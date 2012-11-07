package com.jxj.jnuit.main;

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public class AllJunitTestRunner extends Suite {

	
	
	public AllJunitTestRunner(Class<?> clazz, RunnerBuilder builder)
			throws InitializationError {
		// TODO 根据新的规则重载父类构造器 
		super(clazz, builder);
	}
	
	public List<Class<?>> loadAllTestClass(){
		List<Class<?>> toBeRanTestClassList = new ArrayList<Class<?>>();
		
		List<String> classesFileNameList = Util.filterClassNameList();
		for(String className : classesFileNameList){
			Class<?> classFromName = null;
			try {
				classFromName = Class.forName(className);
			} catch (ClassNotFoundException e) {
				continue;
			}
			
			if(!Util.isInnerClass(className)){
				try {
					classFromName = Class.forName(className);
				} catch (ClassNotFoundException e) {
					continue;
				}
				if(classFromName.isLocalClass() || classFromName.isAnonymousClass()){
					continue;
				}
				if (!Util.isAbstractClass(classFromName) && 
					       (Util.isJUnit4TestClass(classFromName)|| Util.isJUnit3TestClass(classFromName))) 
			   {                             
		        toBeRanTestClassList.add(classFromName); 
			   } 
			}
		}
		
		return toBeRanTestClassList;
	}
}
