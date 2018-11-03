// String templates and multiline strings
// Strings in Kotlin can include references to variables that are interpolated.
// It's also possible to include expressions enclosed in curly braces.
val kotlin = "Kotlin"
check("This is $kotlin" == "This is Kotlin")
check("This is ${kotlin.toUpperCase()}" == "This is KOTLIN")
check("""
        First line
        Second line
        Third line
    """.trimIndent().split("\n") == listOf("First line", "Second line", "Third line"))
