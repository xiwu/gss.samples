package com.codenotfound.jms;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class SenderConfig {

  @Value("${artemis.broker-url}")
  private String brokerUrl;

//  @Bean
//  public ActiveMQConnectionFactory senderActiveMQConnectionFactory() {
//    return new ActiveMQConnectionFactory(brokerUrl);
//  }
//
//  @Bean
//  public CachingConnectionFactory cachingConnectionFactory() {
//    return new CachingConnectionFactory(
//        senderActiveMQConnectionFactory());
//  }
//  @Bean
//  public JmsPoolConnectionFactory senderjmsPoolConnectionFactory() {
//	  //import org.apache.qpid.jms.JmsConnectionFactory;
////	  import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
//	  
//	//        ConnectionFactory factory = new JmsConnectionFactory(connUri);
////      JmsPoolConnectionFactory pool = new JmsPoolConnectionFactory();
//	 
//    JmsPoolConnectionFactory  jpcf = new JmsPoolConnectionFactory();
//    jpcf.setConnectionFactory(senderActiveMQConnectionFactory());
//    return jpcf;
//  }
  
  @Bean
  public ConnectionFactory senderAmqpjmsPoolConnectionFactory() {
	  //import org.apache.qpid.jms.JmsConnectionFactory;
//	  import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
	  
	//        ConnectionFactory factory = new JmsConnectionFactory(connUri);
//      JmsPoolConnectionFactory pool = new JmsPoolConnectionFactory();
	 
//    JmsPoolConnectionFactory  jpcf = new JmsPoolConnectionFactory();

    org.apache.qpid.jms.JmsConnectionFactory jmscf = new org.apache.qpid.jms.JmsConnectionFactory(brokerUrl);
    CachingConnectionFactory ccf = new CachingConnectionFactory(jmscf);
    ccf.setReconnectOnException(true);
    ccf.resetConnection();
//    ccf.setCacheProducers(false);
    return ccf;
  } 

  @Bean
  public ConnectionFactory senderAmqpjmsSingleConnectionFactory() {
	  //import org.apache.qpid.jms.JmsConnectionFactory;
//	  import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
	  
	//        ConnectionFactory factory = new JmsConnectionFactory(connUri);
//      JmsPoolConnectionFactory pool = new JmsPoolConnectionFactory();
	 
//    JmsPoolConnectionFactory  jpcf = new JmsPoolConnectionFactory();

    org.apache.qpid.jms.JmsConnectionFactory jmscf = new org.apache.qpid.jms.JmsConnectionFactory(brokerUrl);
    SingleConnectionFactory scf = new SingleConnectionFactory(jmscf);
    scf.setReconnectOnException(true);
    scf.resetConnection();
//    scf.setCacheProducers(false);
    return scf;
  } 
  
  
  @Bean
  @Scope("prototype")
  public JmsTemplate jmsTemplate() {
    return new JmsTemplate(senderAmqpjmsSingleConnectionFactory());
  }

  @Bean
  public Sender sender() {
    return new Sender();
  }
}
