package com.TravelManagement.repository

import com.TravelManagement.entity.Bus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface BusRepository : JpaRepository<Bus?, Long?> {
    @Query(value = "from Bus where fromStation=:from and toStation=:to and dateOfDeparture=:date")
    fun findBuses(
        @Param("from") from: String?,
        @Param("to") to: String?,
        @Param("date") date: Date?
    ): List<Bus?>?

    @Query("SELECT DISTINCT b.fromStation FROM Bus b")
    fun findFromCities(): List<String?>?

    @Query("SELECT DISTINCT b.toStation FROM Bus b")
    fun findToCities(): List<String?>?
}