package com.example.demo.Model;

import jakarta.persistence.*;

import javax.print.DocFlavor;

@Entity
//@Data
@Table(name="Airport")
public class Airport {
    @Id
    @Column(name = "airportCode", nullable = false, unique = true)
    private String airportCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}