package com.activemq.test;

import javax.jms.Connection;


import javax.jms.ConnectionFactory;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;

public class AMQPTest2 {



public static void main(String[] args) throws JMSException {

 //String sslUri = "failover:(ssl://amqdv1.bnsf.com:61616,ssl://amqdv2.bnsf.com:61616)";
 //JmsConnectionFactory factory = new JmsConnectionFactory("failover:(amqp://amqdv1.bnsf.com:5672,amqp://amqdv2.bnsf.com:5672)");
//	JmsConnectionFactory factory = new JmsConnectionFactory("failover:(amqp://localhost:5672,amqp://10.66.218.176:5672)");
	JmsConnectionFactory factory = new JmsConnectionFactory("failover:(amqp://localhost:5672,amqp://localhost:5872)");
	
 Connection connection = factory.createConnection("admin","admin");
 connection.start();
 Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
 Queue destination = session.createQueue("AMQPTESTQUEUE");
 
 MessageProducer producer = session.createProducer(destination);
TextMessage message = session.createTextMessage("test message");
//Here we are sending the message!

producer.send(message);
System.out.println("Sent '" + message.getText() + "'");

  
 
    /*try {
		cf.setTrustStore("truststore_server.jks");
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    cf.setTrustStorePassword("kafka123"); */
   
connection.close();
}
}