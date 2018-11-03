/*
 Functions

 This is a kotlin script, the main method is automatically generated based on the content of this file.

 Run it with command:
  > kotlinc -script basics.kts

 Use Kotlin's REPL to experiment with new features:
  > kotlinc-jvm
 */

// Kotlin functions support default parameter values and named arguments
fun log(msg: String, logLevel: String = "INFO"): Unit {
    println("[$logLevel] $msg")
}

log("everything seems to be fine")
log("well, that was strange... ", logLevel = "WARN")

fun sum(x: Int, y: Int) = x + y

check(sum(1, 2) == 3)
check(sum(x = 1, y = 2) == 3)
check(sum(y = 2, x = 1) == 3)

// Member functions and extensions with a single parameter can be turned into infix functions
infix fun String.times(repeat: Int) = this.repeat(repeat)
check("123".times(2) == "123123")
check("Poland" to 40000000 == Pair("Poland", 40000000))
Person("Sophia") likes Person("Claudia")

class Person(val name: String) {
    val likedPeople = mutableListOf<Person>()
    infix fun likes(other: Person) {
        likedPeople.add(other)
    }
}

// Kotlin allows to define custom operators:
// unary(+, -, !, ++, --)
// binary (+, -, *, /, %, .., in, !in)
// index operator
// invoke operator
operator fun Int.times(str: String) = str.repeat(this)
check(2 * "hello " == "hello hello ")

// Varargs allow to pass any number of arguments by comma-separating them
fun printAll(vararg messages: String) {
    for (message in messages) println(message)
}

fun log(vararg entries: String) {
    printAll(*entries)
}

printAll("This", "is", "Sparta")
log("log1", "log2", "log3")