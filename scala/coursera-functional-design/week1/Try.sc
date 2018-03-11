import scala.util.control.NonFatal

/* Try resembles Option but instead of Some/None there is Success case
 with a value and Failure case that contains an exception. Try is used
 to pass results of computations that can fail between threads and processes.*/
abstract class Try[+T] {
  def flatMap[U](f: T => Try[U]): Try[U] = this match {
    case Success(x) => try f(x) catch { case NonFatal(exc) => Failure(exc)}
    case fail: Failure => fail
  }

  def map[U](f: T => U): Try[U] = this match {
    case Success(x) => Try(f(x))
    case fail: Failure => fail
  }
}

case class Success[T](x: T) extends Try[T]
case class Failure(exc: Throwable) extends Try[Nothing]

object Try {
  def apply[T](expr: => T): Try[T] =
    try Success(expr)
    catch {
      case NonFatal(e) => Failure(e)
    }
}

(Try(2 + 2) :: Try(2 / 0) :: Nil).map {
  case Success(result) => result
  case Failure(exc) => exc
}

// Try defines map and flatMap so we can make use of for expression:
for {
  result1 <- Try(2 * 2)
  result2 <- Try(2 + 2)
} yield (result1, result2)

for {
  result1 <- Try(2 * 2)
  result2 <- Try(2 / 0)
} yield (result1, result2)
