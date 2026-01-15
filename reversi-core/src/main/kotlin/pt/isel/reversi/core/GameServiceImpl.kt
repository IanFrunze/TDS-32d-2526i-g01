package pt.isel.reversi.core

import pt.isel.reversi.core.storage.GameState

interface GameServiceImpl {
    fun getStorageTypeName(): String
    suspend fun hasAllPlayers(game: Game): Boolean
    suspend fun refresh(game: Game): Game
    suspend fun refreshBase(game: Game): GameState?
    suspend fun hardRefresh(game: Game): Game
    suspend fun saveEndGame(game: Game)
    suspend fun saveOnlyBoard(gameName: String?, gameState: GameState?)
    suspend fun runStorageHealthCheck(): Boolean
    suspend fun closeService()
    suspend fun new(gameName: String, gameStateProvider: () -> GameState)
}