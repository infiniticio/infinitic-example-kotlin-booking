package example.booking // ktlint-disable filename

import example.booking.services.carRental.CarRentalCart
import example.booking.services.flight.FlightBookingCart
import example.booking.services.hotel.HotelBookingCart
import example.booking.workflows.BookingWorkflow
import io.infinitic.clients.InfiniticClient

fun main() {
    InfiniticClient.fromConfigResource("/configs/infinitic.yml", "/configs/all.yml").use { client ->
        // create a stub for BookingWorkflow
        val bookingWorkflow = client.newWorkflow(BookingWorkflow::class.java, tags = setOf("booking"))

        repeat(10) {
            // fake carts
            val carRentalCart = CarRentalCart()
            val flightCart = FlightBookingCart()
            val hotelCart = HotelBookingCart()
            // dispatch a workflow
            client.dispatchAsync(bookingWorkflow::book, carRentalCart, flightCart, hotelCart)
                .thenApply { deferred -> println("Workflows ${deferred.id} ($it) dispatched!") }
                .exceptionally { error -> System.err.println("Failed to dispatch ($it): $error") }
        }
    }
}
