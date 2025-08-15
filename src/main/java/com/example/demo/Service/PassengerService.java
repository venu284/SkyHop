package com.example.demo.Service;

import com.example.demo.Model.Passenger;
import com.example.demo.Model.Reservation;
import com.example.demo.dto.RegisterPassengerRequestDTO;
import com.example.demo.Repository.PassengerRepository;
import com.example.demo.exception.PassengerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    PassengerRepository passengerRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Autowired
    public PassengerService(PassengerRepository passengerRepository){
        this.passengerRepository = passengerRepository;
    }

    public Passenger getPassengerData(Long passengerId){
        return passengerRepository.findById(passengerId)
                .orElseThrow(() -> new PassengerNotFoundException("Passenger not found with id: " + passengerId));
    }

    public void registerPassenger(RegisterPassengerRequestDTO request) {
        // Check for duplicate email or phone
        if (passengerRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        if (passengerRepository.existsByPhoneNumber(request.getPhone())) {
            throw new IllegalArgumentException("Phone number is already in use.");
        }

        // Create a new passenger
        Passenger passenger = new Passenger();
        passenger.setName(request.getName());
        passenger.setPhoneNumber(request.getPhone());
        passenger.setEmail(request.getEmail());
        passenger.setPassword(passwordEncoder.encode(request.getPassword())); // Hash the password

        // Save the passenger to the database
        passengerRepository.save(passenger);
    }

}
