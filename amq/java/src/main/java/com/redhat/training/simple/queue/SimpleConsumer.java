package com.redhat.training.simple.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SimpleConsumer {
    private static final Log LOG = LogFactory.getLog(SimpleConsumer.class);

    private static final Boolean NON_TRANSACTED = false;
    private static final Boolean TRANSACTED = true;
    private static final String CONNECTION_FACTORY_NAME = "myJmsFactory";
    private static final String DESTINATION_NAME = "queue/simple";
    private static final int MESSAGE_TIMEOUT_MILLISECONDS = 120000000;

    public static void main(String args[]) {    	
       
		try {
			 Context context = new InitialContext();
			 ConnectionFactory factory = (ConnectionFactory) context.lookup(CONNECTION_FACTORY_NAME);		     
			 Destination destination = (Destination) context.lookup(DESTINATION_NAME);		        
		     
			 ActiveMQConnection connection = (ActiveMQConnection) factory.createConnection("admin", "admin");		     
			 RedeliveryPolicy policy = connection.getRedeliveryPolicy();		     
			 policy.setInitialRedeliveryDelay(0);		     
			 policy.setMaximumRedeliveries(5);		     
			 policy.setRedeliveryDelay(1000);		        
		     
			 connection.start();		     
			 final Session session = connection.createSession(TRANSACTED, ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
		     
			 MessageConsumer consumer = session.createConsumer(destination);
			 consumer.setMessageListener(new MessageListener() {
		            public void onMessage(Message message) {
		                try {
		                    if (message instanceof TextMessage) {
		                        TextMessage textMessage = (TextMessage) message;
		                        String text = textMessage.getText();
		                        System.out.println("Consuming message: " + text);
		                        if(text.contains("RD")){
		                            System.out.println("Acknowledged This Message.");
		                            message.acknowledge();
		                        } else{
		                        	session.rollback();
		                            System.out.println("Regetting the message.");		                           
		                        }
		                        session.commit();
		                    }else{
		                        System.out.println("Consuming Weird Message: " + message);
		                    }
		                } catch (JMSException e) {
		                    e.printStackTrace();
		                }
		            }
		        });	        
		} catch (Exception e) {			
			e.printStackTrace();
		}
    }
}
