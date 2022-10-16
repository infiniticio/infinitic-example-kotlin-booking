package example.booking.workflows

import example.booking.services.carRental.CarRentalCart
import example.booking.services.flight.FlightBookingCart
import example.booking.services.hotel.HotelBookingCart
import io.infinitic.annotations.Name

@Name("BookingWorkflow")
interface BookingWorkflow {
    fun book(
        carRentalCart: CarRentalCart,
        flightCart: FlightBookingCart,
        hotelCart: HotelBookingCart
    ): BookingResult
}
