package infinitic.example.kotlin.booking

import infinitic.example.kotlin.booking.tasks.carRental.CarRentalCart
import infinitic.example.kotlin.booking.tasks.flight.FlightBookingCart
import infinitic.example.kotlin.booking.tasks.hotel.HotelBookingCart
import infinitic.example.kotlin.booking.workflows.BookingWorkflow
import io.infinitic.pulsar.InfiniticClient
import kotlinx.coroutines.runBlocking
import java.util.UUID

fun main() = runBlocking {

    // instantiate Infinitic client based on infinitic.yml config file
    val client = InfiniticClient.fromFile("configs/infinitic.yml")

    repeat(1) {
        // faking some carts
        val carRentalCart = CarRentalCart(getId())
        val flightCart = FlightBookingCart(getId())
        val hotelCart = HotelBookingCart(getId())

        // starting a workflow
        client.startWorkflow<BookingWorkflow> { book(carRentalCart, flightCart, hotelCart) }
    }

    // closing underlying PulsarClient
    client.close()
}

fun getId() = UUID.randomUUID().toString()
