package example.booking.tasks.flight

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class FlightBookingCart(val cartId: UUID = UUID.randomUUID())
