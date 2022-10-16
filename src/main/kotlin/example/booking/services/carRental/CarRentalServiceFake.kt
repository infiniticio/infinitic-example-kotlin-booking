package example.booking.services.carRental

import example.booking.services.ExponentialBackoffRetry
import example.booking.services.log
import io.infinitic.annotations.Retry
import kotlin.random.Random

@Suppress("unused")
@Retry(with = ExponentialBackoffRetry::class)
class CarRentalServiceFake : CarRentalService {

    override fun book(cart: CarRentalCart): CarRentalResult {
        log("start car rental ...")

        // fake emulation of success/failure
        val r = Random.nextLong(0, 5000)

        return when {
            r >= 4000 -> {
                log("car rental failed")
                CarRentalResult.FAILURE
            }
            r >= 3000 -> {
                log("car rental threw exception!")
                throw RuntimeException("failing request")
            }
            else -> {
                log("car rental succeeded")
                CarRentalResult.SUCCESS
            }
        }
    }

    override fun cancel(cart: CarRentalCart) {
        log("car rental canceled!")
    }
}
