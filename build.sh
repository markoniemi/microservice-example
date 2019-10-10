SERVICE_URL_DEFAULT_ZONE=$(ip route get 1 | awk '{print $NF;exit}')
#SERVICE_URL_DEFAULT_ZONE=$(ip -4 addr show docker0 | grep -Po 'inet \K[\d.]+')
echo $SERVICE_URL_DEFAULT_ZONE
docker run \
-it --rm \
-p 8761:8761 \
-p 8081:8081 \
-p 8082:8082 \
-v "$PWD":/usr/src \
-v "$HOME/.m2":/root/.m2 \
-w /usr/src \
-e SERVICE_URL_DEFAULT_ZONE=$SERVICE_URL_DEFAULT_ZONE \
--add-host="localhost:10.0.2.15" \
--add-host="configserver:10.0.2.15" \
maven:3.6-jdk-8-alpine \
mvn package install verify

#-e SERVICE_URL_DEFAULT_ZONE=$(ip -4 addr show docker0 | grep -Po 'inet \K[\d.]+') \
