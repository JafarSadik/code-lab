package kata.consolecanvas.canvas

interface ShapePredicate {

    fun contains(x: Int, y: Int): Boolean
}

interface Shape : ShapePredicate

class HorizontalLine(val xRange: IntRange, val y: Int) : Shape {

    override fun contains(x: Int, y: Int) = x in xRange && y == this.y
}

class VerticalLine(val x: Int, val yRange: IntRange) : Shape {

    override fun contains(x: Int, y: Int) = x == this.x && y in yRange
}

class Rectangle(val x1: Int, val y1: Int, val x2: Int, val y2: Int) : Shape {

    override fun contains(x: Int, y: Int) = (x == x1 || x == x2) && y in y1..y2 || x in x1..x2 && (y == y1 || y == y2)
}

class FilledRectangle(val x1: Int, val y1: Int, val x2: Int, val y2: Int) : Shape {

    override fun contains(x: Int, y: Int) = x in x1..x2 && y in y1..y2
}

fun Point.on(shape: Shape) = shape.contains(x, y)