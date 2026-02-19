# Reward Application

## Description
This application calculates reward points for customers based on transactions.

Points Calculation Logic:
- 2 points for every dollar spent above $100
- 1 point for every dollar spent between $50 and $100

---

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 In Memory Database
- Maven
- JUnit 5
- Mockito

---

## Project Structure

src/main/java
  ├── controller
  ├── service
  ├── repository
  ├── entity
  ├── dto
  └── exception

src/test/java
  ├── testcontroller
  └── testservice

---

## API Details

### Calculate Rewards

GET  
# Reward Application

## Description
This application calculates reward points for customers based on transactions.

Points Calculation Logic:
- 2 points for every dollar spent above $100
- 1 point for every dollar spent between $50 and $100

---

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 In Memory Database
- Maven
- JUnit 5
- Mockito

---

## Project Structure

src/main/java
  ├── controller
  ├── service
  ├── repository
  ├── entity
  ├── dto
  └── exception

src/test/java
  ├── testcontroller
  └── testservice

---

## API Details

### Calculate Rewards

GET  
/api/v1/rewards/calculate?startDate=2025-01-01&endDate=2025-03-31

### Sample Response

```json
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
