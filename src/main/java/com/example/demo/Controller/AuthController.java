package com.example.demo.Controller;


import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * User login and JWT token generation
     * POST /auth/login
     * Request Body: { "email": "john.doe@example.com", "password": "hashedPassword" }
     * Response: { "token": "<generated JWT token here>" }
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        try {
            String token = authService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok().body("{\"token\": \"" + token + "\"}");
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"message\": \"Invalid credentials\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"An error occurred, please try again later.\"}");
        }
    }


}
