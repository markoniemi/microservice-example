docker run \
-it --rm \
-v /var/run/docker.sock:/var/run/docker.sock \
-v "$PWD":/usr/src \
-v "$HOME/.m2":/root/.m2 \
-w /usr/src \
maven:3.6-jdk-8-alpine \
mvn package -DskipTests=true -P docker "$@"
#mvn package install verify 
#-p 8761:8761 \
#-p 8081:8081 \
#-p 8082:8082 \
