package camelinaction;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;



public class MWSProducerTest{

	public static void main(String[] args){
		CamelContext context = new DefaultCamelContext();
        try {

			context.addRoutes(new RouteBuilder() {
			    @Override
			    public void configure() throws Exception {
			        from("file:data/cxf/met?noop=true").to("log:${body}")
			        .setHeader("operationName",constant("getMeteoData"))
			        .setHeader("operationNamespace", constant("http://frequentis.com/MeteoWebService/v1_5"))  
			                           //http://localhost:9090/MeteoWebService-1.5/MeteoWebService?wsdl
//			        .to("cxf:http://localhost:9090/MeteoWebService-1.5/MeteoWebService?"
//			                + "wsdlURL=http://localhost:9090/MeteoWebService-1.5/MeteoWebService?wsdl"
//			                + "&serviceName={http://mws.frequentis.com/}MeteoWebServiceImplService"
//			                + "&endpointName={http://mws.frequentis.com/}MeteoWebServiceImplPort"
//			                + "&dataFormat=PAYLOAD"
//			                + "&defaultOperationName=getMeteoData"
//			                + "&defaultOperationNamespace=http://frequentis.com/MeteoWebService/v1_5"
//			        )
			        			        .to("cxf:http://localhost:9090/MeteoWebService-1.5/MeteoWebService?"
			        			 + "serviceClass=com.frequentis.meteowebservice.v1_5.MeteoWebService"
			        			 + "&serviceName={http://mws.frequentis.com/}MeteoWebServiceImplService"
			        			 + "&endpointName={http://mws.frequentis.com/}MeteoWebServiceImplPort"
			        			 + "&dataFormat=PAYLOAD"       		
     		)       
			        .log("${body}")
			        .end();
			    }
			});
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			context.start();
	        Thread.sleep(200000);
	        context.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
