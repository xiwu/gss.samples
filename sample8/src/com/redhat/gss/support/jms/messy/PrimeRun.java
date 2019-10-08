package com.redhat.gss.support.jms.messy;

public class PrimeRun {


	public static void main(String args[]) {
		long b = 143;
	     PrimeThread p = new PrimeThread(b);
	     new Thread(p).start();



}
}