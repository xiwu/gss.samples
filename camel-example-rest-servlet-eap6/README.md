# Camel Servlet and EAP6.4 example


### Introduction
An example which shows how to use the Camel Servlet with EAP6.4 based on the sample camel-example-servlet-tomcat. 

### Build
You will need to package this example first:

	mvn package

### Run

To run the example deploy it in EAP6.4 by copying the `.war` to the
deploy folder of EAP6.4

And then hit this url from a webbrowser which can get the user info through id.

curl -u jboss:RedHat2018! http://localhost:8080/camel-example-servlet-tomcat/camel/user/123

The servlet is located at

	http://localhost:8080/camel-example-servlet-tomcat/camel/*


### Documentation

This example is documented at
  <http://camel.apache.org/servlet-tomcat-example.html>

### Forum, Help, etc

If you hit an problems please let us know on the Camel Forums
	<http://camel.apache.org/discussion-forums.html>

Please help us make Apache Camel better - we appreciate any feedback you may
have.  Enjoy!

这个例子是参考了camel 的例子camel-example-servlet-tomcat， 使用servlet作为REST的实现。 
并使用了EAP6作为示例basic authentication如何保护应用安全。
使用servelet实际上是注册了camel的org.apache.camel.component.servlet.CamelHttpTransportServlet 到web容器中，所以使用容器保护应用安全的方法就可以实现basic authentication。

可以参考文章：
http://www.mastertheboss.com/jboss-server/jboss-security/securing-as-7-applications-using-the-applicationrealm
https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/6.4/html/security_guide/use_role-based_security_in_servlets

可以看到访问应用必须通过验证：
curl -u jboss:RedHat2018! http://localhost:8080/camel-example-servlet-tomcat/camel/user/123

需要先在EAP里面添加用户：jboss
What type of user do you wish to add?
 a) Management User (mgmt-users.properties)
 b) Application User (application-users.properties)
(a): b
Enter the details of the new user to add.
Realm (ApplicationRealm) :
Username : jboss
Password : RedHat2018！
Re-enter Password :
What roles do you want this user to belong to? (Please enter a comma separated list, or leave blank for none)[  ]: Manager
About to add user 'admin1234' for realm 'ApplicationRealm'
Is this correct yes/no? yes
What groups do you want this user to belong to? (Please enter a comma separated list, or leave blank for none)[  ]: Manager
About to add user 'jboss' for realm 'ApplicationRealm'
Is this correct yes/no? yes
Added user 'jboss' to file '/home/wuxiaohui/apps/jboss-eap-6.4/standalone/configuration/application-users.properties'
Added user 'jboss' to file '/home/wuxiaohui/apps/jboss-eap-6.4/domain/configuration/application-users.properties'
Added user 'jboss' with groups Manager to file '/home/wuxiaohui/apps/jboss-eap-6.4/standalone/configuration/application-roles.properties'
Added user 'jboss' with groups Manager to file '/home/wuxiaohui/apps/jboss-eap-6.4/domain/configuration/application-roles.properties'
Is this new user going to be used for one AS process to connect to another AS process? 
e.g. for a slave host controller connecting to the master or for a Remoting connection for server to server EJB calls.
yes/no? no
