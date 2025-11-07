import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

dependencies {
    implementation(compose.desktop.currentOs)

    // JetBrains Compose Material3 (já incluído no compose.material3)
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.ui)
    implementation(compose.components.resources)
    implementation(compose.components.uiToolingPreview)

    implementation(project(":reversi-core"))
    implementation(project(":reversi-utils"))

    // Testes desktop
    testImplementation(compose.desktop.uiTestJUnit4)
}

compose.resources {
    publicResClass = true // 👈 Gera as classes "Res.*"
}

compose.desktop {
    application {
        mainClass = "pt.isel.reversi.app.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Reversi"
            packageVersion = "1.0.1"
        }
    }
}

// === Fat Jar executável ===
tasks.register<Jar>("fatJar") {
    group = "build"
    description = "Assembles an executable fat jar including all dependencies."

    archiveBaseName.set("reversi-app")
    archiveVersion.set("v1.0.1")
    archiveClassifier.set("")

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
             configurations.runtimeClasspath.get()
                 .filter { it.name.endsWith(".jar") }
                 .map { zipTree(it) }
         })

    manifest {
        attributes["Main-Class"] = "pt.isel.reversi.app.MainKt"
    }
}

// === Usa o fatJar como o jar padrão ===
tasks {
    build {
        dependsOn("fatJar")
    }

    jar {
        enabled = false
    }
}