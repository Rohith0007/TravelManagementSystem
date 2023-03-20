package com.udemy.TravelManagement.controller

import com.udemy.TravelManagement.entity.User
import com.udemy.TravelManagement.repository.UserRepository
import com.udemy.TravelManagement.service.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class UserController {
    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @Autowired
    var securityService: SecurityService? = null
    @RequestMapping("/registerUser")
    fun showRegistration(): String {
        return "login/registerUser"
    }

    @RequestMapping("/loginUser")
    fun showLogin(): String {
        return "login/login"
    }

    @RequestMapping("register-user")
    fun register(@ModelAttribute user: User): String {
        user.password = bCryptPasswordEncoder!!.encode(user.password)
        userRepository!!.save(user)
        return "login/login"
    }

    @PostMapping("login-user")
    fun login(
        @RequestParam("email") email: String,
        @RequestParam("password") password: String?,
        modelMap: ModelMap
    ): String {
        val userExist = securityService!!.login(email, password)
        if (userExist!!) {
            return "findFlights"
        } else {
            modelMap.addAttribute("msg", "Invalid credentials")
        }
        return "login/login"
    }
}