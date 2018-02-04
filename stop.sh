#!/usr/bin/env bash

# stop mysql
echo "Stopping MySQL container"
docker stop b-rubix-mysql

echo "Removing MySQL container"
docker rm b-rubix-mysql

# stop b-rubix-reference
echo "Stopping b-rubix reference container"
docker stop b-rubix-reference

echo "Removing b-rubix reference container"
docker rm b-rubix-reference



# stop b-rubix-identity
echo "Stopping b-rubix identity container"
docker stop b-rubix-identity

echo "Removing b-rubix identity container"
docker rm b-rubix-identity


# stop b-rubix-service
echo "Stopping b-rubix service container"
docker stop b-rubix-service

echo "Removing b-rubix service container"
docker rm b-rubix-service



# stop b-rubix-connect
echo "Stopping b-rubix connect container"
docker stop b-rubix-connect

echo "Removing b-rubix connect container"
docker rm b-rubix-connect