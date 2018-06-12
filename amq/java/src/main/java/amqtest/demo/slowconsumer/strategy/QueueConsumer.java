package amqtest.demo.slowconsumer.strategy; 
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer; 
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage; 
import javax.jms.Queue;
import org.apache.activemq.ActiveMQConnectionFactory;
/** * 持久订阅设置唯一的客户端ID和订阅者ID。 */
public class QueueConsumer{ 
	public static void main(String[] args) throws JMSException {
	// 连接到ActiveMQ服务器
	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","failover:(tcp://localhost:61616,tcp://localhost:61618)?timeout=10000"); 
	Connection connection = factory.createConnection(); 
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
		//订阅接收方法
		public void onMessage(Message message){
		TextMessage tm = (TextMessage) message; 
		try { 
			message.acknowledge();
			System.out.println("Received message " + ++count + ": " + tm.getText()+":"+tm.getStringProperty("property")); } 
		catch (JMSException e) {
			e.printStackTrace(); }
		} 
		});
	}
} 
