#!/usr/bin/env bash

# stop if container were running already
./stop.sh

# build project and images
./build.sh

#start mysql container
docker run --name b-rubix-mysql -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=brubix -e MYSQL_USER=brubix -e MYSQL_PASSWORD=brubix -p 3306:3306  -d mysql

# to mysql to start
echo "Waiting 30 seconds for MySQL to come up"
sleep 30s

# start b-rubix-service container
# FIXME - link containers, to access MySql
# docker run --name b-rubix-service -p 8080:8080 -d b-rubix-service:SNAPSHOT
# docker run --name b-rubix-service --link b-rubix-mysql:mysql -p 8080:8080 -d b-rubix-service:SNAPSHOT

# run by maven
cd b-rubix-service
mvn spring-boot:run
