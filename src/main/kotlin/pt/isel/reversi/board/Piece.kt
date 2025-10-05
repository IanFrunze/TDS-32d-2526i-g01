package pt.isel.reversi.board

/**
 * Represents a piece on the board.
 */
data class Piece(
    val coordinate: Coordinate,
    val value: PieceType
)