package com.codenotfound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.codenotfound.jms.Sender;

@SpringBootApplication
public class SpringJmsApplication {

	public static void main(String[] args) throws InterruptedException {
	  final ConfigurableApplicationContext context =  SpringApplication.run(SpringJmsApplication.class, args);

//		JmsPoolConnectionFactory connectionFactory = context.getBean(JmsPoolConnectionFactory.class);
//		context.getBeansOfType(JmsPoolConnectionFactory.class);
//		System.out.println(connectionFactory==null?"null":connectionFactory);
    System.out.println("********************* Sending message...");
    Sender sender = context.getBean(Sender.class);
	  long sleeptime = 30000;
	  int messageCount = 100;
	  try { 
	    for (int i = 0; i < messageCount; i++) {
	    	String message = "This is message" + i;
	        sender.send(message);
	        System.out.println("Sent message: " + message);
	        System.out.println("Sleep " + sleeptime);
	        Thread.currentThread().sleep(sleeptime);
	      }
	  } catch(Exception e) {
		  e.printStackTrace();
    	  System.out.println("after exception , Sleep " + sleeptime);
    	  Thread.currentThread().sleep(sleeptime);
    	  // finally still failed, then failed
    	  sender.send("wrong");
	  }

  }
}
