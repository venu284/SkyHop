package com.example.demo.Controller;

import com.example.demo.Model.Passenger;
import com.example.demo.Service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/passenger")
public class PassengerController {

    PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerPassenger(@RequestBody com.example.demo.dto.RegisterPassengerRequestDTO request){
        try {
            passengerService.registerPassenger(request);
            return ResponseEntity.status(201).body("Passenger account created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{passengerId}")
    public ResponseEntity<Passenger> getUserData(@PathVariable Long passengerId){
        Passenger passenger = passengerService.getPassengerData(passengerId);
        return ResponseEntity.ok(passenger);
    }

}
