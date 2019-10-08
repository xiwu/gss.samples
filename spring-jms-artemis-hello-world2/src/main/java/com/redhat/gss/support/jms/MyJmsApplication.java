package com.redhat.gss.support.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MyJmsApplication {

  public static void main(String[] args) {
	  final ConfigurableApplicationContext context =  SpringApplication.run(MyJmsApplication.class, args);

//		JmsPoolConnectionFactory connectionFactory = context.getBean(JmsPoolConnectionFactory.class);
//		context.getBeansOfType(JmsPoolConnectionFactory.class);
//		System.out.println(connectionFactory==null?"null":connectionFactory);
    System.out.println("********************* Sending message...");
//    Sender sender = context.getBean(Sender.class);
//    sender.send("hello");;
  }
}
