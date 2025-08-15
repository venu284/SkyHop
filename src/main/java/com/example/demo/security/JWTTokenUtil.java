package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenUtil {

    @Value("${jwt.secret.key}")
    private String secretKey; // Automatically injects the value from application.properties

    private static final long EXPIRATION_TIME = 3600000; // 1 hour (in milliseconds)

    /**
     * Generate a JWT token for a user
     * @param username The username of the user (subject)
     * @return JWT token
     */
    public String generateToken(String username, Long passengerID) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", "user"); // Example custom claim
            claims.put("passengerID", passengerID);

            return Jwts.builder()
                    .setClaims(claims) // Custom claims
                    .setSubject(username) // Subject (e.g., username or user ID)
                    .setIssuedAt(new Date(System.currentTimeMillis())) // Issue time
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expiration time
                    .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()) // Signing algorithm
                    .compact(); // Generates the JWT string
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }
    /**
     * Validate the JWT token
     * @param token The JWT token
     * @param username The username of the user
     * @return true if the token is valid
     */
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     * Extract username (subject) from the token
     * @param token The JWT token
     * @return Username (subject) from the token
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Check if the token has expired
     * @param token The JWT token
     * @return true if the token is expired
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extract expiration date from the token
     * @param token The JWT token
     * @return Expiration date of the token
     */
    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    /**
     * Extract all claims from the token
     * @param token The JWT token
     * @return Claims from the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}
