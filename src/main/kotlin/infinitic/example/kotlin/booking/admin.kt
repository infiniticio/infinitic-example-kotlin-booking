package infinitic.example.kotlin.booking

import io.infinitic.pulsar.InfiniticAdmin

fun main() {
    val admin = InfiniticAdmin.fromFile("configs/infinitic.yml")

    admin.init()
    admin.close()
}
