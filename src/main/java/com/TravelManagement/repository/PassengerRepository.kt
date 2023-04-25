package com.TravelManagement.repository

import com.TravelManagement.entity.Passenger
import org.springframework.data.jpa.repository.JpaRepository

interface PassengerRepository : JpaRepository<Passenger?, Long?>