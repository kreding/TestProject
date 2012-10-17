package com.jxj.thinkinjava.currency.interruption;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class NIOInterruption {

	public static void main(String[] args) throws Exception{
		ExecutorService exec = Executors.newCachedThreadPool();
		InetSocketAddress isa = new InetSocketAddress("localhost", 8081);
		
		SocketChannel sc1 = SocketChannel.open(isa);
		SocketChannel sc2 = SocketChannel.open(isa);
		
		Future<?> f = exec.submit(new NIOBlocked(sc1));
		exec.execute(new NIOBlocked(sc2));
		
		exec.shutdown();
		
		TimeUnit.SECONDS.sleep(1);
		f.cancel(true);
		
		TimeUnit.SECONDS.sleep(1);
//		exec.shutdownNow();
		sc2.close();
	}

}
