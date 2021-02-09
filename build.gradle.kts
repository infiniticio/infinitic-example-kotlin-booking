import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.30"

    application
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.slf4j:slf4j-simple:1.7.+")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

    // https://github.com/apache/pulsar/issues/9045
    implementation("org.apache.avro:avro") { version { strictly("1.9.+") } }
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")

    implementation("io.infinitic:infinitic-pulsar:0.2.1-SNAPSHOT")
    implementation("io.infinitic:infinitic-client:0.2.1-SNAPSHOT")
}

application {
    // Define the main class for the application.
    mainClassName = "example.booking.WorkerKt"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

task("setupPulsar", JavaExec::class) {
    group = "infinitic"
    main = "example.booking.AdminKt"
    classpath = sourceSets["main"].runtimeClasspath
}

task("startWorkflow", JavaExec::class) {
    group = "infinitic"
    main = "example.booking.ClientKt"
    classpath = sourceSets["main"].runtimeClasspath
}
