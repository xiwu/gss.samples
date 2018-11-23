package com.test.queue.c02183678;

import java.net.URISyntaxException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ReceiveConsumer {
	public static void main(String[] args) throws URISyntaxException, Exception {
		Connection connection = null;
//		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
//				"tcp://localhost:61616");
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin","failover:(tcp://10.66.192.3:61616,tcp://10.66.192.3:61616)?randomize=false&initialReconnectDelay=10000&maxReconnectDelay=10000&maxReconnectAttempts=-1");
		connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		try {
			Queue queue = session.createQueue("customerQueue");

			// Consumer
			MessageConsumer consumer = session.createConsumer(queue);
			TextMessage textMsg = (TextMessage) consumer.receive();
			System.out.println(textMsg);
			System.out.println("Received: " + textMsg.getText());			
		} finally {
			if (session != null) {
				session.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}
