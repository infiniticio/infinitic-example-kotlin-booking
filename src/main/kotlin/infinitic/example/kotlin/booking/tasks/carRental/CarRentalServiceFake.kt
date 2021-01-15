package infinitic.example.kotlin.booking.tasks.carRental

import kotlin.random.Random

class CarRentalServiceFake : CarRentalService {
    override fun book(cart: CarRentalCart): CarRentalResult {
        // fake emulation of success/failure
        val r = Random.nextInt(0, 100)

        return when {
            r >= 80 -> {
                println("${this::class.simpleName}: failed to book cart $cart")
                CarRentalResult.FAILURE
            }
            else -> {
                println("${this::class.simpleName}: cart $cart booked successfully")
                CarRentalResult.SUCCESS
            }
        }
    }

    override fun cancel(cart: CarRentalCart) {
        println("${this::class.simpleName}: canceling cart $cart")
    }
}
