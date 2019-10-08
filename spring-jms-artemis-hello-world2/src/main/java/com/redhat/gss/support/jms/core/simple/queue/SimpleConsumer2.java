package com.redhat.gss.support.jms.core.simple.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleConsumer2 {
    private static final Log LOG = LogFactory.getLog(SimpleConsumer2.class);



    public static void main(String args[]) {    	
       
        String connUri = "tcp://localhost:61616";
        

        ConnectionFactory factory = new ActiveMQConnectionFactory(connUri);
        ActiveMQConnectionFactory amqcf = (ActiveMQConnectionFactory) factory;
        // set timeout to 1200 seconds
        amqcf.getServerLocator().setConnectionTTL(1200000);
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        
        try {
         connection = factory.createConnection(); 	        
		     
			 connection.start();		     
			 session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			 Queue queue = session.createQueue("slimsmart.queque.test");
			  consumer = session.createConsumer(queue);
			  MyListener ml = new MyListener();
			  new Thread(ml).start();
			  consumer.setMessageListener(ml);
			  
}catch(Exception e) {
	e.printStackTrace();
}
    }
}