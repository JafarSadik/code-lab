object FixedPoint {
  val tolerance = 0.0001
  def isCloseEnough(x: Double, y: Double) =
    Math.abs((x - y) / x) / x < tolerance

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)
    }
    iterate(firstGuess)
  }
  fixedPoint(x => 1 + x / 2)(1)

  // square root is a fixed point of the function y = x / y
  // sqrt(x) = y =>  x * x = y => y = x / y
  // Here we need to average two consecutive values y, x/y in order to achieve convergence.
  def sqrt(x: Double) = fixedPoint(y => (y + x / y) / 2.0)(1.0)
  sqrt(2)
  sqrt(4)
}

