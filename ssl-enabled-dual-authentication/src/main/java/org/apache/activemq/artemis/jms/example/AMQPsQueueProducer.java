package org.apache.activemq.artemis.jms.example;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;

public class AMQPsQueueProducer {

   public static void main(String[] args) throws Exception {
      Connection connection = null;
      Connection consumerConnection = null;      
      String sslhost = "amqps://localhost:5500";
      String plainhost = "amqp://localhost:5672";
      
      int maxInactivityDurationInitialDelay = 30000;
      int idleTimeout = 120000;
      boolean verifyHost = false;
      String acknowledgementModename = "AUTO_ACKNOWLEDGE";
      String keyStoreLocation = "/Users/wuxiaohui/apps/amq-broker-7.8.1/examples/features/standard/ssl-enabled-dual-authentication/src/main/resources/activemq/server0/client-side-keystore.jks";
      String keyStorePassword = "secureexample";
      String trustStoreLocation = "/Users/wuxiaohui/apps/amq-broker-7.8.1/examples/features/standard/ssl-enabled-dual-authentication/src/main/resources/activemq/server0/client-side-truststore.jks";
      String trustStorePassword = "secureexample";
      
      String remoteUri = sslhost +
              "?maxInactivityDurationInitialDelay=" +maxInactivityDurationInitialDelay+
              "&amqp.idleTimeout=" +idleTimeout+
              "&transport.verifyHost=" +verifyHost+
              "&acknowledgementModename=" +acknowledgementModename
              +
              "&transport.keyStoreLocation="+keyStoreLocation+
              "&transport.keyStorePassword=" +keyStorePassword+
              "&transport.trustStoreLocation=" +trustStoreLocation+
              "&transport.trustStorePassword=" +trustStorePassword
              ;
      System.out.println("sslhost:" + remoteUri);
      System.out.println("plainhost:" + plainhost);
//      ConnectionFactory connectionFactory = new JmsConnectionFactory("amqps://localhost:5500?sslEnabled=true&trustStorePath=activemq/server0/client-side-truststore.jks&trustStorePassword=secureexample&keyStorePath=activemq/server0/client-side-keystore.jks&keyStorePassword=secureexample");
      ConnectionFactory connectionFactory = new JmsConnectionFactory(remoteUri);
      ConnectionFactory plainconnectionFactory = new JmsConnectionFactory(plainhost);
      try {

         // Step 1. Create an amqp qpid 1.0 ssl connection
         connection = connectionFactory.createConnection();

         
         // Step 2. Create a session
         Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

         // Step 3. Create a sender
         Queue queue = session.createQueue("exampleQueue");
         MessageProducer sender = session.createProducer(queue);
         TextMessage stm = session.createTextMessage("Hello world ");
         
         // Step 4. send a few simple message
         sender.send(stm);

         connection.start();
         System.out.println("Sent message: " + stm.getText());
         

         
         

      } finally {
         if (connection != null) {
            // Step 10. close the connection
            connection.close();
         }

      }
   }
}
