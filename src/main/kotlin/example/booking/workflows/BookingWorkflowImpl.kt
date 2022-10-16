package example.booking.workflows

import example.booking.services.carRental.CarRentalCart
import example.booking.services.carRental.CarRentalResult
import example.booking.services.carRental.CarRentalService
import example.booking.services.flight.FlightBookingCart
import example.booking.services.flight.FlightBookingResult
import example.booking.services.flight.FlightBookingService
import example.booking.services.hotel.HotelBookingCart
import example.booking.services.hotel.HotelBookingResult
import example.booking.services.hotel.HotelBookingService
import io.infinitic.tasks.Task
import io.infinitic.workflows.Workflow
import java.text.SimpleDateFormat
import java.util.Date

@Suppress("unused")
class BookingWorkflowImpl : Workflow(), BookingWorkflow {
    // create stub for CarRentalService
    private val carRentalService = newService(CarRentalService::class.java)

    // create stub for FlightBookingService
    private val flightBookingService = newService(FlightBookingService::class.java)

    // create stub for HotelBookingService
    private val hotelBookingService = newService(HotelBookingService::class.java)

    override fun book(
        carRentalCart: CarRentalCart,
        flightCart: FlightBookingCart,
        hotelCart: HotelBookingCart
    ): BookingResult {
        log("booking started")

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
            log("booking failed")

            return BookingResult.FAILURE
        }

        // printing is done through an inline task
        log("booking succeeded")

        return BookingResult.SUCCESS
    }

    private fun log(msg: String) = inline {
        println(SimpleDateFormat("hh:mm:ss.SSS").format(Date()) + " - " + Task.workflowId + " - " + Task.workflowName + " - " + msg)
    }
}
