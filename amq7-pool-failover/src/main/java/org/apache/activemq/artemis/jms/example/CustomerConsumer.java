package org.apache.activemq.artemis.jms.example;


import org.apache.activemq.artemis.jms.client.*;
import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Message;
import javax.jms.TextMessage;

public class CustomerConsumer {

    private static JmsPoolConnectionFactory amqConnectionPool = null;
    private static ActiveMQConnectionFactory factory = null;
    private static String conUser = "admin";
    private static  String conPwd = "admin";

    private static String connectionUrl = "(tcp://localhost:61616,tcp://localhost:62616)?ha=true&reconnectAttempts=-1&retryInterval=500&retryIntervalMultiplier=1.0&useTopologyForLoadBalancing=false&clientFailureCheckPeriod=5000";
    // below worked well
    //    private static String connectionUrl = "(tcp://localhost:61616,tcp://localhost:62616)?ha=true&retryInterval=100&retryIntervalMultiplier=1.0&reconnectAttempts=30&clientFailureCheckPeriod=30000&consumerWindowSize=4000";
    //(tcp://hostname1:61616,tcp://hostname2:61716,tcp://hostname3:61816)?ha=true&reconnectAttempts=2&retryInterval=500&retryIntervalMultiplier=1.0&useTopologyForLoadBalancing=false
//(tcp://localhost:61616,tcp://localhost:61616)?retryInterval=100;retryIntervalMultiplier=1.0;reconnectAttempts=30;consumerWindowSize=4000
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private ActiveMQQueue queue = null;
    private ActiveMQConnection connection = null;
    private ActiveMQSession session = null;
    private ActiveMQMessageConsumer consumer = null;
    private TextMessage message = null;
    private String queueName = null;


    public Message polling(String destinationName, String listenerConfigFile, int remainderQueueNum) {

        try {
            startConnection(destinationName);
            message = (TextMessage) consumer.receive(100);

            if (message != null) {
                logger.info("Received message: queueName=" + destinationName + " message: " + message.getText());
            }
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Polling message from  Destination: " + destinationName + " failed. JMS Exception:"
                    + e.getMessage(), e);
        } finally {
            if (message != null) {
                try {
                    message.acknowledge();
                    message = null;
                    logger.info("The acknowledgement for message to AMQ broker has been done.");
                } catch (Exception e) {
                    logger.error("There is some issue in acknowledgement to the AMQ Broker:", e);
                }
            }
        }
        return null;
    }

    public void shutdown() {
        if (consumer != null) {
            try {
                consumer.close();
                consumer = null;
                logger.info("Consumer to AMQ closed");
            } catch (Throwable e) {
                logger.error("Consumer to AMQ can not be closed", e);
            }
        }
        if (session != null) {
            try {
                session.close();
                session = null;
                logger.info("Session to AMQ closed");
            } catch (Throwable e) {
                logger.error("Session to AMQ can not be closed", e);
            }
        }
        if (connection != null) {
            try {
                connection.stop();
                connection.close();
                connection = null;
                logger.info("Connection to AMQ closed");
            } catch (Throwable e) {
                logger.error("Connection to AMQ can not be closed", e);
            }
        }
    }

    private void startConnection(String queueName) {
        try {
            if (connection == null || !(connection.isStarted())) {
                connection = getAmqConnection();
                connection.start();
            }
            if (session == null || session.getCoreSession() == null || session.getCoreSession().isClosed()) {
                session = (ActiveMQSession) connection.createSession(false, ActiveMQSession.CLIENT_ACKNOWLEDGE);
            }
            if (queue == null) {
                queue = (ActiveMQQueue) session.createQueue(queueName);
            }
            if (consumer == null || consumer.isClosed()) {
                consumer = (ActiveMQMessageConsumer) session.createConsumer(queue);
            }
        } catch (Exception e) {
            logger.error("Can't Create AMQ Connection and Start Session", e);
            throw new RuntimeException("Can't Create AMQ Connection and Start Session", e);
        }
    }

    public static ActiveMQConnection getAmqConnection() {
        try {
            return (ActiveMQConnection) getAmqConnectionFactory(connectionUrl).createConnection(conUser, conPwd);
        } catch (Exception e) {
            logger.error("Error initializing Connection for AMQ", e);
            return null;
        }
    }

    private static ActiveMQConnectionFactory getAmqConnectionFactory(String conURL) {

                if (factory == null) {
                    try {
                        logger.info("Connection Factory is null, Thus initializing");
                        factory = new ActiveMQConnectionFactory(conURL);
                        logger.info("factory : " + factory);
                    } catch (Exception ex) {
                        logger.error("Error initializing Connection Factory for AMQ", ex);
                    }
                }
        return factory;
    }
}
