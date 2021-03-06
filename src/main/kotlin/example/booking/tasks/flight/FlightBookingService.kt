package example.booking.tasks.flight

interface FlightBookingService {
    fun book(cart: FlightBookingCart): FlightBookingResult

    fun cancel(cart: FlightBookingCart)
}
