package infinitic.example.kotlin.booking.workflows

import kotlinx.serialization.Serializable

@Serializable
enum class BookingResult {
    SUCCESS,
    FAILURE
}
