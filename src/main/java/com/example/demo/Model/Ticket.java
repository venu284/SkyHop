package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="Ticket")
public class Ticket {
    public Ticket(Flight flight, String seatNumber, Double unitPrice, Passenger passenger) {
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.unitPrice = unitPrice;
//        this.passenger = passenger;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketID", nullable = false)
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "flightID")
    private Flight flight;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "reservationID", nullable = true)
    private Reservation reservation;

    @Column(name = "seatNumber", nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private Double unitPrice = 50D;

    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;

//    @ManyToOne
//    @JoinColumn(name = "passengerID")
//    private Passenger passenger;

    public Ticket() {

    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

//    public Passenger getPassenger() {
//        return passenger;
//    }
//
//    public void setPassenger(Passenger passenger) {
//        this.passenger = passenger;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    }
} // Ticket
