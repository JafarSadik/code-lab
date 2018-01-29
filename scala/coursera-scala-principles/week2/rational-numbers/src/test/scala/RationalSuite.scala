import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class RationalSuite extends FlatSpec with Matchers {
  it should "support number addition" in {
    Rational(5, 11) + Rational(6, 11) should be(Rational(1))
    Rational(10) + Rational(12) should be(Rational(22))
    Rational(3, 2) + Rational(3, 2) should be(Rational(3))
    Rational(-3, 2) + Rational(3, 2) should be(Rational(0))
  }

  it should "support number subtraction" in {
    Rational(5, 11) - Rational(6, 11) should be(Rational(-1, 11))
    Rational(10) - Rational(12) should be(Rational(-2))
    Rational(3, 2) - Rational(3, 2) should be(Rational(0))
  }

  it should "support unary minus operator" in {
    -Rational(10) should be(Rational(-10))
    -Rational(4, 2) should be(Rational(-2))
    -Rational(5, 3) should be(Rational(-5, 3))
  }

  it should "support multiplication operator" in {
    Rational(1) * Rational(5) should be(Rational(5))
    Rational(2, 2) * Rational(10, 2) should be(Rational(5))
    Rational(11, 3) * Rational(3, 11) should be(Rational(1))
    Rational(11, 3) * Rational(11) should be(Rational(121, 3))
  }

  it should "support division operator" in {
    Rational(1) / Rational(3) should be(Rational(1, 3))
    Rational(100) / Rational(100) should be(Rational(1))
    Rational(15) / Rational(3) should be(Rational(5))
    Rational(0) / Rational(10) should be(Rational(0))
    Rational(-5) / Rational(-10) should be(Rational(1, 2))
  }

  "comparison operators such as <, >, <=, >=" should "be supported" in {
    Rational(1) should be > Rational(0)
    Rational(2, 3) should be > Rational(1, 3)
    Rational(10, 2) should be >= Rational(10, 2)
    Rational(5, 2) should be <= Rational(5, 2)
    Rational(4, 2) should be < Rational(5, 2)
  }

  "abs" should "result in positive number" in {
    Rational(0).abs should be(Rational(0))
    Rational(1).abs should be(Rational(1))
    Rational(1, 2).abs should be(Rational(1, 2))
    Rational(-1).abs should be(Rational(1))
    Rational(-1, 2).abs should be(Rational(1, 2))
  }

  "max" should "return maximum of two numbers" in {
    Rational(1) max Rational(4) should be(Rational(4))
    Rational(2) max Rational(1) should be(Rational(2))
    Rational(3, 2) max Rational(4, 2) should be(Rational(4, 2))
  }

  "min" should "return minimum of two numbers" in {
    Rational(1) min Rational(4) should be(Rational(1))
    Rational(2) min Rational(1) should be(Rational(1))
    Rational(3, 2) min Rational(4, 2) should be(Rational(3, 2))
  }

  "inverse" should "result in inversed number" in {
    Rational(0).inverse should be(Rational(0))
    Rational(1).inverse should be(Rational(1))
    Rational(1, 3).inverse should be(Rational(3))
    Rational(3).inverse should be(Rational(1, 3))
    Rational(10, 2).inverse should be(Rational(1, 5))
    Rational(-1, 2).inverse should be(Rational(-2))
    Rational(-2, 5).inverse should be(Rational(-5, 2))
  }

  "rational class" should "support object equality" in {
    Rational(1) should equal(Rational(1))
    Rational(2, 2) should equal(Rational(1))
    Rational(3) should not equal Rational(1)
  }

  "rational class" should "support == operator" in {
    assert(Rational(1) == Rational(1))
    assert(Rational(2, 2) == Rational(1))
    assert(Rational(2) != Rational(1))
  }

  "toString" should "print fractional number" in {
    Rational(3, 2).toString should be("3/2")
    Rational(6, 4).toString should be("3/2")
    Rational(11, 15).toString should be("11/15")
  }

  "toString" should "print number when denominator is one" in {
    Rational(1).toString should be("1")
    Rational(2, 1).toString should be("2")
    Rational(4, 2).toString should be("2")
    Rational(100, 25).toString should be("4")
  }

  it should "support implicit conversion from Int" in {
    1 + Rational(1) should be(Rational(2))
    Rational(3, 2) + 1 should be(Rational(5, 2))
  }

  it should "produce IllegalArgumentException for zero denominator" in {
    an[IllegalArgumentException] should be thrownBy {
      Rational(2, 0)
    }
  }

  it should "produce IllegalArgumentException for negative denominator" in {
    an[IllegalArgumentException] should be thrownBy {
      Rational(2, -1)
    }
  }
}
