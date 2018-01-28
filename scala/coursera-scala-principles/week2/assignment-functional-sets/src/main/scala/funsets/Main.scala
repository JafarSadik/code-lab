package funsets

import funsets.FunSets._

object Main extends App {
  println("Check if single element set of {1} contains 1")
  println(contains(singletonSet(1), 1))

  println("Empty set")
  printSet(emptySet)

  println("\nIntersection of two large sets: <-100000, 100> and <95, 1000000>")
  printSet(intersect(boundedSet(-100000, 100), boundedSet(95, 1000000)))

  println("\nFilter the set {1, 2, 3, 4} to contain just even numbers")
  printSet(filter(union(singletonSet(1), singletonSet(2)), _ % 2 == 0))

  println("\nFilter set {1, 2 ,3 ,4, 5, 6, 7, 8, 9, 10} with a predicate e => e % 2 == 0")
  val oneToTen = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  printSet(filter(oneToTen, _ % 2 == 0))

  println("\nCheck if set {1, 2 ,3 ,4, 5, 6, 7, 8, 9, 10} satisfies a predicate for all its items:")
  println("* e => e % 2 == 0: " + forall(oneToTen, _ % 2 == 0))
  println("* e => e > 0: " + forall(oneToTen, _ > 0))
  println("* e => e > 1: " + forall(oneToTen, _ > 1))
  println("* e => e >= 1: " + forall(oneToTen, _ >= 1))
  println("* e => e > 1000: " + forall(oneToTen, _ > 1000))
  println("* e => e < 1000: " + forall(oneToTen, _ < 1000))

  println("\nCheck if set {2 ,4, 6, 8, 10} contains item that fulfills a predicate:")
  println("* e => e % 2 == 0: " + exists(setOf(2, 4, 6, 8, 10), _ % 2 == 0))
  println("* e => e % 2 != 0: " + exists(setOf(2, 4, 6, 8, 10), _ % 2 != 0))
  println("* e => e > 5: " + exists(setOf(2, 4, 6, 8, 10), _ > 5))
  println("* e => e > 10: " + exists(setOf(2, 4, 6, 8, 10), _ > 10))
  println("* e => e < 100: " + exists(setOf(2, 4, 6, 8, 10), _ < 100))
  println("* e => e < 10: " + exists(setOf(2, 4, 6, 8, 10), _ < 10))
  println("* e => e < 5: " + exists(setOf(2, 4, 6, 8, 10), _ < 5))
  println("* e => e < 2: " + exists(setOf(2, 4, 6, 8, 10), _ < 2))

  println("\nMap set {1,3,4,5,7,1000} to a different set using function: e => e - 1")
  printSet(map(setOf(1, 3, 4, 5, 7, 1000), _ - 1))
}
