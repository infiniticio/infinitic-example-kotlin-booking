package infinitic.example.kotlin.booking.tasks.hotel

interface HotelService {
    fun book(hotelCart: HotelCart): HotelResult

    fun cancel(hotelCart: HotelCart)
}
