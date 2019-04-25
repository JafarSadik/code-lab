package tutorial.webapp

object TutorialApp {
  def main(args: Array[String]): Unit = {
    println("Hello world :)")
    println(
        List("abc", "def").flatMap(_.split("")).mkString(" ")
    )
  }
}