package infinitic.example.kotlin.booking.workflows

import infinitic.example.kotlin.booking.tasks.carRental.*
import infinitic.example.kotlin.booking.tasks.flight.*
import infinitic.example.kotlin.booking.tasks.hotel.*
import io.infinitic.workflows.*

class BookingWorkflowImpl : AbstractWorkflow(), BookingWorkflow {
    private val carRentalService = task<CarRentalService>()
    private val flightService = task<FlightBookingService>()
    private val hotelService = task<HotelBookingService>()

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
        val carRentalResult = carRental.result() // wait and assign result for CarRentalService::book
        val flightResult = flight.result() // wait and assign result for FlightService::book method
        val hotelResult = hotel.result() // wait and assign result for HotelService::book method

        // if at least one of the booking is failed than cancel all successful bookings
        if (carRentalResult == CarRentalResult.FAILURE ||
            flightResult == FlightBookingResult.FAILURE ||
            hotelResult == HotelBookingResult.FAILURE
        ) {
            if (carRentalResult == CarRentalResult.SUCCESS) { carRentalService.cancel(carRentalCart) }
            if (flightResult == FlightBookingResult.SUCCESS) { flightService.cancel(flightCart) }
            if (hotelResult == HotelBookingResult.SUCCESS) { hotelService.cancel(hotelCart) }

            inline { println("${this::class.simpleName}: book canceled") }
            return BookingResult.FAILURE
        }

        // everything went fine
        inline { println("${this::class.simpleName}: book succeeded") }
        return BookingResult.SUCCESS
    }
}
