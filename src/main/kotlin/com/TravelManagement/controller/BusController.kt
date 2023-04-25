package com.TravelManagement.controller

import com.TravelManagement.repository.BusRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
@RequestMapping("/buses")
class BusController {
    @Autowired
    private val busRepository: BusRepository? = null
    @RequestMapping
    fun findBuses(
        @RequestParam("from") from: String?,
        @RequestParam("to") to: String?,
        @RequestParam("dateOfDeparture") @DateTimeFormat(pattern = "yyyy-MM-dd") dateOfDeparture: Date?,
        model: Model
    ): String {
        val buses = busRepository!!.findBuses(from, to, dateOfDeparture)
        model.addAttribute("buses", buses)
        return "displayBuses"
    }


    @RequestMapping("/admin/showAddBus")
    fun showAddBus(): String {
        return "addBus"
    }
}