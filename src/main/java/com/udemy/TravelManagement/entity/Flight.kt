package com.udemy.TravelManagement.entity

import com.udemy.TravelManagement.entity.common.AbstractEntity
import java.sql.Timestamp
import java.util.*
import javax.persistence.Entity

@Entity //@Table(name = "FLIGHT",schema = "Udemy")

class Flight : AbstractEntity() {
    var flightNumber: String? = null
    var operatingAirlines: String? = null
    var departureCity: String? = null
    var arrivalCity: String? = null
    var dateOfDeparture: Date? = null
    var estimatedDepartureTime: Timestamp? = null
}