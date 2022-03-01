LDAP 的配置

其中使用了OpenLDAP作为配置，ApacheDirectoryStudio可以可视化配置，

使用了网上公开的一个ldap。
login.config

activemq {

  org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule required
     debug=true
     initialContextFactory=com.sun.jndi.ldap.LdapCtxFactory
     connectionURL="ldap://ldap.forumsys.com:389"
     connectionUsername="cn=read-only-admin,dc=example,dc=com"
     connectionPassword=password
     connectionProtocol=s
     authentication=simple
     userBase="dc=example,dc=com"
     userSearchMatching="(uid={0})"
     userSearchSubtree=true
     roleBase="dc=example,dc=com"
     roleName=cn
     roleSearchMatching="(uniqueMember={0})"
     roleSearchSubtree=true
     referral=follow;
};

artemis.profile里面：
HAWTIO_ROLE='Chemists'

-Dhawtio.role=Chemists




在AMQ启动后，会看到如下信息：
2022-02-17 22:42:11,399 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Create the LDAP initial context.
2022-02-17 22:42:11,399 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Referral handling: follow
2022-02-17 22:42:11,868 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Get the user DN.
2022-02-17 22:42:11,868 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Looking for the user in LDAP with 
2022-02-17 22:42:11,868 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule]   base DN: dc=example,dc=com
2022-02-17 22:42:11,868 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule]   filter: (uid=boyle)
2022-02-17 22:42:12,094 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] LDAP returned a relative name: uid=boyle
2022-02-17 22:42:12,094 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Using DN [uid=boyle,dc=example,dc=com] for binding.
2022-02-17 22:42:12,095 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Binding the user.
2022-02-17 22:42:12,544 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] User uid=boyle,dc=example,dc=com successfully bound.
2022-02-17 22:42:12,544 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Get user roles.
2022-02-17 22:42:12,544 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Looking for the user roles in LDAP with 
2022-02-17 22:42:12,544 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule]   base DN: dc=example,dc=com
2022-02-17 22:42:12,544 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule]   filter: (uniqueMember=uid=boyle,dc=example,dc=com)
2022-02-17 22:42:12,993 DEBUG [org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule] Roles [Chemists] for user boyle



参考：

* https://access.redhat.com/solutions/6708231 How Do I Get My AMQ Broker Running In OpenShift to Use LDAP for Authentication?
  https://access.redhat.com/solutions/4450641 How to configure Red Hat AMQ 7 LDAP authentication and authorisation
  https://developers.redhat.com/blog/2018/09/21/setup-ldap-auth-amq-console#configure_ldap_authentication
* 其他的配置如 DS389
* sample code about ldap

activemq {

   org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule sufficient
        debug=true
        initialContextFactory=com.sun.jndi.ldap.LdapCtxFactory
        connectionURL="ldap://services.test.redhat.com:389"
        connectionUsername="CN=Directory\ Manager"
        connectionPassword="myldappassword"
        connectionProtocol="simple"
        authentication="simple"
        userBase="ou=People,dc=redhat,dc=com"
        userSearchMatching="(&(objectClass=inetOrgPerson)(uid={0}))"
        userSearchSubtree=true
        roleBase="ou=Groups,dc=redhat,dc=com"
        roleName=cn
        roleSearchMatching="(&(objectClass=GroupOfUniqueNames)(UniqueMember={0}))"
        roleSearchSubtree=true;

};

hawtio {

   org.apache.activemq.artemis.spi.core.security.jaas.LDAPLoginModule sufficient
        debug=true
        initialContextFactory=com.sun.jndi.ldap.LdapCtxFactory
        connectionURL="ldap://services.test.redhat.com:389"
        connectionUsername="CN=Directory\ Manager"
        connectionPassword="myldappassword"
        connectionProtocol="simple"
        authentication="simple"
        userBase="ou=People,dc=redhat,dc=com"
        userSearchMatching="(&(objectClass=inetOrgPerson)(uid={0}))"
        userSearchSubtree=true
        roleBase="ou=Groups,dc=redhat,dc=com"
        roleName=cn
        roleSearchMatching="(&(objectClass=GroupOfUniqueNames)(UniqueMember={0}))"
        roleSearchSubtree=true;

};




## 查询命令
ldapsearch -H ldap://localhost:10389 -x -D "uid=admin,ou=system" -w "secret" -b "ou=Groups,dc=example,dc=com" "(member=cn=John+sn=Doe+uid=jdoe,ou=Users,dc=example,dc=com)" -LL cn



ldapsearch -H ldap://localhost:10389 -x -D "uid=admin,ou=system" -w "secret" -b "ou=Groups,dc=example,dc=com" "(|(member=cn=John+sn=Doe+uid=jdoe,ou=Users,dc=example,dc=com)(objectClass=referral))" -LL cn


ldapsearch -H ldap://localhost:10389 -x -D "uid=admin,ou=system" -w "secret" -b "ou=Groups,dc=example,dc=com" "(|(member=jdoe,ou=Users,dc=example,dc=com)(objectClass=referral))" -LL cn


ldapsearch -H ldap://localhost:10389 -x -D "uid=admin,ou=system" -w "secret" -b "ou=Users,dc=example,dc=com" "(uid=jdoe)&(objectClass=InetOrgPerson)" -LL cn


(member=cn=Aohn+sn=Doe+uid=adoe,ou=Users,dc=example,dc=com)



[17:33:20] DEBUG [org.apache.directory.server.OPERATION_LOG] - >> LookupOperation : FilteringOperationContext for Dn 'cn=Aohn+sn=Doe+uid=adoe,ou=Users,dc=example,dc=com', +, *
[17:33:20] DEBUG [org.apache.directory.server.core.authn.AuthenticationInterceptor] - Operation Context: FilteringOperationContext for Dn 'cn=Aohn+sn=Doe+uid=adoe,ou=Users,dc=example,dc=com', +, *
[17:33:20] INFO [org.apache.directory.server.ldap.handlers.request.BindRequestHandler] - The cn=Aohn+sn=Doe+uid=adoe,ou=Users,dc=example,dc=com principalDN cannot be found in the server : bind failure.




(|(member=cn=John+sn=Doe+uid=jdoe,ou=Users,dc=example,dc=com)(objectClass=referral))




ldapsearch -H ldap://localhost:10389 -x -D "uid=admin,ou=system" -w "secret" -b "ou=Users,dc=example,dc=com" "(uid=jdoe)&(objectClass=InetOrgPerson)" -LL cn


ldapsearch -H ldap://ldap.forumsys.com:389 -x -D "cn=read-only-admin,dc=example,dc=com" -w "password" -b  "ou=mathematicians,dc=example,dc=com" -LL cn

ldapsearch -H ldap://ldap.forumsys.com:389 -x -D "cn=read-only-admin,dc=example,dc=com" -w "password" -b "ou=chemists,dc=example,dc=com" "(uniqueMember=uid=boyle,dc=example,dc=com)" -LL cn