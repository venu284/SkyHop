package com.example.demo.Repository;

import com.example.demo.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

   // @Query("Select  f from Flight f where f.departureAirportCode = ?1 and f.destinationAirportCode = ?2 and f.departureTime >= ?3")
   @Query("SELECT f FROM Flight f WHERE f.startAirportCode.airportCode = ?1 " +
           "AND f.endAirportCode.airportCode = ?2 " +
           "AND f.departureTime >= ?3")
    List<Flight> findFlightByOrginDestinationDepartureTime(
            String origin,
            String destination,
            Timestamp date
    );

}
