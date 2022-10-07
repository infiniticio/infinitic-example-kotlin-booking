# Example Kotlin App For Infinitic

Implementing a booking process combining a car rental, a flight, and a hotel reservation.
We require that all 3 bookings have to be successful together:
if any of them fails, we should cancel the other bookings that were successful.

This repository is described in the Infinitic documentation: https://docs.infinitic.io/overview/examples

## Before running
- If needed, update credentials in configs/infinitic.yml

## Running services individually
- run bookingWorkflow service: `./gradlew run --args=/configs/bookingWorkflow.yml`
- run carRental service: `./gradlew run --args=/configs/carRental.yml`
- run flightBooking service: `./gradlew run --args=/configs/flightBooking.yml`
- run hotelBooking service: `./gradlew run --args=/configs/hotelBooking.yml`

## Running services all together
If you do not want to run each service separately, just `./gradlew run`

## Launch
Launch workflows! `./gradlew dispatch`


Note: workflows will never complete if one of services is not running. Also,
- you can run as many instances of each service that you want;
- you can run each service from a different network as soon as they access the same Pulsar namespace.