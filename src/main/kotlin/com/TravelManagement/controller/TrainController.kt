package com.TravelManagement.controller

import com.TravelManagement.repository.TrainRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Controller
@RequestMapping("/trains")
class TrainController {
    @Autowired
    private val trainRepository: TrainRepository? = null
    @RequestMapping
    fun findTrains(
        @RequestParam("from") from: String?,
        @RequestParam("to") to: String?,
        @RequestParam("dateOfDeparture") @DateTimeFormat(pattern = "yyyy-MM-dd") dateOfDeparture: Date?,
        model: Model
    ): String {
        val trains = trainRepository!!.findTrains(from, to, dateOfDeparture)
        model.addAttribute("trains", trains)
        return "displayTrains"
    }

    @RequestMapping("/admin/showAddTrain")
    fun showAddTrain(): String {
        return "addTrain"
    }
}