package com.redhat.gss.support.jms.core.simple.queue;

import java.text.SimpleDateFormat;
import java.util.Date;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

//import org.apache.activemq.ActiveMQConnectionFactory; 
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
/***
 * 测试不回ack，amq是否会重发消息
 * @author suo
 *
 */
public class ClientAckConsumer {

	public static void main(String[] args) throws JMSException{
		// TODO Auto-generated method stub
        // 连接到ActiveMQ服务器  
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");  
        String connUri = "tcp://localhost:61616";
        

        ConnectionFactory factory = new ActiveMQConnectionFactory(connUri);
        ActiveMQConnectionFactory amqcf = (ActiveMQConnectionFactory) factory;
        amqcf.getServerLocator().setConnectionTTL(1200000);
        Connection connection = factory.createConnection();  

        try {
			testUnAckedMessageAreNotConsumedOnSessionClose(connection);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
//        redeliveryPolicy.setInitialRedeliveryDelay(5000);
//        redeliveryPolicy.setRedeliveryDelay(5000);
//        redeliveryPolicy.setMaximumRedeliveries(-1);
//        ((ActiveMQConnection)connection).setRedeliveryPolicy(redeliveryPolicy);
/**        
        //客户端ID,持久订阅需要设置  
        connection.start();  
        Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);  
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
  **/
	}
	
    public static void testUnAckedMessageAreNotConsumedOnSessionClose(Connection connection) throws JMSException, InterruptedException {
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue("slimsmart.queque.test");
        MessageProducer producer = session.createProducer(queue);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String text = "Hello" + formatter.format(date);
        
        System.out.println("sending message to slimsmart.queque.test " +  ": " + text);
        producer.send(session.createTextMessage(text));


        // Attempt to Consume the message...
        MessageConsumer  consumer = session.createConsumer(queue);
//        Thread.sleep(10000);
        Message msg = consumer.receive(1000);
//        assertNotNull(msg);
        if(msg == null) {
        	System.out.println("retry to receive the messages");
//        	Thread.sleep(1000);
        	msg = consumer.receive(1000);
        	if(msg == null) {
            	System.out.println("retry 2nd to receive the messages");
//            	Thread.sleep(1000);
            	msg = consumer.receive(1000);
            	
            }
        	if(msg == null) {
            	System.out.println("retry 3rd to receive the messages");
//            	Thread.sleep(10000);
            	msg = consumer.receive(10000);
            	
            }     	
        	
        }
        msg.acknowledge();
        TextMessage tm = (TextMessage) msg;
        
        System.out.println("*************Received message " +  ": " +tm.getText());
        msg.acknowledge();
        session.close();
    }

}
