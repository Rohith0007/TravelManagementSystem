package com.TravelManagement.controller

import com.TravelManagement.repository.FlightRepository
import com.TravelManagement.entity.Flight
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
@RequestMapping("/flights")
class FlightController {
    @Autowired
    var flightRepository: FlightRepository? = null
    @RequestMapping
    fun findFlights(
        @RequestParam("from") from: String?,
        @RequestParam("to") to: String?,
        @RequestParam("dateOfDeparture") @DateTimeFormat(pattern = "yyyy-MM-dd") dateOfDeparture: Date?,
        model: Model
    ): String {
        val flights = flightRepository!!.findFlights(from, to, dateOfDeparture)
        model.addAttribute("flights", flights)
        return "displayFlights"
    }

    @RequestMapping("admin/showAddFlight")
    fun showAddFlight(): String {
        return "addFlight"
    }

    @PostMapping("/add-flight")
    fun addFlight(@ModelAttribute("flight") flight: Flight?): String? {
        if (flight != null) {
            flightRepository!!.save(flight)
        };
        return "flights"
    }
}
