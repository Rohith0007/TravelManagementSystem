package com.udemy.TravelManagement.service

import com.udemy.TravelManagement.Dto.ReservationRequest
import com.udemy.TravelManagement.entity.*
import com.udemy.TravelManagement.repository.*
import com.udemy.TravelManagement.util.EmailUtil
import com.udemy.TravelManagement.util.PdfGenerator
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
    var trainRepository: TrainRepository? = null

    @Autowired
    var busRepository: BusRepository? = null

    @Autowired
    var pdfGenerator: PdfGenerator? = null

    @Autowired
    var emailUtil: EmailUtil? = null
    fun bookFlight(reservationRequest: ReservationRequest): String? {
        try {
            var flight = Flight()
            var train = Train()
            var bus = Bus()
            if (reservationRequest.flightId != null) {
                flight = flightRepository!!.findById(reservationRequest.getFlightId1()).get();
            } else if (reservationRequest.trainId != null) {
                train = trainRepository!!.findById(reservationRequest.getTrainId1()).get();
            } else {
                bus = busRepository!!.findById(reservationRequest.getBusId1()).get();
            }
            val passenger = Passenger()
            passenger.firstName = reservationRequest.getpFirstName()
            passenger.lastName = reservationRequest.getpLastName()
            passenger.email = reservationRequest.getpEmail()
            passenger.phone = reservationRequest.getpPhone()
            val savedPassenger = passengerRepository!!.save(passenger)
            val reservation = Reservation()
            reservation.checkedIn = false
            if (flight.flightNumber != null) {
                reservation.flight = flight
            } else if(train.trainNumber != null) {
                reservation.train = train
            } else {
                reservation.bus = bus
            }

            reservation.passenger = savedPassenger
            val savedReservation = reservationRepository!!.save(reservation)
            val filePath = "/Users/rohithgupthakona/Downloads/SPL/reservations/" + savedReservation.id + ".pdf"
            if (flight.flightNumber != null) {
                pdfGenerator!!.generateItinerary_F(savedReservation, filePath)
            } else if(train.trainNumber != null) {
                pdfGenerator!!.generateItinerary_T(savedReservation, filePath)
            } else {
                pdfGenerator!!.generateItinerary_B(savedReservation, filePath)
            }
            return filePath
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun sendItinerary() {}

}