package com.example.demo.Model;

import jakarta.persistence.*;

import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passengerID", nullable = false)
    private Long passengerID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;


    @Column(name = "email", nullable = false)
    @Email
    private String email;

//    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Card> cards;


    @Column(name = "password")
    private String password;

//    @ManyToMany
//    @JoinTable( name = "passenger_payment",
//            joinColumns = @JoinColumn(name = "passengerID"),
//            inverseJoinColumns = @JoinColumn(name = "paymentID") )
//    private List<Card> payments = new ArrayList<Card>();


    public Long getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(Long passengerID) {
        this.passengerID = passengerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

//    public List<Card> getPayments() {
//        return payments;
//    }
//
//    public void setPayments(List<Card> payments) {
//        this.payments = payments;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
} // Passenger
