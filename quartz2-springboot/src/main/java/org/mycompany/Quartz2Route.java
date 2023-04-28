package org.mycompany;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Quartz2Route extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("quartz2://currentTimer?trigger.repeatInterval=1000&trigger.repeatCount=5")
                .setBody().simple("Quartz2 Example 1")
                .to("log:out");

        from("quartz2://myGroup1/myTimerName1?cron=0/5+*+*+*+*+?")
        .setBody(constant("Test"))
        .to("log:info");
    }

}

