package pt.isel.reversi.core.storage.serializers

import pt.isel.reversi.core.board.PieceType
import kotlin.test.Test

class PieceTypeSerializerTest {
    val testUnit = SerializerTestUnit(PieceTypeSerializer()) {
        PieceType.entries
    }

    @Test
    fun `Test serialize and deserialize`() {
        testUnit.runTest()
    }
}