# Custom MySql

This repository provides everything you need to run MySql 5.7 in docker.
The project is inspired from [mysql/docker-kafka](https://github.com/docker-library/mysql) whose public image is available [here](https://hub.docker.com/r/mysql/mysql-server/).


* Execute the ``` buildImage ``` script
``` ./buildImage.sh ```

## Running the docker

```bash
$ docker run --name b-rubix-mysql -e MYSQL_ROOT_PASSWORD=root -d b-rubix-mysql:0.1
```


