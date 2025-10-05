package pt.isel.reversi.board

data class Coordinate(val row: Int, val col: Int) {
    constructor(row: Int, col: Char) : this(
        row,
        col.lowercase()[0] - 'a' + 1
    ) {
        require(col in 'a'..'z' || col in 'A'..'Z') {
            "Column must be a letter from a to z or A to Z"
        }
    }

    fun equals(other: Coordinate): Boolean =
        this.row == other.row && this.col == other.col

    operator fun plus(other: Coordinate): Coordinate =
        Coordinate(this.row + other.row, this.col + other.col)

    operator fun minus(other: Coordinate): Coordinate =
        Coordinate(this.row - other.row, this.col - other.col)

    fun isValid(boardSide: Int): Boolean =
        row in 1..boardSide && col in 1..boardSide
}