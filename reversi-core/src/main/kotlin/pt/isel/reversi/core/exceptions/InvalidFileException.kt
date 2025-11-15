package pt.isel.reversi.core.exceptions

import ReversiException

class InvalidFileException(
    message: String = "The provided file is invalid or corrupted",
    type: ErrorType
) : ReversiException(message, type)
