// Data class with an optional, nullable property, non-null by default makes code safer
data class Person(val name: String, val age: Int? = null)

// Named and default arguments, elvis operator, concise lambda functions, predefined collection HOFs and builders
val people = listOf(Person("Alice"), Person("Bob", age = 20))
val oldest = people.maxBy { it.age ?: 0 }

// Parametrized strings, built-in preconditions such as check or require
check("The oldest is $oldest" == "The oldest is Person(name=Bob, age=20)")


