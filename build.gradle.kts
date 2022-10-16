
plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    // Apply the application plugin
    application
}

repositories {
    mavenCentral()
    mavenLocal()
    // mandatory for the dashboard
    maven("https://jitpack.io")
}

dependencies {
    // add a logger
    implementation("org.slf4j:slf4j-simple:2.0.3")
    // Infinitic version
    version = "0.11.0"
    // infinitic client
    implementation("io.infinitic:infinitic-client:$version")
    // infinitic worker
    implementation("io.infinitic:infinitic-worker:$version")
    // infinitic dashboard
    implementation("io.infinitic:infinitic-dashboard:$version")
}

application {
    mainClassName = "example.booking.WorkerKt"
}

task("dispatch", JavaExec::class) {
    group = "infinitic"
    main = "example.booking.DispatchKt"
    classpath = sourceSets["main"].runtimeClasspath
}

task("dashboard", JavaExec::class) {
    group = "infinitic"
    main = "example.booking.DashboardKt"
    classpath = sourceSets["main"].runtimeClasspath
}
