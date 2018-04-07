package calculator

object Polynomial {
  import Math._

  def computeDelta(a: Signal[Double], b: Signal[Double], c: Signal[Double]): Signal[Double] =
    Signal {
      pow(b(), 2) - 4 * a() * c()
    }

  def computeSolutions(a: Signal[Double], b: Signal[Double], c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] =
    Signal {
      val D = delta()
      if (D < 0) Set()
      else {
        val A = a()
        val B = b()

        Set(
          (-B + sqrt(D)) / (2 * A),
          (-B - sqrt(D)) / (2 * A)
        )
      }
    }
}
