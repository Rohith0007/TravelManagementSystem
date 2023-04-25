package com.TravelManagement.entity

import com.TravelManagement.entity.common.AbstractEntity
import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity

class Reservation : AbstractEntity() {
    var checkedIn: Boolean? = null
    var numberOfBags: Int? = null

    @OneToOne
    var passenger: Passenger? = null

    @OneToOne
    var flight: Flight? = null

    @OneToOne
    var train: Train? = null

    @OneToOne
    var bus: Bus? = null
}