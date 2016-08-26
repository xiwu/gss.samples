import java.util.concurrent.CountDownLatch;


public class Driver { // ...
   public static void main(String args[]) throws InterruptedException {
	   int N = 10;
     CountDownLatch startSignal = new CountDownLatch(1);
     CountDownLatch doneSignal = new CountDownLatch(N);
     CountDownLatch gateway = new CountDownLatch(1);
     new Thread(new Worker2(null, gateway)).start();
     
     for (int i = 0; i < N; ++i) // create and start threads
       new Thread(new Worker(startSignal, gateway)).start();

     doSomethingElse();            // don't let run yet
     startSignal.countDown();      // let all threads proceed
     doSomethingmore();
//     doneSignal.await();           // wait 	for all to finish
   }

private static void doSomethingmore() {
	System.out.println("====domore====");
	
}

private static void doSomethingElse() {
	// TODO Auto-generated method stub
	System.out.println("$$$$$doSomethingElse$$$$");
	
}
 }

class Worker implements Runnable {
	   private final CountDownLatch startSignal;
	   private final CountDownLatch doneSignal;
	   Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
	     this.startSignal = startSignal;
	     this.doneSignal = doneSignal;
	   }
	   public void run() {
	     try {
	       startSignal.await();
	       
	       System.out.println("=====now startSignal await ====" + Thread.currentThread().getName());
	       doWork();
	       doneSignal.await();
//	       doneSignal.countDown();
	       System.out.println("=====now doneSignal countDown" + Thread.currentThread().getName());
	     } catch (InterruptedException ex) {} // return;
	   }

	   void doWork() { System.out.println("dowork!"); }
	 }

class Worker2 implements Runnable {
	   private final CountDownLatch startSignal;
	   private final CountDownLatch doneSignal;
	   Worker2(CountDownLatch startSignal, CountDownLatch doneSignal) {
	     this.startSignal = startSignal;
	     this.doneSignal = doneSignal;
	   }
	   public void run() {
		   


	       doWork();
	       doneSignal.countDown();
	       System.out.println("=====Worker2 doneSignal countDown" + Thread.currentThread().getName());

	   }

	   void doWork() { try {
		Thread.currentThread().sleep(20*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} }
	 }