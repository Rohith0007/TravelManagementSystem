package com.udemy.flightReservation.service

import com.udemy.flightReservation.Dto.ReservationRequest
import com.udemy.flightReservation.entity.Passenger
import com.udemy.flightReservation.entity.Reservation
import com.udemy.flightReservation.repository.FlightRepository
import com.udemy.flightReservation.repository.PassengerRepository
import com.udemy.flightReservation.repository.ReservationRepository
import com.udemy.flightReservation.util.EmailUtil
import com.udemy.flightReservation.util.PdfGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReservationService {
    @Autowired
    var flightRepository: FlightRepository? = null

    @Autowired
    var passengerRepository: PassengerRepository? = null

    @Autowired
    var reservationRepository: ReservationRepository? = null

    @Autowired
    var pdfGenerator: PdfGenerator? = null

    @Autowired
    var emailUtil: EmailUtil? = null
    fun bookFlight(reservationRequest: ReservationRequest): String? {
        try {
            val flight = flightRepository!!.findById(reservationRequest.flightId).get()
            val passenger = Passenger()
            passenger.firstName = reservationRequest.getpFirstName()
            passenger.lastName = reservationRequest.getpLastName()
            passenger.email = reservationRequest.getpEmail()
            passenger.phone = reservationRequest.getpPhone()
            val savedPassenger = passengerRepository!!.save(passenger)
            val reservation = Reservation()
            reservation.checkedIn = false
            reservation.flight = flight
            reservation.passenger = savedPassenger
            val savedReservation = reservationRepository!!.save(reservation)
            val filePath = "/Users/rohithgupthakona/Downloads/SPL/reservations/" + savedReservation.id + ".pdf"
            pdfGenerator!!.generateItinerary(savedReservation, filePath)
            return filePath
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun sendItinerary() {}
}