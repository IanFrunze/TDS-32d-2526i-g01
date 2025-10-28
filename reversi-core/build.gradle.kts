plugins {
    kotlin("jvm") version "2.1.20"
}

group = "pt.isel.reversi.core"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}