package mk.thread;

import java.util.ArrayList;
import java.util.List;

public class RunnablePractical implements Runnable {

	Thread t;
	String threadname;
	List<Integer> data = null;
	List<Integer> output = new ArrayList<Integer>();

	RunnablePractical() {

	}

	RunnablePractical(String threadname, List<Integer> data) {
		this.threadname = threadname;
		this.data = data;
		System.out.println("Thread created:: " + threadname);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread is running stage:: " + threadname);
		for (Integer record : data) {
			if (record % 2 == 0)
				output.add(record);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Displayout");
		displayOutput();

	}

	public void threadstarting() {
		System.out.println("Thread starting ::::" + threadname);
		if (t == null) {
			t = new Thread(this, threadname);
			t.start();
		}
	}

	public void displayOutput() {
		System.out.println(output);
	}

	public static void main(String[] args) {
		List<Integer> records = new ArrayList<Integer>();

		// FILL the records in list
		for (int i = 1; i <= 500; i++) {
			records.add(i);
		}
		System.out.println(records);
		RunnablePractical t1 = new RunnablePractical("thread1 ", records.subList(1, (records.size() / 2)));
		t1.threadstarting();
		RunnablePractical t2 = new RunnablePractical("thread2 ",
				records.subList((records.size() / 2) + 1, records.size()));
		t2.threadstarting();
		
	}

}
