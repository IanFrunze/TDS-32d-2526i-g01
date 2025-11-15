package pt.isel.reversi.core.exceptions

import ReversiException

class EndGameException(
    message: String = "The game has ended",
    type: ErrorType
) : ReversiException(message, type)