package com.example.demo.security;

import com.example.demo.Model.Passenger;
import com.example.demo.Repository.PassengerRepository;
import com.example.demo.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Authenticate user and generate JWT token
     *
     * @param email:    User's email
     * @param password: User's password
     * @return JWT token if authentication is successful
     * @throws IllegalArgumentException if user is not found or password is incorrect
     */
    public String authenticateUser(String email, String password) {
        // Find the passenger by email
        try {
            Passenger passenger = passengerRepository.findByEmail(email)
                    .orElseThrow(() -> new InvalidCredentialsException("User not found"));

            // Verify the password
            if (!passwordEncoder.matches(password, passenger.getPassword())) {
                throw new InvalidCredentialsException("Invalid credentials");
            }

            // Generate JWT token after successful authentication
            return jwtTokenUtil.generateToken(passenger.getEmail(), passenger.getPassengerID());

        } catch (InvalidCredentialsException e) {
            throw e; // Pass this exception to be handled centrally
        } catch (Exception e) {
            // Catch unexpected exceptions and rethrow with a generic error
            throw new RuntimeException("An unexpected error occurred during authentication", e);
        }
    }
}
