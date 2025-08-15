
package  com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.example.demo.dto.ReservationRequestDTO;
import com.example.demo.exception.PassengerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

        TicketRepository ticketRepository;
        FlightRepository flightRepository;
        PassengerRepository passengerRepository;
        ReservationRepository reservationRepository;

        @Autowired
        public ReservationService(TicketRepository ticketRepository
                ,FlightRepository flightRepository
                ,PassengerRepository passengerRepository
                ,ReservationRepository reservationRepository) {
            this.ticketRepository = ticketRepository;
            this.flightRepository = flightRepository;
            this.passengerRepository = passengerRepository;
            this.reservationRepository = reservationRepository;
        }

    public List<Reservation> viewReservation(Long passengerId) {
        return reservationRepository.viewReservations(passengerId);
    }

    public Long createReservation(ReservationRequestDTO reservationRequestDTO) {
        if (reservationRequestDTO.getPassengerId() == null) {
            throw new IllegalArgumentException("PassengerID is required");
        }

        Passenger passenger = passengerRepository.findById(reservationRequestDTO.getPassengerId())
                .orElseThrow(() -> new PassengerNotFoundException("Passenger with ID " + reservationRequestDTO.getPassengerId() + " not found"));
        // Map DTO to Reservation entity
        Reservation reservation = new Reservation();
        reservation.setPassenger(passenger);
        reservation.setTickets(new ArrayList<>()); // Empty ticket list as per requirements
        reservation.setCardType(reservationRequestDTO.getCardType());
        reservation.setCardNumber(reservationRequestDTO.getCardNumber());
        reservation.setCardCode(reservationRequestDTO.getCardCode());
        reservation.setZipCode(reservationRequestDTO.getZipcode());
        reservation.setExpirationDate(reservationRequestDTO.getExpiryDate());
        reservation.setTotalPrice(reservationRequestDTO.getTotalPrice()); // Default totalPrice for now

        // Save and return the Reservation ID
        Reservation savedReservation = reservationRepository.save(reservation);
        return savedReservation.getReservationID();
    }
}
