// More functions on lists
val list = List(1, 2, 3, 4, 5)
list.length // the number of elements of list
list.head // first element of list
list.tail // a list containing all elements except the first one
list.last // last element of the list
list.init // a list containing all elements except the last one
list take 2 // a list containing first N elements
Nil take 10000
list drop 2 // the rest of collection after taking first N elements
list(3) // element at position N

// Sample init implementation
def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("init of empty list")
  case List(x) => List()
  case x :: tail => x :: init(tail)
}

init(List(1, 2, 3, 4, 5))

// Creating a new lists
List(1, 2, 3) ++ List(4, 5) // concatenation of two lists
List(1, 2, 3) ::: List(4, 5) // right associative as it ends with :
1 :: 2 :: Nil // the same as 1 :: (2 :: Nil)
List(1, 2, 3) updated(2, 100) // a new lists containing one changed element
List(1, 2, 3).reverse // reverse elements of list

// Sample implementation of reverse() with pattern matching
// No tail recursion so StackOverflowError becomes a problem for a small lists (few 1000s of elements)
// Not as optimal as reverse() implementation in std scala library that is more imperative and makes use of looping
def reverse[T](xs: List[T]): List[T] = xs match {
  case List() => List()
  case y :: ys => reverse(ys) ++ List(y)
}
reverse(1 to 1000 toList)

// Finding elements
list indexOf -100 // index of first element equal to x or -1 if x does not exist
list indexOf 4
list contains 4 // tests whether the sequence contains the given value
list contains 100

// Splitting, slicing, partitioning and grouping
list splitAt 2  // splitting to two lists
list.partition(_ % 2 == 0) // partitioning list according to a predicate
List(1, 1, 2, 3).slice(1, 3) // sublist
// partition elements into a map according to some discriminator function
List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
  .groupBy(x => if (x < 4) "group a" else if (x < 9) "group b" else "group c")

// Transform each element of list
List("abcd", "abc", "ab", "a").map(_.length)
List(1, 2, 3, 4).map(x => x * x)
List("any fool can write code", "that a computer", "can understand").flatMap(_.split(" "))

// Zipping and unzipping lists
List(1, 2, 3) zip List('a', 'b', 'c')
List((1, 'a'), (2, 'b'), (3, 'c')).unzip

// Retrieve all elements satisfying a condition
list.filter(_ > 2)
list.filterNot(_ > 2)
list.takeWhile(_ < 3)
list.dropWhile(_ < 3)

// Combining lists with an operator (foldLeft, reduceLeft, foldRight, reduceRight, reduce, fold)
list.reduceLeft(_ + _) == list.sum   // applies operations from left to right
list.reduceRight(_ + _)              // applies operations from right to left
list.par.reduce(_ + _)              // order of operations not guaranteed but operations can be performed in parallel
List("a", "true", "story").foldLeft("> ")(_ + " " + _)  // applies operations from left to right with a given starting value
list.fold(1)(_ * _) == list.product   // similar to foldLeft but order of operations is not guaranteed

def isEven(v: Int): Boolean = v % 2 == 0
List(0, 1, 2, 3, 4, 5, 6, 7, 8).foldLeft(Map[Boolean, List[Int]]().withDefaultValue(Nil)) {
  (map, elem) => map.updated(isEven(elem), map(isEven(elem)) ++ List(elem))
}

println("Order of operations for reduce left:") //  ---->  (1  +   2)   +  3
List(1, 2, 3).reduceLeft((x, y) => {
  println(s"$x + $y")
  x + y
})

println("Order of operations for reduce right:") // 1 + (2 + 3) <-----
List(1, 2, 3).reduceRight((x, y) => {
  println(s"$x + $y")
  x + y
})

// Determine if list satisfies a condition
list.forall(_ < 1000)  // check if all elements satisfy a predicate
list.exists(_ < -1000) // check if at least one element satisfies a predicate

// Copy elements to a mutable array
val array = new Array[Int](10)
list.copyToArray(array)

// Other operations on lists
List(1, 1, 2, 3).distinct   // distinct elements, eliminates all duplicates
List(List(1, 2, 3), List(4, 5, 6)).flatten  // create list from lists of sequences
"abc".permutations.foreach(println(_))             // generate all permutations of a sequence
List(1, 2, 3, 4).permutations.foreach(println(_))
List(1, 2, 3, 4, 5).mkString("List(", ", " , ")")  // concatenate list elements to a string
List(1, 2, 3, 4, 5).max  // maximum value
List(1, 2, 3, 4, 5).min  // minimum value
List(("never", 1), ("forget", 2), ("this",3), ("advice", 4)).maxBy(_._2)
