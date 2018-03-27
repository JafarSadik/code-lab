trait Wires extends Simulation {
  // Links logical gates and circuits together
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

  // 8 bit bus is built from 8 wires
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
}
