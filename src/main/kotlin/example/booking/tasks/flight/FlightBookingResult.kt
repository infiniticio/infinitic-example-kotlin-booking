package example.booking.tasks.flight

import kotlinx.serialization.Serializable

@Serializable
enum class FlightBookingResult {
    SUCCESS,
    FAILURE
}
