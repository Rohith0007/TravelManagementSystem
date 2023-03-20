package com.udemy.flightReservation.controller

import com.udemy.flightReservation.Dto.ReservationRequest
import com.udemy.flightReservation.repository.FlightRepository
import com.udemy.flightReservation.repository.PassengerRepository
import com.udemy.flightReservation.service.ReservationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/reservation")
class ReservationController {
    @Autowired
    var flightRepository: FlightRepository? = null

    @Autowired
    var passengerRepository: PassengerRepository? = null

    @Autowired
    var reservationService: ReservationService? = null
    @RequestMapping("/{id}")
    fun showCompleteReservation(@PathVariable("id") id: Long, model: Model): String {
        val flight = flightRepository!!.findById(id).get()
        model.addAttribute("flight", flight)
        return "completeReservation"
    }

    @PostMapping("/complete-reservation")
    fun completeReservation(@ModelAttribute reservationRequest: ReservationRequest, model: Model): String {
        val filePath = reservationService!!.bookFlight(reservationRequest)
        //        filePath = "file://"+filePath;
        model.addAttribute("filePath", filePath)
        val flight = flightRepository!!.findById(reservationRequest.flightId).get()
        model.addAttribute("flight", flight)
        model.addAttribute("reservationRequest", reservationRequest)
        println(filePath)
        return "reservationConfirmation"
    }
}