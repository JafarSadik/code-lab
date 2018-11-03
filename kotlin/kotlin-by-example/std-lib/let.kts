// let calls the specified function block with this value as an argument and returns its result
val list = listOf(1, 2, 3)
check(list.let { println("size: ${it.size}"); it } == listOf(1, 2, 3))