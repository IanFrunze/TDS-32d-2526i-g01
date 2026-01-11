package pt.isel.reversi.app.pages.aboutPage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import pt.isel.reversi.app.state.ScreenState
import pt.isel.reversi.app.state.UiState
import pt.isel.reversi.app.state.setError
import pt.isel.reversi.core.exceptions.ReversiException

data class AboutUiState(
    override val screenState: ScreenState = ScreenState()
) : UiState() {
    override fun updateScreenState(newScreenState: ScreenState) =
        copy(screenState = newScreenState)
}

class AboutPageViewModel(
    scope: CoroutineScope,
    globalError: ReversiException? = null
) {
    private val _uiState = mutableStateOf(
        AboutUiState(
            screenState = ScreenState(error = globalError)
        )
    )
    val uiState: State<AboutUiState> = _uiState

    fun setError(error: Exception?) =
        _uiState.setError(error)
}