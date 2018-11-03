import kotlin.math.max

// Higher Order Functions that are frequently used with collections.

// map can be used to transform collection into another collection
val numbers = listOf(-2, -1, 0, 1, 2)
check(numbers.map { it * 2 } == listOf(-4, -2, 0, 2, 4))

// flatmap maps each element into a list and flattens the result into a single list
val words = listOf("this", "is", "a", "really", "useful", "thing")
check(words.flatMap { it.toList() }.toSet() == setOf('t', 'h', 'i', 's', 'a', 'r', 'e', 'l', 'y', 'u', 'f', 'n', 'g'))
check(listOf(1, 2, 3).flatMap { listOf(it, it, it) } == listOf(1, 1, 1, 2, 2, 2, 3, 3, 3))

// filter function filters a collection using a provided predicate
val isNegative = { x: Int -> x < 0 }
val isPositive = { x: Int -> x >= 0 }
check(numbers.filter(isNegative) == listOf(-2, -1))
check(numbers.filter(isPositive) == listOf(0, 1, 2))

// find returns first element matching the given predicate
check(words.find { it.startsWith("t") } == "this")

// any checks if at least one item satisfies the predicate
check(numbers.any { it > 1 })

// all checks if all items satisfy the predicate
check(numbers.all { it < 100 })

// none checks if there is no item that satisfies the predicate
check(numbers.none { it > 100 })

// first, firstOrNull return first element or first elment matching the predicate
val list = listOf(1, 3, 5, 6, 7, 8, 9)
check(list.first() == 1)
check(list.first { it % 2 == 0 } == 6)
check(list.firstOrNull { it == 111 } == null)

// last, lastOrNull return last element or last element matching the predicate
check(list.last() == 9)
check(list.last { it % 2 == 0 } == 8)
check(list.lastOrNull { it == 111 } == null)

// count function counts total number of elements in a collection
check(list.count() == 7)
check(list.count { it % 2 == 0 } == 2)

// partition splits a collection into pair of lists
val (negatives, positives) = numbers.partition { it < 0 }
check(negatives == listOf(-2, -1) && positives == listOf(0, 1, 2))

// associateBy, groupBy create a map by indexing collection by the provided key
data class Person(val name: String, val city: String, val phone: String)

val john = Person("John", "Boston", "+1-888-123456")
val sarah = Person("Sarah", "Munich", "+49-777-789123")
val steven = Person("Steven", "Saint-Petersburg", "+7-999-456789")
val ann = Person("Ann", "Saint-Petersburg", "+7-999-123456")
val people = listOf(john, sarah, steven, ann)

check(people.associateBy { it.phone }["+7-999-456789"] == steven)
check(people.associateBy { it.city }["Saint-Petersburg"] == ann)
check(people.groupBy { it.city }["Saint-Petersburg"] == listOf(steven, ann))
check(people.groupBy { it.city } == mapOf(
        Pair("Saint-Petersburg", listOf(steven, ann)),
        Pair("Munich", listOf(sarah)),
        Pair("Boston", listOf(john))
))
check(people.groupBy { it.city } == people.groupBy(Person::city))


// zip merges two collections by matching indexes
val animals = listOf("elephant", "lion", "mice")
val maxAge = listOf(60, 30, 2)
check(animals.zip(maxAge) == listOf(Pair("elephant", 60), Pair("lion", 30), Pair("mice", 2)))
check(animals.zip(maxAge, { animal, maxAge -> "$animal can live up to $maxAge years" }) == listOf(
        "elephant can live up to 60 years",
        "lion can live up to 30 years",
        "mice can live up to 2 years"
))

// min, max
check(numbers.min() == -2 && numbers.max() == 2)

// sorted, sortedBy
val shuffled = listOf(3, 4, 5, 2, 1)
check(shuffled.sorted() == listOf(1, 2, 3, 4, 5))
check(shuffled.sortedBy({-it}) == listOf(5, 4, 3, 2, 1))
check(listOf(setOf(1, 2), setOf(-1, 10, 20)).sortedBy {it.min()} == listOf(setOf(-1, 10, 20), setOf(1, 2)))

