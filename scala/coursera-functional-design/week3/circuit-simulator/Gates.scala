// Logic gates AND, OR, NOT, NAND
trait Gates extends Simulation with Parameters {

  // Wires logical gates and circuits together
  class Wire {
    private var sigVal = false
    private var actions: List[Action] = Nil

    // Get the current signal on the wire
    def getSignal: Boolean = sigVal

    // Add action performed after wire signal changed
    def addAction(action: Action): Unit = {
      actions = action :: actions
      action()
    }

    // Change wire signal
    def setSignal(signal: Boolean): Unit =
      if (signal != sigVal) {
        sigVal = signal
        for (action <- actions) action()
      }
  }

  // 8 bit bus wire
  class Bus {
    val wireIndices: Range = 0 until 8
    val wires: Array[Wire] = wireIndices.map(_ => new Wire).toArray

    def setSignal(signal: Byte): Unit =
      for (idx <- wireIndices) wires(idx).setSignal(((signal >> idx) & 1) == 1)

    def getSignal: Byte = {
      var busSignal: Int = 0
      for (idx <- wireIndices) {
        val wireSignal = if (wires(idx).getSignal) 1 else 0
        busSignal = busSignal | (wireSignal << idx)
      }
      busSignal.toByte
    }
  }

  // Allows to define any kind of unary gate
  def unaryGate(input: Wire, output: Wire, gateDelay: Int)(f: (Boolean) => Boolean): Unit = {
    def gateAction(): Unit = {
      val signal = input.getSignal
      afterDelay(gateDelay) {
        output setSignal f(signal)
      }
    }

    input addAction gateAction
  }

  // Allows to define any kind of binary gate
  def binaryGate(input1: Wire, input2: Wire, output: Wire, gateDelay: Int)(f: (Boolean, Boolean) => Boolean): Unit = {
    def gateAction(): Unit = {
      val signal1 = input1.getSignal
      val signal2 = input2.getSignal
      afterDelay(gateDelay) {
        output setSignal f(signal1, signal2)
      }
    }

    input1 addAction gateAction
    input2 addAction gateAction
  }

  // One unary (NOT) and three binary (AND, OR, NAND) gates
  def inverter(input: Wire, output: Wire): Unit = unaryGate(input, output, InverterDelay)(!_)

  def andGate(input1: Wire, input2: Wire, output: Wire): Unit = binaryGate(input1, input2, output, AndGateDelay)(_ & _)

  def orGate(input1: Wire, input2: Wire, output: Wire): Unit = binaryGate(input1, input2, output, OrGateDelay)(_ | _)

  def nandGate(input1: Wire, input2: Wire, output: Wire): Unit = binaryGate(input1, input2, output, NandGateDelay)(!_ | !_)

  // Wire probe
  def probe(name: String, wire: Wire): Unit = {
    def probeAction(): Unit = {
      println(s"|$name| time: $currentTime value: ${wire.getSignal}")
    }
    wire addAction probeAction
  }

  // Bus probe
  def probe(name: String, bus: Bus): Unit = {
    def probeAction(): Unit = {
      println(s"|$name| time: $currentTime signal: ${bus.getSignal}")
    }

    for (wire <- bus.wires) {
      wire addAction probeAction
    }
  }
}
