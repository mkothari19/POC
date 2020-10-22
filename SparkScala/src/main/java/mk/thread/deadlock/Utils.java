package mk.thread.deadlock;

public class Utils {

	public static void sleep(long mills) {

		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
