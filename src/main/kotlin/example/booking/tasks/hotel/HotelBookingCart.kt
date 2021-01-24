package example.booking.tasks.hotel

import kotlinx.serialization.Serializable

@Serializable
data class HotelBookingCart(
    val cartId: String
)
