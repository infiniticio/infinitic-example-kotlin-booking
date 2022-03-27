package example.booking

import io.infinitic.dashboard.DashboardServer

fun main(args: Array<String>) {
    // get name of config file
    val file = args.getOrNull(0) ?: "configs/infinitic.yml"
    // start server
    DashboardServer.fromConfigFile(file).start()
}
