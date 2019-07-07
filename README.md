# Getting Started

### This project uses an embedded database
##### An initial Stock is created and persisted at start-up

### For Project build run the command
./gradlew clean build

### To start the Spring boot application run the command
./gradlew bootRun

###### Embedded Tomcat is started on localhost:9092
###### Application is accessible at localhost:9092/farmshop

###### The H2 console is available at localhost:9092/farmshop/h2-console. 
###### Use credentials in applications.properties

### To access the Actuator endpoints:

###### localhost:9092/farmshop/actuator/health
###### localhost:9092/farmshop/actuator/info
###### localhost:9092/farmshop/metrics


