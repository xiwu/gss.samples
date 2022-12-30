package com.mycompany;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
public class Myprocessor implements Processor{

	public void process(Exchange exchange) throws Exception {
		String response = "Is this true? ";
		exchange.getIn().setBody(response);		
	}
}
	
	
	

