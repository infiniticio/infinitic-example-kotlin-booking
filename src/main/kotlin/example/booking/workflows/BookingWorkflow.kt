package example.booking.workflows

import example.booking.tasks.carRental.CarRentalCart
import example.booking.tasks.flight.FlightBookingCart
import example.booking.tasks.hotel.HotelBookingCart
import io.infinitic.workflows.Workflow

interface BookingWorkflow : Workflow {
    fun book(
        carRentalCart: CarRentalCart,
        flightCart: FlightBookingCart,
        hotelCart: HotelBookingCart
    ): BookingResult
}
