package pt.isel.reversi.app.gamePage

import pt.isel.reversi.core.board.Coordinate
import pt.isel.reversi.core.board.PieceType

fun testTagTitle(gameName: String) = "game_page_title_$gameName"
fun testTagBoard() = "game_page_board"
fun testTagCellView(coordinate: Coordinate) =
    "cell_${coordinate.row},${coordinate.col}"
fun testTagPiece(coordinate: Coordinate, type: PieceType?): String {
    val value = when (type) {
        PieceType.BLACK -> "BLACK"
        PieceType.WHITE -> "WHITE"
        null -> ""
    }
    return "Piece_${testTagCellView(coordinate)}_${value}"
}