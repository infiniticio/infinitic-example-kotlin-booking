package example.booking.services.carRental

import io.infinitic.annotations.Name

@Name("CarRental")
interface CarRentalService {
    fun book(cart: CarRentalCart): CarRentalResult

    fun cancel(cart: CarRentalCart)
}
