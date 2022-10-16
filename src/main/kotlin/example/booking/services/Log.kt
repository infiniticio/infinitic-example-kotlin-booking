package example.booking.services

import io.infinitic.tasks.Task
import java.text.SimpleDateFormat
import java.util.Date

internal fun log(msg: String) {
    println(
        SimpleDateFormat("hh:mm:ss.SSS").format(Date()) + " - " + Task.workflowId + " - " + Task.serviceName + " - " + msg
    )
}
