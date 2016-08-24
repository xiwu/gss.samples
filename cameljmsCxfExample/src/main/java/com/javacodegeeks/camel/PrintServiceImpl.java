package com.javacodegeeks.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class PrintServiceImpl implements Processor{

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("$$$exchange.getExchangeId()$$$:" + exchange.getExchangeId());
		System.out.println("$$$exchange.getIn().getBody()$$$:" + exchange.getIn().getBody());
		System.out.println("$$$exchange$$$:" + exchange);
	}

}
