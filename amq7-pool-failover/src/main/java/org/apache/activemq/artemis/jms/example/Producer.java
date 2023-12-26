package org.apache.activemq.artemis.jms.example;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.commons.lang3.StringUtils;
import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Producer {

    private static JmsPoolConnectionFactory amqConnectionPool = null;
    private static ActiveMQConnectionFactory factory = null;
    private static String conUser = "admin";
    private static  String conPwd = "admin";
    private static String connectionUrl = "(tcp://localhost:61616,tcp://localhost:62616)?ha=true&reconnectAttempts=2&retryInterval=500&retryIntervalMultiplier=1.0&useTopologyForLoadBalancing=false&clientFailureCheckPeriod=5000";
    // below worked well
//    private static String connectionUrl = "(tcp://localhost:61616,tcp://localhost:62616)?ha=true&retryInterval=100&retryIntervalMultiplier=1.0&reconnectAttempts=30&clientFailureCheckPeriod=30000&consumerWindowSize=4000";
    //(tcp://hostname1:61616,tcp://hostname2:61716,tcp://hostname3:61816)?ha=true&reconnectAttempts=2&retryInterval=500&retryIntervalMultiplier=1.0&useTopologyForLoadBalancing=false
//(tcp://localhost:61616,tcp://localhost:61616)?retryInterval=100;retryIntervalMultiplier=1.0;reconnectAttempts=30;consumerWindowSize=4000
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static JmsPoolConnectionFactory getAmqPoolConnectionFactory() {
//            synchronized (AmqConnectionUtility.class) {
                if (amqConnectionPool == null) {
                    try {
                        logger.info("Connection pool factory is null, Thus initializing");
                        factory = new ActiveMQConnectionFactory(connectionUrl, conUser, conPwd);
                        amqConnectionPool = new JmsPoolConnectionFactory();
                        amqConnectionPool.setConnectionFactory(factory);
                        amqConnectionPool.setMaxConnections(8);
                        amqConnectionPool.setUseAnonymousProducers(false);
                        logger.info("Amq Pooled Connection Factory created : " + amqConnectionPool);

                    } catch (Exception ex) {
                        logger.error("Error initializing Pooled Connection Factory for AMQ", ex);
                    }
//                }
                }
        return amqConnectionPool;
    }

    public static void shutDownAmqConnectionFactory() {

        if (amqConnectionPool != null) {
            amqConnectionPool.stop();
            logger.info("The amq poolFactory has been stopped");
        } else {
            logger.info("The amq connection pool factory has already been Shutdown.");
        }

        if (factory != null) {
            factory.close();
            logger.info("The amq connection factory has been shutdown");
        } else {
            logger.info("The amq connection factory has already been Shutdown.");
        }
    }

    public static Connection getAmqConnectionFromPool() {
        try {
            return getAmqPoolConnectionFactory().createConnection(conUser, conPwd);

        } catch (Exception e) {
            logger.error("Error initializing Connection for AMQ", e);
        }
        return null;
    }

    public void sendMessageToAMQ(String messagetxt, String destinationName, String listenerConfigFile, int retry) throws JMSException {

        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;

        try {

            connection = getAmqConnectionFromPool();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(destinationName);

            producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage(messagetxt);
            producer.send(message);
            logger.info("send message " + messagetxt  +  "to destination: " + destinationName);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Send message to Jboss AMQ Destination: " + destinationName + " failed. JMS Exception:"
                    + e.getMessage(), e);
        } finally {
            closeResources(connection, session);
        }
    }

    private void closeResources(Connection connection, Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (JMSException e) {
                logger.error(e.getMessage(), e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                logger.error(e.getMessage(), e);
//                throw e;
            }
        }
    }

}
