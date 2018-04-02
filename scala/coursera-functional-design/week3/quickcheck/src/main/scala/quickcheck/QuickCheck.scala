package quickcheck

import org.scalacheck.Arbitrary._
import org.scalacheck.Gen._
import org.scalacheck.Prop._
import org.scalacheck._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {
  property("smallest element from two-element heap") = forAll { (e1: Int, e2: Int) =>
    findMin(mkHeap(e1, e2)) == e1.min(e2)
  }

  property("smallest element from three-element heap") = forAll { (e1: Int, e2: Int, e3: Int) =>
    findMin(mkHeap(e1, e2, e3)) == e1.min(e2).min(e3)
  }

  property("delete min element from single-element heap") = forAll { (e: Int) =>
    isEmpty(deleteMin(mkHeap(e)))
  }

  property("delete min element from two-element heap") = forAll { (e1: Int, e2: Int) =>
    deleteMin(mkHeap(e1, e2)) == mkHeap(e1.max(e2))
  }

  property("elements retrieved in order") = forAll { (anyHeap: H) =>
    isSorted(asList(anyHeap))
  }

  property("minimum of merged heaps") = forAll { (heap1: H, heap2: H) =>
    findMin(meld(heap1, heap2)) == findMin(heap1).min(findMin(heap2))
  }

  property("isEmpty checks if heap is empty or not") = forAll { (anyElem: Int) =>
    isEmpty(empty) && !isEmpty(mkHeap(anyElem))
  }

  property("minimum of single-element heap") = forAll { (elem: Int) =>
    findMin(insert(elem, empty)) == elem
  }

  property("add minimum element and then find it") = forAll { (heap: H) =>
    val min = if (isEmpty(heap)) 0 else findMin(heap)
    findMin(insert(min, heap)) == min
  }

  property("merging heaps is commutative") = forAll { (heap1: H, heap2: H) =>
    equal(
      meld(heap1, heap2),
      meld(heap2, heap1)
    )
  }

  property("adding elements is commutative") = forAll { (heap: H, e1: Int, e2: Int) =>
    equal(
      insert(e1, insert(e2, heap)),
      insert(e2, insert(e1, heap))
    )
  }

  // Convert a priority queue to a list by retrieving its elements until queue is empty
  def asList(heap: H): List[Int] =
    if (isEmpty(heap)) Nil
    else findMin(heap) :: asList(deleteMin(heap))

  // Verify if the provided list is sorted
  def isSorted(list: List[Int]): Boolean = list == list.sorted

  // Are two heaps equal?
  def equal(heap1: H, heap2: H): Boolean = asList(heap1) == asList(heap2)

  // Create a heap from the provided list
  def mkHeap(list: Int*): H = {
    def loop(list: List[Int], heap: H): H = list match {
      case head :: tail => loop(tail, insert(head, heap))
      case Nil => heap
    }

    loop(list.toList, empty)
  }

  // priority heap generator
  lazy val genHeap: Gen[H] = for {
    elem <- arbitrary[A]
    heap <- oneOf(const(empty), genHeap)
  } yield insert(elem, heap)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)
}
