package example.booking

import example.booking.tasks.carRental.CarRentalCart
import example.booking.tasks.flight.FlightBookingCart
import example.booking.tasks.hotel.HotelBookingCart
import example.booking.workflows.BookingResult
import example.booking.workflows.BookingWorkflow
import io.infinitic.pulsar.InfiniticClient
import java.util.UUID

fun main() {
    // instantiate Infinitic client based on infinitic.yml config file
    val client = InfiniticClient.fromConfigFile("configs/infinitic.yml")

    // faking some carts
    val carRentalCart = CarRentalCart(getId())
    val flightCart = FlightBookingCart(getId())
    val hotelCart = HotelBookingCart(getId())

    // create a stub for BookingWorkflow
    val bookingWorkflow = client.workflow<BookingWorkflow>()

    // dispatch a workflow
    client.async(bookingWorkflow) { book(carRentalCart, flightCart, hotelCart) }

    // dispatch a workflow and get result
    val result: BookingResult = bookingWorkflow.book(carRentalCart, flightCart, hotelCart)

    println(result)

    // closing underlying PulsarClient
    client.close()
}

fun getId() = UUID.randomUUID().toString()
