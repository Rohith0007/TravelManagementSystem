package com.udemy.TravelManagement.util

import com.itextpdf.text.Document
import com.itextpdf.text.Phrase
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import com.udemy.TravelManagement.entity.Reservation
import org.springframework.stereotype.Component
import java.io.FileOutputStream

@Component
class PdfGenerator {
    fun generateItinerary(reservation: Reservation, filePath: String?) {
        val document = Document()
        try {
            PdfWriter.getInstance(document, FileOutputStream(filePath))
            document.open()
            document.add(generateTable(reservation))
            document.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun generateTable(reservation: Reservation): PdfPTable {
        val table = PdfPTable(2)
        var cell: PdfPCell
        cell = PdfPCell(Phrase("Flight Itinerary"))
        cell.colspan = 2
        table.addCell(cell)
        cell = PdfPCell(Phrase("Flight Details"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell("Departure city")
        table.addCell(reservation.flight!!.departureCity)
        table.addCell("Arrival city")
        table.addCell(reservation.flight!!.arrivalCity)
        table.addCell("Flight number ")
        table.addCell(reservation.flight!!.flightNumber)
        table.addCell("Departure Time")
        table.addCell(reservation.flight!!.estimatedDepartureTime.toString())
        cell = PdfPCell(Phrase("Passenger Details"))
        cell.colspan = 2
        table.addCell(cell)
        table.addCell("First Name")
        table.addCell(reservation.passenger!!.firstName)
        table.addCell("Last Name")
        table.addCell(reservation.passenger!!.lastName)
        table.addCell("Email")
        table.addCell(reservation.passenger!!.email)
        table.addCell("Phone")
        table.addCell(reservation.passenger!!.phone)
        return table
    }
}