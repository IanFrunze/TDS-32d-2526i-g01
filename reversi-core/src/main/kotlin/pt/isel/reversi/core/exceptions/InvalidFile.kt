package pt.isel.reversi.core.exceptions

class InvalidFile(
    message: String = "The provided file is invalid or corrupted",
    type: ErrorType
) : ReversiException(message, type)
