// In Scala every concrete type is the type of some class or trait.The function type is no exception.
// A type like JBinding => String is just a shorthand for scala.Function1[JBinding, String]

// Subclassing functions: one nice aspect of functions being traits is that we can subclass the function type.
trait MultiMap[Key, Value] extends (Key => List[Value])

case class Sequence[Value](values: Value*) extends (Int => Value) {
  override def apply(index: Int) = values(index)
}

Sequence(2, 4, 6, 8)(3)