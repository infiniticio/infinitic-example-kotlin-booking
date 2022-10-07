
plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    // Apply the application plugin
    application
}

repositories {
    mavenCentral()
    // mandatory for the dashboard
    maven("https://jitpack.io")
    mavenLocal()
}

dependencies {
    // add a logger
    implementation("org.slf4j:slf4j-simple:2.0.3")
    // infinitic client
    implementation("io.infinitic:infinitic-client:0.10.0")
    // infinitic worker
    implementation("io.infinitic:infinitic-worker:0.10.0")
    // infinitic dashboard
    implementation("io.infinitic:infinitic-dashboard:0.10.0")
}

application {
    mainClassName = "example.booking.MainKt"
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
