package example.booking.tasks.hotel

import io.infinitic.tasks.Task
import java.time.Duration
import kotlin.math.pow
import kotlin.random.Random

class HotelBookingServiceFake : Task(), HotelBookingService {

    override fun book(cart: HotelBookingCart): HotelBookingResult {
        printl("start hotel booking ...")

        // fake emulation of success/failure
        val r = Random.nextLong(0, 5000)

        return when {
            r >= 4000 -> {
                printl("hotel booking failed")
                HotelBookingResult.FAILURE
            }
            r >= 3000 -> {
                printl("hotel booking threw exception!")
                throw RuntimeException("failing request")
            }
            else -> {
                printl("hotel booking succeeded")
                HotelBookingResult.SUCCESS
            }
        }
    }

    override fun cancel(cart: HotelBookingCart) {
        printl("hotel booking canceled!")
    }

    // Exponential backoff retry strategy up to 6 attempts
    override fun getDurationBeforeRetry(e: Exception): Duration? {
        val n = context.retryIndex

        return when {
            n < 12 -> Duration.ofSeconds((5 * Math.random() * 2.0.pow(n)).toLong())
            else -> null
        }
    }

    private fun printl(msg: String) {
        println(context.workflowId + " - " + this.javaClass.simpleName + " - " + msg)
    }
}
