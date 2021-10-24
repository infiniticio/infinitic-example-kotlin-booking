package example.booking.tasks.carRental

import io.infinitic.tasks.Task
import java.time.Duration
import kotlin.math.pow
import kotlin.random.Random

class CarRentalServiceFake : Task(), CarRentalService {

     override fun book(cart: CarRentalCart): CarRentalResult {
        println("${this::class.simpleName}     (${cart.cartId}): booking...")

         // fake emulation of success/failure
        val r = Random.nextLong(0, 5000)

        return when {
            r >= 4000 -> {
                println("${this::class.simpleName}     (${cart.cartId}): failed")
                CarRentalResult.FAILURE
            }
            r >= 3000 -> {
                println("${this::class.simpleName}     (${cart.cartId}): exception!")
                throw RuntimeException("failing request")
            }
            else -> {
                println("${this::class.simpleName}     (${cart.cartId}): succeeded")
                CarRentalResult.SUCCESS
            }
        }
    }

    override fun cancel(cart: CarRentalCart) {
        println("${this::class.simpleName}     (${cart.cartId}): canceled")
    }

    // Exponential backoff retry strategy up to 12 attempts
    override fun getDurationBeforeRetry(e: Exception): Duration? {
        val n = context.retryIndex

        return when {
            n < 12 -> Duration.ofSeconds((5 * Math.random() * 2.0.pow(n)).toLong())
            else -> null
        }
    }
}
