// Logic gates AND, OR, NOT, NAND
trait Gates extends Simulation with Wires with Parameters {
  // One unary (NOT) and three binary (AND, OR, NAND) gates
  def inverter(input: Wire, output: Wire): Unit = unaryGate(input, output, InverterDelay)(!_)

  def andGate(input1: Wire, input2: Wire, output: Wire): Unit = binaryGate(input1, input2, output, AndGateDelay)(_ & _)

  def orGate(input1: Wire, input2: Wire, output: Wire): Unit = binaryGate(input1, input2, output, OrGateDelay)(_ | _)

  def nandGate(input1: Wire, input2: Wire, output: Wire): Unit = binaryGate(input1, input2, output, NandGateDelay)(!_ | !_)

  // Allows to define any kind of unary gate
  private def unaryGate(input: Wire, output: Wire, gateDelay: Int)(f: (Boolean) => Boolean): Unit = {
    input.addAction(() => afterDelay(gateDelay) {
      output setSignal f(input.getSignal)
    })
  }

  // Allows to define any kind of binary gate
  private def binaryGate(input1: Wire, input2: Wire, output: Wire, gateDelay: Int)(f: (Boolean, Boolean) => Boolean): Unit = {
    def gateAction(): Unit = afterDelay(gateDelay) {
      output setSignal f(input1.getSignal, input2.getSignal)
    }

    input1 addAction gateAction
    input2 addAction gateAction
  }
}
