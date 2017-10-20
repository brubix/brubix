#!/usr/bin/env bash

# stop if container were running already
./stop.sh

# build project and images
./build.sh

#start mysql container
docker run --name b-rubix-mysql -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=brubix -e MYSQL_USER=brubix -e MYSQL_PASSWORD=brubix -p 3306:3306  -d mysql:latest

# to mysql to start
echo "Waiting 20 seconds for MySQL to come up"
sleep 20s

# start b-rubix-service container
docker run --name b-rubix-service --link b-rubix-mysql:localhost -p 8080:8080 -d b-rubix-service:SNAPSHOT
