Sample CDC balance
==================================================
CDC Sample with Spring Cloud Stream CDC Debezium (Embedded), and dockerized Apache Kafka, Apache Zookeeper and SQL Server.


*** This project is in progress. *** 

To know about CDC: https://en.wikipedia.org/wiki/Change_data_capture

Related technologies: 

* Kotlin: https://kotlinlang.org/docs/reference/
* Debezium (Embedded): https://debezium.io/documentation/reference/1.0/architecture.html#_embedded_engine
* Docker: https://www.docker.com/resources/what-container
* Apache Kafka: https://kafka.apache.org/intro 
* Zookeeper: https://zookeeper.apache.org/ 

What's Here
-----------

This sample includes:

* branch master - Common Spring Boot application with servlet container. 

* README.md - this file
* docker-compose.yml - this file is used by Docker Compose (https://docs.docker.com/compose/) to running Kafka, Zookeeper and SQL Server
* cdc-balance - this directory contains your CDC source files
* balance-api - this directory contains your Balance API to change the database
* db-init - this directory contains the scripts to create the SQL Server database and tables to run this CDC example 


Getting Started
---------------

To work on the sample code, you'll need to clone project's repository to your
local computer. If you haven't, do that first.

1. Install maven.  See https://maven.apache.org/install.html for details.

2. Install Docker (to run Kafka, Zookeeper and SQL Server). See https://docs.docker.com/install/

3. Build the services.

        $ cd sample-cdc-balance/cdc-balance
        $ mvn clean install
        
        $ cd sample-cdc-balance/balance-api
        $ mvn clean install

4. Run the Docker Compose.

        $ docker-compose up -d

5. Run the CDC Balance project
        
        $ cd cdc-balance 
        $ mvn spring-boot:run 
        
6. Run the Balance API
        
        $ cd balance-api
        $ mvn spring-boot:run 

...