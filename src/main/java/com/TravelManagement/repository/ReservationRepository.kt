package com.TravelManagement.repository

import com.TravelManagement.entity.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository : JpaRepository<Reservation?, Long?>