package infinitic.example.kotlin.booking.tasks.flight

import kotlinx.serialization.Serializable

@Serializable
enum class FlightResult {
    SUCCESS,
    FAILURE
}
