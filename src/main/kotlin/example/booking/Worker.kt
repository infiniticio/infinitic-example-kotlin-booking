package example.booking

import io.infinitic.workers.InfiniticWorker

fun main(args: Array<String>) {
    val file = when (args.size) {
        0 -> "/configs/all.yml"
        else -> args[0]
    }

    InfiniticWorker.fromConfigResource(file, "/configs/infinitic.yml").use { worker ->
        worker.start()
    }
}
