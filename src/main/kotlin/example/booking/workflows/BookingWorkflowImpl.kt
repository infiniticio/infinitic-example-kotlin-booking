package example.booking.workflows

import example.booking.tasks.carRental.*
import example.booking.tasks.flight.*
import example.booking.tasks.hotel.*
import io.infinitic.workflows.*

class BookingWorkflowImpl : Workflow(), BookingWorkflow {
    private val carRentalService = newTask<CarRentalService>()
    private val flightService = newTask<FlightBookingService>()
    private val hotelService = newTask<HotelBookingService>()

    override fun book(
        carRentalCart: CarRentalCart,
        flightCart: FlightBookingCart,
        hotelCart: HotelBookingCart
    ): BookingResult {
        // parallel bookings using car rental, flight and hotel services
        val carRental = async(carRentalService) { book(carRentalCart) }
        val flight = async(flightService) { book(flightCart) }
        val hotel = async(hotelService) { book(hotelCart) }

        // wait and assign results
        val carRentalResult = carRental.await() // wait and assign result for CarRentalService::book
        val flightResult = flight.await() // wait and assign result for FlightService::book method
        val hotelResult = hotel.await() // wait and assign result for HotelService::book method

        // if at least one of the booking is failed than cancel all successful bookings
        if (carRentalResult == CarRentalResult.FAILURE ||
            flightResult == FlightBookingResult.FAILURE ||
            hotelResult == HotelBookingResult.FAILURE
        ) {
            if (carRentalResult == CarRentalResult.SUCCESS) { carRentalService.cancel(carRentalCart) }
            if (flightResult == FlightBookingResult.SUCCESS) { flightService.cancel(flightCart) }
            if (hotelResult == HotelBookingResult.SUCCESS) { hotelService.cancel(hotelCart) }

            // booking canceled
            inline { println("${this::class.simpleName}: book canceled  ${context.id}") }

            return BookingResult.FAILURE
        }

        // everything went fine
        inline { println("${this::class.simpleName}: book succeeded ${context.id}") }

        return BookingResult.SUCCESS
    }
}
