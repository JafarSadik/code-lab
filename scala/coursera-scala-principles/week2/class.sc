// Simple class definition
class MyClass(x: Int, y: Int) { // defines a new type MyClass with a default constructor
  require(argsValid(x, y), "Argument y must be positive") // precondition triggering an illegalArgumentException if not met
  def this(x: Int) = this(x, 1) // auxiliary constructor
  def getX = x // public method computed every time it's called
  def getY = y

  val sum = x + y // value computed only once
  private def argsValid(x: Int, y: Int) = y > 0 // private method
  override def toString = s"MyClass($x, $y)"
}

// Instantiate class using the the default an auxiliary  constructors
new MyClass(2, 3)
new MyClass(2)

// Check what happens on attempt to instantiate class with zero in denominator
def expectIllegalArgumentException(expression: => Unit) =
  try {
    expression
    false
  }
  catch {
    case e: IllegalArgumentException => true
    case _: Throwable => false
  }

assert(expectIllegalArgumentException(new MyClass(1, 0)))

// MyClass companion object with a single factory method
object MyClass {
  def apply(x: Int, y: Int = 1): MyClass = new MyClass(x, y)
}

MyClass(2, 3)
MyClass(2)
