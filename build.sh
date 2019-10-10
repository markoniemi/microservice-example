docker run \
-it --rm \
-p 8761:8761 \
-p 8081:8081 \
-p 8082:8082 \
-v "$PWD":/usr/src \
-v "$HOME/.m2":/root/.m2 \
-w /usr/src \
maven:3.6-jdk-8-alpine \
mvn package install verify
