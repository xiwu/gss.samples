package org.mycompany;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloBean implements Hello {

    private String say = "Hello World";

    public String hello() {
    	
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        	System.out.println("=====" + Thread.currentThread().getName() + "====sleep for 3000*10====");
        	System.out.println();
			Thread.currentThread().sleep(300*10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return say + " at " + sdf.format(new Date());
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }
}
