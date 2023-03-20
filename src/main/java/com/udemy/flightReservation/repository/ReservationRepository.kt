package com.udemy.flightReservation.repository

import com.udemy.flightReservation.entity.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<Reservation?, Long?>