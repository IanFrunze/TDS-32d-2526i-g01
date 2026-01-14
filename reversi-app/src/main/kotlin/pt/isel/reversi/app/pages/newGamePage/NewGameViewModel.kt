package pt.isel.reversi.app.pages.newGamePage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pt.isel.reversi.app.exceptions.NoPieceSelected
import pt.isel.reversi.app.state.pages.ScreenState
import pt.isel.reversi.app.state.pages.UiState
import pt.isel.reversi.app.state.pages.ViewModel
import pt.isel.reversi.core.Game
import pt.isel.reversi.core.Player
import pt.isel.reversi.core.board.PieceType
import pt.isel.reversi.core.exceptions.ReversiException
import pt.isel.reversi.core.startNewGame
import pt.isel.reversi.core.storage.MatchPlayers
import pt.isel.reversi.utils.LOGGER

data class NewGameUiState(
    override val screenState: ScreenState = ScreenState()
) : UiState {
    override fun updateScreenState(newScreenState: ScreenState) =
        copy(screenState = newScreenState)
}

class NewGameViewModel(
    private val scope: CoroutineScope,
    private val playerName: String?,
    override val globalError: ReversiException? = null,
    override val setGlobalError: (Exception?) -> Unit,
    private val createGame: (Game) -> Unit,
) : ViewModel<NewGameUiState>() {
    override val _uiState = mutableStateOf(
        NewGameUiState(
            screenState = ScreenState(error = globalError)
        )
    )
    override val uiState: State<NewGameUiState> = _uiState

    fun tryCreateGame(game: Game, boardSize: Int) {
        val currGameName = game.currGameName

        val myPiece: PieceType = game.myPiece ?: run {
            setError(NoPieceSelected())
            return
        }

        scope.launch {
            try {
                val newGame = if (currGameName.isNullOrBlank()) {
                    createLocalGame(boardSize, myPiece)
                } else {
                    val name = playerName ?: myPiece.name
                    createNotLocalGame(playerName = name, currGameName, boardSize, myPiece)
                }

                LOGGER.info("Novo jogo '${currGameName?.ifBlank { "(local)" } ?: "(local)"} ' iniciado.")
                createGame(newGame)
            } catch (e: Exception) {
                setError(e)
            }
        }
    }

    private suspend fun createLocalGame(boardSize: Int, myPiece: PieceType) =
        startNewGame(
            side = boardSize,
            players = MatchPlayers(
                Player(PieceType.BLACK),
                Player(PieceType.WHITE)
            ),
            firstTurn = myPiece,
        )


    private suspend fun createNotLocalGame(
        playerName: String,
        gameName: String,
        boardSize: Int,
        myPiece: PieceType
    ) =
        startNewGame(
            side = boardSize,
            players = MatchPlayers(
                Player(myPiece, name = playerName),
            ),
            firstTurn = myPiece,
            currGameName = gameName.trim(),
        )
}