// The delegation pattern has proven to be a good alternative to implementation inheritance.
// It allows a flexible object composition free of problems caused by inheritance.
// Kotlin supports it natively requiring zero boilerplate code.
interface Base {
    fun eval(): Int
}

class BaseImpl(val x: Int) : Base {
    override fun eval(): Int = x
}

val base = BaseImpl(20)

class Derived1(base: Base) : Base by base
check(Derived1(base).eval() == 20)

class Derived2() : Base by BaseImpl(30)
check(Derived2().eval() == 30)

class Derived3(evalTo: Int) : Base by BaseImpl(evalTo)
check(Derived3(40).eval() == 40)

// Overriding a member of an instance implemented by delegation
class Derived4(evalTo: Int) : Base by BaseImpl(evalTo) {
    override fun eval(): Int = 0
}
check(Derived4(100).eval() == 0)

class Derived5(val base: Base) : Base by base {
    override fun eval(): Int = base.eval() * 2
}
check(Derived5(base).eval() == 40)

class Derived6(evalTo: Int, val base: Base = BaseImpl(evalTo)) : Base by base {
    override fun eval(): Int = base.eval() * 2
}
check(Derived6(100).eval() == 200)

