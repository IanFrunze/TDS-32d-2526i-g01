package pt.isel.reversi.app.pages.winnerPage

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.reversi.app.ScaffoldView
import pt.isel.reversi.app.app.state.AppState
import pt.isel.reversi.app.app.state.ReversiScope
import pt.isel.reversi.app.app.state.ReversiText
import pt.isel.reversi.app.pages.menu.drawCrown
import pt.isel.reversi.app.utils.PreviousPage
import pt.isel.reversi.core.game.Game
import pt.isel.reversi.core.game.gameServices.EmptyGameService
import pt.isel.reversi.core.gameState.Player

fun testTagDrawCrown(): String = "draw_crown"

@Composable
fun ReversiScope.WinnerPage(
    viewModel: WinnerPageViewModel,
    onLeave: () -> Unit
) {
    val state = viewModel.uiState.value

    ScaffoldView(
        setError = { error, type -> viewModel.setError(error, type) },
        error = viewModel.error,
        isLoading = state.screenState.isLoading,
        title = "Fim de Jogo",
        previousPageContent = {
            PreviousPage { onLeave() }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(16.dp),
        ) {
            val winner = state.winner ?: run {
                ReversiText(
                    text = "Empate!",
                    color = appState.theme.textColor
                )
                return@Column
            }
            val players = viewModel.game.gameState?.players?.toList() ?: emptyList()

            if (players.isEmpty()) {
                ReversiText(
                    text = "Dados do vencedor indisponíveis.",
                    color = appState.theme.textColor
                )
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly, // Ensures equal spacing
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    players.forEach { player ->
                        PlayerResultColumn(
                            player = player,
                            isWinner = player == winner,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ReversiScope.PlayerResultColumn(
    player: Player,
    isWinner: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier, // Each player takes 50% width
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.size(80.dp)) {
            Canvas(
                modifier = Modifier.fillMaxSize()
                    .semantics { testTag = testTagDrawCrown() }
            ) {
                val cx = size.width / 2f
                val cy = size.height / 2f
                val crownRadius = size.minDimension / 1.2f

                drawCrown(cx, cy, crownRadius, if (isWinner) 1f else 0f)
            }
        }
        DrawPlayerInfo(player)
    }
}

@Composable
fun ReversiScope.DrawPlayerInfo(player: Player) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ReversiText(
            text = player.name,
            color = appState.theme.textColor,
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
        ReversiText(
            text = "${player.points} pontos",
            color = appState.theme.textColor,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview
@Composable
fun WinnerPagePreview() {
    ReversiScope(appState = AppState.empty(service = EmptyGameService()))
        .WinnerPage(
            viewModel = WinnerPageViewModel(
                game = Game(service = EmptyGameService()),
                globalError = null,
                setGlobalError = { _, _ -> }
            ),
            onLeave = {}
        )
}