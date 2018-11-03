// Kotlin 'when' expression is a much more powerful 'switch' statement
// 'When' expression must be exhaustive and cover all cases.
fun desc(obj: Any): String = when(obj) {
    1 -> "one"
    is Long -> "long"
    is String -> "string"
    else -> "unknown"
}

check(desc(1) == "one")
check(desc(24L) == "long")
check(desc("...") == "string")
check(desc(true) == "unknown")

