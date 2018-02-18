import java.time.LocalDate
import java.time.temporal.ChronoUnit

// Case class declaration
abstract class Account(memberSince: LocalDate) {
  def reduceToBasicAccount = BasicAccount(memberSince)
  def upgradeToPremiumAccount(validUntil: LocalDate) = PremiumAccount(memberSince, validUntil)
}

case class PremiumAccount(memberSince: LocalDate, validUntil: LocalDate) extends Account(memberSince)
case class BasicAccount(memberSince: LocalDate) extends Account(memberSince)
case class User(id: Long, login: String, email: String, account: Account)

// Compiler automatically generates many methods including: toString, hashCode, equals, copy.
// Using reflection to iterate over declarations:
import scala.reflect.runtime.universe._
typeOf[User].decls.foreach(println(_))

// Compiler automatically generates a companion object with a factory method 'apply'
def year(year: Int) = LocalDate.of(year, 1, 1)
val nextYear = LocalDate.now().plus(1, ChronoUnit.YEARS)
var user1 = User(15, "user1", "user1@gmail.com", PremiumAccount(year(2014), nextYear))
val user2 = User(16, "user2", "user2@gmail.com", BasicAccount(year(2016)))

// Class parameters become properties, no need to use val in case class declaration
user1.id
user1.login
user1.email
user1.account

// Natural toString implementation
println(user1)
println(user2)

// Generated hashCode and equals methods work great on hierarchical data structures
assert(user1.hashCode() == user1.copy().hashCode())
assert(user1 == user1.copy())
assert(user1 != user2)

// It's possible to create a mutable case class
case class MutableUser(var id: Long, var login: String, var email: String, var account: Account)
val mutableUser = MutableUser(0, null, null, null)
mutableUser.email = "mutableUser@gmail.com"
mutableUser.login = "mutableUser"

// ... but it should be avoided. Use copy() method that creates a modified object:
val basicAccount = user1.account.reduceToBasicAccount
user1.copy(account = basicAccount)

val premiumAccount = user2.account.upgradeToPremiumAccount(validUntil = nextYear)
user2.copy(account = premiumAccount)

// Case classes especially useful for pattern matching
val users = List(user1, user2)
for (user <- users) yield user match {
  case User(_, _, email, BasicAccount(_)) => s"$email:basic account"
  case User(_, _, email, PremiumAccount(_, date)) => s"$email: premium account valid until $date"
}


