package org.apache.activemq.artemis.jms.example;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;

public class HelloWorld {

    public static void main(String[] args) throws Exception {
        JmsPoolConnectionFactory poolingFactory = new JmsPoolConnectionFactory();

        try {
            // The configuration for the Qpid InitialContextFactory has been supplied in
            // a jndi.properties file in the classpath, which results in it being picked
            // up automatically by the InitialContext constructor.
            Context context = new InitialContext();

            // MessagingHub JmsPoolConnectionFactory
//            JmsPoolConnectionFactory jmsPoolConnectionFactory = new JmsPoolConnectionFactory();
            poolingFactory.setConnectionFactory(coreConnectionFactory("tcp://localhost:61616","admin","admin"));
            poolingFactory.setMaxConnections(20);
            //setUseAnonymousProducers=true causes problems with org.messaginghub.pooled.jms.JmsPoolConnectionFactory when AMQ Address start paging
            poolingFactory.setUseAnonymousProducers(true);
//            return jmsPoolConnectionFactory;


//            ConnectionFactory factory = (ConnectionFactory) context.lookup("myFactoryLookup");

//            poolingFactory.setConnectionFactory(factory);

//            Destination queue = (Destination) context.lookup("myQueueLookup");
            Queue queue = (Queue) context.lookup("queue/exampleQueue");

            final String messagePayload = "Hello World";

            // Each send should end up reusing the same Connection and Session from the pool
            for (int i = 0; i < messagePayload.length(); ++i) {
                Connection connection = poolingFactory.createConnection("admin", "admin");
                connection.setExceptionListener(new MyExceptionListener());
//                connection.start();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                MessageProducer messageProducer = session.createProducer(queue);

                TextMessage message = session.createTextMessage("" + messagePayload.charAt(i));
                messageProducer.send(message, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);

                connection.close();
            }

            // The consumer should reuse the same Connection as the senders, broker should register only
            // one connection having been used for this full example.
            Connection connection = poolingFactory.createConnection("admin", "admin");
            connection.setExceptionListener(new MyExceptionListener());
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            StringBuilder messageReceived = new StringBuilder().append("Received: ");

            MessageConsumer messageConsumer = session.createConsumer(queue);
            for (int i = 0; i < messagePayload.length(); ++i) {
                TextMessage receivedMessage = (TextMessage) messageConsumer.receive(2000l);
                if (receivedMessage != null) {
                    messageReceived.append(receivedMessage.getText());
                } else {
                    messageReceived.setLength(0);
                    messageReceived.append("No message received within the given timeout!");
                    System.out.println("No message received within the given timeout!");
                    break;
                }
            }

            System.out.println(messageReceived.toString());

            connection.close();
        } catch (Exception exp) {
            System.out.println("Caught exception, exiting.");
            exp.printStackTrace(System.out);
            System.exit(1);
        } finally {
            poolingFactory.stop();
        }
    }

    private static class MyExceptionListener implements ExceptionListener {
        @Override
        public void onException(JMSException exception) {
            System.out.println("Connection ExceptionListener fired, exiting.");
            exception.printStackTrace(System.out);
            System.exit(1);
        }
    }


    //CORE ConnectionFactory
    public static ConnectionFactory coreConnectionFactory(String remoteUrl, String username, String password){
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(remoteUrl);
            factory.setUser("username");
            factory.setPassword("password");
        return factory;
    }
}
