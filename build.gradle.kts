import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.10"

    application
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // to be removed with Pulsar 1.8 https://github.com/apache/pulsar/issues/9045
    implementation("org.apache.avro:avro") { version { strictly("1.9.+") } }
    // add a logger
    implementation("org.slf4j:slf4j-simple:1.7.+")
    // infinitic lib
    implementation("io.infinitic:infinitic-factory:0.8.0-SNAPSHOT")
}

application {
    mainClassName = "example.booking.WorkerKt"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

task("startWorkflow", JavaExec::class) {
    group = "infinitic"
    main = "example.booking.ClientKt"
    classpath = sourceSets["main"].runtimeClasspath
}
