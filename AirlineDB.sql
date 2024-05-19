CREATE DATABASE AirlineDB;

USE AirlineDB;

-- Table for Flights
CREATE TABLE Flights (
    FlightID INT PRIMARY KEY AUTO_INCREMENT,
    FlightNumber VARCHAR(10),
    Origin VARCHAR(50),
    Destination VARCHAR(50),
    DepartureDate DATE,
    ArrivalDate DATE,
    AvailableSeats INT,
    ClassType VARCHAR(20)
);

-- Table for Reservations
CREATE TABLE Reservations (
    ReservationID INT PRIMARY KEY AUTO_INCREMENT,
    FlightID INT,
    CustomerName VARCHAR(100),
    Email VARCHAR(100),
    SeatsReserved INT,
    FOREIGN KEY (FlightID) REFERENCES Flights(FlightID)
);
