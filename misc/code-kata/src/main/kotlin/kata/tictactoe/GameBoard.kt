package kata.tictactoe

import kata.tictactoe.BoardItem.Companion.boardItem
import kata.tictactoe.BoardItem.EMPTY

class GameBoard {
    private val boardSize = 3;
    private val board = Array(boardSize * boardSize) { EMPTY }
    private var currentPlayer = 0
    private var gameState = GameState.IN_PROGRESS

    fun move(row: Int, column: Int) {
        validateMove(row, column)
        board[row * boardSize + column] = boardItem(currentPlayer)

        when {
            isVictory() -> gameState = GameState.VICTORY
            isDraw() -> gameState = GameState.DRAW
            else -> switchPlayer()
        }
    }

    fun at(row: Int, column: Int): BoardItem = board[row * boardSize + column]

    private fun validateMove(row: Int, column: Int) {
        if (row < 0 || row >= boardSize || column < 0 || column >= boardSize) {
            throw IndexOutOfBoundsException("Cannot place move outside the board")
        }

        if (at(row, column) != EMPTY) {
            throw IllegalStateException("Cannot move here as the field is not empty")
        }
    }

    fun gameState() = gameState

    fun currentPlayer() = this.currentPlayer

    private fun switchPlayer() {
        currentPlayer = (currentPlayer + 1) % 2
    }

    private fun isVictory() = horizontalLineExists() || verticalLineExists() || diagonalLineExists()

    private fun isDraw() = !isVictory() && boardFull()

    private fun horizontalLineExists(): Boolean {
        for (row in 0 until boardSize) {
            val (first, second, third) = Triple(at(row, 0), at(row, 1), at(row, 2))
            if (first != EMPTY && first == second && second == third) {
                return true
            }
        }

        return false
    }

    private fun verticalLineExists(): Boolean {
        for (col in 0 until boardSize) {
            val (first, second, third) = Triple(at(0, col), at(1, col), at(2, col))
            if (first != EMPTY && first == second && second == third) {
                return true
            }
        }
        return false
    }

    private fun diagonalLineExists(): Boolean {
        return at(0, 0) != EMPTY && at(0, 0) == at(1, 1) && at(1, 1) == at(2, 2) ||
                at(0, 2) != EMPTY && at(0, 2) == at(1, 1) && at(1, 1) == at(2, 0)
    }

    private fun boardFull() = board.all { it != EMPTY }
}