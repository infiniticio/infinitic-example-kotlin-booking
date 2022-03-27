import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.6.10"

    // Apply the application plugin
    application
}

repositories {
    mavenCentral()
    // necessary for the dashboard
    maven("https://jitpack.io")
}

dependencies {
    // add a logger
    implementation("org.slf4j:slf4j-simple:1.7.+")
    // infinitic framework
    implementation("io.infinitic:infinitic-factory:0.9.0")
    // infinitic dashboard
    implementation("io.infinitic:infinitic-dashboard:0.9.0")
}

application {
    mainClassName = "example.booking.WorkerKt"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

task("dispatch", JavaExec::class) {
    group = "infinitic"
    main = "example.booking.ClientKt"
    classpath = sourceSets["main"].runtimeClasspath
}

task("dashboard", JavaExec::class) {
    group = "infinitic"
    main = "example.booking.DashboardKt"
    classpath = sourceSets["main"].runtimeClasspath
    setArgsString("infinitic.yml")
}