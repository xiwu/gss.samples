package com.redhat.activemq;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//import org.apache.activemq.util.Wait;

public class SchedulerTest {
    private static String brokerURL = "tcp://localhost:61616";
    private static transient ConnectionFactory factory;
    private transient Connection connection;
    private transient static Session session;
    private transient static Destination destination;
 
    @Before
    public void setUp() throws Exception {
    	factory = new ActiveMQConnectionFactory(brokerURL);
    	connection = factory.createConnection("admin","admin");
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);    
		 destination = session.createQueue(
				"wxhtest.queue.schduler");
    }	
    @After
    public void tearDown() throws Exception {
        if (connection != null) {
            connection.close();
        }
        if(session !=null) {
        	session.close();
        }
        
        
    }   
    @Test
    public void testSchedule() throws Exception {
        final int COUNT = 1;
        MessageConsumer consumer = session.createConsumer(destination);
        

        final CountDownLatch latch = new CountDownLatch(COUNT);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
            	TextMessage txt = (TextMessage)message;
            	System.out.println("received message: " + txt);
                latch.countDown();
            }
        });

//        connection.start();
        long time = 50000;
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage("test msg");

        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);

        producer.send(message);
        producer.close();
        // make sure the message isn't delivered early
        Thread.sleep(20000);
        assertEquals(latch.getCount(), COUNT);
        latch.await(500, TimeUnit.SECONDS);
        assertEquals(latch.getCount(), 0);
    }

}
