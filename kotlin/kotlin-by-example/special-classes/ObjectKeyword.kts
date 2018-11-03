import java.util.concurrent.atomic.AtomicLong
import java.util.function.*


// To create an object of an anonymous class that extends from some type(s) we write:
val tooLong = object : Predicate<String> {
    override fun test(str: String): Boolean = str.length > 10
}
check(!tooLong.test(""))
check(!tooLong.test("abcdef"))
check(tooLong.test("abcdefghijklmn"))


// An example for object that extends multiple types
abstract class MouseClickHandler(window: String) {
    public val window: String = window
    public abstract fun mouseDown(x: Int, y: Int)
}

interface Scrollable {
    fun onScroll(scroll: Int)
}

val handler: Any = object : MouseClickHandler("overlay5"), Scrollable {
    override fun mouseDown(x: Int, y: Int) {
        println("mouse down for window '$window' at ($x, $y)")
    }

    override fun onScroll(scroll: Int) {
        println("scrolled by $scroll")
    }
}

// Use of type casting to invoke onScroll and mouseDown
(handler as Scrollable).onScroll(4)
(handler as MouseClickHandler).mouseDown(10, 14)

// Use of smart type casting to conditionally invoke onScroll and mouseDown
if (handler is Scrollable) handler.onScroll(4)
if (handler is MouseClickHandler) handler.mouseDown(10, 14)

// A local object can only be used in local and private declarations.
fun adHocObject() {
    val obj = object {
        val x: Int = 20
        val y: Int = 30
    }
    check(obj.x == 20 && obj.y == 30)
}

adHocObject()


// Object expressions can access and modify variables from the encosing scope.
var counter = 0
listOf(1, 2, 3, 4).forEach(object : Consumer<Int> {
    override fun accept(t: Int) {
        counter++
    }
})
check(counter == 4)


// Kotlin makes it easy to declare Singletons. The object declaration mechanism is thread-safe.
object IndexGenerator {
    private var curr = AtomicLong(0L)

    fun next() = curr.incrementAndGet()
}

check(IndexGenerator.next() == 1L && IndexGenerator.next() == 2L && IndexGenerator.next() == 3L)

object GlobalHander : Scrollable {
    var invocations = 0

    override fun onScroll(scroll: Int) {
        invocations++
        println("[$invocations] GlobalHandler.onScroll($scroll)")
    }
}
GlobalHander.onScroll(14)
GlobalHander.onScroll(44)
GlobalHander.onScroll(52)


// Kotlin doesn't have static members or member functions. Instead it provides companion objects.
data class RGB(val r: Int, val g: Int, val b: Int, val a: Int) {
    companion object {
        fun rgb(r: Int, g: Int, b: Int): RGB = rgba(r, g, b, 255)

        fun rgba(r: Int, g: Int, b: Int, a: Int): RGB = RGB(r, g, b, a)
    }
}

println(RGB.rgb(100, 0, 50))
println(RGB.rgba(100, 0, 50, 200))


interface Factory<T> {
    fun create(): T
}

class MyClass {
    companion object : Factory<MyClass> {
        override fun create(): MyClass = MyClass()
    }
}

println(MyClass.create())