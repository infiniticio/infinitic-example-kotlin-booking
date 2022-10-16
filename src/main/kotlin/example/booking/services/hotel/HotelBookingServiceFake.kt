package example.booking.services.hotel

import example.booking.services.ExponentialBackoffRetry
import example.booking.services.log
import io.infinitic.annotations.Retry
import kotlin.random.Random

@Suppress("unused")
@Retry(with = ExponentialBackoffRetry::class)
class HotelBookingServiceFake : HotelBookingService {

    override fun book(cart: HotelBookingCart): HotelBookingResult {
        log("start hotel booking ...")

        // fake emulation of success/failure
        val r = Random.nextLong(0, 5000)

        return when {
            r >= 4000 -> {
                log("hotel booking failed")
                HotelBookingResult.FAILURE
            }
            r >= 3000 -> {
                log("hotel booking threw exception!")
                throw RuntimeException("failing request")
            }
            else -> {
                log("hotel booking succeeded")
                HotelBookingResult.SUCCESS
            }
        }
    }

    override fun cancel(cart: HotelBookingCart) {
        log("hotel booking canceled!")
    }
}
