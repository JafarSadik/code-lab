package kata.tictactoe

enum class BoardItem {
    X, O, EMPTY;

    companion object {
        fun boardItem(player: Int) = when (player) {
            0 -> X
            else -> O
        }
    }
}