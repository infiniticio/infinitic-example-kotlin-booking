package example.booking.tasks.hotel

interface HotelBookingService {
    fun book(cart: HotelBookingCart): HotelBookingResult

    fun cancel(cart: HotelBookingCart)
}
