package pt.isel.reversi.app.pages.menu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import pt.isel.reversi.app.state.AppState
import pt.isel.reversi.app.state.getStateAudioPool
import pt.isel.reversi.app.state.pages.Page
import pt.isel.reversi.app.state.pages.ScreenState
import pt.isel.reversi.app.state.pages.UiState
import pt.isel.reversi.app.state.pages.ViewModel
import pt.isel.reversi.core.exceptions.ReversiException


data class MainMenuUIState(
    override val screenState: ScreenState = ScreenState()
) : UiState {
    override fun updateScreenState(newScreenState: ScreenState): UiState {
        return this.copy(screenState = newScreenState)
    }
}

class MainMenuViewModel(
    private val appState: AppState,
    override val globalError: ReversiException? = null,
    override val setGlobalError: (Exception?) -> Unit,
    val setPage: (Page) -> Unit,
) : ViewModel<MainMenuUIState>() {
    override val _uiState = mutableStateOf(
        MainMenuUIState(
            screenState = ScreenState(
                error = globalError
            )
        )
    )
    override val uiState: State<MainMenuUIState> = _uiState


    fun playMenuAudio() {
        val audioPool = getStateAudioPool(appState)
        val theme = appState.theme
        if (!audioPool.isPlaying(theme.backgroundMusic)) {
            audioPool.stopAll()
            audioPool.play(theme.backgroundMusic)
        }
    }
}