package com.example.RestaurantReservation.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RestaurantReservation.Model.Reservation;
import com.example.RestaurantReservation.Repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation addReservation(Reservation reservation) {
        LocalDate reservationDate = reservation.getReservationDate();
        
        if (isHoliday(reservationDate)) {
            throw new IllegalArgumentException("The restaurant is closed on Wednesday and Friday. Please choose another day.");
        }
        
        if (isFullyBooked(reservationDate)) {
            throw new IllegalArgumentException("The restaurant is fully booked for this date.");
        }
        
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsForWeek(LocalDate weekStartDate) {
        LocalDate weekEndDate = weekStartDate.plusDays(6);
        return reservationRepository.findAllByReservationDateBetween(weekStartDate, weekEndDate);
    }

    private boolean isHoliday(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.WEDNESDAY || dayOfWeek == DayOfWeek.FRIDAY;
    }

    private boolean isFullyBooked(LocalDate date) {
        long reservationCount = reservationRepository.findByReservationDate(date).size();
        return reservationCount >= 2;
    }
}
