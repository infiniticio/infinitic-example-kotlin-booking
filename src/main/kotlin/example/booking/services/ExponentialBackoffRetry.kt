package example.booking.services

import io.infinitic.tasks.WithRetry
import kotlin.math.pow

class ExponentialBackoffRetry : WithRetry {
    // Exponential backoff retry strategy up to 6 attempts
    override fun getSecondsBeforeRetry(retry: Int, exception: Exception): Double? {
        val delay = when {
            retry < 6 -> {
                5 * Math.random() * 2.0.pow(retry)
            }
            else -> {
                null
            }
        }

        log(
            when (delay) {
                null -> "failed after all retries"
                else -> "retry after ${String.format("%.2f", delay)} seconds"
            }
        )

        return delay
    }
}
