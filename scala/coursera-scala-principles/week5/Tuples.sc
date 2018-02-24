// A pair of items
type Question = String
type Points = Int
val answer: (Question, Points) = ("Is Earth round", 40)
answer._1
answer._2

// Tuple might contain 0 - N elements
val emptyTuple = ()
val singletonTuple = (1)
var tuple = (1, 2, 3, 4, "...")
tuple._1
tuple._2
//...
tuple._5

// Pattern matching for tuples
val (first, _, _, _, last) = tuple
s"$first $last"

tuple match {
  case (1, _, _, _, _) => "tuple starts with one"
  case (v, _, _, _, _) => "tuple starts with " + v
}

// Shorthand/alternative declaration of a pair
case class Key[T <: AnyVal](value: T)

1 -> 2
1 -> 2 -> 3
"any" -> 2
Key(23) -> "int key"
Key(5.4f) -> "float key"
Key(5.4) -> "double key"

Map(1 -> "one", 2 -> "two", 3 -> "three")