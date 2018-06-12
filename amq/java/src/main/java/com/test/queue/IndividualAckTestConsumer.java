package com.test.queue;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory; 

/***
 * 测试不回ack，amq是否会重发消息
 * @author suo
 *
 */
public class IndividualAckTestConsumer {

	public static void main(String[] args) throws JMSException, InterruptedException{
		// TODO Auto-generated method stub
        // 连接到ActiveMQ服务器  
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");  
        Connection connection = factory.createConnection();  
        testUnAckedMessageAreNotConsumedOnSessionClose(connection);
  
	}
	
    public static void testUnAckedMessageAreNotConsumedOnSessionClose(Connection connection) throws JMSException, InterruptedException {
        connection.start();
        Session session = connection.createSession(false, org.apache.activemq.ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        Queue queue = session.createQueue("slimsmart.queque.test");
        MessageProducer producer = session.createProducer(queue);
        producer.send(session.createTextMessage("Hello"));

        // Consume the message...
        MessageConsumer consumer = session.createConsumer(queue);
        Message msg = consumer.receive(2000);
        TextMessage tm = (TextMessage) msg; 
        System.out.println("Received message " +  ": " + tm.getText());
//        assertNotNull(msg);
        // Don't ack the message.

        Thread.currentThread().sleep(15000);
        // Reset the session.  This should cause the unacknowledged message to be re-delivered.
        session.close();
    }
    
    public static void test2UnAckedMessageAreNotConsumedOnSessionClose(Connection connection) throws JMSException, InterruptedException {
        connection.start();
        Session session = connection.createSession(false, org.apache.activemq.ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        Queue queue = session.createQueue("slimsmart.queque.test");
        MessageProducer producer = session.createProducer(queue);
        producer.send(session.createTextMessage("Hello"));

        // Consume the message...
        MessageConsumer consumer = session.createConsumer(queue);
        Message msg = consumer.receive(2000);
        TextMessage tm = (TextMessage) msg; 
        System.out.println("Received message " +  ": " + tm.getText());
//        assertNotNull(msg);
        // Don't ack the message.

        Thread.currentThread().sleep(10000);
        // Reset the session.  This should cause the unacknowledged message to be re-delivered.
        session.close();
        session = connection.createSession(false, org.apache.activemq.ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);

        // Attempt to Consume the message...
        consumer = session.createConsumer(queue);
        msg = consumer.receive(2000);
//        assertNotNull(msg);
        
        msg.acknowledge();
//        TextMessage tm = (TextMessage) msg; 
        System.out.println("Received message " +  ": " + tm.getText());
        session.close();
    }
}
