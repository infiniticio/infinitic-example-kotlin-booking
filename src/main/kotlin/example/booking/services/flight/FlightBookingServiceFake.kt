package example.booking.services.flight

import example.booking.services.ExponentialBackoffRetry
import example.booking.services.log
import io.infinitic.annotations.Retry
import kotlin.random.Random

@Suppress("unused")
@Retry(with = ExponentialBackoffRetry::class)
class FlightBookingServiceFake : FlightBookingService {

    override fun book(cart: FlightBookingCart): FlightBookingResult {
        log("start flight booking ...")

        // fake emulation of success/failure
        val r = Random.nextLong(0, 5000)

        return when {
            r >= 4000 -> {
                log("flight booking failed")
                FlightBookingResult.FAILURE
            }
            r >= 3000 -> {
                log("flight booking threw exception!")
                throw RuntimeException("failing request")
            }
            else -> {
                log("flight booking succeeded")
                FlightBookingResult.SUCCESS
            }
        }
    }

    override fun cancel(cart: FlightBookingCart) {
        log("flight booking canceled!")
    }
}
