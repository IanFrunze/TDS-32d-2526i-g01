package pt.isel.reversi.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import org.jetbrains.compose.resources.painterResource
import reversi.reversi_app.generated.resources.Res
import reversi.reversi_app.generated.resources.reversi

fun main() = application {
    var showAbout by remember { mutableStateOf(false) }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Reversi-DEV",
        icon = painterResource(Res.drawable.reversi),
    ) {
        MaterialTheme(
            colorScheme = darkColorScheme(
                primary = Color(0xFF6C63FF),
                surface = Color(0xFF1E1E1E),
                onSurface = Color.White
            )
        ) {
            // === Barra de menus ===
            MenuBar {
                Menu("Ficheiro") {
                    Separator()
                    Item("Sair") { exitApplication() }
                }

                Menu("Ajuda") {
                    Item("Sobre") { showAbout = true }
                }
            }

            // === Conteúdo principal ===
            App()
        }
    }

    // === Janela "Sobre" (popup) ===
    if (showAbout) {
        Window(
            onCloseRequest = { showAbout = false },
            title = "Sobre o Reversi",
            state = rememberWindowState(
                width = 360.dp,
                height = 220.dp,
                position = WindowPosition.Aligned(Alignment.Center)
            ),
            resizable = false,
            alwaysOnTop = true
        ) {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Color(0xFF6C63FF),
                    surface = Color(0xFF1E1E1E),
                    onSurface = Color.White
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Reversi v1.0.1",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(Modifier.height(8.dp))
                        Text("Desenvolvido por Rafael Vermelho Pereira")
                        Text("Compose Desktop 💜 Kotlin 2.2.20")
                        Spacer(Modifier.height(20.dp))
                        Button(onClick = { showAbout = false }) {
                            Text("Fechar")
                        }
                    }
                }
            }
        }
    }
}