The sample demonstrate the usage of undertow handler RequestDumpingHandler to dump the request and response in a spring boot app.
mvn spring-boot:run to start it
and  run
curl -v -X PUT -H "Content-Type: application/json" -d '{"status": "test"}' http://localhost:9091/say/hello
you can see the io.undertow.request.dump REQUEST/RESPONSE log.

