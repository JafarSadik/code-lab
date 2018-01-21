package recfun

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(column: Int, row: Int): Int = {
    val triangleEdge = column == 0 || column == row

    if (triangleEdge) 1
    else {
      val left = pascal(column - 1, row - 1)
      val right = pascal(column, row - 1)
      left + right
    }
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def balanceRecur(chars: List[Char], depth: Int = 0): Int =
      if (chars == Nil || depth < 0) depth
      else chars.head match {
        case '(' => balanceRecur(chars.tail, depth + 1)
        case ')' => balanceRecur(chars.tail, depth - 1)
        case _ => balanceRecur(chars.tail, depth)
      }

    balanceRecur(chars) == 0
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = ???
}
