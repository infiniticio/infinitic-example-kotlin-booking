package infinitic.example.kotlin.booking.tasks.carRental

interface CarRentalService {
    fun book(carRentalCart: CarRentalCart): CarRentalResult

    fun cancel(carRentalCart: CarRentalCart)
}
