// Classes
// The class declaration consists of the class name, parameters and the class body surrounded by curly braces.
// Both the header and the body are optional.
class Something;

class Customer(val id: Int, val name: String, val email: String)

class EmailMessage(val title: String, val content: String)

class Contract(val customer: Customer) {
    fun send(message: EmailMessage) {
        println("""Sending email to ${customer.name}<${customer.email}>
            |  ${message.title}
            |  ${message.content}""".trimMargin())
    }
}

val adam = Customer(1, "Adam", "adam@gmail.com")
val contract = Contract(adam)
contract.send(EmailMessage("Important notification", "Please restart your password immediately"))

// Generics
// Generics classes and functions increase code reusability through type parametrization.
// Below example defines a generic class MutableStack<E> where E is called a generic type parameter.
// The implementation makes heavy use of Kotlin's shorthand syntax for functions that can be defined in a single expression.
class MutableStack<E>(vararg items: E) {
    private val elements = items.toMutableList();

    fun push(element: E) = elements.add(element)

    fun peek(): E = elements.last()

    fun pop(): E = elements.removeAt(elements.size - 1)

    fun isEmpty() = elements.isEmpty()

    fun size() = elements.size
}

// It's also possible to generalize functions
fun <E> mutableStackOf(vararg elements: E) = MutableStack(*elements)

check(mutableStackOf<Int>().isEmpty()) // Here, the type cannot be inferred so we need to provide it
check(mutableStackOf(123).size() == 1)
check(mutableStackOf(123).pop() == 123)

val stack = mutableStackOf<String>()
stack.push("never")
stack.push("underestimate")
stack.push("the")
stack.push("power")
check(stack.size() == 4 && stack.peek() == "power")
stack.push("of")
stack.push("stupid")
stack.push("people")
stack.push("in")
stack.push("a")
stack.push("large")
stack.push("groups")
check(!stack.isEmpty())
check(stack.pop() == "groups" && stack.pop() == "large" && stack.pop() == "a" && stack.pop() == "in")

// Inheritance
// Inheritance is a mechanism where a new class is derived from an existing class retaining a similar implementation.
// Kotlin classes and methods are final by default. We need to use 'open' modifier in order to allow inheritance or overriding.
open class BaseLayout {
    open fun display(): List<Pair<String, String>> {
        return arrayListOf(Pair("window1", "10x20"), Pair("window2", "30x30"));
    }
}

class OverlayLayout : BaseLayout() {
    override fun display(): List<Pair<String, String>> {
        return arrayListOf(Pair("menu", "10x10"), Pair("window1", "10x20"), Pair("window2", "30x30"));
    }
}

check(BaseLayout().display() == listOf(Pair("window1", "10x20"), Pair("window2", "30x30")))
check(OverlayLayout().display() == listOf(Pair("menu", "10x10"), Pair("window1", "10x20"), Pair("window2", "30x30")))

// When a superclass doesn't provide a default constructor, arguments must be provided by a subclass.
// It's also possible to bypass arguments from subclass constructor.
open class Tiger(val origin: String) {
    fun sayHello(): String {
        return "A tiger from $origin says grrhh!";
    }
}

class SiberianTiger(direction: String = "Northern") : Tiger("$direction Siberia")
check(SiberianTiger().sayHello() == "A tiger from Northern Siberia says grrhh!")
check(SiberianTiger("Southern").sayHello() == "A tiger from Southern Siberia says grrhh!")
check(SiberianTiger("Western").sayHello() == "A tiger from Western Siberia says grrhh!")


