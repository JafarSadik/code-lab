// Enum classes are used to model types that represent a finite set of distinct values such as states, modes and so forth.
enum class State {
    IDLE, RUNNING, FINISHED
}

// They may contain properties and methods like other classes.
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF),
    WHITE(0xFFFFFF);

    fun hasRed() = rgb and 0xFF0000 != 0
}

check(Color.WHITE.hasRed())
check(Color.RED.hasRed())
check(!Color.GREEN.hasRed())

val state = State.IDLE
val message = when(state) {
    State.IDLE -> "It's idle"
    State.RUNNING -> "It's running"
    State.FINISHED -> "It's finished"
}
check(message == "It's idle")