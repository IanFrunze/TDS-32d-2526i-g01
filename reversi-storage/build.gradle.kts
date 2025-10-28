plugins {
    kotlin("jvm") version "2.1.20"
}

group = "pt.isel.reversi.storage"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okio:okio:3.16.2")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}