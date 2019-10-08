package com.redhat.gss.support.jms.messy;

public class PrimeThread implements Runnable{

        long minPrime;
        PrimeThread(long minPrime) {
            this.minPrime = minPrime;
        }

        public void run() {
        	System.out.print("run" + minPrime);
        }
    }
