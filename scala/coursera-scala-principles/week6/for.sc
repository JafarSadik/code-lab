// In scala most forms of iterations can be performed with collections and higher order functions.
// Sometimes it's possible to be more expressive using 'for' expression.
case class Person(name: String, age: Int)

val people = List(Person("Alice", 25), Person("Greg", 12), Person("Albert", 60), Person("Julia", 17))

// for expression, iterate and print
for (p <- people) println(p.name)

// for expression with mutable state
var count = 0
for (p <- people) if (p.age > 2) count += 1
s"People old enough: $count"

// for expression with yield and if
for (p <- people if p.age > 20) yield p.name // obtain names of all people over 20 years
people filter (_.age > 20) map (_.name)

// for expression with 2 nested iterations
for (i <- 1 to 10; j <- 1 to 10) yield (s"$i * $j", i * j)

// for expression with 2 nested iterations and local value
for {i <- 1 to 10
     j <- 1 to 10
     product = i * j // introducing local value
} yield (s"$i * $j", product)




