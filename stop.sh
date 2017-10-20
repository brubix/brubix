#!/usr/bin/env bash

# stop mysql
echo "Stopping MySQL container"
docker stop b-rubix-mysql
echo "MySQL container stopped"

echo "Removing MySQL container"
docker rm b-rubix-mysql
echo "MySQL container removed "

# stop b-rubix-service
echo "Stopping b-rubix service container"
docker stop b-rubix-service
echo "b-rubix service container stopped"

echo "Removing b-rubix service container"
docker rm b-rubix-service
echo "b-rubix service container removed"
