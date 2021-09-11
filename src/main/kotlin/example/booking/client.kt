package example.booking

import example.booking.tasks.carRental.CarRentalCart
import example.booking.tasks.flight.FlightBookingCart
import example.booking.tasks.hotel.HotelBookingCart
import example.booking.workflows.BookingWorkflow
import io.infinitic.client.newWorkflow
import io.infinitic.factory.InfiniticClientFactory

fun main() {
    InfiniticClientFactory.fromConfigFile("configs/infinitic.yml", "configs/all.yml").use {
        with(it) {
            repeat(1) {
                // faking some carts
                val carRentalCart = CarRentalCart()
                val flightCart = FlightBookingCart()
                val hotelCart = HotelBookingCart()
                // create a stub for BookingWorkflow
                val bookingWorkflow = newWorkflow<BookingWorkflow>()
                // dispatch a workflow
                val deferred = async(bookingWorkflow) { book(carRentalCart, flightCart, hotelCart) }

                println("workflows ${BookingWorkflow::class} ${deferred.id} dispatched!")
            }
        }
    }
}
