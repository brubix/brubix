#!/usr/bin/env bash

# build project with all tests
mvn clean install

# build docker mysql 5.7
# FIXME custom mysql image not working
# ./custom-docker/mysql/buildImage.sh

# pull mysql image from public docker hub
docker pull mysql

# build docker image for b-rubix-server
cd b-rubix-service
mvn package -Pdocker -DskipTests

# build docker image for b-rubix-identity
cd ..
cd b-rubix-identity
mvn package -Pdocker -DskipTests

cd ..
cd b-rubix-reference
mvn package -Pdocker -DskipTests

cd ..
cd b-rubix-connect
mvn package -Pdocker -DskipTests
