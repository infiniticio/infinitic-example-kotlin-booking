package example.booking

import io.infinitic.factory.InfiniticWorkerFactory

fun main(args: Array<String>) {
    val file = when (args.size) {
        0 -> "configs/all.yml"
        else -> args[0]
    }

    InfiniticWorkerFactory.fromConfigFile(file, "configs/infinitic.yml").use {
        it.start()
    }
}
