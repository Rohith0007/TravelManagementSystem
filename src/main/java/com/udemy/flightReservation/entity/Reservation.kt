package com.udemy.flightReservation.entity

import com.udemy.flightReservation.entity.common.AbstractEntity
import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity //@Table(name = "RESERVATION",schema = "Udemy")

class Reservation : AbstractEntity() {
    var checkedIn: Boolean? = null
    var numberOfBags: Int? = null

    @OneToOne
    var passenger: Passenger? = null

    @OneToOne
    var flight: Flight? = null
}