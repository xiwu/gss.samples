package com.javacodegeeks.camel;

import org.apache.camel.Exchange;
import org.apache.camel.language.XPath;

public class ValidatorBean {
    public void validate(@XPath("/order/@name") String partName, Exchange exchange) {
        // only motors are valid parts in this simple test bean
        if ("motor".equals(partName)) {
            exchange.getOut().setBody("Valid");
        } else {
            exchange.getOut().setBody("Invalid");
        }
        try {
			Thread.currentThread().sleep(4000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
