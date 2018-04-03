// Tracing evaluation of a simple monad
case class BasicMonad[A](value: A) extends Traceable[A] {
  def map[B](f: A => B) = new BasicMonad[B](f(value))
  def flatMap[B](f: A => BasicMonad[B]) = f(value)
  override def toString: String = value.toString
}

for {
  a <- BasicMonad(5) trace()
  b <- BasicMonad(6) trace()
  c <- BasicMonad(2) trace()
} yield ?(a) + ?(b) * ?(c)

// Prints type information associated with a given value
def ?[T](value: =>T) = {
  println(s": $value (${value.getClass})")
  value
}

// A trait that extends BasicMonad with execution tracing capability
trait Traceable[A] {
  basicMonad: BasicMonad[A] =>
  def trace(): Trace[BasicMonad[A]] = Trace(basicMonad)

  case class Trace[Monad <: BasicMonad[A]](target: Monad) {
    def map[B](f: A => B) = trace(s"map($f($target))", target.map(f))
    def flatMap[B](f: A => BasicMonad[B]) = trace(s"flatMap$f)($target)", target.flatMap(f))
    override def toString = target.toString
    private def trace[T](method: String, block: =>T): T = {
      println(s": before $method")
      val result = block
      println(s": after $method")
      result
    }
  }
}
