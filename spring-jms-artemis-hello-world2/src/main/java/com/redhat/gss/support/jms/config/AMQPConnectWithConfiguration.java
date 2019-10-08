package com.redhat.gss.support.jms.config;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;

public class AMQPConnectWithConfiguration {
    public static void main(String[] args) throws Exception {


        String connUri = "tcp://localhost:61616";
        

        ConnectionFactory factory = new JmsConnectionFactory(connUri);
        JmsPoolConnectionFactory pool = new JmsPoolConnectionFactory();

        try {
            pool.setConnectionFactory(factory);

            // Set the max connections per user to a higher value
            pool.setMaxConnections(5);

            // Create a MessageProducer for each createProducer() call
            pool.setUseAnonymousProducers(false);

            Connection conn = pool.createConnection();

            conn.start();

            try {
                System.out.println("CONNECT: Connected to '" + connUri + "'");
                Thread.currentThread().sleep(100000);
            } finally {
                conn.close();
            }
        } finally {
            pool.stop();
        }
    }
}
