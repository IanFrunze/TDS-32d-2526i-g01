package pt.isel.reversi.board

/**
 * Represents a piece on the board.
 */
data class Piece(
    val coordinate: Coordinates,
    val value: PieceType
) {
    fun swap(): Piece {
        return Piece(coordinate, value.swap())
    }
}