
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSLF4j {

	private static final Logger LOG = LoggerFactory.getLogger(TestSLF4j.class);	
	public static void main(String args[]) {
	    Thread t = new Thread(new MyThread());
	    t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

	      public void uncaughtException(Thread t, Throwable e) {
	 		 LOG.error("Error in thread '{}'", TestSLF4j.class.getName(), e);
	      }
	    });
	    t.start();
	  }
	
	}
//		Exception e = new NullPointerException();
//		 LOG.error("Error in thread '{}'", TestSLF4j.class.getName(), e);
//		

	
	class MyThread implements Runnable {

		  public void run() {
		    throw new RuntimeException();
		  }
	
}
