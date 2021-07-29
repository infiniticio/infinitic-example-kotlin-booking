package example.booking.tasks.flight

import io.infinitic.annotations.Name

interface FlightBookingService {
    fun book(cart: FlightBookingCart): FlightBookingResult

    fun cancel(cart: FlightBookingCart)
}
