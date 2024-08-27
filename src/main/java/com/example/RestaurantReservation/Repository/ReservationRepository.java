package com.example.RestaurantReservation.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RestaurantReservation.Model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByReservationDate(LocalDate date);
    List<Reservation> findAllByReservationDateBetween(LocalDate startDate, LocalDate endDate);
}
