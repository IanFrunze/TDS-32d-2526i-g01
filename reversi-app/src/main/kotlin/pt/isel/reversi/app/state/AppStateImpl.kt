package pt.isel.reversi.app.state

import pt.isel.reversi.app.AppTheme
import pt.isel.reversi.app.pages.PagesState
import pt.isel.reversi.core.Game
import pt.isel.reversi.core.GameServiceImpl
import pt.isel.reversi.core.exceptions.ReversiException
import pt.isel.reversi.utils.audio.AudioPool

interface AppStateImpl {
    val game: Game
    val pagesState: PagesState
    val audioPool: AudioPool
    val globalError: ReversiException?
    val theme: AppTheme
    val playerName: String?
    val service: GameServiceImpl
}