// Smart casts
// The Kotlin compiler is smart enough to perform type casts automatically in most cases.
// When you use something like 'is' keyword, the compiler will know the type later based on the context.
import SmartCast.JSON.*

sealed class JSON {
    fun show(): String = when (this) {
        is JNull -> "null"

        is JNum -> "${num}"

        is JStr -> "\"${str}\""

        is JSeq -> seq.joinToString(prefix = "[", separator = ", ", postfix = "]") { it.show() }

        is JObj -> bindings.map { it.key + ":" + it.value.show() }
                .joinToString(prefix = "{", separator = ", ", postfix = "}")
    }

    override fun toString(): String = show()

    object JNull : JSON()
    data class JNum(val num: Long) : JSON()
    data class JStr(val str: String) : JSON()
    data class JSeq(val seq: List<JSON>) : JSON()
    data class JObj(val bindings: Map<String, JSON>) : JSON()
}

check(JNull.show() == "null")
check(JNum(321).show() == "321")
check(JStr("good morning!").show() == "\"good morning!\"")
check(JSeq(emptyList()).show() == "[]")
check(JSeq(listOf(JNum(1))).show() == "[1]")
check(JSeq(listOf(JNum(1), JNum(2), JStr("three"))).show() == "[1, 2, \"three\"]")
check(JObj(emptyMap()).show() == "{}")

val contact = JObj(mapOf(
        "firstName" to JStr("John"),
        "address" to JObj(mapOf(
                "streetAddress" to JStr("21 Old Street"),
                "postalCode" to JNum(2123)
        )),
        "phoneNumbers" to JSeq(listOf(
                JStr("212 555 4321"),
                JStr("212 555 4322")
        ))))

check(contact.show() == "{firstName:\"John\", address:{streetAddress:\"21 Old Street\", postalCode:2123}, phoneNumbers:[\"212 555 4321\", \"212 555 4322\"]}")

