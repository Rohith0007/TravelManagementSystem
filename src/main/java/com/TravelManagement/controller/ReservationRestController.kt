package com.TravelManagement.controller

import com.TravelManagement.Dto.ReservationUpdateRequest
import com.TravelManagement.entity.Reservation
import com.TravelManagement.repository.ReservationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class ReservationRestController {
    @Autowired
    var reservationRepository: ReservationRepository? = null
    @RequestMapping("/reservations/{id}")
    fun findReservation(@PathVariable id: Long): Reservation {
        return reservationRepository!!.findById(id).get()
    }

    @PostMapping("/reservations")
    fun updateReservation(@RequestBody request: com.TravelManagement.Dto.ReservationUpdateRequest): Reservation {
        val currReservation = reservationRepository!!.findById(request.id).get()
        currReservation.checkedIn = request.checkedIn
        currReservation.numberOfBags = request.numberOfBags
        return reservationRepository!!.save(currReservation)
    }
}