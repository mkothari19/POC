package mk.thread;

public class RunnableThreadDemo implements Runnable {

	Thread t;
	private String threadname;

	public RunnableThreadDemo(String threadname) {
		this.threadname = threadname;
		System.out.println("Thread is creating ::" + threadname);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Running thread ::: " + threadname);
		try {
			for (int i = 4; i > 0; i--) {
				System.out.println("thread:::" + threadname + "  , " + i);

				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block

			System.out.println("Thread is intrupted::  " + threadname);
			e.printStackTrace();

		}
		System.out.println("Thread existing:::: " + threadname);
	}

	public void start() {
		System.out.println("Thread starting:;; " + threadname);
		if (t == null) {
			t = new Thread(this, threadname);
			t.start();
		}
	}

	public static void main(String[] args) {
		RunnableThreadDemo t1 = new RunnableThreadDemo("thread-1");
		t1.start();
		RunnableThreadDemo t2 = new RunnableThreadDemo("thread-2");
		t2.start();

	}
}
