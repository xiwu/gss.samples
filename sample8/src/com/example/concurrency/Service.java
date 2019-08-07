package com.example.concurrency;

import java.util.concurrent.CountDownLatch;

public class Service implements Runnable {

	CountDownLatch cdl = null;
	long sleepPeriod = 0;
	String name = null;
	public Service(String name, long sleepPeriod, CountDownLatch cdl) {
		this.name = name;
		this.sleepPeriod = sleepPeriod;
		this.cdl = cdl;
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(name + " running");
		
		System.out.println(name + " will sleep" + sleepPeriod );
		try {
			Thread.currentThread().sleep(sleepPeriod);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cdl.countDown();
		System.out.println(name + " awake");

		
	}

}
