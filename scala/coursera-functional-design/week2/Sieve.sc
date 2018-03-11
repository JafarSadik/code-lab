// Sieve of Eratosthenes is a simple algorithm for finding all prime numbers up to any given limit
//def from(n: Int): Stream[Int] = n #:: from(n + 1)
val naturalNumbers = Stream.from(2)

def sieve(s: Stream[Int] = naturalNumbers): Stream[Int] =
  s.head #:: sieve(s.tail filter(_ % s.head != 0))

sieve().take(100).toList