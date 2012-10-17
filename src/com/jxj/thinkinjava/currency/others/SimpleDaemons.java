package com.jxj.thinkinjava.currency.others;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {

	@Override
	public void run() {
		while(true){
			try {
				TimeUnit.MICROSECONDS.sleep(1);
				System.out.println(Thread.currentThread()+" "+ this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		for(int i = 0; i<100; i++){
			Thread daemon = new Thread(new SimpleDaemons());
			daemon.setDaemon(true);
			daemon.start();
		}
		System.out.println("All daemons started.");
		TimeUnit.MICROSECONDS.sleep(1);
	}

}
