package pt.isel.reversi.app.state

import pt.isel.reversi.app.AppTheme
import pt.isel.reversi.app.AppThemes
import pt.isel.reversi.app.pages.Page
import pt.isel.reversi.app.pages.PagesState
import pt.isel.reversi.core.CoreConfig
import pt.isel.reversi.core.Game
import pt.isel.reversi.core.exceptions.ReversiException
import pt.isel.reversi.core.gameServices.EmptyGameService
import pt.isel.reversi.core.gameServices.GameServiceImpl
import pt.isel.reversi.utils.audio.AudioPool

data class PreviewAppState(
    override val game: Game = Game(config = CoreConfig(emptyMap()), service = EmptyGameService()),
    override val pagesState: PagesState = PagesState(Page.MAIN_MENU, Page.NONE),
    override val audioPool: AudioPool = AudioPool(emptyList()),
    override val globalError: ReversiException? = null,
    override val theme: AppTheme = AppThemes.DARK.appTheme,
    override val playerName: String? = null,
    override val service: GameServiceImpl = EmptyGameService()
): AppStateImpl