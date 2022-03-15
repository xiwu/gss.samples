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

import org.apache.camel.component.undertow.CamelUndertowHttpHandler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.RequestDumpingHandler;
public class CamelUndertowRequestDumpingHandler implements CamelUndertowHttpHandler {

    private HttpHandler next;
    private RequestDumpingHandler requestDumpingHandler;
    
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        if (this.requestDumpingHandler == null) {
            buildRequestDumpingHandler();
        }
        requestDumpingHandler.handleRequest(exchange);
    }

    private void buildRequestDumpingHandler() {
        this.requestDumpingHandler = new RequestDumpingHandler(this.next);
    }

    
    @Override
    public void setNext(HttpHandler nextHandler) {
        this.next = nextHandler;
        
    }

}
