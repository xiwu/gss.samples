package example;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class AmqTestReceiver {
	public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageConsumer consumer;
        connectionFactory = new ActiveMQConnectionFactory(
                "admin", "admin", "tcp://localhost:61616"
        );
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("BugTestQueue");
            consumer = session.createConsumer(destination);
            while (true) {
                TextMessage message = (TextMessage) consumer.receive(); // 设置接收者接收消息的时间，这里设置为100s
                // System.out.println("Received message.");
                if (null != message) {
                    System.out.println("收到消息" + message.getText());
//                    System.out.println("Received.");
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {}
        }
    }
}
