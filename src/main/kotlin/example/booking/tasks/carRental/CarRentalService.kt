package example.booking.tasks.carRental

interface CarRentalService {
    fun book(cart: CarRentalCart): CarRentalResult

    fun cancel(cart: CarRentalCart)
}
