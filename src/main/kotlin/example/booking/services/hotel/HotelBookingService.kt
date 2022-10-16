package example.booking.services.hotel

import io.infinitic.annotations.Name

@Name("HotelBooking")
interface HotelBookingService {
    fun book(cart: HotelBookingCart): HotelBookingResult

    fun cancel(cart: HotelBookingCart)
}
