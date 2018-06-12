package amqtest.demo.slowconsumer.strategy;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactedSimpleConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(TransactedSimpleConsumer.class);

    private static final int MESSAGE_TIMEOUT_MILLISECONDS = 12000*1000;

    public static void main(String args[]) {	
        Connection connection = null;

        try {
        	// 连接到ActiveMQ服务器
        	//ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","failover:(tcp://localhost:61616,tcp://localhost:61616)?randomize=false&initialReconnectDelay=100&maxReconnectDelay=10000"); 
        	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","failover:(tcp://localhost:61616?trace=true,tcp://localhost:61618?trace=true)");
//        	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","failover:tcp://localhost:61616?randomize=false&initialReconnectDelay=100&maxReconnectDelay=10000");
        	 connection = factory.createConnection(); 
        	//客户端ID,持久订阅需要设置 
        	connection.start();
        	Session session = connection.createSession(Boolean.TRUE, org.apache.activemq.ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE); 
        	// 创建主题 
        	Queue queque = session.createQueue("slimsmart.queque.test"); 
        	// 创建持久订阅,指定客户端ID。 
        	MessageConsumer consumer = session.createConsumer(queque);
//            LOG.info("Start consuming messages from " + destination.toString() + " with " + MESSAGE_TIMEOUT_MILLISECONDS + "ms timeout");

            // Synchronous message consumer
            int i = 1;
            while (true) {
                Message message = consumer.receive(MESSAGE_TIMEOUT_MILLISECONDS);
                if (message != null) {
                    if (message instanceof TextMessage) {
//                    	if(i == 3 ) {
////                    		message.acknowledge();
//                    	} else {
//                    		message.acknowledge();
//                    	}
                        String text = ((TextMessage) message).getText();
                        LOG.info("Got " + (i++) + ". message: " + text);
                        Thread.currentThread().sleep(20000);
                        message.acknowledge();
                    }
                } else {
                    break;
                }
            }

            consumer.close();
            session.close();
        } catch (Throwable t) {
            LOG.error("error55",t);
        } finally {
            // Cleanup code
            // In general, you should always close producers, consumers,
            // sessions, and connections in reverse order of creation.
            // For this simple example, a JMS connection.close will
            // clean up all other resources.
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    LOG.error("error66",e);
                }
            }
        }
    }
}