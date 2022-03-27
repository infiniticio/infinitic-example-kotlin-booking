package example.booking.tasks.flight

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class FlightBookingCart(val cartId: UUID = UUID.randomUUID())
