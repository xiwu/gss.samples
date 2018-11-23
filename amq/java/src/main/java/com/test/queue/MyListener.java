package com.test.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyListener implements MessageListener, Runnable{

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub

            TextMessage tm = (TextMessage) message;  
            try {  
                System.out.println("Received message " +  ": " + tm.getText());
                //手动回ACK 消息真正被消费掉，不会Pending
                //不回ACK，虽然能收到，但是pending（再启动Consumer依然能收到）
                Thread.currentThread().sleep(4000*10);
                System.out.println("=========ACKed==========");
//                throw new RuntimeException();
                tm.acknowledge();
            } catch (Exception e) {
                e.printStackTrace();  
            }
        }  		


	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("run");	
	}

}
