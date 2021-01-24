package example.booking.workflows

import kotlinx.serialization.Serializable

@Serializable
enum class BookingResult {
    SUCCESS,
    FAILURE
}
