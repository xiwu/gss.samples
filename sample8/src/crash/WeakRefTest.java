package crash;

import java.lang.ref.WeakReference;

public class WeakRefTest {

	public static void main(String args[]) throws InterruptedException {
//		Object referent = new Object();
//		ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
//		 
//		WeakReference weakReference1 = new WeakReference<>(referent);
//		WeakReference weakReference2 = new WeakReference<>(referent, referenceQueue);
//		Object referent2 = weakReference1.get();
//		weakReference1.clear();
//		Object referent3 = weakReference2.get();
//		Thread.currentThread().sleep(50000);
//		System.gc();
//		if (referent3 != null) {
//			System.out.println("referent!=null");
//		    // GC hasn't removed the instance yet
//		} else {
//		    // GC has cleared the instance
//			System.out.println("referent==null");
//		}
		  Object o = new Object();

	        WeakReference<Object> weak = new WeakReference<Object>(o);

	        o = null;

	        int i = 0;

	        while (weak.get() != null) {

	            i++;

	            System.out.println("Object is not null. count is " + i);

	            if (i % 10 == 0) {

	                System.gc();

	                System.out.println("System.gc() was called!");

	            }

	            try {

	                Thread.sleep(500);

	            } catch (InterruptedException e) {

	            }

	        }

	        System.out.println("object o was cleared by JVM!");

	    }
	}

