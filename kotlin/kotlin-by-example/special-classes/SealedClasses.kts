import SealedClasses.Expr.*

// Sealed classes are used for representing restricted class hierarchies. To declare a sealed class,
// you put the 'sealed' modifier before the name of a class and define the hierarchy in a single file or class.
sealed class Expr {
    data class Const(val number: Double) : Expr()
    data class Sum(val e1: Expr, val e2: Expr) : Expr()
    object NotANumber : Expr()
}

// Sealed classes are abstract and are not allowed to have a non private constructors. The key benefit of using sealed
// classes is that we get a compilation error if 'when' expression doesn't cover all cases.
fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    is NotANumber -> Double.NaN
}

check(eval(NotANumber).isNaN())
check(eval(Const(4.0)) == 4.0)
check(eval(Sum(Const(4.0), Const(3.5))) == 7.5)
