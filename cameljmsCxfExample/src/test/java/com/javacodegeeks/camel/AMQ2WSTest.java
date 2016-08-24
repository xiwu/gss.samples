package com.javacodegeeks.camel;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javacodegeeks.ws.product_service.types.ProductRequest;
import com.javacodegeeks.ws.product_service.types.ProductResponse;
/**
 * @version $Revision: 85 $
 */
public class AMQ2WSTest extends CamelSpringTestSupport {

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
    	System.setProperty("port1", "9000");
        return new ClassPathXmlApplicationContext("camel-context.xml");
    }

    @Test
    public void testAMQ2WS() throws Exception {

    	
		ProductRequest request = new ProductRequest();
		request.setId("P01");
		
		ProductResponse response = template.requestBody("activemq:personnel.records", request, ProductResponse.class);
		System.out.println("Response: Id: " + response.getId() + ", Product: "
				+ response.getDescription() + ", Price: " + response.getPrice());

    }
    
    @Test
    public void testDirect2AMQ2WS() throws Exception {

    	
		ProductRequest request = new ProductRequest();
		request.setId("P01");
		
		ProductResponse response = template.requestBody("direct:activemqstart", "hellowork", ProductResponse.class);
		System.out.println("Response: Id: " + response.getId() + ", Product: "
				+ response.getDescription() + ", Price: " + response.getPrice());

    }
}