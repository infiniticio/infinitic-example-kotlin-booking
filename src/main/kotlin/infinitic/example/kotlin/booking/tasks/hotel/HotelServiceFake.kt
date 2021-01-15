package infinitic.example.kotlin.booking.tasks.hotel

import kotlin.random.Random

class HotelServiceFake : HotelService {
    override fun book(cart: HotelCart): HotelResult {
        // fake emulation of success/failure
        val r = Random.nextInt(0, 100)

        return when {
            r >= 80 -> {
                println("${this::class.simpleName}: failed to book cart $cart")
                HotelResult.FAILURE
            }
            else -> {
                println("${this::class.simpleName}: cart $cart booked successfully")
                HotelResult.SUCCESS
            }
        }
    }

    override fun cancel(cart: HotelCart) {
        println("${this::class.simpleName}: canceling cart $cart")
    }
}
