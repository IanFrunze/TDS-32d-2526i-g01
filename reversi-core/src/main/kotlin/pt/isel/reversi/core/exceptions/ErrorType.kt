package pt.isel.reversi.core.exceptions

enum class ErrorType(val level: String) {
    INFO("INFO"),
    WARNING("WARNING"),
    ERROR("ERROR"),
    CRITICAL("CRITICAL")
}