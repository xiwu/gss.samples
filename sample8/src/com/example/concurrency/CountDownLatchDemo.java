package com.example.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
public static void main(String args[]) throws InterruptedException {
	final CountDownLatch latch = new CountDownLatch(3);

	
	new Thread(new Service("A",10000,latch)).start();
	new Thread(new Service("B",2000,latch)).start();
	new Thread(new Service("C",5000,latch)).start();
	
	latch.await();
	
	System.out.println("all 3 services started, I am OK too");
}
}
