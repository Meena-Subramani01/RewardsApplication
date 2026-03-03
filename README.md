This is a sample Java/Maven/Spring Boot application which provides RESTful services. It can be used as a starter project.

##Reward Points Logic##
- 2 points for every dollar spent above $100
- 1 point for every dollar spent between $50 and $100
- No points for amounts below $50

##Prerequisites##
```
Before diving in, ensure you have the following:
- Java Development Kit (JDK): Download the latest version from the https://www.oracle.com/in/java/technologies/downloads/.
- Postman: Install Postman to test the APIs from the https://www.postman.com/downloads/



##Installation Instructions##
```
- You can import the project as a Maven application into your favorite IDE. I tested it using Spring Tool Suite 5.0.1.
- If Lombok causes issues, refer to the https://stackoverflow.com/questions/56523530/lombok-installation-in-spring-tool-suite-4-for-windows to install it using the jar file
----


##Running the Application##
```
Use one of the following ways to run the Spring Boot application:
- Using Maven: mvn clean package java -jar RewardApplication-0.0.1-SNAPSHOT.jar
- On Unix/Linux Systems: mvn clean package./RewardApplication-0.0.1-SNAPSHOT.jar
----

##Project Structure##
```
src/main/java/com/demo/reward
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 └── exception
```


src/test/java/com/demo/reward
 ├── testcontroller
 └── testservice
 ```

##Built With##
- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- H2 In-Memory Database
- Maven
- JUnit 5
- Mockito


##Testing the Application##
----
###1. Seed Data###
   - Endpoint : POST/api/v1/seed
   - Response:
        Data Seeded Successfully!
----

###2. Calculate Reward Points###
   - Endpoint: GET/api/v1/reward/calculate
   - Parameters :
       startDate : Start date  in yyyy-MM-dd format.
       endDate: End date in yyyy-MM-dd format.
   - Sample Response:
  {"customerResponse":[{"customerId":4,"customerName":"Ravi","monthlyRewards":{"JANUARY":250.0},"totalRewards":250.0},          {"customerId":33,"customerName":"John","monthlyRewards":    {"JANUARY":90.0},"totalRewards":90.0},{"customerId":1,"customerName":"Meena","monthlyRewards":{"JANUARY":90.0,"FEBRUARY":25.0},"totalRewards":115.0},{"customerId":2,"customerName":"Ravi","monthlyRewards":{"JANUARY":250.0},"totalRewards":250.0},{"customerId":3,"customerName":"Meena","monthlyRewards":{"JANUARY":90.0,"FEBRUARY":25.0},"totalRewards":115.0}]}
