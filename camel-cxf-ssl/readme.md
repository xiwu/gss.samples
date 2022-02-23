iiI attached 2 sample projects
There are 2 projects here to demonstrate the usage of camel cxf component using ssl 2 way authentication http:conduit and httpj:engine-factory:

* explain 
In a secure server side , the part should be 

  <httpj:engine-factory bus="cxf">
      <httpj:engine port="${proxy.port}">
        <httpj:tlsServerParameters secureSocketProtocol="TLSv1">
          <sec:keyManagers keyPassword="skpass">
            <sec:keyStore resource="certs/serviceKeystore.jks" password="sspass" type="JKS"/>
          </sec:keyManagers>
          <sec:trustManagers>
            <sec:keyStore resource="certs/serviceKeystore.jks" password="sspass" type="JKS"/>
          </sec:trustManagers>
          <sec:cipherSuitesFilter>
            <sec:include>.*_WITH_3DES_.*</sec:include>
            <sec:include>.*_WITH_DES_.*</sec:include>
            <sec:exclude>.*_WITH_NULL_.*</sec:exclude>
            <sec:exclude>.*_DH_anon_.*</sec:exclude>
          </sec:cipherSuitesFilter>
          <sec:clientAuthentication want="true" required="false"/>
        </httpj:tlsServerParameters>
      </httpj:engine>
  </httpj:engine-factory>


In a secure client side, the part should be 

  <http:conduit name="{http://reportincident.example.camel.apache.org}ReportIncidentEndpoint.http-conduit">
    <http:tlsClientParameters disableCNCheck="true" secureSocketProtocol="TLSv1">
      <sec:keyManagers keyPassword="ckpass">
          <sec:keyStore password="cspass" type="JKS"
          resource="certs/clientKeystore.jks" />
      </sec:keyManagers>
      <sec:trustManagers>
          <sec:keyStore password="cspass" type="JKS"
          resource="certs/clientKeystore.jks" />
      </sec:trustManagers>
      <sec:cipherSuitesFilter>
        <sec:include>.*_WITH_3DES_.*</sec:include>
        <sec:include>.*_WITH_DES_.*</sec:include>
        <sec:exclude>.*_WITH_NULL_.*</sec:exclude>
        <sec:exclude>.*_DH_anon_.*</sec:exclude>
      </sec:cipherSuitesFilter>
    </http:tlsClientParameters>
   </http:conduit>
  
camel-example-cxf-proxy-server
camel-example-cxf-proxy-client

* how to run
you can have a ref, Which is from the doc [1]: 
about the certs part, pls ref the file in src/main/resources/certs/KeyREADME.txt

To run the server code,
mvn camel:run

To run the client code, 

mvn camel:run then 
cp src/main/resources/input.xml inputdata

Then you will see below response in the client console like: 

<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:outputReportIncident xmlns:ns2="http://reportincident.example.camel.apache.org"><code>OK;456</code></ns2:outputReportIncident></soap:Body></soap:Envelope>

[1]https://access.redhat.com/documentation/en-us/red_hat_fuse/7.10/html/apache_karaf_security_guide/camelcxf
