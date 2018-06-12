package amqtest.demo.slowconsumer.strategy;

import java.util.Date;

import javax.jms.Connection;  
import javax.jms.DeliveryMode;  
import javax.jms.JMSException;  
import javax.jms.MessageProducer;  
import javax.jms.Session;  
import javax.jms.TextMessage;  
import javax.jms.Queue;  
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

public class QueueProducer {
	private static final Logger LOG = LoggerFactory.getLogger(FailOverIndividualACKQueueConsumer.class);
	public static void main(String[] args) throws JMSException {  
		// 连接到ActiveMQ服务器
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","failover:(tcp://localhost:61616?trace=true,tcp://localhost:61618?trace=true)");  
        Connection connection = factory.createConnection();  
        connection.start();  
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE); 
     // 创建主题
        //Topic topic = session.createTopic("slimsmart.topic.test");  
        Queue queque = session.createQueue("slimsmart.queque.test");
        MessageProducer producer = session.createProducer(queque);  
     // NON_PERSISTENT 非持久化 PERSISTENT 持久化,发送消息时用使用持久模式 
        //producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
        producer.setDeliveryMode(DeliveryMode.PERSISTENT); 
        TextMessage message = session.createTextMessage();  
        message.setText("topic message topic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic messagetopic message");  
        message.setStringProperty("property", "XXXX message Property");  
        // ·¢²ŒÖ÷ÌâÏûÏ¢  
        for(int i = 0; i < 10; ++i)
        {
        	message.setText("topic message " + i + " " + new Date()); 
        	producer.send(message);
        	LOG.info("Sent message: " + message.getText());    
        }
        session.commit();  
        session.close();  
        connection.close();  
    }  
}
