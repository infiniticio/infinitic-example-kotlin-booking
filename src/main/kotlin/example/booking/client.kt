package example.booking

import example.booking.tasks.carRental.CarRentalCart
import example.booking.tasks.flight.FlightBookingCart
import example.booking.tasks.hotel.HotelBookingCart
import example.booking.workflows.BookingWorkflow
import io.infinitic.factory.InfiniticClientFactory

fun main() {
    InfiniticClientFactory.fromConfigFile("configs/infinitic.yml", "configs/all.yml").use {
        val client = it

        // create a stub for BookingWorkflow
        val bookingWorkflow = client.workflowStub(BookingWorkflow::class.java)

        repeat(100) {
            // faking some carts
            val carRentalCart = CarRentalCart()
            val flightCart = FlightBookingCart()
            val hotelCart = HotelBookingCart()
            // dispatch a workflow
            val deferred = client.dispatch(bookingWorkflow::book)(carRentalCart, flightCart, hotelCart)

            println("workflows ${BookingWorkflow::class} ${deferred.id} dispatched!")
        }
    }
}
