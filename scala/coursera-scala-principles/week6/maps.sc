// Map is a data structure that associates keys with values.
val capitals = Map("US" -> "Washington", "Poland" -> "Warsaw", "Switzerland" -> "Bern")
val romanNumerals = Map("I" -> 1, "V" -> 5, "X" -> 10, "L" -> 50, "C" -> 100, "D" -> 500, "M" -> 1000)
capitals.exists(_._2 == "Warsaw")
capitals.exists { case (country, capital) => capital == "Warsaw" }
capitals("US")
//capitals("Andora")  <---- NoSuchElementException
capitals.get("US") // returns Some(Washington)
capitals.get("Andora") // returns None

// Option[T] trait represents an optional value. Instances can be either None or Some(t)
// Options can be decomposed using pattern matching:
capitals.get("Poland") match {
  case Some(_) => "country exists"
  case None => "country doesn't exist"
}

// Handle None value:
Option(null) == Option {null}
Option {
  null
}.orElse(Option(":D"))
capitals.get("Russia").orElse(Some("unknown"))
capitals.getOrElse("Russia", "unknown")

// It's also possible to apply some standard sequence operations
capitals.get("Poland").map(_.length)
capitals.get("Poland").map(_.toUpperCase())
