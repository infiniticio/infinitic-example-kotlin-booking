package example.booking.tasks.carRental

import io.infinitic.annotations.Name

interface CarRentalService {
    fun book(cart: CarRentalCart): CarRentalResult

    fun cancel(cart: CarRentalCart)
}
