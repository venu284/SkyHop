SET FOREIGN_KEY_CHECKS = 0;

drop table if exists ticket;
drop table if exists card;
drop table if exists reservation;
drop table if exists flight;
drop table if exists passenger;
drop table if exists airport;
drop table if exists airplane;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE IF NOT EXISTS Airport (
    airportCode VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    PRIMARY KEY (airportCode)
    );

INSERT INTO Airport (airportCode, city, country) VALUES
                                                     ('ATL', 'Atlanta', 'United States'),
                                                     ('LAX', 'Los Angeles', 'United States'),
                                                     ('ORD', 'Chicago', 'United States'),
                                                     ('DFW', 'Dallas/Fort Worth', 'United States'),
                                                     ('JFK', 'New York', 'United States'),
                                                     ('DEN', 'Denver', 'United States'),
                                                     ('SFO', 'San Francisco', 'United States'),
                                                     ('SEA', 'Seattle', 'United States'),
                                                     ('MIA', 'Miami', 'United States'),
                                                     ('LAS', 'Las Vegas', 'United States');
CREATE TABLE IF NOT EXISTS Airplane (
                                        airplaneId BIGINT NOT NULL AUTO_INCREMENT,
                                        airline VARCHAR(255) NOT NULL,
    planeModel VARCHAR(255) NOT NULL,
    capacity INTEGER NOT NULL,
    PRIMARY KEY (airplaneId)
    );
INSERT INTO Airplane (airplaneId,airline, planeModel, capacity) VALUES
                                                                    (01,'Delta Air Lines', 'Boeing 737', 160),
                                                                    (02,'American Airlines', 'Airbus A320', 150),
                                                                    (03,'United Airlines', 'Boeing 777', 300),
                                                                    (04,'Southwest Airlines', 'Boeing 737', 175),
                                                                    (05,'JetBlue Airways', 'Airbus A321', 200),
                                                                    (06,'Alaska Airlines', 'Boeing 737', 180),
                                                                    (07,'Spirit Airlines', 'Airbus A320', 145),
                                                                    (08,'Frontier Airlines', 'Airbus A321', 230),
                                                                    (09,'Hawaiian Airlines', 'Airbus A330', 278),
                                                                    (10,'Allegiant Air', 'Airbus A319', 156);


CREATE TABLE IF NOT EXISTS Flight (
                                      flightID BIGINT NOT NULL AUTO_INCREMENT,
                                      airplaneID BIGINT NOT NULL,
                                      startAirportCode VARCHAR(255) NOT NULL,
    endAirportCode VARCHAR(255),
    departureTime TIMESTAMP NOT NULL,
    duration INT NOT NULL,
    flightStatus VARCHAR(255),
    PRIMARY KEY (flightID),
    FOREIGN KEY (airplaneID) REFERENCES Airplane(airplaneId),
    FOREIGN KEY (startAirportCode) REFERENCES Airport(airportCode),
    FOREIGN KEY (endAirportCode) REFERENCES Airport(airportCode)
    );
--
INSERT INTO Flight (airplaneId, startAirportCode, endAirportCode, departureTime, duration, flightStatus) VALUES
                                                                                                                         (01, 'ATL', 'LAX', '2025-04-25 08:00:00', 300, 'SCHEDULED'),
                                                                                                                         (02, 'JFK', 'ORD', '2024-04-25 09:00:00', 150, 'SCHEDULED'),
                                                                                                                         (03, 'SFO', 'SEA', '2024-04-25 10:00:00', 120, 'SCHEDULED'),
                                                                                                                         (04, 'DEN', 'DFW', '2024-04-25 11:00:00', 180, 'SCHEDULED'),
                                                                                                                         (05, 'MIA', 'ATL', '2024-04-24 12:00:00', 180, 'SCHEDULED'),
                                                                                                                         (06, 'SEA', 'LAS', '2024-04-24 13:00:00', 150, 'SCHEDULED'),
                                                                                                                         (07, 'LAX', 'DFW', '2024-04-24 14:00:00', 90, 'SCHEDULED'),
                                                                                                                         (08, 'ORD', 'ATL', '2024-04-24 15:00:00', 180, 'SCHEDULED');