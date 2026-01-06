package pt.isel.reversi.core.storage.serializers

import pt.isel.reversi.core.PlayerName
import pt.isel.reversi.storage.Serializer

/**
 * Serializer for [pt.isel.reversi.core.PlayerName].
 * Format: "<symbol>,<points>" where <symbol> is the piece symbol (e.g. 'B' or 'W') and
 * <points> is the integer number of points the player has.
 */
internal class PlayerNameSerializer : Serializer<PlayerName, String> {
    private val pieceTypeSerializer = PieceTypeSerializer()

    override fun serialize(obj: PlayerName): String {
        val symbol = pieceTypeSerializer.serialize(obj.type)
        val points = obj.name

        return "$symbol,$points"
    }

    override fun deserialize(obj: String): PlayerName {
        val (symbol, name) = obj.trim().split(",")
        val type = pieceTypeSerializer.deserialize(symbol.first())
        return PlayerName(type, name)
    }
}