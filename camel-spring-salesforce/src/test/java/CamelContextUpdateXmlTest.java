import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CamelContextUpdateXmlTest extends CamelSpringTestSupport {

	// TODO Create test message bodies that work for the route(s) being tested
	// Expected message bodies
	protected Object[] expectedBodies = {
			"<something id='1'>expectedBody1</something>",
			"<something id='2'>expectedBody2</something>" };
	// Templates to send to input endpoints
	@Produce(uri = "file:work/camel-salesforce/input")
	protected ProducerTemplate inputEndpoint;

	@Test
	public void testCamelRoute() throws Exception {

//		// Define some expectations
//
//		// For now, let's just wait for some messages// TODO Add some expectations here
//		// Send some messages to input endpoints
//		for (Object expectedBody : expectedBodies) {
//			inputEndpoint.sendBody(expectedBody);
//		}
//
//		// Validate our expectations
//		assertMockEndpointsSatisfied();
		Thread.currentThread().sleep(10*1000*60);
	}

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(
				"META-INF/spring/camel-context-update.xml");
	}

}
