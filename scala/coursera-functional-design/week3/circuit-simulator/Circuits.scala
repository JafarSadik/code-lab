// Defines some basic logic circuits
object Circuits extends Gates {
  // Half adder sums two bits a and b. Result is a sum and bit carry flag
  def halfAdder(a: Wire, b: Wire, sum: Wire, carry: Wire): Unit = {
    val d, e = new Wire
    orGate(a, b, d)
    andGate(a, b, carry)
    inverter(carry, e)
    andGate(d, e, sum)
  }

  // Full adder sums three bits a, b and carry flag from the previous addition.
  def fullAdder(a: Wire, b: Wire, carryIn: Wire, sum: Wire, carryOut: Wire): Unit = {
    val s, c1, c2 = new Wire
    halfAdder(b, carryIn, s, c1)
    halfAdder(a, s, sum, c2)
    orGate(c1, c2, carryOut)
  }

  // 1 bit memory cell is capable of storing a single bit of information when set input is true
  def memoryCell(input: Wire, set: Wire, output: Wire): Unit = {
    val a, b, c = new Wire
    nandGate(input, set, a)
    nandGate(a, set, b)
    nandGate(a, c, output)
    nandGate(output, b, c)
  }
}
