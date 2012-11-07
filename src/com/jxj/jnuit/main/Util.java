package com.jxj.jnuit.main;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * 工具类
 * @author juxuejian
 *
 */
public class Util {
	
	private static final String DEFAULT_CLASSPATH = "java.class.path";
	private static final String INNER_CLASS_CHAR = "$";
	private static final String JAR_SUFFIX = ".jar";
	
	/**
	 * 获取类路径及jar包路径
	 * @return
	 */
	public static String getClasspath(){
		return System.getProperty(DEFAULT_CLASSPATH);
	}
	
	/**
	 * 将classpath按路径分隔放进数组
	 * @param classPath
	 * @return
	 */
	public static String[] splitClassPath(String classPath){
		return null;
	}
	
	/**
	 * 判断给出的路径是否是一个类文件
	 * @param fileName
	 * @return
	 */
	public static boolean isClassFile(String filePath){
		return false;
	}
	
	/**
	 * 获取没有顶级路径的相对文件地址
	 * @param currentFile
	 * @param rootPath
	 * @return
	 */
	public static String getFileNameWithoutRootPath(File currentFile, String rootPath){
		return null;
	}
	
	/**
	 * 去除类文件的后缀(.class)
	 * @param filePath
	 * @return
	 */
	public static String removeClassSuffix(String filePath){
		return null;
	}
	
	/**
	 * 替换文件路径分隔符(/)为点号(.)
	 * @param filePath
	 * @return
	 */
	public static String replaceFileSeparator(String filePath){
		return null;
	}
	
	/**
	 * 获取类路径下所有的class文件的名称列表
	 * @return
	 */
	public static List<String> filterClassNameList(){
		List<String> classNameList = new ArrayList<String>();
		for(String path : Util.splitClassPath(Util.getClasspath())){
			if(!path.toLowerCase().endsWith(JAR_SUFFIX)){
				Util.loadAllClassNames(path, path, classNameList);
			}
		}
		
		return classNameList;
	}
	
	/**
	 * 过滤出路径中的.class文件
	 * @param rootPath
	 * @param currentPath
	 * @param classNameList
	 */
	public static void loadAllClassNames(String rootPath, String currentPath, 
			List<String> classNameList){
		File currentFile = new File(currentPath);
		if(currentFile.isFile()){
			handleClassFile(rootPath, classNameList, currentFile, currentFile);
		}else {
			for(File file : currentFile.listFiles()){
				if(file.isFile()){
					handleClassFile(rootPath, classNameList, currentFile, file);
				}else {
					loadAllClassNames(rootPath, file.getAbsolutePath(), classNameList);
				}
			}
		}
	}

	/**
	 * 处理类文件：去除顶级路径-->去除文件后缀-->替换文件分隔符为点号
	 * @param rootPath
	 * @param classNameList
	 * @param currentFile
	 * @param file
	 */
	private static void handleClassFile(String rootPath,
			List<String> classNameList, File currentFile, File file) {
		if(Util.isClassFile(currentFile.getName())){
			classNameList.add(
				Util.replaceFileSeparator(
					Util.removeClassSuffix(
						Util.getFileNameWithoutRootPath(file, rootPath)
					)
				)
			);
		}
	}
	
	/**
	 * 判断给出的类是否匹配过滤规则
	 * @param filters
	 * @param separator
	 * @param className
	 * @return
	 */
	public static boolean classNameIsInArray(String filters, String separator, String className){
//		String filters = "com.aa.bb.cc1.*;com.aa.bb.cc2.*";
		String[] filterList = filters.split(separator);
		
		if(filters == null || filterList.length < 1){
			return false;
		}
		
		for(String pattern : filterList){
			if(className.matches(pattern)){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 判断是否是抽象类
	 * @param clazz
	 * @return
	 */
	public static boolean isAbstractClass(Class<?> clazz){
		return (clazz.getModifiers()&Modifier.ABSTRACT) != 0;
	}
	
	/**
	 * 判断是否是内部类
	 * @param className
	 * @return
	 */
	public static boolean isInnerClass(String className){
		return className.contains(INNER_CLASS_CHAR);
	}
	
	/**
	 * 判断指定的类是否是JUnit3的测试类，主要判断依据为此类是否继承自TestCase类
	 * @param clazz
	 * @return
	 */
	public static boolean isJUnit3TestClass(Class<?> clazz){
		return TestCase.class.isAssignableFrom(clazz);
	}
	
	/**
	 * 判断指定的类是否是JUnit4的测试类，主要依据是类中是否存在含有Test注解的方法
	 * @param clazz
	 * @return
	 */
	public static boolean isJUnit4TestClass(Class<?> clazz){
		try {
			for(Method method : clazz.getMethods()){
				if(method.getAnnotation(Test.class) != null){
					return true;
				}
			}
		} catch (NoClassDefFoundError e) {
			return false;
		}
		
		return false;
	}
}
