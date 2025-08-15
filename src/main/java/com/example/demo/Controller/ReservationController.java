package com.example.demo.Controller;

import com.example.demo.Model.Reservation;
import com.example.demo.Model.Ticket;
import com.example.demo.Service.PassengerService;
import com.example.demo.Service.ReservationService;
import com.example.demo.Service.TicketService;
import com.example.demo.dto.ReservationRequestDTO;
import com.example.demo.dto.TicketRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@CrossOrigin
public class ReservationController {

    private final TicketService ticketService;

    private final PassengerService passengerService;

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService, PassengerService passengerService, TicketService ticketService) {
        this.reservationService = reservationService;
        this.passengerService = passengerService;
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Long> createReservation(@RequestBody ReservationRequestDTO reservationRequestDTO){

        Long reservationID = reservationService.createReservation(reservationRequestDTO);
        return ResponseEntity.ok(reservationID);
    }

    @PostMapping("/ticket/{reservationID}")
    public ResponseEntity<Long> addTicket(@PathVariable Long reservationID, @RequestBody TicketRequestDTO ticketRequestDTO) {
        // Validate reservation ID
        if (!reservationID.equals(ticketRequestDTO.getReservationID())) {
            return ResponseEntity.badRequest().body(null);
        }

        Long ticketID = ticketService.addTicket(ticketRequestDTO);
        return ResponseEntity.ok(ticketID);
    }

    @GetMapping("/{passengerId}")
    public ResponseEntity<List<Reservation>> viewReservation(@PathVariable Long passengerId){
        List<Reservation> reservations =reservationService.viewReservation(passengerId);
        return ResponseEntity.ok(reservations);
    }

}
