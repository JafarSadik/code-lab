package kata.tictactoe

import kata.tictactoe.BoardItem.Companion.boardItem
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

class GameBoardTest {

    private val gameBoard = GameBoard()
    private val firstPlayer = 0
    private val secondPlayer = 1

    @Test
    fun `first player should place a cross`() {
        move(0, 1)

        assertBoard(
                ' ', 'x', ' ',
                ' ', ' ', ' ',
                ' ', ' ', ' '
        )
    }

    @Test
    fun `second player should place a circle`() {
        move(0, 1)
        move(1, 1)

        assertBoard(
                ' ', 'x', ' ',
                ' ', 'o', ' ',
                ' ', ' ', ' '
        )
    }

    @Test(expected = IllegalStateException::class)
    fun `should fail when the second player attempts to override the first player's move`() {
        move(0, 1)
        move(0, 1)
    }

    @Test(expected = IllegalStateException::class)
    fun `should fail when the first player attempts to override the second player's move`() {
        move(0, 1)
        move(1, 1)
        move(1, 1)
    }

    @Test(expected = IllegalStateException::class)
    fun `should fail when the first player attempts to override his previous move`() {
        move(0, 1)
        move(1, 1)
        move(0, 1)
    }

    @Test(expected = IllegalStateException::class)
    fun `should fail when the second player attempts to override his previous move`() {
        move(0, 1)
        move(1, 1)
        move(2, 2)
        move(1, 1)
    }

    @Test
    fun `should fail to place move outside of the board`() {
        assertThatThrownBy { move(-1, 0) }.isInstanceOf(IndexOutOfBoundsException::class.java)
        assertThatThrownBy { move(0, -1) }.isInstanceOf(IndexOutOfBoundsException::class.java)
        assertThatThrownBy { move(-1, -1) }.isInstanceOf(IndexOutOfBoundsException::class.java)
        assertThatThrownBy { move(3, 0) }.isInstanceOf(IndexOutOfBoundsException::class.java)
        assertThatThrownBy { move(0, 3) }.isInstanceOf(IndexOutOfBoundsException::class.java)
        assertThatThrownBy { move(3, 3) }.isInstanceOf(IndexOutOfBoundsException::class.java)
    }

    /*
       Players perform a sequence of moves blocking opponent victory

       . . .    . x .    . x .    . x x    o x x    o x x
       . . .    . . .    . o .    . o .    . o .    . o .
       . . .    . . .    . . .    . . .    . . .    . . x
    */
    @Test
    fun `players should be able to play in turns`() {
        move(0, 1)
        move(1, 1)
        move(0, 2)
        move(0, 0)
        move(2, 2)

        assertBoard(
                'o', 'x', 'x',
                ' ', 'o', ' ',
                ' ', ' ', 'x'
        )
    }

    @Test
    fun `should indicate game in progress`() {
        move(0, 1)
        assertEquals(GameState.IN_PROGRESS, gameBoard.gameState())
    }

    @Test
    fun `first player wins with horizontal line`() {
        move(0, 0)
        move(1, 0)

        move(0, 1)
        move(1, 1)

        move(0, 2)

        assertPlayerWon(firstPlayer)
    }

    @Test
    fun `second player wins with horizontal line`() {
        move(1, 0)
        move(0, 0)

        move(1, 1)
        move(0, 1)

        move(2, 1)
        move(0, 2)

        assertPlayerWon(secondPlayer)
    }

    @Test
    fun `first player wins with vertical line`() {
        move(0, 0)
        move(0, 1)

        move(1, 0)
        move(1, 1)

        move(2, 0)

        assertPlayerWon(firstPlayer)
    }

    @Test
    fun `second player wins with vertical line`() {
        move(0, 1)
        move(0, 0)

        move(1, 1)
        move(1, 0)

        move(2, 2)
        move(2, 0)

        assertPlayerWon(secondPlayer)
    }

    @Test
    fun `first player wins with diagonal line`() {
        move(0, 0)
        move(2, 0)

        move(1, 1)
        move(0, 2)

        move(2, 2)

        assertPlayerWon(firstPlayer)
    }

    @Test
    fun `second player wins with diagonal line`() {
        move(2, 2)
        move(0, 2)

        move(0, 0)
        move(1, 1)

        move(0, 1)
        move(2, 0)

        assertPlayerWon(secondPlayer)
    }

    @Test
    fun `should detect game draw`() {
        move(0, 1)
        move(0, 0)
        move(1, 1)
        move(0, 2)
        move(1, 2)
        move(1, 0)
        move(2, 0)
        move(2, 1)
        move(2, 2)

        assertEquals(GameState.DRAW, gameBoard.gameState())
    }

    private fun move(row: Int, col: Int) = gameBoard.move(row, col)

    private fun assertPlayerWon(player: Int) {
        assertEquals(GameState.VICTORY, gameBoard.gameState())
        assertEquals(player, gameBoard.currentPlayer())
    }

    private fun assertBoard(vararg expectedBoard: Char) {
        val diff = mutableListOf<Pair<Int, Int>>()

        for (row in 0..2) {
            for (col in 0..2) {
                if (gameBoard.at(row, col) != boardItem(expectedBoard[row * 3 + col])) {
                    diff.add(Pair(row, col))
                }
            }
        }

        if (diff.isNotEmpty()) {
            fail("Expected board doesn't match actual board at positions: $diff")
        }
    }
}