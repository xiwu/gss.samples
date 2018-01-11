# Camel Mail example


### Introduction
An example which shows how to use the Camel Mail component, demonstrate the connectiontimeout 20000 is functioning.
also using AMQ component.

### Build
You will need to package this example first:

	mvn package

### Run
- copy the file simple.xml to the directory $Fuse/deploy.
- using adminconsole to send message to the queue: ESB.EMAIL.HANDLER

choose the payload format as XML, the data like:

```
<?xml version="1.0" encoding="UTF-8"?>
<EmailRequest>
	<From>ncgswxh@sina.com</From>
	<To>ncgswxh@sina.com</To>
	<CC/>
	<Subject>Load Test mail 3997</Subject>
	<Body><![CDATA[	<html><body>test message</body></html>
]]></Body>
	<ContentType>text/html</ContentType>
	<AttachmentURI/>
	<Disclaimer/>
</EmailRequest>
```

Will see after 20000ms, the exception about Read timed out 

```
2017-12-20 16:30:42,304 | INFO  | B.EMAIL.HANDLER] | Core-Utils-Bundles-EmailHandler  | 231 - org.apache.camel.camel-core - 2.17.0.redhat-630310-04 | 
*****Processing Email Request message:
<?xml version="1.0" encoding="UTF-8"?>
<EmailRequest>
	<From>ncgswxh@sina.com</From>
	<To>ncgswxh@sina.com</To>
	<CC/>
	<Subject>Load Test mail 3997</Subject>
	<Body><![CDATA[	<html><body>test message</body></html>
]]></Body>
	<ContentType>text/html</ContentType>
	<AttachmentURI/>
	<Disclaimer/>
</EmailRequest>

2017-12-20 16:30:42,308 | INFO  | B.EMAIL.HANDLER] | Core-Utils-Bundles-EmailHandler  | 231 - org.apache.camel.camel-core - 2.17.0.redhat-630310-04 | 
*****Entering route: Smtp-Send
2017-12-20 16:30:42,316 | INFO  | qtp1144783678-81 | TransportConnector               | 218 - org.apache.activemq.activemq-osgi - 5.11.0.redhat-630310 | Connector vm://amq stopped
2017-12-20 16:31:02,354 | ERROR | B.EMAIL.HANDLER] | DefaultErrorHandler              | 231 - org.apache.camel.camel-core - 2.17.0.redhat-630310-04 | Failed delivery for (MessageId: ID:wuxiaohui.pek.redhat.com-36854-1513754318051-65:1:1:1:1 on ExchangeId: ID-wuxiaohui-pek-redhat-com-40075-1513754318759-17-1). Exhausted after delivery attempt: 1 caught: javax.mail.MessagingException: Exception reading response;
  nested exception is:
	java.net.SocketTimeoutException: Read timed out
```

change the part from 
`<to uri="smtp://smtp.sina.com:465?connectionTimeout=20000" id="smtpSendEndPoint"/>`

to 

`<to uri="smtp://smtp.sina.com:25?username=ncgswxh&amp;password=XXXXX&amp;connectionTimeout=20000" id="smtpSendEndPoint"/>`
will see there is an email in the mail inbox.

### Troubleshooting 

pls enable the debug log , pls add below part to the `$Fuse_home/etc/org.ops4j.pax.logging.cfg`

```
log4j.logger.com.sun.mail = TRACE, sunmail
log4j.appender.sunmail = org.apache.log4j.DailyRollingFileAppender
log4j.appender.sunmail.datePattern = '.'yyyy-MM-dd
log4j.appender.sunmail.layout = org.apache.log4j.EnhancedPatternLayout
log4j.appender.sunmail.layout.ConversionPattern = %d{ABSOLUTE} | %-5.5p | %-16.16t | %-32.32c{1} | %m%n
log4j.appender.sunmail.file = ${karaf.data}/log/sunmail.log
log4j.appender.sunmail.append = true


log4j.logger.javax.mail = TRACE, javamail
log4j.appender.javamail = org.apache.log4j.DailyRollingFileAppender
log4j.appender.javamail.datePattern = '.'yyyy-MM-dd
log4j.appender.javamail.layout = org.apache.log4j.EnhancedPatternLayout
log4j.appender.javamail.layout.ConversionPattern = %d{ABSOLUTE} | %-5.5p | %-16.16t | %-32.32c{1} | %m%n
log4j.appender.javamail.file = ${karaf.data}/log/javamail.log
log4j.appender.javamail.append = true
```

then the debug log will be printed out in the ${karaf.data}/log/sunmail.log and ${karaf.data}/log/javamail.log. 
Can see below information from the sunmail.log file, the timeout and connection timeout were set to 20000 on the socket.
```

16:10:43,492 | TRACE | B.EMAIL.HANDLER] | socket                           | create socket: prefix mail.smtp, localaddr null, localport 0, host smtp.sina.com, port 465, connection timeout 20000, timeout 20000, socket factory null, useSSL false
16:10:43,492 | TRACE | B.EMAIL.HANDLER] | socket                           | set socket read timeout 20000
16:10:43,492 | TRACE | B.EMAIL.HANDLER] | socket                           | connecting...
16:10:43,497 | TRACE | B.EMAIL.HANDLER] | socket                           | success!
16:11:03,511 | DEBUG | B.EMAIL.HANDLER] | smtp                             | exception reading response
java.net.SocketTimeoutException: Read timed out
```
