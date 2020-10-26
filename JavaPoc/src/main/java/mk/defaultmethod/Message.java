package mk.defaultmethod;

@FunctionalInterface
public interface Message {

	void printMessage(String message);
	
	default void sayHelloDefult(String message) {
		System.out.println(message);
	}
	
	static void sayHelloStatic(String message) {
		System.out.println(message);
	}
	
}
