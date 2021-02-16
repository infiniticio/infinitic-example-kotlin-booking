package example.booking

import io.infinitic.pulsar.InfiniticAdmin

fun main() {
    val admin = InfiniticAdmin.fromConfigFile("configs/infinitic.yml")

    admin.setupPulsar()
    admin.close()
}
