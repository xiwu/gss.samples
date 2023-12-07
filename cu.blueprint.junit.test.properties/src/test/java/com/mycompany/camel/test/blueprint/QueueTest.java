package com.mycompany.camel.test.blueprint;

import java.util.concurrent.TimeUnit;

import org.apache.activemq.artemis.junit.EmbeddedActiveMQResource;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class QueueTest extends CamelBlueprintTestSupport {

	private static final Logger logger = LoggerFactory.getLogger(QueueTest.class);

	@Rule
	public EmbeddedActiveMQResource resource = new EmbeddedActiveMQResource();

	@Produce(uri = "jms:queue:{{queue.testqueue}}")
	private ProducerTemplate testQueue;

	private static final String mockQueue = "mock:jms:queue:{{queue.testqueue}}";

	@BeforeClass
	public static void setUpProxy() throws Exception {
//		System.setProperty("http.proxyPort", "8080");
//		System.setProperty("http.proxyHost", "proxy.srv.it2media.de");
//		System.setProperty("https.proxyHost", "proxy.srv.it2media.de");
//		System.setProperty("https.proxyPort", "8080");
	}
	

	@Override
	protected String getBlueprintDescriptor() {
		return "/OSGI-INF/blueprint/blueprint.xml";
	}
	
    @Override
    public String isMockEndpointsAndSkip() {
        // override this method and return the pattern for which endpoints to mock,
        // and skip sending to the original endpoint.
        return mockQueue;
    }
//	
//    @Override
//    protected String getBundleFilter() {
//        return "(!(Bundle-SymbolicName=MyDozerExample))";
//    }
    

	
	@Before
	public void setup() throws Exception {
		super.setUp(); 
		
//		resetMocks();
		
//		System.out.println("#####" + System.getProperty("java.library.path"));
		context.getRouteDefinition("testQueueAbholung").adviceWith(context, new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
//				weaveById("logTestQueue").before().to(mockQueue);
//				weaveById("toTestQueueRecipientslist").before().to(mockQueue);
				weaveById("toTestQueueRecipientslist").after().to(mockQueue);
				
			}
		});
		
		logger.info("JAVA-VERSION: " + System.getProperty("java.version")); 
		logger.info("JAVA-VENDOR: " + System.getProperty("java.vendor")); 
		logger.info("JAVA-VM-NAME: " + System.getProperty("java.vm.name")); 
		logger.info("JAVA-HOME: " + System.getProperty("java.home")); 
		
//		context.start(); 
		
	}
	
	@After
	public void tearDown() throws Exception {
		//sonst bleiben die Messages in den Mocks
		resetMocks();
		
		super.tearDown();

	}
	
	@Override
	public boolean isCreateCamelContextPerClass() {
		return false;
	}

   @Override
   protected String[] loadConfigAdminConfigurationFile() {
       // String[0] = tell Camel the path of the .cfg file to use for OSGi ConfigAdmin in the blueprint XML file
       // String[1] = tell Camel the persistence-id of the cm:property-placeholder in the blueprint XML file
       return new String[]{"src/test/resources/com.mycompany.camel.blueprint.property.cfg", "com.mycompany.camel.blueprint.property"};
   }

	@Test
	public void testSendToQueue() throws Exception {
		
		System.out.println("############# Junit Test start: Send Message to testQueue");
		
		getMockEndpoint(mockQueue).expectedMessageCount(1);		
		testQueue.sendBody("TEST");
		
		//assert fails even though there appear
		//Asserting: mock://jms:queue:mymockqueue is satisfied
		//in the log
		
		assertMockEndpointsSatisfied(30000, TimeUnit.MILLISECONDS);
		
		System.out.println("############# Junit Test Finished");


	}

}
