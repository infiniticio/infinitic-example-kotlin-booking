package example.booking.tasks.flight

import kotlinx.serialization.Serializable

@Serializable
data class FlightBookingCart(
    val cartId: String
)
