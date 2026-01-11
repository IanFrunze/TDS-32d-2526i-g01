package pt.isel.reversi.app.pages.newGamePage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import pt.isel.reversi.app.state.ScreenState
import pt.isel.reversi.app.state.UiState
import pt.isel.reversi.app.state.setError
import pt.isel.reversi.core.exceptions.ReversiException

data class NewGameUiState(
    override val screenState: ScreenState = ScreenState()
) : UiState() {
    override fun updateScreenState(newScreenState: ScreenState) =
        copy(screenState = newScreenState)
}

class NewGameViewModel(
    val scope: CoroutineScope,
    globalError: ReversiException? = null
) {
    private val _uiState = mutableStateOf(
        NewGameUiState(
            screenState = ScreenState(error = globalError)
        )
    )
    val uiState: State<NewGameUiState> = _uiState

    fun setError(error: Exception?) =
        _uiState.setError(error)
}