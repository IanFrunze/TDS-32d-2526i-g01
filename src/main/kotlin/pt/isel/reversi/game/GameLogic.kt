package pt.isel.reversi.game
import pt.isel.reversi.board.Board
import pt.isel.reversi.board.Coordinates
import pt.isel.reversi.board.Piece
import pt.isel.reversi.board.PieceType

class GameLogic: GameLogicImpl {
    override fun play(
        board: Board,
        coordinates: Coordinates,
        pieceType: PieceType
    ): Board {
        TODO("Not yet implemented")
    }

    /**
     * Gets a list of available plays for the player with the specified piece type.
     * A play is considered available if placing a piece of the given type at that position
     * would result in capturing at least one of the opponent's pieces.
     * @param board The current state of the board.
     * @param myPieceType The type of piece for the player (e.g., BLACK or WHITE).
     * @return A list of coordinates where the player can place their piece.
     */
    override fun getAvailablePlays(
        board: Board,
        myPieceType: PieceType,
    ): List<Coordinates> {
        val opponentPieces = board.filter {it.value != myPieceType }
        return opponentPieces
            .flatMap { piece -> // For each opponent piece, find empty spaces around it
                findAround(board, piece, null).filter {
                    getCapturablePieces( // Check if placing this piece would capture any opponent pieces
                        board,
                        piece.swap(),
                        it - piece.coordinate
                    ).isNotEmpty()
                }
            }.toSet().toList()// Remove duplicates by converting to a set and back to a list
    }

    /**
     * Determines if placing a piece of the given type at the specified coordinates is a valid move.
     * A move is considered valid if there is at least one opposite piece type in any of the 8 directions
     * around the specified coordinates.
     * @param board The current state of the board.
     * @param coordinates The coordinates where the piece is to be placed.
     * @param pieceType The type of piece to be placed (e.g., BLACK or WHITE).
     * @return True if the move is valid, false otherwise.
     */
    override fun isValidMove(
        board: Board,
        myPiece: Piece,
    ): Boolean {
        val oppositePieces = findAround(board,myPiece , myPiece.value.swap())
        oppositePieces.forEach { coordinate ->
            val direction = coordinate - myPiece.coordinate
            if (getCapturablePieces(board, myPiece, direction).isNotEmpty()) {
                return true
            }
        }
        return false
    }

    /**
     * Finds all pieces of a specified type around a given piece in all 8 directions.
     * @param board The current state of the board.
     * @param myPiece The piece around which to search.
     * @param findThis The type of piece to search for. If null, it will find empty spaces.
     * @return A list of coordinates where the specified type of piece is found around the given piece.
     */
    override fun findAround(
        board: Board,
        myPiece: Piece,
        findThis: PieceType?
    ): List<Coordinates> {
        // Check all 8 directions
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
        val coordinates = myPiece.coordinate
        return buildList {
            directions.forEach { direction ->
                val newCord = coordinates + direction

                if (newCord.isValid(board.side)
                    && board[newCord] == findThis
                ) {
                    add(newCord)
                }
            }
        }
    }

    /**
     * Gets a list of capturable pieces in the specified direction from the given coordinates.
     * A piece is considered capturable if it is of the opposite type and is followed by a piece of the same type
     * as the one being placed, with no empty spaces in between.
     * @param board The current state of the board.
     * @param myPiece The piece being placed on the board.
     * @param direction The direction to check in (should be one of the 8 possible directions).
     * @return A list of coordinates of capturable pieces in the specified direction.
     */
    override fun getCapturablePieces (
        board: Board,
        myPiece: Piece,
        direction: Coordinates
    ): List<Coordinates> {
        var nextPiece: Piece = Piece(
            myPiece.coordinate + direction,
            board[myPiece.coordinate + direction] ?: return emptyList()
        )
        val capturablePieces = mutableListOf<Coordinates>()

        while (nextPiece.value == myPiece.value.swap()) {
            capturablePieces += nextPiece.coordinate
            nextPiece = Piece(
                nextPiece.coordinate + direction,
                board[nextPiece.coordinate + direction] ?: return emptyList()
            )
        }
        return capturablePieces
    }
}