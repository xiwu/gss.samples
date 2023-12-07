package amqtest.demo.slowconsumer.strategy;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

public class AbortSlowAckConsumerStrategyTest {
    private static final Logger LOG = LoggerFactory.getLogger(AbortSlowAckConsumerStrategyTest.class);

    private static final int MESSAGE_TIMEOUT_MILLISECONDS = 12000*1000;

    public static void main(String args[]) {
        Connection connection = null;

        try {
            // 连接到ActiveMQ服务器
            //ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","failover:(tcp://localhost:61616,tcp://localhost:61616)?randomize=false&initialReconnectDelay=100&maxReconnectDelay=10000");
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616?trace=true&useKeepAlive=false&wireFormat.maxInactivityDuration=20000");
//            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616?trace=true&useKeepAlive=false&wireFormat.maxInactivityDuration=20000");
//        	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","failover:tcp://localhost:61616?randomize=false&initialReconnectDelay=100&maxReconnectDelay=10000");
            connection = factory.createConnection();
            //客户端ID,持久订阅需要设置
            connection.start();
            Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
            // 创建主题
            Queue queue = session.createQueue("slimsmart.queque.test");


            // 发送消息

            MessageProducer producer = session.createProducer(queue);
//
//            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//            TextMessage message = session.createTextMessage();
//            message.setText("test message!");
//            message.setStringProperty("property", "XXXX message Property");
//            // ·¢²ŒÖ÷ÌâÏûÏ¢
//            for(int i = 0; i < 10; ++i)
//            {
//                message.setText("topic message " + i + " " + new Date());
//                producer.send(message);
//                LOG.info("Sent message: " + message.getText());
//            }
            // 创建持久订阅,指定客户端ID。
            MessageConsumer consumer = session.createConsumer(queue);
            TimeUnit.SECONDS.sleep(1250);
//            LOG.info("Start consuming messages from " + destination.toString() + " with " + MESSAGE_TIMEOUT_MILLISECONDS + "ms timeout");

            // Synchronous message consumer
            int i = 1;

//            while (true) {
//                Message message2 = consumer.receive(MESSAGE_TIMEOUT_MILLISECONDS);
//                if (message2 != null) {
//                    if (message2 instanceof TextMessage) {
//                        String text = ((TextMessage) message).getText();
//                        LOG.info("Got " + (i++) + ". message: " + text);
//                    }
//                    TimeUnit.SECONDS.sleep(25);
//                } else {
//                    break;
//                }
//
//            }
            producer.close();
            consumer.close();
            session.close();
        } catch (Throwable t) {
            t.printStackTrace();
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

