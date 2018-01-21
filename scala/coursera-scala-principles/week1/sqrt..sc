/* Calculates square root of a number using Newton's method.*/
def sqrt(x: Double): Double = {
  import scala.annotation.tailrec
  val estimationGuess = 1
  val accuracy = 1e-3

  /*Calculates square root recursively with tail call optimization.*/
  @tailrec
  def sqrtIter(estimation: Double): Double =
    if (isGoodEnough(estimation)) estimation
    else sqrtIter(improve(estimation))

  /*Is error of the estimation below required accuracy?*/
  def isGoodEnough(estimation: Double) =
    normalizedError(estimation) < accuracy

  /*Estimate improvement formula based on Newton's method*/
  def improve(estimation: Double) =
    (x / estimation + estimation) / 2

  /*Calculates normalized square root estimation error as |estimation ^ 2 - x| / x  */
  def normalizedError(estimation: Double) =
    abs(estimation * estimation - x) / x

  def abs(value: Double) = if (value < 0) -value else value

  /*Trigger calculation guessing estimation*/
  sqrtIter(estimationGuess)
}

sqrt(2)
sqrt(4)

// Does it work for very small and very large numbers?
sqrt(1e-6)
sqrt(1e60)