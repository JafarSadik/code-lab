// Function getOrElse provides a safe access to elements of a collection.
// It takes an index and a function that computes default value in case when index is out of bound.
val list = listOf(0, 10, 20)
with(list) {
    check(getOrElse(0) { 123 } == 0)
    check(getOrElse(1) { 123 } == 10)
    check(getOrElse(2) { 123 } == 20)
    check(getOrElse(3) { 123 } == 123)
    check(getOrElse(10000) { it % 2 } == 0)
    check(getOrElse(10001) { it % 2 } == 1)
}

val map = mapOf(1 to "one", 2 to "two", 3 to "three")
with(map) {
    check(getOrElse(1, {"other"}) == "one")
    check(getOrElse(2, {"other"}) == "two")
    check(getOrElse(3, {"other"}) == "three")
    check(getOrElse(4, {"other"}) == "other")

    check(getOrDefault(1, "other") == "one")
    check(getOrDefault(2, "other") == "two")
    check(getOrDefault(3, "other") == "three")
    check(getOrDefault(4, "other") == "other")
}