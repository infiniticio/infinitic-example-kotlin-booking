package example.booking.tasks.flight

import kotlin.random.Random

class FlightBookingServiceFake : FlightBookingService {
    override fun book(cart: FlightBookingCart): FlightBookingResult {
        // fake emulation of success/failure
        println("${this::class.simpleName} (${cart.cartId}): booking...")

        val r = Random.nextLong(0, 5000)
//        Thread.sleep(r)

        return when {
            r >= 4000 -> {
                println("${this::class.simpleName} (${cart.cartId}): failed")
                FlightBookingResult.FAILURE
            }
            // uncomment lines below to test task retries
//            r >= 3000 -> {
//                println("${this::class.simpleName} (${cart.cartId}): exception! (retry in ${getRetryDelay()}s)")
//                throw RuntimeException("failing request")
//            }
            else -> {
                println("${this::class.simpleName} (${cart.cartId}): succeeded")
                FlightBookingResult.SUCCESS
            }
        }
    }

    override fun cancel(cart: FlightBookingCart) {
        println("${this::class.simpleName} (${cart.cartId}): canceled")
    }

    fun getRetryDelay() = 5F
}
