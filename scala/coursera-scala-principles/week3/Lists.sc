import scala.annotation.tailrec

// A trait representing an ordered collection of elements of type T.
trait IntList {
  // Returns true if the list does not contain any elements
  def isEmpty: Boolean

  // Returns first element of the list
  def head: Int

  // Returns the list without its first element
  def tail: IntList

  // Returns n-th element of the list
  def get(index: Int): Int

  // Calculates list length
  def length: Int

  // Appends element to beginning of list
  def ::(e: Int): IntList

  // Concatenate two lists
  def :::(list: IntList): IntList
}

// Non-empty implementation of list
class ImmutableList(val head: Int, val tail: IntList) extends IntList {
  def isEmpty = false

  def get(index: Int): Int = if (index == 0) head else tail.get(index - 1)

  def length: Int = 1 + tail.length

  def ::(e: Int) = new ImmutableList(e, this)

  def :::(list: IntList): IntList =
    if (list.isEmpty) this
    else list.head :: list.tail ::: this

  override def toString = {
    @tailrec
    def concat(list: IntList, sb: StringBuilder = StringBuilder.newBuilder): String = {
      list match {
        case EmptyList => sb.toString()
        case _ => concat(list.tail, sb.append(", ").append(list.head))
      }
    }

    "[" + head + concat(this.tail) + "]"
  }
}

// Empty implementation of list
object EmptyList extends IntList {
  def ifEmpty: Boolean = true

  def tail = throw new NoSuchElementException("Nil.tail")

  def head: Nothing = throw new NoSuchElementException("Nil.head")

  def get(index: Int) = throw new IndexOutOfBoundsException("Nil.get")

  def isEmpty = true

  def length = 0

  def ::(e: Int): IntList = IntList(e)

  def :::(list: IntList) = list

  override def toString = "[]"
}

// Factory methods for IntList
object IntList {
  def apply(values: Int*): IntList =
    if (values.isEmpty) EmptyList
    else new ImmutableList(values.head, apply(values.tail: _*))
}

// empty list
val emptyList = EmptyList
emptyList.isEmpty
emptyList.length

// create a list with few elements
val list = IntList(1, 2, 3, 4, 5)
list.isEmpty
list.length
list.tail
list.get(4)

// create a list as a concatenation of its elements
1 :: 6 :: 7 :: EmptyList

// list concatenation
emptyList ::: emptyList
list ::: emptyList
(1 :: 2 :: 3 :: EmptyList) ::: (4 :: 5 :: 6 :: EmptyList)



