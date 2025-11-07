import org.gradle.kotlin.dsl.kotlin

plugins {
    application
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.github.RafaPear:KtFlag:1.5.4")
    implementation(project(":reversi-core"))
    implementation(project(":reversi-utils"))
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("pt.isel.reversi.cli.MainKt")
}

// === Fat JAR Task ===
tasks.register<Jar>("fatJar") {
    group = "build"
    description = "Assembles a fat jar including all dependencies."

    // Nome final
    archiveBaseName.set("reversi-cli")
    archiveVersion.set("v1.0.1")
    archiveClassifier.set("") // sem "-all" → substitui o jar padrão

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    // Inclui classes do projeto
    from(sourceSets.main.get().output)

    // Inclui dependências (todas as libs do classpath)
    dependsOn(configurations.runtimeClasspath)
    from({
             configurations.runtimeClasspath.get()
                 .filter { it.name.endsWith(".jar") }
                 .map { zipTree(it) }
         })

    // Manifest com Main-Class
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}

// === Tornar o fatJar o padrão ===
tasks {
    build {
        dependsOn("fatJar")
    }

    jar {
        enabled = false // desativa o jar “magro” padrão
    }
}