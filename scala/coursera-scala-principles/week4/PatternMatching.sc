import java.io.IOException
import java.time.LocalDate

import scala.util.Random

// Pattern matching with constant pattern
val rand: Int = Random.nextInt(5)
rand match {
  case 0 => "zero"
  case 1 => "one"
  case 2 => "two"
  case _ => "many"
}

// Pattern matching with wildcard 'catch all' pattern
val result = "..."
result match {
  case "..." => result
  case _ => "unhandled case"
}

// Pattern matching with typed pattern
val v: Any = if (rand == 0) rand.toString else if (rand < 3) rand else (rand, rand * 2)
v match {
  case s: String => s"string, value: $s, length: $s.length  "
  case n: Int => s"number, value: $n"
  case (a, b) => s"a tuple, ($a, $b)"
}

// Pattern matching with lists and pattern guards
def checkList(list: List[Any]): String = list match {
  case Nil => "empty list"
  case l if l.size > 8 => "a very long list"
  case List(e) => s"single element list containing just '$e'"
  case List(_, _, l: List[Int], _*) => s"list contains $l as its 3rd element"
  case List(e1, e2, _*) => s"list contains at least two elements '$e1' and '$e2'"
}

checkList(Nil)
checkList(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
checkList(List(10))
checkList(List("abc", 13, 20, 40))
checkList(List(1, 2, List(1, 2, 3)))

// Pattern matching with case classes and constructor patterns
abstract class Notification
case class Email(sender: String, title: String, body: String) extends Notification
case class SMS(caller: String, message: String) extends Notification

def showNotification(notification: Notification): String = notification match {
  case Email(email, title, _) => s"You got an email from $email with title $title"
  case SMS(number, message) => s"You got an SMS from $number. Message: $message"
}

showNotification(Email("servermonitor@gmail.com", "Backend server down!", "Details: ..."))
showNotification(SMS("+44123456789", "Congratulations on your new job!"))

// Use of pattern matching to decompose a tuple during initialization
val (v1, v2, (v3, v4, v5)) = (1, 2, (3, 4, 5))

// Use of pattern matching to decompose a list during initialization
val element1 :: element2 :: tail = List(1, 2, 3, 4, 5, 6)

// Use of pattern matching to decompose a case class during initialization
case class PremiumAccount(memberSince: LocalDate, validUntil: LocalDate)

def year(year: Int) = LocalDate.of(year, 1, 1)
val PremiumAccount(memberSince, validUntil) = PremiumAccount(year(2014), year(2020))

// Use of pattern matching in function definition
def matchTest(x: Int): String = x match {
  case 0 => "zero"
  case 1 => "one"
  case 2 => "two"
  case _ => "many"
}
matchTest(rand)

// Pattern matching used to match exceptions
try {
  if (rand <= 1) throw new IOException("Connection broken")
  else if (rand <= 3) throw new RuntimeException("Some business exception")
  else throw new Error("Failed to migrate database")
}
catch {
  case io: IOException => println("IOException: " + io.getMessage)
  case re: RuntimeException => println("Runtime exception: " + re.getMessage)
  case e => println("Unknown exception: " + e)
}