package pt.isel.reversi.core.exceptions

class EndGame(
    message: String = "The game has ended",
    type: ErrorType
) : ReversiException(message, type)