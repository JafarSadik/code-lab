// Non tail recursive implementation of factorial
def factorialNonTailRecursive(n: Long): Long =
  if (n == 0) 1 else n * factorialNonTailRecursive(n - 1)

factorialNonTailRecursive(4)
factorialNonTailRecursive(7)
factorialNonTailRecursive(11)
factorialNonTailRecursive(16)
factorialNonTailRecursive(25)

// Tail recursive implementation of factorial
import scala.annotation.tailrec

def factorialTailRecursive(n: Long): Long = {
  @tailrec
  def factorial(acc: Long, n: Long): Long =
    if (n == 0) acc
    else factorial(n - 1, n * acc)

  factorial(n, 1)
}

factorialTailRecursive(4)
factorialTailRecursive(7)
factorialTailRecursive(11)
factorialTailRecursive(16)
factorialTailRecursive(25)
factorialTailRecursive(35)

