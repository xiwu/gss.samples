package com.javacodegeeks.camel;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
public class MyProcessor implements Processor{

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
//        int responseCode = exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, int.class); 
		if(exchange.hasOut()) {
			System.out.println("=====exchange.hasOut()===== ");
	        int responseCode = exchange.getOut().getHeader("org.apache.cxf.message.Message.RESPONSE_CODE", int.class); 

	        System.out.println("=====responseCode===== " + responseCode);
		}
		else {
			Map<String, Object> map = exchange.getIn().getHeaders();
			Set<String> keyset = map.keySet();
			Iterator it = keyset.iterator();
			keyset.size();
			
			String s = null;
			Object o = null;
			while( it.hasNext()) {
				
				 s = (String) it.next();
				 o =  map.get(s);
				 if (s.equalsIgnoreCase("ResponseContext")) {
					 System.out.println("ResponseContext RESPONSE_CODE  " + ((Map)o).get("org.apache.cxf.message.Message.RESPONSE_CODE"));
				 }
				 System.out.println(s + ", value: " + o);
				 
			}
			

		}
//		Map<?,?> a = (exchange.getIn().getHeaders().get("ResponseContext"));
		Integer code = (Integer)((Map)(exchange.getIn().getHeaders().get("ResponseContext"))).get("org.apache.cxf.message.Message.RESPONSE_CODE");
		
		 System.out.println(code + ", codevalue: " + code);
		 if(exchange.getIn().getHeaders().get("camelhttpresponsecode")!=null) {
			 Integer status = (Integer) exchange.getIn().getHeaders().get("camelhttpresponsecode");	 
			 System.out.println(status + ", status: " + status);
		
		 }
		
	}

}
