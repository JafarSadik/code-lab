// A higher order function is a function that takes another function as parameter and/or returns a function.
fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

check(calculate(3, 5, { x, y -> x + y }) == 8)

fun unaryOp(type: String): (Int) -> Int = when(type) {
    "square" -> {x -> x * x}
    "ident" -> {x -> x}
    "cube" -> {x -> x * x * x}
    else -> unaryOp("ident")
}

check(unaryOp("ident")(11) == 11)
check(unaryOp("square")(11) == 121)
check(unaryOp("cube")(11) == 1331)
check(unaryOp("unknown")(11) == 11)
