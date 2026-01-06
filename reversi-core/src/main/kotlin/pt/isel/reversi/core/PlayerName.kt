package pt.isel.reversi.core

import pt.isel.reversi.core.board.PieceType

/**
 * Represents a player in the game.
 * @property type The type of piece the player uses.
 * @property name The name of the player.
 */
data class PlayerName(
    val type: PieceType,
    val name: String
) {
    /**
     * Swaps the player's piece type.
     * @return A new Player instance with the swapped piece type.
     */
    fun swap(): PlayerName {
        val swappedType = when (type) {
            PieceType.BLACK -> PieceType.WHITE
            PieceType.WHITE -> PieceType.BLACK
        }
        return PlayerName(
            name = name,
            type = swappedType
        )
    }
}