package com.jxj.thinkinjava.currency.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

public class PrioritizedTaskConsumer implements Runnable {

	private PriorityBlockingQueue<Runnable> queue;
	
	public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while(!Thread.interrupted()){
				queue.take().run();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finished PrioritizedTaskConsumer");
	}

}
