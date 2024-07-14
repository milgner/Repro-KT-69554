plugins {
    kotlin("jvm") version "2.0.0"
    id("org.jetbrains.kotlin.plugin.jpa") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
    kotlin("kapt") version "2.0.0"
}

// not necessary for Gradle 8, but better to have it explicit
kotlin {
    jvmToolchain(21)
}

dependencies {
    kapt("org.pf4j:pf4j:3.11.1")

    compileOnly("io.arrow-kt:arrow-core:1.2.4")
    compileOnly("org.pf4j:pf4j:3.11.1")

    testImplementation("io.arrow-kt:arrow-core:1.2.4")
    testImplementation("org.pf4j:pf4j:3.11.1")
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
}