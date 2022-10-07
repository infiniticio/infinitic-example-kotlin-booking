package example.booking.tasks.flight

import io.infinitic.tasks.Task
import java.time.Duration
import kotlin.math.pow
import kotlin.random.Random

class FlightBookingServiceFake : Task(), FlightBookingService {

    override fun book(cart: FlightBookingCart): FlightBookingResult {
        printl("start flight booking ...")

        // fake emulation of success/failure
        val r = Random.nextLong(0, 5000)

        return when {
            r >= 4000 -> {
                printl("flight booking failed")
                FlightBookingResult.FAILURE
            }
            r >= 3000 -> {
                printl("flight booking threw exception!")
                throw RuntimeException("failing request")
            }
            else -> {
                printl("flight booking succeeded")
                FlightBookingResult.SUCCESS
            }
        }
    }

    override fun cancel(cart: FlightBookingCart) {
        printl("flight booking canceled!")
    }

    // Exponential backoff retry strategy up to 12 attempts
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
