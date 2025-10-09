package pt.isel.reversi.cli

import pt.isel.reversi.cli.commands.ExitCmd
import pt.isel.reversi.cli.commands.NewCmd
import pt.isel.reversi.core.game.GameImpl
import pt.isel.reversi.core.game.MockGame
import pt.isel.reversi.core.game.localgda.LocalGDA
import pt.rafap.ktflag.CommandParser
import pt.rafap.ktflag.cmd.CommandImpl
import pt.rafap.ktflag.cmd.CommandResultType
import pt.rafap.ktflag.style.Colors
import pt.rafap.ktflag.style.Colors.colorText

/**
 * Entry point for the CLI version of the Reversi game.
 * Initializes the board and command parser, and handles user input.
 */
fun runCli(debug: Boolean = false) {
    /**
     * The current game board. Initialized with size 8.
     */
    var game: GameImpl = MockGame.OnePlayer(LocalGDA(), "game.txt")

    val debugCommands: Array<CommandImpl<GameImpl>> = if (debug) arrayOf(
        // Add debug commands here
    ) else arrayOf()

    val parser = CommandParser(NewCmd, ExitCmd, *debugCommands)

    while (true) {
        val input = parser.readInput()
        val result = parser.parseInputToResult(input, game)

        if (result == null) {
            println(
                colorText(
                    "[ERROR] Unknown command",
                    Colors.RED
                )
            )
            continue
        }

        when {
            result.type != CommandResultType.SUCCESS -> result.printError()
            result.result != null                    -> game = result.result!!
        }
    }
}