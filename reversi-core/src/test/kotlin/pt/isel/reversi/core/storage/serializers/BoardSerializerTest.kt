package pt.isel.reversi.core.storage.serializers

import org.junit.Test
import pt.isel.reversi.core.board.Board
import pt.isel.reversi.core.board.Coordinate
import pt.isel.reversi.core.board.Piece
import pt.isel.reversi.core.board.PieceType

class BoardSerializerTest {
    val testUnit = SerializerTestUnit(BoardSerializer()) {
        val list = mutableListOf<Board>()
        val sides = listOf(4, 6, 8, 10, 12, 14, 16)
        for (side in sides) {
            val pieces = mutableListOf<Piece>()
            for (i in 0 until side) {
                for (j in 0 until side) {
                    val cord = Coordinate(i, j)
                    val pieceType = PieceType.entries.random()
                    pieces += Piece(cord, pieceType)
                }
            }
            list += Board(side, pieces)
        }
        list
    }

    @Test
    fun `Test serialize and deserialize`() {
        testUnit.runTest()
    }
}