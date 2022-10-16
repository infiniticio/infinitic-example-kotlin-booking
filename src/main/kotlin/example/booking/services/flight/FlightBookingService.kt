package example.booking.services.flight

import io.infinitic.annotations.Name

@Name("FlightBooking")
interface FlightBookingService {
    fun book(cart: FlightBookingCart): FlightBookingResult

    fun cancel(cart: FlightBookingCart)
}
