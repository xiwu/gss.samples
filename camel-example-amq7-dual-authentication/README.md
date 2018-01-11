# Spring-Boot and Camel XML with AMQ7 dual authentication

01999334
setup a test project using the config in the sample
amq-broker-7.0.3/examples/features/standard/ssl-enabled-dual-authentication, using camel route to send/receive messages to/from AMQ7. 


### Building

The example can be built with

    mvn clean install

### Running the example in IDE

run the command  `mvn verify` to start the server in amq-broker-7.0.3/examples/features/standard/ssl-enabled-dual-authentication and then adjust the configs in the camel-context.xml, import it into the IDE, run the camel route context. 
 

