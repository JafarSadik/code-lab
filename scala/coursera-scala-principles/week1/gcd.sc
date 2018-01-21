import scala.annotation.tailrec

/* Tail recursive implementation of greatest common divisor*/
@tailrec
def gcd(a: Int, b: Int): Int =
  if (b == 0) a else gcd(b, a % b)

gcd(12, 4)
gcd(12, 24)
gcd(12, 30)
gcd(1000, 1001)

