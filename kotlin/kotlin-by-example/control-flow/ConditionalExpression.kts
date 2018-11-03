// In Kotlin 'if' can be used as statement and expression.
fun max(a: Int, b: Int) = if (a > b) a else b
check(max(4, 5) == 5)

val sth = if (40 > 50) 40 else 50
check(sth == 50)
