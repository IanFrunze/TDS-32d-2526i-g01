package pt.isel
/**
 * Represents a board game grid.
 *
 * @property Piece The piece on the board, either 'b' for black or 'w' for white.
 * @property side The size of the board (number of rows and columns).
 */
class Board(private val side: Int) {
    private val pieces: List<Piece> = listOf()

    /**
     * Represents a piece on the board.
     */
    data class Piece(val row: Int, val col: Int, var value: Char)

    /**
     * Gets the piece at the specified row and column.
     * @return The piece at the specified position, or null if there is no piece.
     */
    operator fun get(row: Int, col: Char): Piece? = this[row, charIdxToInt(col)]

    /**
     * Gets the piece at the specified row and column.
     * @return The piece at the specified position, or null if there is no piece.
     */
    operator fun get(row: Int, col: Int): Piece? {
        require(row in 1..side) {
            "Row must be between 1 and $side" }
        require(col in 1..side) {
            "Column must be between 1 and $side"
        }
        return pieces.find { it.row == row && it.col == col }
    }

    /**
     * Changes the piece at the specified row and column from 'b' to 'w' or from 'w' to 'b'.
     * @return true if the piece was changed, false if there is no piece at the specified position.
     */
    fun changePiece(row: Int, col: Char): Boolean = changePiece(row, charIdxToInt(col))

    /**
     * Changes the piece at the specified row and column from 'b' to 'w' or from 'w' to 'b'.
     * @return true if the piece was changed, false if there is no piece at the specified position.
     */
    fun changePiece(row: Int, col: Int): Boolean {
        require(row in 1..side) {
            "Row must be between 1 and $side" }
        require(col in 1..side) {
            "Column must be between 1 and $side"
        }
        val value = this[row,col]?.value ?: return false
        val newValue = if (value == 'b') 'w' else 'b'
        this[row,col]?.value = newValue
        return true
    }

    /**
     * Adds a piece to the board at the specified row and column.
     */
    fun addPiece(row: Int, col: Char, value: Char) = addPiece(row, charIdxToInt(col), value)

    /**
     * Adds a piece to the board at the specified row and column.
     */
    fun addPiece(row: Int, col: Int, value: Char) {
        val value = value.lowercase()[0]

        require(row in 1..side) {
            "Row must be between 1 and $side"
        }
        require(col in 1..side) {
            "Column must be between 1 and $side"
        }
        require(value == 'b' || value == 'w') {
            "Value must be 'b' or 'w'"
        }

        pieces.plus(Piece(row, col, value))
    }

    /**
     * Converts a column character ('a', 'b', ...) to its corresponding integer index.
     */
    private fun charIdxToInt(col: Char): Int {
        val colLower = col.lowercase()[0]
        require(colLower in 'a'..'a' + side - 1) {
            "Column must be between 'a' and '${'a' + side - 1}'" }
        return colLower - 'a' + 1
    }
}