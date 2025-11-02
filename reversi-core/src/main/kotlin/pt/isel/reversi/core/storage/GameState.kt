package pt.isel.reversi.core.storage

import pt.isel.reversi.core.board.Board
import pt.isel.reversi.core.board.PieceType

data class GameState(
    val lastPlayer: PieceType? = null,
    val board: Board? = null
)

