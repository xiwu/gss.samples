package com.redhat.gss.support.jms.messy;

public class HelloRunnable implements Runnable {

    public void run() {
        System.out.println("Hello from a thread!");
        try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void main(String args[]) throws InterruptedException {
        (new Thread(new HelloRunnable())).start();
        System.out.println("main thread finished");
    }

}