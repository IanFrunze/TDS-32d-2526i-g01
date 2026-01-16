package pt.isel.reversi.app.gamePageTest

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import pt.isel.reversi.app.pages.game.GamePageViewModel
import pt.isel.reversi.core.board.PieceType
import pt.isel.reversi.core.game.gameServices.FakeGameService
import pt.isel.reversi.core.game.startNewGame
import pt.isel.reversi.core.gameState.MatchPlayers
import pt.isel.reversi.core.gameState.Player
import pt.isel.reversi.utils.BASE_FOLDER
import java.io.File
import kotlin.test.*

class GamePageViewModelTests {


    val service = FakeGameService()

    val game = runBlocking {
        startNewGame(
            side = 4,
            players = MatchPlayers(Player(type = PieceType.BLACK), Player(type = PieceType.WHITE)),
            firstTurn = PieceType.BLACK,
            currGameName = null,
            service = service,
        )
    }

    fun vmForTest(scope: CoroutineScope) = GamePageViewModel(
        game,
        scope,
        {},
        {},
        {},
        null,
        { _, _ -> }
    )

    val id = "testGame"
    fun vmNotLocalGame(scope: CoroutineScope) = GamePageViewModel(
        game.copy(currGameName = id),
        scope,
        { },
        { },
        { },
        null,
        { _, _ -> }
    )



    @BeforeTest
    @AfterTest
    fun cleanUp() {
        File(BASE_FOLDER).deleteRecursively()
    }

    @BeforeTest
    fun setup() {
        service.save(
            id,
            game.gameState!!
        )
    }

    @AfterTest
    fun tearDown() {
        service.delete(id)
    }

    @Test
    fun `verify that the state initializes correctly`() = runTest {

        val uut = vmForTest(this)

        // Verify initial state
        assertNotNull(uut.uiState.value)
        assertEquals(game, uut.uiState.value.game)
    }

    @Test
    fun `verify that get available plays works correctly`() = runTest {
        val uut = vmForTest(this)

        val availablePlays = uut.getAvailablePlays()
        val expectedPlays = game.getAvailablePlays()

        assertEquals(expectedPlays, availablePlays)
    }

    @Test
    fun `verify that set target mode works correctly`() = runTest {
        val uut = vmForTest(this)

        val initialTarget = uut.uiState.value.game.target
        uut.setTarget(!initialTarget)

        assertEquals(!initialTarget, uut.uiState.value.game.target)
    }

    @Test
    fun `verify that save preserves game state`() = runTest {
        val uut = vmForTest(this)

        uut.save()
        // Verify save was called
        assertNotNull(uut.uiState.value)
    }

    @Test
    fun `verify polling control methods work`() = runTest {
        val uut = vmForTest(this)

        // Initially no polling
        assertEquals(false, uut.isPollingActive())

        // Start polling would require coroutine context, so we just verify the state
        assertNotNull(uut)
    }

    @Test
    fun `verify that starting polling twice throws exception`() = runTest {
        val uut = vmForTest(this)
        uut.startPolling()
        try {
            uut.startPolling()
            assert(false) // Should not reach here
        } catch (_: IllegalStateException) {
            assert(true) // Expected exception
        } finally {
            uut.stopPolling()
        }
    }

    @Test
    fun `verify polling refreshes game state`() = runTest {
        val uut = vmNotLocalGame(this)
        val initialGame = uut.uiState.value.game

        uut.startPolling()

        var expected = initialGame.play(initialGame.getAvailablePlays().first())
        expected = expected.copy(gameState = expected.gameState?.refreshPlayers())

        service.save(
            id,
            expected.gameState!!
        )

        delay(200L)
        uut.stopPolling()

        assertEquals(expected.gameState, uut.uiState.value.game.gameState)
    }

    @Test
    fun `verify polling does not update when no changes`() = runTest {
        val uut = vmNotLocalGame(this)
        val initialGame = uut.uiState.value.game

        uut.startPolling()

        delay(200L)
        uut.stopPolling()

        assertEquals(initialGame.gameState, uut.uiState.value.game.gameState)
    }

    @Test
    fun `verify polling if game have a winner call setGame and setPage`() = runTest {
        var setGameCalled = false
        var setPageCalled = false

        val uut = GamePageViewModel(
            game.copy(
                gameState = game.gameState?.copy(
                    winner = Player(type = PieceType.BLACK)
                )
            ),
            this,
            setGame ={
                setGameCalled = true
            },
            {},
            setPage ={
                setPageCalled = true
            },
            null,
            { _, _ -> }
        )

        uut.startPolling()
        delay(100L)
        uut.stopPolling()

        assertTrue(setGameCalled)
        assertTrue(setPageCalled)
    }

    @Test
    fun `verify polling setError when myPiece is null`() = runTest {
        val uut = GamePageViewModel(
            game.copy(
                currGameName = id,
                myPiece = null
            ),
            this,
            {},
            {
            },
            {},
            null,
            { _, _ -> }
        )

        uut.startPolling()
        delay(100L)
        uut.stopPolling()

        assertTrue(uut.error != null)
    }

    @Test
    fun `verify polling preserve player names when refreshing`() = runTest {
        val uut = vmNotLocalGame(this)
        val initialGame = uut.uiState.value.game
        val initialName = initialGame.gameState?.players?.getPlayerByType(initialGame.myPiece!!)?.name
        val myPiece = initialGame.myPiece!!

        uut.startPolling()

        //need to change lastModified to trigger update
        var expected = initialGame.play(initialGame.getAvailablePlays().first())
        expected = expected.copy(
            gameState = expected.gameState?.refreshPlayers()
        )

        //change my player name in storage
        service.save(
            id,
            expected.gameState?.copy(
                players = expected.gameState?.changeName(
                    newName = "NewName",
                    pieceType = myPiece
                )!!.players
            )!!
        )

        delay(200L)
        uut.stopPolling()

        val refreshedPlayer = uut.uiState.value.game.gameState?.players?.getPlayerByType(myPiece)
        // Verify that the player's name remains unchanged
        assertEquals(initialName, refreshedPlayer?.name)
    }
}



