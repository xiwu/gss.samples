package com.test.queue.c02183678;

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
public class ListenerConsumer {

	public static void main(String[] args) throws JMSException{
		// TODO Auto-generated method stub
        // 连接到ActiveMQ服务器  
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","failover:(tcp://10.66.192.3:61616,tcp://10.66.192.3:61616)?randomize=false&initialReconnectDelay=10000&maxReconnectDelay=10000&maxReconnectAttempts=-1");  
        Connection connection = factory.createConnection();  
//        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
//        redeliveryPolicy.setInitialRedeliveryDelay(5000);
//        redeliveryPolicy.setRedeliveryDelay(5000);
//        redeliveryPolicy.setMaximumRedeliveries(-1);
//        ((ActiveMQConnection)connection).setRedeliveryPolicy(redeliveryPolicy);
        
        //客户端ID,持久订阅需要设置  
        connection.start();  
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);  
        // 创建主题  
        Queue queque = session.createQueue("slimsmart.queque.test"); 
        // 创建持久订阅,指定客户端ID。  
        MessageConsumer consumer = session.createConsumer(queque);  
        //MessageConsumer consumer = session.createConsumer(topic); 
        consumer.setMessageListener(new MessageListener() {  
        	private int count = 0;
            // 订阅接收方法 
        	@Override
            public void onMessage(Message message) {  
                TextMessage tm = (TextMessage) message;  
                try {  
                    System.out.println("Received message " +  ": " + tm.getText()+":"+tm.getIntProperty("sequence"));
                    //手动回ACK 消息真正被消费掉，不会Pending
                    //不回ACK，虽然能收到，但是pending（再启动Consumer依然能收到）
                    throw new RuntimeException();
//                    tm.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();  
                }  
            }  
        }); 
        
        while(true) {
        	
        }
        
        
  
	}


}
