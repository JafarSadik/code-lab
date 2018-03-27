// Defines some basic logic circuits
object Circuits extends Gates with Probes {
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

  // Byte adder allows to add 2 bytes together. Built from eight full adders.
  def byteAdder(inputBus1: Bus, inputBus2: Bus, carryIn: Wire, outputBus: Bus, carryOut: Wire): Unit = {
    def wire = new Wire
    val overflows: List[Wire] = List(carryIn, wire, wire, wire, wire, wire, wire, wire, carryOut)

    for (idx <- inputBus1.wireIndices) {
      val cin = overflows(idx)
      val cout = overflows(idx + 1)
      fullAdder(inputBus1.wires(idx), inputBus2.wires(idx), cin, outputBus.wires(idx), cout)
    }
  }

  // 1 bit memory cell is capable of storing a single bit of information when set input is true
  def memoryCell(input: Wire, setFlag: Wire, output: Wire): Unit = {
    val a, b, c = new Wire
    nandGate(input, setFlag, a)
    nandGate(a, setFlag, b)
    nandGate(a, c, output)
    nandGate(output, b, c)
  }

  // 8 bit memory built from eight 1-bit memory cells
  def byteMemory(inputBus: Bus, setFlag: Wire, outputBus: Bus): Unit =
    for (idx <- inputBus.wireIndices) {
      memoryCell(inputBus.wires(idx), setFlag, outputBus.wires(idx))
    }
}
