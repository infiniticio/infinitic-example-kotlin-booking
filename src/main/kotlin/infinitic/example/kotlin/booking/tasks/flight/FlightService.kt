package infinitic.example.kotlin.booking.tasks.flight

interface FlightService {
    fun book(flightCart: FlightCart): FlightResult

    fun cancel(flightCart: FlightCart)
}
