package kata.consolecanvas.canvas

import kata.consolecanvas.common.ignore
import java.util.*
import kotlin.math.max
import kotlin.math.min

/**
 * A virtual drawing area that supports some basic graphical operations
 * like drawing points, lines, rectangles, filling shapes
 */
open class Canvas2D(val width: Int, val height: Int) {
    private val canvasData: CanvasData

    init {
        require(width >= 0 && height >= 0) {
            "Cannot initialize canvas of negative size: width = $width, height = $height"
        }

        canvasData = emptyCanvas()
    }

    open fun setPixel(x: Int, y: Int, colour: Colour) = setPixel(Point(x, y), colour)

    open fun setPixel(point: Point, colour: Colour) {
        if (inBounds(point.x, point.y)) {
            canvasData[width * (point.y - 1) + point.x - 1] = colour
        }
    }

    open fun pixelAt(x: Int, y: Int): Colour = pixelAt(Point(x, y))

    open fun pixelAt(point: Point): Colour = when {
        inBounds(point.x, point.y) -> canvasData[width * (point.y - 1) + point.x - 1]
        else -> emptyColour
    }

    open fun drawLine(from: Point, to: Point): Unit =
            when {
                from.x > to.x -> drawLine(to, from)
                from.y == to.y -> drawHorizontalLine(from, to)
                from.x == to.x -> drawVerticalLine(from, to)
                else -> ignore
            }

    open fun drawRect(upperLeftCorner: Point, lowerRightCorner: Point) {
        val upperRightCorner = Point(upperLeftCorner.x, lowerRightCorner.y)
        val lowerLeftCorner = Point(lowerRightCorner.x, upperLeftCorner.y)

        drawLine(upperLeftCorner, upperRightCorner)
        drawLine(upperLeftCorner, lowerLeftCorner)
        drawLine(lowerLeftCorner, lowerRightCorner)
        drawLine(upperRightCorner, lowerRightCorner)
    }

    open fun fill(startPoint: Point, fillColour: Colour) = with(Stack<Point>()) {
        val replaceColour = pixelAt(startPoint)

        fun pushIfInBounds(vararg points: Point) {
            for (point in points) {
                if (inBounds(point.x, point.y)) push(point)
            }
        }

        if (replaceColour != fillColour) {
            pushIfInBounds(startPoint)

            while (isNotEmpty()) {
                val point = pop()
                if (pixelAt(point) == replaceColour) {
                    setPixel(point, fillColour)
                    pushIfInBounds(
                            Point(point.x + 1, point.y),
                            Point(point.x - 1, point.y),
                            Point(point.x, point.y + 1),
                            Point(point.x, point.y - 1)
                    )
                }
            }
        }
    }

    override fun toString(): String = display()

    open fun display(): String = with(StringBuilder()) {
        if (width > 0 && height > 0) {
            val horizontalBorder = "-".repeat(width + 2)
            val verticalBorder = "|"
            appendln(horizontalBorder)

            for (row in 1..height) {
                append(verticalBorder)
                for (col in 1..width) {
                    append(pixelAt(col, row))
                }
                appendln(verticalBorder)
            }

            append(horizontalBorder)
        }

        toString()
    }

    open fun forEach(f: ((Point, Colour) -> Unit)) {
        for (y in 1..height) {
            for (x in 1..width) {
                val point = Point(x, y)
                f(point, pixelAt(point))
            }
        }
    }

    open fun getData(): CanvasData = canvasData.copyOf()

    open fun setData(canvasData: CanvasData) = canvasData.copyInto(this.canvasData)

    private fun drawHorizontalLine(from: Point, to: Point) =
            (from.x..to.x).forEach { x -> setPixel(Point(x, from.y), lineColour) }

    private fun drawVerticalLine(from: Point, to: Point) {
        val (y1, y2) = Pair(min(from.y, to.y), max(from.y, to.y))
        (y1..y2).forEach { y -> setPixel(Point(from.x, y), lineColour) }
    }

    private fun inBounds(x: Int, y: Int) = x in 1..width && y in 1..height

    private fun emptyCanvas(): CanvasData {
        val canvas = CanvasData(width * height)
        canvas.fill(emptyColour)
        return canvas
    }
}