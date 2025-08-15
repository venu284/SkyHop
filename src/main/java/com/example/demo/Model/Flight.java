package com.example.demo.Model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
//@Data
@Table(name="Flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flightID", nullable = false)
    private Long flightID;

    @ManyToOne
    @JoinColumn(name = "airplaneID", nullable = false)
    private Airplane airplaneID;


    @ManyToOne
    @JoinColumn(name = "startAirportCode", nullable = false)
    private Airport startAirportCode;

    @ManyToOne
    @JoinColumn(name = "endAirportCode", nullable = false)
    private Airport endAirportCode;

    @Column(name = "departureTime", nullable = false)
    private Timestamp departureTime;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "flightStatus", nullable = true)
    private FlightStatus flightStatus = FlightStatus.SCHEDULED;

    public Long getFlightID() {
        return flightID;
    }

    public void setFlightID(Long flightID) {
        this.flightID = flightID;
    }

    public Airplane getAirplaneID() {
        return airplaneID;
    }

    public void setAirplaneID(Airplane airplaneID) {
        this.airplaneID = airplaneID;
    }

    public Airport getStartAirportCode() {
        return startAirportCode;
    }

    public void setStartAirportCode(Airport startAirportCode) {
        this.startAirportCode = startAirportCode;
    }

    public Airport getEndAirportCode() {
        return endAirportCode;
    }

    public void setEndAirportCode(Airport endAirportCode) {
        this.endAirportCode = endAirportCode;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }
}
