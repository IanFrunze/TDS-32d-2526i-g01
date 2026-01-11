package pt.isel.reversi.app.pages.settingsPage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import pt.isel.reversi.app.state.ScreenState
import pt.isel.reversi.app.state.UiState
import pt.isel.reversi.app.state.setError
import pt.isel.reversi.core.exceptions.ReversiException

data class SettingsUiState(
    override val screenState: ScreenState = ScreenState()
): UiState() {
    override fun updateScreenState(newScreenState: ScreenState) =
        copy(screenState = newScreenState)
}

class SettingsViewModel(
    scope: CoroutineScope,
    globalError: ReversiException? = null
) {
    private val _uiState = mutableStateOf(
        SettingsUiState(
            screenState = ScreenState(error = globalError)
        )
    )
    val uiState: State<SettingsUiState> = _uiState

    fun setError(error: Exception?) =
        _uiState.setError(error)
}