package mk.thread.deadlock;

public class Thread1 implements  Runnable {

	Shared s1;
	Shared s2;
	Thread t1;
	
	Thread1(Shared s1,Shared s2){
		this.s1=s1;
		this.s2=s2;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		s1.mytest1(s2);
	}

	public void startT1() {
		if(t1==null) {
			t1.start();
		}
	}
}
