// Destructive declarations allow for even more concise syntax
val (x, y, z) = arrayOf(5, 10, 15)
check(listOf(x, y, z) == listOf(5, 10, 15))

val people = mapOf("Alice" to 21, "Bob" to 25)
check(people.map { (name, age) -> "$name is $age years old" } == listOf(
        "Alice is 21 years old",
        "Bob is 25 years old")
)

fun <T : Comparable<T>> minMax(list: List<T>) = Pair(list.min(), list.max())
check(minMax(listOf(-1, 0, 1, 2, 3)) == Pair(-1, 3))
check(minMax(listOf('a', 'b', 'c')) == Pair('a', 'c'))
check(minMax(listOf("this", "is", "sparta")) == Pair("is", "this"))


data class User(val id: Long, val userName: String)

fun getUser(id: Long, userName: String) = User(id, userName)
val (paulId, paulUserName) = getUser(15L, "Paul")
check(paulId == 15L && paulUserName == "Paul")

val (_, steveUserName) = getUser(16L, "Steve")
check(steveUserName == "Steve")

