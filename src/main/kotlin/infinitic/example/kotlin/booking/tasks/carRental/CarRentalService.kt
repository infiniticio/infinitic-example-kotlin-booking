package infinitic.example.kotlin.booking.tasks.carRental

interface CarRentalService {
    fun book(cart: CarRentalCart): CarRentalResult

    fun cancel(cart: CarRentalCart)
}
