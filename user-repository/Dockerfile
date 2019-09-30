FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/BOOT-INF/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY target/user-repository-0.1-SNAPSHOT.jar /app/user-repository-0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app/user-repository-0.1-SNAPSHOT.jar"]
