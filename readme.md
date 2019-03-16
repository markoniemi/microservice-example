# Spring microservice example

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
