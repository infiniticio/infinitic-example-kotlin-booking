import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.30"

    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-simple:1.7.+")

    implementation("io.infinitic:infinitic-pulsar:0.5.0")
    implementation("io.infinitic:infinitic-client:0.5.0")
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
