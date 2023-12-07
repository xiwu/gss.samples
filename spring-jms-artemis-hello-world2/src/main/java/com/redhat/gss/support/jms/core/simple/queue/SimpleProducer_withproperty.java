package com.redhat.gss.support.jms.core.simple.queue;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleProducer_withproperty {
    private static final Log LOG = LogFactory.getLog(SimpleProducer_withproperty.class);

    // will send 2000 messages to the destination

    private static int 		NUM_MESSAGES_TO_BE_SENT = 1;


    public static void main(String args[]) {

        Connection connection = null;

        try {

            String connUri = "(tcp://localhost:61618,tcp://localhost:61616)";
            ConnectionFactory factory = new ActiveMQConnectionFactory(connUri);
            ActiveMQConnectionFactory amqcf = (ActiveMQConnectionFactory) factory;
            amqcf.getServerLocator().setConnectionTTL(1200000);
//            Connection connection = null;
            connection = factory.createConnection(); 	   
            Session session = null;
            connection.start();
		//Session.CLIENT_ACKNOWLEDG    Session.AUTO_ACKNOWLEDGE
             session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
             Queue queue = session.createQueue("slimsmart.queque.test");
            MessageProducer producer = session.createProducer(queue);
	
	    producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            

            for (int i = 1; i <= NUM_MESSAGES_TO_BE_SENT; i++) {

                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String text = i + "Hello" + formatter.format(date);
                
                Message message = session.createTextMessage(text);
                message.setStringProperty("JMSXUserID", "");
   //             LOG.info("Sending to destination: " + destination.toString() + " this text: '" + message.getText());
                producer.send(message);
		         System.out.println("Sent " + text);
           //     Thread.sleep(100);
            }

            // Cleanup
            producer.close();
            session.close();
            
        } catch (Throwable t) {
            LOG.error(t);
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
                    LOG.error(e);
                }
            }
        }
    }
}

