package com.udemy.flightReservation.repository

import com.udemy.flightReservation.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User?, Long?> {
    fun findByEmail(email: String?): User?
}