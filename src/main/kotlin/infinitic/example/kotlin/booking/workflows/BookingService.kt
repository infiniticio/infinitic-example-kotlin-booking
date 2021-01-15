package infinitic.example.kotlin.booking.workflows

import infinitic.example.kotlin.booking.tasks.carRental.CarRentalCart
import infinitic.example.kotlin.booking.tasks.flight.FlightCart
import infinitic.example.kotlin.booking.tasks.hotel.HotelCart
import io.infinitic.workflows.Workflow

interface BookingService : Workflow {
    fun book(
        carRentalCart: CarRentalCart,
        flightCart: FlightCart,
        hotelCart: HotelCart
    ): BookingResult
}
