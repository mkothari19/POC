package mk.thread.executorservices;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mk.thread.deadlock.Utils;



public class WorkerThread implements Runnable {

	int command;
	WorkerThread(int command){
		this.command=command;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread is start "+ Thread.currentThread().getName()+" "+ command);
		process();
		System.out.println("Thread is start "+ Thread.currentThread().getName()+" "+ command);
	}
	
	private void process() {
		Utils.sleep(500);
	}
	
	public static void main(String[] args) {
		
		ExecutorService executor=Executors.newFixedThreadPool(5);
		
		for(int i=1;i<=10;i++) {
			Runnable workers= new WorkerThread(i);
			executor.execute(workers);
		}
		executor.shutdown();
		
		if(executor.isTerminated()) {
			System.out.println("All threads are terminated");
		}
		
	}

}
