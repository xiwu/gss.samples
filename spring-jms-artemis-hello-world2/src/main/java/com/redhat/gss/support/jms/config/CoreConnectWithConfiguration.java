package com.redhat.gss.support.jms.config;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class CoreConnectWithConfiguration {
    public static void main(String[] args) throws Exception {


        String connUri = "tcp://localhost:61616";
        

        ConnectionFactory factory = new ActiveMQConnectionFactory(connUri);
        


        try (Connection conn = factory.createConnection())
        {

            conn.start();


                System.out.println("CONNECT: Connected to '" + connUri + "'");
                Thread.currentThread().sleep(100000);
        }
            }

}
