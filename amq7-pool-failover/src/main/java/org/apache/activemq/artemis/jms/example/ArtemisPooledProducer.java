package org.apache.activemq.artemis.jms.example;


import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

import java.util.concurrent.*;

public class ArtemisPooledProducer {

    private static final int MAX_POOL_SIZE = 10;
    private static final int THREAD_POOL_SIZE = 5;
    //    private static final String brokerURL = "(tcp://localhost:61616,tcp://localhost:61617,tcp://localhost:61618)?ha=true&ackBatchSize=0&blockOnAcknowledge=true&randomize=true&consumerWindowSize=0&sslEnabled=true&";
    private static final String brokerURL = "(tcp://localhost:61616,tcp://localhost:61617,tcp://localhost:61618)?ha=true";
    private static final String destinationName = "test-queue";
    private static volatile JmsPoolConnectionFactory amqConnectionPool = null;
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    public static void main(String[] args) {

        // System.setProperty("org.apache.activemq.ssl.trustStore", Config.getTruststore());
        // System.setProperty("org.apache.activemq.ssl.trustStorePassword", Config.getTrustStorePassword());
        // System.setProperty("org.apache.activemq.ssl.keyStore", Config.getKetStore());
        // System.setProperty("org.apache.activemq.ssl.keyStorePassword", Config.getKetStorePassword());
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
        factory.setUser("admin");
        factory.setPassword("admin");
        factory.setReconnectAttempts(1); // Infinite retry attempts
        factory.setRetryInterval(100);  // Retry every 5 seconds
        factory.setRetryIntervalMultiplier(1.0); // No exponential backoff
        factory.setUseTopologyForLoadBalancing(true); // Load balancing among brokers
        logger.info("The amq connection pool factory initialized!");
        // Initialize JmsPoolConnectionFactory and set the connection factory
        amqConnectionPool = new JmsPoolConnectionFactory();
        amqConnectionPool.setConnectionFactory(factory);
        amqConnectionPool.setMaxConnections(MAX_POOL_SIZE);

        amqConnectionPool.setConnectionCheckInterval(1000);
        amqConnectionPool.setConnectionIdleTimeout(3000);


        // Create session and producer outside the loop
        // Get a connection from the pool
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        for (int i = 0; i < 10000000; i++) {
            final int messageId = i;
            executorService.submit(() -> {
                try (Connection connection = amqConnectionPool.createConnection()) {
                    logger.info("connection:" + connection);
                    connection.start();
                    try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)){
                        Queue queue = session.createQueue(destinationName);
                        TextMessage message = session.createTextMessage("Message " + messageId);
                        MessageProducer producer = session.createProducer(queue);
                        producer.send(message);
                        System.out.println("Sent: " + message.getText());
                    }

                    // Create session and producer
                } catch (JMSException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
        executorService.shutdown();
    }
}

