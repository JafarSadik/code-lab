package kata.tictactoe

enum class BoardItem {
    X, O, EMPTY;

    companion object {
        fun boardItem(player: Int) = if (player == 0) X else O

        fun boardItem(item: Char) = when(item) {
            'x' -> X
            'o' -> O
            else -> EMPTY
        }
    }
}