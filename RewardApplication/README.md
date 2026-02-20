# Reward Application

##Description

This Spring Boot application calculates reward points for customers based on their transactions within a given date range.

The API supports:
-Reward calculation per customer
-Monthly reward breakdown
-Total reward calculation
-Date range validation (maximum 3 months)
-Global exception handling

##Reward Points Logic
-2 points for every dollar spent above $100
-1 point for every dollar spent between $50 and $100
-No points for amounts below $50

Example:
If transaction amount = $120
	(120 − 100) × 2 = 40
	(100 − 50) × 1 = 50
	Total = 90 points
	
If transaction amount = $120.75
	(120.75 − 100) × 2 = 41.5
	50
	Total = 91.5 points
	
##Tech Stack
-Java 17
-Spring Boot 3.2.5
-Spring Data JPA
-H2 In-Memory Database
-Maven
-JUnit 5
-Mockito

#Project Structure
src/main/java/com/demo/reward
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 └── exception

src/test/java/com/demo/reward
 ├── testcontroller
 └── testservice
 
 ##Api details
 Calculate Rewards
 /api/v1/rewards/calculate?startDate=2025-01-01&endDate=2025-03-31
 
# Validation Rules
-Start date must not be after end date
-Date range must not exceed 3 months
-Transactions must exist in given date range

#Sample Response
{
  "customerResponse": [
    {
      "customerId": 1,
      "customerName": "Meena",
      "monthlyRewards": {
        "JANUARY": 90
      },
      "totalRewards": 90
    }
  ]
}

##Running Application
-Clean and Compile
	mvn clean compile
-Run Application
	mvn spring-boot:run
-Application Runs at
	http://localhost:8081
-Running Test
	mvn test
-Run clean build with test
	mvn clean install