package example.booking

import example.booking.tasks.carRental.CarRentalCart
import example.booking.tasks.flight.FlightBookingCart
import example.booking.tasks.hotel.HotelBookingCart
import example.booking.workflows.BookingWorkflow
import io.infinitic.client.newWorkflow
import io.infinitic.pulsar.PulsarInfiniticClient

fun main() {
    // instantiate Infinitic client based on infinitic.yml config file
    val client = PulsarInfiniticClient.fromConfigFile("configs/infinitic.yml")

    // faking some carts
    val carRentalCart = CarRentalCart()
    val flightCart = FlightBookingCart()
    val hotelCart = HotelBookingCart()

    // create a stub for BookingWorkflow
    val bookingWorkflow = client.newWorkflow<BookingWorkflow>()

    // dispatch a workflow
    client.async(bookingWorkflow) { book(carRentalCart, flightCart, hotelCart) }

    println("workflow ${BookingWorkflow::class} dispatched!")
}
