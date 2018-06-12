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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/** * 持久订阅设置唯一的客户端ID和订阅者ID。 */
public class FailOverIndividualACKQueueConsumerWithExListener implements javax.jms.ExceptionListener{ 
	private static final Logger LOG = LoggerFactory.getLogger(FailOverIndividualACKQueueConsumerWithExListener.class);
	public static void main(String[] args) {
		try {
	// 连接到ActiveMQ服务器
	//will close the connection after 1 min		
//	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616?useKeepAlive=false&wireFormat.maxInactivityDuration=20000"); 
	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin","failover:(tcp://localhost:61616)?randomize=false&initialReconnectDelay=10000&maxReconnectDelay=10000&maxReconnectAttempts=-1&useKeepAlive=false");
	Connection connection = factory.createConnection(); 
	//客户端ID,持久订阅需要设置 
	connection.start();
	Session session = connection.createSession(Boolean.FALSE, org.apache.activemq.ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE); 
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
			LOG.info("Received message " + ++count + ": " + tm.getText()+":"+tm.getStringProperty("property"));  
//			Thread.currentThread().sleep(12000);
		}
		catch (JMSException e) {
			e.printStackTrace(); }

//		catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		} 
		});
		} 		catch (Exception e) {
			e.printStackTrace();
			LOG.info("50XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx");
		}
	}
	@Override
	public void onException(JMSException exception) {
		// TODO Auto-generated method stub
		LOG.info("56XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx");
	}
} 
