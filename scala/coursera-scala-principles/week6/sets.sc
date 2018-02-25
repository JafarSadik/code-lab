// Set store certain values without any particular order, and no repeated values.
val fruits = Set("apple", "banana", "pear", "orange")
val numbers = (1 to 10).toSet
numbers.map(_ / 2) contains 5

// most operations on sequences are also available on sets
fruits.filter(_.startsWith("a"))
fruits.mkString(", ")
fruits.exists(_.length > 5)
fruits.groupBy(_.length).map{case(e,v) => (e, v.size)} // group by length, then calculate distribution

// algebraic operations on sets
val setA = (1 to 6).toSet
val setB = (4 to 10).toSet
                      // alternatives
setA contains 4      // setA(4)
setA.empty
setA.isEmpty
setA diff setB       // setA -- setB
setB diff setA       // setB -- setA
setA union setB      // setA | setB
setA intersect setB  // setA & setB
setA subsetOf setB

