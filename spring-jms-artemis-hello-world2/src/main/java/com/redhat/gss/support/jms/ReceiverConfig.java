package com.redhat.gss.support.jms;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
public class ReceiverConfig {

  @Value("${artemis.broker-url}")
  private String brokerUrl;

  @Bean
  public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
    return new ActiveMQConnectionFactory(brokerUrl);
  }
  @Bean
  public JmsPoolConnectionFactory jmsPoolConnectionFactory() {
	  //import org.apache.qpid.jms.JmsConnectionFactory;
//	  import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
	  
	//        ConnectionFactory factory = new JmsConnectionFactory(connUri);
//      JmsPoolConnectionFactory pool = new JmsPoolConnectionFactory();
	 
    JmsPoolConnectionFactory  jpcf = new JmsPoolConnectionFactory();
    jpcf.setConnectionFactory(receiverActiveMQConnectionFactory());
    // Set the max connections per user to a higher value
    jpcf.setMaxConnections(1);
    jpcf.setMaxSessionsPerConnection(1);
    // Create a MessageProducer for each createProducer() call
    jpcf.setUseAnonymousProducers(false);
    return jpcf;
  }
  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
    DefaultJmsListenerContainerFactory factory =
        new DefaultJmsListenerContainerFactory();
    factory
        .setConnectionFactory(jmsPoolConnectionFactory());
    factory.setConcurrency("1-10");

    return factory;
  }

  @Bean
  public Receiver receiver() {
    return new Receiver();
  }
}
