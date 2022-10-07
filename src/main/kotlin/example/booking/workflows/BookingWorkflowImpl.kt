package example.booking.workflows

import example.booking.tasks.carRental.CarRentalCart
import example.booking.tasks.carRental.CarRentalResult
import example.booking.tasks.carRental.CarRentalService
import example.booking.tasks.flight.FlightBookingCart
import example.booking.tasks.flight.FlightBookingResult
import example.booking.tasks.flight.FlightBookingService
import example.booking.tasks.hotel.HotelBookingCart
import example.booking.tasks.hotel.HotelBookingResult
import example.booking.tasks.hotel.HotelBookingService
import io.infinitic.workflows.Workflow

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
        printl("booking started")

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
            printl("booking failed")

            return BookingResult.FAILURE
        }

        // printing is done through an inline task
        printl("booking succeeded")

        return BookingResult.SUCCESS
    }

    private fun printl(msg: String) {
        inline { println(context.id + " - " + this.javaClass.simpleName + " - " + msg) }
    }
}
