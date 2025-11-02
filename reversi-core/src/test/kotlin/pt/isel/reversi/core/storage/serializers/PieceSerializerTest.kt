package pt.isel.reversi.core.storage.serializers

import pt.isel.reversi.core.board.Coordinate
import pt.isel.reversi.core.board.Piece
import pt.isel.reversi.core.board.PieceType
import kotlin.test.Test

class PieceSerializerTest {
    val testUnit = SerializerTestUnit(PieceSerializer()){
        val list = mutableListOf<Piece>()
        for (i in 1..50) {
            for (j in 1..50) {
                val coord = Coordinate(i, j)
                for (pieceType in PieceType.entries) {
                    list += Piece(coord, pieceType)
                }
            }
        }
        list
    }

    @Test
    fun `Test serialize and deserialize`() {
        testUnit.runTest()
    }
}