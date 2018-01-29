package funsets

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSuite, Matchers}

@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite with Matchers {

  import FunSets._

  test("contains is implemented") {
    assert(contains(e => true, 100))
  }

  test("singleton set {1} should contain element 1") {
    assert(contains(singletonSet(1), 1))
  }

  test("singleton set {1} does not contain {-1, 0, 2, 3}") {
    assert(!contains(singletonSet(1), -1))
    assert(!contains(singletonSet(1), 0))
    assert(!contains(singletonSet(1), 2))
    assert(!contains(singletonSet(1), 3))
  }

  test("union of two empty sets should be empty set") {
    count(union(emptySet, emptySet), -1000 to 1000) shouldBe 0
  }

  test("a union of two sets should contain all elements from both sets") {
    toList(union(setOf(1, 2, 3), boundedSet(2, 5))) should contain only(1, 2, 3, 4, 5)
    toList(union(emptySet, boundedSet(2, 5))) should contain only(2, 3, 4, 5)
    toList(union(emptySet, emptySet)) shouldBe empty
  }

  test("an intersection of two sets should contain all common elements from the both sets") {
    toList(intersect(boundedSet(-100, 10), boundedSet(8, 500))) should contain only(8, 9, 10)
    toList(intersect(boundedSet(-100, 10), boundedSet(20, 100))) shouldBe empty
  }

  test("a difference of two sets should contain all elements from the first set that are not in the second set") {
    toList(diff(boundedSet(-100, 100), lowerBoundedSet(-95))) should contain only(-100, -99, -98, -97, -96)
    toList(diff(setOf(1, 2, 3), setOf(2, 3))) should contain only 1
    toList(diff(setOf(1, 2, 3), unboundedSet)) shouldBe empty
  }

  test("filter set values with a predicate") {
    toList(filter(boundedSet(1, 10), even)) should contain only(2, 4, 6, 8, 10)
    toList(filter(boundedSet(-100, 100), _ > 97)) should contain only(98, 99, 100)
    toList(filter(boundedSet(-100, 100), _ < -97)) should contain only(-98, -99, -100)
  }

  test("forAll() checks if all elements within the set satisfy given predicate") {
    forall(setOf(1, 2, 3, 4, 5), even) shouldBe false
    forall(setOf(2, 4, 6, 8), even) shouldBe true
    forall(setOf(2, 4, 6, 8), _ > 0) shouldBe true
    forall(setOf(2, 4, 6, 8), _ < 100) shouldBe true
    forall(setOf(2, 4, 6, 8), _ < 5) shouldBe false
  }

  test("exists() checks if at least one element within the set satisfy given predicate") {
    exists(setOf(1, 3, 5, 7, 9), even) shouldBe false
    exists(setOf(1, 3, 5, 7, 9), !even(_)) shouldBe true
    exists(setOf(1, 3, 5, 7, 9), _ > 0) shouldBe true
    exists(setOf(1, 3, 5, 7, 9), _ < 100) shouldBe true
    exists(setOf(1, 3, 5, 7, 9), _ < 6) shouldBe true
    exists(setOf(1, 3, 5, 7, 9), _ < 1) shouldBe false
  }

  test("map a set to a different set using function Int => Int") {
    toList(map(setOf(1, 3, 4, 5, 7, 1000), _ - 1)) should contain only(0, 2, 3, 4, 6, 999)
    toList(map(setOf(-4, -3, -2, -1, 0, 1, 2, 3, 4), _ * 2)) should contain only(-8, -6, -4, -2, 0, 2, 4, 6, 8)
  }

  // Checks if the provided integer is an even number
  def even(v: Int): Boolean = v % 2 == 0

  // Counts elements of set in a given range
  private def count(set: Set, range: Range = -1000 to 1000): Int = range.count(set(_))

  // List containing all elements of a set in a given range.
  private def toList(set: Set, range: Range = -1000 to 1000): List[Int] = range.filter(set(_)).toList
}
