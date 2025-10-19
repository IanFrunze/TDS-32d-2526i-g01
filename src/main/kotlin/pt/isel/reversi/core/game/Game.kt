package pt.isel.reversi.core.game

import pt.isel.reversi.core.board.Board
import pt.isel.reversi.core.board.Coordinate
import pt.isel.reversi.core.board.PieceType

class Game(
    override val dataAccess: GDAImpl,
    override val players: List<Player>,
    override val currGameName: String?,
    override val board: Board?,
    override val target: Boolean,
    override val isLocal: Boolean
) : GameImpl {
    override fun play(coordinate: Coordinate): GameImpl {
        TODO("Not yet implemented")
    }

    override fun pieceOptions(): List<PieceType> {
        TODO("Not yet implemented")
    }

    override fun setTargetMode(target: Boolean): GameImpl {
        TODO("Not yet implemented")
    }

    override fun getAvailablePlays(): List<Pair<Int, Int>> {
        TODO("Not yet implemented")
    }

    override fun startNewGame() {
        TODO("Not yet implemented")
    }

    override fun pass() {
        TODO("Not yet implemented")
    }

    override fun refresh(): GameImpl {
        TODO("Not yet implemented")
    }

    override fun poopBoard(): Board {
        TODO("Not yet implemented")
    }

    override fun copy(
        dataAccess: GDAImpl,
        players: List<Player>,
        currGameName: String?,
        board: Board?,
        target: Boolean,
        isLocal: Boolean
    ): GameImpl {
        TODO("Not yet implemented")
    }
}