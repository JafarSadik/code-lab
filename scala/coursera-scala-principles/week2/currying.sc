// sum of elements; implemented with tail recursion and currying
def sum(f: Int => Int)(a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, acc + f(a))
  }

  loop(a, 0)
}

// sum elements 1..5
def id(x: Int): Int = x
sum(id)(1, 5)

// compose two new functions using sum
// notice that partial application (underscore) is required
val sumInts = sum(id) _
val sumSquares = sum((x: Int) => x * x) _

// apply the above functions for a range <1, 3>:
sumInts(1, 3)
sumSquares(1, 3)

// implementing product with map-reduce
def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
  if (a > b) zero
  else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))

def product(f: Int => Int)(n: Int): Int = mapReduce(f, (a, b) => a * b, 1)(1, n)
product(id)(5)


// currying with 3 parameter list
def max3(a: Double)(b: Double)(c: Double) = {
  import Math.max
  max(a, max(b, c))
}

max3(3)(3)(5)
//def max2(a: Double, b) = max3(a, b, b)
def max2 = max3(Double.MinValue)_
max2(1)(2)

// utilize currying to create a simple control structure
// call by name argument is important as we don't want to evaluate the expression when called
def times(repeat: Int)(block: =>Unit): Unit =
  1 to repeat foreach(x => block)

times(4) {
  println("...")
}