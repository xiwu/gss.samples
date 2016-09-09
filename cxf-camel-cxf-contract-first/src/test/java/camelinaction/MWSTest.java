package camelinaction;

import org.apache.camel.CamelContext;
import org.apache.camel.Expression;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;



public class MWSTest{

	public static void main(String[] args){
		CamelContext context = new DefaultCamelContext();
        try {
			context.addRoutes(new RouteBuilder() {
			    @Override
			    public void configure() throws Exception {
                    
			        from("cxf:http://localhost:9090/MeteoWebService-1.5/MeteoWebService?"
			                + "wsdlURL=file:///home/wuxiaohui/apps/new-jboss-fuse-6.2.1.redhat-107/quickstarts/cxf/camel-cxf-contract-first/src/main/resources/wsdl/a.wsdl"
			                + "&serviceName={http://mws.frequentis.com/}MeteoWebServiceImplService"
			                + "&endpointName={http://mws.frequentis.com/}MeteoWebServiceImplPort"
			                + "&dataFormat=PAYLOAD"
			                + "&defaultOperationName=getMeteoData"
			                + "&defaultOperationNamespace=http://frequentis.com/MeteoWebService/v1_5"
			                + "&serviceClass=com.frequentis.meteowebservice.v1_5.MeteoWebService"
			                //+ "&xmlns:ns1=http://frequentis.com/MeteoWebService/v1_5"
			        ).to("log:foo").process(new MyProcessor()).to("log:end");

			    }
			});
//			context.addRoutes(new RouteBuilder() {
//			    @Override
//			    public void configure() throws Exception {
//			        from("file:data/cxf/met?noop=true")                       
//			        .to("cxf:http://localhost:9090/MeteoWebService-1.5/MeteoWebService?"
//			                + "wsdlURL=http://localhost:9090/MeteoWebService-1.5/MeteoWebService?wsdl"
//			                + "&serviceName={http://mws.frequentis.com/}MeteoWebServiceImplService"
//			                + "&endpointName={http://mws.frequentis.com/}MeteoWebServiceImplPort"
//			                + "&dataFormat=PAYLOAD"
//			                + "&defaultOperationName=getMeteoData"
//			                + "&defaultOperationNamespace=http://frequentis.com/MeteoWebService/v1_5"
//			        )
//			        .log("${body}")
//			        .end();
//			    }
//			});
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			context.start();
	        Thread.sleep(600*1000);
	        context.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



}
