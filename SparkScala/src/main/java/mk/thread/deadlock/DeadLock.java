package mk.thread.deadlock;

public class DeadLock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Shared s1=new Shared();
		Shared s2=new Shared();
		Thread1 t1=new Thread1(s1,s2);
		t1.startT1();
		
		Thread2 t2=new Thread2(s1,s2);
		t2.startT2();
		
	}

}
