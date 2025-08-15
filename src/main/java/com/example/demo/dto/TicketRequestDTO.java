package com.example.demo.dto;

import javax.validation.constraints.NotNull;

public class TicketRequestDTO {

    @NotNull(message = "ReservationID cannot be null")
    private Long reservationID;

    @NotNull(message = "First Name cannot be null")
    private String firstName;

    @NotNull(message = "Last Name cannot be null")
    private String lastName;

    @NotNull(message = "Seat Number cannot be null")
    private String seatNum;

    @NotNull(message = "flight Number cannot be null")
    private Long flightId;


    @NotNull(message = "unit price cannot be null")
    private Double unitPrice;

    public @NotNull(message = "flight Number cannot be null") Long getFlightId() {
        return flightId;
    }
    public @NotNull(message = "unit price cannot be null") Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(@NotNull(message = "unit price cannot be null") Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setFlightId(@NotNull(message = "flight Number cannot be null") Long flightId) {
        this.flightId = flightId;
    }

    public @NotNull(message = "ReservationID cannot be null") Long getReservationID() {
        return reservationID;
    }

    public void setReservationID(@NotNull(message = "ReservationID cannot be null") Long reservationID) {
        this.reservationID = reservationID;
    }

    public @NotNull(message = "First Name cannot be null") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = "First Name cannot be null") String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = "Last Name cannot be null") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull(message = "Last Name cannot be null") String lastName) {
        this.lastName = lastName;
    }

    public @NotNull(message = "Seat Number cannot be null") String getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(@NotNull(message = "Seat Number cannot be null") String seatNum) {
        this.seatNum = seatNum;
    }
}
