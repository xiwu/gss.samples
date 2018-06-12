/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.transport.failover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.transport.Transport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FailoverDurableWithOutstandingAck implements MessageListener, Runnable {

    private static final Log LOG = LogFactory.getLog(FailoverDurableWithOutstandingAck.class);
	private String brokerURI = "failover:(tcp://localhost:61616?trace=true)";
	private BrokerService broker;

    private ArrayList<Integer> consumedMessageList;
    private int producedMessageCount;
    private ActiveMQConnection consumerConnection;
    private Session consumerSession;
    private CountDownLatch receivedLatch;
    private int failoverTripPoint;
    private boolean failOnMessage = true;

    // Test variable params.
    private int waitTimeMills;
    private int ackMode;
    private int prefetchSize;
    private int count;
    private FailureStrategy failureStrategy;

    public static final String CONNECTION_ID = "DurableConsumerTest-ClientId";
    public static final String SUBSCRIPTION_ID = "DurableConsumerTest-SubscriptionId";
    public static final String DESTIONATION_ID = "DurableConsumerTest-TopicId";

    @Before
    public void setUp() throws Exception
    {
    	this.startBroker(true);

        this.failoverTripPoint = 25;
        this.consumedMessageList = new ArrayList<Integer>();
        this.producedMessageCount = 0;
        this.consumerConnection = null;
        this.consumerSession = null;
        this.receivedLatch = null;
        this.failOnMessage = true;

        this.ackMode = ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE;
        this.waitTimeMills = 100;
        this.prefetchSize = 100;
        this.count = 50;
    }

	@After
	public void stopBroker() throws Exception {
	    if (broker != null) {
	        broker.stop();
	        broker.waitUntilStopped();
	        broker = null;
	    }
	}

	public void startBroker(boolean deleteAllMessagesOnStartup) throws Exception {
	    this.broker = createBroker(deleteAllMessagesOnStartup);
        this.broker.start();
        this.broker.waitUntilStarted();
	}

	public BrokerService createBroker(boolean deleteAllMessagesOnStartup) throws Exception {
        this.broker = new BrokerService();
        this.broker.addConnector("tcp://localhost:61616");
        this.broker.setDeleteAllMessagesOnStartup(deleteAllMessagesOnStartup);
        this.broker.setPersistent(true);

	    return broker;
	}

	@Test
	public void testDurableSubscriberGetsAllMessageOnConnectionFailure() {
    	this.failureStrategy = new ConnectionFailureStrategy();

    	doTestDurableSubscriberGetsAllItsMessages();
	}

	@Test
	public void testDurableSubscriberGetsAllMessageOnBrokerFailure() {
    	this.failureStrategy = new BrokerFailureStrategy();

    	doTestDurableSubscriberGetsAllItsMessages();
	}

	public void doTestDurableSubscriberGetsAllItsMessages() {

        try {

            // Register a durable sub at the broker.
            registerDurableConsumer();

            // produce some messages
            produceSomeMessages(this.count);

            // start consumer back up
            Thread consumerThread = new Thread(this);
            consumerThread.start();

            // Wait for all the Messages to arrive.
            if (!this.receivedLatch.await(30 * 1000, TimeUnit.MILLISECONDS)) {

                long remaining = this.receivedLatch.getCount();
                while(remaining-- != 0) {
                    this.receivedLatch.countDown();
                }
            }

            consumerThread.join(10000);

            checkThatNoMessagesAreAvailable();

            unregisterDurableConsumer();

            assertEquals("Messages consumed should equeal Messages produced.",
            			 producedMessageCount, consumedMessageList.size());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onMessage(Message message) {

        try {

            int id = Integer.valueOf(((TextMessage) message).getText());
            if (!consumedMessageList.contains(id)) {
                consumedMessageList.add(id);
            } else {
                if( !message.getJMSRedelivered() ) {
                    LOG.debug("Duplicate message!!! " + ((TextMessage) message).getText());
                } else {
                	LOG.debug("Redelivered Message " + ((TextMessage) message).getText());
                }
            }

            if (id % 10 == 0) {
            	LOG.debug("Consumer:  At message " + ((TextMessage) message).getText());
            }

            if (id == failoverTripPoint && failOnMessage) {
            	LOG.debug("*** Starting Failover of broker connection now! ***");
            	this.failureStrategy.execute();
            }

            if (this.consumerSession.getTransacted() ) {
                this.consumerSession.commit();
            }

            if (this.consumerSession.getAcknowledgeMode() == Session.CLIENT_ACKNOWLEDGE ||
                this.consumerSession.getAcknowledgeMode() == ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE ) {

                message.acknowledge();
            }

            // add slight delay
            Thread.sleep(waitTimeMills);

        } catch (Exception e) {
            LOG.debug(String.format("Catch %s - %s", new Date().toString(), e.getMessage()));
        } finally {
            this.receivedLatch.countDown();
        }
    }

    public void run() {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURI);
        factory.setClientID( CONNECTION_ID );
        factory.getPrefetchPolicy().setDurableTopicPrefetch( this.prefetchSize );

        try {
            this.consumerConnection = (ActiveMQConnection) factory.createConnection();
            this.consumerSession = this.consumerConnection.createSession(false, ackMode);
            Topic destination = this.consumerSession.createTopic(
                DESTIONATION_ID + "?consumer.prefetchSize=" + this.prefetchSize );

            MessageConsumer durableConsumer =
                this.consumerSession.createDurableSubscriber( destination, SUBSCRIPTION_ID );
            durableConsumer.setMessageListener(this);

            this.consumerConnection.start();

            this.receivedLatch.await();

            durableConsumer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                this.consumerSession.close();
            } catch(Exception ex) {
            }
            try{
                this.consumerConnection.close();
            } catch(Exception ex) {
            }
        }
    }

    private void checkThatNoMessagesAreAvailable() throws Exception {
    	ActiveMQConnection connection = null;
    	try{
	        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURI);
	        factory.setClientID( CONNECTION_ID );
	        connection = (ActiveMQConnection) factory.createConnection();
	        connection.start();
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        Topic destination = session.createTopic(DESTIONATION_ID);
	        MessageConsumer durableConsumer = session.createDurableSubscriber( destination, SUBSCRIPTION_ID );
	        assertNull("All Messages should have been consumed previously", durableConsumer.receive(5000));
    	} finally {
    		connection.close();
    	}
    }

    private void produceSomeMessages(int amountOfMessages) throws Exception {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURI);
        factory.setUseAsyncSend( true );
        factory.setClientID( CONNECTION_ID );

        ActiveMQConnection connection = (ActiveMQConnection) factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic destination = session.createTopic(DESTIONATION_ID);
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode( DeliveryMode.PERSISTENT );

        try {
            TextMessage message = session.createTextMessage();

            for(int i = 1; i <= amountOfMessages; i++) {

                try {
                    message.setText( Integer.toString( i ) );
                    producer.send(message);

                    this.producedMessageCount++;

                } catch( Exception e ) {
                    e.printStackTrace();
                }
            }

            this.receivedLatch = new CountDownLatch(amountOfMessages);
        } catch( Exception e ) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public void registerDurableConsumer() throws Exception {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURI);
        factory.setClientID(CONNECTION_ID);

        ActiveMQConnection connection = (ActiveMQConnection) factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic destination = session.createTopic(DESTIONATION_ID);
        TopicSubscriber subscriber = session.createDurableSubscriber(destination, SUBSCRIPTION_ID);

        subscriber.close();
        connection.close();
    }

    public void unregisterDurableConsumer() throws Exception {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURI);
        factory.setClientID(CONNECTION_ID);

        ActiveMQConnection connection = (ActiveMQConnection) factory.createConnection();
        connection.start();
        connection.unsubscribe(SUBSCRIPTION_ID);
        connection.close();
    }

    private interface FailureStrategy {
    	void execute();
    }

    private class ConnectionFailureStrategy implements FailureStrategy {

    	public void execute() {
            try {
                ActiveMQConnection amqCon = (ActiveMQConnection) consumerConnection;
                Transport transport = amqCon.getTransport();
                Socket sock = (Socket) transport.narrow(Socket.class);

                if(sock != null) {
                    sock.close();
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
    	}
    }

    private class BrokerFailureStrategy implements FailureStrategy {

    	public void execute() {
            try {
            	stopBroker();
            	Thread.sleep(10000);
            	startBroker(false);
            	Thread.sleep(1000);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
    	}
    }
}
