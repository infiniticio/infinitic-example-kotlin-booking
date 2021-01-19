package infinitic.example.kotlin.booking.workflows

import infinitic.example.kotlin.booking.tasks.carRental.CarRentalCart
import infinitic.example.kotlin.booking.tasks.flight.FlightBookingCart
import infinitic.example.kotlin.booking.tasks.hotel.HotelBookingCart
import io.infinitic.workflows.Workflow

interface BookingWorkflow : Workflow {
    fun book(
        carRentalCart: CarRentalCart,
        flightCart: FlightBookingCart,
        hotelCart: HotelBookingCart
    ): BookingResult
}
