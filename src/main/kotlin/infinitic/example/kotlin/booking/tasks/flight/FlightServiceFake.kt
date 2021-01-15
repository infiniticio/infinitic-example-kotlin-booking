package infinitic.example.kotlin.booking.tasks.flight

import kotlin.random.Random

class FlightServiceFake : FlightService {
    override fun book(cart: FlightCart): FlightResult {
        // fake emulation of success/failure
        val r = Random.nextInt(0, 100)

        return when {
            r >= 80 -> {
                println("${this::class.simpleName}: failed to book cart $cart")
                FlightResult.FAILURE
            }
            else -> {
                println("${this::class.simpleName}: cart $cart booked successfully")
                FlightResult.SUCCESS
            }
        }
    }

    override fun cancel(cart: FlightCart) {
        println("${this::class.simpleName}: canceling cart $cart")
    }
}
