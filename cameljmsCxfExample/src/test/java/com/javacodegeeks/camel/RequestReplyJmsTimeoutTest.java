package com.javacodegeeks.camel;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentClientAcknowledge;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class RequestReplyJmsTimeoutTest extends CamelTestSupport {

    protected CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = super.createCamelContext();

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        camelContext.addComponent("jms", jmsComponentClientAcknowledge(connectionFactory));

        return camelContext;
    }
    
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            
            @Override
            public void configure() throws Exception {
                from("jms:incomingOrders?replyTo=replyorders").inOut("jms:validate?replyTo=replyvalidate&requestTimeout=300000");                
                from("jms:validate").bean(ValidatorBean.class);
                
            }
        };
    }

    @Test
    public void testClientGetsReply() throws Exception {
        Object requestBody = template.requestBody("jms:incomingOrders?requestTimeout=100000", "<order name=\"motor\" amount=\"1\" customer=\"honda\"/>");
        assertEquals("Valid", requestBody);        
    }    

    @Test
    public void testInvalidMessage() throws Exception {
        Object requestBody = template.requestBody("jms:incomingOrders?requestTimeout=100000", "<order name=\"fork\" amount=\"1\" customer=\"honda\"/>");
        assertEquals("Invalid", requestBody);        
    }    
}

