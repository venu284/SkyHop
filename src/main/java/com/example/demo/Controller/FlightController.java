package com.example.demo.Controller;

import com.example.demo.Model.Flight;
import com.example.demo.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
public class FlightController {

    FlightService flightService;

    @Autowired
    public FlightController( FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flight/search")
    public ResponseEntity<List<Flight>> retriveFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String date) {
        System.out.println("Decoded date: " + date); // Prints 2024-11-20 00:00:00
        List<Flight> flights = flightService.retriveFlights(origin,destination,date);
        if (flights.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("Message", "No flights available for the selected route and date.")
                    .body(Collections.emptyList());
        }
        return ResponseEntity.ok(flights);

    }


}
