import org.jetbrains.dokka.Platform
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm") version "2.1.20" apply false
    id("org.jetbrains.dokka") version "1.9.20" apply false
}

group = "pt.isel.reversi"
version = "0.0.1"

allprojects {
    repositories {
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.dokka")

    tasks.withType<DokkaTask>().configureEach {
        dokkaSourceSets.configureEach {
            platform.set(Platform.jvm)
            includes.from("Module.md")
        }
    }
}
