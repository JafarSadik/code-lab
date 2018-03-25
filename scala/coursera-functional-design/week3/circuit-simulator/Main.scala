import Circuits._

object Main {

  def main(args: Array[String]): Unit = args.toList match {
    case "1" :: Nil => halfAdderSimulation()
    case "2" :: Nil => fullAdderSimulation()
    case "3" :: Nil => memoryCellSimulation()
    case "4" :: Nil => byteMemorySimulation()
    case _ => displayUsage()
  }

  private def displayUsage(): Unit =
    println(
      """Select simulation to run:
        | [1] Half Adder
        | [2] Full Adder
        | [3] 1-Bit Memory
        | [4] 1-Byte Memory
      """.stripMargin)

  private def halfAdderSimulation(): Unit = {
    println("Half adder simulation")

    val in1, in2, sum, carryOut = new Wire
    halfAdder(in1, in2, sum, carryOut)
    probe("sum", sum)
    probe("carryOut", carryOut)

    println("*** 1 + 1 = {sum: 0, carry: 1}***")
    in1.setSignal(true)
    in2.setSignal(true)
    runSimulation()

    println("*** 1 + 0 = {sum: 1, carry: 0}***")
    in1.setSignal(true)
    in2.setSignal(false)
    runSimulation()
  }

  private def fullAdderSimulation(): Unit = {
    println("Full adder simulation")

    val in1, in2, sum, carryIn, carryOut = new Wire
    fullAdder(in1, in2, carryIn, sum, carryOut)
    probe("carryOut", carryOut)
    probe("sum", sum)

    println("*** 1 + 1, carry: 0 = {sum: 0, carry: 1} ***")
    in1.setSignal(true)
    in2.setSignal(true)
    carryIn.setSignal(false)
    runSimulation()

    println("*** 1 + 1, carry: 1 = {sum: 1, carry: 1} ***")
    in1.setSignal(true)
    in2.setSignal(true)
    carryIn.setSignal(true)
    runSimulation()

    println("*** 0 + 0, carry: 1 = {sum: 1, carry: 0} ***")
    in1.setSignal(false)
    in2.setSignal(false)
    carryIn.setSignal(true)
    runSimulation()
  }

  private def memoryCellSimulation(): Unit = {
    println("Memory cell simulation")

    val input, setFlag, output = new Wire
    memoryCell(input, setFlag, output)
    probe("output", output)

    println("*** in: 0, set: 1 -> out: 0 ***")
    input.setSignal(false)
    setFlag.setSignal(true)
    runSimulation()

    println("*** in: 0, set: 0 -> out: 0 ***")
    input.setSignal(false)
    setFlag.setSignal(false)
    runSimulation()

    println("*** in: 1, set: 0 -> out: 0 ***")
    input.setSignal(true)
    setFlag.setSignal(false)
    runSimulation()

    println("*** in: 1, set: 1 -> out: 1 ***")
    input.setSignal(true)
    setFlag.setSignal(true)
    runSimulation()

    println("*** in: 0, set: 0 -> out: 1 ***")
    input.setSignal(false)
    setFlag.setSignal(false)
    runSimulation()

    println("*** in: 0, set: 1 -> out: 0 ***")
    input.setSignal(false)
    setFlag.setSignal(true)
    runSimulation()

    println("*** in: 1, set: 1 -> out: 1 ***")
    input.setSignal(true)
    setFlag.setSignal(true)
    runSimulation()

    println("*** in: 0, set: 0 -> out: 1 ***")
    input.setSignal(false)
    setFlag.setSignal(false)
    runSimulation()
  }

  private def byteMemorySimulation(): Unit = {
    println("One byte memory simulation")

    val inputBus, outputBus = new Bus
    val setFlag = new Wire
    byteMemory(inputBus, setFlag, outputBus)
    probe("output bus", outputBus)

    println("*** in: 8, setFlag: 1 -> out: 8")
    inputBus.setSignal(8)
    setFlag.setSignal(true)
    runSimulation()

    println("*** in: 0, setFlag: 0 -> out: 8")
    inputBus.setSignal(0)
    setFlag.setSignal(false)
    runSimulation()

    println("*** in: 120, setFlag: 0 -> out: 8")
    inputBus.setSignal(120)
    setFlag.setSignal(false)
    runSimulation()

    println("*** in: 120, setFlag: 1 -> out: 120")
    inputBus.setSignal(120)
    setFlag.setSignal(true)
    runSimulation()
  }
}
