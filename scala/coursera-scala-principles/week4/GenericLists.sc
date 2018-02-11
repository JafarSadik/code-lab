import scala.annotation.tailrec

// A trait representing an ordered collection of elements.
trait MyList[+T] {
  // Returns true if the list does not contain any elements
  def isEmpty: Boolean

  // Returns first element of the list
  def head: T

  // Returns the list without its first element
  def tail: MyList[T]

  // Returns n-th element of the list
  def get(index: Int): T

  // Calculates list length
  def length: Int

  // Appends element to beginning of list
  def ::[R >: T](e: R): MyList[R]

  // Concatenate two lists
  def :::[R >: T](list: MyList[R]): MyList[R]
}

// Implementation of non-empty immutable list
class ImmutableList[T](val head: T, val tail: MyList[T]) extends MyList[T] {
  def isEmpty = false

  def get(index: Int): T = if (index == 0) head else tail.get(index - 1)

  def length: Int = 1 + tail.length

  def ::[R >: T](e: R) = new ImmutableList(e, this)

  def :::[R >: T](list: MyList[R]): MyList[R] =
    if (list.isEmpty) this
    else list.head :: list.tail ::: this

  override def toString = {
    @tailrec
    def concat(list: MyList[T], sb: StringBuilder = StringBuilder.newBuilder): String = {
      list match {
        case EmptyList => sb.toString()
        case _ => concat(list.tail, sb.append(", ").append(list.head))
      }
    }

    "[" + head + concat(this.tail) + "]"
  }
}

// Implementation of an empty immutable list
class EmptyList extends MyList[Nothing] {
  def ifEmpty: Boolean = true

  def tail = throw new NoSuchElementException("Nil.tail")

  def head: Nothing = throw new NoSuchElementException("Nil.head")

  def get(index: Int) = throw new IndexOutOfBoundsException("Nil.get")

  def isEmpty = true

  def length = 0

  def ::[R](e: R): MyList[R] = MyList(e)

  def :::[R](list: MyList[R]) = list

  override def toString = "[]"
}

object EmptyList extends EmptyList {
  def apply() = new EmptyList()
}

// Factory methods for MyList
object MyList {
  def apply[T](values: T*): MyList[T] =
    if (values.isEmpty) EmptyList
    else new ImmutableList(values.head, apply(values.tail: _*))
}

// empty list
val emptyList = EmptyList
emptyList.isEmpty
emptyList.length

// create a list with a few elements
val list = MyList("scala", "has", "an", "advanced", "type", "system")
list.isEmpty
list.length
list.tail
list.get(4)

// create a list as a concatenation of its elements
case class Token(token: String)
Token("X15") :: Token("X16") :: Token("X17") :: EmptyList

// concatenating lists
emptyList ::: emptyList
list ::: emptyList
('a' :: 'b' :: 'c' :: EmptyList) ::: (4 :: 5 :: 6 :: EmptyList)

abstract class Parent {}
case class Child1() extends Parent
case class Child2() extends Parent
Child1() :: (Child1() :: EmptyList()) ::: (Child2() :: EmptyList())

