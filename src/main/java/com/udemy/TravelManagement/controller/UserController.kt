package com.udemy.TravelManagement.controller

import com.udemy.TravelManagement.entity.Flight
import com.udemy.TravelManagement.entity.User
import com.udemy.TravelManagement.repository.BusRepository
import com.udemy.TravelManagement.repository.FlightRepository
import com.udemy.TravelManagement.repository.TrainRepository
import com.udemy.TravelManagement.repository.UserRepository
import com.udemy.TravelManagement.service.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.*


@Controller
class UserController {
    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    var trainRepository: TrainRepository? = null

    @Autowired
    var busRepository: BusRepository? = null

    @Autowired
    var flightRepository: FlightRepository? = null

    @Autowired
    private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @Autowired
    var securityService: SecurityService? = null
    @RequestMapping("/registerUser")
    fun showRegistration(): String {
        return "login/registerUser_2"
    }

    @RequestMapping("/")
    fun indexPage(): String {
        return "index_1"
    }

    @RequestMapping("/loginUser")
    fun showLogin(): String {
        return "login/login_2"
    }

    @RequestMapping("register-user")
    fun register(@ModelAttribute user: User): String {
        user.password = bCryptPasswordEncoder!!.encode(user.password)
        userRepository!!.save(user)
        return "login/login_2"
    }

    @PostMapping("login-user")
    fun login(
        @RequestParam("email") email: String,
        @RequestParam("password") password: String?,
        modelMap: ModelMap
    ): String {
        val userExist = securityService!!.login(email, password)
        if (userExist!!) {
            return "mot_3"
        } else {
            modelMap.addAttribute("msg", "Invalid credentials")
        }
        return "login/login_2"
    }

    @GetMapping("login-user")
    fun loginFromPage(): String {
        return "mot_3"
    }

    @PostMapping("doItAgain")
    fun doItAgain(): String{
        return "mot_3"
    }

    @PostMapping("select-transportation")
    fun mot(
        @RequestParam("transportation") transportation: String,
        model: Model
    ): String? {
        if (transportation == "flight"){
            val fromCities: List<String?>? = flightRepository!!.findFromCities()
            val toCities: List<String?>? = flightRepository!!.findToCities()
            val dates: List<String> = getAvailableDates()
            model.addAttribute("fromCities", fromCities)
            model.addAttribute("toCities", toCities)
            model.addAttribute("dates", dates)
            return "findFlights_4";
        } else if (transportation == "train") {
            val fromCities: List<String?>? = trainRepository!!.findFromCities()
            val toCities: List<String?>? = trainRepository!!.findToCities()
            val dates: List<String> = getAvailableDates()
            model.addAttribute("fromCities", fromCities)
            model.addAttribute("toCities", toCities)
            model.addAttribute("dates", dates)
            return "findTrains_4"
        } else {
            val fromCities: List<String?>? = busRepository!!.findFromCities()
            val toCities: List<String?>? = busRepository!!.findToCities()
            val dates: List<String> = getAvailableDates()
            model.addAttribute("fromCities", fromCities)
            model.addAttribute("toCities", toCities)
            model.addAttribute("dates", dates)
            return "findBuses_4"
        }
        return null;
    }

    fun getAvailableDates(): List<String> {
        val dates: MutableList<String> = ArrayList()
        val calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01")
        calendar.add(Calendar.YEAR, 26)
        val endDate = calendar.time
        calendar.time = SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01")
        while (calendar.time.before(endDate)) {
            val result = calendar.time
            val date = SimpleDateFormat("yyyy-MM-dd").format(result)
            dates.add(date)
            calendar.add(Calendar.DATE, 1)
        }
        return dates
    }

}