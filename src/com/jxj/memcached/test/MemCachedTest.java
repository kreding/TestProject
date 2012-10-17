package com.jxj.memcached.test;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

/**
 * Oct 15, 2011
 */
public class MemCachedTest {
	private static MemcachedClient c;
	
	public static void init(){
		InetSocketAddress addr =  new InetSocketAddress("127.0.0.1", 11211);
		try {
			c=new MemcachedClient(addr);
			c.set("name", 3600, "jxj");
			System.out.println(c.get("name"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void shutdown(){
		c.shutdown();
	}
	
	public static void main(String[] args) {
		init();
		printCurThreads();
		shutdown();
		System.out.println("************");
		printCurThreads();
		
	}

	private static void printCurThreads() {
		Thread[] tarray = new Thread[Thread.activeCount()];
		Thread.enumerate(tarray);
		for(Thread ele : tarray){
			System.out.println(ele.getName());
		}
	}
}

