package com.redhat.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class IDTest {
    private static String brokerURL = "tcp://localhost:61616";
    private static transient ConnectionFactory factory;
    private transient Connection connection;
    private transient static Session session;
    public static int totalTopicCount  = 200;
    public static int consumerCount_perTopic = 1;

    public IDTest() throws JMSException, InterruptedException {
    	factory = new ActiveMQConnectionFactory(brokerURL);
    	connection = factory.createConnection("admin","admin");
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        System.out.println("connection:" + connection);
        System.out.println("session"+ session);
        Session session2 = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        System.out.println("session2"+ session2);
        Thread.currentThread().sleep(10000);
        Connection  connection2 = factory.createConnection("admin","admin");
        connection2.start();
        System.out.println("connection2:" + connection2);
    }
    public static void main(String[] args) throws JMSException, InterruptedException {
    	IDTest idtest = new IDTest();
    	
	}
}
