package example.booking

import io.infinitic.pulsar.PulsarInfiniticAdmin

fun main() {
    val admin = PulsarInfiniticAdmin.fromConfigFile("configs/infinitic.yml")

    admin.setupPulsar()

    admin.printTopicStats()

    admin.close()
}
