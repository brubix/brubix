= Custom MySql

This repository provides everything you need to run MySql 5.7 in docker.
The project is inspired from https://github.com/docker-library/mysql[mysql] whose public image is available https://hub.docker.com/r/mysql/mysql-server/[here].

* Execute the `buildImage` script
`./buildImage.sh`

== Running the docker

[source,bash]
----
 $ docker run --name b-rubix-mysql -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=brubix -e MYSQL_USER=brubix -e MYSQL_PASSWORD=brubix -p 3306:3306  -d b-rubix-mysql:0.1
----