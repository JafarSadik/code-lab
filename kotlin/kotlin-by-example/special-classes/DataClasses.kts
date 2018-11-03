// Data classes make it easy to declare classes that are used to store values.
// They automatically generate toString, equals, hashCode, copy and componentN
data class User(val id: Long, val name: String)

val user1 = User(1, "Alex")
val user2 = User(1, "Alex")
val user3 = User(2, "Bob")

// toString()
check(user1.toString() == "User(id=1, name=Alex)")

// equals() and hashCode()
check(user1.hashCode() == user2.hashCode())
check(user1 == user2 && user1 != user3)

// copy()
val user4 = user1.copy(name = "Steven")
check(user4.id == 1L && user4.name == "Steven")

// componentN()
check(user1.component1() == 1L && user1.component2() == "Alex")
