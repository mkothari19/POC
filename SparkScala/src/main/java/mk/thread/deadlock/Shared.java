package mk.thread.deadlock;

public class Shared {
	
	synchronized void mytest1(Shared s1) {
		
		System.out.println("mytest1 begin ");
		Utils.sleep(50);
		s1.mytest2();
		System.out.println("mytest1 end ");
	}
	
	synchronized void mytest2() {
		System.out.println("mytest1 begin ");
		Utils.sleep(50);
		System.out.println("mytest1 end ");
	}

	
}
