package pt.isel.reversi

import pt.isel.reversi.cli.runCli

fun main(args: Array<String>) {
    val debug = args.contains("--debug")
    runCli(debug)
}