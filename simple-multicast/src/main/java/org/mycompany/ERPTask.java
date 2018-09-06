package org.mycompany;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.AsyncCallback;
import org.apache.camel.Exchange;

public class ERPTask implements Runnable {

    private  Exchange exchange;
//    private final AsyncCallback callback;
    private String say = "Hello Custom";
    private String msgId= "";
    public ERPTask() {
    	
    }
    public ERPTask(Exchange exchange, String msgId) {
        this.exchange = exchange;	
        this.msgId = msgId;
//        this.callback = callback;
    }

    public void setExchange(Exchange exchange) {
    	this.exchange = exchange;
    }
    
    public void SetMsgid(String msgId) {
    	this.msgId = msgId;
    }
    public void run() {
        // set reply
//        String in = exchange.getIn().getBody(String.class);
        exchange.getOut().setBody(hello());
//        callback.done(false);
    }
    public String hello() {
    	
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
        	System.out.println("XXXX" + Thread.currentThread().getName() + "XXXX sleep for 3000*10 XXXX");
        	System.out.println();
			Thread.currentThread().sleep(3000*10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return say + " at " + sdf.format(new Date());
    }
}