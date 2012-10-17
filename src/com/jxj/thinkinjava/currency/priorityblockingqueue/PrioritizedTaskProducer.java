package com.jxj.thinkinjava.currency.priorityblockingqueue;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class PrioritizedTaskProducer implements Runnable {

	private Random rand = new Random(47);
	private Queue<Runnable> queue;
	private ExecutorService exec;
	public PrioritizedTaskProducer(Queue<Runnable> queue, ExecutorService e){
		this.queue = queue;
		this.exec = e;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 20; i++){
			queue.add(new PrioritizedTask(rand.nextInt(20)));
			Thread.yield();
		}
		try {
			TimeUnit.MILLISECONDS.sleep(250);
			for(int i=0; i<10; i++){
				queue.add(new PrioritizedTask(10));
			}
			
			for(int i=0; i<10; i++){
				queue.add(new PrioritizedTask(i));
			}
			
			queue.add(new PrioritizedTask.EndSentinel(exec));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finished PrioritizedTaskProducer");
	}

}
