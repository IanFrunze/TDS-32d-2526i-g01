package pt.isel.reversi.app

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import pt.isel.reversi.core.BOARD_SIDE


object BoardConfig {
    const val HEIGHT_FRACTION = 0.9f
    const val WIDTH_FRACTION = 0.6f
    val COLOR = Color(0xFF800080)

    val LINE_COLOR = Color.Black
    const val LINE_STROKE_WIDTH = 4f
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
    ) {
        Board()
    }
}


/** Displays the game board. */
@Preview
@Composable
fun Board() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Grid()
    }
}

/**
 * Draws the board grid.
 */
@Composable
fun Grid() {
    val modifier = Modifier.fillMaxSize()

    Box(
        modifier = Modifier
            .background(BoardConfig.COLOR)
            .fillMaxHeight(BoardConfig.HEIGHT_FRACTION)
            .fillMaxWidth(BoardConfig.WIDTH_FRACTION),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = modifier) {
            drawGrid(BOARD_SIDE, this)
        }
    }
}


/**
 * Draws a grid on the given DrawScope
 * @param side The number of cells on one side of the grid
 * @param drawScope The DrawScope where the grid will be drawn
 */
fun drawGrid(side: Int, drawScope: DrawScope) = with(drawScope) {
    val cellWidth = size.width / side
    val cellHeight = size.height / side

    for (x in 1 until side) {
        drawLine(
            color = BoardConfig.LINE_COLOR,
            strokeWidth = BoardConfig.LINE_STROKE_WIDTH,
            start = Offset(x = x * cellWidth, y = 0f),
            end = Offset(x = x * cellWidth, y = this.size.height),
        )
    }

    for (y in 1 until side) {
        drawLine(
            color = BoardConfig.LINE_COLOR,
            strokeWidth = BoardConfig.LINE_STROKE_WIDTH,
            start = Offset(x = 0f, y = y * cellHeight),
            end = Offset(x = this.size.width, y = y * cellHeight),
        )
    }
}