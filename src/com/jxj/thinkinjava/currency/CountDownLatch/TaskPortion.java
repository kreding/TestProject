package com.jxj.thinkinjava.currency.CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TaskPortion implements Runnable {

	private static int counter = 0;
	private final int id = counter++;
	private static Random rand = new Random(47);
	private final CountDownLatch latch;
	
	public TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public void run() {
		try {
			doWork();
			latch.countDown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void doWork() throws InterruptedException{
		TimeUnit.MICROSECONDS.sleep(rand.nextInt(2000));
		System.out.println(this + "completed");
	}
	
	public String toString(){
		return String.format("%1$-3d", id);
	}
}
