import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import java.net.URI

val kotlinVersion: String by project
val pf4jVersion: String by project
val version: String by project

val pluginsDir by extra { file("${layout.buildDirectory}/plugins") }

buildscript {
    dependencies {
        classpath(kotlin("gradle-plugin", version = "2.0.0"))
    }
}

plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("kapt") version "2.0.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"
}

tasks.register<Zip>("zipCoverage") {
    archiveFileName.set("coverage.zip")
    destinationDirectory.set(layout.buildDirectory.dir("reports"))
    from(layout.buildDirectory.dir("reports/jacoco/testCodeCoverageReport/html/"))
}

tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions.languageVersion.set(KotlinVersion.KOTLIN_2_0)
    compilerOptions.progressiveMode = true
    compilerOptions.freeCompilerArgs.add("-Xcontext-receivers")
}

dependencies {
}

tasks.check {
    dependsOn(tasks.named<JacocoReport>("testCodeCoverageReport"))
}

allprojects {
    // a bit of a hack: the "plugins" project is just a container and doesn't have
    // any documentation of its own.
    if (name == "plugins") {
        return@allprojects
    }

    dependencies {
    }

    tasks.withType<Test> {
        // there are some CI failures which might be related, trying to disable it for now
        systemProperties["junit.jupiter.execution.parallel.enabled"] = true
        systemProperties["junit.jupiter.execution.parallel.mode.default"] = "concurrent"
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 4).takeIf { it > 0 } ?: 1

        maxHeapSize = "1G"
        // prevents hanging tests. But it's also a good guideline that no single test should run longer than 5 seconds
        // manually docker pull if tests initially fail due to timeout
        systemProperties["kotest.framework.invocation.timeout"] = "50000"
        systemProperties["kotest.framework.parallelism"] = maxParallelForks
        systemProperties["kotest.framework.isolation.mode"] = "InstancePerTest"

        useJUnitPlatform()

        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    repositories {
        mavenCentral()
        maven { url = URI("https://jitpack.io") }
    }
}


tasks.build {
    dependsOn("plugins:assemblePlugins")
}
