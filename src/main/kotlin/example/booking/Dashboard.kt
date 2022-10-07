package example.booking // ktlint-disable filename

import io.infinitic.dashboard.InfiniticDashboard

fun main(args: Array<String>) {
    // get name of config file
    val file = args.getOrNull(0) ?: "/configs/infinitic.yml"
    // start server
    InfiniticDashboard.fromConfigResource(file).start()
}
