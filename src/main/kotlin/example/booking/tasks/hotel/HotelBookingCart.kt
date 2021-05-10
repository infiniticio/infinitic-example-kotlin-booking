package example.booking.tasks.hotel

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class HotelBookingCart(val cartId: UUID = UUID.randomUUID())
