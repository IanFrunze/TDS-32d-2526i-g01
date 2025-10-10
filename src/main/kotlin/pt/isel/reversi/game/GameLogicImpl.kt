package pt.isel.reversi.game

import pt.isel.reversi.board.PieceType
import pt.isel.reversi.board.Board
import pt.isel.reversi.board.Coordinates
import pt.isel.reversi.board.Piece

interface GameLogicImpl {
    fun play(board: Board, myPiece: Piece): Board

    fun getAvailablePlays(
        board: Board,
        myPieceType: PieceType,
    ): List<Coordinates>

    fun isValidMove(
        board: Board,
        myPiece: Piece,
        ): Boolean

    fun findAround(
        board: Board,
        myPiece: Piece,
        findThis: PieceType?
    ): List<Coordinates>

    fun getCapturablePieces (
        board: Board,
        myPiece: Piece,
        direction: Coordinates
    ): List<Coordinates>
}