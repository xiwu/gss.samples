
```
fabric:profile-delete --force demo-quartz2-issue
fabric:profile-create demo-quartz2-issue

fabric:profile-edit --bundles mvn:sg.demo/quartz2-issue/0.0.2 demo-quartz2-issue

#fabric:profile-edit --bundles mvn:io.fabric8/fabric-zookeeper-spring/1.0.0.redhat-379 demo-quartz2-issue
 fabric:profile-edit --bundles mvn:io.fabric8/fabric-zookeeper-spring/1.2.0.redhat-630310 demo-quartz2-issue

fabric:profile-edit --features fabric-camel  demo-quartz2-issue
fabric:profile-edit --features fabric-zookeeper  demo-quartz2-issue

fabric:profile-edit --features camel-core    demo-quartz2-issue
fabric:profile-edit --features camel-spring  demo-quartz2-issue

fabric:profile-edit --features camel-quartz2  demo-quartz2-issue

container-create-child --profile demo-quartz2-issue root child2
```


