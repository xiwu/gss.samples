package sample8;

public class RunnableTest {

	public static void main(String...strings ) {
		Runnable r2 = () -> System.out.println("hello runnable!");
		
		r2.run();
	}
	

	
}
