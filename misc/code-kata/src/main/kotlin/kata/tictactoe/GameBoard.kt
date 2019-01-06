package kata.tictactoe

import kata.tictactoe.BoardItem.Companion.boardItem
import kata.tictactoe.BoardItem.EMPTY
import kata.tictactoe.GameState.*

class GameBoard {
    private val boardSize = 3;
    private val board = Array(boardSize * boardSize) { EMPTY }
    private var currentPlayer = 0
    private var gameState = IN_PROGRESS

    fun move(row: Int, column: Int) {
        validateMove(row, column)
        board[row * boardSize + column] = boardItem(currentPlayer)

        when {
            isVictory() -> gameState = VICTORY
            isDraw() -> gameState = DRAW
            else -> switchPlayer()
        }
    }

    fun at(row: Int, column: Int): BoardItem = board[row * boardSize + column]

    fun gameState() = gameState

    fun currentPlayer() = this.currentPlayer

    private fun validateMove(row: Int, column: Int) {
        if (row < 0 || row >= boardSize || column < 0 || column >= boardSize) {
            throw IndexOutOfBoundsException("Cannot place move outside the board")
        }

        if (at(row, column) != EMPTY) {
            throw IllegalStateException("Cannot move here as the field is not empty")
        }
    }

    private fun switchPlayer() {
        currentPlayer = (currentPlayer + 1) % 2
    }

    private fun isVictory() = horizontalLineExists() || verticalLineExists() || diagonalLineExists()

    private fun isDraw() = !isVictory() && boardFull()

    private fun boardFull() = board.all { it != EMPTY }

    private fun horizontalLineExists(): Boolean {
        for (row in 0 until boardSize) {
            if (at(row, 0) != EMPTY && at(row, 0) == at(row, 1) && at(row, 1) == at(row, 2)) {
                return true
            }
        }

        return false
    }

    private fun verticalLineExists(): Boolean {
        for (col in 0 until boardSize) {
            if (at(0, col) != EMPTY && at(0, col) == at(1, col) && at(1, col) == at(2, col)) {
                return true
            }
        }
        return false
    }

    private fun diagonalLineExists(): Boolean {
        return at(0, 0) != EMPTY && at(0, 0) == at(1, 1) && at(1, 1) == at(2, 2) ||
                at(0, 2) != EMPTY && at(0, 2) == at(1, 1) && at(1, 1) == at(2, 0)
    }
}