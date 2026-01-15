package pt.isel.reversi.core

import kotlinx.coroutines.runBlocking
import java.io.File

fun cleanup(func: suspend () -> Unit) {
    val conf = loadCoreConfig()
    File(conf.savesPath).deleteRecursively()
    runBlocking { func() }
    File(conf.savesPath).deleteRecursively()
}

