import pt.isel.reversi.board.Board
import pt.isel.reversi.board.Coordinates
import pt.isel.reversi.board.Piece
import pt.isel.reversi.board.PieceType
import pt.isel.reversi.game.GameLogic
import kotlin.test.Test

class GameLogicTests {
    @Test
    fun `checkAround and isValidMove returns empty list when there are no pieces around` () {
        val board = Board(8)
        val coordinates = Coordinates(0, 0)
        val uut = GameLogic().findAround(board, Piece(coordinates, PieceType.BLACK), PieceType.WHITE)
        assert(emptyList<Coordinates>() == uut)
        assert(!GameLogic().isValidMove(board, Piece(coordinates, PieceType.BLACK)))
    }

    @Test
    fun `checkAround and isValidMove return empty list when have only same pieces around`(){
        var board = Board(8)
        val cordPiece = Coordinates(2, 2)
        val directions = listOf(
            Coordinates(-1, -1), // Top-left
            Coordinates(-1, 0), // Top
            Coordinates(-1, 1), // Top-right
            Coordinates(0, -1), // Left
            Coordinates(0, 1), // Right
            Coordinates(1, -1), // Bottom-left
            Coordinates(1, 0), // Bottom
            Coordinates(1, 1)  // Bottom-right
        )
        val newPieces = directions.map { dir ->
            (cordPiece + dir)
        }
        newPieces.forEach { piece ->
            board = board.addPiece(piece, PieceType.BLACK)
        }
        val uut = GameLogic().findAround(board,Piece(cordPiece, PieceType.BLACK), PieceType.WHITE)
        assert(emptyList<Coordinates>() == uut)
        assert(!GameLogic().isValidMove(board, Piece(cordPiece, PieceType.BLACK)))
    }

    @Test
    fun `checkAround and isValidMove return list with all opposite pieces around`(){
        var board = Board(8)
        val cordPiece = Coordinates(2, 2)
        val directions = listOf(
            Coordinates(-1, -1), // Top-left
            Coordinates(-1, 0), // Top
            Coordinates(-1, 1), // Top-right
            Coordinates(0, -1), // Left
            Coordinates(0, 1), // Right
            Coordinates(1, -1), // Bottom-left
            Coordinates(1, 0), // Bottom
            Coordinates(1, 1)  // Bottom-right
        )
        val newPieces = directions.map { dir ->
            (cordPiece + dir)
        }
        newPieces.forEach { piece ->
            board = board.addPiece(piece, PieceType.WHITE)
        }
        val uut = GameLogic().findAround(board, Piece(cordPiece, PieceType.BLACK), PieceType.WHITE)
        assert(newPieces == uut)
        assert(GameLogic().isValidMove(board, Piece(cordPiece, PieceType.BLACK)))
    }

    @Test
    fun `checkAround and isValidMove return list with some opposite pieces around`(){
        var board = Board(8)
        val cordPiece = Coordinates(2, 2)
        val directions = listOf(
            Coordinates(-1, -1), // Top-left
            Coordinates(-1, 0), // Top
            Coordinates(-1, 1), // Top-right
            Coordinates(0, -1), // Left
            Coordinates(0, 1), // Right
            Coordinates(1, -1), // Bottom-left
            Coordinates(1, 0), // Bottom
            Coordinates(1, 1)  // Bottom-right
        )
        val newPieces = directions.map { dir ->
            (cordPiece + dir)
        }
        val oppositePieces = listOf(newPieces[0], newPieces[3], newPieces[5])
        val samePieces = listOf(newPieces[1], newPieces[2], newPieces[4], newPieces[6], newPieces[7])
        oppositePieces.forEach { piece ->
            board = board.addPiece(piece, PieceType.WHITE)
        }
        samePieces.forEach { piece ->
            board = board.addPiece(piece, PieceType.BLACK)
        }
        val uut = GameLogic().findAround(board,Piece(cordPiece, PieceType.BLACK), PieceType.WHITE)
        assert(oppositePieces == uut)
        assert(GameLogic().isValidMove(board, Piece(cordPiece, PieceType.BLACK)))
    }

    @Test
    fun `getAvailablePlays returns correct list of coordinates`() {
        val board = Board(8).addPiece(Coordinates(2, 2),PieceType.WHITE)
        val myPieceType = PieceType.BLACK

        val expectedCoordinates = listOf(
            Coordinates(1, 1),
            Coordinates(1, 2),
            Coordinates(1, 3),
            Coordinates(2, 1),
            Coordinates(2, 3),
            Coordinates(3, 1),
            Coordinates(3, 2),
            Coordinates(3, 3)
        )

        assert(
            GameLogic().getAvailablePlays(board, myPieceType) == expectedCoordinates)
    }
}