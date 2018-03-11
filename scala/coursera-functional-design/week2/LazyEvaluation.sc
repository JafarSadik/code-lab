// Lazy evaluation scheme delays evaluation of expression to its first application.
// It's very powerful principle as it avoids both unnecessary and repeated computations.

// by-value, by-name and lazy parameters
def expr = {
  val x = { print("x"); 1}      //called by-value; one-time, eager evaluation
  lazy val y = { print("y"); 2} //lazy evaluation; one-time, lazy evaluation
  def z = {print("z"); 3}       //called by-name; evaluated whenever it's used

  z + y + x + z + y + x
}
expr


// accepts single parameter passed by-name
def calc(param: => Int): Int = {
  lazy val result = param // once computed, param will be reused
  List.range(1, 100).map(_ + result).sum
}
// this would take a long time to execute if not 'lazy val'
calc({
  //sleep simulates an expensive operation
  Thread.sleep(500)
  10
})

