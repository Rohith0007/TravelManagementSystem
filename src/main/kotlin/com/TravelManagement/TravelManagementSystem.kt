package com.TravelManagement

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class TravelManagementSystem {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(com.TravelManagement.TravelManagementSystem::class.java, *args)
        }
    }
}
