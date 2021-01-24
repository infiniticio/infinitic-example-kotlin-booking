package example.booking.tasks.carRental

import kotlinx.serialization.Serializable

@Serializable
enum class CarRentalResult {
    SUCCESS,
    FAILURE
}
