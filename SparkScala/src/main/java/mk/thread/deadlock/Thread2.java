package mk.thread.deadlock;

public class Thread2 implements Runnable {

	Shared s1;
	Shared s2;
	Thread t2;
	
	Thread2(Shared s1,Shared s2){
		this.s1=s1;
		this.s2=s2;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		s2.mytest1(s1);
	}

	public void startT2() {
		if(t2==null) {
			t2.start();
		}
	}
}
