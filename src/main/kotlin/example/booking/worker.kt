package example.booking

import io.infinitic.pulsar.InfiniticWorker

fun main(args: Array<String>) {
    val file = when (args.size) {
        0 -> "configs/all.yml"
        else -> args[0]
    }
    InfiniticWorker.fromConfigFile(file, "configs/infinitic.yml").start()
}
