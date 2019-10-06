FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN apk --no-cache add curl
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/BOOT-INF/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
EXPOSE 8081
COPY target/config-server-0.1-SNAPSHOT.jar /app/config-server-0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-XX:MaxRAMFraction=1","-XshowSettings:vm","-jar","/app/config-server-0.1-SNAPSHOT.jar"]
HEALTHCHECK --interval=5s --timeout=3s --start-period=40s --retries=5 \
  CMD curl -f http://localhost:8081/user-repository/default || exit 1
