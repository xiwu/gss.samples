/**
 *  Copyright 2005-2018 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package org.mycompany;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.undertow.CamelUndertowHttpHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * A spring-boot application that includes a Camel route builder to setup the Camel routes
 */
@SpringBootApplication
public class Application {

    // must have a main method spring-boot can run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean("handlers")
    List<CamelUndertowHttpHandler> getHandlers() {
        List<CamelUndertowHttpHandler> handlers = new ArrayList<CamelUndertowHttpHandler>();
        handlers.add(new CamelUndertowRequestDumpingHandler());
        return handlers;
    }
    @Component
    class RestApi extends RouteBuilder {

        @Override
        public void configure() {
            restConfiguration()
                .contextPath("/")
                .enableCORS(true)
                .host("localhost")
                .port(9091)
                .endpointProperty("handlers", "#handlers")
                .component("undertow");

            rest("/say")
                .put("/hello")
                .consumes("application/json")
                    .route().routeId("route1")
                    .log("Hello World from REST DSL")
                    .endRest();
               
        }
    }


}
