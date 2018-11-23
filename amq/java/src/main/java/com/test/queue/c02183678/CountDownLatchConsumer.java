package com.test.queue.c02183678;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchConsumer implements MessageListener{
	   private static final String BROKER_URL = "failover:(tcp://10.66.192.3:61616,tcp://10.66.192.3:61616)?randomize=false&initialReconnectDelay=10000&maxReconnectDelay=10000&maxReconnectAttempts=-1";

	    private static final Boolean NON_TRANSACTED = false;


	    private final CountDownLatch countDownLatch;
	    public CountDownLatchConsumer(CountDownLatch latch) {
	        countDownLatch = latch;
	    }

	    public static void main(String[] args) {
	        String url = BROKER_URL;
	        if (args.length > 0) {
	            url = args[0].trim();
	        }
	        System.out.println("\nWaiting to receive messages... Either waiting for END message or press Ctrl+C to exit");
	        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", url);
	        Connection connection = null;
	        final CountDownLatch latch = new CountDownLatch(1);

	        try {

	            connection = connectionFactory.createConnection();
//	            String clientId = System.getProperty("clientId");
//	            connection.setClientID(clientId);

	            connection.start();

	            Session session = connection.createSession(NON_TRANSACTED, Session.AUTO_ACKNOWLEDGE);
	            Queue queque = session.createQueue("slimsmart.queque.test"); 

	            MessageConsumer consumer = session.createConsumer(queque);  
	            consumer.setMessageListener(new CountDownLatchConsumer(latch));

	            latch.await();
	            consumer.close();
	            session.close();

	        } catch (Exception e) {
	        	e.printStackTrace();
	            System.out.println("Caught exception!");
	        }
	        finally {
	            if (connection != null) {
	                try {
	                    connection.close();
	                } catch (JMSException e) {
	                    System.out.println("Could not close an open connection...");
	                }
	            }
	        }
	    }

	    @Override
	    public void onMessage(Message message) {
	        try {
	            if (message instanceof TextMessage) {
	                String text = ((TextMessage) message).getText();
	                if ("END".equalsIgnoreCase(text)) {
	                    System.out.println("Received END message!");
	                    countDownLatch.countDown();
	                }
	                else {
	                    System.out.println("Received message:" +text);
	                }
	            }
	        } catch (JMSException e) {
	        	e.printStackTrace();
	            System.out.println("Got a JMS Exception!");
	        }
	    }
}
