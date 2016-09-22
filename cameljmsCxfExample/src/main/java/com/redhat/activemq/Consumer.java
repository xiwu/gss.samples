package com.redhat.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {

    private static String brokerURL = "tcp://localhost:61616";
    private static transient ConnectionFactory factory;
    private transient Connection connection;
    private transient static Session session;
    public static int totalTopicCount  = 200;
    public static int consumerCount_perTopic = 1;
    
    public Consumer() throws JMSException {
    	factory = new ActiveMQConnectionFactory(brokerURL);
    	connection = factory.createConnection("admin","admin");
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
    }
    
    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }    
    
    public static void main(String[] args) throws JMSException {
    	Consumer consumer = new Consumer();
    	
    	for(int i = 0 ; i < totalTopicCount; i++) {
			Destination destination = consumer.getSession().createQueue(
					"Consumer.A.VirtualTopic.TEST" + i);
			for(int j = 0 ; j < consumerCount_perTopic; j++) {
			MessageConsumer messageConsumer = session
					.createConsumer(destination);
			messageConsumer.setMessageListener(new Listener());
			}
    	}
    }
	public Session getSession() {
		return session;
	}

}
