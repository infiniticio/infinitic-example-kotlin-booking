package infinitic.example.kotlin.booking

import io.infinitic.pulsar.InfiniticWorker

fun main(args: Array<String>) {
    InfiniticWorker.fromFile(*args, "configs/infinitic.yml").start()
}
