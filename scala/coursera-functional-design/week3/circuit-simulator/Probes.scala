trait Probes extends Simulation with Wires{
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
