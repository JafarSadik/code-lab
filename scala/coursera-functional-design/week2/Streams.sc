// Streams are similar to lists but their tail is computed lazily. Because of this, a stream can be infinitely long.
// Only those elements requested are computed. Otherwise streams have the same performance characteristics as lists.

// Stream declaration and concatenation
Stream.empty // empty stream
Stream(1, 2, 3) // stream factory
(1 to 2000000).toStream // using Range to create a stream
Stream(1 to 200000)
1 #:: 2 #:: 3 #:: Stream.empty // with stream operators #:: and #:::
Stream(1, 2, 3) #::: Stream(4, 5, 6)
Stream.cons(1, Stream.empty)

// Stream factory methods
Stream.range(1, 2000)
Stream.range(1, 20000, 7)
Stream.from(start = 1000)
Stream.from(start = 1000, step = 10)

// A simple infinite stream defined with a given start number.
// It would fail with lists as tail is evaluated eagerly and would result in an infinite loop.
def allIntsAbove(n: Int): Stream[Int] = n #:: allIntsAbove(n + 1)
allIntsAbove(4).slice(10000, 10011).toList
allIntsAbove(1).map(_* 55).slice(10000, 10010).toList

// Calculating Fibonacci sequence with an infinite stream
def fib(a: Int = 1, b: Int = 1): Stream[Int] = a #:: fib(b, a + b)
fib() take 10 toList


// #:: operator can be used in pattern matching
val fibN1 #:: fibN2 #:: fibN3 #:: _ = fib().drop(10)
s"... $fibN1, $fibN2, $fibN3 ..."

val x1 #:: x2 #:: x3 #:: _ = (10000 to 1000000000).toStream.filter(_ % 2 == 0)


// Logistic map with an initial value 0.8 and parameter 3,9
// https://en.wikipedia.org/wiki/Logistic_map
Stream.iterate(0.8)(n => 3.9 * n * (1 - n))
  .take(30).foreach(println(_))


