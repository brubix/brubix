#!/usr/bin/env bash

# stop mysql
echo "Stopping MySQL container"
docker stop b-rubix-mysql

echo "Removing MySQL container"
docker rm b-rubix-mysql

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
