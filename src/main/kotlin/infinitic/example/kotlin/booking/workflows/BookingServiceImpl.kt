package infinitic.example.kotlin.booking.workflows

import infinitic.example.kotlin.booking.tasks.carRental.*
import infinitic.example.kotlin.booking.tasks.flight.*
import infinitic.example.kotlin.booking.tasks.hotel.*
import io.infinitic.workflows.*

class BookingServiceImpl : WorkflowBase(), BookingService {
    private val carRentalService = task<CarRentalService>()
    private val flightService = task<FlightService>()
    private val hotelService = task<HotelService>()

    override fun book(
        carRentalCart: CarRentalCart,
        flightCart: FlightCart,
        hotelCart: HotelCart
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
            flightResult == FlightResult.FAILURE ||
            hotelResult == HotelResult.FAILURE
        ) {
            if (carRentalResult == CarRentalResult.SUCCESS) { carRentalService.cancel(carRentalCart) }
            if (flightResult == FlightResult.SUCCESS) { flightService.cancel(flightCart) }
            if (hotelResult == HotelResult.SUCCESS) { hotelService.cancel(hotelCart) }

            inline { println("${this::class.simpleName}: booking failed") }
            return BookingResult.FAILURE
        }

        // everything went fine
        inline { println("${this::class.simpleName}: booking succeeded") }
        return BookingResult.SUCCESS
    }
}
