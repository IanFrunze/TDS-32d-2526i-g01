import pt.isel.reversi.core.exceptions.ErrorType

abstract class ReversiException(
    message: String,
    val type: ErrorType
) : Exception(message)