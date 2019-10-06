# Spring microservice example

<tr>
<td><a href="https://github.com/markoniemi/microservice-example">microservice-example</a></td>
<td>
<a href="https://dev.azure.com/markoniemi0275/markoniemi/_build/latest?definitionId=8&branchName=master">
<image src="https://dev.azure.com/markoniemi0275/markoniemi/_apis/build/status/markoniemi.microservice-example?branchName=master"/>
</a>
</td>
<td>
</td>
<td>
<a href="https://travis-ci.org/markoniemi/microservice-example">
<image src="https://travis-ci.org/markoniemi/microservice-example.svg?branch=master"/>
<a/>
</td>
<td>
<a href="https://sonarcloud.io/dashboard?id=org.survey%3Amicroservice-example">
<image src="https://sonarcloud.io/api/project_badges/measure?project=org.survey%3Amicroservice-example&metric=alert_status"/>
<a/>
</td>
<td>
<a href="https://sonarcloud.io/dashboard?id=org.survey%3Amicroservice-example">
<image src="https://sonarcloud.io/api/project_badges/measure?project=org.survey%3Amicroservice-example&metric=coverage"/>
<a/>
</td>
<td>
<a href="https://snyk.io/test/github/markoniemi/microservice-example">
<image src="https://snyk.io/test/github/markoniemi/microservice-example/badge.svg"/>
</td>
</tr>


### Run user-repository w/o config-server, using local defaults: 

```
mvn spring-boot:run -P local
```

or

```
java -jar user-repository/target/user-repository-0.1-SNAPSHOT.jar -Dspring.cloud.config.enabled=false -Dspring.profiles.active=local
```

### Run user-repository with config server:


```
mvn -f config-server/pom.xml spring-boot:run
mvn -f user-repository/pom.xml spring-boot:run
```

or

```
java -jar config-server/target/config-server-0.1-SNAPSHOT.jar
java -jar user-repository/target/user-repository-0.1-SNAPSHOT.jar
```

### Test config server:
[discovery service](http://localhost:8081/config-server/)
[config service](http://localhost:8081/config-server/user-repository-default.properties)

### Test user-repository:
[hello](http://localhost:8082/user-repository/hello/test)
[users](http://localhost:8082/user-repository/api/rest/users)


@startuml

title Spring Cloud

package config-server {
    [spring-config]
    [<<service-discovery>>\nEureka]
}
[<<service-discovery>>\nEureka] -up- registry
[spring-config] -right-> registry: register
[spring-config] -up- config
[user-repository] --> config: read
[user-repository] --> registry: read & register
[spring-config] -down-> [github]
[spring-config] -down-> [filesystem]
[spring-config] -down-> [<<config>>\nConsul]

@enduml