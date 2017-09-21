# Brubix

## Set up MySQL ##

    Download MySQL and install, then run below commands
         
    
    mysql> create database brubix;
    mysql> create user 'brubix'@'localhost' identified by 'brubix';
    mysql> grant all on brubix.* to 'brubix'@'localhost';    

## Running b-Rubix in local ##
    $ cd brubix
    $ mvn clean install
    $ cd b-rubix-service
    $ mvn spring-boot:run

## b-Rubix Swagger ##  
    http://localhost:8080/v1/swagger-ui.html
    
## b-Rubix Sample Data ##
    https://github.com/brubix/brubix/tree/master/b-rubix-service/src/test/java/com/brubix/brubixservice/data
      
  
    