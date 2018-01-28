/**
  * An immutable implementation of rational numbers
  *
  * @param numerator   any positive or negative integer
  * @param denominator positive, non-zero integer
  */
class Rational(numerator: Int, denominator: Int = 1) extends Ordered[Rational] {

  import Rational._

  require(denominator > 0, "denominator must be positive")

  val numer: Int = numerator / gcd
  val denom: Int = denominator / gcd

  def ==(that: Rational): Boolean = compare(that) == 0

  def +(that: Rational): Rational = Rational(
    numer * that.denom + that.numer * denom,
    denom * that.denom
  )

  def *(that: Rational): Rational = Rational(
    numer * that.numer,
    denom * that.denom
  )

  def /(that: Rational): Rational = this * that.inverse

  def inverse: Rational =
    if (this == zero) zero
    else if (this >= zero) Rational(denom, numer)
    else Rational(-denom, -numer)

  def -(that: Rational): Rational = this + -that

  def unary_- = Rational(-numer, denom)

  def max(that: Rational): Rational =
    if (numer * that.denom > that.numer * denom) this
    else that

  def min(that: Rational): Rational =
    if (numer * that.denom < that.numer * denom) this
    else that

  override def equals(any: scala.Any): Boolean =
    any match {
      case that: Rational => this == that
      case that: Int => this == Rational(that)
      case _ => false
    }

  def abs: Rational = Rational(abs(numer), denom)

  override def compare(that: Rational): Int = numer * that.denom - that.numer * denom

  override def toString: String =
    if (denom == 1) numer.toString
    else s"$numer/$denom"

  private def abs(x: Int) = if (x > 0) x else -x

  private lazy val gcd = {
    def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

    gcd(abs(numerator), denominator)
  }
}

object Rational {
  val zero: Rational = Rational(0)

  def apply(numerator: Int, denominator: Int = 1) =
    new Rational(numerator, denominator)

  implicit def IntToRational(number: Int): Rational = Rational(number)
}
