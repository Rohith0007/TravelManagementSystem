package com.udemy.flightReservation.repository

import com.udemy.flightReservation.entity.Flight
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface FlightRepository : JpaRepository<Flight?, Long?> {
    @Query(value = "from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity and dateOfDeparture=:dateOfDeparture")
    fun findFlights(
        @Param("departureCity") from: String?,
        @Param("arrivalCity") to: String?,
        @Param("dateOfDeparture") dateOfDeparture: Date?
    ): List<Flight?>?
}