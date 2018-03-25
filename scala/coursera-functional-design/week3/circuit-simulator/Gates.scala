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

  // NOT gate
  def inverter(input: Wire, output: Wire): Unit = {
    def inverterAction(): Unit = {
      val inputSignal = input.getSignal
      afterDelay(InverterDelay) {
        output setSignal !inputSignal
      }
    }

    input addAction inverterAction
  }

  // AND gate
  def andGate(input1: Wire, input2: Wire, output: Wire): Unit = {
    def andGateAction(): Unit = {
      val signal1 = input1.getSignal
      val signal2 = input2.getSignal
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
      val signal1 = input1.getSignal
      val signal2 = input2.getSignal
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
      var signal1 = input1.getSignal
      var signal2 = input2.getSignal
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
}
