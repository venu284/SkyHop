package com.example.demo.Repository;

import com.example.demo.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation r where r.passenger.passengerID = :passengerId")
    List<Reservation> viewReservations(@Param("passengerId") Long passengerid);
}
