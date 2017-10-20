# Custom MySql

This repository provides everything you need to run MySql 5.7 in docker.
The project is inspired from[mysql](https://github.com/docker-library/mysql) whose public image is available [here](https://hub.docker.com/r/mysql/mysql-server/)

## Usage
* Navigate to the mysql folder<br>
``` cd mysql/ ```

* Execute the ``` buildImage ``` script
``` ./buildImage.sh ```

## Running the docker

[source,bash]
```bash
 $ docker run --name b-rubix-mysql -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=brubix -e MYSQL_USER=brubix -e MYSQL_PASSWORD=brubix -p 3306:3306  -d b-rubix-mysql:0.1
```