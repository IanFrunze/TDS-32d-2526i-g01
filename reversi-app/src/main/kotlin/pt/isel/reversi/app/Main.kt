package pt.isel.reversi.app

import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import reversi.reversi_app.generated.resources.Res
import reversi.reversi_app.generated.resources.reversi

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Reversi-DEV",
        icon = painterResource(Res.drawable.reversi),
    ) {
        // === Barra de menus ===
        MenuBar {
            Menu("Ficheiro") {
                Separator()
                Item("Sair") { exitApplication() }
            }

            Menu("Ajuda") {
                Item("Sobre") { }
            }
        }

        // === Conteúdo principal ===
        App()

    }
}