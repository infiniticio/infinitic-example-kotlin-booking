package example.booking.tasks.carRental

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class CarRentalCart(val cartId: UUID = UUID.randomUUID())
