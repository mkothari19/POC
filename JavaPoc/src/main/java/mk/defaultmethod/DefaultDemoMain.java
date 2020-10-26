package mk.defaultmethod;

public class DefaultDemoMain implements Message {

	@Override
	public void printMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
	}
	
	public static void main(String[] args) {
		DefaultDemoMain message=new DefaultDemoMain();
		message.printMessage("Hello SAM");
		message.sayHelloDefult("Hello Default");
		Message.sayHelloStatic("Hello Static");
	}

}
