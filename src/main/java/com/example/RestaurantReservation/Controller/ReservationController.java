package com.example.RestaurantReservation.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.RestaurantReservation.Model.Reservation;
import com.example.RestaurantReservation.Service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody Reservation reservation) {
        try {
            Reservation savedReservation = reservationService.addReservation(reservation);
            return ResponseEntity.ok(savedReservation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/week")
    public ResponseEntity<?> getReservationsForWeek(@RequestParam String startDate) {
        try {
            LocalDate weekStartDate = LocalDate.parse(startDate);
            List<Reservation> reservations = reservationService.getReservationsForWeek(weekStartDate);
            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use YYYY-MM-DD.");
        }
    }
}
