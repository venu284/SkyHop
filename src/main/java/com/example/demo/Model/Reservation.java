package com.example.demo.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Reservation")
public class Reservation {

    public Reservation(List<Ticket> tickets, Passenger passenger, Double totalPrice) {
        this.tickets = tickets;
        this.passenger = passenger;
        this.totalPrice = totalPrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservationID", nullable  = false)
    private Long reservationID;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
    private List<Ticket> tickets = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "passengerID")
    private Passenger passenger;

    @Column(name = "totalPrice", nullable = false)
    private Double totalPrice;

    @Column (name = "cardType", nullable = false)
    private String cardType;

    @Column (name = "cardNumber",nullable = false)
    private String cardNumber;

    @Column (name = "cardCode",nullable = false)
    private String cardCode;

    @Column (name = "zipCode",nullable = false)
    private String zipCode;

    @Column (name = "expirationDate",nullable = false)
    private String expirationDate;


    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
//    @JoinColumn(name = "flightID")
//    private Flight flightID;



    public Reservation() {

    }

    public Long getReservationID() {
        return reservationID;
    }

    public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

