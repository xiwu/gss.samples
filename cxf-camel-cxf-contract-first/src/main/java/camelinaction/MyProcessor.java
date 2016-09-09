package camelinaction;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
public class MyProcessor implements Processor{

	public void process(Exchange exchange) throws Exception {
		File file = new File("/home/wuxiaohui/apps/new-jboss-fuse-6.2.1.redhat-107/quickstarts/cxf/camel-cxf-contract-first/src/test/resources/mws_response.xml");
		String response = Util.getFileStr(file);
		exchange.getIn().setBody(response);


}
}		
