package funsets


/**
  * Purely Functional Set
  */
object FunSets {
  /**
    * We represent a set by its characteristic function, i.e.
    * its `contains` predicate.
    */
  type Set = Int => Boolean

  /**
    * Indicates whether a set contains a given element.
    */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /**
    * Returns the set of the one given element.
    */
  def singletonSet(elem: Int): Set = _ == elem

  /**
    * Returns empty set
    */
  def emptySet: Set = _ => false

  /**
    * Returns a set containing all possible integers
    */
  def unboundedSet: Set = _ => true

  /**
    * A set containing all specified numbers
    */
  def setOf(elements: Int*): Set = elements.contains(_)

  /**
    * Returns a set containing all integers below or equal to a specified upper bound.
    */
  def upperBoundedSet(upperBound: Int): Set = _ <= upperBound

  /**
    * Returns a set containing all integers above or equal to a specified lower bound.
    */
  def lowerBoundedSet(lowerBound: Int): Set = _ >= lowerBound

  /**
    * Returns a set of all integers limited by lower bound (inclusive) and upper bound (inclusive)
    */
  def boundedSet(lowerBound: Int, upperBound: Int): Set =
    elem => elem >= lowerBound && elem <= upperBound

  /**
    * Returns the union of the two given sets,
    * the sets of all elements that are in either `s` or `t`.
    */
  def union(s: Set, t: Set): Set = elem => s(elem) || t(elem)

  /**
    * Returns the intersection of the two given sets,
    * the set of all elements that are both in `s` and `t`.
    */
  def intersect(s: Set, t: Set): Set = elem => s(elem) && t(elem)

  /**
    * Returns the difference of the two given sets,
    * the set of all elements of `s` that are not in `t`.
    */
  def diff(s: Set, t: Set): Set = elem => s(elem) && !t(elem)

  /**
    * Returns the subset of `s` for which `p` holds.
    */
  def filter(s: Set, p: Int => Boolean): Set = intersect(s, p)


  /**
    * The bounds for `forall` and `exists` are +/- 1000.
    */
  val bound = 1000

  /**
    * Returns whether all bounded integers within `s` satisfy `p`.
    */
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (s(a) && !p(a)) false
      else iter(a + 1)
    }

    iter(-bound)
  }

  /**
    * Returns whether there exists a bounded integer within `s`
    * that satisfies `p`.
    */
  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, !p(_))

  /**
    * Returns a set transformed by applying `f` to each element of `s`.
    */
  def map(s: Set, f: Int => Int): Set = (elem: Int) => exists(s, f(_) == elem)

  /**
    * Displays the contents of a set
    */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
    * Prints the contents of a set on the console.
    */
  def printSet(s: Set) {
    println(toString(s))
  }
}
