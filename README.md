# Term-Project: Airline Ticketing Applicaiton - SkyHop

A flight booking management system focused on database management principles. This application allows users to search, book, and manage flight reservations.

## Features

- User authentication & authorization
- Flight search and filtering
- Seat selection and booking
- Payment processing
- User dashboard for booking management
- Admin panel for system management

## Tech Stack

- **Frontend**: HTML (1.6%), CSS (14.6%), JavaScript (45.1%)
- **Backend**: Java (38.7%), Spring Boot
- **Database**: MySQL/PostgreSQL

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/venu284/Flight-Booking.git
   cd Flight-Booking
   ```

2. **Database Setup**
   ```sql
   CREATE DATABASE flight_booking;
   SOURCE db/scripts/schema.sql;
   ```

3. **Backend Setup**
   ```bash
   cd backend
   mvn clean install
   mvn spring-boot:run
   ```

4. **Frontend Setup**
   ```bash
   cd frontend
   npm install
   npm start
   ```

## Project Structure

- `backend/`: Java Spring Boot application
- `frontend/`: JavaScript/React frontend
- `db/`: Database scripts and schemas

## API Endpoints

Main endpoints include authentication, flight search, booking management, and payment processing.

- **Database Structure**
  
    - Airplane: airplaneId (PK), airline, planeModel, capacity
    - Airport: airportCode (PK), city, country
    - Flight: flightId (PK), departureTime, duration, flightStatus, airplaneId (FK), startAirportCode (FK), endAirportCode (FK)
    - Passenger: passengerId (PK), phoneNumber, email, password, name
    - Ticket: ticketId (PK), seatNumber, unitPrice, flightId (FK), reservationId (FK), firstName, lastName
    - Reservation: reservationId (PK), totalPrice, cardCode, cardNumber, cardType, expirationDate, zipCode, passengerId (FK), flightId (FK)
  
    
## Prerequisites

Before you begin, ensure you have met the following requirements:

- Latest version of Java (Java 23)
- Java Oracle SDK 23.0.1
- MySQL Ver 5.7.24
- Node.js (for React)
- IDE of choice (IntelliJ)

For testing purposes:

- Database: MySQL Workbench
- API Endpoints: Postman (port 8080)

SQL Workbench Setup:

- NOTE: `src/main/resources/application.properties` defines the schema name
- Example: `spring.datasource.url=jdbc:mysql://localhost:3306/**<enter_schema_name_here>**`
- Create a new schema on MySQL Workbench and make sure it matches the name from application.properties
