package example.booking

import io.infinitic.pulsar.PulsarInfiniticWorker

fun main(args: Array<String>) {
    val file = when (args.size) {
        0 -> "configs/all.yml"
        else -> args[0]
    }
    PulsarInfiniticWorker. fromConfigFile(file, "configs/infinitic.yml").start()
}
