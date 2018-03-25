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

  // NOT gate
  def inverter(input: Wire, output: Wire): Unit = {
    def inverterAction(): Unit = {
      lazy val inputSignal = input.getSignal
      afterDelay(InverterDelay) {
        output setSignal !inputSignal
      }
    }

    input addAction inverterAction
  }

  // AND gate
  def andGate(input1: Wire, input2: Wire, output: Wire): Unit = {
    def andGateAction(): Unit = {
      lazy val signal1 = input1.getSignal
      lazy val signal2 = input2.getSignal
      afterDelay(AndGateDelay) {
        output setSignal signal1 & signal2
      }
    }

    input1 addAction andGateAction
    input2 addAction andGateAction
  }

  // NAND gate
  def nandGate(input1: Wire, input2: Wire, output: Wire): Unit = {
    def nandGateAction(): Unit = {
      lazy val signal1 = input1.getSignal
      lazy val signal2 = input2.getSignal
      afterDelay(AndGateDelay) {
        output setSignal !(signal1 & signal2)
      }
    }

    input1 addAction nandGateAction
    input2 addAction nandGateAction
  }

  // OR gate
  def orGate(input1: Wire, input2: Wire, output: Wire): Unit = {
    def orGateAction(): Unit = {
      lazy val signal1 = input1.getSignal
      lazy val signal2 = input2.getSignal
      afterDelay(OrGateDelay) {
        output setSignal signal1 | signal2
      }
    }

    input1 addAction orGateAction
    input2 addAction orGateAction
  }

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
