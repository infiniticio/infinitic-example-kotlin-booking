package example.booking

import example.booking.tasks.carRental.CarRentalCart
import example.booking.tasks.flight.FlightBookingCart
import example.booking.tasks.hotel.HotelBookingCart
import example.booking.workflows.BookingWorkflow
import io.infinitic.pulsar.InfiniticClient
import kotlinx.coroutines.runBlocking
import java.util.UUID

fun main() = runBlocking {
    // instantiate Infinitic client based on infinitic.yml config file
    val client = InfiniticClient.fromFile("configs/infinitic.yml")

    // faking some carts
    val carRentalCart = CarRentalCart(getId())
    val flightCart = FlightBookingCart(getId())
    val hotelCart = HotelBookingCart(getId())

    // create a stub from BookingWorkflow interface
    val bookingWorkflow = client.workflow<BookingWorkflow>()
    // dispatch a workflow
    client.async(bookingWorkflow) { book(carRentalCart, flightCart, hotelCart) }

    // closing underlying PulsarClient
    client.close()
}

fun getId() = UUID.randomUUID().toString()
