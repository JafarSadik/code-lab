// List.sorted() takes Ordering as the second parameter
List(3, 2, 1).sorted(Ordering.Int)
List("def", "abc").sorted(Ordering.String)

// Ordering is an implicit parameter so the compiler will automatically search for available implementation
List(3, 2, 1).sorted
List("def", "abc").sorted

// Sorting priority tokens
case class XToken(priority: Int, value: String)
val tokenList = List(XToken(10, "acc"), XToken(10, "abc"), XToken(10, "abc"), XToken(0, "bcd"), XToken(1, "efg"))

// in an absence of implicit objects you'll get a compilation error
// tokenList.sorted   <--- WON'T compile

// but you're able to provide a class-specific ordering:
tokenList.sorted(Ordering.by[XToken, Int](e => 10 - e.priority))

// and even compare by multiple values
tokenList.sorted(Ordering.by[XToken, (Int, String)](e => (10 - e.priority, e.value)))

// other convenient ways of sorting token list
tokenList.sortBy(_.value) // sorting tokens by value
tokenList.sortBy(1000 - _.priority) // sorting tokens by priority
tokenList.sortBy(e => (100 - e.priority, e.value)) // sorting by multiple values

// sort array by the second parameter
import scala.util.Sorting
val pairs = Array(("a", 5, 2), ("c", 3, 1), ("b", 1, 3))
Sorting.quickSort(pairs)(Ordering.by(_._2))

// Declare custom implicit parameter in a companion object
import scala.math.Ordering
case class Person(name: String, age: Int)

object Person {
  trait AgeOrdering extends Ordering[Person] {
    override def compare(x: Person, y: Person) = x.age - y.age
  }

  // Keyword 'implicit' signals compiler that this object could be used as an implicit parameter
  implicit object Age extends AgeOrdering
}

val people = List(Person("Stephen", 10), Person("Adam", 34), Person("Sean", 3), Person("Alice", 60))

// As previously you're able to provide custom ordering
people.sorted(Ordering.by[Person, Int](_.age))

// Companion object allows to explicitly compare by Age.
people.sorted(Person.Age)

// But 'Age' is declared as implicit object so compiler is able to provide it in place of the missing parameter
people.sorted

// When implicit parameter is not present scala compiler will try to locate it:
// 1. first searching in the scope of method invocation and if no implicit object with matching type can be found then
// 2. it looks for members marked 'implicit' in all associated companion objects
// For this reason the following code is making use of a different implementation of Ordering:
{
  implicit object OrderByAge extends Ordering[Person] {
    override def compare(x: Person, y: Person) = {
      println("---- Called an alternative implementation of age ordering-----")
      x.age - y.age
    }
  }
  people.sorted
}