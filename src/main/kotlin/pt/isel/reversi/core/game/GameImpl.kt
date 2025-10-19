package pt.isel.reversi.core.game

import pt.isel.reversi.core.board.Board
import pt.isel.reversi.core.board.Coordinate
import pt.isel.reversi.core.board.PieceType

/**
 * Interface for the main game operations and state in Reversi.
 */
interface GameImpl {
    /** Data access layer for game persistence. */
    val dataAccess: GDAImpl

    /** List of players in the game. */
    val players: List<Player>

    /** The current game name, if any. */
    val currGameName: String?

    /** The current board state. */
    val board: Board?

    /** Indicates if target mode is enabled. */
    val target: Boolean

    /** Indicates if the game is local. */
    val isLocal: Boolean

    /**
     * Plays a move at the specified row and column.
     * @param row The row to play.
     * @param col The column to play.
     * @return The new game state after the move, or null if invalid.
     */
    fun play(coordinate: Coordinate): GameImpl

    /**
     * Gets the available piece options for a player name.
     * @param name The player's name.
     * @return List of available piece types.
     */
    fun pieceOptions(): List<PieceType>

    /**
     * Sets the target mode for the game.
     * @param target True to enable target mode.
     * @return The updated game state.
     */
    fun setTargetMode(target: Boolean): GameImpl

    /**
     * Gets the available plays for the current player.
     * @return List of available (row, column) pairs.
     */
    fun getAvailablePlays(): List<Coordinate>

    /**
     * Starts a new game.
     */
    fun startNewGame()

    /**
     * Passes the current turn.
     */
    fun pass()

    /**
     * Refreshes the game state.
     * @return The refreshed game state.
     */
    fun refresh(): GameImpl

    /**
     * Returns the current board state.
     * @return The board.
     */
    fun poopBoard(): Board

    fun copy(
        dataAccess: GDAImpl = this.dataAccess,
        players: List<Player> = this.players,
        currGameName: String? = this.currGameName,
        board: Board? = this.board,
        target: Boolean = this.target,
        isLocal: Boolean = this.isLocal
    ): GameImpl
}