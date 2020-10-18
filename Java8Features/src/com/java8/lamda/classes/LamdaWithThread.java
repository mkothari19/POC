package com.java8.lamda.classes;

public class LamdaWithThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Create thread without Lamda
		
		Runnable r1=new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("I am Here");
			}
		
	};
	
	Thread t1=new Thread(r1);
	t1.start();
	
	//Create thread with lamda
	
	Runnable r2=()->{System.out.println("lamda");};
	
	Thread t2=new Thread(r2);
	t2.start();
	
	}
}
