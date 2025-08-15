# Term-Project: Airline Ticketing Applicaiton - SkyHop

The Flight E-Booking Application, SkyHop, is a comprehensive platform designed to streamline the flight booking process. 

## Key Features

- **Objective**
  
    Implement a streamlined application to book airline flights.  

- **User Roles**
  
    Catering to individuals that are interested in learning about full-stack development.
  
- **Key Goals**

    - _Secure Data Management_: Passenger accounts store sensitive information securely, including payment details.
    - _Efficient Booking System_: Allows customers to browse flights, make reservations, and receive instant updates.
    - _Scalable Database Design_: Robust database schema with structured data for flights, payments, reservations, and more.
      
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

## Technologies Used

- Frontend: React
- Backend: Java, Spring Boot
- Database: MySQL

## Execution

1. You can download the Zip file and extract it
2. Open the project in you preferred IDE with the root folder named `Arun_TermProject`
3. Navigate to `src/main/resources/application.properties`
4. Modify the following based on your the setup of MySQL: make sure schema name on application.properties is same as MySQL Workbench
   1. `spring.datasource.username= <your_username>`
   2. `spring.datasource.password= <your_password>`
   3. `jwt.secret.key= <your_password>`
6. Run the backend by clicking on the run icon in IDE
7. Check the build terminal, to see if the Spring Boot project is running (API endpoints on localhost:8080, MySQL on port 3306)
8. Open Terminal on IDE and enter the following:
   1. Enter `pwd` to verify that your path is currently `/Arun_TermProject`
   2. Enter `cd frontend/` - navigates to the React frontend
   3. Enter `npm install` - installs all the necessary dependencies
   4. Check terminal to see all dependencies are installed
   5. Then enter `npm start` - start the localhost:3030 server
9. Once both the frontend and backend are running, the project is up and running successfully.

## Flight Table

- This table only contains 8 flight all for Nov 20, 2024
    - <img width="569" alt="image" src="https://github.com/user-attachments/assets/fcab6d92-374b-454d-834e-c89206f83381">


## Contribution

This project is a collaborative effor involving a team of 5 members, including the manager.

Please refer to the file `Manger_Report.pdf`. for details on each member's contributions.

## Documentation

Please refer to the documentation file named `Documentation.pdf`.

## Schema Analysis

Please refer to the Schema Analysis portion in `Documentation.pdf`.
