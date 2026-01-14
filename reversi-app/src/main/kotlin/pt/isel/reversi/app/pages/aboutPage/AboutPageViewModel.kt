package pt.isel.reversi.app.pages.aboutPage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import pt.isel.reversi.app.state.pages.ScreenState
import pt.isel.reversi.app.state.pages.UiState
import pt.isel.reversi.app.state.pages.ViewModel
import pt.isel.reversi.core.exceptions.ReversiException

data class AboutUiState(
    override val screenState: ScreenState = ScreenState()
) : UiState {
    override fun updateScreenState(newScreenState: ScreenState) =
        copy(screenState = newScreenState)
}

class AboutPageViewModel(
    override val globalError: ReversiException? = null,
    override val setGlobalError: (Exception?) -> Unit,
) : ViewModel<AboutUiState>() {
    override val _uiState = mutableStateOf(
        AboutUiState(
            screenState = ScreenState(error = globalError)
        )
    )
    override val uiState: State<AboutUiState> = _uiState

}