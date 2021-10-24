package example.booking.workflows

import example.booking.tasks.carRental.*
import example.booking.tasks.flight.*
import example.booking.tasks.hotel.*
import io.infinitic.workflows.*

class BookingWorkflowImpl : Workflow(), BookingWorkflow {
    // create stub for CarRentalService
    private val carRentalService = newTask(CarRentalService::class.java)
    // create stub for FlightBookingService
    private val flightBookingService = newTask(FlightBookingService::class.java)
    // create stub for HotelBookingService
    private val hotelBookingService = newTask(HotelBookingService::class.java)

    override fun book(
        carRentalCart: CarRentalCart,
        flightCart: FlightBookingCart,
        hotelCart: HotelBookingCart
    ): BookingResult {
        // dispatch parallel bookings using car, flight and hotel services
        val deferredCarRental = dispatch(carRentalService::book, carRentalCart)
        val deferredFlightBooking = dispatch(flightBookingService::book, flightCart)
        val deferredHotelBooking = dispatch(hotelBookingService::book, hotelCart)

        // wait and get result of deferred CarRentalService::book
        val carRentalResult = deferredCarRental.await()
        // wait and get result of deferred FlightService::book
        val flightResult = deferredFlightBooking.await()
        // wait and get result of deferred HotelService::book
        val hotelResult = deferredHotelBooking.await()

        // if at least one of the booking is failed than cancel all successful bookings
        if (carRentalResult == CarRentalResult.FAILURE ||
            flightResult == FlightBookingResult.FAILURE ||
            hotelResult == HotelBookingResult.FAILURE
        ) {
            if (carRentalResult == CarRentalResult.SUCCESS) { carRentalService.cancel(carRentalCart) }
            if (flightResult == FlightBookingResult.SUCCESS) { flightBookingService.cancel(flightCart) }
            if (hotelResult == HotelBookingResult.SUCCESS) { hotelBookingService.cancel(hotelCart) }

            // printing is done through an inline task
            inline { println("${this::class.simpleName}: book canceled  ${context.id}") }

            return BookingResult.FAILURE
        }

        // printing is done through an inline task
        inline { println("${this::class.simpleName}: book succeeded ${context.id}") }

        return BookingResult.SUCCESS
    }
}
