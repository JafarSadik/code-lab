// Extension Functions and Properties
// Kotlin provides the ability to extend a class with new functionality without having to inherit the class or use
// any type of design pattern such as Decorator. This is done via special declarations called extensions
fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val tmp: T = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

val numbers = mutableListOf(1, 2, 3)
check(numbers == listOf(1, 2, 3))

numbers.swap(1, 2)
check(numbers == listOf(1, 3, 2))

val chars = mutableListOf('a', 'b', 'c')
chars.swap(0, 1)
check(chars == listOf('b', 'a', 'c'))