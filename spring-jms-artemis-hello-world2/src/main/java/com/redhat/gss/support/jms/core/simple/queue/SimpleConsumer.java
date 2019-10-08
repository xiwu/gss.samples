package com.redhat.gss.support.jms.core.simple.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;


import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleConsumer {
    private static final Log LOG = LogFactory.getLog(SimpleConsumer.class);

    private static final Boolean NON_TRANSACTED = false;
    private static final Boolean TRANSACTED = true;
    private static final String CONNECTION_FACTORY_NAME = "myJmsFactory";
    private static final String DESTINATION_NAME = "queue/simple";
    private static final int MESSAGE_TIMEOUT_MILLISECONDS = 120000000;

    public static void main(String args[]) {    	
       
        String connUri = "tcp://localhost:61616";
        

        ConnectionFactory factory = new ActiveMQConnectionFactory(connUri);
        ActiveMQConnectionFactory amqcf = (ActiveMQConnectionFactory) factory;
        amqcf.getServerLocator().setConnectionTTL(1200000);
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        
        try {
         connection = factory.createConnection(); 	        
		     
			 connection.start();		     
			 session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			 Queue queue = session.createQueue("slimsmart.queque.test");
			  consumer = session.createConsumer(queue);
			  System.out.println("created consumer");
			  Thread.sleep(20000);
			 consumer.setMessageListener(new MessageListener() {
		            public void onMessage(Message message) {
		                try {
		                    if (message instanceof TextMessage) {
		                        TextMessage textMessage = (TextMessage) message;
		                        String text = textMessage.getText();
		                        System.out.println("Consuming message: " + text);
		                        message.acknowledge();
		                    }else{
		                        System.out.println("Consuming Weird Message: " + message);
		                    }
		                } catch (JMSException e) {
		                    e.printStackTrace();
		                }
		            }
		        });	        
		}  
catch(Exception e) {
    	e.printStackTrace();
    }finally {
    	try {
    	consumer.close();
    	session.close();
    	connection.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}

}