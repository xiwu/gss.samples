package com.test.queue;

import java.io.*;

class Test {
     public static void main(String args[]) throws Exception {
//    	 System.out.println("heell");
    	 Thread.currentThread().sleep(100*1000);
           RandomAccessFile f = new RandomAccessFile("tttttt", "rw");
           f.setLength(1024 * 1024 * 1024);
           
//           System.out.println("erere");
     }
}
