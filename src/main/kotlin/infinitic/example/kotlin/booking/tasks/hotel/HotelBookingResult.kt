package infinitic.example.kotlin.booking.tasks.hotel

import kotlinx.serialization.Serializable

@Serializable
enum class HotelBookingResult {
    SUCCESS,
    FAILURE
}
