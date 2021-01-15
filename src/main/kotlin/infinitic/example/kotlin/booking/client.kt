package infinitic.example.kotlin.booking

import infinitic.example.kotlin.booking.tasks.carRental.CarRentalCart
import infinitic.example.kotlin.booking.tasks.flight.FlightCart
import infinitic.example.kotlin.booking.tasks.hotel.HotelCart
import infinitic.example.kotlin.booking.workflows.BookingService
import io.infinitic.pulsar.InfiniticClient
import kotlinx.coroutines.runBlocking
import java.util.UUID

fun main() = runBlocking {
    // instantiate Infinitic client based on infinitic.yml config file
    val client = InfiniticClient.fromFile("infinitic.yml")

    // faking some carts
    val carRentalCart = CarRentalCart(getId())
    val flightCart = FlightCart(getId())
    val hotelCart = HotelCart(getId())

    // starting a workflow
    client.startWorkflow<BookingService> { book(carRentalCart, flightCart, hotelCart) }

    // closing underlying PulsarClient
    client.close()
}

fun getId() = UUID.randomUUID().toString()
