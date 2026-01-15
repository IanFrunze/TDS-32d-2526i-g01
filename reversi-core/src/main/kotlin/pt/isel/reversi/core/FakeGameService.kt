package pt.isel.reversi.core

import pt.isel.reversi.core.storage.GameState

class FakeGameService: GameServiceImpl {
    override fun getStorageTypeName(): String {
        return "FakeStorage"
    }

    override suspend fun hasAllPlayers(game: Game): Boolean {
        return true
    }

    override suspend fun refresh(game: Game): Game {
        return game
    }

    override suspend fun refreshBase(game: Game): GameState? {
        return game.gameState
    }

    override suspend fun hardRefresh(game: Game): Game {
        return game
    }

    override suspend fun saveEndGame(game: Game) {
        // No-op
    }

    override suspend fun saveOnlyBoard(gameName: String?, gameState: GameState?) {
        // No-op
    }

    override suspend fun runStorageHealthCheck(): Boolean {
        return true
    }

    override suspend fun closeService() {
        // No-op
    }

    override suspend fun new(gameName: String, gameStateProvider: () -> GameState) {
        // No-op
    }
}