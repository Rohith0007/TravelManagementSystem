package com.TravelManagement.controller

import com.TravelManagement.repository.BusRepository
import com.TravelManagement.repository.FlightRepository
import com.TravelManagement.repository.TrainRepository
import com.TravelManagement.service.ReservationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/reservation")
class ReservationController {
    @Autowired
    var flightRepository: FlightRepository? = null

    @Autowired
    var trainRepository: TrainRepository? = null

    @Autowired
    var busRepository: BusRepository? = null

    @Autowired
    var reservationService: ReservationService? = null

    @RequestMapping("/{id}")
    fun showCompleteReservation(@PathVariable("id") id: Long, @RequestParam(value = "mot", required = false) mot: String?, model: Model): String {
        return if (mot == "train") {
            val trains = trainRepository!!.findById(id).get()
            model.addAttribute("trains", trains)
            model.addAttribute("train", trains)
            "completeReservation_T"
        } else if (mot == "flight") {
            val flight = flightRepository!!.findById(id).get()
            model.addAttribute("flight", flight)
            model.addAttribute("flights", flight)
            "completeReservation_F"
        } else {
            val buses = busRepository!!.findById(id).get()
            model.addAttribute("buses", buses)
            model.addAttribute("bus", buses)
            "completeReservation_B"
        }
    }


    @PostMapping("/complete-reservation")
    fun completeReservation(@ModelAttribute reservationRequest: com.TravelManagement.Dto.ReservationRequest, model: Model): String {
        val filePath = reservationService!!.bookFlight(reservationRequest)
        model.addAttribute("filePath", filePath)
        if (reservationRequest.flightId != null) {
            val flight = reservationRequest.flightId?.let { flightRepository!!.findById(it).get() }
            model.addAttribute("flight", flight)
            model.addAttribute("reservationRequest", reservationRequest)
            println(filePath)
            return "reservationConfirmation_F"
        } else if (reservationRequest.trainId != null) {
            val train = reservationRequest.trainId?.let { trainRepository!!.findById(it).get() }
            model.addAttribute("train", train)
            model.addAttribute("reservationRequest", reservationRequest)
            println(filePath)
            return "reservationConfirmation_T"
        } else {
            val bus = reservationRequest.busId?.let { busRepository!!.findById(it).get() }
            model.addAttribute("bus", bus)
            model.addAttribute("reservationRequest", reservationRequest)
            println(filePath)
            return "reservationConfirmation_B"
        }
    }
}